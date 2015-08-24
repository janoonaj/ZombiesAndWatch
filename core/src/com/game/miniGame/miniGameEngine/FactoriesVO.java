package com.game.miniGame.miniGameEngine;

import com.game.miniGame.characters.zombies.ZombieFactory;
import com.game.miniGame.characters.ufos.UfoFactory;
import com.game.miniGame.scenario.HouseFactory;

public class FactoriesVO {
    public com.game.miniGame.scenario.WallFactory wallFactory;
    public HouseFactory houseFactory;
    public ZombieFactory zombieFactory;
    public UfoFactory ufoFactory;

    public FactoriesVO(ZombieFactory zombieFactory, UfoFactory ufoFactory, com.game.miniGame.scenario.WallFactory wallFactory,
                       HouseFactory houseFactory) {
        this.zombieFactory = zombieFactory;
        this.ufoFactory = ufoFactory;
        this.wallFactory = wallFactory;
        this.houseFactory = houseFactory;
    }
}
