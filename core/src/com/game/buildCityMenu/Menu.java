package com.game.buildCityMenu;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.AssetsFactory;
import com.game.buildCityMenu.buildCityButtons.ButtonType;
import com.game.buildCityMenu.buildCityButtons.ClickEventVO;
import com.game.buildCityMenu.buildCityButtons.ControllerType;
import com.game.signal.EventListener;
import com.game.signal.SignalListener;

import java.util.Hashtable;

public class Menu implements Screen, SignalListener {
    private Stage stage;
    private Table table;
    private CityInfoPanel cityInfoPanel;
    Hashtable<Integer, com.game.buildCityMenu.buildCityButtons.BuildCityController> controllers = new Hashtable<Integer, com.game.buildCityMenu.buildCityButtons.BuildCityController>();
    private AvailablePointsController availablePointsController;

    public Menu(Stage stage) {
        this.stage = stage;
        cityInfoPanel = new CityInfoPanel(CityInfoVO.test());
        controllers.put(ControllerType.HOUSE,
                new com.game.buildCityMenu.buildCityButtons.BuildCityController(AssetsFactory.instance().getButtonHouse(), ControllerType.HOUSE, 10));
        controllers.put(ControllerType.HARVEST,
                new com.game.buildCityMenu.buildCityButtons.BuildCityController(AssetsFactory.instance().getButtonHarvest(), ControllerType.HARVEST, 10));
        controllers.put(ControllerType.WALL,
                new com.game.buildCityMenu.buildCityButtons.BuildCityController(AssetsFactory.instance().getButtonWall(), ControllerType.WALL, 10));
        controllers.put(ControllerType.MILITIA,
                new com.game.buildCityMenu.buildCityButtons.BuildCityController(AssetsFactory.instance().getButtonMilitia(), ControllerType.MILITIA, 10));
        controllers.get(ControllerType.HOUSE).onClick.add(new EventListener(this));
        controllers.get(ControllerType.HARVEST).onClick.add(new EventListener(this));
        controllers.get(ControllerType.WALL).onClick.add(new EventListener(this));
        controllers.get(ControllerType.MILITIA).onClick.add(new EventListener(this));

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
        tableDownLeft.add(controllers.get(ControllerType.HOUSE).getTable()).padBottom(10);
        tableDownLeft.add(controllers.get(ControllerType.HARVEST).getTable()).padBottom(10);
        tableDownLeft.add(availablePointsController.getTable()).padBottom(10);
        tableDownLeft.row();
        tableDownLeft.add(controllers.get(ControllerType.WALL).getTable());
        tableDownLeft.add(controllers.get(ControllerType.MILITIA).getTable());
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

    @Override
    public void signalReceived(Signal signal, Object data) {
        ClickEventVO event = (ClickEventVO) data;
        if(event.getButtonClicked() == ButtonType.PLUS && availablePointsController.canRemoveMark()
                && controllers.get(event.getChangeOnCityClicked()).canAddMark()) {
            availablePointsController.removeMark();
            controllers.get(event.getChangeOnCityClicked()).addMark();
        }
        if(event.getButtonClicked() == ButtonType.MINUS && availablePointsController.canAddMark()
                && controllers.get(event.getChangeOnCityClicked()).canRemoveMark()) {
            availablePointsController.addMark();
            controllers.get(event.getChangeOnCityClicked()).removeMark();
        }
    }
}
