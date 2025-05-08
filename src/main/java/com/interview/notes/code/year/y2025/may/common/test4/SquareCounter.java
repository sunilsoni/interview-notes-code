package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.*;
import java.util.stream.*;

class Point {
    int x, y;
    Point(int x, int y) { this.x = x; this.y = y; }

    @Override
    public int hashCode() { return Objects.hash(x, y); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return this.x == p.x && this.y == p.y;
    }
}

public class SquareCounter {

    // Optimal algorithm to count squares
    public static int countSquares(List<Point> points) {
        Set<Point> pointSet = new HashSet<>(points);
        int count = 0;

        for (Point p1 : points) {
            for (Point p2 : points) {
                if (p1.x < p2.x && p1.y == p2.y) {
                    int sideLength = p2.x - p1.x;
                    Point p3 = new Point(p1.x, p1.y + sideLength);
                    Point p4 = new Point(p2.x, p2.y + sideLength);

                    if (pointSet.contains(p3) && pointSet.contains(p4)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Test case 1: Minimal reproducible example
        List<Point> points1 = Arrays.asList(
            new Point(2, 3), new Point(4, 3), new Point(2, 1), new Point(4, 1)
        );
        System.out.println("Test Case 1: " + (countSquares(points1) == 1 ? "PASS" : "FAIL"));

        // Test case 2: Multiple squares
        List<Point> points2 = Arrays.asList(
            new Point(2, 3), new Point(4, 3), new Point(6, 3),
            new Point(2, 1), new Point(4, 1), new Point(6, 1)
        );
        System.out.println("Test Case 2: " + (countSquares(points2) == 2 ? "PASS" : "FAIL"));

        // Test case 3: Edge case, no squares
        List<Point> points3 = Arrays.asList(
            new Point(0, 0), new Point(1, 1), new Point(2, 2)
        );
        System.out.println("Test Case 3: " + (countSquares(points3) == 0 ? "PASS" : "FAIL"));

        // Test case 4: Large data handling
        List<Point> largePoints = IntStream.range(0, 1000).boxed()
                .flatMap(x -> IntStream.range(0, 1000).mapToObj(y -> new Point(x, y)))
                .collect(Collectors.toList());

        // This should be computationally reasonable
        System.out.println("Test Case 4: Large input test - Started");
        int largeCount = countSquares(largePoints);
        System.out.println("Test Case 4: Large input test - Completed, squares found: " + largeCount);
    }
}
