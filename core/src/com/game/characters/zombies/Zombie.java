package com.game.characters.zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.characters.Rhythmical;

public class Zombie extends Image implements Rhythmical {

    public Zombie(Texture texture) {
        super(texture);
    }

    @Override
    public void work() {

    }
}
