package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter{

    @Override
    public boolean keyDown (int keycode) {
        if(keycode == Input.Keys.LEFT) {
            pressLeft();
        }
        if(keycode == Input.Keys.RIGHT) {
            pressRight();
        }
        return false;
    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        if(screenX <= Gdx.graphics.getWidth() / 2) {
            pressLeft();
        } else {
            pressRight();
        }
        return false;
    }

    private void pressLeft() {
        //Logger.print("left");
    }

    private void pressRight() {
//Logger.print("right");
    }

}
