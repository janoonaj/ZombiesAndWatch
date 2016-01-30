package com.game.miniGame.characters.militia;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.miniGame.board.GameScreenPos;
import com.game.miniGame.characters.Rhythmical;

public class Militia extends Image implements Rhythmical {
    private final int level;
    private final GameScreenPos gameScreenPos;
    private int pos = 8;
    private int nextPos = 8;
    private int movingToPos = 8;

    public Militia(Texture texture, int militiaLevel, GameScreenPos gameScreenPos) {
        super(texture);
        level = militiaLevel;
        this.gameScreenPos = gameScreenPos;
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
        return false;
    }

    private void updateScreenPos() {
        Vector2 newPos = gameScreenPos.getScreen2Militia(pos);
        this.setPosition(newPos.x - this.getWidth() / 2, newPos.y);
    }
}
