package com.game.test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.AssetsFactory;



/*
Hacer un sistema de stage con actores. No tenemos que reimplemntar mil veces cosas
como eventos touch o posición x/y.

 */

public class Test1 implements Screen {
    private SpriteBatch batch;

    public Test1(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(AssetsFactory.instance().getCowboyBW(), 300, 300);
        batch.end();
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
