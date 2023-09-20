package com.interview.notes.code.months.june23.test9;

import java.util.Arrays;

/**
 * This solution has a time complexity of O(n), where n is the number of box types.
 * The space complexity is O(1) since we only use a constant amount of extra space. Is there anything else you would like to know? 😊
 */
public class AmazonBox {
    private static int[][] boxTypes = {{24, 20, 16}, {20, 16, 12}, {50, 40, 2}, {40, 30, 2}, {60, 3, 3}, {15, 3, 3}};

    public static boolean canBeHidden(int[] item) {
        for (int[] boxType : boxTypes) {
            if (fitsInBox(item, boxType)) {
                return true;
            }
        }
        return false;
    }

    private static boolean fitsInBox(int[] item, int[] boxType) {
        int[] sortedItem = item.clone();
        int[] sortedBoxType = boxType.clone();
        Arrays.sort(sortedItem);
        Arrays.sort(sortedBoxType);
        for (int i = 0; i < sortedItem.length; i++) {
            if (sortedItem[i] > sortedBoxType[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canBeHidden(new int[]{10, 10, 10})); // true
        System.out.println(canBeHidden(new int[]{60, 5, 5})); // false
    }
}
