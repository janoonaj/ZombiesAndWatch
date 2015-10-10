package com.game.buildCityMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.AssetsFactory;

public class Menu implements Screen {
    private Stage stage;
    private Table table;
    private Skin skin = new Skin(Gdx.files.internal("skins.json"));

    public Menu(Stage stage) {
        this.stage = stage;
        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);



        ///////////
        // TABLES
        ///////////
        Table tableUp = new Table();
        Table tableDown = new Table();
        Table tableDownLeft = new Table().left();
        Table tableDownRight = new Table().right();
        Table tableButton1 = new Table();
        Table tableButton2 = new Table();
        Table tableButton3 = new Table();
        Table tableButton4 = new Table();
        tableDownLeft.add(tableButton1).padBottom(10);
        tableDownLeft.add(tableButton2).padBottom(10);

        Table tableAvailablePoints = new Table();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                Image mark = new Image(AssetsFactory.instance().getMarkEmpty());
                mark.setOrigin(mark.getWidth()/2, mark.getHeight()/2);
                mark.setRotation(90f);
                tableAvailablePoints.add(mark).padRight(10).padLeft(10);
            }
            if(i < 4) tableAvailablePoints.row();
        }
        tableDownLeft.add(tableAvailablePoints).padBottom(10);


        tableDownLeft.row();
        tableDownLeft.add(tableButton3);
        tableDownLeft.add(tableButton4);



        tableDownLeft.add(new ImageButton(new Image(AssetsFactory.instance().getButtonOK()).getDrawable()));



        tableDown.add(tableDownLeft);
        tableDown.add(tableDownRight);
        table.add(tableUp).padBottom(20);
        table.row();
        table.add(tableDown);


        ///////////
        // TABLE UP
        ///////////
        Label population = new Label("Population: 0", skin);
        Label maxPopulation = new Label("MaxPopulation: 0", skin);
        Label food = new Label("Food: 0", skin);
        Label militia = new Label("Militia: Lvl1", skin);
        Label defenses = new Label("Defenses: 100%", skin);
        tableUp.add(population).uniform();
        tableUp.add(food).uniform();
        tableUp.add(defenses).uniform();
        tableUp.row();
        tableUp.add(maxPopulation).uniform();
        tableUp.add(militia).uniform();






        ///////////
        // TABLE DOWN LEFT
        ///////////

        ImageButton bttnHouse = new ImageButton(new Image(AssetsFactory.instance().getButtonHouse()).getDrawable());
        ImageButton bttnHarvest = new ImageButton(new Image(AssetsFactory.instance().getButtonHarvest()).getDrawable());
        ImageButton bttnWall = new ImageButton(new Image(AssetsFactory.instance().getButtonWall()).getDrawable());
        ImageButton bttnMilitia = new ImageButton(new Image(AssetsFactory.instance().getButtonMilitia()).getDrawable());
        ImageButton bttnPlus1 = new ImageButton(new Image(AssetsFactory.instance().getButtonPlus()).getDrawable());
        ImageButton bttnPlus2 = new ImageButton(new Image(AssetsFactory.instance().getButtonPlus()).getDrawable());
        ImageButton bttnPlus3 = new ImageButton(new Image(AssetsFactory.instance().getButtonPlus()).getDrawable());
        ImageButton bttnPlus4 = new ImageButton(new Image(AssetsFactory.instance().getButtonPlus()).getDrawable());
        ImageButton bttnMinus1 = new ImageButton(new Image(AssetsFactory.instance().getButtonMinus()).getDrawable());
        ImageButton bttnMinus2 = new ImageButton(new Image(AssetsFactory.instance().getButtonMinus()).getDrawable());
        ImageButton bttnMinus3 = new ImageButton(new Image(AssetsFactory.instance().getButtonMinus()).getDrawable());
        ImageButton bttnMinus4 = new ImageButton(new Image(AssetsFactory.instance().getButtonMinus()).getDrawable());

        Table tableSelector1 = new Table();
        for(int i = 0; i < 10; i++) {
            tableSelector1.add(new Image(AssetsFactory.instance().getMarkEmpty())).padRight(3);
        }
        tableButton1.add(tableSelector1);
        tableButton1.row();
        tableButton1.add(bttnHouse);
        Table tablePluMin1 = new Table();
        tablePluMin1.add(bttnPlus1);tablePluMin1.row();tablePluMin1.add(bttnMinus1);
        tableButton1.add(tablePluMin1);

        Table tableSelector2 = new Table();
        for(int i = 0; i < 10; i++) {
            tableSelector2.add(new Image(AssetsFactory.instance().getMarkEmpty())).padRight(3);
        }
        tableButton2.add(tableSelector2);
        tableButton2.row();
        tableButton2.add(bttnHarvest);
        Table tablePluMin2 = new Table();
        tablePluMin2.add(bttnPlus2);tablePluMin2.row();tablePluMin2.add(bttnMinus2);
        tableButton2.add(tablePluMin2);

        Table tableSelector3 = new Table();
        for(int i = 0; i < 10; i++) {
            tableSelector3.add(new Image(AssetsFactory.instance().getMarkEmpty())).padRight(3);
        }
        tableButton3.add(tableSelector3);
        tableButton3.row();
        tableButton3.add(bttnWall);
        Table tablePluMin3 = new Table();
        tablePluMin3.add(bttnPlus3);tablePluMin3.row();tablePluMin3.add(bttnMinus3);
        tableButton3.add(tablePluMin3);

        Table tableSelector4 = new Table();
        for(int i = 0; i < 10; i++) {
            tableSelector4.add(new Image(AssetsFactory.instance().getMarkEmpty())).padRight(3);
        }
        tableButton4.add(tableSelector4);
        tableButton4.row();
        tableButton4.add(bttnMilitia);
        Table tablePluMin4 = new Table();
        tablePluMin4.add(bttnPlus4);tablePluMin4.row();tablePluMin4.add(bttnMinus4);
        tableButton4.add(tablePluMin4);




        ///////////
        // TABLE DOWN RIGHT
        ///////////
        /*Table tableAvailablePoints = new Table();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                Image mark = new Image(AssetsFactory.instance().getMarkEmpty());
                mark.setOrigin(mark.getWidth()/2, mark.getHeight()/2);
                mark.setRotation(90f);
                tableAvailablePoints.add(mark).padRight(10).padLeft(10);
            }
            if(i < 4) tableAvailablePoints.row();
        }

        ImageButton bttnOK = new ImageButton(new Image(AssetsFactory.instance().getButtonOK()).getDrawable());
        tableDownRight.add(tableAvailablePoints);
        tableDownRight.row();
        tableDownRight.add(bttnOK);*/


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
