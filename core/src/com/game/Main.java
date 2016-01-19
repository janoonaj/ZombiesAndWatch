package com.game;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.game.signal.EventListener;
import com.game.signal.SignalListener;

public class Main extends Game implements SignalListener {

    private boolean gameStarted;
    //private Stage miniGameStage;
    //private Stage cityMenuStage;
    //private InputHandler inputHandler;
    private GameFlow gameFlow;

    @Override
	public void create () {
        AssetsFactory.instance().loadTextures();
        //miniGameStage = new Stage();
        //cityMenuStage = new Stage();
        //inputHandler = new InputHandler();
	}
    @Override
    public void render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();

        if(!gameStarted && AssetsFactory.instance().assetsLoaded()) {
            gameStarted = true;
            gameFlow = new GameFlow();
            gameFlow.onChangeScreen.add(new EventListener(this));
            gameFlow.start();
            //TODO: study what Test1 really needs. Maybe some stuff could be injected as weell
            //instead of being created inside.

            //MiniGame
            //Gdx.input.setInputProcessor(inputHandler);
            //setScreen(new Test1(miniGameStage, inputHandler));

            //Build city main menu
            //Gdx.input.setInputProcessor(cityMenuStage);
            //setScreen(new Menu(cityMenuStage));
        }
    }

    @Override
    public void signalReceived(Signal signal, Object data) {
        setScreen((Screen)data);
    }
}
