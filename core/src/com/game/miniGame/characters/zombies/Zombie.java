package com.game.miniGame.characters.zombies;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.miniGame.Config;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.characters.Side;
import com.game.miniGame.scenario.Population;

import java.util.Random;

public class Zombie extends Group implements com.game.miniGame.characters.Rhythmical {
    private final com.game.miniGame.board.GameScreenPos gameScreenPos;
    protected Image img;
    protected int boardPos;
    protected final GameBoard gameBoard;
    protected final Side side;
    protected int health = Config.healthZombie;
    public Signal onKilled = new Signal();

    public Zombie(Texture texture, int boardPos, Side side, BoardVO board) {
        super();
        img = new Image(texture);
        this.addActor(img);
        this.boardPos = boardPos;
        this.gameBoard = board.gameBoard;
        this.side = side;
        this.gameScreenPos = board.gameScreenPos;
    }

    public void updatePos() {
        if(attackHouse(boardPos)) return;

        int nextPos = calculateNextPos();
        if(nextPos < 1 || nextPos > gameBoard.getRighestPos()) return;

        if(attackWall(nextPos)) return;

        changePos(nextPos);
    }

    public void draw() {
        float nextX = gameScreenPos.getScreenPosZombies(boardPos).x - img.getWidth() / 2;
        float nextY = gameScreenPos.getScreenPosZombies(boardPos).y;
        if (gameBoard.getZombies(boardPos).size() > 1) {
            //temp "horde" effect
            nextY += new Random().nextInt(40) - 20;
            nextX += new Random().nextInt(10) - 5;
        }
        setPosition(nextX, nextY);
    }

    public void damage(int pointsOfDamage) {
        health -= pointsOfDamage;
        if (health <= 0) killed();
    }

    protected Boolean attackHouse(int pos) {
        if (gameBoard.getHouse(pos) != null) {
            gameBoard.getHouse(pos).damage(Config.zombieDamage);
            Population.instance().remove(Config.humansKilledByZombiesPerStep);
            return true;
        }
        return false;
    }

    protected Boolean attackWall(int nextPos) {
        if (gameBoard.getWall(nextPos) != null) {
            gameBoard.getWall(nextPos).damage(Config.zombieDamage);
            return true;
        }
        return false;
    }

    protected int calculateNextPos() {
        if (side == Side.LEFT) return boardPos - 1;
        if (side == Side.RIGHT) return boardPos + 1;
        return boardPos;
    }

    protected void changePos(int newPos) {
        gameBoard.removeZombie(boardPos, this);
        gameBoard.addZombie(newPos, this);
        boardPos = newPos;
    }

    protected void killed() {
        onKilled.dispatch(this);
        onKilled.removeAllListeners();
        remove();
    }
}
