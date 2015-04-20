package com.game.scenario;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.board.BoardVO;
import com.game.board.GameBoard;
import com.game.board.GameScreenPos;
import com.game.test.Test1;

public class WallFactory {

    private final Test1 test1;
    private final GameScreenPos gameScreenPos;
    GameBoard gameBoard;

    public WallFactory(BoardVO board, Test1 test1) {
        this.gameBoard = board.gameBoard;
        this.test1 = test1;
        this.gameScreenPos = board.gameScreenPos;
    }

    public Wall createWall(int boardPos) {
        Texture wallGreen = AssetsFactory.instance().getWallGreen();
        Wall wall = new Wall(wallGreen,
                AssetsFactory.instance().getWallYellow(),
                AssetsFactory.instance().getWallRed(),
                test1,
                boardPos);
        gameBoard.buildWall(wall, boardPos);
        Vector2 pos = getScreenPos(boardPos);
        wall.setPosition(pos.x - wallGreen.getWidth() / 2, pos.y);
        return wall;
    }

    public void demolish(int boardPos) {
        gameBoard.demolishWall(boardPos);
    }

    private Vector2 getScreenPos(int boardPos) {
        if(boardPos >= gameBoard.getCenterBoard()) {
            return gameScreenPos.getRightEdgeSceenPos(boardPos);
        } else {
            return gameScreenPos.getLeftEdgeSceenPos(boardPos);
        }
    }
}
