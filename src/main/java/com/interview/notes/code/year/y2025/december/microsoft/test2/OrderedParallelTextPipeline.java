package com.interview.notes.code.year.y2025.december.microsoft.test2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class OrderedParallelTextPipeline {

    static String run(String a, String b) {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        try {
            var first = CompletableFuture.supplyAsync(() -> a, executor);
            var second = CompletableFuture.supplyAsync(() -> b, executor);
            return first.thenCombine(second, String::concat).join();
        } finally {
            executor.close();
        }
    }

    static void test(String name, String a, String b, String expected) {
        var result = run(a, b);
        System.out.println(name + " : " + (result.equals(expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test("Basic",
                "My name ",
                "is vishal",
                "My name is vishal"
        );

        test("EmptyFirst",
                "",
                "data",
                "data"
        );

        test("EmptySecond",
                "data",
                "",
                "data"
        );

        var largeA = IntStream.range(0, 1_000_000)
                .mapToObj(i -> "A")
                .reduce("", String::concat);

        var largeB = IntStream.range(0, 1_000_000)
                .mapToObj(i -> "B")
                .reduce("", String::concat);

        test("LargeData",
                largeA,
                largeB,
                largeA + largeB
        );
    }
}