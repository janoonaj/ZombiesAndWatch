package com.game.test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.AssetsFactory;
import com.game.InputHandler;
import com.game.characters.Cowboy;
import com.game.characters.zombies.ZombieFactory;


public class Test1 implements Screen {
    private final ZombieFactory zombieFactory;
    private Stage stage;
    private Cowboy cowboy;
    private GameBoard gameBoard;

    public Test1(Stage stage, ZombieFactory zombieFactory, InputHandler inputHandler) {
        this.stage = stage;
        this.zombieFactory = zombieFactory;
        gameBoard = new GameBoard();
        cowboy = new Cowboy(AssetsFactory.instance().getCowboyBW(), gameBoard);
        stage.addActor(cowboy);
        inputHandler.subscribe(cowboy);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        zombieFactory.getMetronome().update(delta);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
