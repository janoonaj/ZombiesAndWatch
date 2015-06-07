package com.game.scenario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Population extends Label {
    private static Population instance;
    private static Integer initialInhabitants;

    private Integer inhabitants;

    public static Population instance() {
        if(Population.instance == null) {
            Skin skin = new Skin(Gdx.files.internal("skins.json"));
            Population.instance = new Population(Population.initialInhabitants, skin);
        }
        return Population.instance;
    }

    public static void setInitialInhabitants(int inhabitants) {
        Population.initialInhabitants = inhabitants;
    }

    private Population(Integer inhabitants, Skin skin) {
        super(inhabitants.toString(), skin);
        this.inhabitants = inhabitants;
        updateText();
    }

    public void remove(Integer killed) {
        inhabitants -= killed;
        updateText();
    }

    private void updateText() {
        this.setText("Habitantes vivos: " + inhabitants.toString());
    }
}
