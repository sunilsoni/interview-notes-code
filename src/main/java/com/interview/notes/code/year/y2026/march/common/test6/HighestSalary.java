package com.interview.notes.code.year.y2026.march.common.test6;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HighestSalary {

    // method to find highest salary
    static int highest(int[] arr){
        return Arrays.stream(arr)          // convert array to stream to process elements easily
                     .max()                // find maximum element in the stream
                     .orElse(0);           // if array empty return 0
    }

    public static void main(String[] args) {

        // test case 1 normal input
        int[] t1={1000,3000,2000,5000,4000};  // sample salary data
        int e1=5000;                          // expected result highest salary
        System.out.println("Test1 "+(highest(t1)==e1?"PASS":"FAIL"));

        // test case 2 single value
        int[] t2={9000};                      // only one salary
        int e2=9000;                          // expected same value
        System.out.println("Test2 "+(highest(t2)==e2?"PASS":"FAIL"));

        // test case 3 duplicates
        int[] t3={2000,2000,2000};            // all salaries same
        int e3=2000;                          // highest still same
        System.out.println("Test3 "+(highest(t3)==e3?"PASS":"FAIL"));

        // test case 4 negative values edge case
        int[] t4={-10,-20,-5};                // negative salaries edge case
        int e4=-5;                            // highest among negatives
        System.out.println("Test4 "+(highest(t4)==e4?"PASS":"FAIL"));

        // large data test
        int n=1_000_000;                      // simulate large dataset
        int[] big=IntStream.range(1,n).toArray(); // generate large salary list
        System.out.println("Large Test "+(highest(big)==n-1?"PASS":"FAIL"));
    }
}