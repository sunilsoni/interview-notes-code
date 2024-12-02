package com.interview.notes.code.year.y2023.july23.test4;

public class Test {


    public static void main(String[] args) {

        System.out.println(calculatePiValue(1000));//
    }

    public static double calculatePiValue(int n) {

        double finalSum = 0.0;
        for (int i = 1; i <= n; i++) {

            finalSum += 1.0 / (i * i);

        }


        return finalSum;
    }


}

