package com.interview.notes.code.year.y2025.may.common.test4;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateFilesFinder {

    // Method to compute SHA-256 hash for file contents
    private static String getFileChecksum(Path filePath) throws Exception {
        byte[] data = Files.readAllBytes(filePath);
        byte[] hash = MessageDigest.getInstance("SHA-256").digest(data);
        return new BigInteger(1, hash).toString(16);
    }

    // Method to find duplicates by content
    public static List<List<String>> findDuplicateFiles(String directoryPath) throws Exception {
        Map<String, List<String>> contentToFilePaths = new HashMap<>();

        // Traverse the directory using Files.walk
        Files.walk(Paths.get(directoryPath))
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    try {
                        String checksum = getFileChecksum(path);
                        contentToFilePaths
                                .computeIfAbsent(checksum, k -> new ArrayList<>())
                                .add(path.toString());
                    } catch (Exception e) {
                        System.err.println("Error processing file: " + path + ", error: " + e.getMessage());
                    }
                });

        // Filter groups that contain duplicates
        return contentToFilePaths.values().stream()
                .filter(list -> list.size() > 1)
                .collect(Collectors.toList());
    }

    // Main method for testing
    public static void main(String[] args) throws Exception {

        // Minimal Test Case (create your own files/folders to validate)
        String testDir = "./test_directory";  // change to your actual test directory

        List<List<String>> duplicates = findDuplicateFiles(testDir);

        if (duplicates.isEmpty()) {
            System.out.println("No duplicate files found.");
        } else {
            System.out.println("Duplicate files detected:");
            duplicates.forEach(group -> {
                System.out.println("Group:");
                group.forEach(System.out::println);
            });
        }

        // Edge Case (Empty directory or large data)
        testEdgeCases();
    }

    // Method for additional edge case testing
    private static void testEdgeCases() throws Exception {
        // Edge Case 1: Empty Directory
        String emptyDir = "./empty_directory";
        List<List<String>> result = findDuplicateFiles(emptyDir);
        System.out.println("Empty directory test: " + (result.isEmpty() ? "PASS" : "FAIL"));

        // Edge Case 2: Large Directory with many files
        // For actual testing, set up a large directory with many files
        // Here, just demonstrating structure, as large tests require manual setup
        String largeDir = "./large_directory";
        result = findDuplicateFiles(largeDir);
        System.out.println("Large directory test: " + (result != null ? "PASS (manual check)" : "FAIL"));
    }
}