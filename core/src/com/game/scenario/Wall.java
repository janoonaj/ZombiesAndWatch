package com.game.scenario;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.game.Config;
import com.game.miniGameEngine.GameEngine;
import com.game.test.Test1;

import java.util.HashMap;

public class Wall extends Image {

    private enum Status {
        NORMAL(Config.healthWall),
        MEDIUM(Config.healthWall - Config.healthWall / 3),
        WEAK(Config.healthWall / 3),
        BROKEN(0);

        int lifePoints;

        Status(int lifePoints) {
            this.lifePoints = lifePoints;
        }

        public int getLifePoints() {
            return lifePoints;
        }
    }

    private final GameEngine gameEngine;
    private final int boardPos;
    HashMap<Status, Texture> textures = new HashMap<Status, Texture>();
    private int health = Config.healthWall;
    private Status currentStatus = Status.NORMAL;

    public Wall(Texture textureStrong, Texture textureMed, Texture textureWeak, int boardPos, GameEngine gameEngine) {
        super(textureStrong);
        this.gameEngine = gameEngine;
        this.boardPos = boardPos;

        textures.put(Status.NORMAL, textureStrong);
        textures.put(Status.MEDIUM, textureMed);
        textures.put(Status.WEAK, textureWeak);
        updateStatus();
    }

    public void damage(int pointsOfDamage) {
        health -= pointsOfDamage;
        updateStatus();
        if (health <= 0) {
            this.gameEngine.demolishWall(boardPos);
            remove();
        }
    }

    private void updateStatus() {
        Status newStatus = calculateNewStatus();
        if (newStatus == currentStatus) return;

        currentStatus = newStatus;
        setDrawable(new SpriteDrawable(new Sprite(textures.get(currentStatus))));
    }

    private Status calculateNewStatus() {
        if (health > Status.MEDIUM.getLifePoints()) return Status.NORMAL;
        else if (health > Status.WEAK.getLifePoints()) return Status.MEDIUM;
        return Status.WEAK;
    }
}
