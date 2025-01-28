package com.interview.notes.code.year.y2025.jan24.amazon.test10;

import java.time.Instant;

/*
Design Client Side rate Limiter

 How would you design and implement a client-side rate limiter for security cameras to ensure that command and control traffic does not interfere with video streaming, while maintaining fair use of limited device resources and bandwidth?
 */
public interface RateLimiter {
    void setRequestLimit(int perSecondLimit);

    boolean isRequestAllowed();
}

class FixedWindowRateLimiter implements RateLimiter {
    private int requestLimit;
    private int requestCount;
    private long windowStart;

    public FixedWindowRateLimiter(int perSecondLimit) {
        setRequestLimit(perSecondLimit);
        this.requestCount = 0;
        this.windowStart = Instant.now().getEpochSecond();
    }

    @Override
    public void setRequestLimit(int perSecondLimit) {
        if (perSecondLimit <= 0) {
            throw new IllegalArgumentException("Request limit must be positive.");
        }
        this.requestLimit = perSecondLimit;
    }

    @Override
    public synchronized boolean isRequestAllowed() {
        long currentTime = Instant.now().getEpochSecond();
        if (currentTime > windowStart) {
            windowStart = currentTime;
            requestCount = 0;
        }
        if (requestCount < requestLimit) {
            requestCount++;
            return true;
        }
        return false;
    }
}

