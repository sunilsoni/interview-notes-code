package com.interview.notes.code.year.y2025.october.jpmc.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    // Method to convert list of words into camelCase
    private static String toCamelCase(List<String> words) {
        // Stream through each word and adjust casing rules
        return IntStream.range(0, words.size())
                .mapToObj(i -> {
                    String word = words.get(i).trim(); // remove spaces
                    if (i == 0) {
                        // first word all lowercase
                        return word.toLowerCase();
                    } else {
                        // first char upper, rest lower
                        return word.substring(0, 1).toUpperCase() +
                               word.substring(1).toLowerCase();
                    }
                })
                .collect(Collectors.joining("")); // combine into one string
    }

    public static void main(String[] args) {

        // ------------------- TESTING SECTION -------------------

        List<TestCase> tests = Arrays.asList(
            new TestCase(Arrays.asList("Camel", "Case", "LOOKS", "like", "ThIs"), "camelCaseLooksLikeThis"),
            new TestCase(Arrays.asList("HELLO", "world"), "helloWorld"),
            new TestCase(Arrays.asList("java", "STREAMS", "Api"), "javaStreamsApi"),
            new TestCase(List.of("One"), "one"),
            new TestCase(Arrays.asList("a", "b", "C"), "aBC")
        );

        tests.forEach(t -> {
            long start = System.currentTimeMillis();
            String result = toCamelCase(t.input);
            long end = System.currentTimeMillis();
            String status = result.equals(t.expected) ? "PASS" : "FAIL";
            System.out.printf("Input=%s | Output=%s | Expected=%s | Result=%s | Time=%d ms%n",
                    t.input, result, t.expected, status, (end - start));
        });

        // --------------- LARGE DATA TEST -------------------
        List<String> largeInput = IntStream.range(0, 100000)
                .mapToObj(i -> "word" + i)
                .collect(Collectors.toList());
        long startLarge = System.currentTimeMillis();
        String largeResult = toCamelCase(largeInput);
        long endLarge = System.currentTimeMillis();
        System.out.printf("Large Data Test | Length=%d | Time=%d ms%n",
                largeResult.length(), (endLarge - startLarge));
    }

    // Simple helper class for test case
    static class TestCase {
        List<String> input;
        String expected;
        TestCase(List<String> input, String expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
