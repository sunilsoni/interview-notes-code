package com.interview.notes.code.months.nov23.test5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Outcome {

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static int solve(List<Integer> arr) {
        Collections.sort(arr, Collections.reverseOrder());
        int score = 0;
        int operationNumber = 1;
        while (!arr.isEmpty()) {
            int maxPairGCD = 0;
            int gcdValue, index1 = 0, index2 = 0;
            for (int i = 0; i < arr.size(); i++) {
                for (int j = i + 1; j < arr.size(); j++) {
                    gcdValue = gcd(arr.get(i), arr.get(j));
                    if (gcdValue * operationNumber > maxPairGCD) {
                        maxPairGCD = gcdValue * operationNumber;
                        index1 = i;
                        index2 = j;
                    }
                }
            }
            score += maxPairGCD;
            if (index1 > index2) {
                arr.remove(index1);
                arr.remove(index2);
            } else {
                arr.remove(index2);
                arr.remove(index1);
            }
            operationNumber++;
        }
        return score;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> arr = new ArrayList<>(2 * n);
        for (int i = 0; i < 2 * n; i++) {
            arr.add(scanner.nextInt());
        }
        scanner.close();

        System.out.println(solve(arr));
    }
}
