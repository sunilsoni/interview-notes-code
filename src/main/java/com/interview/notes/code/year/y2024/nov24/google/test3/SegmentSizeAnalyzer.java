package com.interview.notes.code.year.y2024.nov24.google.test3;

public class SegmentSizeAnalyzer {
    // Test different segment sizes
    public static void analyzeSegmentSizes() {
        int[] sizes = {16, 64, 256, 1024, 4096};
        for (int size : sizes) {
            testSegmentSize(size);
        }
    }

    private static void testSegmentSize(int segmentSize) {
        SetOfRanges ranges = null;//new SetOfRanges(segmentSize);

        // Memory usage before
        long memBefore = 0l;//getMemoryUsage();

        // Add typical range patterns
        for (int i = 0; i < 1000; i++) {
            // Sparse ranges
            ranges.addRange(i * 100, i * 100 + 10);
            // Dense ranges
            ranges.addRange(i * 1000, (i + 1) * 1000);
        }

        // Memory usage after
        long memAfter = 0l;//getMemoryUsage();

        // Performance test
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            ranges.query(i);
        }
        long endTime = System.nanoTime();

        System.out.printf("Segment Size: %d\n", segmentSize);
        System.out.printf("Memory Used: %d KB\n", (memAfter - memBefore) / 1024);
        System.out.printf("Query Time: %d ms\n\n", (endTime - startTime) / 1000000);
    }
}