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
        manager.load("cowboyBW.png", Texture.class);
        manager.load("cowboyColor.png", Texture.class);
        manager.load("zombieR.png", Texture.class);
        manager.load("zombieL.png", Texture.class);
        manager.load("wall_green.png", Texture.class);
        manager.load("wall_yellow.png", Texture.class);
        manager.load("wall_red.png", Texture.class);
        manager.load("house.png", Texture.class);
        manager.load("hotel.png", Texture.class);
        manager.load("farm.png", Texture.class);
        manager.load("church.png", Texture.class);
    }

    public boolean assetsLoaded() {
        return manager.update();
    }

    public Texture getCowboyBW() {
        return manager.get("cowboyBW.png");
    }

    public Texture getCowboyColor() {
        return manager.get("cowboyColor.png");
    }

    public Texture getWallGreen() { return manager.get("wall_green.png");}
    public Texture getWallYellow() { return manager.get("wall_yellow.png");}
    public Texture getWallRed() { return manager.get("wall_red.png");}

    public Texture getZombieLeft() {
        return manager.get("zombieL.png");
    }
    public Texture getZombieRight() {
        return manager.get("zombieR.png");
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

    public void dispose() {
        manager.dispose();
    }
}
