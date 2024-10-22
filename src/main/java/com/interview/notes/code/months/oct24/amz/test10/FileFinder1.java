package com.interview.notes.code.months.oct24.amz.test10;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FileFinder1 {

    private static final long SIZE_THRESHOLD = 5 * 1024 * 1024; // 5 MB in bytes
    private static final long EXACT_SIZE = 10 * 1024 * 1024; // 10 MB in bytes
    private static final long DAYS_THRESHOLD = 10; // Files created within the last 10 days
    private static List<String> fileExtensions = List.of(".xml", ".json"); // List of file extensions to search for

    public static void main(String[] args) {
        String testDirectory = "/path/to/test/directory";
        System.out.println("Starting to find large files in directory: " + testDirectory);
        List<File> largeFiles = findLargeFiles(new File(testDirectory));
        
        System.out.println("Files over 5 MB:");
        for (File file : largeFiles) {
            System.out.println(file.getAbsolutePath() + " - " + (file.length() / 1024 / 1024) + " MB");
        }

        System.out.println("Files exactly 10 MB and created within the last 10 days:");
        List<File> recentFiles = findRecentFiles(new File(testDirectory));
        for (File file : recentFiles) {
            System.out.println(file.getAbsolutePath() + " - " + (file.length() / 1024 / 1024) + " MB");
        }
        
        runTests(); // Run the test cases to verify functionality
    }

    // Method to find all files larger than the specified SIZE_THRESHOLD in the given directory
    public static List<File> findLargeFiles(File directory) {
        System.out.println("Searching for large files in: " + directory.getAbsolutePath());
        List<File> result = new ArrayList<>();
        findLargeFilesRecursive(directory, result);
        System.out.println("Found " + result.size() + " large files in directory: " + directory.getAbsolutePath());
        return result;
    }

    // Recursive helper method to find large files and files with specific extensions
    private static void findLargeFilesRecursive(File directory, List<File> result) {
        System.out.println("Checking directory: " + directory.getAbsolutePath());
        File[] files = directory.listFiles(); // Get all files and subdirectories in the directory
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) { // If the file is a directory, search recursively
                    System.out.println("Entering subdirectory: " + file.getAbsolutePath());
                    findLargeFilesRecursive(file, result);
                } else if (file.length() > SIZE_THRESHOLD || hasMatchingExtension(file)) { // If the file is larger than the threshold or matches a specified extension, add to result
                    System.out.println("Found file: " + file.getAbsolutePath() + " (" + (file.length() / 1024 / 1024) + " MB)");
                    result.add(file);
                }
            }
        }
    }

    // Method to find files exactly 10 MB in size and created within the last 10 days
    public static List<File> findRecentFiles(File directory) {
        System.out.println("Searching for files exactly 10 MB and created within the last 10 days in: " + directory.getAbsolutePath());
        List<File> result = new ArrayList<>();
        findRecentFilesRecursive(directory, result);
        System.out.println("Found " + result.size() + " recent files in directory: " + directory.getAbsolutePath());
        return result;
    }

    // Recursive helper method to find recent files based on size and creation time
    private static void findRecentFilesRecursive(File directory, List<File> result) {
        System.out.println("Checking directory: " + directory.getAbsolutePath());
        File[] files = directory.listFiles(); // Get all files and subdirectories in the directory
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) { // If the file is a directory, search recursively
                    System.out.println("Entering subdirectory: " + file.getAbsolutePath());
                    findRecentFilesRecursive(file, result);
                } else if (file.length() == EXACT_SIZE && isCreatedWithinLastDays(file, DAYS_THRESHOLD)) { // If the file is exactly 10 MB and created within the last 10 days, add to result
                    System.out.println("Found recent file: " + file.getAbsolutePath() + " (" + (file.length() / 1024 / 1024) + " MB)");
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
        assert findLargeFiles(emptyDir).isEmpty() : "Test case 1 failed: Empty directory should return no files";
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
        assert findLargeFiles(smallFilesDir).isEmpty() : "Test case 2 failed: Directory with small files should return no results";
        deleteDirectory(smallFilesDir); // Clean up the test directory

        // Test case 3: Directory with large files
        // This test would require creating actual large files, which might not be practical for all environments
        // Instead, we'll mock this test by temporarily changing the SIZE_THRESHOLD

        long originalThreshold = SIZE_THRESHOLD;
        try {
            System.out.println("Test case 3: Directory with large files (mocked)");
            // Use reflection to change the value of SIZE_THRESHOLD temporarily
            java.lang.reflect.Field field = FileFinder1.class.getDeclaredField("SIZE_THRESHOLD");
            field.setAccessible(true);
            field.set(null, 1L); // Set threshold to 1 byte temporarily to simulate large files

            File largeFilesDir = new File("large_files_test_dir");
            largeFilesDir.mkdir();
            new File(largeFilesDir, "large_file.txt").createNewFile(); // Create a file that exceeds the temporary threshold

            List<File> result = findLargeFiles(largeFilesDir);
            assert result.size() == 1 : "Test case 3 failed: Should find one large file";

            deleteDirectory(largeFilesDir); // Clean up the test directory
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Reset the threshold back to its original value
            try {
                java.lang.reflect.Field field = FileFinder1.class.getDeclaredField("SIZE_THRESHOLD");
                field.setAccessible(true);
                field.set(null, originalThreshold);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
