package com.game.board;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

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
    private List<Integer> wallsPosition; //left side of the cell

    public GameBoard() {
        wallsPosition = Arrays.asList(6, 11);
    }

    public Vector2 getScreenPosZombies(int boardPos) {
        return new Vector2(xAt(boardPos), screenYCenter);
    }

    public Vector2 getScreen2Cowboy(int boardPos) {
        return new Vector2(xAt(boardPos), screenYCenter + cellWidth);
    }

    public Vector2 getLeftEdgeSceenPos(int boardPos) {
        return new Vector2(xLeftEdgeOf(boardPos), screenYCenter);
    }

    public Vector2 getRightEdgeSceenPos(int boardPos) {
        return new Vector2(xRightEdgeOf(boardPos), screenYCenter);
    }

    public PositionOnBoardVO getRightest() {
        int rightestPos = 15;
        return new PositionOnBoardVO(getScreenPosZombies(rightestPos), rightestPos);
    }

    public PositionOnBoardVO getLeftest() {
        int leftestPos = 1;
        return new PositionOnBoardVO(getScreenPosZombies(leftestPos), leftestPos);
    }

    public List<Integer> getZombieLimits() {
        return Arrays.asList(5, 11);
    }

    public List<PositionOnBoardVO> getWalls() {
        return Arrays.asList(new PositionOnBoardVO(getLeftEdgeSceenPos(wallsPosition.get(0)), wallsPosition.get(0)),
                new PositionOnBoardVO(getRightEdgeSceenPos(wallsPosition.get(1)), wallsPosition.get(1)));
    }

    //Returns center point of a board position.
    private int xAt(int boardPos) {
        return (cellWidth * boardPos) + cellWidth / 2;
    }

    //Returns left point of a board position
    private int xLeftEdgeOf(int boardPos) {
        return (cellWidth * boardPos);
    }

    private int xRightEdgeOf(int boardPos) {
        return (cellWidth * boardPos + cellWidth);
    }
}
