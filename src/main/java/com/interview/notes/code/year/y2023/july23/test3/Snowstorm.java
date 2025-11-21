package com.interview.notes.code.year.y2023.july23.test3;

public class Snowstorm extends Storm {
    private final double amountOfSnow;

    public Snowstorm(double eyeRadius, double eyePositionX, double eyePositionY, double amountOfSnow) {
        super(eyeRadius, eyePositionX, eyePositionY);
        this.amountOfSnow = amountOfSnow;
    }

    public double getAmountOfSnow() {
        return amountOfSnow;
    }
}
