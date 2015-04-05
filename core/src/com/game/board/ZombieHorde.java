package com.game.board;

import com.game.characters.zombies.Zombie;

import java.util.ArrayList;
import java.util.List;

public class ZombieHorde {
    private List<Zombie> horde = new ArrayList<Zombie>();

    public void addZ(Zombie zombie) {
        horde.add(zombie);
    }

    public void addZ(List<Zombie> miniHorde) {
        horde.addAll(miniHorde);
    }

    public void killZ(Zombie zombie) {
        horde.remove(zombie);
    }
}
