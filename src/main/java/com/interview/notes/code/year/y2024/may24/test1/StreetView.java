package com.interview.notes.code.year.y2024.may24.test1;

import java.util.Arrays;
import java.util.List;

public class StreetView {
    public static boolean isBlockCovered(List<Integer> pictures, int blockSize, int blockStart, int blockEnd) {
        // Create a grid representation of the street
        boolean[] street = new boolean[blockEnd - blockStart + 1];

        // Mark cells covered by pictures
        for (int pic : pictures) {
            int start = pic - blockStart;
            int end = start + blockSize;
            for (int i = start; i < end && i < street.length; i++) {
                street[i] = true;
            }
        }

        // Check if the entire block is covered
        for (int i = blockStart; i <= blockEnd; i++) {
            if (!street[i - blockStart]) {
                return false; // Found an uncovered segment
            }
        }
        return true; // Entire block is covered
    }

    public static void main(String[] args) {
        List<Integer> pictures1 = Arrays.asList(2, 5, 7, 10); // Assuming the length of each picture is 5
        int blockSize1 = 6; // Block size
        int blockStart1 = 1; // Block start
        int blockEnd1 = 6; // Block end
        System.out.println(isBlockCovered(pictures1, blockSize1, blockStart1, blockEnd1)); // Output: true

        List<Integer> pictures2 = Arrays.asList(1, 4, 7); // Assuming the length of each picture is 5
        int blockSize2 = 6; // Block size
        int blockStart2 = 1; // Block start
        int blockEnd2 = 6; // Block end
        System.out.println(isBlockCovered(pictures2, blockSize2, blockStart2, blockEnd2)); // Output: false
    }
}
