package com.interview.notes.code.year.y2024.oct24.test19;

import java.io.PrintStream;

public class FoodTester {
    public static void main(String[] args) {
        // Reset System.out to console
        PrintStream originalOut = System.out;

        System.out.println("Running Food Class Test Cases...\n");

        System.out.println("=== Test Case 1: Basic Functionality ===");
        // Test Bread
        Food bread = new Bread(4.0, 1.1, 13.8);
        System.out.println("\nTesting Bread:");
        System.out.println(bread.getType() + " is " + (bread.getType().equals("vegetarian") ? "PASS" : "FAIL"));
        System.out.println("Expected tastyScore=8, Actual=" + bread.getTaste() +
                " : " + (bread.getTaste() == 8 ? "PASS" : "FAIL"));
        bread.getMacroNutrients();

        // Test Egg
        Food egg = new Egg(6.3, 5.3, 0.6);
        System.out.println("\nTesting Egg:");
        System.out.println(egg.getType() + " is " + (egg.getType().equals("non-vegetarian") ? "PASS" : "FAIL"));
        System.out.println("Expected tastyScore=7, Actual=" + egg.getTaste() +
                " : " + (egg.getTaste() == 7 ? "PASS" : "FAIL"));
        egg.getMacroNutrients();

        System.out.println("\n=== Test Case 2: Edge Cases ===");
        // Zero values
        Food zeroBread = new Bread(0.0, 0.0, 0.0);
        System.out.println("\nTesting Zero Values Bread:");
        zeroBread.getMacroNutrients();

        // Large values
        Food largeEgg = new Egg(999.9, 999.9, 999.9);
        System.out.println("\nTesting Large Values Egg:");
        largeEgg.getMacroNutrients();

        System.out.println("\n=== Test Case 3: Performance Test ===");
        long startTime = System.currentTimeMillis();
        int testCount = 1000;

        for (int i = 0; i < testCount; i++) {
            if (i % 2 == 0) {
                Food testBread = new Bread(i * 0.1, i * 0.2, i * 0.3);
                testBread.getType();
                testBread.getTaste();
            } else {
                Food testEgg = new Egg(i * 0.1, i * 0.2, i * 0.3);
                testEgg.getType();
                testEgg.getTaste();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Processed " + testCount + " items in " + (endTime - startTime) + "ms");
    }
}

abstract class Food {
    protected double proteins;
    protected double fats;
    protected double carbs;
    protected int tastyScore;
    protected String type;

    public Food(double proteins, double fats, double carbs) {
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    abstract void getMacroNutrients();

    public String getType() {
        return type;
    }

    public double getTaste() {
        return tastyScore;
    }
}

class Egg extends Food {
    public Egg(double proteins, double fats, double carbs) {
        super(proteins, fats, carbs);
        this.tastyScore = 7;
        this.type = "non-vegetarian";
    }

    @Override
    void getMacroNutrients() {
        System.out.printf("An egg has %.1f gms of protein, %.1f gms of fats and %.1f gms of carbohydrates.\n",
                proteins, fats, carbs);
    }
}

class Bread extends Food {
    public Bread(double proteins, double fats, double carbs) {
        super(proteins, fats, carbs);
        this.tastyScore = 8;
        this.type = "vegetarian";
    }

    @Override
    void getMacroNutrients() {
        System.out.printf("A slice of bread has %.1f gms of protein, %.1f gms of fats and %.1f gms of carbohydrates.\n",
                proteins, fats, carbs);
    }
}