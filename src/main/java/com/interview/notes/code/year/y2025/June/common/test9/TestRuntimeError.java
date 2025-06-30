package com.interview.notes.code.year.y2025.June.common.test9;

public class TestRuntimeError {
    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[0] = 0;
        arr[1] = Integer.parseInt("1");
        arr[2] = Integer.parseInt(null); // NumberFormatException at runtime
        arr[3] = 3;
        System.out.println(arr[1]);
    }
}
