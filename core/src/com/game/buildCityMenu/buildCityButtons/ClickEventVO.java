package com.game.buildCityMenu.buildCityButtons;

public class ClickEventVO {
    private final int changeOnCityClicked;
    private final int buttonClicked;

    public ClickEventVO(int cityClicked, int buttonClicked) {
        this.changeOnCityClicked = cityClicked;
        this.buttonClicked = buttonClicked;
    }

    public int getChangeOnCityClicked() {
        return changeOnCityClicked;
    }

    public int getButtonClicked() {
        return buttonClicked;
    }
}
