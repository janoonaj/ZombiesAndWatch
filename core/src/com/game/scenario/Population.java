package com.game.scenario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Population extends Label {
    private Integer inhabitants;

    public Population(Integer inhabitants) {
        super(inhabitants.toString(), new Skin(Gdx.files.internal("skins.json")));
        this.inhabitants = inhabitants;
    }

    public void remove(Integer killed) {
        inhabitants -= killed;
        this.setText(inhabitants.toString());
    }
}
