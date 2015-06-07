package com.game;


import com.game.characters.Rhythmical;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Metronome {
    private float currentTime;
    private float updateTime;
    private float newUpdateTimeAccelerated = 0f;
    List<Rhythmical> subscribers = new ArrayList<Rhythmical>();
    List<Rhythmical> subscribersToRemove = new ArrayList<Rhythmical>();

    public Metronome(float updateTime) {
        this.updateTime = updateTime;
        this.currentTime = (new Random().nextFloat() * updateTime);
    }

    public boolean update(float delta) {
        currentTime += delta;
        if(currentTime >= updateTime) {
            currentTime = 0f;
            refreshUpdateTimeIfItHasToAccelerate();
            removeElements();
            for (Rhythmical subscriber : subscribers) {
                subscriber.updatePos();
            }
            for (Rhythmical subscriber : subscribers) {
                subscriber.draw();
            }
            return true;
        }
        return false;
    }

    private void refreshUpdateTimeIfItHasToAccelerate() {
        if(newUpdateTimeAccelerated == 0f) return;

        Logger.print("accelerated: before was " + updateTime + " now is " + newUpdateTimeAccelerated);
        updateTime = newUpdateTimeAccelerated;
        newUpdateTimeAccelerated = 0f;
    }

    private void removeElements() {
        for(Rhythmical subscriberToRemove : subscribersToRemove) {
            subscribers.remove(subscriberToRemove);
        }
        subscribersToRemove.clear();
    }

    public void subscribe(Rhythmical rhythmical) {
        subscribers.add(rhythmical);
    }

    public void unsubscribe(Rhythmical rhythmical) {
        //Elements will be deleted one step after requested. This is because
        //we don't want to change the Collection on one thread when it's been used
        //on another thread (ConcurrentModificationException)
        subscribersToRemove.add(rhythmical);
    }

    public void accelerate(float percentage) {
        newUpdateTimeAccelerated = updateTime - ((updateTime / 100 ) * percentage);
    }
}

