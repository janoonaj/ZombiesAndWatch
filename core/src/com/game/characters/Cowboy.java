package com.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.Config;
import com.game.board.BoardVO;
import com.game.board.GameBoard;
import com.game.board.GameScreenPos;
import com.game.test.Interactive;
import com.game.test.Test1;

public class Cowboy extends Image implements Interactive{

    private final GameBoard gameBoard;
    private final Test1 test1;
    private final GameScreenPos gameScreenPos;

    private int pos = 8;

    public Cowboy(Texture texture, BoardVO board, Test1 test1) {
        super(texture);
        this.gameBoard = board.gameBoard;
        this.test1 = test1;
        this.gameScreenPos = board.gameScreenPos;
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
        if(attackZombie(nextPos)) return;
        if(nextPos == gameBoard.nonPlayableLeftEdge || nextPos == gameBoard.nonPlayableRightEdge) return;
        pos = nextPos;
        updateScreenPos();
    }

    private void updateScreenPos() {
        Vector2 newPos = gameScreenPos.getScreen2Cowboy(pos);
        this.setPosition(newPos.x - this.getImageWidth() / 2, newPos.y);
    }

    //Return true if a zombie is hit
    private boolean attackZombie(int nextPos) {
        if(gameBoard.getZombies(nextPos).size() == 0) return false;
        gameBoard.getZombies(nextPos).get(0).damage(Config.cowboyDamage);
        return true;
    }
}
