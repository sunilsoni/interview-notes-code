package com.interview.notes.code.year.y2026.september.common.test6;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KNN { // Main class.

    static List<Point> nearest(List<Point> data, int x, int y, int k) { // Find K nearest points.

        var heap = new PriorityQueue<Point>( // Keep only K closest points.
                Comparator.comparingLong((Point p) -> p.distance(x, y)).reversed()
        );

        for (var p : data) { // Read every point once.
            heap.offer(p); // Add current point.

            if (heap.size() > k) // If heap has more than K points.
                heap.poll(); // Remove the farthest point.
        }

        return heap.stream() // Stream only K points.
                .sorted(Comparator.comparingLong(p -> p.distance(x, y))) // Nearest first.
                .toList(); // Return result.
    }

    static String classify(List<Point> data, int x, int y, int k) { // Predict label.

        return nearest(data, x, y, k).stream() // Get K nearest points.
                .collect(Collectors.groupingBy( // Group same labels.
                        Point::label,
                        Collectors.counting()
                ))
                .entrySet().stream() // Read label counts.
                .max(Map.Entry.comparingByValue()) // Find majority label.
                .orElseThrow() // Ensure result exists.
                .getKey(); // Return predicted label.
    }

    static void test(String name, String actual, String expected) { // Test helper.

        System.out.println( // Print PASS or FAIL.
                name + ": " +
                (actual.equals(expected) ? "PASS" : "FAIL - " + actual)
        );
    }

    static void main(String[] args) { // Start program.

        List<Point> data = List.of( // Training data.
                new Point(1, 0, "Cat"), // Cat.
                new Point(1, 1, "Cat"), // Cat.
                new Point(2, 0, "Cat"), // Cat.
                new Point(3, 4, "Dog"), // Dog.
                new Point(3, 6, "Dog"), // Dog.
                new Point(4, 5, "Dog"), // Dog.
                new Point(0, 0, "Cat")  // Cat.
        );

        var neighbors = nearest(data, 2, 2, 3); // Find nearest 3.

        System.out.println("Nearest: " + neighbors); // Print neighbors.

        test( // Test given example.
                "Given Test",
                classify(data, 2, 2, 3),
                "Cat"
        );

        test( // Test Dog prediction.
                "Dog Test",
                classify(data, 4, 5, 3),
                "Dog"
        );

        test( // Test existing Cat point.
                "Cat Test",
                classify(data, 1, 0, 3),
                "Cat"
        );

        var large = IntStream.range(0, 1_000_000) // Create 1 million points.
                .mapToObj(i -> new Point(i, i, "Cat")) // Convert to points.
                .toList(); // Store points.

        test( // Large data test.
                "Million Test",
                classify(large, 500_000, 500_000, 3),
                "Cat"
        );
    }

    record Point(int x, int y, String label) { // Store point and label.

        long distance(int a, int b) { // Calculate squared distance.
            long dx = (long) x - a; // X difference.
            long dy = (long) y - b; // Y difference.
            return dx * dx + dy * dy; // Return squared distance.
        }
    }
}