package com.game.miniGame.characters.zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.miniGame.Config;
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

    public void updatePos() {
        if(rise()) return;
        super.updatePos();
    }

    private Boolean rise() {
        if(rised) return false;
        rised = true;
        removeActor(rising);
        addActor(img);
        return true;
    }

    /*
    Attacks wall if it's in its same position.
     */
    protected Boolean attackWall(int nextPos) {
        if (gameBoard.getWall(boardPos) != null) {
            gameBoard.getWall(boardPos).damage(Config.zombieDamage);
            return true;
        }
        return false;
    }
}
