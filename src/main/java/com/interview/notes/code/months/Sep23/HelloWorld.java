package com.interview.notes.code.months.Sep23;


//In below test cases retrun next number which has next biggest number which has same numbers
public class HelloWorld {

    public static int CountingMinutesI(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        
        // Find the first pair of adjacent digits (i, i-1) where digit[i] > digit[i-1]
        int i = digits.length - 1;
        while (i > 0 && digits[i] <= digits[i-1]) {
            i--;
        }

        // If no such pair is found, return -1
        if (i == 0) {
            return -1;  // Can't create a larger permutation
        }

        // Find the smallest digit to the right of digits[i-1] that's greater than digits[i-1]
        int j = digits.length - 1;
        while (digits[j] <= digits[i-1]) {
            j--;
        }

        // Swap digits[i-1] and digits[j]
        char temp = digits[i-1];
        digits[i-1] = digits[j];
        digits[j] = temp;

        // Reverse the sequence of digits after position i-1
        reverse(digits, i, digits.length - 1);
        
        return Integer.parseInt(new String(digits));
    }

    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println(CountingMinutesI(123));    // 132
        System.out.println(CountingMinutesI(12453));  // 12534
        System.out.println(CountingMinutesI(11121));  // 11211
        System.out.println(CountingMinutesI(41352));  // 41523
    }
}
