package com.interview.notes.code.year.y2025.may.common.test4;

import java.io.*;
import java.security.MessageDigest;
import java.util.*;

public class DuplicateFileFinderBFS {
    private final Map<String, List<String>> hashMap;

    public DuplicateFileFinderBFS() {
        this.hashMap = new HashMap<>();
    }

    public static void main(String[] args) {
        String directoryPath = "C:/TestFolder"; // Change this to your directory path
        DuplicateFileFinderBFS finder = new DuplicateFileFinderBFS();
        finder.findDuplicates(directoryPath);
        finder.printDuplicates();
    }

    public void findDuplicates(String directoryPath) {
        File rootDirectory = new File(directoryPath);
        if (!rootDirectory.exists() || !rootDirectory.isDirectory()) {
            System.out.println("Invalid directory path!");
            return;
        }

        // Using Queue for BFS
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
                    processFile(file);
                }
            }
        }
    }

    private void processFile(File file) {
        try {
            String fileHash = calculateFileHash(file);
            hashMap.computeIfAbsent(fileHash, k -> new ArrayList<>())
                   .add(file.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error processing file: " + file.getName() + " - " + e.getMessage());
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

    // Test method to verify functionality
    public static void test() {
        try {
            // Create test directory structure
            File testDir = new File("testDir");
            testDir.mkdir();

            // Create test files
            createTestFiles(testDir);

            // Find duplicates
            DuplicateFileFinderBFS finder = new DuplicateFileFinderBFS();
            finder.findDuplicates(testDir.getAbsolutePath());
            finder.printDuplicates();

            // Cleanup
            deleteDirectory(testDir);

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        }
    }

    private static void createTestFiles(File testDir) throws IOException {
        // Create files in root directory
        createTestFile(testDir, "file1.txt", "content1");
        createTestFile(testDir, "file2.txt", "content1"); // duplicate
        createTestFile(testDir, "file3.txt", "content2");

        // Create subdirectory with duplicate file
        File subDir = new File(testDir, "subDir");
        subDir.mkdir();
        createTestFile(subDir, "file4.txt", "content1"); // duplicate

        // Create another level of subdirectory
        File subSubDir = new File(subDir, "subSubDir");
        subSubDir.mkdir();
        createTestFile(subSubDir, "file5.txt", "content1"); // duplicate
    }

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

    // Method to get results
    public Map<String, List<String>> getResults() {
        return new HashMap<>(hashMap);
    }

    // Method to clear results
    public void clearResults() {
        hashMap.clear();
    }
}
