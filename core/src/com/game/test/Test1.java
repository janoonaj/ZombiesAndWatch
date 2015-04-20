package com.game.test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.AssetsFactory;
import com.game.miniGameEngine.FactoriesVO;
import com.game.InputHandler;
import com.game.miniGameEngine.MiniGameUpdater;
import com.game.board.BoardVO;
import com.game.board.GameBoard;
import com.game.board.GameScreenPos;
import com.game.characters.Cowboy;
import com.game.characters.zombies.Zombie;
import com.game.characters.zombies.ZombieFactory;
import com.game.ovnis.UfoFactory;
import com.game.scenario.HouseFactory;
import com.game.scenario.WallFactory;

public class Test1 implements Screen {
    private final ZombieFactory zombieFactory;
    private final WallFactory wallFactory;
    private final HouseFactory houseFactory;
    private final UfoFactory ufoFactory;
    private final MiniGameUpdater updater;
    private Stage stage;
    private Cowboy cowboy;
    private final int numMaxUfo = 1;

    public Test1(Stage stage, InputHandler inputHandler) {
        this.stage = stage;
        GameBoard gameBoard = new GameBoard();
        BoardVO board = new BoardVO(gameBoard, new GameScreenPos(gameBoard));
        this.zombieFactory = new ZombieFactory(board, this);
        this.wallFactory = new WallFactory(board, this);
        this.houseFactory = new HouseFactory(board, this);
        this.ufoFactory = new UfoFactory(board, this);
        createCowboy(inputHandler, board);
        createWalls();
        createHouses();
        updater = new MiniGameUpdater(stage, new FactoriesVO(zombieFactory, ufoFactory),
                                                            numMaxUfo);
    }

    private void createCowboy(InputHandler inputHandler, BoardVO board) {
        cowboy = new Cowboy(AssetsFactory.instance().getCowboyBW(), board, this);
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
        updater.render(delta);
        stage.act();
        stage.draw();
    }



    public void killZombie(Zombie zombie) {
        zombieFactory.deleteZombie(zombie);
    }

    public void demolishWall(int boardPos) {
        wallFactory.demolish(boardPos);
    }

    public void demolishHouse(int boardPos) { houseFactory.demolish(boardPos);   }

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
