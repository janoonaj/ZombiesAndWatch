package com.game.miniGame.miniGameEngine;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.miniGame.characters.ufos.Ufo;
import com.game.miniGame.characters.zombies.ZombieFactory;
import com.game.signal.EventListener;
import com.game.signal.SignalListener;

import java.util.Random;

public class MiniGameUpdater implements SignalListener {

    private Stage stage;
    private ZombieFactory zombieFactory;
    private com.game.miniGame.characters.ufos.UfoFactory ufoFactory;
    private OrchestraConductor orchestraConductor;
    private final int numMaxUFO;
    private int currentNumUFO = 0;

    public MiniGameUpdater(Stage stage, FactoriesVO factories, int numMaxUFO, OrchestraConductor orchestraConductor) {
        this.stage = stage;
        this.zombieFactory = factories.zombieFactory;
        this.ufoFactory = factories.ufoFactory;
        this.numMaxUFO = numMaxUFO;
        this.orchestraConductor = orchestraConductor;
    }

    public void render(float delta) {
        if (zombieFactory.getMetronomeLeft().update(delta)) {
            createZombies(com.game.miniGame.characters.Side.LEFT);
        }
        if (zombieFactory.getMetronomeRight().update(delta)) {
            createZombies(com.game.miniGame.characters.Side.RIGHT);
        }
        if (ufoFactory.getMetronome().update(delta)) {
            createUfo();
        }
        orchestraConductor.update(delta);
    }

    private void createZombies(com.game.miniGame.characters.Side side) {
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
            Ufo newUfo = ufoFactory.createUfo(com.game.miniGame.characters.Side.random());
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
