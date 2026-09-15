package com.interview.notes.code.year.y2026.september.common.test5;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KNN { // Main class.

    static List<Point> nearest(List<Point> data, int x, int y, int k) { // Find K nearest.
        return data.stream() // Read all points.
                .sorted(Comparator.comparingLong(p -> p.distance(x, y))) // Nearest first.
                .limit(k) // Take K points.
                .toList(); // Return list.
    }

    static String classify(List<Point> data, int x, int y, int k) { // Predict label.
        return nearest(data, x, y, k).stream() // Get nearest neighbors.
                .collect(Collectors.groupingBy(Point::label, Collectors.counting())) // Count labels.
                .entrySet().stream() // Read label counts.
                .max(Map.Entry.comparingByValue()) // Find highest count.
                .orElseThrow() // Ensure result exists.
                .getKey(); // Return Cat or Dog.
    }

    static void test(String name, String actual, String expected) { // Test helper.
        System.out.println(name + ": " + (actual.equals(expected) ? "PASS" : "FAIL")); // PASS/FAIL.
    }

    static void main(String[] args) { // Start program.

        List<Point> data = List.of( // Training data.
                new Point(1, 0, "Cat"), // Cat point.
                new Point(1, 1, "Cat"), // Cat point.
                new Point(2, 0, "Cat"), // Cat point.
                new Point(3, 4, "Dog"), // Dog point.
                new Point(3, 6, "Dog"), // Dog point.
                new Point(4, 5, "Dog"), // Dog point.
                new Point(0, 0, "Cat")  // Cat point.
        );

        var neighbors = nearest(data, 2, 2, 3); // Find nearest 3.
        System.out.println("Nearest: " + neighbors); // Print neighbors.

        test("Given Test", classify(data, 2, 2, 3), "Cat"); // Required test.
        test("Dog Test", classify(data, 4, 5, 3), "Dog"); // Dog test.
        test("Exact Point", classify(data, 1, 0, 3), "Cat"); // Existing point test.

        var large = IntStream.range(0, 100_000) // Create large input.
                .mapToObj(i -> new Point(i, i, "Cat")) // Create 100K points.
                .toList(); // Store points.

        test("Large Test", classify(large, 50_000, 50_000, 5), "Cat"); // Large test.
    }

    record Point(int x, int y, String label) { // Store point and label.
        long distance(int a, int b) { // Calculate squared distance.
            long dx = x - a; // X difference.
            long dy = y - b; // Y difference.
            return dx * dx + dy * dy; // Euclidean distance without sqrt.
        }
    }
}