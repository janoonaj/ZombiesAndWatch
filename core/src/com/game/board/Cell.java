package com.game.board;

import com.game.characters.zombies.Zombie;
import com.game.scenario.Wall;

import java.util.ArrayList;
import java.util.List;

//Can have one or more zombies on a cell.
//There is not such a thing like "wall at the left side of the cell" or "wall right". That's just visual.

public class Cell {

    List<Zombie> zombies = new ArrayList<Zombie>();
    Wall wall;

    public void addWall(Wall wall) {
        this.wall = wall;
    }

    public Wall getWall() {
        return wall;
    }

    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public void removeZombie(Zombie zombie) {
        zombies.remove(zombie);
    }

    public List<Zombie> getZombies() {
        return zombies;
    }
}
