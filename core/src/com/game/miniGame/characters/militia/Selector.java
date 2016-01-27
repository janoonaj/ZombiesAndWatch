package com.game.miniGame.characters.militia;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Selector extends Image {

    public final int pos;

    public Selector(Texture militiaMark, Vector2 screen2Militia, int pos) {
        super(militiaMark);
        setPosition(screen2Militia.x, screen2Militia.y);
        this.pos = pos;
    }
}
