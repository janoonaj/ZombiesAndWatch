package com.game.scenario;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.Config;
import com.game.test.Test1;


/*
TODO: different number of life points for ach house?
 */
public class House extends Image {
    private final Test1 test1;
    private final int boardPos;
    private int health = Config.healthHouse;

    public House(Texture texture, Test1 test1, int boardPos) {
        super(texture);
        this.test1 = test1;
        this.boardPos = boardPos;
    }

    public void damage(int pointsOfDamage) {
        health -= pointsOfDamage;
        if(health <= 0) {
            this.test1.demolishHouse(boardPos);
            remove();
        }
    }
}
