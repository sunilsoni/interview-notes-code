package com.interview.notes.code.months.dec24.test9;

public class CustomThreadPoolExample {
    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        CustomThreadPool threadPool = new CustomThreadPool(3);

        // Submit tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            threadPool.execute(() -> {
                System.out.println("Task " + taskId + " is running on thread: "
                        + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " is completed");
            });
        }

        // Sleep for a while to let tasks execute
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Shutdown the thread pool
        threadPool.shutdown();
    }
}
