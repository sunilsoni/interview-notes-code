package com.interview.notes.code.year.y2024.dec24.test9;

import java.util.LinkedList;
import java.util.Queue;

public class CustomThreadPool {
    private final int nThreads;
    private final WorkerThread[] workers;
    private final Queue<Runnable> taskQueue;

    public CustomThreadPool(int nThreads) {
        this.nThreads = nThreads;
        taskQueue = new LinkedList<>();
        workers = new WorkerThread[nThreads];

        // Create and start worker threads
        for (int i = 0; i < nThreads; i++) {
            workers[i] = new WorkerThread();
            workers[i].start();
        }
    }

    public synchronized void execute(Runnable task) {
        taskQueue.offer(task);
        notify(); // Notify waiting worker threads that a task is available
    }

    public synchronized void shutdown() {
        for (WorkerThread worker : workers) {
            worker.stopWorker();
        }
        notifyAll(); // Wake up all waiting threads
    }

    private class WorkerThread extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            Runnable task;

            while (running) {
                synchronized (CustomThreadPool.this) {
                    while (taskQueue.isEmpty() && running) {
                        try {
                            CustomThreadPool.this.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    if (!running) {
                        break;
                    }

                    task = taskQueue.poll();
                }

                try {
                    if (task != null) {
                        task.run();
                    }
                } catch (RuntimeException e) {
                    System.err.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }

        public void stopWorker() {
            running = false;
        }
    }
}
