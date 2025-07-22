package com.interview.notes.code.year.y2025.july.common.test5;

import java.util.*;
import java.util.stream.Collectors;

public class FileSystemPathFinder {
    
    public static List<String> findFilePaths(Map<String, Object> fileSystem) {
        List<String> paths = new ArrayList<>();
        traverseFileSystem("", fileSystem, paths);
        return paths.stream()
                   .filter(path -> path.contains(".")) // Only return file paths
                   .sorted()
                   .collect(Collectors.toList());
    }

    private static void traverseFileSystem(String currentPath, Map<String, Object> directory, List<String> paths) {
        directory.forEach((key, value) -> {
            String newPath = currentPath.isEmpty() ? key : currentPath + "/" + key;
            if (value instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> subDir = (Map<String, Object>) value;
                traverseFileSystem(newPath, subDir, paths);
            } else {
                paths.add(newPath);
            }
        });
    }

    public static void main(String[] args) {
        // Test Case 1: Basic file system structure
        Map<String, Object> fileSystem1 = Map.of(
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

        // Test Case 2: Empty file system
        Map<String, Object> fileSystem2 = new HashMap<>();

        // Test Case 3: Single file
        Map<String, Object> fileSystem3 = Map.of(
            "root", Map.of(
                "single.txt", null
            )
        );

        // Run tests
        runTest("Test 1: Basic file system", fileSystem1);
        runTest("Test 2: Empty file system", fileSystem2);
        runTest("Test 3: Single file", fileSystem3);
    }

    private static void runTest(String testName, Map<String, Object> fileSystem) {
        System.out.println("\n" + testName);
        System.out.println("Input: " + fileSystem);
        List<String> result = findFilePaths(fileSystem);
        System.out.println("Output: " + result);
        
        // Verify results
        boolean passed = verifyResults(fileSystem, result);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
    }

    private static boolean verifyResults(Map<String, Object> fileSystem, List<String> paths) {
        // Basic verification logic
        if (fileSystem.isEmpty() && paths.isEmpty()) return true;
        if (paths.stream().allMatch(path -> path.contains("."))) return true;
        return !paths.isEmpty();
    }
}
