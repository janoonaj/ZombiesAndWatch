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
    private int pos = 8;
    private final GameBoard gameBoard;
    private final GameScreenPos gameScreenPos;
    private final PlayerBehaviour playerBehaviour;

    public Cowboy(Texture texture, BoardVO board) {
        super(texture);
        gameBoard = board.gameBoard;
        gameScreenPos = board.gameScreenPos;
        playerBehaviour = new PlayerBehaviour(board.gameBoard);
        updateScreenPos();
    }

    public void pressedLeft() {
        doStuff(pos-1);
    }
    public void pressedRight() {
        doStuff(pos + 1);
    }
    public void shoot() {
        if(playerBehaviour.attackZombie(pos, Config.cowboyDamage)) return;
        if(playerBehaviour.attackUfo(pos, Config.cowboyDamage)) return;
        if(playerBehaviour.attackZombie(pos + 1, Config.cowboyDamage)) return;
        if(playerBehaviour.attackZombie(pos - 1, Config.cowboyDamage)) return;
    }

    private void doStuff(int nextPos) {
        if(playerBehaviour.attackZombie(nextPos, Config.cowboyDamage)) return;
        if(nextPos == gameBoard.nonPlayableLeftEdge || nextPos == gameBoard.nonPlayableRightEdge) return;
        pos = nextPos;
        updateScreenPos();
    }

    private void updateScreenPos() {
        Vector2 newPos = gameScreenPos.getScreen2Cowboy(pos);
        this.setPosition(newPos.x - this.getWidth() / 2, newPos.y);
    }
}
