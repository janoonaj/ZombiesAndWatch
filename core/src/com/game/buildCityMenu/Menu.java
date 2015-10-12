package com.game.buildCityMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.AssetsFactory;

public class Menu implements Screen {
    private Stage stage;
    private Table table;
    private CityInfoPanel cityInfoPanel;
    private BuildCityController controllerHouse;
    private BuildCityController controllerHarvest;
    private BuildCityController controllerWall;
    private BuildCityController controllerMilitia;
    private AvailablePointsController availablePointsController;

    public Menu(Stage stage) {
        this.stage = stage;
        cityInfoPanel = new CityInfoPanel(CityInfoVO.test());
        controllerHouse = new BuildCityController(AssetsFactory.instance().getButtonHouse(), 10);
        controllerHarvest = new BuildCityController(AssetsFactory.instance().getButtonHarvest(), 10);
        controllerWall = new BuildCityController(AssetsFactory.instance().getButtonWall(), 10);
        controllerMilitia = new BuildCityController(AssetsFactory.instance().getButtonMilitia(), 10);
        availablePointsController = new AvailablePointsController(10);
        prepareTable();


    }

    private void prepareTable() {
        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);
        Table tableDown = new Table();
        Table tableDownLeft = new Table().left();
        Table tableDownRight = new Table().right();
        tableDownLeft.add(controllerHouse.getTable()).padBottom(10);
        tableDownLeft.add(controllerHarvest.getTable()).padBottom(10);
        tableDownLeft.add(availablePointsController.getTable()).padBottom(10);
        tableDownLeft.row();
        tableDownLeft.add(controllerWall.getTable());
        tableDownLeft.add(controllerMilitia.getTable());
        tableDownLeft.add(new ImageButton(new Image(AssetsFactory.instance().getButtonOK()).getDrawable()));
        tableDown.add(tableDownLeft);
        tableDown.add(tableDownRight);
        table.add(cityInfoPanel.getTable()).padBottom(20);
        table.row();
        table.add(tableDown);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
