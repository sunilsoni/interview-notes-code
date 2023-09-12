package com.interview.notes.code.Aug23.test4;

public class ArraySequence1 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 22, 23, 5, 0, 0, -100, 100};

        // Find the position of 0,0 sequence
        int zeroSeqPos = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == 0 && arr[i + 1] == 0) {
                zeroSeqPos = i;
                break;
            }
        }

        // Print the sequence just before 0,0 sequence
        if (zeroSeqPos > 1) { // This check ensures that there are at least 3 numbers before the 0,0 sequence
            System.out.println(arr[zeroSeqPos - 3] + "," + arr[zeroSeqPos - 2] + "," + arr[zeroSeqPos - 1]);
        } else {
            System.out.println("No sufficient sequence found before 0,0 sequence.");
        }
    }
}
