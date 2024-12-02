package com.interview.notes.code.year.y2024.oct24.test14;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

class Solution {
    static public void main(String[] args) {
        testSampleCase();
        testLargeDataCase();
        testEdgeCases();
    }

    public static void testSampleCase() {
        runTest("Sample Case", 2, 5);
    }

    public static void testLargeDataCase() {
        runTest("Large Data Case", 10, 100000);
    }

    public static void testEdgeCases() {
        runTest("Edge Case 1", 1, 1);
        runTest("Edge Case 2", 10, 100000);
    }

    private static void runTest(String testName, int threadCount, int transactionsPerThread) {
        Account account = new Account();
        Transaction transaction = new Transaction(account);

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor.execute(new TransactionRunnable(transaction, transactionsPerThread));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int finalBalance = account.getBalance();
        long depositSum = transaction.getDepositSum();
        long withdrawSum = transaction.getWithdrawSum();
        long failedWithdrawSum = transaction.getFailedWithdrawSum();

        System.out.println(testName + ":");
        System.out.println("Total Transactions: " + (threadCount * transactionsPerThread));
        System.out.println("Final Balance: $" + finalBalance);
        System.out.println("Deposit Sum: $" + depositSum);
        System.out.println("Withdraw Sum: $" + withdrawSum);
        System.out.println("Failed Withdraw Sum: $" + failedWithdrawSum);
        System.out.println("Validation: " + (finalBalance == depositSum - withdrawSum ? "PASS" : "FAIL"));
        System.out.println();
    }

    static class Account {
        private final int STRIPE_COUNT = 16;
        private final AtomicInteger[] balances;

        public Account() {
            balances = new AtomicInteger[STRIPE_COUNT];
            for (int i = 0; i < STRIPE_COUNT; i++) {
                balances[i] = new AtomicInteger(0);
            }
        }

        private int getStripe(int money) {
            return Math.abs(money) % STRIPE_COUNT;
        }

        public void deposit(int money) {
            balances[getStripe(money)].addAndGet(money);
        }

        public boolean withdraw(int money) {
            AtomicInteger stripe = balances[getStripe(money)];
            int current, next;
            do {
                current = stripe.get();
                next = current - money;
                if (next < 0) return false;
            } while (!stripe.compareAndSet(current, next));
            return true;
        }

        public int getBalance() {
            return Arrays.stream(balances).mapToInt(AtomicInteger::get).sum();
        }
    }

    static class Transaction {
        private final Account account;
        private final LongAdder depositSum = new LongAdder();
        private final LongAdder withdrawSum = new LongAdder();
        private final LongAdder failedWithdrawSum = new LongAdder();

        public Transaction(Account account) {
            this.account = account;
        }

        public void deposit(int money) {
            account.deposit(money);
            depositSum.add(money);
        }

        public void withdraw(int money) {
            if (account.withdraw(money)) {
                withdrawSum.add(money);
            } else {
                failedWithdrawSum.add(money);
            }
        }

        public long getDepositSum() {
            return depositSum.sum();
        }

        public long getWithdrawSum() {
            return withdrawSum.sum();
        }

        public long getFailedWithdrawSum() {
            return failedWithdrawSum.sum();
        }
    }

    static class TransactionRunnable implements Runnable {
        private static final ThreadLocal<Random> RANDOM = ThreadLocal.withInitial(Random::new);
        private final Transaction transaction;
        private final int transactionsCount;

        public TransactionRunnable(Transaction transaction, int transactionsCount) {
            this.transaction = transaction;
            this.transactionsCount = transactionsCount;
        }

        @Override
        public void run() {
            Random random = RANDOM.get();
            for (int i = 0; i < transactionsCount; i++) {
                int money = random.nextInt(100) + 1;
                if (random.nextBoolean()) {
                    transaction.deposit(money);
                } else {
                    transaction.withdraw(money);
                }
            }
        }
    }
}
