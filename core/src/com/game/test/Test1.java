package com.game.test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.characters.zombies.ZombieFactory;


public class Test1 implements Screen {
    private final ZombieFactory zombieFactory;
    private Stage stage;

    public Test1(Stage stage, ZombieFactory zombieFactory) {
        this.stage = stage;
        this.zombieFactory = zombieFactory;
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
