package com.game.miniGame.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.game.miniGame.Config;
import com.game.miniGame.miniGameEngine.MiniGameUpdater;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.characters.zombies.ZombieFactory;
import com.game.miniGame.scenario.Population;

public class Test1 implements Screen {
    private final ZombieFactory zombieFactory;
    private final com.game.miniGame.scenario.WallFactory wallFactory;
    private final com.game.miniGame.scenario.HouseFactory houseFactory;
    private final com.game.miniGame.characters.ufos.UfoFactory ufoFactory;
    private final MiniGameUpdater updater;
    private Stage stage;
    private com.game.miniGame.characters.Cowboy cowboy;
    private final int numMaxUfo = 1;

    private Label timeCounter;
    private float mSecsPassed = 0;

    public Test1(Stage stage, com.game.miniGame.InputHandler inputHandler) {
        this.stage = stage;
        GameBoard gameBoard = new GameBoard();
        com.game.miniGame.board.BoardVO board = new com.game.miniGame.board.BoardVO(gameBoard, new com.game.miniGame.board.GameScreenPos(gameBoard));
        this.zombieFactory = new ZombieFactory(board);
        this.wallFactory = new com.game.miniGame.scenario.WallFactory(board);
        this.houseFactory = new com.game.miniGame.scenario.HouseFactory(board);
        this.ufoFactory = new com.game.miniGame.characters.ufos.UfoFactory(board);
        com.game.miniGame.miniGameEngine.FactoriesVO factories = new com.game.miniGame.miniGameEngine.FactoriesVO(zombieFactory, ufoFactory, wallFactory, houseFactory);
        com.game.miniGame.miniGameEngine.OrchestraConductor orchestraConductor = new com.game.miniGame.miniGameEngine.OrchestraConductor(factories);
        updater = new MiniGameUpdater(stage, factories, numMaxUfo, orchestraConductor);
        createCowboy(inputHandler, board);
        createWalls();
        createHouses();
        createUI();
    }

    private void createCowboy(com.game.miniGame.InputHandler inputHandler, com.game.miniGame.board.BoardVO board) {
        cowboy = new com.game.miniGame.characters.Cowboy(com.game.miniGame.AssetsFactory.instance().getCowboyBW(), board);
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

    private void createUI() {
        Population.setInitialInhabitants(Config.startPopulation);
        Population pop = Population.instance();
        stage.addActor(pop);
        pop.setX(0f);
        pop.setY(Gdx.graphics.getHeight() - pop.getHeight());

        timeCounter = new Label("0", new Skin(Gdx.files.internal("skins.json")));
        stage.addActor(timeCounter);
        timeCounter.setX(Gdx.graphics.getWidth() - 50);
        timeCounter.setY(Gdx.graphics.getHeight() - timeCounter.getHeight());

        Image shotgun = new Image(com.game.miniGame.AssetsFactory.instance().getShotgun());
        stage.addActor(shotgun);
        shotgun.setX(Gdx.graphics.getWidth() - shotgun.getWidth());
        shotgun.setY(0);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(mSecsPassed < Config.finalMiniGame) {
            updater.render(delta);
            mSecsPassed += delta;
            timeCounter.setText(Integer.toString((int) mSecsPassed));
        }

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
