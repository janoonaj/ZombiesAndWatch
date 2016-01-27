package com.game.miniGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.game.AssetsFactory;
import com.game.CityInfoVO;
import com.game.miniGame.Config;
import com.game.miniGame.MinigameInputHandler;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.board.GameScreenPos;
import com.game.miniGame.characters.Cowboy;
import com.game.miniGame.characters.militia.MilitiaController;
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
    private Cowboy cowboy;
    private MilitiaController militiaController;
    private final int numMaxUfo = 1;

    private Label timeCounter;
    private float mSecsPassed = 0;

    public GameWatchGame(Stage stage, MinigameInputHandler minigameInputHandler, CityInfoVO cityInfoVO) {
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
        createCowboy(board);
        createMilitia(board, cityInfoVO.getMilitiaLevel());
        updater = new MiniGameUpdater(stage, factories, numMaxUfo, orchestraConductor, militiaController);
        minigameInputHandler.subscribe(cowboy, militiaController);
        createWalls();
        createHouses();
        createUI();
    }

    private void createMilitia(BoardVO board, int militiaLevel) {
        militiaController = new MilitiaController(board, militiaLevel);
        stage.addActor(militiaController.getMilitia());
        for(Image img : militiaController.getSelectors()) {
            stage.addActor(img);
        }
    }

    private void createCowboy(BoardVO board) {
        cowboy = new Cowboy(AssetsFactory.instance().getCowboyBW(), board);
        stage.addActor(cowboy);
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
        militiaController.dispose();
        ufoFactory.dispose();
        zombieFactory.dispose();
    }
}
