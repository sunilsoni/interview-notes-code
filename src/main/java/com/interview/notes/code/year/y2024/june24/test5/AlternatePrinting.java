package com.interview.notes.code.year.y2024.june24.test5;

/**
 * 1A2B3C4D5E6F7G
 * <p>
 * o there is. You are having one pattern, so you just analyze this pattern. This is having one, a, 2b, 3c, 4d, 5b, 6f, 70. So once it is printing the number, then it is printing the alphabet, then again, number then alphabet. So it is printing alternatively first number, then alphabet, then again, number, then alphabet. So you have to write the program for me using a multi study, so that it can print up to n numbers, like
 */
public class AlternatePrinting {

    public static void main(String[] args) {
        int n = 7; // Number of pairs to print (adjust as needed)

        // Create two threads: one for numbers and one for alphabets
        Thread numberThread = new Thread(new NumberPrinter(n));
        Thread alphabetThread = new Thread(new AlphabetPrinter(n));

        // Start both threads
        numberThread.start();
        alphabetThread.start();
    }

    // Runnable implementation for printing numbers
    static class NumberPrinter implements Runnable {
        private final int n;

        public NumberPrinter(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                System.out.print(i);
                try {
                    // Sleep to simulate some delay between number and alphabet printing
                    Thread.sleep(200); // Adjust delay as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Runnable implementation for printing alphabets
    static class AlphabetPrinter implements Runnable {
        private final int n;

        public AlphabetPrinter(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 0; i < n; i++) {
                System.out.print((char) ('A' + i));
                try {
                    // Sleep to simulate some delay between number and alphabet printing
                    Thread.sleep(200); // Adjust delay as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
