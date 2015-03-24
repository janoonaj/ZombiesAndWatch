package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.test.Test1;

public class Main extends Game {

    private boolean gameStarted;
    private SpriteBatch batch;

    @Override
	public void create () {
        AssetsFactory.instance().loadTextures();
        batch = new SpriteBatch();
	}

    @Override
    public void render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();

        if(!gameStarted && AssetsFactory.instance().assetsLoaded()) {
            gameStarted = true;
            setScreen(new Test1(batch));
        }
    }
}
