package com.interview.notes.code.months.nov24.amazon.test10;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/*

Implement a utility for searching files - Create a function that searches for a file with specific name, within a given directory and its subdirectories.
The function should return the full path of the file if found, or indicate that the file doesn't exist.
Expand it to different requirements such as size, modified date, etc

 */
public class FileSearchUtility {
    
    // Search criteria class to hold various search parameters
    public static class SearchCriteria {
        String fileName;
        Long minSize;
        Long maxSize;
        LocalDateTime modifiedAfter;
        LocalDateTime modifiedBefore;
        
        private SearchCriteria(Builder builder) {
            this.fileName = builder.fileName;
            this.minSize = builder.minSize;
            this.maxSize = builder.maxSize;
            this.modifiedAfter = builder.modifiedAfter;
            this.modifiedBefore = builder.modifiedBefore;
        }
        
        // Builder pattern for search criteria
        public static class Builder {
            private String fileName;
            private Long minSize;
            private Long maxSize;
            private LocalDateTime modifiedAfter;
            private LocalDateTime modifiedBefore;
            
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
            
            public Builder modifiedAfter(LocalDateTime after) {
                this.modifiedAfter = after;
                return this;
            }
            
            public Builder modifiedBefore(LocalDateTime before) {
                this.modifiedBefore = before;
                return this;
            }
            
            public SearchCriteria build() {
                return new SearchCriteria(this);
            }
        }
    }
    
    // Main search method
    public static List<String> searchFiles(String rootPath, SearchCriteria criteria) {
        List<String> results = new ArrayList<>();
        try {
            Files.walkFileTree(Paths.get(rootPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (matchesCriteria(file, criteria)) {
                        results.add(file.toAbsolutePath().toString());
                    }
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println("Error searching files: " + e.getMessage());
        }
        return results;
    }
    
    // Helper method to check if file matches criteria
    private static boolean matchesCriteria(Path file, SearchCriteria criteria) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
            
            // Check filename
            if (criteria.fileName != null && 
                !file.getFileName().toString().matches(criteria.fileName)) {
                return false;
            }
            
            // Check file size
            long size = attrs.size();
            if (criteria.minSize != null && size < criteria.minSize) return false;
            if (criteria.maxSize != null && size > criteria.maxSize) return false;
            
            // Check modification time
            LocalDateTime modifiedTime = LocalDateTime.ofInstant(
                attrs.lastModifiedTime().toInstant(), ZoneId.systemDefault());
            if (criteria.modifiedAfter != null && 
                modifiedTime.isBefore(criteria.modifiedAfter)) return false;
            if (criteria.modifiedBefore != null && 
                modifiedTime.isAfter(criteria.modifiedBefore)) return false;
            
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Main method with tests
    public static void main(String[] args) {
        // Create test directory structure
        String testDir = "test_dir";
        createTestDirectory(testDir);
        
        // Test cases
        runTest("Test 1: Simple file search by name", () -> {
            SearchCriteria criteria = new SearchCriteria.Builder()
                .withFileName("test1.txt")
                .build();
            List<String> results = searchFiles(testDir, criteria);
            return results.size() == 1 && results.get(0).endsWith("test1.txt");
        });
        
        runTest("Test 2: Search by size range", () -> {
            SearchCriteria criteria = new SearchCriteria.Builder()
                .withMinSize(100L)
                .withMaxSize(1000L)
                .build();
            List<String> results = searchFiles(testDir, criteria);
            return results.size() > 0;
        });
        
        runTest("Test 3: Search by modification date", () -> {
            SearchCriteria criteria = new SearchCriteria.Builder()
                .modifiedAfter(LocalDateTime.now().minusDays(1))
                .build();
            List<String> results = searchFiles(testDir, criteria);
            return results.size() > 0;
        });
        
        // Cleanup test directory
        deleteTestDirectory(testDir);
    }
    
    // Test helper methods
    private static void runTest(String testName, TestCase testCase) {
        try {
            boolean result = testCase.execute();
            System.out.println(testName + ": " + (result ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println(testName + ": FAIL (Exception: " + e.getMessage() + ")");
        }
    }
    
    @FunctionalInterface
    interface TestCase {
        boolean execute() throws Exception;
    }
    
    // Test setup helpers
    private static void createTestDirectory(String testDir) {
        try {
            Files.createDirectories(Paths.get(testDir));
            Files.write(Paths.get(testDir, "test1.txt"), 
                       "Test content".getBytes());
            Files.write(Paths.get(testDir, "test2.txt"), 
                       new byte[500]);
            Files.createDirectories(Paths.get(testDir, "subdir"));
            Files.write(Paths.get(testDir, "subdir", "test3.txt"), 
                       "Subdir test".getBytes());
        } catch (IOException e) {
            System.err.println("Error creating test directory: " + e.getMessage());
        }
    }
    
    private static void deleteTestDirectory(String testDir) {
        try {
            Files.walk(Paths.get(testDir))
                 .sorted(Comparator.reverseOrder())
                 .map(Path::toFile)
                 .forEach(File::delete);
        } catch (IOException e) {
            System.err.println("Error deleting test directory: " + e.getMessage());
        }
    }
}