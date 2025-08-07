package com.interview.notes.code.year.y2025.august.common.test10;

import java.util.*;
import java.util.stream.*;

// Exception thrown when a cuisine is not registered
class UnservableCuisineRequestException extends Exception {
    public UnservableCuisineRequestException(String message) {
        super(message);
    }
}

// Abstract cuisine class (stub)
abstract class Cuisine { }

// Concrete cuisine types
class ChineseCuisine extends Cuisine { }
class ItalianCuisine  extends Cuisine { }
class JapaneseCuisine extends Cuisine { }
class MexicanCuisine  extends Cuisine { }

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

    // Returns the serving message or throws if not registered
    public String serveCuisine(String cuisineKey, String dish)
            throws UnservableCuisineRequestException {
        if (!registry.containsKey(cuisineKey)) {
            throw new UnservableCuisineRequestException(
                "Unservable cuisine " + cuisineKey + " for dish " + dish
            );
        }
        return "Serving " + dish + "(" + cuisineKey + ")";
    }
}

// Simple data classes for testing
class Order {
    final String key, dish;
    Order(String key, String dish) { this.key = key; this.dish = dish; }
}
class TestCase {
    final List<Order> orders;
    final List<String> expected;
    TestCase(List<Order> orders, List<String> expected) {
        this.orders = orders; this.expected = expected;
    }
}

public class FoodFactoryTest {
    // Registers all known cuisines before serving
    private static List<String> processOrders(List<Order> orders) {
        FoodFactory factory = FoodFactory.getFactory();
        factory.registerCuisine("Chinese", new ChineseCuisine());
        factory.registerCuisine("Italian", new ItalianCuisine());
        factory.registerCuisine("Japanese", new JapaneseCuisine());
        factory.registerCuisine("Mexican", new MexicanCuisine());

        // Use Streams to map each order to its output string
        return orders.stream()
            .map(o -> {
                try {
                    return factory.serveCuisine(o.key, o.dish);
                } catch (UnservableCuisineRequestException e) {
                    return e.getMessage();
                }
            })
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
            // Sample test from prompt
            new TestCase(
                Arrays.asList(
                    new Order("Italian",  "Lasagne"),
                    new Order("Japanese", "Kamameshi"),
                    new Order("Polish",   "Marjoram")
                ),
                Arrays.asList(
                    "Serving Lasagne(Italian)",
                    "Serving Kamameshi(Japanese)",
                    "Unservable cuisine Polish for dish Marjoram"
                )
            )
            // you can add more TestCase(...) entries here
        );

        // Run each test
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            List<String> actual = processOrders(tc.orders);
            boolean pass = actual.equals(tc.expected);
            System.out.println("Test Case " + (i+1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Actual:   " + actual);
            }
        }

        // Large‐input performance check
        int N = 50_000;
        List<Order> large = IntStream.range(0, N)
            .mapToObj(i -> new Order("Italian", "Dish" + i))
            .collect(Collectors.toList());
        List<String> result = processOrders(large);
        boolean largeOk = result.size() == N
            && result.stream().allMatch(s -> s.startsWith("Serving Dish"));
        System.out.println("Large Test Case: " + (largeOk ? "PASS" : "FAIL"));
    }
}