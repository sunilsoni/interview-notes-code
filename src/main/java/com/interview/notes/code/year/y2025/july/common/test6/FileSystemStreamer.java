package com.interview.notes.code.year.y2025.july.common.test6;

import java.util.*;
import java.util.stream.*;

public class FileSystemStreamer {

    /**
     * Recursively traverses the nested map, emitting file paths.
     */
    @SuppressWarnings("unchecked")
    private static Stream<String> traverse(Map<String, Object> node, String pathSoFar) {
        return node.entrySet().stream()
            .flatMap(entry -> {
                String name = entry.getKey();
                Object val = entry.getValue();
                // build the next path segment
                String nextPath = pathSoFar.isEmpty() ? name : pathSoFar + "/" + name;

                if (val instanceof Map) {
                    // directory: recurse
                    return traverse((Map<String, Object>) val, nextPath);
                } else {
                    // file: emit the full path
                    return Stream.of(nextPath);
                }
            });
    }

    /**
     * Public API: lists all file paths in the file system map.
     */
    public static Stream<String> listAllFiles(Map<String, Object> fileSystem) {
        return traverse(fileSystem, "");
    }

    /**
     * Simple main method to run PASS/FAIL tests.
     */
    public static void main(String[] args) {
        // --- Test 1: Provided example ---
        Map<String, Object> fileSystem = Map.of(
            "root", Map.of(
                "dir1", Map.of(
                    "file1.txt", null,
                    "file2.txt", null
                ),
                "dir2", Map.of(
                    "subdir1", Map.of(
                        "file3.txt", null
                    ),
                    "file4.txt", null
                )
            )
        );
        List<String> expected1 = Arrays.asList(
            "root/dir1/file1.txt",
            "root/dir1/file2.txt",
            "root/dir2/subdir1/file3.txt",
            "root/dir2/file4.txt"
        );
        runTest("Example Test", fileSystem, expected1);

        // --- Test 2: Empty map ---
        runTest("Empty FS Test",
                Collections.<String,Object>emptyMap(),
                Collections.<String>emptyList());

        // --- Test 3: Single file at root ---
        Map<String,Object> single = Map.of("foo.txt", null);
        runTest("Single File Test", single, List.of("foo.txt"));

        // --- Test 4: Large flat map ---
        Map<String,Object> largeDir = new HashMap<>();
        int N = 10_000;
        for (int i = 0; i < N; i++) {
            largeDir.put("file" + i + ".txt", null);
        }
        Map<String,Object> largeFs = Map.of("root", largeDir);
        List<String> resultLarge = listAllFiles(largeFs).collect(Collectors.toList());
        System.out.println("Large Input Test: " +
            (resultLarge.size() == N ? "PASS" : "FAIL (got " + resultLarge.size() + ")"));
    }

    /**
     * Helper to run a single test: compares sorted actual vs. expected.
     */
    private static void runTest(String testName,
                                Map<String, Object> fs,
                                List<String> expected) {
        List<String> actual = listAllFiles(fs).collect(Collectors.toList());

        // sort both for comparison, since Map.of() iteration order isn't guaranteed
        List<String> sortedActual = actual.stream().sorted().collect(Collectors.toList());
        List<String> sortedExpected = expected.stream().sorted().collect(Collectors.toList());

        boolean pass = sortedActual.equals(sortedExpected);
        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("  Expected: " + sortedExpected);
            System.out.println("  Actual:   " + sortedActual);
        }
    }
}