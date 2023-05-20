package com.interview.notes.code.test.test2;

class EvenOddThread implements Runnable {

    private int[] numbers;
    private int start;
    private int end;

    public EvenOddThread(int[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            if (numbers[i] % 2 == 0) {
                System.out.println(numbers[i]);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        EvenOddThread evenThread = new EvenOddThread(numbers, 0, 5);
        EvenOddThread oddThread = new EvenOddThread(numbers, 5, 10);

        Thread even = new Thread(evenThread);
        Thread odd = new Thread(oddThread);

        even.start();
        odd.start();

        try {
            even.join();
            odd.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
