package com.interview.notes.code.year.y2026.march.Hackerank.tes1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ThreadedPrimeAlternator {
    private final List<Integer> result = new ArrayList<>();
    private int turn = 1;
    private boolean done = false;

    public static void main(String[] args) {
        test(10, List.of(2, 7, 3));
        test(21, List.of(2, 13, 3, 17, 5, 19, 7));

        ThreadedPrimeAlternator p = new ThreadedPrimeAlternator();
        System.out.println("Large Data Test (N=100000) : " + (!p.execute(100000).isEmpty() ? "PASS" : "FAIL"));
    }

    private static void test(int n, List<Integer> expected) {
        System.out.println("Test N=" + n + " : " + (expected.equals(new ThreadedPrimeAlternator().execute(n)) ? "PASS" : "FAIL"));
    }

    private boolean isPrime(int n) {
        return n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0);
    }

    private void findPrimes(int start, int end, int tId) {
        IntStream.rangeClosed(start, end).filter(this::isPrime).takeWhile(i -> !done).forEach(i -> {
            synchronized (this) {
                while (turn != tId && !done) {
                    try { wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                }
                if (!done) {
                    result.add(i);
                    turn = tId == 1 ? 2 : 1;
                    notifyAll();
                }
            }
        });

        synchronized (this) {
            while (turn != tId && !done) {
                try { wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            done = true;
            notifyAll();
        }
    }

    public List<Integer> execute(int n) {
        Thread t1 = new Thread(() -> findPrimes(1, (n + 1) / 2, 1));
        Thread t2 = new Thread(() -> findPrimes((n + 1) / 2 + 1, n, 2));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return result;
    }
}