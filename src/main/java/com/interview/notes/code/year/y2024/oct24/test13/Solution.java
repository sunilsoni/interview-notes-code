package com.interview.notes.code.year.y2024.oct24.test13;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Solution {
    public static void main(String[] args) {
        testSampleCase();
        testLargeDataCase();
        testEdgeCases();
    }

    public static void testSampleCase() {
        Account account = new Account();
        Transaction transaction = new Transaction(account);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new TransactionRunnable(transaction, 3));
        executor.execute(new TransactionRunnable(transaction, 2));

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> transactions = transaction.getTransactions();
        System.out.println("Sample Case Transactions:");
        transactions.forEach(System.out::println);
        System.out.println("Balance $" + account.getBalance());
        System.out.println("Sample Case: " + (validateTransactions(transactions, account.getBalance()) ? "PASS" : "FAIL"));
        System.out.println();
    }

    public static void testLargeDataCase() {
        Account account = new Account();
        Transaction transaction = new Transaction(account);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(new TransactionRunnable(transaction, 10000));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> transactions = transaction.getTransactions();
        System.out.println("Large Data Case:");
        System.out.println("Total Transactions: " + transactions.size());
        System.out.println("Balance $" + account.getBalance());
        System.out.println("Large Data Case: " + (validateTransactions(transactions, account.getBalance()) ? "PASS" : "FAIL"));
        System.out.println();
    }

    public static void testEdgeCases() {
        Account account = new Account();
        Transaction transaction = new Transaction(account);

        // Test case 1: Single thread, single transaction
        new TransactionRunnable(transaction, 1).run();
        System.out.println("Edge Case 1: " + (validateTransactions(transaction.getTransactions(), account.getBalance()) ? "PASS" : "FAIL"));

        // Test case 2: Maximum number of threads and transactions
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(new TransactionRunnable(transaction, 10000));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Edge Case 2: " + (validateTransactions(transaction.getTransactions(), account.getBalance()) ? "PASS" : "FAIL"));
    }

    private static boolean validateTransactions(List<String> transactions, int finalBalance) {
        int balance = 0;
        for (String transaction : transactions) {
            if (transaction.startsWith("Depositing")) {
                balance += Integer.parseInt(transaction.split("\\$")[1]);
            } else if (transaction.startsWith("Withdrawing")) {
                int amount = Integer.parseInt(transaction.split("\\$")[1].split(" ")[0]);
                if (balance >= amount) {
                    balance -= amount;
                }
            }
        }
        return balance == finalBalance;
    }

    static class Account {
        private int balance = 0;

        public synchronized String deposit(int money) {
            balance += money;
            return "Depositing $" + money;
        }

        public synchronized String withdraw(int money) {
            if (balance >= money) {
                balance -= money;
                return "Withdrawing $" + money;
            } else {
                return "Withdrawing $" + money + " (Insufficient Balance)";
            }
        }

        public int getBalance() {
            return balance;
        }
    }

    static class Transaction {
        private Account account;
        private List<String> transactions;

        public Transaction(Account account) {
            this.account = account;
            this.transactions = new CopyOnWriteArrayList<>();
        }

        public void deposit(int money) {
            String result = account.deposit(money);
            transactions.add(result);
        }

        public void withdraw(int money) {
            String result = account.withdraw(money);
            transactions.add(result);
        }

        public List<String> getTransactions() {
            return new ArrayList<>(transactions);
        }
    }

    static class TransactionRunnable implements Runnable {
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
                int transactionType = RANDOM_GENERATOR.nextInt(2);
                int money = RANDOM_GENERATOR.nextInt(100) + 1;

                if (transactionType == 0) {
                    transaction.deposit(money);
                } else {
                    transaction.withdraw(money);
                }
            }
        }
    }
}
