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
}
