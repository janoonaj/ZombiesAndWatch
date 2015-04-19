package com.game.board;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.game.characters.zombies.Zombie;
import com.game.ovnis.Ufo;
import com.game.scenario.House;
import com.game.scenario.Wall;

import java.util.ArrayList;
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
    private int cellSideLength = Gdx.graphics.getWidth() / 17;
    private int screenYCenter = Gdx.graphics.getHeight() / 2;
    public int nonPlayableLeftEdge = 5;
    public int nonPlayableRightEdge = 11;
    private int leftestCellIndex = 1;
    private int rightestCellIndex = 15;
    private List<Cell> cells = new ArrayList<Cell>();

    public GameBoard() {
        for(int cellIndex = 0; cellIndex < rightestCellIndex; cellIndex++) {
            cells.add(new Cell());
        }
    }

    public void buildWall(Wall wall, int pos) {
        cells.get(pos-1).addWall(wall);
    }

    public void buildHouse(House house, int pos) {
        cells.get(pos-1).addHouse(house);
    }

    public void addZombie(int pos, Zombie zombie) {
        cells.get(pos-1).addZombie(zombie);
    }

    public Wall getWall(int pos) {
        return cells.get(pos-1).getWall();
    }
    public House getHouse(int pos) {
        return cells.get(pos-1).getHouse();
    }

    public List<Zombie> getZombies(int pos) {
        return cells.get(pos-1).getZombies();
    }

    public void removeZombie(int pos, Zombie zombie) {
        cells.get(pos-1).removeZombie(zombie);
    }

    public void demolishWall(int pos) {cells.get(pos -1 ).demolishWall();}

    public void demolishHouse(int pos) {
        cells.get(pos -1 ).demolishHouse();
    }

    public void removeZombie(Zombie zombie) {
        for (Cell cell : cells) {
            if(cell.removeZombie(zombie)) return;
        }
    }

    public void addUfo(int pos, Ufo ufo) {
        cells.get(pos-1).addUfo(ufo);
    }

    public void removeUfo(int pos) {
        cells.get(pos-1).removeUfo();
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

    public int getCenterBoard() {
        return (rightestCellIndex + leftestCellIndex) / 2;
    }

    public int getRighestPos() {
        return this.rightestCellIndex;
    }

    public int getLeftestPos() { return this.leftestCellIndex; }

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
