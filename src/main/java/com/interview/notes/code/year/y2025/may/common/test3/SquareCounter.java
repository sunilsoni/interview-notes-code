package com.interview.notes.code.year.y2025.may.common.test3;

import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class SquareCounter {
    public static int countSquares(List<Point> points) {
        // Convert list to set for O(1) lookup
        Set<Point> pointSet = new HashSet<>(points);
        int count = 0;

        // Check each pair of points as potential diagonal points of a square
        for (Point p1 : points) {
            for (Point p2 : points) {
                if (p1.x == p2.x || p1.y == p2.y) continue; // Skip if points are on same x or y

                // Calculate other two points of potential square
                Point p3 = new Point(p1.x, p2.y);
                Point p4 = new Point(p2.x, p1.y);

                // Check if points form a square (equal sides and 90 degree angles)
                if (isSquare(p1, p2, p3, p4) &&
                        pointSet.contains(p3) &&
                        pointSet.contains(p4)) {
                    count++;
                }
            }
        }

        // Each square is counted 4 times (once for each corner as starting point)
        return count / 4;
    }

    private static boolean isSquare(Point p1, Point p2, Point p3, Point p4) {
        int d1 = distance(p1, p3);
        int d2 = distance(p1, p4);
        int d3 = distance(p2, p3);
        int d4 = distance(p2, p4);

        // All sides must be equal and non-zero
        return d1 > 0 && d1 == d2 && d2 == d3 && d3 == d4;
    }

    private static int distance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    public static void main(String[] args) {
        // Test cases
        List<Point> test1 = Arrays.asList(
                new Point(2, 3), new Point(4, 3), new Point(6, 3),
                new Point(2, 1), new Point(4, 1), new Point(6, 1)
        );

        System.out.println("Test 1: " + countSquares(test1));

        // Add more test cases here
        List<Point> test2 = Arrays.asList(
                new Point(0, 0), new Point(1, 0),
                new Point(0, 1), new Point(1, 1)
        );

        System.out.println("Test 2: " + countSquares(test2));
    }
}
