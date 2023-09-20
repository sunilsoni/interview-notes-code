package com.interview.notes.code.months.june23.test8;

import java.util.Arrays;

public class BoxManagerTest {
    public static void main(String[] args) {
        BoxManager boxManager = new BoxManager();

        // Add boxes
        boxManager.addBox(new Box(24, 20, 16));
        boxManager.addBox(new Box(20, 16, 12));
        boxManager.addBox(new Box(50, 40, 2));
        boxManager.addBox(new Box(40, 30, 2));
        boxManager.addBox(new Box(60, 3, 3));
        boxManager.addBox(new Box(15, 3, 3));

        FitChecker fitChecker = new FitChecker(boxManager);

        int[][] items = {
                {10, 10, 10},
                {60, 5, 5}
        };

        for (int[] item : items) {
            Arrays.sort(item);
            if (fitChecker.canItemBeHidden(item)) {
                System.out.println("Yes, can be hidden");
            } else {
                System.out.println("No, cannot be hidden");
            }
        }
    }
}
