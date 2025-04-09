package com.interview.notes.code.year.y2025.march.common.test21;/*
Create two thread example to print even and odd number. first thread should print odd number and the second thread should print the even number. output should be like 1,2,3,4,5,6,...100. 
Note: - we are able to identify which thread printed the value While printing the output.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {
    public static void main(String[] args) throws InterruptedException {

        List<String> output = Collections.synchronizedList(new ArrayList<>());

        EvenOdd printer = new EvenOdd(100, output);
        printer.startPrinting();

    }
}

class EvenOdd {

    private final int max;
    private final Object LOCK = new Object();
    private final List<String> output;
    private int counter = 1;

    public EvenOdd(int max, List<String> output) {
        this.max = max;
        this.output = output;
    }

    public void startPrinting() throws InterruptedException {
        Thread oddThread = new Thread(new OddThread(), "OddThread");
        Thread evenThread = new Thread(new EvenThread(), "EvenThread");

        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();
    }

    class OddThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if (counter > max) {
                        LOCK.notifyAll();
                        break;
                    }

                    if (counter % 2 != 0) {
                        String message = Thread.currentThread().getName() + " : " + counter;
                        System.out.println(message);
                        output.add(message);
                        counter++;
                        LOCK.notifyAll();
                    } else {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }

    class EvenThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if (counter > max) {
                        LOCK.notifyAll();
                        break;
                    }

                    if (counter % 2 == 0) {
                        String message = Thread.currentThread().getName() + " : " + counter;
                        System.out.println(message);
                        output.add(message);
                        counter++;
                        LOCK.notifyAll();
                    } else {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }
}



