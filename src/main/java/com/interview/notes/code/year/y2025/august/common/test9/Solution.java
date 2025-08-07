package com.interview.notes.code.year.y2025.august.common.test9;

import java.util.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

// === Locked stub code ===
abstract class Cuisine {
    public abstract Cuisine serveFood(String dish);
}

class UnservableCuisineRequestException extends Exception {
    public UnservableCuisineRequestException(String message) {
        super(message);
    }
}
// =========================

// Concrete cuisines
class Chinese extends Cuisine {
    private final String dish;
    public Chinese() { this.dish = null; }
    private Chinese(String dish) { this.dish = dish; }

    @Override
    public Cuisine serveFood(String dish) {
        return new Chinese(dish);
    }

    public String getDish() {
        return dish;
    }
}

class Italian extends Cuisine {
    private final String dish;
    public Italian() { this.dish = null; }
    private Italian(String dish) { this.dish = dish; }

    @Override
    public Cuisine serveFood(String dish) {
        return new Italian(dish);
    }

    public String getDish() {
        return dish;
    }
}

class Japanese extends Cuisine {
    private final String dish;
    public Japanese() { this.dish = null; }
    private Japanese(String dish) { this.dish = dish; }

    @Override
    public Cuisine serveFood(String dish) {
        return new Japanese(dish);
    }

    public String getDish() {
        return dish;
    }
}

class Mexican extends Cuisine {
    private final String dish;
    public Mexican() { this.dish = null; }
    private Mexican(String dish) { this.dish = dish; }

    @Override
    public Cuisine serveFood(String dish) {
        return new Mexican(dish);
    }

    public String getDish() {
        return dish;
    }
}

// Singleton FoodFactory
class FoodFactory {
    private static final FoodFactory INSTANCE = new FoodFactory();
    private final Map<String, Cuisine> registry = new HashMap<>();

    private FoodFactory() { }

    public static FoodFactory getFactory() {
        return INSTANCE;
    }

    public void registerCuisine(String cuisineKey, Cuisine cuisine) {
        registry.put(cuisineKey, cuisine);
    }

    public Cuisine serveCuisine(String cuisineKey, String dish)
            throws UnservableCuisineRequestException {
        Cuisine prototype = registry.get(cuisineKey);
        if (prototype == null) {
            throw new UnservableCuisineRequestException(
                "Unservable cuisine " + cuisineKey + " for dish " + dish
            );
        }
        return prototype.serveFood(dish);
    }
}

// Your Solution class with static registration + main
public class Solution {
    private static final Scanner INPUT_READER = new Scanner(System.in);
    private static final FoodFactory FOOD_FACTORY = FoodFactory.getFactory();

    static {
        FOOD_FACTORY.registerCuisine("Chinese",  new Chinese());
        FOOD_FACTORY.registerCuisine("Italian",  new Italian());
        FOOD_FACTORY.registerCuisine("Japanese", new Japanese());
        FOOD_FACTORY.registerCuisine("Mexican",  new Mexican());
    }

    public static void main(String[] args) {
        int totalNumberOfOrders = Integer.parseInt(INPUT_READER.nextLine());
        while (totalNumberOfOrders-- > 0) {
            String[] food = INPUT_READER.nextLine().split(" ");
            String cuisine = food[0];
            String dish    = food[1];

            try {
                Cuisine served = FOOD_FACTORY.serveCuisine(cuisine, dish);
                switch (cuisine) {
                    case "Chinese":
                        Chinese c = (Chinese) served;
                        System.out.println("Serving " + c.getDish() + "(Chinese)");
                        break;
                    case "Italian":
                        Italian i = (Italian) served;
                        System.out.println("Serving " + i.getDish() + "(Italian)");
                        break;
                    case "Japanese":
                        Japanese j = (Japanese) served;
                        System.out.println("Serving " + j.getDish() + "(Japanese)");
                        break;
                    case "Mexican":
                        Mexican m = (Mexican) served;
                        System.out.println("Serving " + m.getDish() + "(Mexican)");
                        break;
                    default:
                        // no action
                }
            } catch (UnservableCuisineRequestException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}