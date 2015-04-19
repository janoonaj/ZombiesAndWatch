package com.game.characters.zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.Config;
import com.game.Metronome;
import com.game.board.GameBoard;
import com.game.characters.Side;
import com.game.test.Test1;

//TODO: object pooling
public class ZombieFactory {
    private final GameBoard board;
    private final Test1 test1;
    private final Metronome metronomeLeft = new Metronome(Config.timeZombie);
    private final Metronome metronomeRight = new Metronome(Config.timeZombie);

    public ZombieFactory(GameBoard board, Test1 test1) {
        this.board = board;
        this.test1 = test1;
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
        int boardPos = board.getRighestPos();
        Zombie zombie = riseZombie(boardPos, board.getScreenPosZombies(boardPos),
                            Side.LEFT, AssetsFactory.instance().getZombieLeft());
        metronomeRight.subscribe(zombie);
        return zombie;
    }

    private Zombie createZombieLeft() {
        int boardPos = board.getLeftestPos();
        Zombie zombie = riseZombie(boardPos, board.getScreenPosZombies(boardPos),
                                    Side.RIGHT, AssetsFactory.instance().getZombieRight());
        metronomeLeft.subscribe(zombie);
        return zombie;
    }

    private Zombie riseZombie(int boardPos, Vector2 screenCoords, Side side, Texture texture) {
        Zombie zombie = new Zombie(texture, boardPos, side, board, test1);
        zombie.setPosition(screenCoords.x - texture.getWidth() / 2, screenCoords.y);
        board.addZombie(boardPos, zombie);
        return zombie;
    }

    public void deleteZombie(Zombie zombie) {
        metronomeLeft.unsubscribe(zombie);
        metronomeRight.unsubscribe(zombie);
        board.removeZombie(zombie);
    }
}
