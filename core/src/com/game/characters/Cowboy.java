package com.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.board.GameBoard;
import com.game.test.Interactive;

public class Cowboy extends Image implements Interactive{

    private final int leftEdge = 6;
    private final int rightEdge = 10;
    private final GameBoard gameBoard;

    private int pos = 8;

    public Cowboy(Texture texture, GameBoard gameBoard) {
        super(texture);
        this.gameBoard = gameBoard;
        updatePos();
    }

    @Override
    public void pressedLeft() {
        if(pos == leftEdge) return;
        pos--;
        updatePos();
    }

    @Override
    public void pressedRight() {
        if(pos == rightEdge) return;
        pos++;
        updatePos();
    }

    private void updatePos() {
        Vector2 newPos = gameBoard.getScreen2Cowboy(pos);
        this.setPosition(newPos.x, newPos.y);
    }

    public int getPos() {
        return pos;
    }
}
