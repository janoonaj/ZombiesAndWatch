package com.game.buildCityMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.game.CityInfoVO;

import java.util.Arrays;
import java.util.List;

public class CityInfoResultsPanel implements Panel {

    private Table table;

    public CityInfoResultsPanel(CityInfoVO cityPrev, CityInfoVO city, ImageButton bttnOK) {
        table = new Table();
        bttnOK.setVisible(false);
        prepareTable(table, cityPrev, city, bttnOK);
    }

    private void prepareTable(Table table, CityInfoVO cityPrev, CityInfoVO city, final ImageButton bttnOK) {
        Skin skin = new Skin(Gdx.files.internal("skins.json"));
        Skin skinBold = new Skin(Gdx.files.internal("skinsBold.json"));

        final Label titleBonus = new Label("BONUS", skinBold);
        Label titleAfter = new Label("CITY", skinBold);

        Label population = new Label("Population", skinBold);
        Label maxPopulation = new Label("MaxPopulation", skinBold);
        Label food = new Label("Food", skinBold);
        Label militia = new Label("Militia", skinBold);
        Label defenses = new Label("Defenses", skinBold);

        Label populationBefore = new Label(cityPrev.getPopulation() + "", skin);
        Label maxPopulationBefore = new Label(cityPrev.getMaxPopulation() + "", skin);
        Label foodBefore = new Label(cityPrev.getFood() + "", skin);
        Label militiaBefore = new Label("Lvl " + cityPrev.getMilitiaLevel(), skin);
        Label defensesBefore = new Label(cityPrev.getDefenses() + "%", skin);

        Label populationBonus = new Label(parseBonus(city.getPopulation() - cityPrev.getPopulation(), ""), skin);
        Label maxPopulationBonus = new Label(parseBonus(city.getMaxPopulation() - cityPrev.getMaxPopulation(), ""), skin);
        Label foodBonus = new Label(parseBonus(city.getFood() - cityPrev.getFood(), ""), skin);
        Label militiaBonus = new Label(parseBonus(city.getMilitiaPoints() - cityPrev.getMilitiaPoints(), " points"), skin);
        Label defensesBonus = new Label(parseBonus(city.getDefenses() - cityPrev.getDefenses(), "%"), skin);

        Label populationAfter = new Label(Integer.toString(city.getPopulation()), skin);
        Label maxPopulationAfter = new Label(Integer.toString(city.getMaxPopulation()), skin);
        Label foodAfter = new Label(Integer.toString(city.getFood()), skin);
        Label militiaAfter = new Label("Lvl " + city.getMilitiaLevel(), skin);
        Label defensesAfter = new Label(city.getDefenses() + "%", skin);

        final List<Label> labels = Arrays.asList(foodBonus, foodAfter, militiaBonus, militiaAfter,
                defensesBonus, defensesAfter, maxPopulationBonus, maxPopulationAfter, populationBonus, populationAfter);
        for (Label label : labels) {
            label.setVisible(false);
        }

        table.add(new Label("", skin)).uniform().pad(20);
        table.add(new Label("", skin)).uniform().pad(20);
        table.add(titleBonus).uniform().pad(20);
        table.add(titleAfter).uniform().pad(20);
        table.row();
        table.add(food).uniform().pad(20);
        table.add(foodBefore).uniform().pad(20);
        table.add(foodBonus).uniform().pad(20);
        table.add(foodAfter).uniform().pad(20);
        table.row();
        table.add(militia).uniform().pad(20);
        table.add(militiaBefore).uniform().pad(20);
        table.add(militiaBonus).uniform().pad(20);
        table.add(militiaAfter).uniform().pad(20);
        table.row();
        table.add(defenses).uniform().pad(20);
        table.add(defensesBefore).uniform().pad(20);
        table.add(defensesBonus).uniform().pad(20);
        table.add(defensesAfter).uniform().pad(20);
        table.row();
        table.add(maxPopulation).uniform().pad(20);
        table.add(maxPopulationBefore).uniform().pad(20);
        table.add(maxPopulationBonus).uniform().pad(20);
        table.add(maxPopulationAfter).uniform().pad(20);
        table.row();
        table.add(population).uniform().pad(20);
        table.add(populationBefore).uniform().pad(20);
        table.add(populationBonus).uniform().pad(20);
        table.add(populationAfter).uniform().pad(20);

        final float timeMult = 0.7f;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                labels.get(0).setVisible(true);
            }
        }, timeMult, 1, 1);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {labels.get(1).setVisible(true);}}, 2*timeMult, 1, 1);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {labels.get(2).setVisible(true);}}, 3*timeMult, 1, 1);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {labels.get(3).setVisible(true);}}, 4*timeMult, 1, 1);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {labels.get(4).setVisible(true);}}, 5*timeMult, 1, 1);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {labels.get(5).setVisible(true);}}, 6*timeMult, 1, 1);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {labels.get(6).setVisible(true);}}, 7*timeMult, 1, 1);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {labels.get(7).setVisible(true);}}, 8*timeMult, 1, 1);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {labels.get(8).setVisible(true);}}, 9*timeMult, 1, 1);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {labels.get(9).setVisible(true);bttnOK.setVisible(true); }}, 10*timeMult, 1, 1);
    }

    private String parseBonus(int bonus, String extraChar) {
        if (bonus < 0) {
            return "-" + bonus + extraChar;
        } else {
            return "+" + bonus + extraChar;
        }
    }

    @Override
    public Table getTable() {
        return table;
    }
}
