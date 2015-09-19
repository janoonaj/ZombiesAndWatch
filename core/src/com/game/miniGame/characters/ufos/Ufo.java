package com.game.miniGame.characters.ufos;


import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.List;
import java.util.Random;

enum UfoState {
    GOING_TARGET, GETTING_HUMANS, RUNNING_AWAY
}

public class Ufo extends Image implements com.game.miniGame.characters.Rhythmical {
    private final com.game.miniGame.board.GameScreenPos gameScreenPos;
    private int boardPos;
    private final com.game.miniGame.board.GameBoard gameBoard;
    private int health = com.game.miniGame.Config.healthUfo;
    private int targetPos;
    private UfoState state = UfoState.GOING_TARGET;
    private Image ray;
    public Signal onExtractingHumans = new Signal();
    public Signal onFlyAway = new Signal();
    public Signal onDestroyed = new Signal();
    private Integer numPrisoners = 0;

    public Ufo(Texture texture, int boardPos, com.game.miniGame.board.BoardVO board) {
        super(texture);
        this.boardPos = boardPos;
        this.gameBoard = board.gameBoard;
        this.gameScreenPos = board.gameScreenPos;
        selectHouseTarget();
    }

    @Override
    public void updatePos() {
        if (state == UfoState.GOING_TARGET) {
            moveUfoToTarget();
            if (boardPos == targetPos) state = UfoState.GETTING_HUMANS;
        } else if (state == UfoState.GETTING_HUMANS) {
            extractHumans();
        } else if (state == UfoState.RUNNING_AWAY) {
            if (boardPos == targetPos) {
                flyAway();
                return;
            }
            moveUfoToTarget();
        }
    }

    @Override
    public void draw() {
        float nextX = gameScreenPos.getScreenPosUfo(boardPos).x - this.getImageWidth() / 2;
        float nextY = gameScreenPos.getScreenPosUfo(boardPos).y;
        setPosition(nextX, nextY);
    }

    private void selectHouseTarget() {
        List<Integer> boardPosWithHouses = gameBoard.getPosWithHouses();
        if(boardPosWithHouses.isEmpty()) {
            targetPos = gameBoard.getCenterBoard();
        } else {
            int randomIndex = new Random().nextInt(boardPosWithHouses.size());
            targetPos = boardPosWithHouses.get(randomIndex);
        }
    }

    private void moveUfoToTarget() {
        int nextPos;
        if (targetPos < boardPos) {
            nextPos = boardPos - 1;
        } else {
            nextPos = boardPos + 1;
        }
        gameBoard.removeUfo(this);
        gameBoard.addUfo(nextPos, this);
        boardPos = nextPos;
    }

    private void extractHumans() {
        if (ray == null) {
            Texture rayTexture = com.game.miniGame.AssetsFactory.instance().getUfoRay();
            ray = new Image(rayTexture);
            ray.setX(this.getX());
            ray.setY(this.getY() - this.getImageHeight() - rayTexture.getHeight());
            this.getStage().addActor(ray);
        }

        onExtractingHumans.dispatch(com.game.miniGame.Config.humansExtractedByUfoPerStep);
        com.game.miniGame.scenario.Population.instance().remove(com.game.miniGame.Config.humansExtractedByUfoPerStep);
        numPrisoners += com.game.miniGame.Config.humansExtractedByUfoPerStep;
        if (numPrisoners >= com.game.miniGame.Config.ufoHumansCapacity) {
            selectRandomRunawayBoardPos();
            state = UfoState.RUNNING_AWAY;
            ray.remove();
            ray = null;
        }
    }

    private void selectRandomRunawayBoardPos() {
        if (new Random().nextBoolean()) targetPos = gameBoard.getLeftestPos();
        else targetPos = gameBoard.getRighestPos();
    }

    private void flyAway() {
        onFlyAway.dispatch(this);
        removeListeners();
        remove();
    }

    public void damage(int pointsOfDamage) {
        health -= pointsOfDamage;
        if (health <= 0) {
            destroy();
        }
    }

    private void destroy() {
        onDestroyed.dispatch(this);
        if(ray != null) {
            ray.remove();
            ray = null;
        }
        removeListeners();
        remove();
    }

    private void removeListeners() {
        onFlyAway.removeAllListeners();
        onExtractingHumans.removeAllListeners();
        onDestroyed.removeAllListeners();
    }
}
