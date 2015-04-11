package com.game.characters.zombies;

import com.badlogic.gdx.graphics.Texture;
import com.game.AssetsFactory;
import com.game.Config;
import com.game.Metronome;
import com.game.board.GameBoard;
import com.game.board.PositionOnBoardVO;
import com.game.test.Test1;

//TODO: object pooling
public class ZombieFactory {
    private final GameBoard board;
    private final Metronome metronomeLeft = new Metronome(Config.timeZombie);
    private final Metronome metronomeRight = new Metronome(Config.timeZombie);

    public enum Side {RIGHT, LEFT}

    public ZombieFactory(GameBoard board) {
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
        Zombie zombie = riseZombie(board.getRightest(), Movement.LEFT, AssetsFactory.instance().getZombieLeft());
        metronomeRight.subscribe(zombie);
        return zombie;
    }

    private Zombie createZombieLeft() {
        Zombie zombie = riseZombie(board.getLeftest(), Movement.RIGHT, AssetsFactory.instance().getZombieRight());
        metronomeLeft.subscribe(zombie);
        return zombie;
    }

    private Zombie riseZombie(PositionOnBoardVO pos, Movement movement, Texture texture) {
        Zombie zombie = new Zombie(texture, pos.getBoardPos(), movement, board);
        zombie.setPosition(pos.getScreenCoords().x, pos.getScreenCoords().y);
        board.addZombie(pos.getBoardPos(), zombie);
        return zombie;
    }

    public void deleteZombie(Zombie zombie) {
        metronomeLeft.unsubscribe(zombie);
        board.removeZombie(zombie);
    }
}
