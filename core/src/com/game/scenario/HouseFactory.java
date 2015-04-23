package com.game.scenario;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.GameFactory;
import com.game.board.BoardVO;
import com.game.board.GameBoard;
import com.game.board.GameScreenPos;
import com.game.test.Test1;

import java.util.Random;

public class HouseFactory  extends GameFactory {

    private final GameBoard gameBoard;
    private final GameScreenPos gameScreenPos;

    public HouseFactory(BoardVO board) {
        this.gameBoard = board.gameBoard;
        this.gameScreenPos = board.gameScreenPos;
    }

    public House createHouse(int boardPos) {
        Texture texture = getTexture();
        House house = new House(texture,
                boardPos, gameEngine);
        gameBoard.buildHouse(house, boardPos);
        Vector2 pos = gameScreenPos.getScreenPosZombies(boardPos);
        house.setPosition(pos.x - texture.getWidth() / 2, pos.y);
        return house;
    }

    public void demolish(int boardPos) {
        gameBoard.demolishHouse(boardPos);
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
}
