package com.game.characters.zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.board.GameBoard;
import com.game.characters.Rhythmical;

import java.util.List;

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

    @Override
    public void work() {
        List<Integer> limits = gameBoard.getZombieLimits();
        if(limits.contains(boardPos)) return;

        if(movement == Movement.LEFT) {
            boardPos--;
        }
        if(movement == Movement.RIGHT) {
            boardPos++;
        }
        setPosition(gameBoard.getScreenPosZombies(boardPos).x, gameBoard.getScreenPosZombies(boardPos).y);
    }
}
