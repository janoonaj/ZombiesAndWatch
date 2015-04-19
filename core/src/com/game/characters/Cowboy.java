package com.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.Config;
import com.game.Logger;
import com.game.board.GameBoard;
import com.game.test.Interactive;
import com.game.test.Test1;

public class Cowboy extends Image implements Interactive{

    private final GameBoard gameBoard;
    private final Test1 test1;

    private int pos = 8;

    public Cowboy(Texture texture, GameBoard gameBoard, Test1 test1) {
        super(texture);
        this.gameBoard = gameBoard;
        this.test1 = test1;
        updateScreenPos();
    }

    @Override
    public void pressedLeft() {
        doStuff(pos-1);
    }

    @Override
    public void pressedRight() {
        doStuff(pos+1);
    }

    private void doStuff(int nextPos) {
        if(killZombie(nextPos)) return;
        if(nextPos == gameBoard.nonPlayableLeftEdge || nextPos == gameBoard.nonPlayableRightEdge) return;
        pos = nextPos;
        updateScreenPos();
    }

    private void updateScreenPos() {
        Vector2 newPos = gameBoard.getScreen2Cowboy(pos);
        this.setPosition(newPos.x - this.getImageWidth() / 2, newPos.y);
    }

    private boolean killZombie(int nextPos) {
        if(gameBoard.getZombies(nextPos).size() == 0) return false;
        gameBoard.getZombies(nextPos).get(0).damage(Config.cowboyDamage);
        return true;
    }

    public int getPos() {
        return pos;
    }
}
