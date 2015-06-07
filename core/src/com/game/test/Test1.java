package com.game.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.game.AssetsFactory;
import com.game.Config;
import com.game.miniGameEngine.OrchestraConductor;
import com.game.miniGameEngine.FactoriesVO;
import com.game.InputHandler;
import com.game.miniGameEngine.MiniGameUpdater;
import com.game.board.BoardVO;
import com.game.board.GameBoard;
import com.game.board.GameScreenPos;
import com.game.characters.Cowboy;
import com.game.characters.zombies.ZombieFactory;
import com.game.characters.ufos.UfoFactory;
import com.game.scenario.HouseFactory;
import com.game.scenario.Population;
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

    private Label timeCounter;
    private float mSecsPassed = 0;

    public Test1(Stage stage, InputHandler inputHandler) {
        this.stage = stage;
        GameBoard gameBoard = new GameBoard();
        BoardVO board = new BoardVO(gameBoard, new GameScreenPos(gameBoard));
        this.zombieFactory = new ZombieFactory(board);
        this.wallFactory = new WallFactory(board);
        this.houseFactory = new HouseFactory(board);
        this.ufoFactory = new UfoFactory(board);
        FactoriesVO factories = new FactoriesVO(zombieFactory, ufoFactory, wallFactory, houseFactory);
        OrchestraConductor orchestraConductor = new OrchestraConductor(factories);
        updater = new MiniGameUpdater(stage, factories, numMaxUfo, orchestraConductor);
        createCowboy(inputHandler, board);
        createWalls();
        createHouses();
        createUI();
    }

    private void createCowboy(InputHandler inputHandler, BoardVO board) {
        cowboy = new Cowboy(AssetsFactory.instance().getCowboyBW(), board);
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

        Image shotgun = new Image(AssetsFactory.instance().getShotgun());
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
