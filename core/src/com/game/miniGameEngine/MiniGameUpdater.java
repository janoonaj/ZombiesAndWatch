package com.game.miniGameEngine;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.characters.Side;
import com.game.characters.zombies.ZombieFactory;
import com.game.ovnis.UfoFactory;

import java.util.Random;

public class MiniGameUpdater {

    private Stage stage;
    private ZombieFactory zombieFactory;
    private UfoFactory ufoFactory;
    private final int numMaxUFO;
    private int currentNumUFO = 0;

    public MiniGameUpdater(Stage stage, FactoriesVO factories, int numMaxUFO) {
        this.stage = stage;
        this.zombieFactory = factories.zombieFactory;
        this.ufoFactory = factories.ufoFactory;
        this.numMaxUFO = numMaxUFO;
    }

    public void render(float delta) {
        if (zombieFactory.getMetronomeLeft().update(delta)) {
            createZombies(Side.LEFT);
        }
        if (zombieFactory.getMetronomeRight().update(delta)) {
            createZombies(Side.RIGHT);
        }
        if (ufoFactory.getMetronome().update(delta)) {
            createUfo();
        }
    }

    private void createZombies(Side side) {
        int probabilityCreateZombie = 60;
        int randCreateZombies = new Random().nextInt(99) + 1;
        if (randCreateZombies <= probabilityCreateZombie) {
            stage.addActor(zombieFactory.createZombie(side));
        }
    }

    private void createUfo() {
        if(currentNumUFO >= numMaxUFO) return;
        int probabilityCreateUFO = 100;
        int randCreateUFO = new Random().nextInt(99) + 1;
        if (randCreateUFO <= probabilityCreateUFO) {
            currentNumUFO++;
            stage.addActor(ufoFactory.createUfo(Side.random()));
        }
    }
}
