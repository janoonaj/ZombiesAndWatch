package com.game.buildCityMenu;

public class BuildCityPointsVO {
    private final int house;
    private final int harvest;
    private final int wall;
    private final int militia;

    public BuildCityPointsVO(int house, int harvest, int wall, int militia) {
        this.house = house;
        this.harvest = harvest;
        this.wall = wall;
        this.militia = militia;
    }

    public int getHouse() {
        return house;
    }

    public int getHarvest() {
        return harvest;
    }

    public int getWall() {
        return wall;
    }

    public int getMilitia() {
        return militia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuildCityPointsVO that = (BuildCityPointsVO) o;

        if (house != that.house) return false;
        if (harvest != that.harvest) return false;
        if (wall != that.wall) return false;
        return militia == that.militia;

    }

    @Override
    public int hashCode() {
        int result = house;
        result = 31 * result + harvest;
        result = 31 * result + wall;
        result = 31 * result + militia;
        return result;
    }
}
