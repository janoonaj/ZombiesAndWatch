package com.game.miniGame.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.miniGame.characters.ufos.Ufo;
import com.game.miniGame.Config;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.test.Interactive;

public class Cowboy extends Image implements Interactive{

    private final GameBoard gameBoard;
    private final com.game.miniGame.board.GameScreenPos gameScreenPos;

    private int pos = 8;

    public Cowboy(Texture texture, com.game.miniGame.board.BoardVO board) {
        super(texture);
        this.gameBoard = board.gameBoard;
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

    @Override
    public void shoot() {
        if(attackZombie(pos)) return;
        if(attackUfo()) return;
        if(attackZombie(pos + 1)) return;
        if(attackZombie(pos-1)) return;
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

    //Return true if there is UFo in the same pos. Attack it
    private boolean attackUfo() {
        Ufo ufo = gameBoard.getUfo(pos);
        if(ufo == null) return false;
        ufo.damage(Config.cowboyDamage);
        return true;
    }

}