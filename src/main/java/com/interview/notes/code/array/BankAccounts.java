package com.interview.notes.code.array;

/**
 * You are given a list of N transfers (numbered from 0 to N-1) between two banks: bank A and bank B. The K-th transfer is described by two values:
 * • R[K] (either "A" or "B") representing the recipient (the bank the transfer is sent to); • V[K] denoting the value sent via the transfer.
 * All transfers are completed in the order they appear on the list. The banks do not want to go into debt (in other words, their account balance may not drop below 0). What minimum initial account balance in each bank is necessary in order to complete the transfers?
 *
 * Write a function:
 * class Solution { public int[' solution(String R, int() V); }
 * that, given a string R and an array of integers V, both of length N, returns an array of two integers. The integers should represent the minimum initial account balances for banks A and B in the following order: [bank A, bank B].
 * Result array should be returned as an array of integers.
 *
 * Examples:
 * 1. Given R = "BAABA" and V = [2, 4, 1, 1, 2], the function should return [2, 4]. The bank accounts' balances after each transfer are shown in the following table:
 * initial balance A + 2 B + 4 transfer 2 from A to B 0 6 transfer 4 from B to A 4 2 transfer 1 from B to A 5 1 transfer 1 from A to B 4 2 transfer 2 from B to A 6 0
 * 2. Given R = "ABAB" and V = [10, 5, 10, 15], the function should return [0, 15]. 3. Given R = "B" and V = [100], the function should return [100, 0].
 *
 * Write an efficient algorithm for the following assumptions:
 *
 *
 * • string R and array V are both of length N; • N is an integer within the range [1..100,000]; • each element of array V is an integer within the range [1..10,000]; • string R is made only of the characters "A" and/or "B".
 */
class BankAccounts {
        public static int[] solution4(String R, int[] V) {
            int minA = 0;
            int minB = 0;

            int balA = 0;
            int balB = 0;

            for (int i = 0; i < R.length(); i++) {
                if (R.charAt(i) == 'A') {
                    balA += V[i];
                    balB -= V[i];
                    if (balB < minB) {
                        minB = balB;
                    }
                } else if (R.charAt(i) == 'B') {
                    balB += V[i];
                    balA -= V[i];
                    if (balA < minA) {
                        minA = balA;
                    }
                }
            }

            return new int[] { -minA, -minB };
        }


    public static void main(String[] args) {
        int[] a = {2,4,1,1,2};
        int[] arr = solution4("BAABA",a);
        System.out.println(arr[0]+ " "+arr[1]);
    }
}

