package com.interview.notes.code.year.y2024.nov24.test3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ServiceChainDemo {

    // Helper method to simulate delay
    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Test methods
    public static void main(String[] args) {
        testBasicFlow();
        testErrorHandling();
        testTimeout();
        testLargeLoad();
    }

    private static void testBasicFlow() {
        System.out.println("\nTesting Basic Flow:");
        UserService userService = new UserService();
        try {
            long startTime = System.currentTimeMillis();

            Integer result = userService.getCombinedResult().get();

            long endTime = System.currentTimeMillis();
            System.out.println("Combined Result: " + result);
            System.out.println("Total Execution Time: " + (endTime - startTime) + "ms");
            System.out.println("Test Basic Flow: PASS");

        } catch (Exception e) {
            System.out.println("Test Basic Flow: FAIL");
            e.printStackTrace();
        }
    }

    private static void testErrorHandling() {
        System.out.println("\nTesting Error Handling:");
        UserService userService = new UserService() {
            @Override
            public CompletableFuture<Integer> getCombinedResult() {
                return CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("Simulated error");
                });
            }
        };

        try {
            userService.getCombinedResult()
                    .exceptionally(throwable -> {
                        System.out.println("Error handled: " + throwable.getMessage());
                        return -1;
                    })
                    .get();
            System.out.println("Test Error Handling: PASS");
        } catch (Exception e) {
            System.out.println("Test Error Handling: FAIL");
            e.printStackTrace();
        }
    }

    private static void testTimeout() {
        System.out.println("\nTesting Timeout:");
        UserService userService = new UserService();
        try {
            Integer result = userService.getCombinedResult()
                    .completeOnTimeout(-1, 5, TimeUnit.SECONDS)
                    .get();
            System.out.println("Result with timeout: " + result);
            System.out.println("Test Timeout: PASS");
        } catch (Exception e) {
            System.out.println("Test Timeout: FAIL");
            e.printStackTrace();
        }
    }

    private static void testLargeLoad() {
        System.out.println("\nTesting Large Load:");
        UserService userService = new UserService();
        try {
            CompletableFuture<?>[] futures = new CompletableFuture[10];
            for (int i = 0; i < 10; i++) {
                futures[i] = userService.getCombinedResult()
                        .thenAccept(result ->
                                System.out.println("Batch result: " + result));
            }

            CompletableFuture.allOf(futures).get();
            System.out.println("Test Large Load: PASS");
        } catch (Exception e) {
            System.out.println("Test Large Load: FAIL");
            e.printStackTrace();
        }
    }

    // Service classes to simulate different microservices
    static class ServiceA {
        public CompletableFuture<Integer> getData() {
            return CompletableFuture.supplyAsync(() -> {
                // Simulate service delay
                sleep(2);
                return 10;
            });
        }
    }

    static class ServiceB {
        public CompletableFuture<Integer> getData() {
            return CompletableFuture.supplyAsync(() -> {
                // Simulate service delay
                sleep(1);
                return 20;
            });
        }
    }

    static class UserService {
        private final ServiceA serviceA;
        private final ServiceB serviceB;

        public UserService() {
            this.serviceA = new ServiceA();
            this.serviceB = new ServiceB();
        }

        public CompletableFuture<Integer> getCombinedResult() {
            return CompletableFuture.supplyAsync(() -> {
                // Start both service calls in parallel
                CompletableFuture<Integer> futureA = serviceA.getData();
                CompletableFuture<Integer> futureB = serviceB.getData();

                // Combine results when both are complete
                return futureA
                        .thenCombine(futureB, (resultA, resultB) -> {
                            System.out.println("Service A result: " + resultA);
                            System.out.println("Service B result: " + resultB);
                            return resultA + resultB;
                        })
                        .join();
            });
        }

        // Alternative implementation using thenCombine directly
        public CompletableFuture<Integer> getCombinedResultAlternative() {
            return serviceA.getData()
                    .thenCombine(serviceB.getData(), (resultA, resultB) -> {
                        System.out.println("Service A result: " + resultA);
                        System.out.println("Service B result: " + resultB);
                        return resultA + resultB;
                    });
        }
    }
}