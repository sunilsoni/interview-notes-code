package com.interview.notes.code.year.y2025.march.common.test1;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

class Rectangle {
    Point topLeft;
    double width;
    double height;

    Rectangle(Point topLeft, double width, double height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }
}

class Solution {
    // Simulated rand(n) function that returns number between 0 and n
    static double rand(double n) {
        return Math.random() * n;
    }

    // Function to get random point inside rectangle
    static Point getRandomPoint(Rectangle rect) {
        if (rect == null) return null;

        // Get random x coordinate
        double randomX = rect.topLeft.x + rand(rect.width);

        // Get random y coordinate
        double randomY = rect.topLeft.y - rand(rect.height);

        return new Point(randomX, randomY);
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1: Normal rectangle
        Rectangle rect1 = new Rectangle(new Point(0, 10), 5, 10);
        Point p1 = getRandomPoint(rect1);
        boolean test1 = validatePoint(p1, rect1);
        System.out.println("Test 1: " + (test1 ? "PASS" : "FAIL"));

        // Test Case 2: Zero width rectangle
        Rectangle rect2 = new Rectangle(new Point(0, 0), 0, 5);
        Point p2 = getRandomPoint(rect2);
        boolean test2 = validatePoint(p2, rect2);
        System.out.println("Test 2: " + (test2 ? "PASS" : "FAIL"));

        // Test Case 3: Large rectangle
        Rectangle rect3 = new Rectangle(new Point(0, 1000000), 1000000, 1000000);
        Point p3 = getRandomPoint(rect3);
        boolean test3 = validatePoint(p3, rect3);
        System.out.println("Test 3: " + (test3 ? "PASS" : "FAIL"));
    }

    // Helper method to validate if point is inside rectangle
    static boolean validatePoint(Point p, Rectangle r) {
        if (p == null || r == null) return false;

        return p.x >= r.topLeft.x &&
                p.x <= (r.topLeft.x + r.width) &&
                p.y <= r.topLeft.y &&
                p.y >= (r.topLeft.y - r.height);
    }
}
