package com.game.test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.AssetsFactory;
import com.game.InputHandler;
import com.game.board.GameBoard;
import com.game.board.GameScreenPos;
import com.game.characters.Cowboy;
import com.game.characters.Side;
import com.game.characters.zombies.Zombie;
import com.game.characters.zombies.ZombieFactory;
import com.game.ovnis.UfoFactory;
import com.game.scenario.HouseFactory;
import com.game.scenario.WallFactory;

import java.util.Random;

public class Test1 implements Screen {
    private final ZombieFactory zombieFactory;
    private final WallFactory wallFactory;
    private final HouseFactory houseFactory;
    private final UfoFactory ufoFactory;
    private final GameScreenPos gameScreenPos;
    private Stage stage;
    private Cowboy cowboy;
    private GameBoard gameBoard;
    private final int maxNumUFO = 1;
    private int currentNumUFO = 0;

    public Test1(Stage stage, InputHandler inputHandler) {
        this.stage = stage;
        gameBoard = new GameBoard();
        gameScreenPos = new GameScreenPos(gameBoard);
        this.zombieFactory = new ZombieFactory(gameBoard, gameScreenPos, this);
        this.wallFactory = new WallFactory(gameBoard, gameScreenPos, this);
        this.houseFactory = new HouseFactory(gameBoard, gameScreenPos, this);
        this.ufoFactory = new UfoFactory(gameBoard, gameScreenPos, this);
        createCowboy(inputHandler);
        createWalls();
        createHouses();
    }

    private void createCowboy(InputHandler inputHandler) {
        cowboy = new Cowboy(AssetsFactory.instance().getCowboyBW(), gameBoard, gameScreenPos, this);
        stage.addActor(cowboy);
        inputHandler.subscribe(cowboy);
    }

    private void createWalls() {
        stage.addActor(wallFactory.createWall(6));
        stage.addActor(wallFactory.createWall(10));
    }

    private void createHouses() {
        stage.addActor(houseFactory.createHouse(6));
        stage.addActor(houseFactory.createHouse(7));
        stage.addActor(houseFactory.createHouse(8));
        stage.addActor(houseFactory.createHouse(9));
        stage.addActor(houseFactory.createHouse(10));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (zombieFactory.getMetronomeLeft().update(delta)) {
            createZombies(Side.LEFT);
        }
        if (zombieFactory.getMetronomeRight().update(delta)) {
            createZombies(Side.RIGHT);
        }
        if (ufoFactory.getMetronome().update(delta)) {
            createUfo();
        }
        stage.act();
        stage.draw();
    }

    private void createZombies(Side side) {
        int probabilityCreateZombie = 60;
        int randCreateZombies = new Random().nextInt(99) + 1;
        if (randCreateZombies <= probabilityCreateZombie) {
            stage.addActor(zombieFactory.createZombie(side));
        }
    }

    public void killZombie(Zombie zombie) {
        zombieFactory.deleteZombie(zombie);
    }

    public void demolishWall(int boardPos) {
        wallFactory.demolish(boardPos);
    }

    public void demolishHouse(int boardPos) { houseFactory.demolish(boardPos);   }

    private void createUfo() {
        if(currentNumUFO >= maxNumUFO) return;
        int probabilityCreateUFO = 100;
        int randCreateUFO = new Random().nextInt(99) + 1;
        if (randCreateUFO <= probabilityCreateUFO) {
            currentNumUFO++;
            stage.addActor(ufoFactory.createUfo(Side.random()));
        }
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
