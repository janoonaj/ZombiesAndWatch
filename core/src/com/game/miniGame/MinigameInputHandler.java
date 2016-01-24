package com.game.miniGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.game.miniGame.characters.Cowboy;

public class MinigameInputHandler extends InputAdapter {
    private Cowboy player;

    public void subscribe(Cowboy cowboy) {
        player = cowboy;
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
        return false;
    }
}
