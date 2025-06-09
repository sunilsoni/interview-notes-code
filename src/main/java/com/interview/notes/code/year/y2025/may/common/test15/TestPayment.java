package com.interview.notes.code.year.y2025.may.common.test15;

import java.util.*;

enum TransactionType { P2P, P2M, SELF }

class TransactionSummary {
    private final int transactionId;
    private final boolean isSenderEligibleForReward;

    public TransactionSummary(int transactionId, boolean isSenderEligibleForReward) {
        this.transactionId = transactionId;
        this.isSenderEligibleForReward = isSenderEligibleForReward;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public boolean isSenderEligibleForReward() {
        return isSenderEligibleForReward;
    }
}

class Payment {
    private Map<Integer, Integer> p2pCount = new HashMap<>();
    private Map<Integer, Integer> p2mCount = new HashMap<>();
    private Map<Integer, Integer> selfCount = new HashMap<>();
    private Map<Integer, Long> p2mSum = new HashMap<>();

    // Min-heap to track top 100 users by P2M sum
    private PriorityQueue<User> heap = new PriorityQueue<>();
    private Map<Integer, User> userMap = new HashMap<>();

    private static class User implements Comparable<User> {
        int id;
        long sum;
        User(int id, long sum) {
            this.id = id;
            this.sum = sum;
        }
        @Override
        public int compareTo(User o) {
            int cmp = Long.compare(this.sum, o.sum);
            return (cmp != 0) ? cmp : Integer.compare(this.id, o.id);
        }
    }

    public TransactionSummary makePayment(int transactionId, int senderId, int amount, TransactionType transactionType) {
        if (transactionType == TransactionType.P2P) {
            p2pCount.put(senderId, p2pCount.getOrDefault(senderId, 0) + 1);
        } else if (transactionType == TransactionType.P2M) {
            p2mCount.put(senderId, p2mCount.getOrDefault(senderId, 0) + 1);
            long prevSum = p2mSum.getOrDefault(senderId, 0L);
            long newSum = prevSum + amount;
            p2mSum.put(senderId, newSum);

            if (userMap.containsKey(senderId)) {
                heap.remove(userMap.get(senderId));
            }
            User u = new User(senderId, newSum);
            userMap.put(senderId, u);
            heap.offer(u);
            if (heap.size() > 100) {
                User removed = heap.poll();
                userMap.remove(removed.id);
                p2mSum.remove(removed.id, removed.sum);
            }
        } else {
            selfCount.put(senderId, selfCount.getOrDefault(senderId, 0) + 1);
        }
        boolean eligible = (transactionType == TransactionType.P2M
                && p2mSum.containsKey(senderId)
                && heap.contains(userMap.get(senderId)));
        return new TransactionSummary(transactionId, eligible);
    }

    public int getNumberOfTransactions(int senderId, TransactionType transactionType) {
        switch (transactionType) {
            case P2P:
                return p2pCount.getOrDefault(senderId, 0);
            case P2M:
                return p2mCount.getOrDefault(senderId, 0);
            default:
                return selfCount.getOrDefault(senderId, 0);
        }
    }
}

public class TestPayment {
    public static void main(String[] args) {
        Payment payment = new Payment();
        // Sample test cases
        TransactionSummary ts0 = payment.makePayment(0, 2,    100, TransactionType.P2P);
        System.out.println(ts0.getTransactionId() + " " + ts0.isSenderEligibleForReward());

        TransactionSummary ts1 = payment.makePayment(1, 4,     18, TransactionType.P2M);
        System.out.println(ts1.getTransactionId() + " " + ts1.isSenderEligibleForReward());

        TransactionSummary ts2 = payment.makePayment(2, 2,     50, TransactionType.P2M);
        System.out.println(ts2.getTransactionId() + " " + ts2.isSenderEligibleForReward());

        int countP2P = payment.getNumberOfTransactions(2, TransactionType.P2P);
        System.out.println(countP2P);
    }
}