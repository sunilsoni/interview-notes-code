package com.interview.notes.code.year.y2024.may24.test3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test3 {
    public static void main(String[] args) {

        byte a = 10;
        float b;
        int c;
        b = (float) ++a * a;
        c = (short) b;
        System.out.println(a++ + ++b + .1);
        System.out.println(b + .1);
        System.out.println(c + .1);


        ExecutorService threadpool = Executors.newFixedThreadPool(2);
        Runnable runnable = () -> System.out.println("I am a runnable");
        Callable<Object> callable = () -> {
            System.out.println("I am a Callable");
            return null;
        };
        // Future<Object> runnableResult = threadpool.submit(runnable);
        Future<Object> callableResult = threadpool.submit(callable);
    }
}
