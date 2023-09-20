package com.interview.notes.code.months.june23.test2;

import java.util.HashSet;
import java.util.Set;

public class CrossPathChecker {

    public static void main(String[] args) {
        String path = "NESW";
        System.out.println("Does the path cross itself? " + isPathCrossing(path));
    }

    public static boolean isPathCrossing(String path) {
        Set<String> visitedLocations = new HashSet<>();
        int x = 0, y = 0;

        visitedLocations.add(pointToString(x, y));

        for (char direction : path.toCharArray()) {
            switch (direction) {
                case 'N':
                    y++;
                    break;
                case 'S':
                    y--;
                    break;
                case 'E':
                    x++;
                    break;
                case 'W':
                    x--;
                    break;
            }

            String newPosition = pointToString(x, y);

            if (visitedLocations.contains(newPosition)) {
                return true;
            } else {
                visitedLocations.add(newPosition);
            }
        }

        return false;
    }

    private static String pointToString(int x, int y) {
        return x + "," + y;
    }
}
