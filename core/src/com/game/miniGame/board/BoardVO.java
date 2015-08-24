package com.game.miniGame.board;

public class BoardVO {
    public GameBoard gameBoard;
    public GameScreenPos gameScreenPos;

    public BoardVO(GameBoard gameBoard, GameScreenPos gameScreenPos) {
        this.gameBoard = gameBoard;
        this.gameScreenPos = gameScreenPos;
    }
}
