package com.game.miniGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.game.AssetsFactory;
import com.game.miniGame.Config;
import com.game.miniGame.MinigameInputHandler;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.board.GameScreenPos;
import com.game.miniGame.characters.Cowboy;
import com.game.miniGame.characters.ufos.UfoFactory;
import com.game.miniGame.characters.zombies.ZombieFactory;

import com.game.miniGame.miniGameEngine.FactoriesVO;
import com.game.miniGame.miniGameEngine.MiniGameUpdater;
import com.game.miniGame.miniGameEngine.OrchestraConductor;
import com.game.miniGame.scenario.*;


public class GameWatchGame implements Screen {
    private final MinigameInputHandler minigameInputHandler;
    private final ZombieFactory zombieFactory;
    private final WallFactory wallFactory;
    private final HouseFactory houseFactory;
    private final UfoFactory ufoFactory;
    private final MiniGameUpdater updater;
    private Stage stage;
    private com.game.miniGame.characters.Cowboy cowboy;
    private final int numMaxUfo = 1;

    private Label timeCounter;
    private float mSecsPassed = 0;

    public GameWatchGame(Stage stage, MinigameInputHandler minigameInputHandler) {
        this.stage = stage;
        this.minigameInputHandler = minigameInputHandler;
        GameBoard gameBoard = new GameBoard();
        BoardVO board = new BoardVO(gameBoard, new GameScreenPos(gameBoard));
        this.zombieFactory = new ZombieFactory(board);
        this.wallFactory = new WallFactory(board);
        this.houseFactory = new HouseFactory(board);
        this.ufoFactory = new UfoFactory(board);
        FactoriesVO factories = new FactoriesVO(zombieFactory, ufoFactory, wallFactory, houseFactory);
        OrchestraConductor orchestraConductor = new OrchestraConductor(factories);
        updater = new MiniGameUpdater(stage, factories, numMaxUfo, orchestraConductor);
        createCowboy(board);
        createWalls();
        createHouses();
        createUI();
    }

    private void createCowboy(BoardVO board) {
        cowboy = new Cowboy(AssetsFactory.instance().getCowboyBW(), board);
        stage.addActor(cowboy);
        minigameInputHandler.subscribe(cowboy);
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
        if (mSecsPassed < Config.finalMiniGame) {
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
