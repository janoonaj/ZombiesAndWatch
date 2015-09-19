package com.game.miniGame.characters.zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.miniGame.Logger;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.characters.Side;


public class ZombieFromBelow extends Zombie{
    private Image rising;
    private Boolean rised = false;

    public ZombieFromBelow(Texture textureZombie, Texture textureRising, int boardPos, Side side, BoardVO board) {
        super(textureZombie, boardPos, side, board);
        removeActor(img);
        rising = new Image(textureRising);
        addActor(rising);
    }

    /*
    Different behaviour than parent.
    It's born inside the city. Go to Houses.
     */
    public void updatePos() {
       if(rise()) return;

        if(attackHouse(boardPos)) return;

        int nextPos = calculateNextPos();
        if(nextPos < 1 || nextPos > gameBoard.getRighestPos()) return;

        if(attackWall(boardPos)) return;

        changePos(nextPos);
    }

    private Boolean rise() {
        if(rised) return false;
        rised = true;
        removeActor(rising);
        addActor(img);
        return true;
    }
}
