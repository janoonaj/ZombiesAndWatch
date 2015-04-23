package com.game;

import com.game.miniGameEngine.GameEngine;

public class GameFactory {
    protected GameEngine gameEngine;

    public void setEngine (GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
}
