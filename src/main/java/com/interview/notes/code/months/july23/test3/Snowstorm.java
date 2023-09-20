package com.interview.notes.code.months.july23.test3;

public class Snowstorm extends Storm {
    private double amountOfSnow;

    public Snowstorm(double eyeRadius, double eyePositionX, double eyePositionY, double amountOfSnow) {
        super(eyeRadius, eyePositionX, eyePositionY);
        this.amountOfSnow = amountOfSnow;
    }

    public double getAmountOfSnow() {
        return amountOfSnow;
    }
}
