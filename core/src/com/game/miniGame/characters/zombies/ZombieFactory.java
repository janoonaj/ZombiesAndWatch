package com.game.miniGame.characters.zombies;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.miniGame.Metronome;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.characters.Side;
import com.game.miniGame.Config;
import com.game.signal.EventListener;
import com.game.signal.SignalListener;

import java.util.List;
import java.util.Random;

//TODO: object pooling
public class ZombieFactory implements SignalListener {
    private final Metronome metronomeLeft = new Metronome(Config.timeZombie);
    private final Metronome metronomeRight = new Metronome(Config.timeZombie);
    private final BoardVO board;

    public ZombieFactory(com.game.miniGame.board.BoardVO board) {
        this.board = board;
    }

    public Metronome getMetronomeLeft() {
        return metronomeLeft;
    }

    public Metronome getMetronomeRight() {
        return metronomeRight;
    }

    public Zombie createZombie(Side side) {
        if (side == Side.LEFT) {
            return createZombieLeft();
        }
        if (side == Side.RIGHT) {
            return createZombieRight();
        }
        return null;
    }

    public Zombie createZombieAtCity() {
        List<Integer> cityPos = board.gameBoard.getCityPositions();
        int randomCityIndex = (new Random()).nextInt(cityPos.size());
        Texture texture;
        Side side;
        if(randomCityIndex <= cityPos.size() / 2) {
            texture = AssetsFactory.instance().getZombieRight();
            side = Side.RIGHT;
        } else {
            texture = AssetsFactory.instance().getZombieLeft();
            side = Side.LEFT;
        }
        int boardPos = cityPos.get(randomCityIndex);

        Vector2 screenCoords = board.gameScreenPos.getScreenPosZombies(boardPos);
        Zombie zombie = new ZombieFromBelow(texture,
                                        AssetsFactory.instance().getZombieHead(), boardPos, side, board);
        zombie.setPosition(screenCoords.x - texture.getWidth() / 2, screenCoords.y);
        board.gameBoard.addZombie(boardPos, zombie);
        zombie.onKilled.add(new EventListener(this));
        metronomeRight.subscribe(zombie);
        return zombie;
    }

    private Zombie createZombieRight() {
        int boardPos = board.gameBoard.getRighestPos();
        Zombie zombie = riseZombie(boardPos, board.gameScreenPos.getScreenPosZombies(boardPos),
                Side.LEFT, AssetsFactory.instance().getZombieLeft());
        metronomeRight.subscribe(zombie);
        return zombie;
    }

    private Zombie createZombieLeft() {
        int boardPos = board.gameBoard.getLeftestPos();
        Zombie zombie = riseZombie(boardPos, board.gameScreenPos.getScreenPosZombies(boardPos),
                                    Side.RIGHT, AssetsFactory.instance().getZombieRight());
        metronomeLeft.subscribe(zombie);
        return zombie;
    }

    public Zombie createZombieAt(int pos) {
        Zombie zombie = riseZombie(pos, board.gameScreenPos.getScreenPosZombies(pos),
                Side.RIGHT, AssetsFactory.instance().getZombieRight());
        metronomeLeft.subscribe(zombie);
        return zombie;
    }

    private Zombie riseZombie(int boardPos, Vector2 screenCoords, Side side, Texture texture) {
        Zombie zombie = new Zombie(texture, boardPos, side, board);
        zombie.setPosition(screenCoords.x - texture.getWidth() / 2, screenCoords.y);
        board.gameBoard.addZombie(boardPos, zombie);
        zombie.onKilled.add(new EventListener(this));
        return zombie;
    }

    @Override
    //Delete zombie
    public void signalReceived(Signal signal, Object data) {
        Zombie zombie = (Zombie)data;
        metronomeLeft.unsubscribe(zombie);
        metronomeRight.unsubscribe(zombie);
        board.gameBoard.removeZombie(zombie);
    }
}
