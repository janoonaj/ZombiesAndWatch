package com.game.board;


import com.game.characters.zombies.Zombie;
import com.game.characters.ufos.Ufo;
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


public class GameBoard {
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

    public void removeUfo(Ufo ufo) {
        for (Cell cell : cells) {
            if(cell.removeUfo()) return;
        }
    }

    public int getCenterBoard() {
        return (rightestCellIndex + leftestCellIndex) / 2;
    }

    public int getRighestPos() {
        return this.rightestCellIndex;
    }

    public int getLeftestPos() { return this.leftestCellIndex; }

    public List<Integer> getPosWithHouses() {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for (int boardPos = leftestCellIndex; boardPos <= rightestCellIndex; boardPos++) {
            if(cells.get(boardPos-1).getHouse() != null) positions.add(boardPos);
        }
        return positions;
    }
}