package com.interview.notes.code.months.year2023.july23.test3;

/**
 * Question Java:
 * <p>
 * Create a new package-private class, named
 * Storm, and move all code duplicated in the classes
 * <p>
 * Rainstorm and Snowstorm to Storm:
 * <p>
 * •  The Rainstorm and Snowstorm classes should inherit from the Storm class.
 * <p>
 * •  The Storm class should contain all duplicated code (identical fields, methods, and constructor) from the Rainstorm and Snowstorm classes.
 * <p>
 * •  The functionality of the Rainstorm and Snowstorm classes should stay the same.
 */
class Storm {
    protected double eyeRadius;
    protected double eyePositionX;
    protected double eyePositionY;

    public Storm(double eyeRadius, double eyePositionX, double eyePositionY) {
        this.eyeRadius = eyeRadius;
        this.eyePositionX = eyePositionX;
        this.eyePositionY = eyePositionY;
    }

    public boolean isInEyeOfTheStorm(double positionX, double positionY) {
        double distance = Math.sqrt(Math.pow(positionX - eyePositionX, 2) + Math.pow(positionY - eyePositionY, 2));
        return distance < eyeRadius;
    }

    public double getEyeRadius() {
        return eyeRadius;
    }

    public double getEyePositionX() {
        return eyePositionX;
    }

    public double getEyePositionY() {
        return eyePositionY;
    }
}
