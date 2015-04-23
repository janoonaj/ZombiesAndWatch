package com.game.scenario;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.Config;
import com.game.miniGameEngine.GameEngine;
import com.game.test.Test1;


/*
TODO: different number of life points for ach house?
 */
public class House extends Image {
    private final int boardPos;
    private int health = Config.healthHouse;
    private final GameEngine gameEngine;

    public House(Texture texture, int boardPos, GameEngine gameEngine) {
        super(texture);
        this.gameEngine = gameEngine;
        this.boardPos = boardPos;
    }

    public void damage(int pointsOfDamage) {
        health -= pointsOfDamage;
        if(health <= 0) {
            this.gameEngine.demolishHouse(boardPos);
            remove();
        }
    }
}
