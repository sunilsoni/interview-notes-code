package com.interview.notes.code.months.june23.test9;

import java.util.Arrays;

class Box {
    int[] dimensions;

    Box(int length, int width, int height) {
        dimensions = new int[]{length, width, height};
        Arrays.sort(dimensions);  // sort the dimensions
    }

    boolean canFit(int[] itemDimensions) {
        for (int i = 0; i < 3; i++) {
            if (itemDimensions[i] > dimensions[i]) {
                return false;
            }
        }
        return true;
    }
}

public class BoxTest {
    public static void main(String[] args) {
        Box[] boxes = {
                new Box(24, 20, 16),
                new Box(20, 16, 12),
                new Box(50, 40, 2),
                new Box(40, 30, 2),
                new Box(60, 3, 3),
                new Box(15, 3, 3)
        };

        int[][] items = {
                {10, 10, 10},
                {60, 5, 5}
        };

        for (int[] item : items) {
            Arrays.sort(item);
            boolean canBeHidden = false;
            for (Box box : boxes) {
                if (box.canFit(item)) {
                    canBeHidden = true;
                    break;
                }
            }
            System.out.println(canBeHidden ? "Yes, can be hidden" : "No, cannot be hidden");
        }
    }
}
