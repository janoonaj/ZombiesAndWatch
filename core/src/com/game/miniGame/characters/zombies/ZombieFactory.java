package com.game.miniGame.characters.zombies;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.miniGame.characters.Side;
import com.game.miniGame.Config;
import com.game.miniGame.signal.EventListener;

//TODO: object pooling
public class ZombieFactory implements com.game.miniGame.signal.SignalListener {
    private final com.game.miniGame.Metronome metronomeLeft = new com.game.miniGame.Metronome(Config.timeZombie);
    private final com.game.miniGame.Metronome metronomeRight = new com.game.miniGame.Metronome(Config.timeZombie);
    private final com.game.miniGame.board.BoardVO board;

    public ZombieFactory(com.game.miniGame.board.BoardVO board) {
        this.board = board;
    }

    public com.game.miniGame.Metronome getMetronomeLeft() {
        return metronomeLeft;
    }

    public com.game.miniGame.Metronome getMetronomeRight() {
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
                            Side.LEFT, com.game.miniGame.AssetsFactory.instance().getZombieLeft());
        metronomeRight.subscribe(zombie);
        return zombie;
    }

    private Zombie createZombieLeft() {
        int boardPos = board.gameBoard.getLeftestPos();
        Zombie zombie = riseZombie(boardPos, board.gameScreenPos.getScreenPosZombies(boardPos),
                                    Side.RIGHT, com.game.miniGame.AssetsFactory.instance().getZombieRight());
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
