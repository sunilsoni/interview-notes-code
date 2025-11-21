package com.interview.notes.code.year.y2024.dec24.test9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // 1. Create a fixed thread pool with 3 threads
        //ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorService executor = new ThreadPoolExecutor(
                2, // Core pool size
                4, // Maximum pool size
                60, // Keep-alive time
                TimeUnit.SECONDS, // Time unit for keep-alive
                new LinkedBlockingQueue<>(2) // Task queue
        );

        for (int i = 1; i <= 6; i++) {
            executor.execute(() -> {
                System.out.println("Task running on: " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();

        // 2. Create multiple tasks
        for (int i = 1; i <= 5; i++) {
            Runnable task = new WorkerTask("Task " + i);
            System.out.println("Created: " + task);

            // 3. Submit tasks to thread pool
            executor.execute(task);
        }

        // 4. Shutdown the thread pool
        executor.shutdown();

        // 5. Wait for all tasks to complete
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed");
    }
}

// Worker Task Class
class WorkerTask implements Runnable {
    private final String name;

    public WorkerTask(String name) {
        this.name = name;
    }

    public void run() {
        try {
            System.out.println(name + " is running on thread: "
                    + Thread.currentThread().getName());
            // Simulate some work
            Thread.sleep(1000);
            System.out.println(name + " completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return name;
    }
}
