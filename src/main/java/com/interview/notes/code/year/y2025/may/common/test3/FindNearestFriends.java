package com.interview.notes.code.year.y2025.may.common.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
**Problem Statement**

You are visiting Chicago and happen to know a few friends who all live nearby. Because of limited time between plans, you can only see a few of them. You choose to decide based on how far they are from your Airbnb.

* Your coordinates: `[-3, 239]`
* Your friends' coordinates:

```
[
  [-2, 129],
  [12, 13],
  [5, 123],
  [-4, -234],
  [17, 132],
  [52, 93],
  [-1, 12]
]
```

**Task**
Write a function to get the coordinates of the 3 closest friends to your location.

 */
public class FindNearestFriends {
    // Brute force solution
    public static List<Point> findClosestFriendsBruteForce(Point myLocation, List<Point> friends, int k) {
        // Calculate distance for each friend
        for (Point friend : friends) {
            friend.distance = calculateDistance(myLocation, friend);
        }

        // Sort based on distance
        friends.sort((a, b) -> Double.compare(a.distance, b.distance));

        // Return first k friends
        return friends.subList(0, Math.min(k, friends.size()));
    }

    private static double calculateDistance(Point p1, Point p2) {
        int dx = p1.x - p2.x;
        int dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        // Test cases
        Point myLocation = new Point(-3, 239);
        List<Point> friends = Arrays.asList(
                new Point(-2, 129),
                new Point(12, 13),
                new Point(5, 123),
                new Point(-4, -234),
                new Point(17, 132),
                new Point(52, 93),
                new Point(-1, 12)
        );

        // Test 1: Normal case
        System.out.println("Test 1: Finding 3 closest friends");
        List<Point> result = findClosestFriendsBruteForce(myLocation, new ArrayList<>(friends), 3);
        System.out.println("Result: " + result);

        // Test 2: Empty list
        System.out.println("\nTest 2: Empty friends list");
        result = findClosestFriendsBruteForce(myLocation, new ArrayList<>(), 3);
        System.out.println("Result: " + result);

        // Test 3: k larger than list size
        System.out.println("\nTest 3: k larger than list size");
        result = findClosestFriendsBruteForce(myLocation, new ArrayList<>(friends), 10);
        System.out.println("Result: " + result);

        // Test 4: Large data set
        List<Point> largeFriendsList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            largeFriendsList.add(new Point(rand.nextInt(1000), rand.nextInt(1000)));
        }

        long startTime = System.currentTimeMillis();
        result = findClosestFriendsBruteForce(myLocation, largeFriendsList, 3);
        long endTime = System.currentTimeMillis();

        System.out.println("\nTest 4: Large dataset (10000 points)");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

    static class Point {
        int x, y;
        double distance;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }
}
