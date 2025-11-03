package com.interview.notes.code.year.y2025.november.Amazon.test1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * File Deduplication Tool - Identifies duplicate files for cleanup
 * This class helps identify which files are duplicates when a friend 
 * has pranked you by copying files to different locations with different names
 */
public class FileDuplicateDetector {
    
    /**
     * Main method to detect duplicate files using Java 8 Stream API
     * This method groups files by their content hash and identifies duplicates
     *
     * @param files List of all files to analyze
     * @return DuplicateDetectionResult containing files to keep and delete
     */
    public static DuplicateDetectionResult findDuplicateFiles(List<FileInfo> files) {
        // Create result object to store the outcome
        DuplicateDetectionResult result = new DuplicateDetectionResult();

        // Use Java 8 Stream API to group files by their content hash
        // Files with same content hash are duplicates
        Map<String, List<FileInfo>> groupedFiles = files.stream()
            // Group files by content hash - this is the key to finding duplicates
            .collect(Collectors.groupingBy(FileInfo::contentHash));

        // Process each group to identify duplicates
        groupedFiles.entrySet().stream()
            // Process each hash group
            .forEach(entry -> {
                String contentHash = entry.getKey();        // Get the content hash
                List<FileInfo> duplicateFiles = entry.getValue(); // Get all files with same hash

                // If there's only one file with this hash, it's not a duplicate
                if (duplicateFiles.size() == 1) {
                    // Single file - add to keep list
                    result.addFileToKeep(duplicateFiles.get(0));
                } else {
                    // Multiple files with same hash - these are duplicates
                    // Keep the first file (could be based on any criteria like oldest, smallest name, etc.)
                    result.addFileToKeep(duplicateFiles.get(0));

                    // Add remaining files to delete list using Stream API
                    duplicateFiles.stream()
                        .skip(1)  // Skip the first file (which we're keeping)
                        .forEach(result::addFileToDelete); // Add rest to delete list
                }
            });

        // Return the result containing files to keep and delete
        return result;
    }
    
    /**
     * Alternative method using more advanced Stream operations
     * This demonstrates different Stream API techniques for the same problem
     *
     * @param files List of all files to analyze
     * @return Set of files that can be safely deleted
     */
    public static Set<FileInfo> findFilesToDelete(List<FileInfo> files) {
        // Use Stream API to find files that can be deleted
        return files.stream()
            // Group files by content hash
            .collect(Collectors.groupingBy(FileInfo::contentHash))
            .values() // Get all groups of files
            .stream()
            // Filter groups that have more than one file (duplicates)
            .filter(group -> group.size() > 1)
            // For each group, skip first file and collect rest for deletion
            .flatMap(group -> group.stream().skip(1))
            // Collect all files to delete into a Set
            .collect(Collectors.toSet());
    }
    
    /**
     * Method to get statistics about duplicate files
     * Provides useful information about the duplicate detection results
     *
     * @param files List of all files to analyze
     * @return Map containing various statistics
     */
    public static Map<String, Object> getDuplicateStatistics(List<FileInfo> files) {
        // Create map to store statistics
        Map<String, Object> stats = new HashMap<>();

        // Group files by content hash to analyze duplicates
        Map<String, List<FileInfo>> groupedFiles = files.stream()
            .collect(Collectors.groupingBy(FileInfo::contentHash));

        // Calculate total number of files
        stats.put("totalFiles", files.size());

        // Calculate number of unique files (distinct content)
        stats.put("uniqueFiles", groupedFiles.size());

        // Calculate number of duplicate groups
        long duplicateGroups = groupedFiles.values().stream()
            .mapToLong(List::size)  // Get size of each group
            .filter(size -> size > 1) // Filter groups with more than one file
            .count(); // Count such groups
        stats.put("duplicateGroups", duplicateGroups);

        // Calculate total number of duplicate files (files that can be deleted)
        long duplicateFiles = groupedFiles.values().stream()
            .mapToLong(group -> Math.max(0, group.size() - 1)) // For each group, count extras
            .sum(); // Sum all extras
        stats.put("duplicateFiles", duplicateFiles);

        // Calculate space that can be saved by deleting duplicates
        long spaceSaved = groupedFiles.values().stream()
            .filter(group -> group.size() > 1) // Only groups with duplicates
            .mapToLong(group -> {
                // Calculate space saved for this group
                long fileSize = group.get(0).fileSize(); // Get file size
                return fileSize * (group.size() - 1); // Multiply by number of extras
            })
            .sum(); // Sum space saved across all groups
        stats.put("spaceSavedBytes", spaceSaved);

        // Return the statistics map
        return stats;
    }
    
