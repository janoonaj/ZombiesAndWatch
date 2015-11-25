package com.game.buildCityMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CityInfoPanel implements Panel {

    private final com.game.CityInfoVO cityInfo;
    private Table table;
    private Skin skin;

    public CityInfoPanel(com.game.CityInfoVO cityInfo) {
        this.cityInfo = cityInfo;
        skin = new Skin(Gdx.files.internal("skins.json"));
        table = new Table();
        prepareTable();
    }

    private void prepareTable() {
        Label population = new Label("Population: " + cityInfo.getPopulation(), skin);
        Label maxPopulation = new Label("MaxPopulation: " + cityInfo.getMaxPopulation(), skin);
        Label food = new Label("Food: " +  cityInfo.getFood(), skin);
        Label militia = new Label("Militia: Lvl"  +  cityInfo.getMilitiaLevel(), skin);
        Label defenses = new Label("Defenses: " +  cityInfo.getDefenses() + "%", skin);
        table.add(population).uniform();
        table.add(food).uniform();
        table.add(defenses).uniform();
        table.row();
        table.add(maxPopulation).uniform();
        table.add(militia).uniform();
    }

    @Override
    public Table getTable() {
        return table;
    }
}
