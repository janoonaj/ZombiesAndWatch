package com.game.miniGame.characters.militia;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.AssetsFactory;
import com.game.miniGame.Config;
import com.game.miniGame.Metronome;
import com.game.miniGame.board.BoardVO;
import com.game.miniGame.board.GameBoard;
import com.game.miniGame.board.GameScreenPos;

import java.util.ArrayList;
import java.util.List;

public class MilitiaController {
    private final int FIRST_POSITION = 6;

    private final Militia militia;
    private final GameScreenPos gameScreenPos;
    private final ArrayList<Selector> clickableSelectors = new ArrayList<Selector>();
    private final Metronome metronome;


    public MilitiaController(BoardVO board, int militiaLevel) {
        gameScreenPos = board.gameScreenPos;
        militia = new Militia(AssetsFactory.instance().getMilitia(), militiaLevel, board);
        metronome = new Metronome(Config.timeMilitia - Config.timeIncreaseMilitiaPerLevel * (militiaLevel - 1));
        metronome.subscribe(militia);
        createControllers();
    }

    private void createControllers() {
        //Positions 6..10
        for(int i = 0; i < 5; i++) {
            Texture txt = AssetsFactory.instance().getMilitiaMark();
            Vector2 posSelector =  gameScreenPos.getScreen2Militia(FIRST_POSITION + i);
            posSelector.x -= txt.getWidth() / 2;
            Selector selector = new Selector(txt, posSelector, FIRST_POSITION + i);
            clickableSelectors.add(selector);
            selector.setVisible(false);
        }
    }

    public List<Selector> getSelectors() {
        return clickableSelectors;
    }

    public Metronome getMetronome() {
        return metronome;
    }

    public Militia getMilitia() {
        return militia;
    }

    public void clickMilitia(){
        militia.stop();

        //Keep the selector sharing the cell with the Militia character hidden.
        for(int index = 0; index < clickableSelectors.size(); index++) {
            if(militia.getPos() == index + FIRST_POSITION) {
                continue;
            }
            clickableSelectors.get(index).setVisible(true);
        }
    }

    public void clickSelector(Selector selectorClicked) {
        for(Selector selector : clickableSelectors) {
            selector.setVisible(false);
        }
        metronome.reset();
        militia.moveTo(selectorClicked.pos);
    }

    public void dispose() {
        metronome.kill();
    }
}
