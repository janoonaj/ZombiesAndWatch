package com.game.miniGameEngine;

import com.game.characters.zombies.ZombieFactory;
import com.game.ovnis.UfoFactory;

public class FactoriesVO {
    public ZombieFactory zombieFactory;
    public UfoFactory ufoFactory;

    public FactoriesVO(ZombieFactory zombieFactory, UfoFactory ufoFactory) {
        this.zombieFactory = zombieFactory;
        this.ufoFactory = ufoFactory;
    }
}
