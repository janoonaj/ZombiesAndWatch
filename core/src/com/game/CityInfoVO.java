package com.game;

public class CityInfoVO {
    private final int population;
    private final int maxPopulation;
    private final int food;
    private final int militiaLevel;
    private final int defenses;

    public static CityInfoVO test() {
        return new com.game.CityInfoVO(1000, 1500, 23000, 2, 55);
    }

    public static CityInfoVO copyPopulation(CityInfoVO info, int data) {
        return new CityInfoVO(data, info.getMaxPopulation(), info.getFood(), info.getMilitiaLevel(), info.getDefenses());
    }

    public static CityInfoVO copyMaxPopulation(CityInfoVO info, int data) {
        return new CityInfoVO(info.getPopulation(), data, info.getFood(), info.getMilitiaLevel(), info.getDefenses());
    }

    public static CityInfoVO copyFood(CityInfoVO info, int data) {
        return new CityInfoVO(info.getPopulation(), info.getMaxPopulation(), data, info.getMilitiaLevel(), info.getDefenses());
    }

    public static CityInfoVO copyMilitia(CityInfoVO info, int data) {
        return new CityInfoVO(info.getPopulation(), info.getMaxPopulation(), info.getFood(), data, info.getDefenses());
    }

    public static CityInfoVO copyDefenses(CityInfoVO info, int data) {
        return new CityInfoVO(info.getPopulation(), info.getMaxPopulation(),
                                info.getFood(), info.getMilitiaLevel(), Math.min(100, data));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityInfoVO that = (CityInfoVO) o;

        if (population != that.population) return false;
        if (maxPopulation != that.maxPopulation) return false;
        if (food != that.food) return false;
        if (militiaLevel != that.militiaLevel) return false;
        return defenses == that.defenses;

    }

    @Override
    public int hashCode() {
        int result = population;
        result = 31 * result + maxPopulation;
        result = 31 * result + food;
        result = 31 * result + militiaLevel;
        result = 31 * result + defenses;
        return result;
    }
}
