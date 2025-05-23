package com.interview.notes.code.year.y2025.may.common.test5;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateFileFinder {
    public static void main(String[] args) {
        // Example usage
        String directoryPath = "C:/TestFolder";  // Change this to your directory path
        Map<String, List<String>> duplicates = findDuplicateFiles(directoryPath);
        printDuplicates(duplicates);
    }

    public static Map<String, List<String>> findDuplicateFiles(String directoryPath) {
        Map<String, List<String>> hashMap = new HashMap<>();
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Invalid directory path!");
            return hashMap;
        }

        findDuplicatesRecursive(directory, hashMap);
        return hashMap;
    }

    private static void findDuplicatesRecursive(File directory, Map<String, List<String>> hashMap) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                findDuplicatesRecursive(file, hashMap);
            } else {
                try {
                    String fileHash = calculateFileHash(file);
                    List<String> fileList = hashMap.getOrDefault(fileHash, new ArrayList<>());
                    fileList.add(file.getAbsolutePath());
                    hashMap.put(fileHash, fileList);
                } catch (Exception e) {
                    System.out.println("Error processing file: " + file.getName());
                }
            }
        }
    }

    private static String calculateFileHash(File file) throws Exception {
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

    private static void printDuplicates(Map<String, List<String>> hashMap) {
        boolean foundDuplicates = false;
        for (Map.Entry<String, List<String>> entry : hashMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                foundDuplicates = true;
                System.out.println("\nDuplicate files found:");
                for (String filePath : entry.getValue()) {
                    System.out.println(filePath);
                }
            }
        }
        if (!foundDuplicates) {
            System.out.println("No duplicate files found.");
        }
    }

    // Simple test method
    public static void test() {
        try {
            // Create test directory structure
            File testDir = new File("testDir");
            testDir.mkdir();

            // Create some test files
            createTestFile(testDir, "file1.txt", "content1");
            createTestFile(testDir, "file2.txt", "content1");  // duplicate
            createTestFile(testDir, "file3.txt", "content2");

            // Create subdirectory with duplicate file
            File subDir = new File(testDir, "subDir");
            subDir.mkdir();
            createTestFile(subDir, "file4.txt", "content1");  // duplicate

            // Find duplicates
            Map<String, List<String>> duplicates = findDuplicateFiles(testDir.getAbsolutePath());
            printDuplicates(duplicates);

            // Cleanup
            deleteDirectory(testDir);

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        }
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
}
