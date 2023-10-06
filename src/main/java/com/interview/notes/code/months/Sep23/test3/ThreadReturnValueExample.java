package com.interview.notes.code.months.Sep23.test3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadReturnValueExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // Create a Callable that returns a result
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // Perform some computation
                return 42;
            }
        };

        // Submit the Callable to the ExecutorService
        Future<Integer> future = executorService.submit(callable);

        // Get the result from the future
        int result = future.get();

        System.out.println("Result from the thread: " + result);

        // Shutdown the ExecutorService
        executorService.shutdown();
    }
}
