package com.game.buildCityMenu.buildCityButtons;


import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.AssetsFactory;
import com.game.buildCityMenu.Panel;

public class BuildCityController implements Panel {
    private final int MARKS_PADDING = 3;

    private final Image mainImage;
    private Table table = new Table();
    private int numSelectedMarks = 0;
    private int numMaxMarks;
    private Integer type;
    private ImageButton bttnPlus;
    private ImageButton bttnMinus;
    public Signal onClick = new Signal();

    public BuildCityController(Texture image, int type, int numMaxMarks) {
        this.numMaxMarks = numMaxMarks;
        this.mainImage = new Image(image);
        this.type = type;
        bttnPlus = new ImageButton(new Image(AssetsFactory.instance().getButtonPlus()).getDrawable());
        bttnMinus = new ImageButton(new Image(AssetsFactory.instance().getButtonMinus()).getDrawable());
        eventButtons();
        prepareTable();
    }

    public void addMark() {
        numSelectedMarks++;
        prepareTable();
    }

    public void removeMark() {
        numSelectedMarks--;
        prepareTable();
    }

    public boolean canAddMark() {
        return numSelectedMarks < (numMaxMarks);
    }

    public boolean canRemoveMark() {
        return numSelectedMarks > 0;
    }

    private void prepareTable() {
        table.clear();
        Table tableMarks = new Table();
        int markedCells = 0;
        for(int markIndex = 0; markIndex < numMaxMarks; markIndex++) {
            if(markedCells < numSelectedMarks) {
                tableMarks.add(getMarked()).padRight(MARKS_PADDING);
                markedCells++;
            }
            else
                tableMarks.add(getUnmarked()).padRight(MARKS_PADDING);
        }
        table.add(tableMarks);
        table.row();
        table.add(mainImage);
        Table tablePluMin1 = new Table();
        tablePluMin1.add(bttnPlus);tablePluMin1.row();tablePluMin1.add(bttnMinus);
        table.add(tablePluMin1);
    }

    private Image getMarked() {
        return new Image(AssetsFactory.instance().getMarkFilled());
    }

    private Image getUnmarked() {
        return new Image(AssetsFactory.instance().getMarkEmpty());
    }

    private void eventButtons() {
        bttnPlus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick.dispatch(new ClickEventVO(type, ButtonType.PLUS));
            }
        });
        bttnMinus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick.dispatch(new ClickEventVO(type, ButtonType.MINUS));
            }
        });
    }

    @Override
     public Table getTable() {
        return table;
    }

    //@Override
    public void dispose() {
        onClick.removeAllListeners();
    }
}
