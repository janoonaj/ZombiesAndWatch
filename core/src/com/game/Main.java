package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.characters.zombies.ZombieFactory;
import com.game.test.Test1;

public class Main extends Game {

    private boolean gameStarted;
    private Stage stage;
    private Metronome metronome;
    private InputHandler inputHandler;

    @Override
	public void create () {
        AssetsFactory.instance().loadTextures();
        stage = new Stage();
        metronome = new Metronome();
        inputHandler = new InputHandler();
        Gdx.input.setInputProcessor(inputHandler);
	}
    @Override
    public void render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();

        if(!gameStarted && AssetsFactory.instance().assetsLoaded()) {
            gameStarted = true;
            //TODO: study what Test1 really needs. Maybe some stuff could be injected as weell
            //instead of being created inside.
            setScreen(new Test1(stage, new ZombieFactory(), inputHandler));
        }
    }
}
