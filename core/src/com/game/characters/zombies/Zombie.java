package com.game.characters.zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.Logger;
import com.game.board.GameBoard;
import com.game.characters.Rhythmical;

import java.util.List;
import java.util.Random;

public class Zombie extends Image implements Rhythmical {

    private int boardPos;
    private final GameBoard gameBoard;
    private final Movement movement;

    public Zombie(Texture texture, int boardPos, Movement movement, GameBoard gameBoard) {
        super(texture);
        this.boardPos = boardPos;
        this.gameBoard = gameBoard;
        this.movement = movement;
    }

    public void updatePos() {
        int nextPos = boardPos;
        if (movement == Movement.LEFT) {
            nextPos = boardPos - 1;
        }
        if (movement == Movement.RIGHT) {
            nextPos = boardPos + 1;
        }
        if(gameBoard.getWall(nextPos) != null) {
            return;
        }

        changePos(nextPos);
    }

    public void draw() {
        float nextX = gameBoard.getScreenPosZombies(boardPos).x;
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
}
