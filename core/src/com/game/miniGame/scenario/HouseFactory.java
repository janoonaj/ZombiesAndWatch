package com.game.miniGame.scenario;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.board.GameScreenPos;
import com.game.miniGame.signal.EventListener;
import com.game.miniGame.signal.SignalListener;

import java.util.Random;

public class HouseFactory implements SignalListener {

    private final GameBoard gameBoard;
    private final GameScreenPos gameScreenPos;

    public HouseFactory(BoardVO board) {
        this.gameBoard = board.gameBoard;
        this.gameScreenPos = board.gameScreenPos;
    }

    public House createHouse(int boardPos) {
        Texture texture = getTexture();
        House house = new House(texture, boardPos);
        house.onDemolished.add(new EventListener(this));
        gameBoard.buildHouse(house, boardPos);
        Vector2 pos = gameScreenPos.getScreenPosZombies(boardPos);
        house.setPosition(pos.x - texture.getWidth() / 2, pos.y);
        return house;
    }

    private Texture getTexture() {
        switch (new Random().nextInt(4)) {
            case 0 : return AssetsFactory.instance().getHotel();
            case 1 : return AssetsFactory.instance().getHouse();
            case 2 : return AssetsFactory.instance().getFarm();
            case 3 : return AssetsFactory.instance().getChurch();
        }
        return null;
    }

    @Override
    public void signalReceived(Signal signal, Object boardPos) {
        gameBoard.demolishHouse((Integer)boardPos);
    }
}
