package com.mj.cab10.model;

public class CarType {
    private boolean isChosen;

    public CarType() {
        isChosen = false;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }
}
