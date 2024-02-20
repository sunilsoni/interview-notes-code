package com.interview.notes.code.months.jan24.test11;

public abstract class Food {
    protected double proteins;
    protected double fats;
    protected double carbs;
    protected double tastyScore;

    public Food(double proteins, double fats, double carbs) {
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    public abstract void getMacroNutrients();

    public double getTaste() {
        return this.tastyScore;
    }
}