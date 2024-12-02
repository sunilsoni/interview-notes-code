package com.interview.notes.code.year.y2024.nov24.amazon.test10;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomFileSearchUtility {

    // Custom implementation of file search
    public static List<String> searchFiles(String rootPath, SearchCriteria criteria) {
        List<String> results = new ArrayList<>();
        File rootDir = new File(rootPath);

        if (!rootDir.exists()) {
            return results;
        }

        searchFilesRecursive(rootDir, criteria, results);
        return results;
    }

    // Recursive method to search files
    private static void searchFilesRecursive(File currentDir, SearchCriteria criteria, List<String> results) {
        try {
            File[] files = currentDir.listFiles();
            if (files == null) return;

            for (File file : files) {
                if (file.isDirectory()) {
                    searchFilesRecursive(file, criteria, results);
                } else if (matchesCriteria(file, criteria)) {
                    results.add(file.getAbsolutePath());
                }
            }
        } catch (SecurityException e) {
            System.err.println("Access denied to directory: " + currentDir.getPath());
        }
    }

    // Check if file matches search criteria
    private static boolean matchesCriteria(File file, SearchCriteria criteria) {
        // Check filename
        if (criteria.fileName != null &&
                !file.getName().matches(criteria.fileName)) {
            return false;
        }

        // Check file size
        long size = file.length();
        if (criteria.minSize != null && size < criteria.minSize) return false;
        if (criteria.maxSize != null && size > criteria.maxSize) return false;

        // Check modification time
        long lastModified = file.lastModified();
        if (criteria.modifiedAfter != null &&
                lastModified < criteria.modifiedAfter) return false;
        if (criteria.modifiedBefore != null &&
                lastModified > criteria.modifiedBefore) return false;

        return true;
    }

    // Test implementation
    public static void main(String[] args) {
        // Create test directory structure
        String testDir = "custom_test_dir";
        createTestDirectory(testDir);

        // Test cases
        runTest("Test 1: Search by filename", () -> {
            SearchCriteria criteria = new SearchCriteria.Builder()
                    .withFileName("test1.txt")
                    .build();
            List<String> results = searchFiles(testDir, criteria);
            return results.size() == 1 && results.get(0).endsWith("test1.txt");
        });

        runTest("Test 2: Search by size", () -> {
            SearchCriteria criteria = new SearchCriteria.Builder()
                    .withMinSize(10L)
                    .withMaxSize(1000L)
                    .build();
            List<String> results = searchFiles(testDir, criteria);
            return results.size() > 0;
        });

        runTest("Test 3: Search by modification time", () -> {
            SearchCriteria criteria = new SearchCriteria.Builder()
                    .modifiedAfter(System.currentTimeMillis() - 3600000) // Last hour
                    .build();
            List<String> results = searchFiles(testDir, criteria);
            return results.size() > 0;
        });

        runTest("Test 4: Search with multiple criteria", () -> {
            SearchCriteria criteria = new SearchCriteria.Builder()
                    .withFileName(".*\\.txt")
                    .withMinSize(5L)
                    .build();
            List<String> results = searchFiles(testDir, criteria);
            return results.size() > 0;
        });

        runTest("Test 5: Search in non-existent directory", () -> {
            SearchCriteria criteria = new SearchCriteria.Builder()
                    .withFileName("test.txt")
                    .build();
            List<String> results = searchFiles("non_existent_dir", criteria);
            return results.isEmpty();
        });

        // Performance test with large directory
        runTest("Test 6: Performance test with large directory", () -> {
            createLargeTestDirectory(testDir + "/large", 1000);
            SearchCriteria criteria = new SearchCriteria.Builder()
                    .withFileName(".*\\.txt")
                    .build();
            long startTime = System.currentTimeMillis();
            List<String> results = searchFiles(testDir + "/large", criteria);
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) + "ms");
            return results.size() > 0;
        });

        // Cleanup
        deleteTestDirectory(testDir);
    }

    // Test helper methods
    private static void createTestDirectory(String testDir) {
        File dir = new File(testDir);
        dir.mkdirs();

        try {
            // Create test files
            createTestFile(new File(dir, "test1.txt"), "Test content 1");
            createTestFile(new File(dir, "test2.txt"), "Test content 2");

            // Create subdirectory with files
            File subDir = new File(dir, "subdir");
            subDir.mkdir();
            createTestFile(new File(subDir, "test3.txt"), "Test content 3");
        } catch (Exception e) {
            System.err.println("Error creating test directory: " + e.getMessage());
        }
    }

    private static void createLargeTestDirectory(String dirPath, int fileCount) {
        File dir = new File(dirPath);
        dir.mkdirs();

        for (int i = 0; i < fileCount; i++) {
            createTestFile(new File(dir, "file" + i + ".txt"), "Content " + i);
            if (i % 10 == 0) {
                File subDir = new File(dir, "subdir" + i);
                subDir.mkdir();
                createTestFile(new File(subDir, "subfile" + i + ".txt"), "Sub Content " + i);
            }
        }
    }

    private static void createTestFile(File file, String content) {
        try {
            java.io.FileWriter writer = new java.io.FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            System.err.println("Error creating test file: " + e.getMessage());
        }
    }

    private static void deleteTestDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteTestDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        dir.delete();
    }

    private static void deleteTestDirectory(String testDir) {
        deleteTestDirectory(new File(testDir));
    }

    private static void runTest(String testName, TestCase testCase) {
        try {
            boolean result = testCase.execute();
            System.out.println(testName + ": " + (result ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println(testName + ": FAIL (Exception: " + e.getMessage() + ")");
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    interface TestCase {
        boolean execute() throws Exception;
    }

    // Search criteria class using Builder pattern
    public static class SearchCriteria {
        String fileName;
        Long minSize;
        Long maxSize;
        Long modifiedAfter;
        Long modifiedBefore;

        private SearchCriteria(Builder builder) {
            this.fileName = builder.fileName;
            this.minSize = builder.minSize;
            this.maxSize = builder.maxSize;
            this.modifiedAfter = builder.modifiedAfter;
            this.modifiedBefore = builder.modifiedBefore;
        }

        public static class Builder {
            private String fileName;
            private Long minSize;
            private Long maxSize;
            private Long modifiedAfter;
            private Long modifiedBefore;

            public Builder withFileName(String fileName) {
                this.fileName = fileName;
                return this;
            }

            public Builder withMinSize(Long minSize) {
                this.minSize = minSize;
                return this;
            }

            public Builder withMaxSize(Long maxSize) {
                this.maxSize = maxSize;
                return this;
            }

            public Builder modifiedAfter(Long after) {
                this.modifiedAfter = after;
                return this;
            }

            public Builder modifiedBefore(Long before) {
                this.modifiedBefore = before;
                return this;
            }

            public SearchCriteria build() {
                return new SearchCriteria(this);
            }
        }
    }
}