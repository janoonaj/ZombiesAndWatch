package com.game.miniGame.characters.militia;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.miniGame.Config;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.board.GameScreenPos;
import com.game.miniGame.characters.PlayerBehaviour;
import com.game.miniGame.characters.Rhythmical;
import com.game.miniGame.characters.ufos.Ufo;

public class Militia extends Image implements Rhythmical {
    private final int level;
    private final GameScreenPos gameScreenPos;
    private final GameBoard gameBoard;
    private final PlayerBehaviour playerBehaviour;
    private int pos = 8;
    private int nextPos = 8;
    private int movingToPos = 8;

    public Militia(Texture texture, int militiaLevel, BoardVO board) {
        super(texture);
        level = militiaLevel;
        this.gameScreenPos = board.gameScreenPos;
        this.gameBoard = board.gameBoard;
        this.playerBehaviour = new PlayerBehaviour(board.gameBoard);
        updateScreenPos();
    }

    public int getPos() { return pos; }

    public void moveTo(int pos) {
        movingToPos = pos;
    }

    public void stop() {
        movingToPos = pos;
    }

    @Override
    public void updatePos() {
        if(!shoot()) {
            move();
        }
    }

    @Override
    public void draw() {
        if(nextPos != pos) {
            pos = nextPos;
            updateScreenPos();
        }
    }

    private void move() {
        if(movingToPos > pos) nextPos = pos + 1;
        if(movingToPos < pos) nextPos = pos - 1;
    }

    private boolean shoot() {
        if(playerBehaviour.attackZombie(pos, Config.militiaDamage)) return true;
        if(playerBehaviour.attackUfo(pos, Config.militiaDamage)) return true;
        if(playerBehaviour.attackZombie(pos + 1, Config.militiaDamage)) return true;
        return false;
    }

    private void updateScreenPos() {
        Vector2 newPos = gameScreenPos.getScreen2Militia(pos);
        this.setPosition(newPos.x - this.getWidth() / 2, newPos.y);
    }
}