    /**
     * Test method to validate the solution with various test cases
     * This method runs all test cases and reports pass/fail status
     */
    public static void main(String[] args) {
        System.out.println("=== File Duplicate Detector Test Suite ===\n");

        // Test case counter for tracking
        int testCaseNumber = 1;
        int passedTests = 0;
        int totalTests = 0;

        // Test Case 1: Basic duplicate detection
        System.out.println("Test Case " + testCaseNumber + ": Basic Duplicate Detection");
        try {
            // Create test files with some duplicates
            List<FileInfo> files1 = Arrays.asList(
                new FileInfo("f1.txt", 100, "hash1", "C:/"),      // Original file
                new FileInfo("f2.txt", 200, "hash2", "C:/"),      // Original file
                new FileInfo("f3.txt", 300, "hash3", "C:/"),      // Original file
                new FileInfo("f4.txt", 200, "hash2", "D:/"),      // Copy of f2 (same hash)
                new FileInfo("f5.txt", 300, "hash3", "D:/")       // Copy of f3 (same hash)
            );

            // Run duplicate detection
            DuplicateDetectionResult result1 = findDuplicateFiles(files1);

            // Validate results
            boolean test1Pass = result1.getFilesToKeep().size() == 3 &&  // Should keep 3 unique files
                               result1.getFilesToDelete().size() == 2;   // Should delete 2 duplicates

            System.out.println("Files to keep: " + result1.getFilesToKeep().size());
            System.out.println("Files to delete: " + result1.getFilesToDelete().size());
            System.out.println("Result: " + (test1Pass ? "PASS" : "FAIL"));

            if (test1Pass) passedTests++;
            totalTests++;

        } catch (Exception e) {
            System.out.println("Result: FAIL - Exception: " + e.getMessage());
            totalTests++;
        }

        testCaseNumber++;
        System.out.println();

        // Test Case 2: No duplicates
        System.out.println("Test Case " + testCaseNumber + ": No Duplicates");
        try {
            // Create test files with no duplicates
            List<FileInfo> files2 = Arrays.asList(
                new FileInfo("unique1.txt", 100, "hash1", "C:/"),
                new FileInfo("unique2.txt", 200, "hash2", "C:/"),
                new FileInfo("unique3.txt", 300, "hash3", "C:/")
            );

            DuplicateDetectionResult result2 = findDuplicateFiles(files2);

            boolean test2Pass = result2.getFilesToKeep().size() == 3 &&
                               result2.getFilesToDelete().size() == 0;

            System.out.println("Files to keep: " + result2.getFilesToKeep().size());
            System.out.println("Files to delete: " + result2.getFilesToDelete().size());
            System.out.println("Result: " + (test2Pass ? "PASS" : "FAIL"));

            if (test2Pass) passedTests++;
            totalTests++;

        } catch (Exception e) {
            System.out.println("Result: FAIL - Exception: " + e.getMessage());
            totalTests++;
        }

        testCaseNumber++;
        System.out.println();

        // Test Case 3: All files are duplicates
        System.out.println("Test Case " + testCaseNumber + ": All Files Are Duplicates");
        try {
            List<FileInfo> files3 = Arrays.asList(
                new FileInfo("copy1.txt", 500, "samehash", "C:/"),
                new FileInfo("copy2.txt", 500, "samehash", "D:/"),
                new FileInfo("copy3.txt", 500, "samehash", "E:/"),
                new FileInfo("copy4.txt", 500, "samehash", "F:/")
            );

            DuplicateDetectionResult result3 = findDuplicateFiles(files3);

            boolean test3Pass = result3.getFilesToKeep().size() == 1 &&
                               result3.getFilesToDelete().size() == 3;

            System.out.println("Files to keep: " + result3.getFilesToKeep().size());
            System.out.println("Files to delete: " + result3.getFilesToDelete().size());
            System.out.println("Result: " + (test3Pass ? "PASS" : "FAIL"));

            if (test3Pass) passedTests++;
            totalTests++;

        } catch (Exception e) {
            System.out.println("Result: FAIL - Exception: " + e.getMessage());
            totalTests++;
        }

        testCaseNumber++;
        System.out.println();

        // Test Case 4: Empty file list
        System.out.println("Test Case " + testCaseNumber + ": Empty File List");
        try {
            List<FileInfo> files4 = new ArrayList<>();

            DuplicateDetectionResult result4 = findDuplicateFiles(files4);

            boolean test4Pass = result4.getFilesToKeep().size() == 0 &&
                               result4.getFilesToDelete().size() == 0;

            System.out.println("Files to keep: " + result4.getFilesToKeep().size());
            System.out.println("Files to delete: " + result4.getFilesToDelete().size());
            System.out.println("Result: " + (test4Pass ? "PASS" : "FAIL"));

            if (test4Pass) passedTests++;
            totalTests++;

        } catch (Exception e) {
            System.out.println("Result: FAIL - Exception: " + e.getMessage());
            totalTests++;
        }

        testCaseNumber++;
        System.out.println();

        // Test Case 5: Large data test (Performance test)
        System.out.println("Test Case " + testCaseNumber + ": Large Data Set");
        try {
            // Create large dataset with many duplicates
            List<FileInfo> largeFileList = new ArrayList<>();

            // Generate 10000 files with some duplicates
            for (int i = 0; i < 10000; i++) {
                // Create some duplicates by reusing hash values
                String hash = "hash" + (i % 1000); // This creates duplicates
                largeFileList.add(new FileInfo("file" + i + ".txt", i % 500 + 100, hash, "C:/"));
            }

            long startTime = System.currentTimeMillis();
            DuplicateDetectionResult largeResult = findDuplicateFiles(largeFileList);
            long endTime = System.currentTimeMillis();

            // Validate that we have some files to keep and some to delete
            boolean test5Pass = largeResult.getFilesToKeep().size() > 0 &&
                               largeResult.getFilesToDelete
                                       ().size() > 0;

            System.out.println("Files to keep: " + largeResult.getFilesToKeep().size());
            System.out.println("Files to delete: " + largeResult.getFilesToDelete().size());
            System.out.println("Test executed in: " + (endTime - startTime) + "ms");
            System.out.println("Result: " + (test5Pass ? "PASS" : "FAIL"));

            if (test5Pass) passedTests++;
            totalTests++;

        } catch (Exception e) {
            System.out.println("Result: FAIL - Exception: " + e.getMessage());
            totalTests++;
        }

        // Print final test results
        System.out.println("\n=== Test Summary ===");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed Tests: " + passedTests);
        System.out.println("Failed Tests: " + (totalTests - passedTests));
    }

