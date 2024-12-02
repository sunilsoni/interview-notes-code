package com.interview.notes.code.year.y2023.Oct23.test9;

public class CompareValues {

    public static void main(String[] args) {
        System.out.println(greaterValue("banana", "apple")); // apple
        System.out.println(greaterValue(3, 8));              // 8
        // This will result in a compile-time error, enforcing type safety.
        // System.out.println(greaterValue("3", 8));         
    }

    public static <T> T greaterValue(T input1, T input2) {
        if (input1 instanceof Comparable) {
            Comparable<T> comp1 = (Comparable<T>) input1;
            return comp1.compareTo(input2) > 0 ? input1 : input2;
        }
        throw new IllegalArgumentException("Cannot compare the given types");
    }
}
