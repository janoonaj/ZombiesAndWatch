package com.game.miniGame.characters.zombies;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.miniGame.Config;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.characters.Side;
import com.game.miniGame.scenario.Population;

import java.util.Random;

public class Zombie extends Image implements com.game.miniGame.characters.Rhythmical {
    private final com.game.miniGame.board.GameScreenPos gameScreenPos;
    private int boardPos;
    private final GameBoard gameBoard;
    private final Side side;
    private int health = Config.healthZombie;
    public Signal onKilled = new Signal();

    public Zombie(Texture texture, int boardPos, Side side, com.game.miniGame.board.BoardVO board) {
        super(texture);
        this.boardPos = boardPos;
        this.gameBoard = board.gameBoard;
        this.side = side;
        this.gameScreenPos = board.gameScreenPos;
    }

    public void updatePos() {
        int nextPos = boardPos;
        if (side == Side.LEFT) {
            nextPos = boardPos - 1;
            if (nextPos < 1) return;
        }
        if (side == Side.RIGHT) {
            nextPos = boardPos + 1;
            if (nextPos > gameBoard.getRighestPos()) return;
        }
        if (gameBoard.getWall(nextPos) != null) {
            gameBoard.getWall(nextPos).damage(Config.zombieDamage);
            return;
        }

        if (gameBoard.getHouse(nextPos) != null) {
            gameBoard.getHouse(nextPos).damage(Config.zombieDamage);
            Population.instance().remove(Config.humansKilledByZombiesPerStep);
            return;
        }

        changePos(nextPos);
    }

    public void draw() {
        float nextX = gameScreenPos.getScreenPosZombies(boardPos).x - this.getImageWidth() / 2;
        float nextY = gameScreenPos.getScreenPosZombies(boardPos).y;
        if (gameBoard.getZombies(boardPos).size() > 1) {
            //temp "horde" effect
            nextY += new Random().nextInt(40) - 20;
            nextX += new Random().nextInt(10) - 5;
        }
        setPosition(nextX, nextY);
    }

    private void changePos(int newPos) {
        gameBoard.removeZombie(boardPos, this);
        gameBoard.addZombie(newPos, this);
        boardPos = newPos;
    }

    public void damage(int pointsOfDamage) {
        health -= pointsOfDamage;
        if (health <= 0) {
            onKilled.dispatch(this);
            onKilled.removeAllListeners();
            remove();
        }
    }
}
