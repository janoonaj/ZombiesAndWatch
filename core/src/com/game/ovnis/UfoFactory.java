package com.game.ovnis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.Config;
import com.game.GameFactory;
import com.game.Metronome;
import com.game.board.BoardVO;
import com.game.characters.Side;

//TODO: object pooling?
public class UfoFactory extends GameFactory {
    private final Metronome metronome = new Metronome(Config.timeOvni);
    private final BoardVO board;

    public UfoFactory(BoardVO board) {
        this.board = board;
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
        int boardPos = board.gameBoard.getRighestPos();
        Ufo ufo = flyUfo(boardPos, board.gameScreenPos.getScreenPosOvni(boardPos), Side.LEFT, AssetsFactory.instance().getOvni());
        metronome.subscribe(ufo);
        return ufo;
    }

    private Ufo createUfoLeft() {
        int boardPos = board.gameBoard.getLeftestPos();
        Ufo ufo = flyUfo(boardPos, board.gameScreenPos.getScreenPosOvni(boardPos), Side.RIGHT, AssetsFactory.instance().getOvni());
        metronome.subscribe(ufo);
        return ufo;
    }

    private Ufo flyUfo(int boardPos, Vector2 screenPos, Side side, Texture texture) {
        Ufo ufo = new Ufo(texture, boardPos, side, board, gameEngine);
        ufo.setPosition(screenPos.x - texture.getWidth() / 2, screenPos.y);
        board.gameBoard.addUfo(boardPos, ufo);
        return ufo;
    }
}
