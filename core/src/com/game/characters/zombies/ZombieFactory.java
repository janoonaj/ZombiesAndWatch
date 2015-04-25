package com.game.characters.zombies;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.Config;
import com.game.Metronome;
import com.game.board.BoardVO;
import com.game.characters.Side;
import com.game.signal.EventListener;
import com.game.signal.SignalListener;

//TODO: object pooling
public class ZombieFactory implements SignalListener {
    private final Metronome metronomeLeft = new Metronome(Config.timeZombie);
    private final Metronome metronomeRight = new Metronome(Config.timeZombie);
    private final BoardVO board;

    public ZombieFactory(BoardVO board) {
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

    private Zombie riseZombie(int boardPos, Vector2 screenCoords, Side side, Texture texture) {
        Zombie zombie = new Zombie(texture, boardPos, side, board);
        zombie.setPosition(screenCoords.x - texture.getWidth() / 2, screenCoords.y);
        board.gameBoard.addZombie(boardPos, zombie);
        zombie.onKilled.add(new EventListener(this));
        return zombie;
    }

    public void deleteZombie(Zombie zombie) {
        metronomeLeft.unsubscribe(zombie);
        metronomeRight.unsubscribe(zombie);
        board.gameBoard.removeZombie(zombie);
    }

    @Override
    public void signalReceived(Signal signal, Object data) {
        deleteZombie((Zombie)data);
    }
}
