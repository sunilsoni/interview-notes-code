package com.interview.notes.code.months.dec24.Amazon.test7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/*
Say you have a host with millions of files. How do you find all the duplicate files?
- Write code to process duplicate file names
Assumptions:
- filenames aren't unique but path is different for all
- sizes varying. Some in Gb others just a few bytes
- Aim for faster processing
 */
public class FindDuplicateFilesByContent {

    /**
     * Compute hash of the file content.
     * This example uses SHA-256. For production, handle exceptions properly.
     */
    static String computeHash(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (var is = Files.newInputStream(Paths.get(filePath))) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }
        byte[] hashBytes = digest.digest();
        // Convert to hex string for readability
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Find duplicates by file content:
     * 1. Group by size
     * 2. For groups with more than one file, compute hashes
     * 3. Group by hash and identify duplicates
     */
    public static List<List<FileData>> findDuplicatesByContent(List<FileData> files) throws IOException, NoSuchAlgorithmException {
        // Group by file size
        Map<Long, List<FileData>> bySize = new HashMap<>();
        for (FileData f : files) {
            bySize.computeIfAbsent(f.size, k -> new ArrayList<>()).add(f);
        }

        // For each size group, if there's more than one file, compare by hash
        List<List<FileData>> duplicates = new ArrayList<>();
        for (Map.Entry<Long, List<FileData>> entry : bySize.entrySet()) {
            List<FileData> group = entry.getValue();
            if (group.size() > 1) {
                // Compute hash and group
                Map<String, List<FileData>> byHash = new HashMap<>();
                for (FileData f : group) {
                    String hash = computeHash(f.path);
                    byHash.computeIfAbsent(hash, h -> new ArrayList<>()).add(f);
                }

                // Extract duplicates from hash groups
                for (List<FileData> sameHashFiles : byHash.values()) {
                    if (sameHashFiles.size() > 1) {
                        duplicates.add(sameHashFiles);
                    }
                }
            }
        }
        return duplicates;
    }

    public static void main(String[] args) throws Exception {
        // Example test:
        // Assume these files exist and have known content
        List<FileData> testFiles = Arrays.asList(
                new FileData("/path/to/file1.txt", 100),
                new FileData("/path/to/file2.txt", 100),
                new FileData("/path/to/file3.txt", 200),
                new FileData("/path/to/file4.txt", 100) // Suppose file1.txt and file4.txt have identical content
        );

        List<List<FileData>> duplicates = findDuplicatesByContent(testFiles);

        // Simple PASS/FAIL check:
        // If we know beforehand that file1 and file4 are duplicates:
        boolean foundExpected = duplicates.stream()
                .anyMatch(group -> group.stream().anyMatch(f -> f.path.endsWith("file1.txt")) &&
                        group.stream().anyMatch(f -> f.path.endsWith("file4.txt")));

        System.out.println("Duplicates Found: " + duplicates);
        System.out.println("Test (Expected duplicates): " + (foundExpected ? "PASS" : "FAIL"));
    }

    static class FileData {
        String path;
        long size;

        FileData(String path, long size) {
            this.path = path;
            this.size = size;
        }

        @Override
        public String toString() {
            return "FileData{path='" + path + "', size=" + size + "}";
        }
    }
}