    /**
     * Represents a file with its properties
     * This class simulates file attributes that help identify duplicates
     *
     * @param fileName    Name of the file
     * @param fileSize    Size of file in bytes - used to identify duplicates
     * @param contentHash Hash of file content - unique identifier for file content
     * @param location    File path/location
     */
        record FileInfo(String fileName, long fileSize, String contentHash, String location) {
        // Constructor to create a new FileInfo object with all required properties
        // Set the file name
        // Set the file size
        // Set the content hash
        // Set the file location

        // Override toString method to provide readable file information
            @Override
            public String toString() {
                return String.format("File: %s, Size: %d, Hash: %s, Location: %s",
                        fileName, fileSize, contentHash, location);
            }
        }
    
    /**
     * Result class to store information about duplicate files
     * Contains files to keep and files to delete
     */
    static class DuplicateDetectionResult {
        private final List<FileInfo> filesToKeep;    // Files that should be kept
        private final List<FileInfo> filesToDelete;  // Files that should be deleted (duplicates)

        // Constructor to initialize the result with empty lists
        public DuplicateDetectionResult() {
            this.filesToKeep = new ArrayList<>();     // Initialize empty list for files to keep
            this.filesToDelete = new ArrayList<>();   // Initialize empty list for files to delete
        }

        // Getter method to retrieve files that should be kept
        public List<FileInfo> getFilesToKeep() { return filesToKeep; }

        // Getter method to retrieve files that should be deleted
        public List<FileInfo> getFilesToDelete() { return filesToDelete; }

        // Method to add a file to the "keep" list
        public void addFileToKeep(FileInfo file) { filesToKeep.add(file); }

        // Method to add a file to the "delete" list
        public void addFileToDelete(FileInfo file) { filesToDelete.add(file); }
    }
}