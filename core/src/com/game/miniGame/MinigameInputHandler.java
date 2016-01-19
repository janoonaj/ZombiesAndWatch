package com.game.miniGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.game.miniGame.test.Interactive;

import java.util.ArrayList;
import java.util.List;

public class MinigameInputHandler extends InputAdapter {
    List<Interactive> interactives = new ArrayList<Interactive>();

    public void subscribe(Interactive interactive) {
        interactives.add(interactive);
    }

    public void unsubscribeAll() {
        interactives.clear();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            pressLeft();
        }
        if (keycode == Input.Keys.RIGHT) {
            pressRight();
        }
        if(keycode == Input.Keys.SPACE) {
            shoot();
        }
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX <= Gdx.graphics.getWidth() / 2) {
            pressLeft();
        } else {
            if(screenY >= Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 5) {
                shoot();
            }
            else {
                pressRight();
            }
        }
        return false;
    }

    private void pressLeft() {
        for (Interactive interactive : interactives) {
            interactive.pressedLeft();
        }
    }

    private void pressRight() {
        for (Interactive interactive : interactives) {
            interactive.pressedRight();
        }
    }

    private void shoot() {
        for (Interactive interactive : interactives) {
            interactive.shoot();
        }
    }

}
