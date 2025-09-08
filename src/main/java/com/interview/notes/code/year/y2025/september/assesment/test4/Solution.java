package com.interview.notes.code.year.y2025.september.assesment.test4;

import java.util.*;

abstract class Cuisine {
    public abstract String getDish();
}

class Chinese extends Cuisine {
    private String dish;
    Chinese(String dish) { this.dish = dish; }
    public String getDish() { return dish; }
}

class Italian extends Cuisine {
    private String dish;
    Italian(String dish) { this.dish = dish; }
    public String getDish() { return dish; }
}

class Japanese extends Cuisine {
    private String dish;
    Japanese(String dish) { this.dish = dish; }
    public String getDish() { return dish; }
}

class Mexican extends Cuisine {
    private String dish;
    Mexican(String dish) { this.dish = dish; }
    public String getDish() { return dish; }
}

class UnservableCuisineRequestException extends Exception {
    public UnservableCuisineRequestException(String message) {
        super(message);
    }
}

class FoodFactory {
    private static FoodFactory instance;
    private Map<String, Class<? extends Cuisine>> cuisineMap = new HashMap<>();

    private FoodFactory() {}

    public static FoodFactory getFactory() {
        if (instance == null) instance = new FoodFactory();
        return instance;
    }

    public void registerCuisine(String cuisineKey, Cuisine cuisine) {
        cuisineMap.put(cuisineKey, cuisine.getClass());
    }

    public Cuisine serveCuisine(String cuisineKey, String dish) throws UnservableCuisineRequestException {
        if (!cuisineMap.containsKey(cuisineKey)) {
            throw new UnservableCuisineRequestException("Unservable cuisine " + cuisineKey + " for dish " + dish);
        }
        try {
            return cuisineMap.get(cuisineKey).getConstructor(String.class).newInstance(dish);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public class Solution {
    private static final Scanner INPUT_READER = new Scanner(System.in);
    private static final FoodFactory FOOD_FACTORY = FoodFactory.getFactory();

    static {
        FOOD_FACTORY.registerCuisine("Chinese", new Chinese(""));
        FOOD_FACTORY.registerCuisine("Italian", new Italian(""));
        FOOD_FACTORY.registerCuisine("Japanese", new Japanese(""));
        FOOD_FACTORY.registerCuisine("Mexican", new Mexican(""));
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
                switch (cuisine) {
                    case "Chinese":
                        Chinese c = (Chinese) servedFood;
                        results.add("Serving " + c.getDish() + "(Chinese)");
                        break;
                    case "Italian":
                        Italian i = (Italian) servedFood;
                        results.add("Serving " + i.getDish() + "(Italian)");
                        break;
                    case "Japanese":
                        Japanese j = (Japanese) servedFood;
                        results.add("Serving " + j.getDish() + "(Japanese)");
                        break;
                    case "Mexican":
                        Mexican m = (Mexican) servedFood;
                        results.add("Serving " + m.getDish() + "(Mexican)");
                        break;
                }
            } catch (UnservableCuisineRequestException ex) {
                results.add(ex.getMessage());
            }
        }

        List<String> expected = Arrays.asList(
            "Serving Lasagne(Italian)",
            "Serving Kamameshi(Japanese)",
            "Unservable cuisine Polish for dish Marjoram"
        );

        for (int i = 0; i < results.size(); i++) {
            String status = (i < expected.size() && results.get(i).equals(expected.get(i))) ? "PASS" : "FAIL";
            System.out.println("Input: " + results.get(i) + " | Expected: " + (i < expected.size() ? expected.get(i) : "N/A") + " | " + status);
        }

        int largeN = 100;
        boolean largePass = true;
        for (int i = 0; i < largeN; i++) {
            try {
                Cuisine served = FOOD_FACTORY.serveCuisine("Italian", "Dish" + i);
                if (!("Dish" + i).equals(((Italian) served).getDish())) {
                    largePass = false;
                    break;
                }
            } catch (Exception e) {
                largePass = false;
                break;
            }
        }
        System.out.println("Large Input Test: " + (largePass ? "PASS" : "FAIL"));
    }
}