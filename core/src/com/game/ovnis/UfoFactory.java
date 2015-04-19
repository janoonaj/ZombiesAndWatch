package com.game.ovnis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.Config;
import com.game.Metronome;
import com.game.board.GameBoard;
import com.game.board.GameScreenPos;
import com.game.characters.Side;
import com.game.test.Test1;

//TODO: object pooling?
public class UfoFactory {
    private final GameBoard board;
    private final Metronome metronome = new Metronome(Config.timeOvni);
    private final Test1 test1;
    private final GameScreenPos gameScreenPos;

    public UfoFactory(GameBoard board, GameScreenPos gameScreenPos, Test1 test1) {
        this.board = board;
        this.test1 = test1;
        this.gameScreenPos = gameScreenPos;
    }

    public Metronome getMetronome () {
        return metronome;
    }

    public Ufo createUfo(Side side) {
        if (side == Side.LEFT) {
            return createUfoLeft();
        }
        if (side == Side.RIGHT) {
            return createUfoRight();
        }
        return null;
    }

    private Ufo createUfoRight() {
        int boardPos = board.getRighestPos();
        Ufo ufo = flyUfo(boardPos, gameScreenPos.getScreenPosOvni(boardPos), Side.LEFT, AssetsFactory.instance().getOvni());
        metronome.subscribe(ufo);
        return ufo;
    }

    private Ufo createUfoLeft() {
        int boardPos = board.getLeftestPos();
        Ufo ufo = flyUfo(boardPos, gameScreenPos.getScreenPosOvni(boardPos), Side.RIGHT, AssetsFactory.instance().getOvni());
        metronome.subscribe(ufo);
        return ufo;
    }

    private Ufo flyUfo(int boardPos, Vector2 screenPos, Side side, Texture texture) {
        Ufo ufo = new Ufo(texture, boardPos, side, board, gameScreenPos, test1);
        ufo.setPosition(screenPos.x - texture.getWidth() / 2, screenPos.y);
        board.addUfo(boardPos, ufo);
        return ufo;
    }
}
