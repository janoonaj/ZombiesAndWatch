package com.game;


import com.game.characters.Rhythmical;

import java.util.ArrayList;
import java.util.List;

public class Metronome {
    private float currentTime = 0;
    List<Rhythmical> subscribers = new ArrayList<Rhythmical>();

    public boolean update(float delta) {
        currentTime += delta;
        if(currentTime >= Config.timeZombie) {
            currentTime = 0f;
            for (Rhythmical subscriber : subscribers) {
                subscriber.work();
            }
            return true;
        }
        return false;
    }

    public void subscribe(Rhythmical rhythmical) {
        subscribers.add(rhythmical);
    }

    public void unsubscribe(Rhythmical rhythmical) {
        subscribers.remove(rhythmical);
    }
}

