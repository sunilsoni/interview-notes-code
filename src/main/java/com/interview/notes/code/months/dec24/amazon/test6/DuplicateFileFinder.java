package com.interview.notes.code.months.dec24.amazon.test6;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DuplicateFileFinder {

    /**
     * Groups files by their sizes.
     *
     * @param files List of file paths.
     * @return Map where the key is file size and the value is a list of file paths with that size.
     */
    public static Map<Long, List<Path>> groupFilesBySize(List<Path> files) {
        Map<Long, List<Path>> sizeMap = new HashMap<>();
        for (Path file : files) {
            try {
                long size = Files.size(file);
                sizeMap.computeIfAbsent(size, k -> new ArrayList<>()).add(file);
            } catch (IOException e) {
                System.err.println("Unable to access file: " + file + ". Skipping.");
            }
        }
        return sizeMap;
    }

    /**
     * Computes the hash of a file's content using SHA-256.
     *
     * @param file Path to the file.
     * @return Hexadecimal string of the file's hash.
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static String computeFileHash(Path file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (FileInputStream fis = new FileInputStream(file.toFile())) {
            byte[] byteArray = new byte[1024 * 1024]; // 1MB buffer
            int bytesCount;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
        }
        byte[] bytes = digest.digest();
        // Convert to hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Groups files by their hash values.
     *
     * @param files List of file paths with the same size.
     * @return Map where the key is the file hash and the value is a list of file paths with that hash.
     */
    public static Map<String, List<Path>> groupFilesByHash(List<Path> files) {
        Map<String, List<Path>> hashMap = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (Path file : files) {
            executor.submit(() -> {
                try {
                    String hash = computeFileHash(file);
                    hashMap.computeIfAbsent(hash, k -> Collections.synchronizedList(new ArrayList<>())).add(file);
                } catch (IOException | NoSuchAlgorithmException e) {
                    System.err.println("Error hashing file: " + file + ". Skipping.");
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS); // Adjust as needed
        } catch (InterruptedException e) {
            System.err.println("Hashing interrupted.");
        }

        return hashMap;
    }

    /**
     * Finds duplicate files in the given list of files.
     *
     * @param files List of file paths.
     * @return List of lists, where each sublist contains paths to duplicate files.
     */
    public static List<List<Path>> findDuplicateFiles(List<Path> files) {
        List<List<Path>> duplicates = new ArrayList<>();
        Map<Long, List<Path>> sizeMap = groupFilesBySize(files);

        for (Map.Entry<Long, List<Path>> entry : sizeMap.entrySet()) {
            List<Path> sameSizeFiles = entry.getValue();
            if (sameSizeFiles.size() < 2) continue; // No duplicates possible

            Map<String, List<Path>> hashMap = groupFilesByHash(sameSizeFiles);
            for (List<Path> dupList : hashMap.values()) {
                if (dupList.size() > 1) {
                    duplicates.add(dupList);
                }
            }
        }

        return duplicates;
    }

    /**
     * Main method for testing the DuplicateFileFinder.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: No duplicates
        testCases.add(new TestCase("No Duplicates",
                Arrays.asList(
                        Paths.get("test_files/file1.txt"),
                        Paths.get("test_files/file2.txt"),
                        Paths.get("test_files/file3.txt")
                ),
                Collections.emptyList()
        ));

        // Test Case 2: Some duplicates
        testCases.add(new TestCase("Some Duplicates",
                Arrays.asList(
                        Paths.get("test_files/file1.txt"),
                        Paths.get("test_files/file2.txt"),
                        Paths.get("test_files/file1_copy.txt")
                ),
                Arrays.asList(
                        Arrays.asList(Paths.get("test_files/file1.txt"), Paths.get("test_files/file1_copy.txt"))
                )
        ));

        // Test Case 3: All duplicates
        testCases.add(new TestCase("All Duplicates",
                Arrays.asList(
                        Paths.get("test_files/file1.txt"),
                        Paths.get("test_files/file1_copy1.txt"),
                        Paths.get("test_files/file1_copy2.txt")
                ),
                Arrays.asList(
                        Arrays.asList(
                                Paths.get("test_files/file1.txt"),
                                Paths.get("test_files/file1_copy1.txt"),
                                Paths.get("test_files/file1_copy2.txt")
                        )
                )
        ));

        // Test Case 4: Edge Case - Empty Files
        testCases.add(new TestCase("Empty Files",
                Arrays.asList(
                        Paths.get("test_files/empty1.txt"),
                        Paths.get("test_files/empty2.txt"),
                        Paths.get("test_files/non_empty.txt")
                ),
                Arrays.asList(
                        Arrays.asList(
                                Paths.get("test_files/empty1.txt"),
                                Paths.get("test_files/empty2.txt")
                        )
                )
        ));

        // Test Case 5: Large Files (Assuming they exist and are duplicates)
        testCases.add(new TestCase("Large Duplicate Files",
                Arrays.asList(
                        Paths.get("test_files/large1.bin"),
                        Paths.get("test_files/large2.bin"),
                        Paths.get("test_files/large1_copy.bin")
                ),
                Arrays.asList(
                        Arrays.asList(
                                Paths.get("test_files/large1.bin"),
                                Paths.get("test_files/large1_copy.bin")
                        )
                )
        ));

        // Execute test cases
        int passed = 0;
        for (TestCase testCase : testCases) {
            System.out.println("Running Test Case: " + testCase.name);
            List<List<Path>> actualDuplicates = findDuplicateFiles(testCase.inputFiles);
            boolean result = compareDuplicates(actualDuplicates, testCase.expectedDuplicates);
            if (result) {
                System.out.println("PASS\n");
                passed++;
            } else {
                System.out.println("FAIL\n");
            }
        }

        System.out.println("Test Results: " + passed + "/" + testCases.size() + " Passed.");
    }

    /**
     * Compares the actual duplicates with the expected duplicates.
     *
     * @param actual   Actual duplicates found.
     * @param expected Expected duplicates.
     * @return True if they match, else false.
     */
    public static boolean compareDuplicates(List<List<Path>> actual, List<List<Path>> expected) {
        if (actual.size() != expected.size()) return false;

        // Convert lists to sets of sets for unordered comparison
        Set<Set<Path>> actualSet = new HashSet<>();
        for (List<Path> group : actual) {
            actualSet.add(new HashSet<>(group));
        }

        Set<Set<Path>> expectedSet = new HashSet<>();
        for (List<Path> group : expected) {
            expectedSet.add(new HashSet<>(group));
        }

        return actualSet.equals(expectedSet);
    }

    /**
     * Inner class to represent a test case.
     */
    static class TestCase {
        String name;
        List<Path> inputFiles;
        List<List<Path>> expectedDuplicates;

        TestCase(String name, List<Path> inputFiles, List<List<Path>> expectedDuplicates) {
            this.name = name;
            this.inputFiles = inputFiles;
            this.expectedDuplicates = expectedDuplicates;
        }
    }
}
