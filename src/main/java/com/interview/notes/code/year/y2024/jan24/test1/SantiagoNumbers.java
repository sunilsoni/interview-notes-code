package com.interview.notes.code.year.y2024.jan24.test1;

import java.util.Scanner;

/**
 * Section: 7 of 8| Coding
 * Question: 1 of 1
 * AP
 * Mathematics: Number Series
 * Amy loves numbers that start with 1 and calls them "Santiago numbers".
 * Amy asks Holt to give her any range and claims that she will find the number of Santiago numbers in that range.
 * Holt gives her two integers L and R denoting the starting and end of range [L, R] where L and R are also included in the range.
 * As soon as she starts working on this range, Jake asks for her help in a case.
 * She assigns you the task of finding the number of Santiago numbers in the range L to R (inclusive).
 * Note
 * Santiago numbers start with 1 i.e.
 * 1,10,11,12,....,100,101,...,199,1000,.... are all Santiago Numbers.
 * Function Description
 * In the provided code snippet, implement the provided getCountOfSantiagoNumbers (...) method.
 * You can write your code in the space below the phrase "WRITE YOUR LOGIC HERE".
 * There will be multiple test cases running so the Input and Output should match exactly as provided.
 * The base Output variable result is set to a default value of -404 which can be modified. Additionally, you can add or remove these output variables.
 * Input Format
 * The first line contains an integer L denoting the start of the range.
 * The second line contains an integer R denoting the end of the range.
 * Sample Input
 * 1 -- denotes L
 * 500 -- denotes R
 * Constraints
 * 1< =L,R<=10^18
 * Output Format
 * Print the number of Santiago numbers in the range [L, R].
 */
public class SantiagoNumbers {

    public static int getCountOfSantiagoNumbers(int L, int R) {
        int result = 0;
        // Starting from L, count numbers that start with '1'
        for (int i = L; i <= R; i++) {
            // Convert number to string to check if it starts with '1'
            String number = String.valueOf(i);
            if (number.startsWith("1")) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();
        int R = sc.nextInt();
        System.out.print(getCountOfSantiagoNumbers(L, R));
        sc.close();
    }
}
