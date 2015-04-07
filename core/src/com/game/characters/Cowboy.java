package com.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.board.GameBoard;
import com.game.test.Interactive;
import com.game.test.Test1;

public class Cowboy extends Image implements Interactive{

    private final int leftEdge = 6;
    private final int rightEdge = 10;
    private final GameBoard gameBoard;
    private final Test1 test1;

    private int pos = 8;

    public Cowboy(Texture texture, GameBoard gameBoard, Test1 test1) {
        super(texture);
        this.gameBoard = gameBoard;
        this.test1 = test1;
        updatePos();
    }

    @Override
    public void pressedLeft() {
        if(pos == leftEdge) {
            test1.killZombie(pos - 1);
            return;
        }
        pos--;
        updatePos();
    }

    @Override
    public void pressedRight() {
        if(pos == rightEdge) {
            test1.killZombie(pos + 1);
            return;
        }
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
