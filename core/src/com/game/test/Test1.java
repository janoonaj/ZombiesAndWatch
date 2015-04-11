package com.game.test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.AssetsFactory;
import com.game.InputHandler;
import com.game.board.GameBoard;
import com.game.characters.Cowboy;
import com.game.characters.zombies.Zombie;
import com.game.characters.zombies.ZombieFactory;
import com.game.scenario.Wall;
import com.game.scenario.WallFactory;

import java.util.Random;

public class Test1 implements Screen {
    private final ZombieFactory zombieFactory;
    private final WallFactory wallFactory;
    private Stage stage;
    private Cowboy cowboy;
    private GameBoard gameBoard;

    public Test1(Stage stage, InputHandler inputHandler) {
        this.stage = stage;
        gameBoard = new GameBoard();
        this.zombieFactory = new ZombieFactory(gameBoard);
        this.wallFactory = new WallFactory(gameBoard, this);
        createCowboy(inputHandler);
        createWalls();
    }

    private void createCowboy(InputHandler inputHandler) {
        cowboy = new Cowboy(AssetsFactory.instance().getCowboyBW(), gameBoard, this);
        stage.addActor(cowboy);
        inputHandler.subscribe(cowboy);
    }

    private void createWalls() {
        Wall wall1 = wallFactory.createWall(6);
        Wall wall2 = wallFactory.createWall(10);
        stage.addActor(wall1);
        stage.addActor(wall2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (zombieFactory.getMetronomeLeft().update(delta)) {
            createZombies(ZombieFactory.Side.LEFT);
        }
        if (zombieFactory.getMetronomeRight().update(delta)) {
            createZombies(ZombieFactory.Side.RIGHT);
        }
        stage.act();
        stage.draw();
    }

    private void createZombies(ZombieFactory.Side side) {
        int probabilityCreateZombie = 60;
        int randCreateZombies = new Random().nextInt(99) + 1;
        if (randCreateZombies <= probabilityCreateZombie) {
            stage.addActor(zombieFactory.createZombie(side));
        }
    }

    public void killZombie(int boardPos) {
        if (gameBoard.getZombies(boardPos).size() == 0) return;

        Zombie zKilled = gameBoard.getZombies(boardPos).get(0);
        zombieFactory.deleteZombie(zKilled);
        zKilled.kill();
    }

    public void demolishWall(int boardPos) {
        wallFactory.demolishWall(boardPos);
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
