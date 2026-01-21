package com.interview.notes.code.year.y2025.december.microsoft.test1;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ParallelOrderedTaskExecutor {

    static String execute(String part, String name, long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }
        System.out.println(name + " done: " + part);
        return part;
    }

    static String runParallelOrdered(String s1, String s2) {
        var f1 = CompletableFuture.supplyAsync(() -> execute(s1, "T1", 200));
        var f2 = CompletableFuture.supplyAsync(() -> execute(s2, "T2", 50));
        return f1.thenCombine(f2, (a, b) -> a + b).join();
    }

    static String runWithVirtualThreads(String s1, String s2) throws Exception {
        var results = new String[2];
        var latch = new CountDownLatch(2);

        Thread.ofVirtual().start(() -> {
            results[0] = execute(s1, "VT1", 200);
            latch.countDown();
        });

        Thread.ofVirtual().start(() -> {
            results[1] = execute(s2, "VT2", 50);
            latch.countDown();
        });

        latch.await();
        return results[0] + results[1];
    }

    public static void main(String[] args) throws Exception {
        String s1 = "My name ";
        String s2 = "is vishal";
        String expected = "My name is vishal";

        record Test(String p1, String p2, String exp) {
        }

        var tests = List.of(
                new Test(s1, s2, expected),
                new Test("Hello ", "World", "Hello World"),
                new Test("First ", "Second", "First Second")
        );

        System.out.println("=== CompletableFuture Test ===\n");
        int pass = 0;
        for (int i = 0; i < tests.size(); i++) {
            var t = tests.get(i);
            var result = runParallelOrdered(t.p1(), t.p2());
            boolean ok = result.equals(t.exp());
            System.out.println("Test" + (i + 1) + ": " + (ok ? "PASS" : "FAIL"));
            System.out.println("Result: " + result + "\n");
            if (ok) pass++;
        }

        System.out.println("=== Virtual Threads Test ===\n");
        for (int i = 0; i < tests.size(); i++) {
            var t = tests.get(i);
            var result = runWithVirtualThreads(t.p1(), t.p2());
            boolean ok = result.equals(t.exp());
            System.out.println("Test" + (i + 1) + ": " + (ok ? "PASS" : "FAIL"));
            System.out.println("Result: " + result + "\n");
            if (ok) pass++;
        }

        System.out.println("=== Large Data Test ===");
        var large1 = IntStream.range(0, 5000).mapToObj(i -> "A").collect(Collectors.joining());
        var large2 = IntStream.range(0, 5000).mapToObj(i -> "B").collect(Collectors.joining());

        long start = System.currentTimeMillis();
        var largeResult = runParallelOrdered(large1, large2);
        System.out.println("Large Test: " + (largeResult.equals(large1 + large2) ? "PASS" : "FAIL"));
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");

        System.out.println("\nTotal: " + pass + "/" + (tests.size() * 2) + " PASSED");
    }
}