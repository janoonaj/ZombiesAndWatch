package com.game.scenario;

import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.board.GameBoard;
import com.game.test.Test1;

public class WallFactory {

    private final Test1 test1;
    GameBoard gameBoard;

    public WallFactory(GameBoard gameBoard, Test1 test1) {
        this.gameBoard = gameBoard;
        this.test1 = test1;
    }

    public Wall createWall(int boardPos) {
        Wall wall = new Wall(AssetsFactory.instance().getWallGreen(),
                AssetsFactory.instance().getWallYellow(),
                AssetsFactory.instance().getWallRed(),
                test1,
                boardPos);
        gameBoard.addWall(wall, boardPos);
        Vector2 pos = getScreenPos(boardPos);
        wall.setPosition(pos.x, pos.y);
        return wall;
    }

    public void demolishWall(int boardPos) {
        gameBoard.demolishWall(boardPos);
    }

    private Vector2 getScreenPos(int boardPos) {
        if(boardPos >= gameBoard.getCenterBoard()) {
            return gameBoard.getRightEdgeSceenPos(boardPos);
        } else {
            return gameBoard.getLeftEdgeSceenPos(boardPos);
        }
    }
}
