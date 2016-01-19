package com.game;

public class CityInfoVO {
    private final int population;
    private final int maxPopulation;
    private final int food;
    private final int militiaPoints;
    private final int defenses;

    public static CityInfoVO test() {
        return new com.game.CityInfoVO(1000, 1500, 23000, 2, 55);
    }

    public static CityInfoVO copyPopulation(CityInfoVO info, int data) {
        return new CityInfoVO(data, info.getMaxPopulation(), info.getFood(), info.getMilitiaPoints(), info.getDefenses());
    }

    public static CityInfoVO copyMaxPopulation(CityInfoVO info, int data) {
        return new CityInfoVO(info.getPopulation(), data, info.getFood(), info.getMilitiaPoints(), info.getDefenses());
    }

    public static CityInfoVO copyFood(CityInfoVO info, int data) {
        return new CityInfoVO(info.getPopulation(), info.getMaxPopulation(), data, info.getMilitiaPoints(), info.getDefenses());
    }

    public static CityInfoVO copyMilitia(CityInfoVO info, int data) {
        return new CityInfoVO(info.getPopulation(), info.getMaxPopulation(), info.getFood(), data, info.getDefenses());
    }

    public static CityInfoVO copyDefenses(CityInfoVO info, int data) {
        return new CityInfoVO(info.getPopulation(), info.getMaxPopulation(),
                                info.getFood(), info.getMilitiaPoints(), Math.min(100, data));
    }

    public CityInfoVO(int population, int maxPopulation, int food, int militiaPoints, int defenses) {
        this.population = population;
        this.maxPopulation = maxPopulation;
        this.food = food;
        this.militiaPoints = militiaPoints;
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

    public int getMilitiaPoints() {
        return militiaPoints;
    }

    public int getDefenses() {
        return defenses;
    }

    public int getMilitiaLevel() {
        if(militiaPoints < 3) return 1;
        if(militiaPoints < 8) return 2;
        if(militiaPoints < 18) return 3;
        if(militiaPoints < 38) return 4;
        return 5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityInfoVO that = (CityInfoVO) o;

        if (population != that.population) return false;
        if (maxPopulation != that.maxPopulation) return false;
        if (food != that.food) return false;
        if (militiaPoints != that.militiaPoints) return false;
        return defenses == that.defenses;

    }

    @Override
    public int hashCode() {
        int result = population;
        result = 31 * result + maxPopulation;
        result = 31 * result + food;
        result = 31 * result + militiaPoints;
        result = 31 * result + defenses;
        return result;
    }

    @Override
    public String toString() {
        String mssg = "Population: " + population + " Max Pop: " + maxPopulation + " Food: " + food +
                " Militia: " + militiaPoints + " Militia level: " + getMilitiaLevel() + " Defenses: " + defenses;
        return mssg;
    }
}
