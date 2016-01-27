package com.game.miniGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.Logger;
import com.game.miniGame.characters.Cowboy;
import com.game.miniGame.characters.militia.MilitiaController;
import com.game.miniGame.characters.militia.Selector;

import java.util.Arrays;
import java.util.List;

public class MinigameInputHandler extends InputAdapter {
    private Cowboy player;
    private MilitiaController militiaController;

    public void subscribe(Cowboy cowboy, MilitiaController militiaController) {
        player = cowboy;
        this.militiaController = militiaController;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            player.pressedLeft();
        }
        if (keycode == Input.Keys.RIGHT) {
            player.pressedRight();
        }
        if(keycode == Input.Keys.SPACE) {
            player.shoot();
        }
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(!touchDownMilitia(screenX, screenY)) {
            touchDownCowboy(screenX, screenY);
        }
        return false;
    }

    private void touchDownCowboy(int screenX, int screenY) {
        if (screenX <= Gdx.graphics.getWidth() / 2) {
            player.pressedLeft();
        } else {
            if(screenY >= Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 5) {
                player.shoot();
            }
            else {
                player.pressedRight();
            }
        }
    }

    private boolean touchDownMilitia(int screenX, int screenY) {
        for(Selector selector : militiaController.getSelectors()) {
            if(collides(selector, new Vector2(screenX, screenY))) {
                militiaController.clickSelector(selector);
                return true;
            }
            if(collides(militiaController.getMilitia(), new Vector2(screenX, screenY))) {
                militiaController.clickMilitia();
                return true;
            }
        }
        return false;
    }

    private boolean collides(Image img, Vector2 screenPos) {
        float mappedY = Gdx.graphics.getHeight() - img.getY();
        return (screenPos.x >= img.getX() && screenPos.x <= img.getX() + img.getImageWidth() &&
                screenPos.y <= mappedY && screenPos.y >= mappedY - img.getImageHeight());
    }
}
