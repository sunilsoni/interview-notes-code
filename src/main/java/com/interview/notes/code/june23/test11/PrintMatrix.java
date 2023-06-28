package com.interview.notes.code.june23.test11;

/**
 *
 In Java

 Write a java program to print out a matrix. The print should have the commas
 Input:
 Is a matnx
 Output:
 1.0,1,0
 0,1. 0,1
 0.1, 1,1
 */
public class PrintMatrix {
    public static void main(String[] args) {
        double[][] matrix = {
            {1.0, 1, 0},
            {0, 1.0, 1},
            {0.1, 1, 1}
        };

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
                if (j < matrix[i].length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }
}
