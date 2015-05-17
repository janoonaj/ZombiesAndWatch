package com.game.miniGameEngine;

import com.game.characters.zombies.ZombieFactory;
import com.game.characters.ufos.UfoFactory;
import com.game.scenario.HouseFactory;
import com.game.scenario.WallFactory;

public class FactoriesVO {
    public WallFactory wallFactory;
    public HouseFactory houseFactory;
    public ZombieFactory zombieFactory;
    public UfoFactory ufoFactory;

    public FactoriesVO(ZombieFactory zombieFactory, UfoFactory ufoFactory, WallFactory wallFactory,
                       HouseFactory houseFactory) {
        this.zombieFactory = zombieFactory;
        this.ufoFactory = ufoFactory;
        this.wallFactory = wallFactory;
        this.houseFactory = houseFactory;
    }
}
