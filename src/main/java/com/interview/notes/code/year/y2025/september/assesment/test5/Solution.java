package com.interview.notes.code.year.y2025.september.assesment.test5;

import java.util.*;

/**
 * Problem overview:
 * - Implement a simple Food Factory that can serve dishes for registered cuisines.
 * - Input: number of orders followed by lines of "<Cuisine> <Dish>".
 * - Output: For each order, either a "Serving <Dish>(<Cuisine>)" line or an error if cuisine is not registered.
 * <p>
 * Design notes:
 * - Uses a simple factory (singleton) to register cuisine prototypes and serve dish-specific instances.
 * - Each Cuisine subclass returns a new instance with the requested dish via serveFood.
 * - Unregistered cuisine results in UnservableCuisineRequestException with a clear message.
 */
abstract class Cuisine {
    /**
     * Return a cuisine instance carrying the requested dish.
     */
    public abstract Cuisine serveFood(String dish);

    /**
     * Return the name of the dish carried by this cuisine instance.
     */
    public abstract String getDish();
}

/**
 * Chinese cuisine implementation. Creates a new instance carrying the requested dish.
 */
class Chinese extends Cuisine {
    private String dish;

    Chinese() {
    }

    Chinese(String dish) {
        this.dish = dish;
    }

    public String getDish() {
        return dish;
    }

    @Override
    public Cuisine serveFood(String dish) {
        return new Chinese(dish);
    }
}

/**
 * Italian cuisine implementation.
 */
class Italian extends Cuisine {
    private String dish;

    Italian() {
    }

    Italian(String dish) {
        this.dish = dish;
    }

    public String getDish() {
        return dish;
    }

    @Override
    public Cuisine serveFood(String dish) {
        return new Italian(dish);
    }
}

/**
 * Japanese cuisine implementation.
 */
class Japanese extends Cuisine {
    private String dish;

    Japanese() {
    }

    Japanese(String dish) {
        this.dish = dish;
    }

    public String getDish() {
        return dish;
    }

    @Override
    public Cuisine serveFood(String dish) {
        return new Japanese(dish);
    }
}

/**
 * Mexican cuisine implementation.
 */
class Mexican extends Cuisine {
    private String dish;

    Mexican() {
    }

    Mexican(String dish) {
        this.dish = dish;
    }

    public String getDish() {
        return dish;
    }

    @Override
    public Cuisine serveFood(String dish) {
        return new Mexican(dish);
    }
}

/**
 * Exception thrown when a cuisine key is not registered in the factory.
 */
class UnservableCuisineRequestException extends Exception {
    public UnservableCuisineRequestException(String message) {
        super(message);
    }
}

/**
 * FoodFactory is a simple singleton registry mapping cuisine keys to Cuisine prototypes.
 * registerCuisine adds/overrides a cuisine prototype. serveCuisine returns a dish-specific instance.
 */
class FoodFactory {
    private static FoodFactory instance;
    private final Map<String, Cuisine> cuisineMap = new HashMap<>();

    private FoodFactory() {
    }

    /**
     * Get the singleton factory instance.
     */
    public static FoodFactory getFactory() {
        if (instance == null) instance = new FoodFactory();
        return instance;
    }

    /**
     * Register a cuisine prototype by key (e.g., "Chinese").
     */
    public void registerCuisine(String cuisineKey, Cuisine cuisine) {
        cuisineMap.put(cuisineKey, cuisine);
    }

    /**
     * Serve a dish for a given cuisine key.
     *
     * @throws UnservableCuisineRequestException if cuisineKey is not registered
     */
    public Cuisine serveCuisine(String cuisineKey, String dish) throws UnservableCuisineRequestException {
        if (!cuisineMap.containsKey(cuisineKey)) {
            throw new UnservableCuisineRequestException("Unservable cuisine " + cuisineKey + " for dish " + dish);
        }
        return cuisineMap.get(cuisineKey).serveFood(dish);
    }
}

/**
 * Driver class: reads input, uses FoodFactory, and prints serving lines or error messages.
 */
public class Solution {
    private static final Scanner INPUT_READER = new Scanner(System.in);
    private static final FoodFactory FOOD_FACTORY = FoodFactory.getFactory();

    static {
        // Register supported cuisines at startup.
        FOOD_FACTORY.registerCuisine("Chinese", new Chinese());
        FOOD_FACTORY.registerCuisine("Italian", new Italian());
        FOOD_FACTORY.registerCuisine("Japanese", new Japanese());
        FOOD_FACTORY.registerCuisine("Mexican", new Mexican());
    }

    public static void main(String[] args) {
        int totalNumberOfOrders = Integer.parseInt(INPUT_READER.nextLine());
        List<String> results = new ArrayList<>();

        while (totalNumberOfOrders-- > 0) {
            String[] food = INPUT_READER.nextLine().split(" ");
            String cuisine = food[0];
            String dish = food[1];

            try {
                Cuisine servedFood = FOOD_FACTORY.serveCuisine(cuisine, dish);

                // Print output in required format based on cuisine type
                switch (cuisine) {
                    case "Chinese":
                        results.add("Serving " + servedFood.getDish() + "(Chinese)");
                        break;
                    case "Italian":
                        results.add("Serving " + servedFood.getDish() + "(Italian)");
                        break;
                    case "Japanese":
                        results.add("Serving " + servedFood.getDish() + "(Japanese)");
                        break;
                    case "Mexican":
                        results.add("Serving " + servedFood.getDish() + "(Mexican)");
                        break;
                }
            } catch (UnservableCuisineRequestException ex) {
                // Forward exception message as per requirement
                results.add(ex.getMessage());
            }
        }

        results.forEach(System.out::println);
    }
}