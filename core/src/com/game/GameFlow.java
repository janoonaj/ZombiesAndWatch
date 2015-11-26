package com.game;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.buildCityMenu.BuildCityPointsVO;
import com.game.buildCityMenu.Menu;
import com.game.city.Rules;
import com.game.miniGame.InputHandler;
import com.game.signal.EventListener;
import com.game.signal.SignalListener;

public class GameFlow implements SignalListener {
    public Signal onChangeScreen = new Signal();
    private InputHandler inputHandler = new InputHandler();
    private Stage currentStage  = new Stage();

    private CityInfoVO cityInfoVO = CityInfoVO.test();

    public void start() {
        currentStage.dispose();
        currentStage = new Stage();
        Gdx.input.setInputProcessor(currentStage);
        Menu menu = new Menu(currentStage, cityInfoVO);
        onChangeScreen.dispatch(menu);

        menu.onFinished.add(new EventListener(this));

    }

    @Override
    public void signalReceived(Signal signal, Object data) {
        if(data instanceof BuildCityPointsVO) {
            CityInfoVO newCity = Rules.updateCityInfo((BuildCityPointsVO)data, cityInfoVO);
            int asas = 3;
        }
    }
}
