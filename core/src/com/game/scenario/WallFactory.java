package com.game.scenario;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.GameFactory;
import com.game.board.BoardVO;
import com.game.board.GameBoard;
import com.game.board.GameScreenPos;

public class WallFactory  extends GameFactory {

    private final GameScreenPos gameScreenPos;
    GameBoard gameBoard;

    public WallFactory(BoardVO board) {
        this.gameBoard = board.gameBoard;
        this.gameScreenPos = board.gameScreenPos;
    }

    public Wall createWall(int boardPos) {
        Texture wallGreen = AssetsFactory.instance().getWallGreen();
        Wall wall = new Wall(wallGreen,
                AssetsFactory.instance().getWallYellow(),
                AssetsFactory.instance().getWallRed(),
                boardPos, gameEngine);
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
