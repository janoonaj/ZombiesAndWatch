package com.game.characters.zombies;

import com.game.AssetsFactory;
import com.game.Metronome;

public class ZombieFactory {
    private Metronome metronome = new Metronome();

    public Metronome getMetronome() {
        return metronome;
    }

    public Zombie createZombie() {
        Zombie zombie = new Zombie(AssetsFactory.instance().getCowboyBW());
        metronome.subscribe(zombie);

        //temp
        zombie.setX(300);
        zombie.setY(300);
        zombie.setRotation(100);

        return zombie;
    }
}
