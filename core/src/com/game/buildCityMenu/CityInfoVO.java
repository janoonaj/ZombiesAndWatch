package com.game.buildCityMenu;

public class CityInfoVO {
    private int population;
    private int maxPopulation;
    private int food;
    private int militiaLevel;
    private int defenses;

    public static CityInfoVO test() {
        return new CityInfoVO(1000, 1500, 23000, 2, 55);
    }

    public CityInfoVO(int population, int maxPopulation, int food, int militiaLevel, int defenses) {
        this.population = population;
        this.maxPopulation = maxPopulation;
        this.food = food;
        this.militiaLevel = militiaLevel;
        this.defenses = defenses;
    }

    public int getPopulation() {
        return population;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public int getFood() {
        return food;
    }

    public int getMilitiaLevel() {
        return militiaLevel;
    }

    public int getDefenses() {
        return defenses;
    }
}
