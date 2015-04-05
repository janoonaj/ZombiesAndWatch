package com.game.board;

import com.badlogic.gdx.math.Vector2;

public class PositionOnBoardVO {
    private Vector2 screenCoords;
    private int boardPos;

    public PositionOnBoardVO(Vector2 screenCoords, int boardPos) {
        this.screenCoords = screenCoords;
        this.boardPos = boardPos;
    }

    public Vector2 getScreenCoords() {
        return screenCoords;
    }

    public int getBoardPos() {
        return boardPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionOnBoardVO that = (PositionOnBoardVO) o;

        if (boardPos != that.boardPos) return false;
        if (screenCoords != null ? !screenCoords.equals(that.screenCoords) : that.screenCoords != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = screenCoords != null ? screenCoords.hashCode() : 0;
        result = 31 * result + boardPos;
        return result;
    }
}
