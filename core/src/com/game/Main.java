package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.buildCityMenu.Menu;
import com.game.miniGame.InputHandler;

public class Main extends Game {

    private boolean gameStarted;
    private Stage miniGameStage;
    private Stage cityMenuStage;
    private InputHandler inputHandler;

    @Override
	public void create () {
        AssetsFactory.instance().loadTextures();
        miniGameStage = new Stage();
        cityMenuStage = new Stage();
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

            //MiniGame
            //setScreen(new Test1(miniGameStage, inputHandler));

            //Build city main menu
            setScreen(new Menu(cityMenuStage));
        }
    }
}
