package com.interview.notes.code.july23.test3;

public class Rainstorm extends Storm {
    public Rainstorm(double eyeRadius, double eyePositionX, double eyePositionY) {
        super(eyeRadius, eyePositionX, eyePositionY);
    }

    public double amountOfRain() {
        return eyeRadius * 20;
    }
}
