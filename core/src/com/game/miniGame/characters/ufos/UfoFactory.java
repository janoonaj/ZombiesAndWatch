package com.game.miniGame.characters.ufos;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.miniGame.characters.Side;
import com.game.miniGame.Config;
import com.game.miniGame.board.BoardVO;
import com.game.signal.EventListener;
import com.game.signal.SignalListener;

//TODO: object pooling?
public class UfoFactory implements SignalListener{
    private final com.game.miniGame.Metronome metronome = new com.game.miniGame.Metronome(Config.timeOvni);
    private final BoardVO board;

    public UfoFactory(BoardVO board) {
        this.board = board;
    }

    public com.game.miniGame.Metronome getMetronome () {
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
        Ufo ufo = flyUfo(boardPos, board.gameScreenPos.getScreenPosUfo(boardPos), AssetsFactory.instance().getUfo());
        metronome.subscribe(ufo);
        return ufo;
    }

    private Ufo createUfoLeft() {
        int boardPos = board.gameBoard.getLeftestPos();
        Ufo ufo = flyUfo(boardPos, board.gameScreenPos.getScreenPosUfo(boardPos), AssetsFactory.instance().getUfo());
        metronome.subscribe(ufo);
        return ufo;
    }

    private Ufo flyUfo(int boardPos, Vector2 screenPos, Texture texture) {
        Ufo ufo = new Ufo(texture, boardPos, board);
        ufo.setPosition(screenPos.x - texture.getWidth() / 2, screenPos.y);
        board.gameBoard.addUfo(boardPos, ufo);
        ufo.onFlyAway.add(new EventListener(this));
        ufo.onDestroyed.add(new EventListener(this));
        return ufo;
    }

    @Override
    public void signalReceived(Signal signal, Object data) {
        metronome.unsubscribe((Ufo)data);
        board.gameBoard.removeUfo((Ufo)data);
    }
}
