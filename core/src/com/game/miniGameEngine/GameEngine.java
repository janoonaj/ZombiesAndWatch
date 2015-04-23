package com.game.miniGameEngine;

import com.game.characters.zombies.Zombie;

public class GameEngine {
    FactoriesVO factories;

    public GameEngine(FactoriesVO factories) {
        this.factories = factories;
        factories.houseFactory.setEngine(this);
        factories.wallFactory.setEngine(this);
        factories.zombieFactory.setEngine(this);
        factories.ufoFactory.setEngine(this);
    }

    public void killZombie(Zombie zombie) {
        factories.zombieFactory.deleteZombie(zombie);
    }

    public void demolishWall(int boardPos) {
        factories.wallFactory.demolish(boardPos);
    }

    public void demolishHouse(int boardPos) { factories.houseFactory.demolish(boardPos);   }
}
