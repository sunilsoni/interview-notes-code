package com.interview.notes.code.year.y2025.feb.common.test7;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// Transaction Processing Service
class TransactionProcessor {
    private final ExecutorService executorService;
    private final BlockingQueue<String> transactionQueue;
    private final AtomicInteger transactionCount = new AtomicInteger(0);

    public TransactionProcessor(int numThreads) {
        this.executorService = Executors.newFixedThreadPool(numThreads);
        this.transactionQueue = new LinkedBlockingQueue<>();
    }

    // Method to add transactions to the queue
    public void submitTransaction(String transaction) {
        transactionQueue.offer(transaction);
    }

    // Process transactions concurrently
    public void processTransactions() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            executorService.execute(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        String transaction = transactionQueue.poll(2, TimeUnit.SECONDS);
                        if (transaction != null) {
                            process(transaction);
                        } else {
                            break; // Exit if no transactions are available
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupt status
                    }
                }
            });
        }
    }

    private void process(String transaction) {
        System.out.println(Thread.currentThread().getName() + " processing transaction: " + transaction);
        transactionCount.incrementAndGet();
    }

    // Gracefully shut down executor
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public int getProcessedTransactionCount() {
        return transactionCount.get();
    }
}

// Main Class to Run the Transaction Processor
public class MultiThreadedTransactionProcessing {
    public static void main(String[] args) {
        TransactionProcessor processor = new TransactionProcessor(4); // 4 Worker Threads

        // Simulate adding transactions
        for (int i = 1; i <= 20; i++) {
            processor.submitTransaction("TXN" + i);
        }

        // Process transactions concurrently
        processor.processTransactions();

        // Wait before shutting down
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        processor.shutdown();
        System.out.println("Total Processed Transactions: " + processor.getProcessedTransactionCount());
    }
}
