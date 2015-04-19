package com.game.characters.zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.Config;
import com.game.board.GameBoard;
import com.game.characters.Side;
import com.game.characters.Rhythmical;
import com.game.test.Test1;

import java.util.Random;

public class Zombie extends Image implements Rhythmical {
    private final Test1 test1;
    private int boardPos;
    private final GameBoard gameBoard;
    private final Side side;
    private int health = Config.healthZombie;

    public Zombie(Texture texture, int boardPos, Side side, GameBoard gameBoard, Test1 test1) {
        super(texture);
        this.boardPos = boardPos;
        this.gameBoard = gameBoard;
        this.side = side;
        this.test1 = test1;
    }

    public void updatePos() {
        int nextPos = boardPos;
        if (side == Side.LEFT) {
            nextPos = boardPos - 1; if(nextPos < 1 ) return;
        }
        if (side == Side.RIGHT) {
            nextPos = boardPos + 1; if(nextPos > gameBoard.getRighestPos()) return;
        }
        if(gameBoard.getWall(nextPos) != null) {
            gameBoard.getWall(nextPos).damage(Config.zombieDamage);
            return;
        }

        if(gameBoard.getHouse(nextPos) != null) {
            gameBoard.getHouse(nextPos).damage(Config.zombieDamage);
            return;
        }

        changePos(nextPos);
    }

    public void draw() {
        float nextX = gameBoard.getScreenPosZombies(boardPos).x - this.getImageWidth() / 2;
        float nextY = gameBoard.getScreenPosZombies(boardPos).y;
        if(gameBoard.getZombies(boardPos).size() > 1) {
            //temp "horde" effect
            nextY += new Random().nextInt(40) - 20;
            nextX += new Random().nextInt(10) - 5;
        }
        setPosition(nextX, nextY);
    }

    private void changePos(int newPos) {
        gameBoard.removeZombie(boardPos, this);
        gameBoard.addZombie(newPos, this);
        boardPos = newPos;
    }

    public void damage(int pointsOfDamage) {
        health -= pointsOfDamage;
        if(health <= 0) {
            this.test1.killZombie(this);
            remove();
        }
    }
}
