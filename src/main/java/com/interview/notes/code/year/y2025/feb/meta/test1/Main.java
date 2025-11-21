package com.interview.notes.code.year.y2025.feb.meta.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

record SparseVector(int[] indices, double[] values) {
    SparseVector(int[] indices, double[] values) {
        if (indices.length != values.length) {
            throw new IllegalArgumentException("Indices and values must be the same length");
        }

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < indices.length; i++) {
            entries.add(new Entry(indices[i], values[i]));
        }
        Collections.sort(entries, Comparator.comparingInt(e -> e.index));

        this.indices = new int[entries.size()];
        this.values = new double[entries.size()];
        for (int i = 0; i < entries.size(); i++) {
            this.indices[i] = entries.get(i).index;
            this.values[i] = entries.get(i).value;
        }
    }

    private static class Entry {
        int index;
        double value;

        Entry(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }
}

class DotProductCalculator {
    public static double dotProduct(SparseVector a, SparseVector b) {
        double sum = 0.0;
        int[] aIndices = a.indices();
        double[] aValues = a.values();
        int[] bIndices = b.indices();
        double[] bValues = b.values();

        int i = 0, j = 0;
        while (i < aIndices.length && j < bIndices.length) {
            int aIdx = aIndices[i];
            int bIdx = bIndices[j];
            if (aIdx == bIdx) {
                sum += aValues[i++] * bValues[j++];
            } else if (aIdx < bIdx) {
                i++;
            } else {
                j++;
            }
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        testLargeVectors();
    }

    private static void testCase1() {
        SparseVector v1 = new SparseVector(new int[0], new double[0]);
        SparseVector v2 = new SparseVector(new int[0], new double[0]);
        evaluateTest("Test Case 1 (Both Empty)", 0.0, DotProductCalculator.dotProduct(v1, v2));
    }

    private static void testCase2() {
        SparseVector v1 = new SparseVector(new int[]{5}, new double[]{2.0});
        SparseVector v2 = new SparseVector(new int[]{5}, new double[]{3.0});
        evaluateTest("Test Case 2 (Single Element)", 6.0, DotProductCalculator.dotProduct(v1, v2));
    }

    private static void testCase3() {
        SparseVector v1 = new SparseVector(new int[]{1, 3, 5}, new double[]{2.0, 4.0, 6.0});
        SparseVector v2 = new SparseVector(new int[]{3, 4, 5}, new double[]{5.0, 6.0, 7.0});
        evaluateTest("Test Case 3 (Partial Overlap)", 62.0, DotProductCalculator.dotProduct(v1, v2));
    }

    private static void testCase4() {
        SparseVector v1 = new SparseVector(new int[]{1, 2}, new double[]{1.0, 2.0});
        SparseVector v2 = new SparseVector(new int[]{3, 4}, new double[]{3.0, 4.0});
        evaluateTest("Test Case 4 (No Overlap)", 0.0, DotProductCalculator.dotProduct(v1, v2));
    }

    private static void testCase5() {
        SparseVector v1 = new SparseVector(new int[]{1, 2, 3}, new double[]{1.0, 2.0, 3.0});
        SparseVector v2 = new SparseVector(new int[]{1, 2}, new double[]{4.0, 5.0});
        evaluateTest("Test Case 5 (Different Lengths)", 14.0, DotProductCalculator.dotProduct(v1, v2));
    }

    private static void testLargeVectors() {
        int size = 10000;
        int[] indices1 = new int[size];
        double[] values1 = new double[size];
        for (int i = 0; i < size; i++) {
            indices1[i] = i;
            values1[i] = 2.0;
        }
        SparseVector v1 = new SparseVector(indices1, values1);

        int[] indices2 = new int[size];
        double[] values2 = new double[size];
        for (int i = 0; i < size; i++) {
            indices2[i] = i + 5000;
            values2[i] = 3.0;
        }
        SparseVector v2 = new SparseVector(indices2, values2);

        double expected = 5000 * 2.0 * 3.0;
        evaluateTest("Test Large Vectors", expected, DotProductCalculator.dotProduct(v1, v2));
    }

    private static void evaluateTest(String testName, double expected, double actual) {
        if (Double.compare(expected, actual) == 0) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL. Expected " + expected + " but got " + actual);
        }
    }
}