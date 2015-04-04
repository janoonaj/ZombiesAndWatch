package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.game.test.Interactive;

import java.util.ArrayList;
import java.util.List;

public class InputHandler extends InputAdapter {
    List<Interactive> interactives = new ArrayList<Interactive>();

    public void subscribe(Interactive interactive) {
        interactives.add(interactive);
    }

    public void unsubscribe(Interactive interactive) {
        interactives.remove(interactive);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            pressLeft();
        }
        if (keycode == Input.Keys.RIGHT) {
            pressRight();
        }
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX <= Gdx.graphics.getWidth() / 2) {
            pressLeft();
        } else {
            pressRight();
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

}
