package com.interview.notes.code.year.y2024.oct24.test14;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/*
1. Java Threads: Transactions
In this challenge, simulate a banking system. Create the Account and Transaction classes.
1. The Account class has a data member int balance, initially assigned to zero. The class should implement the following three methods:
1. String deposit(int money) to add money to the balance. This method should return a string that describes the deposit transaction, i.e., "Depositing #money".
2. String withdraw(int money) to subtract money from the balance. This method should return a string that describes the withdraw transaction, i.e., "Withdrawing $money". Note that, if there is insufficient balance to successfully withdraw the desired amount, then the balance should not be adjusted, and the returned string should be "Withdrawing $money (Insufficient Balance)".
3. int getBalance) to return the account balance.
2. The Transaction class has two data members Account account and List<String> transactions. The class should implement the following three methods:
1. void deposit(int money) to invoke the deposit method in the Account class. This should add the transaction message to the transactions list.
2. void withdraw(int money) to invoke the withdraw method in the Account class. This should add the transaction message to the transactions list.
3. List<String> getTransaction() to return the transactions.
Evaluation
The locked stub code in the editor validates the correctness of the Account and Transaction class implementations by making deposit and withdrawal transactions using threads. The locked stub code prints each transaction followed by the account balance. The output of the execution is non-deterministic, so the checker performs each of the transactions in the provided order. If all the transactions are executed correctly given a starting balance of $0, then the checker considers such transactions valid. For example, the following list of transactions is valid:
Depositing $59
Withdrawing $2
Depositing $62
Depositing $16
 */
class BankTxnFInal {
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

        // Reset account and transaction for the second test
        account = new Account();
        transaction = new Transaction(account);

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
        AtomicInteger balance = new AtomicInteger(0);
        transactions.forEach(transaction -> {
            if (transaction.startsWith("Depositing")) {
                balance.addAndGet(Integer.parseInt(transaction.split("\\$")[1]));
            } else if (transaction.startsWith("Withdrawing") && !transaction.endsWith("(Insufficient Balance)")) {
                balance.addAndGet(-Integer.parseInt(transaction.split("\\$")[1]));
            }
        });
        return balance.get() == finalBalance;
    }

    static class Account {
        private AtomicInteger balance = new AtomicInteger(0);
        private ReentrantLock lock = new ReentrantLock();

        public String deposit(int money) {
            lock.lock();
            try {
                balance.addAndGet(money);
                return "Depositing $" + money;
            } finally {
                lock.unlock();
            }
        }

        public String withdraw(int money) {
            lock.lock();
            try {
                if (balance.get() >= money) {
                    balance.addAndGet(-money);
                    return "Withdrawing $" + money;
                } else {
                    return "Withdrawing $" + money + " (Insufficient Balance)";
                }
            } finally {
                lock.unlock();
            }
        }

        public int getBalance() {
            return balance.get();
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
