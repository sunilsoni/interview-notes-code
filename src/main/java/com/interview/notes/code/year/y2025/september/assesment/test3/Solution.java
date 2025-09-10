package com.interview.notes.code.year.y2025.september.assesment.test3;

import java.util.*;
import java.util.stream.IntStream;

abstract class Cuisine {
    abstract String serve(String dish);
}

class Italian extends Cuisine {
    @Override
    String serve(String dish) {
        return "Serving " + dish + "(Italian)";
    }
}

class Chinese extends Cuisine {
    @Override
    String serve(String dish) {
        return "Serving " + dish + "(Chinese)";
    }
}

class Japanese extends Cuisine {
    @Override
    String serve(String dish) {
        return "Serving " + dish + "(Japanese)";
    }
}

class Mexican extends Cuisine {
    @Override
    String serve(String dish) {
        return "Serving " + dish + "(Mexican)";
    }
}

class UnservableCuisineRequestException extends Exception {
    public UnservableCuisineRequestException(String cuisineKey, String dish) {
        super("Unservable cuisine " + cuisineKey + " for dish " + dish);
    }
}

class FoodFactory {
    private static FoodFactory instance;
    private final Map<String, Cuisine> cuisineMap = new HashMap<>();

    private FoodFactory() {
    }

    public static FoodFactory getFactory() {
        if (instance == null) {
            instance = new FoodFactory();
        }
        return instance;
    }

    public void registerCuisine(String cuisineKey, Cuisine cuisine) {
        cuisineMap.put(cuisineKey, cuisine);
    }

    public Cuisine serveCuisine(String cuisineKey, String dish) throws UnservableCuisineRequestException {
        if (!cuisineMap.containsKey(cuisineKey)) {
            throw new UnservableCuisineRequestException(cuisineKey, dish);
        }
        return cuisineMap.get(cuisineKey);
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalOrders = Integer.parseInt(sc.nextLine());
        FoodFactory factory = FoodFactory.getFactory();
        factory.registerCuisine("Italian", new Italian());
        factory.registerCuisine("Chinese", new Chinese());
        factory.registerCuisine("Japanese", new Japanese());
        factory.registerCuisine("Mexican", new Mexican());

        List<String> results = new ArrayList<>();

        IntStream.range(0, totalOrders).forEach(i -> {
            String[] input = sc.nextLine().split(" ");
            String cuisine = input[0];
            String dish = input[1];
            try {
                Cuisine c = factory.serveCuisine(cuisine, dish);
                results.add(c.serve(dish));
            } catch (UnservableCuisineRequestException e) {
                results.add(e.getMessage());
            }
        });

        List<String> expected = Arrays.asList(
                "Serving Lasagne(Italian)",
                "Serving Kamameshi(Japanese)",
                "Unservable cuisine Polish for dish Marjoram"
        );

        IntStream.range(0, results.size()).forEach(i -> {
            String status = results.get(i).equals(expected.get(i)) ? "PASS" : "FAIL";
            System.out.println("Input: " + results.get(i) + " | Expected: " + expected.get(i) + " | " + status);
        });

        int largeN = 100;
        List<String> largeInputs = new ArrayList<>();
        IntStream.range(0, largeN).forEach(i -> largeInputs.add("Italian Dish" + i));
        boolean largePass = largeInputs.stream().allMatch(s -> {
            String[] arr = s.split(" ");
            try {
                Cuisine c = factory.serveCuisine(arr[0], arr[1]);
                return c.serve(arr[1]).startsWith("Serving Dish");
            } catch (Exception e) {
                return false;
            }
        });
        System.out.println("Large Input Test: " + (largePass ? "PASS" : "FAIL"));
    }
}