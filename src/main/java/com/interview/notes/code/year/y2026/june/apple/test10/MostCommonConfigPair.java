package com.interview.notes.code.year.y2026.june.apple.test10;

// Imports collection classes such as List, Map, Set, and TreeMap.

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Imports Function.identity() for grouping identical values.
// Imports Stream collectors such as groupingBy and counting.
// Imports IntStream to create a large test input.

// Class name clearly describes the problem.
public class MostCommonConfigPair {


// Finds all key-value pairs having the highest occurrence count.
static Result mostCommon(List<Map<String, Object>> configs) {

    // Reads all configuration maps as a stream.
    var counts = configs.stream()

            // Converts every map into a stream of key-value entries.
            .flatMap(config -> config.entrySet().stream())

            // Converts each entry into a simple "key=value" format.
            .map(entry -> entry.getKey() + "=" + entry.getValue())

            // Groups equal pairs and counts their occurrences.
            .collect(Collectors.groupingBy(

                    // Uses the complete "key=value" string as the grouping key.
                    Function.identity(),

                    // Keeps results sorted and gives predictable output.
                    TreeMap::new,

                    // Counts how many times each pair appears.
                    Collectors.counting()
            ));

    // Reads all occurrence counts.
    long max = counts.values().stream()

            // Converts Long objects into primitive long values.
            .mapToLong(Long::longValue)

            // Finds the highest occurrence count.
            .max()

            // Returns zero when the input list is empty.
            .orElse(0);

    // Reads all counted key-value pairs.
    var pairs = counts.entrySet().stream()

            // Keeps only pairs matching the highest count.
            .filter(entry -> entry.getValue() == max)

            // Extracts only the "key=value" text.
            .map(Map.Entry::getKey)

            // Converts the stream into a list.
            .toList();

    // Returns the most common pairs and their count.
    return new Result(pairs, max);
}

// Runs one test case and prints PASS or FAIL.
static void test(
        String name,
        List<Map<String, Object>> input,
        List<String> expectedPairs,
        long expectedCount) {

    // Executes the solution for the current test input.
    var actual = mostCommon(input);

    // Uses sets because the order of tied pairs should not matter.
    boolean samePairs =
            new HashSet<>(actual.pairs())
                    .equals(new HashSet<>(expectedPairs));

    // Checks both the pairs and their occurrence count.
    boolean pass =
            samePairs && actual.count() == expectedCount;

    // Prints the final test result.
    System.out.println(
            name + ": "
                    + (pass ? "PASS" : "FAIL")
                    + " -> "
                    + actual
    );
}

// Main method runs all test cases without JUnit.
public static void main(String[] args) {

    // Creates the configurations provided in the question.
    var configs = List.of(

            // First configuration.
            Map.<String, Object>of(
                    "status", "active",
                    "region", "us-west",
                    "version", "2.1",
                    "retries", 3
            ),

            // Second configuration.
            Map.<String, Object>of(
                    "status", "active",
                    "region", "us-east",
                    "version", "2.1",
                    "retries", 3
            ),

            // Third configuration has no retries field.
            Map.<String, Object>of(
                    "status", "active",
                    "region", "us-west",
                    "version", "2.0"
            ),

            // Fourth configuration has inactive status.
            Map.<String, Object>of(
                    "status", "inactive",
                    "region", "us-west",
                    "version", "2.1",
                    "retries", 3
            ),

            // Fifth configuration uses the eu-west region.
            Map.<String, Object>of(
                    "status", "active",
                    "region", "eu-west",
                    "version", "2.1",
                    "retries", 3
            )
    );

    // Tests the input provided in the interview question.
    test(
            "Given input",
            configs,
            List.of(
                    "status=active",
                    "version=2.1",
                    "retries=3"
            ),
            4
    );

    // Tests an empty configuration list.
    test(
            "Empty input",
            List.of(),
            List.of(),
            0
    );

    // Tests a list containing only one key-value pair.
    test(
            "Single pair",
            List.of(
                    Map.of(
                            "status", "active"
                    )
            ),
            List.of("status=active"),
            1
    );

    // Creates 100,000 configurations for large-input testing.
    var largeInput = IntStream.range(0, 100_000)

            // Gives every record the same status but a unique ID.
            .mapToObj(index ->
                    Map.<String, Object>of(
                            "status", "active",
                            "id", index
                    )
            )

            // Converts the generated stream into a list.
            .toList();

    // Verifies that the solution handles large input correctly.
    test(
            "Large input",
            largeInput,
            List.of("status=active"),
            100_000
    );
}

// Stores the most common pairs and their occurrence count.
record Result(List<String> pairs, long count) {
}


}
