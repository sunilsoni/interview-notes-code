package com.interview.notes.code.year.y2025.may.common.test3;

import java.io.*;
import java.security.MessageDigest;
import java.util.*;

public class DuplicateFileFinderBFS {
    private final Map<String, List<String>> hashMap;

    public DuplicateFileFinderBFS() {
        this.hashMap = new HashMap<>();
    }

    // Test utilities
    private static void createTestFile(File dir, String fileName, String content) throws IOException {
        File file = new File(dir, fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }

    private static void deleteDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        dir.delete();
    }

    private static void createTestStructure() throws IOException {
        // Create test directory structure
        File testDir = new File("testDir");
        testDir.mkdir();

        // Create files in root directory
        createTestFile(testDir, "file1.txt", "content1");
        createTestFile(testDir, "file2.txt", "content1"); // duplicate
        createTestFile(testDir, "file3.txt", "content2");

        // Create subdirectory with duplicate file
        File subDir = new File(testDir, "subDir");
        subDir.mkdir();
        createTestFile(subDir, "file4.txt", "content1"); // duplicate

        // Create deeper structure
        File deepDir = new File(subDir, "deepDir");
        deepDir.mkdir();
        createTestFile(deepDir, "file5.txt", "content2"); // duplicate with file3.txt
    }

    public static void main(String[] args) {
        try {
            // Create test directory structure
            createTestStructure();

            // Initialize finder and search for duplicates
            DuplicateFileFinderBFS finder = new DuplicateFileFinderBFS();
            finder.findDuplicates("testDir");

            // Print results
            System.out.println("Found duplicates:");
            finder.printDuplicates();

            // Clean up test directory
            deleteDirectory(new File("testDir"));

        } catch (IOException e) {
            System.err.println("Error during test: " + e.getMessage());
        }

        // Example with actual directory (uncomment and modify path as needed)
        /*
        DuplicateFileFinderBFS realFinder = new DuplicateFileFinderBFS();
        realFinder.findDuplicates("C:/YourActualDirectory");
        realFinder.printDuplicates();
        */
    }

    public void findDuplicates(String directoryPath) {
        File rootDirectory = new File(directoryPath);
        if (!rootDirectory.exists() || !rootDirectory.isDirectory()) {
            System.out.println("Invalid directory path!");
            return;
        }

        Queue<File> directoryQueue = new LinkedList<>();
        directoryQueue.offer(rootDirectory);

        while (!directoryQueue.isEmpty()) {
            File currentDirectory = directoryQueue.poll();
            File[] files = currentDirectory.listFiles();

            if (files == null) continue;

            for (File file : files) {
                if (file.isDirectory()) {
                    directoryQueue.offer(file);
                } else {
                    try {
                        String fileHash = calculateFileHash(file);
                        hashMap.computeIfAbsent(fileHash, k -> new ArrayList<>())
                                .add(file.getAbsolutePath());
                    } catch (Exception e) {
                        System.err.println("Error processing file: " + file.getName() +
                                " - " + e.getMessage());
                    }
                }
            }
        }
    }

    private String calculateFileHash(File file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            byte[] buffer = new byte[8192];
            int count;
            while ((count = bis.read(buffer)) != -1) {
                md.update(buffer, 0, count);
            }
        }

        byte[] hash = md.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public void printDuplicates() {
        boolean foundDuplicates = false;
        for (Map.Entry<String, List<String>> entry : hashMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                foundDuplicates = true;
                System.out.println("\nDuplicate files (Hash: " + entry.getKey() + "):");
                for (String filePath : entry.getValue()) {
                    System.out.println("- " + filePath);
                }
            }
        }
        if (!foundDuplicates) {
            System.out.println("No duplicate files found.");
        }
    }

    public Map<String, List<String>> getResults() {
        return new HashMap<>(hashMap);
    }

    public void clearResults() {
        hashMap.clear();
    }
}
