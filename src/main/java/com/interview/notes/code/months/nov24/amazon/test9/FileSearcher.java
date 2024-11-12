package com.interview.notes.code.months.nov24.amazon.test9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileSearcher {

    /**
     * Searches for files matching the given criteria within the specified directory and its subdirectories.
     *
     * @param startDir       The starting directory for the search.
     * @param fileName       The name of the file to search for.
     * @param minSize        The minimum file size in bytes (inclusive).
     * @param maxSize        The maximum file size in bytes (inclusive).
     * @param modifiedAfter  The earliest last modified time (inclusive).
     * @param modifiedBefore The latest last modified time (inclusive).
     * @return A list of full paths to files that match the criteria.
     * @throws IOException If an I/O error occurs.
     */
    public static List<String> searchFiles(Path startDir, String fileName, long minSize, long maxSize,
                                           Instant modifiedAfter, Instant modifiedBefore) throws IOException {
        List<String> result = new ArrayList<>();

        try (Stream<Path> stream = Files.find(startDir, Integer.MAX_VALUE, (path, attr) -> {
            boolean matches = true;

            if (fileName != null && !path.getFileName().toString().equals(fileName)) {
                matches = false;
            }

            if (matches && attr.isRegularFile()) {
                if (minSize >= 0 && attr.size() < minSize) {
                    matches = false;
                }
                if (maxSize >= 0 && attr.size() > maxSize) {
                    matches = false;
                }
                if (modifiedAfter != null && attr.lastModifiedTime().toInstant().isBefore(modifiedAfter)) {
                    matches = false;
                }
                if (modifiedBefore != null && attr.lastModifiedTime().toInstant().isAfter(modifiedBefore)) {
                    matches = false;
                }
            } else {
                matches = false;
            }

            return matches;
        })) {
            stream.forEach(path -> result.add(path.toAbsolutePath().toString()));
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int passedTests = 0;
        int failedTests = 0;

        // Test Case 1: Search for a file by name
        try {
            List<String> results = searchFiles(Paths.get("test_directory"), "example.txt", -1, -1, null, null);
            if (!results.isEmpty()) {
                System.out.println("Test Case 1 Passed: Found files: " + results);
                passedTests++;
            } else {
                System.out.println("Test Case 1 Failed: File not found.");
                failedTests++;
            }
        } catch (IOException e) {
            System.out.println("Test Case 1 Failed with exception: " + e.getMessage());
            failedTests++;
        }

        // Additional test cases can be added here following the same pattern.

        // Summary of test results
        System.out.println("Total Passed Tests: " + passedTests);
        System.out.println("Total Failed Tests: " + failedTests);
    }
}
