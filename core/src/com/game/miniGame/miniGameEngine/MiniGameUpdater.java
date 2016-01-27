package com.game.miniGame.miniGameEngine;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.miniGame.characters.Side;
import com.game.miniGame.characters.militia.MilitiaController;
import com.game.miniGame.characters.ufos.Ufo;
import com.game.miniGame.characters.ufos.UfoFactory;
import com.game.miniGame.characters.zombies.ZombieFactory;
import com.game.signal.EventListener;
import com.game.signal.SignalListener;

import java.util.Random;

public class MiniGameUpdater implements SignalListener {

    private final MilitiaController militiaController;
    private final Stage stage;
    private final ZombieFactory zombieFactory;
    private final UfoFactory ufoFactory;
    private final OrchestraConductor orchestraConductor;
    private final int numMaxUFO;
    private int currentNumUFO = 0;

    public MiniGameUpdater(Stage stage, FactoriesVO factories, int numMaxUFO, OrchestraConductor orchestraConductor, MilitiaController militiaController) {
        this.stage = stage;
        this.zombieFactory = factories.zombieFactory;
        this.ufoFactory = factories.ufoFactory;
        this.numMaxUFO = numMaxUFO;
        this.orchestraConductor = orchestraConductor;
        this.militiaController = militiaController;
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
        orchestraConductor.update(delta);
        militiaController.getMetronome().update(delta);
    }

    private void createZombies(Side side) {
        int probabilityCreateZombie = 30;
        int randCreateZombies = new Random().nextInt(99) + 1;
        if (randCreateZombies <= probabilityCreateZombie) {
            stage.addActor(zombieFactory.createZombie(side));
        }
        int probabilityCreateZombieCenter = 15;
        int randCreateZombiesCenter = new Random().nextInt(99) + 1;
        if (randCreateZombiesCenter <= probabilityCreateZombieCenter) {
            stage.addActor(zombieFactory.createZombieAtCity());
        }
    }

    private void createUfo() {
        if(currentNumUFO >= numMaxUFO) return;
        int probabilityCreateUFO = 10;
        int randCreateUFO = new Random().nextInt(99) + 1;
        if (randCreateUFO <= probabilityCreateUFO) {
            currentNumUFO++;
            Ufo newUfo = ufoFactory.createUfo(Side.random());
            stage.addActor(newUfo);
            newUfo.onFlyAway.add(new EventListener(this));
            newUfo.onDestroyed.add(new EventListener(this));
        }
    }

    @Override
    public void signalReceived(Signal signal, Object data) {
        currentNumUFO--;
    }
}
