package com.interview.notes.code.months.may24.test8;

public class Main {
    public static void main(String[] args) {
        // Example inputs from the screenshots
        int[][] examples = {
                {1, 4, 1, 6, 2, 1, 8, 2}, // Example #1
                {1, 1, 1, 4, 2, 2}        // Example #2
        };

        for (int[] example : examples) {
            Queue q = new Queue();
            System.out.println("New Test Case");

            for (int i = 0; i < example.length; i++) {
                int queryType = example[i]; // Query type (1 for push, 2 for pop)

                if (queryType == 1) {
                    int data = example[++i]; // Increment i to get the data element
                    q.push(data);
                } else if (queryType == 2) {
                    System.out.print(q.pop() + " ");
                }
            }
            System.out.println(); // Newline after each test case output
        }
    }
}
