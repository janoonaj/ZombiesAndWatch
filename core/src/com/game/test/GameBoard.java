package com.game.test;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/*
Positions
margin - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14 - 15 - margin
1-5 && 11-15 zombies
6-10 player
8 starting point
 */
public class GameBoard {
    private int cellWidth = Gdx.graphics.getWidth() / 17;
    private int screenYCenter = Gdx.graphics.getHeight() / 2;

    public Vector2 getScreenPos(int boardPos) {
        return new Vector2(xAt(boardPos), screenYCenter);
    }

    //Returns center point of a board position.
    private int xAt(int boardPos) {
        return (cellWidth * boardPos) + cellWidth / 2;
    }
}
