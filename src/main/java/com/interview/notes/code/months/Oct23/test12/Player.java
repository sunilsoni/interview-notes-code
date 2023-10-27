package com.interview.notes.code.months.Oct23.test12;

public class Player {

    private String name;
    private int currentPosition;

    public Player() {
        name = "Player";
        currentPosition = 0;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}