class RateLimiterTest {
    public static void main(String[] args) {
        System.out.println("Starting RateLimiter Tests...");

        // Test Case 1: Basic Functionality
        try {
            RateLimiter limiter = new FixedWindowRateLimiter(5); // 5 requests per second
            boolean passed = true;
            for (int i = 0; i < 5; i++) {
                if (!limiter.isRequestAllowed()) {
                    passed = false;
                    break;
                }
            }
            if (passed && !limiter.isRequestAllowed()) {
                System.out.println("Test Case 1 Passed: Basic Functionality");
            } else {
                System.out.println("Test Case 1 Failed: Basic Functionality");
            }
        } catch (Exception e) {
            System.out.println("Test Case 1 Failed with Exception: " + e.getMessage());
        }

        // Test Case 2: Burst Requests
        try {
            RateLimiter limiter = new FixedWindowRateLimiter(10); // 10 requests per second
            boolean passed = true;
            for (int i = 0; i < 10; i++) {
                if (!limiter.isRequestAllowed()) {
                    passed = false;
                    break;
                }
            }
            // 11th request should fail
            if (passed && !limiter.isRequestAllowed()) {
                System.out.println("Test Case 2 Passed: Burst Requests");
            } else {
                System.out.println("Test Case 2 Failed: Burst Requests");
            }
        } catch (Exception e) {
            System.out.println("Test Case 2 Failed with Exception: " + e.getMessage());
        }

        // Test Case 3: Refill After Window
        try {
            RateLimiter limiter = new FixedWindowRateLimiter(2); // 2 requests per second
            limiter.isRequestAllowed(); // 1
            limiter.isRequestAllowed(); // 2
            // Third request should fail
            if (!limiter.isRequestAllowed()) {
                // Wait for 1 second to reset the window
                Thread.sleep(1000);
                // Now, request should be allowed
                if (limiter.isRequestAllowed()) {
                    System.out.println("Test Case 3 Passed: Refill After Window");
                } else {
                    System.out.println("Test Case 3 Failed: Refill After Window");
                }
            } else {
                System.out.println("Test Case 3 Failed: Refill After Window");
            }
        } catch (Exception e) {
            System.out.println("Test Case 3 Failed with Exception: " + e.getMessage());
        }

        // Test Case 4: Large Number of Requests
        try {
            RateLimiter limiter = new FixedWindowRateLimiter(1000); // 1000 requests per second
            boolean passed = true;
            for (int i = 0; i < 1000; i++) {
                if (!limiter.isRequestAllowed()) {
                    passed = false;
                    break;
                }
            }
            if (passed && !limiter.isRequestAllowed()) {
                System.out.println("Test Case 4 Passed: Large Number of Requests");
            } else {
                System.out.println("Test Case 4 Failed: Large Number of Requests");
            }
        } catch (Exception e) {
            System.out.println("Test Case 4 Failed with Exception: " + e.getMessage());
        }

        // Test Case 5: High Burst with Refill
        try {
            RateLimiter limiter = new FixedWindowRateLimiter(5); // 5 requests per second
            boolean passed = true;
            for (int i = 0; i < 5; i++) {
                if (!limiter.isRequestAllowed()) {
                    passed = false;
                    break;
                }
            }
            // Wait half a second and make 3 more requests
            if (passed) {
                Thread.sleep(500); // 0.5 seconds
                for (int i = 0; i < 3; i++) {
                    if (!limiter.isRequestAllowed()) {
                        passed = false;
                        break;
                    }
                }
                if (passed) {
                    System.out.println("Test Case 5 Passed: High Burst with Partial Refill");
                } else {
                    System.out.println("Test Case 5 Failed: High Burst with Partial Refill");
                }
            } else {
                System.out.println("Test Case 5 Failed: High Burst with Partial Refill");
            }
        } catch (Exception e) {
            System.out.println("Test Case 5 Failed with Exception: " + e.getMessage());
        }

        // Test Case 6: Zero Request Limit
        try {
            RateLimiter limiter = new FixedWindowRateLimiter(0); // Invalid limit
            System.out.println("Test Case 6 Failed: Zero Request Limit not handled");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 6 Passed: Zero Request Limit");
        } catch (Exception e) {
            System.out.println("Test Case 6 Failed with Unexpected Exception: " + e.getMessage());
        }

        // Test Case 7: Negative Request Limit
        try {
            RateLimiter limiter = new FixedWindowRateLimiter(-5); // Invalid limit
            System.out.println("Test Case 7 Failed: Negative Request Limit not handled");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 7 Passed: Negative Request Limit");
        } catch (Exception e) {
            System.out.println("Test Case 7 Failed with Unexpected Exception: " + e.getMessage());
        }

        // Test Case 8: Long Idle Period
        try {
            RateLimiter limiter = new FixedWindowRateLimiter(3); // 3 requests per second
            limiter.isRequestAllowed(); // 1
            limiter.isRequestAllowed(); // 2
            limiter.isRequestAllowed(); // 3
            // Fourth request should fail
            if (!limiter.isRequestAllowed()) {
                // Wait for 2 seconds to reset the window
                Thread.sleep(2000);
                // Now, 3 more requests should be allowed
                boolean passed = true;
                for (int i = 0; i < 3; i++) {
                    if (!limiter.isRequestAllowed()) {
                        passed = false;
                        break;
                    }
                }
                if (passed) {
                    System.out.println("Test Case 8 Passed: Long Idle Period");
                } else {
                    System.out.println("Test Case 8 Failed: Long Idle Period");
                }
            } else {
                System.out.println("Test Case 8 Failed: Long Idle Period");
            }
        } catch (Exception e) {
            System.out.println("Test Case 8 Failed with Exception: " + e.getMessage());
        }

        // Test Case 9: Concurrent Access Simulation
        try {
            final RateLimiter limiter = new FixedWindowRateLimiter(50); // 50 requests per second
            int threads = 10;
            int requestsPerThread = 10;
            Thread[] threadPool = new Thread[threads];
            final int[] allowed = {0};

            for (int i = 0; i < threads; i++) {
                threadPool[i] = new Thread(() -> {
                    for (int j = 0; j < requestsPerThread; j++) {
                        if (limiter.isRequestAllowed()) {
                            synchronized (allowed) {
                                allowed[0]++;
                            }
                        }
                    }
                });
                threadPool[i].start();
            }

            for (int i = 0; i < threads; i++) {
                threadPool[i].join();
            }

            if (allowed[0] == 50) {
                System.out.println("Test Case 9 Passed: Concurrent Access Simulation");
            } else {
                System.out.println("Test Case 9 Failed: Concurrent Access Simulation (Allowed: " + allowed[0] + ")");
            }
        } catch (Exception e) {
            System.out.println("Test Case 9 Failed with Exception: " + e.getMessage());
        }

        // Test Case 10: Large Data Inputs
        try {
            RateLimiter limiter = new FixedWindowRateLimiter(10000); // 10,000 requests per second
            boolean passed = true;
            for (int i = 0; i < 10000; i++) {
                if (!limiter.isRequestAllowed()) {
                    passed = false;
                    break;
                }
            }
            if (passed && !limiter.isRequestAllowed()) {
                System.out.println("Test Case 10 Passed: Large Data Inputs");
            } else {
                System.out.println("Test Case 10 Failed: Large Data Inputs");
            }
        } catch (Exception e) {
            System.out.println("Test Case 10 Failed with Exception: " + e.getMessage());
        }

        System.out.println("RateLimiter Tests Completed.");
    }
}
