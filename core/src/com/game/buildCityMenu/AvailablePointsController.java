package com.game.buildCityMenu;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.AssetsFactory;

public class AvailablePointsController implements Panel {
    private final int PADDING_LATERAL = 10;
    private final int NUM_ROWS = 5;
    private final int NUM_COLUMNS = 5;
    private Table table = new Table();
    private int numMarked;

    public AvailablePointsController(int numMarked) {
        this.numMarked = numMarked;
        prepareTable();
    }

    @Override
    public Table getTable() {
        return table;
    }

    public void addMark() {
        numMarked++;
        prepareTable();
    }

    public void removeMark() {
        numMarked--;
        prepareTable();
    }

    public boolean canAddMark() {
        return numMarked < (NUM_ROWS * NUM_COLUMNS);
    }

    public boolean canRemoveMark() {
        return numMarked > 0;
    }

    private void prepareTable() {
        table.reset();
        int contMarks = 0;
        int marksTotal = NUM_COLUMNS * NUM_ROWS;
        for(int rowIndex = 0; rowIndex < NUM_ROWS; rowIndex++) {
            for(int columnIndex = 0; columnIndex < NUM_COLUMNS; columnIndex++) {
                Image mark;
                if(numMarked >= marksTotal - contMarks)
                    mark = getMarked();
                else
                    mark = getUnmarked();
                table.add(mark).padRight(PADDING_LATERAL).padLeft(PADDING_LATERAL);
                contMarks++;
            }
            if(rowIndex < NUM_ROWS - 1) table.row();
        }
    }

    private Image getMarked() {
        return rotate90(new Image(AssetsFactory.instance().getMarkFilled()));
    }

    private Image getUnmarked() {
        return rotate90(new Image(AssetsFactory.instance().getMarkEmpty()));
    }

    private Image rotate90(Image image) {
        image.setOrigin(image.getWidth()/2, image.getHeight()/2);
        image.setRotation(90);
        return image;
    }
}
