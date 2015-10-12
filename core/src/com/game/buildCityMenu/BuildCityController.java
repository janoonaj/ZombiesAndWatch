package com.game.buildCityMenu;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.AssetsFactory;

public class BuildCityController implements Panel {
    private final int MARKS_PADDING = 3;

    private final Image mainImage;
    private Table table = new Table();
    private int numSelectedMarks = 0;
    private int numMaxMarks;
    private ImageButton bttnPlus;
    private ImageButton bttnMinus;

    public BuildCityController(Texture image, int numMaxMarks) {
        this.numMaxMarks = numMaxMarks;
        this.mainImage = new Image(image);
        bttnPlus = new ImageButton(new Image(AssetsFactory.instance().getButtonPlus()).getDrawable());
        bttnMinus = new ImageButton(new Image(AssetsFactory.instance().getButtonMinus()).getDrawable());
        prepareTable();
    }

    private void prepareTable() {
        Table tableSelector1 = new Table();
        for(int markIndex = 0; markIndex < numMaxMarks; markIndex++) {
            tableSelector1.add(getUnmarked()).padRight(MARKS_PADDING);
        }
        table.add(tableSelector1);
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

    @Override
    public Table getTable() {
        return table;
    }
}
