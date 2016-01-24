package com.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetsFactory {
    private static AssetsFactory instance;
    private AssetManager manager = new AssetManager();

    public static AssetsFactory instance() {
        if (instance == null) {
            instance = new AssetsFactory();
        }
        return instance;
    }

    private AssetsFactory() {

    }

    public void loadTextures() {

        //Minigame
        manager.load("cowboyBW.png", Texture.class);
        manager.load("zombie_test_show_R.png", Texture.class);
        manager.load("zombie_test_show_L.png", Texture.class);
        manager.load("ufo.png", Texture.class);
        manager.load("ufoRay.png", Texture.class);
        manager.load("wall_green.png", Texture.class);
        manager.load("wall_yellow.png", Texture.class);
        manager.load("wall_red.png", Texture.class);
        manager.load("house.png", Texture.class);
        manager.load("hotel.png", Texture.class);
        manager.load("farm.png", Texture.class);
        manager.load("church.png", Texture.class);
        manager.load("shotgun.jpg", Texture.class);
        manager.load("zombieHead.png", Texture.class);
        manager.load("militia.png", Texture.class);
        manager.load("militiaMark.png", Texture.class);

        //City Menu
        manager.load("buttons/harvest.png", Texture.class);
        manager.load("buttons/house.png", Texture.class);
        manager.load("buttons/militia.png", Texture.class);
        manager.load("buttons/wall.png", Texture.class);
        manager.load("buttons/ok.png", Texture.class);
        manager.load("buttons/plus.png", Texture.class);
        manager.load("buttons/minus.png", Texture.class);
        manager.load("buttons/markEmpty.png", Texture.class);
        manager.load("buttons/markFilled.png", Texture.class);
        manager.load("icons/house.png", Texture.class);
        manager.load("icons/militia.png", Texture.class);
        manager.load("icons/wall.png", Texture.class);
        manager.load("icons/harvest.png", Texture.class);
        manager.load("icons/population.png", Texture.class);
    }

    public boolean assetsLoaded() {
        return manager.update();
    }

    public Texture getCowboyBW() {
        return manager.get("cowboyBW.png");
    }
    public Texture getWallGreen() { return manager.get("wall_green.png");}
    public Texture getWallYellow() { return manager.get("wall_yellow.png");}
    public Texture getWallRed() { return manager.get("wall_red.png");}
    public Texture getMilitia() { return manager.get("militia.png");}
    public Texture getMilitiaMark() { return manager.get("militiaMark.png");}

    public Texture getZombieLeft() {
        return manager.get("zombie_test_show_L.png");
    }
    public Texture getZombieRight() {
        return manager.get("zombie_test_show_R.png");
    }
    public Texture getHouse() {
        return manager.get("house.png");
    }
    public Texture getHotel() {
        return manager.get("hotel.png");
    }
    public Texture getFarm() {
        return manager.get("farm.png");
    }
    public Texture getChurch() {
        return manager.get("church.png");
    }
    public Texture getUfo() { return manager.get("ufo.png"); }
    public Texture getUfoRay() { return manager.get("ufoRay.png"); }
    public Texture getShotgun() { return manager.get("shotgun.jpg"); }
    public Texture getZombieHead() { return manager.get("zombieHead.png"); }


    public Texture getButtonHarvest() { return manager.get("buttons/harvest.png"); }
    public Texture getButtonHouse() { return manager.get("buttons/house.png"); }
    public Texture getButtonMilitia() { return manager.get("buttons/militia.png"); }
    public Texture getButtonWall() { return manager.get("buttons/wall.png"); }
    public Texture getButtonOK() { return manager.get("buttons/ok.png"); }
    public Texture getButtonPlus() { return manager.get("buttons/plus.png"); }
    public Texture getButtonMinus() { return manager.get("buttons/minus.png"); }
    public Texture getMarkEmpty() { return manager.get("buttons/markEmpty.png"); }
    public Texture getMarkFilled() { return manager.get("buttons/markFilled.png"); }

    public Texture getIconHouse() { return manager.get("icons/house.png"); }
    public Texture getIconMilitia() { return manager.get("icons/militia.png"); }
    public Texture getIconWall() { return manager.get("icons/wall.png"); }
    public Texture getIconHarvest() { return manager.get("icons/harvest.png"); }
    public Texture getIconPopulation() { return manager.get("icons/population.png"); }

    public void dispose() {
        manager.dispose();
    }
}
