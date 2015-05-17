package com.game.scenario;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.Config;
import com.badlogic.ashley.signals.Signal;


/*
TODO: different number of life points for each house?
 */
public class House extends Image {
    private final Integer boardPos;
    private int health = Config.healthHouse;
    public Signal onDemolished = new Signal();

    public House(Texture texture, int boardPos) {
        super(texture);
        this.boardPos = boardPos;
    }

    public void damage(int pointsOfDamage) {
        health -= pointsOfDamage;
        if(health <= 0) {
            onDemolished.dispatch(boardPos);
            onDemolished.removeAllListeners();
            remove();
        }
    }
}
