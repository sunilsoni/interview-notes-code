package com.interview.notes.code.year.y2025.november.assessment.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BingoNumberFinder {

    public static int getBingoNumber(List<List<Integer>> mat, List<Integer> arr) {
        int n = mat.size();
        if (n == 0) {
            return -1;
        }
        int m = mat.get(0).size();
        int total = n * m;

        int[] rowIndex = new int[total + 1];
        int[] colIndex = new int[total + 1];

        for (int i = 0; i < n; i++) {
            List<Integer> row = mat.get(i);
            for (int j = 0; j < m; j++) {
                int value = row.get(j);
                rowIndex[value] = i;
                colIndex[value] = j;
            }
        }

        int[] rowCount = new int[n];
        int[] colCount = new int[m];

        for (int number : arr) {
            int r = rowIndex[number];
            int c = colIndex[number];
            rowCount[r]++;
            colCount[c]++;
            if (rowCount[r] == m || colCount[c] == n) {
                return number;
            }
        }

        return -1;
    }

    private static boolean runSingleTest(String name,
                                         List<List<Integer>> mat,
                                         List<Integer> arr,
                                         int expected) {
        int actual = getBingoNumber(mat, arr);
        boolean pass = actual == expected;
        System.out.println(name + ": " + (pass ? "PASS" : "FAIL")
                + " (expected=" + expected + ", actual=" + actual + ")");
        return pass;
    }

    private static List<List<Integer>> buildMatrix(int[][] values) {
        List<List<Integer>> mat = new ArrayList<>();
        for (int[] row : values) {
            List<Integer> listRow = new ArrayList<>();
            for (int v : row) {
                listRow.add(v);
            }
            mat.add(listRow);
        }
        return mat;
    }

    public static void main(String[] args) {
        boolean allPass = true;

        List<List<Integer>> exampleMat = buildMatrix(new int[][]{
                {3, 1, 8},
                {4, 6, 2},
                {7, 5, 9}
        });
        List<Integer> exampleArr = Arrays.asList(5, 4, 8, 7, 6, 1, 9, 2, 3);
        allPass &= runSingleTest("ExampleTest", exampleMat, exampleArr, 1);

        List<List<Integer>> sample0Mat = buildMatrix(new int[][]{
                {1, 6},
                {2, 4},
                {5, 3}
        });
        List<Integer> sample0Arr = Arrays.asList(2, 4, 3, 1, 5, 6);
        allPass &= runSingleTest("SampleTest0", sample0Mat, sample0Arr, 4);

        List<List<Integer>> sample1Mat = buildMatrix(new int[][]{
                {2, 4, 6},
                {5, 1, 3}
        });
        List<Integer> sample1Arr = Arrays.asList(2, 1, 3, 5, 6, 4);
        allPass &= runSingleTest("SampleTest1", sample1Mat, sample1Arr, 5);

        List<List<Integer>> singleMat = buildMatrix(new int[][]{
                {7}
        });
        List<Integer> singleArr = List.of(7);
        allPass &= runSingleTest("SingleCell", singleMat, singleArr, 7);

        List<List<Integer>> rowCompleteMat = buildMatrix(new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        });
        List<Integer> rowCompleteArr = Arrays.asList(4, 5, 6, 1, 2, 3);
        allPass &= runSingleTest("RowCompleteLater", rowCompleteMat, rowCompleteArr, 6);

        int n = 1000;
        int m = 1000;
        int total = n * m;
        List<List<Integer>> largeMat = new ArrayList<>();
        int value = 1;
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                row.add(value++);
            }
            largeMat.add(row);
        }
        List<Integer> largeArr = new ArrayList<>();
        for (int i = 1; i <= total; i++) {
            largeArr.add(i);
        }
        allPass &= runSingleTest("LargeDataTest", largeMat, largeArr, m);

        System.out.println("Overall result: " + (allPass ? "PASS" : "FAIL"));
    }
}
