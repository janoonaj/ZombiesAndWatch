package com.game;

import com.game.buildCityMenu.BuildCityPointsVO;

public class Rules {

    public static CityInfoVO newPopulation(CityInfoVO city) {
        int newPopulation = city.getPopulation();

        if(city.getFood() < newPopulation) newPopulation -= (newPopulation - city.getFood() / 2);
        else if(city.getFood() > newPopulation * 2) newPopulation += (city.getFood() - newPopulation)/5;

        if(city.getDefenses() < 30) newPopulation -= newPopulation / 10;
        if(city.getDefenses() > 60) newPopulation += newPopulation / 10;

        return CityInfoVO.copyPopulation(city, Math.min(newPopulation, city.getMaxPopulation()));
    }

    public static CityInfoVO updateCityInfo(BuildCityPointsVO buildCityPoints, CityInfoVO cityInfo) {
        if(buildCityPoints.getHouse() > 0 ) {
            cityInfo = CityInfoVO.copyMaxPopulation(cityInfo, cityInfo.getMaxPopulation() + 25 * buildCityPoints.getHouse());
        }
        if(buildCityPoints.getHarvest() > 0) {
            cityInfo = CityInfoVO.copyFood(cityInfo, cityInfo.getFood() + 1000 * buildCityPoints.getHarvest());
        }
        if(buildCityPoints.getMilitia() > 0) {
            cityInfo = CityInfoVO.copyMilitia(cityInfo, cityInfo.getMilitiaPoints() + buildCityPoints.getMilitia());
        }
        if(buildCityPoints.getWall() > 0) {
            cityInfo = CityInfoVO.copyDefenses(cityInfo, cityInfo.getDefenses() + 2 * buildCityPoints.getWall());
        }
        return cityInfo;
    }
}
