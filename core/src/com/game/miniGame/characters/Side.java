package com.game.miniGame.characters;

import java.util.Random;

public enum Side {
    RIGHT, LEFT;

    public static Side random() {
        if(new Random().nextBoolean()) return RIGHT;
        return LEFT;
    }
}