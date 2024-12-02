package com.interview.notes.code.year.y2024.oct24.test14;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Account {
    private int balance = 0;

    // Synchronized deposit method to avoid race conditions
    public synchronized String deposit(int money) {
        balance += money;
        return "Depositing $" + money;
    }

    // Synchronized withdraw method to avoid race conditions
    public synchronized String withdraw(int money) {
        if (balance < money) {
            return "Withdrawing $" + money + " (Insufficient Balance)";
        } else {
            balance -= money;
            return "Withdrawing $" + money;
        }
    }

    // Synchronized method to return the current balance
    public synchronized int getBalance() {
        return balance;
    }
}

class Transaction {
    private Account account;
    private List<String> transactions;

    public Transaction(Account account) {
        this.account = account;
        this.transactions = new ArrayList<>();
    }

    public void deposit(int money) {
        String transactionLog = account.deposit(money);
        transactions.add(transactionLog);
    }

    public void withdraw(int money) {
        String transactionLog = account.withdraw(money);
        transactions.add(transactionLog);
    }

    public List<String> getTransactionLogs() {
        return transactions;
    }
}

class TransactionRunnable implements Runnable {
    private static final SecureRandom RANDOM_GENERATOR = new SecureRandom();
    private final Transaction transaction;
    private final int transactionsCount;

    public TransactionRunnable(Transaction transaction, int transactionsCount) {
        this.transaction = transaction;
        this.transactionsCount = transactionsCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < transactionsCount; i++) {
            int transactionType = RANDOM_GENERATOR.nextInt(2);  // 0 for deposit, 1 for withdraw
            int money = RANDOM_GENERATOR.nextInt(100) + 1;  // Random amount between 1 and 100

            if (transactionType == 0) {
                transaction.deposit(money);
            } else {
                transaction.withdraw(money);
            }
        }
    }
}

public class BankTransactionTest {

    public static void main(String[] args) throws InterruptedException {
        // Example test case: 2 threads, each performing 3 transactions
        int threadsCount = 2;
        int transactionsCount = 3;

        Account account = new Account();
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        Transaction transaction = new Transaction(account);

        // Create and execute threads
        for (int i = 0; i < threadsCount; i++) {
            executor.submit(new TransactionRunnable(transaction, transactionsCount));
        }

        // Shutdown executor and wait for threads to finish
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Output results
        List<String> transactionLogs = transaction.getTransactionLogs();
        for (String log : transactionLogs) {
            System.out.println(log);
        }
        System.out.println("Balance $" + account.getBalance());

        // Example: You can add test case assertions here if required.
    }
}
