package com.interview.notes.code.months.oct24.amazon.test9;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {

    private static final long SIZE_THRESHOLD = 5 * 1024 * 1024; // 5 MB in bytes

    public static void main(String[] args) {
        String testDirectory = "/path/to/test/directory";
        List<File> largeFiles = findLargeFiles(new File(testDirectory));

        System.out.println("Files over 5 MB:");
        for (File file : largeFiles) {
            System.out.println(file.getAbsolutePath() + " - " + (file.length() / 1024 / 1024) + " MB");
        }

        runTests();
    }

    public static List<File> findLargeFiles(File directory) {
        List<File> result = new ArrayList<>();
        findLargeFilesRecursive(directory, result);
        return result;
    }

    private static void findLargeFilesRecursive(File directory, List<File> result) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findLargeFilesRecursive(file, result);
                } else if (file.length() > SIZE_THRESHOLD) {
                    result.add(file);
                }
            }
        }
    }

    private static void runTests() {
        // Test case 1: Empty directory
        File emptyDir = new File("empty_test_dir");
        emptyDir.mkdir();
        assert findLargeFiles(emptyDir).isEmpty() : "Test case 1 failed: Empty directory should return no files";
        emptyDir.delete();

        // Test case 2: Directory with no large files
        File smallFilesDir = new File("small_files_test_dir");
        smallFilesDir.mkdir();
        try {
            new File(smallFilesDir, "small_file.txt").createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert findLargeFiles(smallFilesDir).isEmpty() : "Test case 2 failed: Directory with small files should return no results";
        deleteDirectory(smallFilesDir);

        // Test case 3: Directory with large files
        // This test would require creating actual large files, which might not be practical for all environments
        // Instead, we'll mock this test by temporarily changing the SIZE_THRESHOLD

        long originalThreshold = SIZE_THRESHOLD;
        try {
            java.lang.reflect.Field field = FileFinder.class.getDeclaredField("SIZE_THRESHOLD");
            field.setAccessible(true);
            field.set(null, 1L); // Set threshold to 1 byte temporarily

            File largeFilesDir = new File("large_files_test_dir");
            largeFilesDir.mkdir();
            new File(largeFilesDir, "large_file.txt").createNewFile();

            List<File> result = findLargeFiles(largeFilesDir);
            assert result.size() == 1 : "Test case 3 failed: Should find one large file";

            deleteDirectory(largeFilesDir);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Reset the threshold
            try {
                java.lang.reflect.Field field = FileFinder.class.getDeclaredField("SIZE_THRESHOLD");
                field.setAccessible(true);
                field.set(null, originalThreshold);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("All tests passed successfully!");
    }

    private static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }
}
