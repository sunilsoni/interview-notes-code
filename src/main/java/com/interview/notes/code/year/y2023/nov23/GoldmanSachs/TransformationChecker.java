package com.interview.notes.code.year.y2023.nov23.GoldmanSachs;

import java.util.*;

public class TransformationChecker {

    public static String isPossible(int a, int b, int c, int d) {
        if (c < a || d < b) {
            return "No";
        }

        Queue<Pair> queue = new LinkedList<>();
        Set<Pair> visited = new HashSet<>();

        queue.offer(new Pair(a, b));
        visited.add(new Pair(a, b));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            if (current.x == c && current.y == d) {
                return "Yes";
            }

            Pair operation1 = new Pair(current.x + current.y, current.y);
            Pair operation2 = new Pair(current.x, current.x + current.y);

            if (operation1.x <= c && !visited.contains(operation1)) {
                queue.offer(operation1);
                visited.add(operation1);
            }

            if (operation2.y <= d && !visited.contains(operation2)) {
                queue.offer(operation2);
                visited.add(operation2);
            }
        }

        return "No";
    }

    public static void main(String[] args) {
        System.out.println(isPossible(1, 1, 5, 2)); // Expected output: Yes
        System.out.println(isPossible(1, 4, 5, 9)); // Expected output: Yes
        System.out.println(isPossible(1, 2, 3, 6)); // Expected output: No
        // System.out.println(isPossible(1, 2
    }

    private static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
