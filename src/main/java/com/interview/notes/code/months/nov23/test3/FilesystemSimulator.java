package com.interview.notes.code.months.nov23.test3;

import java.util.HashMap;
import java.util.HashSet;

public class FilesystemSimulator {

    public static String solution(String[] commands) {
        HashMap<String, HashSet<String>> buckets = new HashMap<>();
        String currentBucket = "";

        for (String command : commands) {
            String[] parts = command.split(" ");
            String action = parts[0];

            if ("goto".equals(action)) {
                currentBucket = parts[1];
                // Ensure the bucket exists in our map
                buckets.putIfAbsent(currentBucket, new HashSet<>());
            } else if ("create".equals(action) && !currentBucket.isEmpty()) {
                String fileName = parts[1];
                // Add the file to the current bucket if it doesn't already exist
                buckets.get(currentBucket).add(fileName);
            }
        }

        // Find the bucket with the largest number of files
        String bucketWithMostFiles = "";
        int maxFiles = 0;

        for (String bucket : buckets.keySet()) {
            int fileCount = buckets.get(bucket).size();
            if (fileCount > maxFiles) {
                maxFiles = fileCount;
                bucketWithMostFiles = bucket;
            } else if (fileCount == maxFiles) {
                // If two buckets have the same number of files, return the lexicographically smaller one
                if (bucketWithMostFiles.compareTo(bucket) > 0) {
                    bucketWithMostFiles = bucket;
                }
            }
        }

        return bucketWithMostFiles;
    }

    public static void main(String[] args) {
        String[] commands = {
                "goto bucketA", "create fileA", "create fileB",
                "goto bucketB", "create fileA", "goto bucketC",
                "create fileA", "create fileB", "create fileC"
        };

        System.out.println("The bucket with the most files is: " + solution(commands));
    }
}
