package com.interview.notes.code.year.y2025.June.amazon.test1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileFinder {
    // Constant for 5MB in bytes (5 * 1024 * 1024)
    private static final long SIZE_THRESHOLD = 5_242_880;

    // Main method to find large files
    public static List<File> findLargeFiles(String directoryPath) {
        // Create File object for the starting directory
        File directory = new File(directoryPath);
        
        // List to store files that meet our criteria
        List<File> results = new ArrayList<>();
        
        // Check if directory exists and is actually a directory
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Invalid directory path: " + directoryPath);
            return results;
        }

        try {
            // Use Java 8 Stream API to process files
            results = java.nio.file.Files.walk(directory.toPath())
                .filter(path -> path.toFile().isFile()) // Only process files, not directories
                .map(path -> path.toFile()) // Convert Path to File
                .filter(file -> file.length() > SIZE_THRESHOLD) // Filter files > 5MB
                .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error searching directory: " + e.getMessage());
        }

        return results;
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Search in current directory
        testFindLargeFiles(".");

        // Test Case 2: Search in non-existent directory
        testFindLargeFiles("/nonexistent/path");

        // Test Case 3: Search in system directory (for larger files)
        testFindLargeFiles("/usr");

        // Test Case 4: Search in user's home directory
        testFindLargeFiles(System.getProperty("user.home"));
    }

    // Helper method to run and validate test cases
    private static void testFindLargeFiles(String path) {
        System.out.println("\nTesting directory: " + path);
        System.out.println("------------------------");

        List<File> largeFiles = findLargeFiles(path);

        // Print results
        if (largeFiles.isEmpty()) {
            System.out.println("No files larger than 5MB found");
        } else {
            System.out.println("Found " + largeFiles.size() + " files larger than 5MB:");
            largeFiles.forEach(file -> {
                System.out.printf("File: %s (Size: %.2f MB)%n", 
                    file.getAbsolutePath(), 
                    file.length() / (1024.0 * 1024.0));
            });
        }
    }
}
