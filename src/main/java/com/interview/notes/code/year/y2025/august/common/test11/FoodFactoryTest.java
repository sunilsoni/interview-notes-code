package com.interview.notes.code.year.y2025.august.common.test11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// === Locked stub code ===
abstract class Cuisine {
    // Called by the factory to serve a dish; returns a Cuisine
    public abstract Cuisine serveFood(String dish);
}

class UnservableCuisineRequestException extends Exception {
    public UnservableCuisineRequestException(String message) {
        super(message);
    }
}
// =========================

// Our singleton FoodFactory
class FoodFactory {
    private static final FoodFactory INSTANCE = new FoodFactory();
    private final Map<String, Cuisine> registry = new HashMap<>();

    private FoodFactory() {
    }

    public static FoodFactory getFactory() {
        return INSTANCE;
    }

    public void registerCuisine(String cuisineKey, Cuisine cuisine) {
        registry.put(cuisineKey, cuisine);
    }

    public Cuisine serveCuisine(String cuisineKey, String dish)
            throws UnservableCuisineRequestException {
        Cuisine c = registry.get(cuisineKey);
        if (c == null) {
            throw new UnservableCuisineRequestException(
                    "Unservable cuisine " + cuisineKey + " for dish " + dish
            );
        }
        // delegate to the cuisine’s serveFood, which returns
        // an object whose toString() prints the Serving message
        return c.serveFood(dish);
    }
}

// Four built-in cuisines
class ChineseCuisine extends Cuisine {
    @Override
    public Cuisine serveFood(String dish) {
        // return an anonymous Cuisine whose toString() prints our message
        return new Cuisine() {
            @Override
            public Cuisine serveFood(String d) {
                return this;
            }

            @Override
            public String toString() {
                return "Serving " + dish + "(Chinese)";
            }
        };
    }
}

class ItalianCuisine extends Cuisine {
    @Override
    public Cuisine serveFood(String dish) {
        return new Cuisine() {
            @Override
            public Cuisine serveFood(String d) {
                return this;
            }

            @Override
            public String toString() {
                return "Serving " + dish + "(Italian)";
            }
        };
    }
}

class JapaneseCuisine extends Cuisine {
    @Override
    public Cuisine serveFood(String dish) {
        return new Cuisine() {
            @Override
            public Cuisine serveFood(String d) {
                return this;
            }

            @Override
            public String toString() {
                return "Serving " + dish + "(Japanese)";
            }
        };
    }
}

class MexicanCuisine extends Cuisine {
    @Override
    public Cuisine serveFood(String dish) {
        return new Cuisine() {
            @Override
            public Cuisine serveFood(String d) {
                return this;
            }

            @Override
            public String toString() {
                return "Serving " + dish + "(Mexican)";
            }
        };
    }
}

// Simple structs for our tests
class Order {
    final String key, dish;

    Order(String key, String dish) {
        this.key = key;
        this.dish = dish;
    }
}

class TestCase {
    final List<Order> orders;
    final List<String> expected;

    TestCase(List<Order> orders, List<String> expected) {
        this.orders = orders;
        this.expected = expected;
    }
}

public class FoodFactoryTest {
    // Helper to register all built-ins once
    private static void registerBuiltIns(FoodFactory f) {
        f.registerCuisine("Chinese", new ChineseCuisine());
        f.registerCuisine("Italian", new ItalianCuisine());
        f.registerCuisine("Japanese", new JapaneseCuisine());
        f.registerCuisine("Mexican", new MexicanCuisine());
    }

    // Run through the orders and collect their messages
    private static List<String> processOrders(List<Order> orders) {
        FoodFactory factory = FoodFactory.getFactory();
        registerBuiltIns(factory);

        return orders.stream().map(o -> {
            try {
                Cuisine served = factory.serveCuisine(o.key, o.dish);
                return served.toString();
            } catch (UnservableCuisineRequestException e) {
                return e.getMessage();
            }
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
                // --- Sample from prompt ---
                new TestCase(
                        Arrays.asList(
                                new Order("Italian", "Lasagne"),
                                new Order("Japanese", "Kamameshi"),
                                new Order("Polish", "Marjoram")
                        ),
                        Arrays.asList(
                                "Serving Lasagne(Italian)",
                                "Serving Kamameshi(Japanese)",
                                "Unservable cuisine Polish for dish Marjoram"
                        )
                )
                // ← add more TestCase(...) here as needed
        );

        // Run each named test
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            List<String> actual = processOrders(tc.orders);
            boolean pass = actual.equals(tc.expected);
            System.out.println("Test Case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Actual:   " + actual);
            }
        }

        // --- Large-input performance check ---
        int N = 50_000;
        List<Order> large = IntStream.range(0, N)
                .mapToObj(i -> new Order("Italian", "Dish" + i))
                .collect(Collectors.toList());
        List<String> largeResult = processOrders(large);
        boolean largeOk = largeResult.size() == N
                && largeResult.stream().allMatch(s -> s.startsWith("Serving Dish"));
        System.out.println("Large Test Case: " + (largeOk ? "PASS" : "FAIL"));
    }
}