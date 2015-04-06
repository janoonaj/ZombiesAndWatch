package com.game.board;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.game.characters.zombies.Zombie;
import com.game.scenario.Wall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Positions
margin - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14 - 15 - margin
1-5 && 11-15 zombies
6-10 player
8 starting point
 */


////////////////////////
////////////////////////
//TODO this class does two things
//-> Manages elements like zombies and walls
//-> Manages screen pos / coordenades
//
//  REFACTOR will be needed.
//
////////////////////////
////////////////////////


public class GameBoard {
    private int cellWidth = Gdx.graphics.getWidth() / 17;
    private int screenYCenter = Gdx.graphics.getHeight() / 2;
    private List<Cell> cells = new ArrayList<Cell>();

    public GameBoard() {
        for(int cellIndex = 0; cellIndex < 15; cellIndex++) {
            cells.add(new Cell());
        }
    }

    public void addWall(Wall wall, int pos) {
        cells.get(pos-1).addWall(wall);
    }

    public void addZombie(int pos, Zombie zombie) {
        cells.get(pos-1).addZombie(zombie);
    }

    public Wall getWall(int pos) {
        return cells.get(pos-1).getWall();
    }

    public List<Zombie> getZombies(int pos) {
        return cells.get(pos-1).getZombies();
    }

    public void removeZombie(int pos, Zombie zombie) {
        cells.get(pos-1).removeZombie(zombie);
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
