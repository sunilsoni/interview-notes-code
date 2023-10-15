package com.interview.notes.code.months.Oct23.test5;


/**
 *
 There are N empty glasses with a capacity of 1,2,.... N liters (there is exactly one glass of each
 unique capacity). You want to pour exactly K liters of water into glasses. Each glass may be either full
 or empty (a glass cannot be partially filled). What is the minimum number of glasses that you need to
 contain K liters of water?
 Write a function:
 class Solution { public int solution(int N, int K); }
 that, given two integers N and K, returns the minimum number of glasses that are needed to contain
 exactly K liters of water. If it is not possible to pour exactly K liters of water into glasses then the
 function should return -1.
 Examples:
 1. Given N = 5 and K = 8, the function should return 2. There are five glasses of capacity 1,2,3,4 and
 5. You can use two glasses with capacity 3 and 5 to hold 8 liters of water.
 2. Given N = 4 and K = 10, the function should return 4. You must use all the glasses to contain 10
 liters of water.
 3. Given N = 1 and K = 2, the function should return -1. There is only one glass with capacity 1, so you
 cannot pour 2 liters of water.
 4. G iven N = 10 and K = 5, the function should return 1. You can use the glass with capacity 5.
 Write an efficient algorithm for the following assumptions:
 •   N is an integer within the range [1 ..1,000,000];
 •   K is an integer within the range [1 ..1,000,000,000].


 */
public class MaxNoOfGlasses {
  
  // Function to find the minimum number of glasses required to contain K liters of water
  public int solution(int N, int K) {
    // Step 1: Calculate the maximum water that all glasses can hold
    // The sum of the first N natural numbers is (N * (N + 1)) / 2
    long maxCapacity = (long) N * (N + 1) / 2;

    // If K is greater than maxCapacity, it's not possible to contain K liters
    if (K > maxCapacity) {
      return -1;
    }

    int numberOfGlassesUsed = 0; // Step 2: Initialize a counter for the number of glasses used
    int remainingWater = K; // Initialize remaining water to be poured
    
    // Step 3-6: Start filling the glasses, beginning with the largest
    for (int i = N; i >= 1; i--) {
      // If the remaining water to be poured is greater than or equal to the current glass capacity
      if (remainingWater >= i) {
        remainingWater -= i; // Subtract current glass capacity from remaining water
        numberOfGlassesUsed++; // Increment the counter
      }

      // If remaining water is zero, break
      if (remainingWater == 0) {
        break;
      }
    }

    return numberOfGlassesUsed;
  }

  public static void main(String[] args) {
    MaxNoOfGlasses s = new MaxNoOfGlasses();
    System.out.println(s.solution(5, 8));  // Should print 2
    System.out.println(s.solution(4, 10)); // Should print 4
    System.out.println(s.solution(1, 2));  // Should print -1
    System.out.println(s.solution(10, 5)); // Should print 1
  }
}
