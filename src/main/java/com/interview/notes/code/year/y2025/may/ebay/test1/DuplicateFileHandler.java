package com.interview.notes.code.year.y2025.may.ebay.test1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.*;

public class DuplicateFileHandler {
    private static final int BUFFER_SIZE = 8192;
    private final Map<String, List<Path>> hashMap;

    public DuplicateFileHandler() {
        this.hashMap = new HashMap<>();
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    // Example usage and testing
    public static void main(String[] args) {
        // Create test directory structure
        try {
            createTestStructure();

            DuplicateFileHandler handler = new DuplicateFileHandler();

            // Test BFS
            System.out.println("Testing BFS:");
            handler.findDuplicatesBFS("testDir");
            handler.printDuplicates();

            // Remove duplicates (keep oldest files)
            System.out.println("\nRemoving duplicates (keeping oldest files)...");
            handler.removeDuplicates(true);

            // Clear and test DFS
            handler.clearResults();
            System.out.println("\nTesting DFS:");
            handler.findDuplicatesDFS("testDir");
            handler.printDuplicates();

            // Clean up test directory
            deleteDirectory(Paths.get("testDir"));

        } catch (Exception e) {
            System.err.println("Test error: " + e.getMessage());
        }
    }

    // Test utilities
    private static void createTestStructure() throws IOException {
        Path testDir = Files.createDirectories(Paths.get("testDir"));

        // Create test files
        Files.write(testDir.resolve("file1.txt"), "content1".getBytes());
        Files.write(testDir.resolve("file2.txt"), "content1".getBytes());
        Files.write(testDir.resolve("file3.txt"), "content2".getBytes());

        Path subDir = Files.createDirectories(testDir.resolve("subDir"));
        Files.write(subDir.resolve("file4.txt"), "content1".getBytes());

        Path deepDir = Files.createDirectories(subDir.resolve("deepDir"));
        Files.write(deepDir.resolve("file5.txt"), "content2".getBytes());
    }

    private static void deleteDirectory(Path directory) throws IOException {
        Files.walk(directory)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        System.err.println("Failed to delete: " + path);
                    }
                });
    }

    // BFS implementation
    public void findDuplicatesBFS(String directoryPath) {
        try {
            Path rootPath = Paths.get(directoryPath);
            if (!Files.isDirectory(rootPath)) {
                throw new IllegalArgumentException("Invalid directory path: " + directoryPath);
            }

            Queue<Path> dirQueue = new LinkedList<>();
            dirQueue.offer(rootPath);

            while (!dirQueue.isEmpty()) {
                Path currentDir = dirQueue.poll();
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDir)) {
                    for (Path path : stream) {
                        if (Files.isDirectory(path)) {
                            dirQueue.offer(path);
                        } else {
                            processFile(path);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in BFS traversal: " + e.getMessage());
        }
    }

    // DFS implementation
    public void findDuplicatesDFS(String directoryPath) {
        try {
            Path rootPath = Paths.get(directoryPath);
            if (!Files.isDirectory(rootPath)) {
                throw new IllegalArgumentException("Invalid directory path: " + directoryPath);
            }
            dfsTraversal(rootPath);
        } catch (Exception e) {
            System.err.println("Error in DFS traversal: " + e.getMessage());
        }
    }

    private void dfsTraversal(Path directory) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path path : stream) {
                if (Files.isDirectory(path)) {
                    dfsTraversal(path);
                } else {
                    processFile(path);
                }
            }
        }
    }

    private void processFile(Path file) {
        try {
            String fileHash = calculateFileHash(file);
            hashMap.computeIfAbsent(fileHash, k -> new ArrayList<>()).add(file);
        } catch (Exception e) {
            System.err.println("Error processing file: " + file + " - " + e.getMessage());
        }
    }

    private String calculateFileHash(Path file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (InputStream is = new BufferedInputStream(Files.newInputStream(file))) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int read;
            while ((read = is.read(buffer)) != -1) {
                md.update(buffer, 0, read);
            }
        }
        return bytesToHex(md.digest());
    }

    public void removeDuplicates(boolean keepOldest) {
        for (List<Path> duplicates : hashMap.values()) {
            if (duplicates.size() > 1) {
                Path fileToKeep = keepOldest ?
                        findOldestFile(duplicates) :
                        findNewestFile(duplicates);

                deleteDuplicatesExcept(duplicates, fileToKeep);
            }
        }
    }

    private Path findOldestFile(List<Path> files) {
        return files.stream()
                .min(Comparator.comparing(path -> {
                    try {
                        return Files.getLastModifiedTime(path).toMillis();
                    } catch (IOException e) {
                        return Long.MAX_VALUE;
                    }
                }))
                .orElse(files.get(0));
    }

    private Path findNewestFile(List<Path> files) {
        return files.stream()
                .max(Comparator.comparing(path -> {
                    try {
                        return Files.getLastModifiedTime(path).toMillis();
                    } catch (IOException e) {
                        return Long.MIN_VALUE;
                    }
                }))
                .orElse(files.get(0));
    }

    private void deleteDuplicatesExcept(List<Path> files, Path fileToKeep) {
        files.stream()
                .filter(file -> !file.equals(fileToKeep))
                .forEach(file -> {
                    try {
                        Files.delete(file);
                        System.out.println("Deleted duplicate: " + file);
                    } catch (IOException e) {
                        System.err.println("Failed to delete: " + file + " - " + e.getMessage());
                    }
                });
    }

    public void printDuplicates() {
        boolean found = false;
        for (Map.Entry<String, List<Path>> entry : hashMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                found = true;
                System.out.println("\nDuplicate files (Hash: " + entry.getKey() + "):");
                entry.getValue().forEach(path -> System.out.println("- " + path));
            }
        }
        if (!found) {
            System.out.println("No duplicates found.");
        }
    }

    public void clearResults() {
        hashMap.clear();
    }
}
