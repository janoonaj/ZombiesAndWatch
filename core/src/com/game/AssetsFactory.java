package com.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetsFactory {
    private static AssetsFactory instance;
    private AssetManager manager = new AssetManager();

    public static AssetsFactory instance() {
        if(instance == null) {
            instance = new AssetsFactory();
        }
        return instance;
    }

    private AssetsFactory() {

    }

    public void loadTextures() {
        manager.load("cowboyBW.png", Texture.class);
        manager.load("cowboyColor.png", Texture.class);
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

    public void dispose() {
        manager.dispose();
    }
}
