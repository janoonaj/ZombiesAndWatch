package com.game.miniGame.scenario;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.miniGame.AssetsFactory;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.board.GameScreenPos;
import com.game.miniGame.signal.EventListener;
import com.game.miniGame.signal.SignalListener;

public class WallFactory implements SignalListener {

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
                boardPos);
        wall.onDemolished.add(new EventListener(this));
        gameBoard.buildWall(wall, boardPos);
        Vector2 pos = getScreenPos(boardPos);
        wall.setPosition(pos.x - wallGreen.getWidth() / 2, pos.y);
        return wall;
    }

    private Vector2 getScreenPos(int boardPos) {
        if(boardPos >= gameBoard.getCenterBoard()) {
            return gameScreenPos.getRightEdgeSceenPos(boardPos);
        } else {
            return gameScreenPos.getLeftEdgeSceenPos(boardPos);
        }
    }

    @Override
    public void signalReceived(Signal signal, Object boardPos) {
        gameBoard.demolishWall((Integer)boardPos);
    }
}
