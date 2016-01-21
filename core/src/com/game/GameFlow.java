package com.game;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.buildCityMenu.BuildCityPointsVO;
import com.game.buildCityMenu.CityInfoResultsPanel;
import com.game.buildCityMenu.Menu;
import com.game.miniGame.MinigameInputHandler;
import com.game.miniGame.test.GameWatchGame;
import com.game.signal.EventListener;
import com.game.signal.SignalListener;

public class GameFlow implements SignalListener {
    public Signal onChangeScreen = new Signal();
    private MinigameInputHandler minigameInputHandler = new MinigameInputHandler();
    private Stage currentStage  = new Stage();
    private CityInfoVO cityInfoVO = CityInfoVO.test();

    public void start() {
        currentStage.dispose();
        currentStage = new Stage();
        Gdx.input.setInputProcessor(currentStage);
        Menu menu = new Menu(currentStage, cityInfoVO);
        onChangeScreen.dispatch(menu);

        menu.onFinished.add(new EventListener(this));



//        Gdx.input.setInputProcessor(minigameInputHandler);
//        GameWatchGame minigame = new GameWatchGame(currentStage, minigameInputHandler);
//        onChangeScreen.dispatch(minigame);

    }

    @Override
    public void signalReceived(Signal signal, Object data) {
        if(data instanceof CityInfoVO) {
            int asd = 3;
//            CityInfoVO newCity = Rules.updateCityInfo((BuildCityPointsVO)data, cityInfoVO);
//            CityInfoResultsPanel results = new CityInfoResultsPanel(cityInfoVO, newCity);

        }
        // End Game And Wathc Mini Game
        else if(data instanceof GameWatchGame){

        }
    }
}
