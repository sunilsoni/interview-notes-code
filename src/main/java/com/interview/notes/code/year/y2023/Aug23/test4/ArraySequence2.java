package com.interview.notes.code.year.y2023.Aug23.test4;

public class ArraySequence2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 22, 23, 5, 0, 0, -100, 100};
        StringBuilder sequence = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                if (sequence.length() > 0) {
                    System.out.println(sequence.substring(0, sequence.length() - 1)); // remove the last comma
                    sequence = new StringBuilder();
                }
            } else {
                sequence.append(arr[i]).append(",");
            }
        }

        if (sequence.length() > 0) {
            System.out.println(sequence.substring(0, sequence.length() - 1)); // for the last sequence if no zero at the end
        }
    }
}
