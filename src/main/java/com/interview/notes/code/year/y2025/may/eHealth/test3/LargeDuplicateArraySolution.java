package com.interview.notes.code.year.y2025.may.eHealth.test3;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class LargeDuplicateArraySolution {
    
    // Method to handle large arrays using chunking
    public static int removeDuplicatesFromLargeArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // For very large arrays, process in chunks
        final int CHUNK_SIZE = 1_000_000; // Process 1 million elements at a time
        int writeIndex = Math.min(2, nums.length); // Initialize with up to 2 elements
        
        try {
            for (int i = 2; i < nums.length; i += CHUNK_SIZE) {
                int endIndex = Math.min(i + CHUNK_SIZE, nums.length);
                writeIndex = processChunk(nums, i, endIndex, writeIndex);
                
                // Optional: Add progress monitoring
                if (i % (CHUNK_SIZE * 10) == 0) {
                    System.out.printf("Processed %d elements (%.2f%%)\n", 
                        i, (100.0 * i / nums.length));
                }
            }
        } catch (OutOfMemoryError e) {
            // Fallback to streaming approach if array is too large
            System.out.println("Switching to stream processing due to memory constraints");
            return processUsingStream(nums);
        }
        
        return writeIndex;
    }

    private static int processChunk(int[] nums, int start, int end, int writeIndex) {
        for (int readIndex = start; readIndex < end; readIndex++) {
            if (nums[readIndex] != nums[writeIndex - 2]) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }
        return writeIndex;
    }

    // Fallback method using Java 8 Streams for extremely large arrays
    private static int processUsingStream(int[] nums) {
        try {
            AtomicInteger writeIndex = new AtomicInteger(2);
            Arrays.stream(nums)
                  .skip(2)
                  .parallel()
                  .forEach(num -> {
                      synchronized (writeIndex) {
                          if (num != nums[writeIndex.get() - 2]) {
                              nums[writeIndex.get()] = num;
                              writeIndex.incrementAndGet();
                          }
                      }
                  });
            return writeIndex.get();
        } catch (Exception e) {
            System.out.println("Error processing stream: " + e.getMessage());
            return 0;
        }
    }

    public static void main(String[] args) {
        // Test with different sizes
        testWithSize(100);
        testWithSize(1_000_000);
        testWithSize(10_000_000);
        // Uncomment for even larger tests
        // testWithSize(1_000_000_000);
    }

    private static void testWithSize(int size) {
        System.out.println("\nTesting with array size: " + size);
        
        // Generate large test array
        int[] largeArray = generateLargeArray(size);
        
        // Measure time and memory
        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        
        int result = removeDuplicatesFromLargeArray(largeArray);
        
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long endTime = System.currentTimeMillis();
        
        // Print results
        System.out.printf("Time taken: %d ms\n", (endTime - startTime));
        System.out.printf("Memory used: %d MB\n", (usedMemoryAfter - usedMemoryBefore) / (1024 * 1024));
        System.out.printf("Result length: %d\n", result);
        
        // Validate first few elements
        System.out.println("First 5 elements after processing: " + 
            Arrays.toString(Arrays.copyOfRange(largeArray, 0, Math.min(5, result))));
    }

    private static int[] generateLargeArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        
        // Generate sorted array with duplicates
        int currentNum = 0;
        for (int i = 0; i < size; i++) {
            if (random.nextDouble() < 0.3) { // 30% chance to increment number
                currentNum++;
            }
            array[i] = currentNum;
        }
        return array;
    }

    // Memory monitoring utility
    private static void printMemoryStats() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;
        
        System.out.printf("Memory - Total: %dMB, Used: %dMB, Free: %dMB\n",
            totalMemory, usedMemory, freeMemory);
    }
}
