package com.interview.notes.code.year.y2023.july23.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static List<int[]> divideArray(int[] array, int chunkSize) {
        List<int[]> listOfChunks = new ArrayList<>();

        for (int i = 0; i < array.length; i += chunkSize) {
            int end = Math.min(array.length, i + chunkSize);
            listOfChunks.add(Arrays.copyOfRange(array, i, end));
        }

        return listOfChunks;
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        int chunkSize = 10;
        List<int[]> listOfChunks = divideArray(array, chunkSize);

        for (int[] chunk : listOfChunks) {
            System.out.println(Arrays.toString(chunk));
        }
    }
}
