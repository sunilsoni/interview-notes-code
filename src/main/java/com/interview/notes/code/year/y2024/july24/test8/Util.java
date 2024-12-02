package com.interview.notes.code.year.y2024.july24.test8;

public class Util {
    // Generic method to swap two elements in an array
    public static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}