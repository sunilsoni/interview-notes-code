package com.interview.notes.code.year.y2025.october.common.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListRotation {
    public static void rotateList(List<String> list, int n) {
        // Make sure n is within list size
        n = n % list.size();

        // First reverse the entire list
        reverse(list, 0, list.size() - 1);

        // Then reverse first n elements
        reverse(list, 0, n - 1);

        // Finally reverse remaining elements
        reverse(list, n, list.size() - 1);
    }

    private static void reverse(List<String> list, int start, int end) {
        while (start < end) {
            // Swap elements
            String temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        // Create the list
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "abc"));
        int n = 3;

        System.out.println("Original list: " + list);
        rotateList(list, n);
        System.out.println("Rotated list: " + list);
    }
}
