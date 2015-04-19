package com.game.ovnis;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.Config;
import com.game.board.GameBoard;
import com.game.characters.Rhythmical;
import com.game.characters.Side;
import com.game.test.Test1;

public class Ufo extends Image implements Rhythmical {
    private final Test1 test1;
    private int boardPos;
    private final GameBoard gameBoard;
    private final Side side;
    private int health = Config.healthOvni;


    public Ufo(Texture texture, int boardPos, Side side, GameBoard board, Test1 test1) {
        super(texture);
        this.boardPos = boardPos;
        this.gameBoard = board;
        this.side = side;
        this.test1 = test1;
    }

    @Override
    public void updatePos() {
        int nextPos = boardPos;
        if (side == Side.LEFT) {
            nextPos = boardPos - 1;
            if(nextPos < 1 ) return;
        }
        if (side == Side.RIGHT) {
            nextPos = boardPos + 1;
            if(nextPos > gameBoard.getRighestPos()) return;
        }

        changePos(nextPos);
    }

    @Override
    public void draw() {
        float nextX = gameBoard.getScreenPosOvni(boardPos).x - this.getImageWidth() / 2;
        float nextY = gameBoard.getScreenPosOvni(boardPos).y;
        setPosition(nextX, nextY);
    }

    private void changePos(int newPos) {
        gameBoard.removeUfo(boardPos);
        gameBoard.addUfo(newPos, this);
        boardPos = newPos;
    }
}
