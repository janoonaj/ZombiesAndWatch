package com.game.board;

import com.game.characters.zombies.Zombie;
import com.game.characters.ufos.Ufo;
import com.game.scenario.House;
import com.game.scenario.Wall;

import java.util.ArrayList;
import java.util.List;

//Can have one or more zombies on a cell.
//There is not such a thing like "wall at the left side of the cell" or "wall right". That's just visual.

public class Cell {

    private List<Zombie> zombies = new ArrayList<Zombie>();
    private Wall wall;
    private House house;
    private Ufo ufo;

    public void addWall(Wall wall) {
        this.wall = wall;
    }

    public void demolishWall() { this.wall = null; }

    public void addHouse(House house) {
        this.house = house;
    }

    public void demolishHouse() {
        this.house = null;
    }

    public Wall getWall() {
        return wall;
    }

    public House getHouse() { return house; }

    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public boolean removeZombie(Zombie zombie) {
        return zombies.remove(zombie);
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public boolean removeUfo() {
        boolean hasUfo = (ufo != null);
        this.ufo = null;
        return hasUfo;
    }

    public void addUfo(Ufo ufo) {
        this.ufo = ufo;
    }

    public Ufo getUfo() {
        return ufo;
    }
}
