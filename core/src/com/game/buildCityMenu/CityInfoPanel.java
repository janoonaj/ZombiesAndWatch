package com.game.buildCityMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.AssetsFactory;
import com.game.CityInfoVO;

public class CityInfoPanel implements Panel {

    private final CityInfoVO cityInfo;
    private Table table;
    private Skin skin;

    public CityInfoPanel(CityInfoVO cityInfo) {
        this.cityInfo = cityInfo;
        skin = new Skin(Gdx.files.internal("skins.json"));
        table = new Table();
        prepareTable();
    }

    private void prepareTable() {
        Image population = new Image(AssetsFactory.instance().getIconPopulation());
        Image maxPopulation = new Image(AssetsFactory.instance().getIconHouse());
        Image food = new Image(AssetsFactory.instance().getIconHarvest());
        Image militia = new Image(AssetsFactory.instance().getIconMilitia());
        Image defenses = new Image(AssetsFactory.instance().getIconWall());

        table.add(population).uniform();
        table.add(new Label(cityInfo.getPopulation() + "", skin)).uniform();
        table.add(food).uniform();
        table.add(new Label(cityInfo.getFood() + "", skin)).uniform();
        table.add(defenses).uniform();
        table.add(new Label(cityInfo.getDefenses() + "%", skin)).uniform();
        table.row();;
        table.add(maxPopulation).uniform();
        table.add(new Label(cityInfo.getMaxPopulation() + "", skin)).center().uniform();
        table.add(militia).uniform();
        table.add(new Label("lvl" + cityInfo.getMilitiaLevel(), skin)).center();
    }

    @Override
    public Table getTable() {
        return table;
    }
}
