package com.game.city;

import com.game.CityInfoVO;
import com.game.buildCityMenu.BuildCityPointsVO;

public class Rules {

    public static int newPopulation(CityInfoVO city) {
        int newPopulation = city.getPopulation();

        if(city.getFood() < newPopulation) newPopulation -= (newPopulation - city.getFood() / 2);
        else if(city.getFood() > newPopulation * 2) newPopulation += (city.getFood() - newPopulation)/5;

        if(city.getDefenses() < 30) newPopulation -= newPopulation / 10;
        if(city.getDefenses() > 60) newPopulation += newPopulation / 10;

        return Math.min(newPopulation, city.getMaxPopulation());
    }

    public static CityInfoVO updateCityInfo(BuildCityPointsVO buildCityPoints, CityInfoVO cityInfo) {
        if(buildCityPoints.getHouse() > 0 ) {
            cityInfo = CityInfoVO.copyMaxPopulation(cityInfo, 25 * buildCityPoints.getHouse());
        }
        if(buildCityPoints.getHarvest() > 0) {
            cityInfo = CityInfoVO.copyFood(cityInfo, 1000 * buildCityPoints.getHarvest());
        }
        if(buildCityPoints.getMilitia() > 0) {
            cityInfo = CityInfoVO.copyMilitia(cityInfo, buildCityPoints.getMilitia());
        }
        if(buildCityPoints.getWall() > 0) {
            cityInfo = CityInfoVO.copyDefenses(cityInfo, 2 * buildCityPoints.getWall());
        }
        return cityInfo;
    }
}
