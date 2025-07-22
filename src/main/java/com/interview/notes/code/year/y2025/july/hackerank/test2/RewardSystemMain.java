package com.interview.notes.code.year.y2025.july.hackerank.test2;

import java.util.*;

enum TransactionType {
    P2M, P2P, Self;
}

class TransactionSummary {
    int transactionId;
    boolean isSenderEligibleForReward;

    public TransactionSummary(int transactionId, boolean isSenderEligibleForReward) {
        this.transactionId = transactionId;
        this.isSenderEligibleForReward = isSenderEligibleForReward;
    }

    @Override
    public String toString() {
        return transactionId + " " + isSenderEligibleForReward;
    }
}

class Payment {
    private static final int TOP_K = 100;
    // Track transaction counts per user/type
    private Map<Integer, Map<TransactionType, Integer>> txnCounts = new HashMap<>();
    // Track total P2M amount per user
    private Map<Integer, Long> userP2mSum = new HashMap<>();
    // Min-heap of size 100 for top P2M senders (amount, senderId)
    private PriorityQueue<long[]> top100 = new PriorityQueue<>((a, b) -> {
        if (a[0] == b[0]) return Long.compare(a[1], b[1]);
        return Long.compare(a[0], b[0]);
    });
    private Set<Integer> top100Ids = new HashSet<>();

    // Rebuilds the heap if a user's P2M amount increases
    private void updateTop100(int senderId, long newTotal) {
        if (top100Ids.contains(senderId)) {
            // Remove old entry and add new
            top100.removeIf(pair -> pair[1] == senderId);
            top100.add(new long[]{newTotal, senderId});
        } else {
            if (top100.size() < TOP_K) {
                top100.add(new long[]{newTotal, senderId});
                top100Ids.add(senderId);
            } else if (top100.peek()[0] < newTotal || (top100.peek()[0] == newTotal && top100.peek()[1] > senderId)) {
                long[] removed = top100.poll();
                if (removed != null) top100Ids.remove((int) removed[1]);
                top100.add(new long[]{newTotal, senderId});
                top100Ids.add(senderId);
            }
        }
    }

    public TransactionSummary makePayment(int transactionId, int senderId, int amount, TransactionType transactionType) {
        // Count transaction
        txnCounts.computeIfAbsent(senderId, k -> new EnumMap<>(TransactionType.class))
                .merge(transactionType, 1, Integer::sum);

        boolean eligible = false;

        if (transactionType == TransactionType.P2M) {
            long newTotal = userP2mSum.getOrDefault(senderId, 0L) + amount;
            userP2mSum.put(senderId, newTotal);
            updateTop100(senderId, newTotal);
            eligible = top100Ids.contains(senderId);
        }
        return new TransactionSummary(transactionId, eligible);
    }

    public int getNumberOfTransactions(int senderId, TransactionType transactionType) {
        return txnCounts.getOrDefault(senderId, Collections.emptyMap())
                .getOrDefault(transactionType, 0);
    }
}

// ---------- Main method for I/O and testing ----------
public class RewardSystemMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = Integer.parseInt(sc.nextLine().trim());
        Payment payment = new Payment();

        // For large test validation
        List<String> output = new ArrayList<>();

        while (total-- > 0) {
            String[] parts = sc.nextLine().split(" ");
            if (parts[0].equals("makePayment")) {
                int transactionId = Integer.parseInt(parts[1]);
                int senderId = Integer.parseInt(parts[2]);
                int amount = Integer.parseInt(parts[3]);
                TransactionType tt = TransactionType.valueOf(parts[4]);
                TransactionSummary ts = payment.makePayment(transactionId, senderId, amount, tt);
                System.out.println(ts);
                output.add(ts.toString());
            } else if (parts[0].equals("getNumberOfTransactions")) {
                int senderId = Integer.parseInt(parts[1]);
                TransactionType tt = TransactionType.valueOf(parts[2]);
                int count = payment.getNumberOfTransactions(senderId, tt);
                System.out.println(count);
                output.add(String.valueOf(count));
            }
        }

        // Uncomment for large-scale testing
        // testLargeScale();

        // Optional: Call validateOutput(output, expectedOutput) for test harness
    }

    // -------- Test case generator and validation ----------
    // Simulates large-scale load and verifies correctness
    public static void testLargeScale() {
        Payment p = new Payment();
        int users = 1000;
        int txns = 150000;
        for (int i = 1; i <= txns; i++) {
            int sender = (i % users) + 1;
            int amount = (i % 1000) + 1;
            p.makePayment(i, sender, amount, TransactionType.P2M);
        }
        // Query: Is user 1 in top 100?
        TransactionSummary last = p.makePayment(txns + 1, 1, 1000, TransactionType.P2M);
        System.out.println("User 1 eligible: " + last.isSenderEligibleForReward);
    }
}
