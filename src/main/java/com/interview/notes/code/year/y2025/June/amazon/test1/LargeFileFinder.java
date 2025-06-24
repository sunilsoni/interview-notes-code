package com.interview.notes.code.year.y2025.June.amazon.test1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
**Question:**

The Unix `find` command allows you to search for files under a given directory. You can specify criteria for files you are interested in.

Imagine that you need to write code in a high-level language like Java that performs a similar task to the `find` command.

**Task:**
Write a Java program to **find all files over 5 MB somewhere under a given directory**.

 */
public class LargeFileFinder {

    // 5 MB expressed in bytes (5 * 1024 * 1024)
    private static final long SIZE_THRESHOLD = 5L * 1024 * 1024;

    /**
     * Recursively walks the file tree under rootDirPath and returns
     * all regular files whose size exceeds SIZE_THRESHOLD.
     *
     * @param rootDirPath The directory to search under.
     * @return List of Paths for files > 5 MB.
     * @throws IOException If an I/O error occurs during traversal.
     */
    public static List<Path> findLargeFiles(String rootDirPath) throws IOException {
        // Convert the input string into a Path object
        Path startPath = Paths.get(rootDirPath);

        // Walk the entire tree (unlimited depth), auto-closing the stream
        try (Stream<Path> stream = Files.walk(startPath)) {
            return stream
                    // Only consider normal files, not directories or special files
                    .filter(Files::isRegularFile)
                    // Keep only those whose size is greater than our threshold
                    .filter(path -> {
                        try {
                            return Files.size(path) > SIZE_THRESHOLD;
                        } catch (IOException e) {
                            // If we can’t read the size, skip this file
                            return false;
                        }
                    })
                    // Collect the results into a List<Path>
                    .collect(Collectors.toList());
        }
    }

    /**
     * If you pass a directory as an argument, it lists all >5 MB files.
     * If you pass no arguments, it runs a self-checking test suite.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            String dir = args[0];
            try {
                List<Path> result = findLargeFiles(dir);
                System.out.println("Files larger than 5 MB under " + dir + ":");
                result.forEach(System.out::println);
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            runTests();  // simple PASS/FAIL harness
        }
    }

    /**
     * A very basic test harness that:
     * 1. Creates a temp directory.
     * 2. Puts one small file (1 KB) and one large file (5 MB + 1 KB) in it.
     * 3. Verifies only the large file is found.
     */
    private static void runTests() {
        System.out.println("Running tests…");
        try {
            Path tempDir = Files.createTempDirectory("testDir");
            Path smallFile = tempDir.resolve("small.txt");
            Files.write(smallFile, new byte[1024]);  // 1 KB

            Path largeFile = tempDir.resolve("large.bin");
            Files.write(largeFile, new byte[(int) (SIZE_THRESHOLD + 1024)]);  // 5 MB + 1 KB

            List<Path> found = findLargeFiles(tempDir.toString());
            if (found.size() == 1 && found.get(0).equals(largeFile)) {
                System.out.println("PASS: Only large file detected");
            } else {
                System.out.println("FAIL: Expected 1 file, found " + found.size());
            }

            // Cleanup
            Files.deleteIfExists(smallFile);
            Files.deleteIfExists(largeFile);
            Files.deleteIfExists(tempDir);
        } catch (IOException e) {
            System.err.println("Test error: " + e.getMessage());
        }
    }
}