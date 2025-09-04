package com.interview.notes.code.year.y2025.september.common.test2;

public class CheckOddEven {


    public static boolean isEven(int num) {
        int temp = num;

        while(temp != 0 && temp != 1 && temp != -1) {
            if(temp > 0) {
                temp = temp - 2;
            } else {
                temp = temp + 2;  // For negative numbers, add 2 instead of subtracting
            }
        }

        return temp == 0;  // If temp is 0, number is even
    }

    // Test cases
    public static void main(String[] args) {
        System.out.println(isEven(-13));  // false (odd)
        System.out.println(isEven(-12));  // true (even)
        System.out.println(isEven(13));   // false (odd)
        System.out.println(isEven(12));   // true (even)
        System.out.println(isEven(0));    // true (even)
        System.out.println(isEven(-1));   // false (odd)
        System.out.println(isEven(1));    // false (odd)
    }

}
