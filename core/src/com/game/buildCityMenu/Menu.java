package com.game.buildCityMenu;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.*;
import com.game.CityInfoVO;
import com.game.buildCityMenu.buildCityButtons.BuildCityController;
import com.game.buildCityMenu.buildCityButtons.ButtonType;
import com.game.buildCityMenu.buildCityButtons.ClickEventVO;
import com.game.buildCityMenu.buildCityButtons.ControllerType;
import com.game.messages.CityBuildMenuMssg;
import com.game.signal.EventListener;
import com.game.signal.SignalListener;

import java.util.Hashtable;

public class Menu implements Screen, SignalListener {
    private Stage stage;
    private Table table;
    private Table tableDown;
    private CityInfoPanel cityInfoPanel;
    Hashtable<Integer, BuildCityController> controllers = new Hashtable<Integer, com.game.buildCityMenu.buildCityButtons.BuildCityController>();
    private AvailablePointsController availablePointsController;
    private ImageButton bttnOK;
    public Signal onFinished = new Signal();
    private CityInfoVO cityInfo;
    private CityInfoVO finalCity;

    public Menu(Stage stage, CityInfoVO cityInfoVO) {
        this.stage = stage;
        this.cityInfo = cityInfoVO;
        cityInfoPanel = new CityInfoPanel(cityInfoVO);
        controllers.put(ControllerType.HOUSE,
                new BuildCityController(AssetsFactory.instance().getButtonHouse(), ControllerType.HOUSE, 10));
        controllers.put(ControllerType.HARVEST,
                new BuildCityController(AssetsFactory.instance().getButtonHarvest(), ControllerType.HARVEST, 10));
        controllers.put(ControllerType.WALL,
                new BuildCityController(AssetsFactory.instance().getButtonWall(), ControllerType.WALL, 10));
        controllers.put(ControllerType.MILITIA,
                new BuildCityController(AssetsFactory.instance().getButtonMilitia(), ControllerType.MILITIA, 10));
        controllers.get(ControllerType.HOUSE).onClick.add(new EventListener(this));
        controllers.get(ControllerType.HARVEST).onClick.add(new EventListener(this));
        controllers.get(ControllerType.WALL).onClick.add(new EventListener(this));
        controllers.get(ControllerType.MILITIA).onClick.add(new EventListener(this));
        setupOkButton();
        availablePointsController = new AvailablePointsController(10);
        prepareTable();
    }

    private void prepareTable() {
        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);
        tableDown = new Table().left();
        tableDown.add(controllers.get(ControllerType.HOUSE).getTable()).padBottom(10);
        tableDown.add(controllers.get(ControllerType.HARVEST).getTable()).padBottom(10);
        tableDown.add(availablePointsController.getTable()).padBottom(10);
        tableDown.row();
        tableDown.add(controllers.get(ControllerType.WALL).getTable());
        tableDown.add(controllers.get(ControllerType.MILITIA).getTable());
        tableDown.add(bttnOK);
        table.add(cityInfoPanel.getTable()).padBottom(20);
        table.row();
        table.add(tableDown);
    }

    private void setupOkButton() {
        bttnOK = new ImageButton(new Image(AssetsFactory.instance().getButtonOK()).getDrawable());
        bttnOK.setVisible(false);
        bttnOK.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(finalCity == null) {
                    finalCity = Rules.updateCityInfo(new BuildCityPointsVO(
                            controllers.get(ControllerType.HOUSE).getMarks(),
                            controllers.get(ControllerType.HARVEST).getMarks(),
                            controllers.get(ControllerType.WALL).getMarks(),
                            controllers.get(ControllerType.MILITIA).getMarks()), cityInfo);
                    finalCity = Rules.newPopulation(finalCity);

                    disposeControllers();
                    table.reset();
                    showResults(cityInfo, finalCity);
                } else {
                        onFinished.dispatch(new CityBuildMenuMssg(finalCity));
                }
            }
        });
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
        if(availablePointsController.getMarks() == 0)
            bttnOK.setVisible(true);
        else
            bttnOK.setVisible(false);
    }

    public void showResults(CityInfoVO cityPrev, CityInfoVO cityNext) {
        CityInfoResultsPanel resultPanel = new CityInfoResultsPanel(cityPrev, cityNext, bttnOK);
        table.add(resultPanel.getTable());
        table.add(bttnOK).expand();
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

    public void disposeControllers() {
        controllers.get(ControllerType.HOUSE).dispose();
        controllers.get(ControllerType.HARVEST).dispose();
        controllers.get(ControllerType.WALL).dispose();
        controllers.get(ControllerType.MILITIA).dispose();
    }

    @Override
    public void dispose() {

        onFinished.removeAllListeners();
    }
}
