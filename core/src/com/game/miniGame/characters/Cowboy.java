package com.game.miniGame.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.Logger;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.board.GameScreenPos;
import com.game.miniGame.characters.ufos.Ufo;
import com.game.miniGame.Config;
import com.game.miniGame.board.GameBoard;

public class Cowboy extends Image{

    private final GameBoard gameBoard;
    private final GameScreenPos gameScreenPos;

    private int pos = 8;

    public Cowboy(Texture texture, BoardVO board) {
        super(texture);
        this.gameBoard = board.gameBoard;
        this.gameScreenPos = board.gameScreenPos;
        updateScreenPos();
    }

    public void pressedLeft() {
        doStuff(pos-1);
    }
    public void pressedRight() {
        doStuff(pos+1);
    }
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
        this.setPosition(newPos.x - this.getWidth() / 2, newPos.y);
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
