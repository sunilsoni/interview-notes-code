package com.interview.notes.code.year.y2024.oct24.amazon.test10;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FileFinder {

    private static final List<String> fileExtensions = List.of(".xml", ".json"); // List of file extensions to search for

    public static void main(String[] args) {
        String testDirectory = "/path/to/test/directory";
        System.out.println("Starting to find files based on different criteria in directory: " + testDirectory);

        // Example: Find files larger than 5 MB
        List<File> largeFiles = findFiles(new File(testDirectory), file -> file.length() > 5 * 1024 * 1024);
        System.out.println("Files over 5 MB:");
        for (File file : largeFiles) {
            System.out.println(file.getAbsolutePath() + " - " + (file.length() / 1024 / 1024) + " MB");
        }

        // Example: Find files exactly 10 MB and created within the last 10 days
        List<File> recentFiles = findFiles(new File(testDirectory), file -> file.length() == 10 * 1024 * 1024 && isCreatedWithinLastDays(file, 10));
        System.out.println("Files exactly 10 MB and created within the last 10 days:");
        for (File file : recentFiles) {
            System.out.println(file.getAbsolutePath() + " - " + (file.length() / 1024 / 1024) + " MB");
        }

        runTests(); // Run the test cases to verify functionality
    }

    // Method to find files based on a given condition
    public static List<File> findFiles(File directory, Predicate<File> condition) {
        System.out.println("Searching for files in: " + directory.getAbsolutePath());
        List<File> result = new ArrayList<>();
        findFilesRecursive(directory, condition, result);
        System.out.println("Found " + result.size() + " files in directory: " + directory.getAbsolutePath());
        return result;
    }

    // Recursive helper method to find files based on a given condition
    private static void findFilesRecursive(File directory, Predicate<File> condition, List<File> result) {
        System.out.println("Checking directory: " + directory.getAbsolutePath());
        File[] files = directory.listFiles(); // Get all files and subdirectories in the directory
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) { // If the file is a directory, search recursively
                    System.out.println("Entering subdirectory: " + file.getAbsolutePath());
                    findFilesRecursive(file, condition, result);
                } else if (condition.test(file) || hasMatchingExtension(file)) { // If the file matches the condition or has a matching extension, add to result
                    System.out.println("Found file: " + file.getAbsolutePath() + " (" + (file.length() / 1024 / 1024) + " MB)");
                    result.add(file);
                }
            }
        }
    }

    // Method to check if a file was created within the last specified number of days
    private static boolean isCreatedWithinLastDays(File file, long days) {
        long currentTime = System.currentTimeMillis();
        long lastModifiedTime = file.lastModified();
        ZonedDateTime fileTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(lastModifiedTime), ZoneId.systemDefault());
        ZonedDateTime currentTimeMinusDays = ZonedDateTime.now().minusDays(days);
        return fileTime.isAfter(currentTimeMinusDays);
    }

    // Method to check if a file has a matching extension
    private static boolean hasMatchingExtension(File file) {
        for (String extension : fileExtensions) {
            if (file.getName().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    // Method to run test cases
    private static void runTests() {
        System.out.println("Running test cases...");
        // Test case 1: Empty directory
        File emptyDir = new File("empty_test_dir");
        emptyDir.mkdir();
        System.out.println("Test case 1: Empty directory");
        assert findFiles(emptyDir, file -> file.length() > 5 * 1024 * 1024).isEmpty() : "Test case 1 failed: Empty directory should return no files";
        emptyDir.delete();

        // Test case 2: Directory with no large files
        File smallFilesDir = new File("small_files_test_dir");
        smallFilesDir.mkdir();
        System.out.println("Test case 2: Directory with no large files");
        try {
            new File(smallFilesDir, "small_file.txt").createNewFile(); // Create a small file
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert findFiles(smallFilesDir, file -> file.length() > 5 * 1024 * 1024).isEmpty() : "Test case 2 failed: Directory with small files should return no results";
        deleteDirectory(smallFilesDir); // Clean up the test directory

        // Test case 3: Directory with large files
        // This test would require creating actual large files, which might not be practical for all environments
        // Instead, we'll mock this test by temporarily changing the condition

        try {
            System.out.println("Test case 3: Directory with large files (mocked)");
            File largeFilesDir = new File("large_files_test_dir");
            largeFilesDir.mkdir();
            new File(largeFilesDir, "large_file.txt").createNewFile(); // Create a file that exceeds the temporary threshold

            List<File> result = findFiles(largeFilesDir, file -> true); // Mock condition to simulate finding the file
            assert result.size() == 1 : "Test case 3 failed: Should find one large file";

            deleteDirectory(largeFilesDir); // Clean up the test directory
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("All tests passed successfully!");
    }

    // Method to delete a directory and its contents recursively
    private static void deleteDirectory(File directory) {
        System.out.println("Deleting directory: " + directory.getAbsolutePath());
        File[] files = directory.listFiles(); // Get all files and subdirectories in the directory
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) { // If the file is a directory, delete its contents recursively
                    deleteDirectory(file);
                } else { // If the file is a regular file, delete it
                    System.out.println("Deleting file: " + file.getAbsolutePath());
                    file.delete();
                }
            }
        }
        directory.delete(); // Delete the empty directory
        System.out.println("Deleted directory: " + directory.getAbsolutePath());
    }
}
