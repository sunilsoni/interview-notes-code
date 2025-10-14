package com.interview.notes.code.year.y2025.october.common.test6;

public class CustomArraySort {

    public static void main(String[] args) {
        int[] input = {4, 9, 0, 2, 0, 7, 0, 0, 3, 8};
        int[] output = sortArray(input);

        // print output
        for (int n : output) {
            System.out.print(n + " ");
        }
    }

    // method to perform custom sorting
    private static int[] sortArray(int[] arr) {
        int n = arr.length;

        // Step 1: Count zeros
        int zeroCount = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) zeroCount++;
        }

        // Step 2: Separate non-zero elements into temp array
        int[] temp = new int[n - zeroCount];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                temp[index++] = arr[i];
            }
        }

        // Step 3: Split temp into odds and evens
        int oddCount = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] % 2 != 0) oddCount++;
        }

        int[] odds = new int[oddCount];
        int[] evens = new int[temp.length - oddCount];
        int oi = 0, ei = 0;

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] % 2 != 0)
                odds[oi++] = temp[i];
            else
                evens[ei++] = temp[i];
        }

        // Step 4: Sort both arrays manually (simple bubble sort)
        bubbleSort(odds);
        bubbleSort(evens);

        // Step 5: Merge results + zeros
        int[] result = new int[n];
        int pos = 0;

        for (int i = 0; i < odds.length; i++) result[pos++] = odds[i];
        for (int i = 0; i < evens.length; i++) result[pos++] = evens[i];
        for (int i = 0; i < zeroCount; i++) result[pos++] = 0;

        return result;
    }

    // Simple bubble sort (ascending)
    private static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
