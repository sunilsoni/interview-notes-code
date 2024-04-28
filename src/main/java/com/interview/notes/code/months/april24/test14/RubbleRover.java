package com.interview.notes.code.months.april24.test14;

import java.util.Scanner;

public class RubbleRover {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int P = scanner.nextInt(); // RubbleRover's initial position
        int N = scanner.nextInt(); // Number of positions with garbage
        int[] garbageArray = new int[N]; // Array indicating presence of garbage (0 or 1)

        for (int i = 0; i < N; i++) {
            garbageArray[i] = scanner.nextInt();
        }

        // Initialize variables
        int collectedGarbage = 0;
        boolean unsure = false;

        // Check left side
        for (int i = P - 2; i >= 0; i--) {
            if (garbageArray[i] == 1) {
                collectedGarbage++;
                unsure = false;
            } else if (garbageArray[i] == 0 && !unsure) {
                unsure = true;
            } else if (garbageArray[i] == 0 && unsure) {
                break;
            }
        }

        // Check right side
        unsure = false;
        for (int i = P; i < N; i++) {
            if (garbageArray[i] == 1) {
                collectedGarbage++;
                unsure = false;
            } else if (garbageArray[i] == 0 && !unsure) {
                unsure = true;
            } else if (garbageArray[i] == 0 && unsure) {
                break;
            }
        }

        // Output result
        System.out.println(collectedGarbage);
    }
}
