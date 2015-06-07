package com.game.miniGameEngine;

import com.game.Logger;
import com.game.Metronome;

import java.util.ArrayList;
import java.util.List;

//Manages the timing of the minigame. Accelerating al the metronomes whenever it's needed.
public class OrchestraConductor {
    private List<Metronome> metronomes = new ArrayList<Metronome>();
    private List<MiniGameState> states = new ArrayList<MiniGameState>();
    private MiniGameState currentState;

    private float currentTime = 0;
    //List of update times
    private final float secondsToStartStage2 = 20;
    private final float secondsToStartStage3 = 45;
    private final float secondsToStartStage4 = 65;
    private final float secondsToFinishGame = 90;
    private final float percentajeAccelerationStep2 = 15;
    private final float percentajeAccelerationStep3 = 15;
    private final float percentajeAccelerationStep4 = 20;


    public OrchestraConductor(FactoriesVO factories) {
        metronomes.add(factories.ufoFactory.getMetronome());
        metronomes.add(factories.zombieFactory.getMetronomeLeft());
        metronomes.add(factories.zombieFactory.getMetronomeRight());
        states.add(new MiniGameState(0, 0));
        states.add(new MiniGameState(percentajeAccelerationStep2, secondsToStartStage2));
        states.add(new MiniGameState(percentajeAccelerationStep3, secondsToStartStage3));
        states.add(new MiniGameState(percentajeAccelerationStep4, secondsToStartStage4));
        states.add(new MiniGameState(0, secondsToFinishGame));
        currentState = states.get(0);
    }

    public void update(float delta) {
        currentTime += delta;
        MiniGameState perfectState = whichStateIShouldBe(currentTime);
        if(perfectState != currentState) {
            currentState = perfectState;
            for(int indexMetronomes = 0; indexMetronomes < metronomes.size(); indexMetronomes++) {
                metronomes.get(indexMetronomes).accelerate(currentState.acceleratedUpdateTimePercentage);
            }
        }
    }

    private MiniGameState whichStateIShouldBe(float time) {
        for(int indexStates = 1; indexStates < states.size(); indexStates++) {
            if(time < states.get(indexStates).changeTime) return states.get(indexStates - 1);
        }
        return states.get(states.size() - 1);
    }
}

class MiniGameState {
    public float acceleratedUpdateTimePercentage;
    public float changeTime;

    public MiniGameState(float acceleratedUpdateTimePercentage, float changeTime) {
        this.acceleratedUpdateTimePercentage = acceleratedUpdateTimePercentage;
        this.changeTime = changeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MiniGameState that = (MiniGameState) o;

        if (Float.compare(that.acceleratedUpdateTimePercentage, acceleratedUpdateTimePercentage) != 0)
            return false;
        return Float.compare(that.changeTime, changeTime) == 0;

    }

    @Override
    public int hashCode() {
        int result = (acceleratedUpdateTimePercentage != +0.0f ? Float.floatToIntBits(acceleratedUpdateTimePercentage) : 0);
        result = 31 * result + (changeTime != +0.0f ? Float.floatToIntBits(changeTime) : 0);
        return result;
    }
}
