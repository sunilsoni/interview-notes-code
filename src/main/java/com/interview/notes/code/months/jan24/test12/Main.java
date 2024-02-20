package com.interview.notes.code.months.jan24.test12;

import java.util.Scanner;

abstract class Food {
    protected double proteins;
    protected double fats;
    protected double carbs;
    protected int tastyScore;

    public Food(double proteins, double fats, double carbs) {
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    public abstract String getType();

    public abstract void getMacroNutrients();

    public int getTastyScore() {
        return tastyScore;
    }
}

class Egg extends Food {
    private static final String TYPE = "non-vegetarian";
    private static final int TASTY_SCORE = 7;

    public Egg(double proteins, double fats, double carbs) {
        super(proteins, fats, carbs);
        this.tastyScore = TASTY_SCORE;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void getMacroNutrients() {
        System.out.printf("An egg has %.1f gms of protein, %.1f gms of fats and %.1f gms of carbohydrates.%n", proteins, fats, carbs);
    }
}

class Bread extends Food {
    private static final String TYPE = "vegetarian";
    private static final int TASTY_SCORE = 8;

    public Bread(double proteins, double fats, double carbs) {
        super(proteins, fats, carbs);
        this.tastyScore = TASTY_SCORE;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void getMacroNutrients() {
        System.out.printf("A slice of bread has %.1f gms of protein, %.1f gms of fats and %.1f gms of carbohydrates.%n", proteins, fats, carbs);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline after integer

        for (int i = 0; i < n; i++) {
            String foodName = scanner.nextLine();

            Food food;
            if (foodName.equals("Bread")) {
                double breadProteins = scanner.nextDouble();
                double breadFats = scanner.nextDouble();
                double breadCarbs = scanner.nextDouble();
                scanner.nextLine(); // Consume newline after values
                food = new Bread(breadProteins, breadFats, breadCarbs);
            } else if (foodName.equals("Egg")) {
                double eggProteins = scanner.nextDouble();
                double eggFats = scanner.nextDouble();
                double eggCarbs = scanner.nextDouble();
                scanner.nextLine(); // Consume newline after values
                food = new Egg(eggProteins, eggFats, eggCarbs);
            } else {
                System.out.println("Invalid food name.");
                continue;
            }

            String nextAction = scanner.nextLine();
            while (!nextAction.equals("quit")) {
                switch (nextAction) {
                    case "getType":
                        System.out.println(food.getType());
                        break;
                    case "getMacros":
                        food.getMacroNutrients();
                        break;
                    case "getTaste":
                        System.out.println("Taste: " + food.getTastyScore());
                        break;
                    default:
                        System.out.println("Invalid action.");
                }
                nextAction = scanner.nextLine();
            }
        }
    }
}
