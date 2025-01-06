package com.interview.notes.code.year.y2024.dec24.amazon.test7;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/*
 Say you have a host with millions of files. How do you find all the duplicate files?
- Write code to process duplicate file names
Assumptions:
- filenames aren't unique but path is different for all
- sizes varying. Some in Gb others just a few bytes
- Aim for faster processing
 */
public class FindDuplicateFiles1 {

    /**
     * Finds duplicates based on filename and size, then verifies content.
     * Returns a list of duplicate groups where each group contains files with identical content.
     */
    public static List<List<FileData>> findDuplicates(List<FileData> files) {
        // Initial grouping by filename and size
        Map<FileKey, List<FileData>> initialGroup = new HashMap<>();
        for (FileData f : files) {
            FileKey key = new FileKey(f.filename, f.size);
            initialGroup.computeIfAbsent(key, k -> new ArrayList<>()).add(f);
        }

        // Prepare to group by content hash
        List<List<FileData>> duplicates = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Map<String, List<FileData>>>> futures = new ArrayList<>();

        for (Map.Entry<FileKey, List<FileData>> entry : initialGroup.entrySet()) {
            List<FileData> group = entry.getValue();
            if (group.size() < 2) continue; // No duplicates possible

            // Submit a task to process each group
            futures.add(executor.submit(() -> {
                Map<String, List<FileData>> hashGroup = new HashMap<>();
                for (FileData file : group) {
                    try {
                        String hash = computeFileHash(file.path);
                        hashGroup.computeIfAbsent(hash, k -> new ArrayList<>()).add(file);
                    } catch (IOException | NoSuchAlgorithmException e) {
                        System.err.println("Error processing file: " + file.path + ". Skipping.");
                    }
                }
                return hashGroup;
            }));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS); // Adjust timeout as needed
        } catch (InterruptedException e) {
            System.err.println("Hashing process was interrupted.");
        }

        // Collect results from all futures
        for (Future<Map<String, List<FileData>>> future : futures) {
            try {
                Map<String, List<FileData>> hashGroup = future.get();
                for (List<FileData> dupGroup : hashGroup.values()) {
                    if (dupGroup.size() > 1) {
                        duplicates.add(dupGroup);
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error retrieving hash group results.");
            }
        }

        return duplicates;
    }

    /**
     * Computes the SHA-256 hash of a file's content.
     *
     * @param filePath Path to the file.
     * @return Hexadecimal string representation of the hash.
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static String computeFileHash(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        Path path = Paths.get(filePath);
        try (FileInputStream fis = new FileInputStream(path.toFile())) {
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

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: No files
        testCases.add(new TestCase("No Files",
                new ArrayList<>(),
                Collections.emptyList()
        ));

        // Test Case 2: All unique
        testCases.add(new TestCase("All Unique",
                Arrays.asList(
                        new FileData("a.txt", "/path/a.txt", 100),
                        new FileData("b.txt", "/path/b.txt", 200),
                        new FileData("c.txt", "/path/c.txt", 300)
                ),
                Collections.emptyList()
        ));

        // Test Case 3: Some duplicates by filename and size with same content
        testCases.add(new TestCase("Some Duplicates (Same Content)",
                Arrays.asList(
                        new FileData("a.txt", "/path1/a.txt", 100),
                        new FileData("a.txt", "/path2/a.txt", 100),
                        new FileData("b.txt", "/path/b.txt", 200)
                ),
                Arrays.asList(
                        Arrays.asList(
                                new FileData("a.txt", "/path1/a.txt", 100),
                                new FileData("a.txt", "/path2/a.txt", 100)
                        )
                )
        ));

        // Test Case 4: Same filename, different size (not duplicates)
        testCases.add(new TestCase("Same Name Different Size",
                Arrays.asList(
                        new FileData("a.txt", "/path1/a.txt", 100),
                        new FileData("a.txt", "/path2/a.txt", 200)
                ),
                Collections.emptyList()
        ));

        // Test Case 5: Different filenames, same size and content
        testCases.add(new TestCase("Different Names Same Content",
                Arrays.asList(
                        new FileData("a.txt", "/path1/a.txt", 100),
                        new FileData("b.txt", "/path2/b.txt", 100)
                ),
                Collections.emptyList() // Since filenames differ, they are not grouped
        ));

        // Test Case 6: Files with same name and size but different content
        testCases.add(new TestCase("Same Name and Size Different Content",
                Arrays.asList(
                        new FileData("a.txt", "/path1/a.txt", 100),
                        new FileData("a.txt", "/path2/a.txt", 100)
                ),
                Collections.emptyList() // Assuming different content, but based on your logic, they are duplicates
        ));

        // Test Case 7: Large Data Test (Conceptual)
        testCases.add(new TestCase("Large Data Conceptual Test",
                generateLargeTestData(1000000), // Generate 1 million dummy files
                Collections.emptyList() // Not verifying actual duplicates here
        ));

        // Execute test cases
        int passed = 0;
        for (TestCase testCase : testCases) {
            System.out.println("Running Test Case: " + testCase.name);
            List<List<FileData>> actualDuplicates = findDuplicates(testCase.inputFiles);
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
    public static boolean compareDuplicates(List<List<FileData>> actual, List<List<FileData>> expected) {
        if (actual.size() != expected.size()) return false;

        // Convert lists to sets of sets for unordered comparison
        Set<Set<String>> actualSet = new HashSet<>();
        for (List<FileData> group : actual) {
            Set<String> groupSet = group.stream()
                    .map(f -> f.path)
                    .collect(Collectors.toSet());
            actualSet.add(groupSet);
        }

        Set<Set<String>> expectedSet = new HashSet<>();
        for (List<FileData> group : expected) {
            Set<String> groupSet = group.stream()
                    .map(f -> f.path)
                    .collect(Collectors.toSet());
            expectedSet.add(groupSet);
        }

        return actualSet.equals(expectedSet);
    }

    /**
     * Generates a large list of dummy FileData for testing purposes.
     * All files have unique names and sizes to simulate large data without actual duplicates.
     *
     * @param count Number of dummy files to generate.
     * @return List of FileData objects.
     */
    public static List<FileData> generateLargeTestData(int count) {
        List<FileData> largeTestData = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            largeTestData.add(new FileData("file" + i + ".txt", "/path/file" + i + ".txt", i));
        }
        return largeTestData;
    }

    // Simple data structure to hold file metadata
    static class FileData {
        String filename;
        String path;
        long size;

        FileData(String filename, String path, long size) {
            this.filename = filename;
            this.path = path;
            this.size = size;
        }

        @Override
        public String toString() {
            return "FileData{filename='" + filename + "', path='" + path + "', size=" + size + "}";
        }
    }

    // A key that combines filename and size for initial grouping
    static class FileKey {
        String filename;
        long size;

        FileKey(String filename, long size) {
            this.filename = filename;
            this.size = size;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof FileKey)) return false;
            FileKey other = (FileKey) obj;
            return this.size == other.size && Objects.equals(this.filename, other.filename);
        }

        @Override
        public int hashCode() {
            return Objects.hash(filename, size);
        }
    }

    /**
     * Inner class to represent a test case.
     */
    static class TestCase {
        String name;
        List<FileData> inputFiles;
        List<List<FileData>> expectedDuplicates;

        TestCase(String name, List<FileData> inputFiles, List<List<FileData>> expectedDuplicates) {
            this.name = name;
            this.inputFiles = inputFiles;
            this.expectedDuplicates = expectedDuplicates;
        }
    }
}
