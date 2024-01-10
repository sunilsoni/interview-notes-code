package com.interview.notes.code.months.year2023.Oct23.test12;

import java.util.Scanner;

public class ArrayRotator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("Enter array elements:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        array = rotateByTwo(array);

        System.out.println("Rotated Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    public static int[] rotateByTwo(int[] arr) {
        if (arr.length < 3) {
            return arr;
        }
        int first = arr[0];
        int second = arr[1];

        for (int i = 0; i < arr.length - 2; i++) {
            arr[i] = arr[i + 2];
        }

        arr[arr.length - 2] = first;
        arr[arr.length - 1] = second;

        return arr;
    }
}
