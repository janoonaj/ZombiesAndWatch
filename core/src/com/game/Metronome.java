package com.game;


import com.game.characters.Rhythmical;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Metronome {
    private float currentTime;
    private float updateTime;
    List<Rhythmical> subscribers = new ArrayList<Rhythmical>();

    public Metronome(float updateTime) {
        this.updateTime = updateTime;
        this.currentTime = (new Random().nextFloat() * updateTime);
    }

    public boolean update(float delta) {
        currentTime += delta;
        if(currentTime >= updateTime) {
            currentTime = 0f;
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

    public void subscribe(Rhythmical rhythmical) {
        subscribers.add(rhythmical);
    }

    public void unsubscribe(Rhythmical rhythmical) {
        subscribers.remove(rhythmical);
    }
}

