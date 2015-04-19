package com.game.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/*
Sets and manages screen positions, x and y coordenades of all elements on screen.
 */
public class GameScreenPos {
    private final int cellSideLength;
    private final int screenYCenter = Gdx.graphics.getHeight() / 2;
    private final GameBoard gameBoard;

    public GameScreenPos(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        //2 => right and left screen margin.
        cellSideLength = Gdx.graphics.getWidth() / (gameBoard.getRighestPos() + 2);
    }

    public Vector2 getScreenPosZombies(int boardPos) {
        return new Vector2(xAt(boardPos), screenYCenter);
    }

    public Vector2 getScreenPosOvni(int boardPos) {
        return new Vector2(xAt(boardPos), Gdx.graphics.getHeight() - cellSideLength);
    }

    public Vector2 getScreen2Cowboy(int boardPos) {
        return new Vector2(xAt(boardPos), screenYCenter + cellSideLength);
    }

    public Vector2 getLeftEdgeSceenPos(int boardPos) {
        return new Vector2(xLeftEdgeOf(boardPos), screenYCenter);
    }

    public Vector2 getRightEdgeSceenPos(int boardPos) {
        return new Vector2(xRightEdgeOf(boardPos), screenYCenter);
    }

    //Returns center point of a board position.
    private int xAt(int boardPos) {
        return (cellSideLength * boardPos) + cellSideLength / 2;
    }

    //Returns left point of a board position
    private int xLeftEdgeOf(int boardPos) {
        return (cellSideLength * boardPos);
    }

    private int xRightEdgeOf(int boardPos) {
        return (cellSideLength * boardPos + cellSideLength);
    }
}
