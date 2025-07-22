package com.interview.notes.code.year.y2025.july.hackerank.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

enum TransactionType {
    P2M, P2P, Self
}

class TransactionSummary {
    int transactionId;
    boolean isSenderEligibleForReward;

    TransactionSummary(int transactionId, boolean isSenderEligibleForReward) {
        this.transactionId = transactionId;
        this.isSenderEligibleForReward = isSenderEligibleForReward;
    }
}

public class Payment {
    private static final int TOP_K = 100;
    // Track counts of each transaction type per sender
    private final Map<Integer, int[]> txCount = new HashMap<>();
    // Track cumulative P2M amounts per sender
    private final Map<Integer, Long> p2mSum = new HashMap<>();
    // Maintain the top‐100 senders by P2M sum
    private final TreeSet<Sender> topSet = new TreeSet<>();
    private final Set<Integer> topSenders = new HashSet<>();

    // ----------------------------------------------------------------
    // main() to drive the custom‐testing harness
    // ----------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int totalRequests = Integer.parseInt(line.trim());

        Payment payment = new Payment();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < totalRequests; i++) {
            String request = br.readLine();
            if (request == null || request.isEmpty()) {
                i--;
                continue;
            }
            String[] parts = request.split(" ");
            switch (parts[0]) {
                case "makePayment": {
                    int txnId = Integer.parseInt(parts[1]);
                    int senderId = Integer.parseInt(parts[2]);
                    int amount = Integer.parseInt(parts[3]);
                    TransactionType type = TransactionType.valueOf(parts[4]);

                    TransactionSummary summary =
                            payment.makePayment(txnId, senderId, amount, type);
                    out.append(summary.transactionId)
                            .append(' ')
                            .append(summary.isSenderEligibleForReward)
                            .append('\n');
                    break;
                }
                case "getNumberOfTransactions": {
                    int senderId = Integer.parseInt(parts[1]);
                    TransactionType type = TransactionType.valueOf(parts[2]);
                    int count = payment.getNumberOfTransactions(senderId, type);
                    out.append(count).append('\n');
                    break;
                }
            }
        }

        // print all at once
        System.out.print(out);
    }

    /**
     * Process a transaction.
     *
     * @param transactionId   Unique ID of this transaction
     * @param senderId        ID of the user sending the payment
     * @param amount          Amount sent (1 ≤ amount ≤ 1000)
     * @param transactionType Type of transaction (P2P, P2M, or Self)
     * @return A TransactionSummary containing the transactionId
     * and whether the sender is eligible for a reward
     */
    TransactionSummary makePayment(int transactionId,
                                   int senderId,
                                   int amount,
                                   TransactionType transactionType) {
        // 1) increment the per‐sender, per‐type count
        int[] counts = txCount.computeIfAbsent(senderId, k -> new int[3]);
        counts[transactionType.ordinal()]++;

        boolean eligible = false;
        if (transactionType == TransactionType.P2M) {
            // 2) update this sender's cumulative P2M sum
            long oldSum = p2mSum.getOrDefault(senderId, 0L);
            long newSum = oldSum + amount;
            p2mSum.put(senderId, newSum);

            boolean wasTop = topSenders.contains(senderId);
            if (wasTop) {
                // remove old entry and re‐insert with new sum
                topSet.remove(new Sender(oldSum, senderId));
                topSet.add(new Sender(newSum, senderId));
            } else {
                if (topSet.size() < TOP_K) {
                    // still room in top100
                    topSet.add(new Sender(newSum, senderId));
                    topSenders.add(senderId);
                } else {
                    // compare to the smallest in top100
                    Sender smallest = topSet.first();
                    if (newSum > smallest.sum ||
                            (newSum == smallest.sum && senderId < smallest.id)) {
                        // evict the current smallest
                        topSet.pollFirst();
                        topSenders.remove(smallest.id);
                        // insert this sender
                        topSet.add(new Sender(newSum, senderId));
                        topSenders.add(senderId);
                    }
                }
            }
            eligible = topSenders.contains(senderId);
        }

        return new TransactionSummary(transactionId, eligible);
    }

    /**
     * Query how many transactions of a given type a user has made so far.
     *
     * @param senderId        ID of the user
     * @param transactionType TransactionType to count
     * @return Number of transactions of that type made by the user
     */
    int getNumberOfTransactions(int senderId,
                                TransactionType transactionType) {
        int[] counts = txCount.get(senderId);
        return (counts == null ? 0 : counts[transactionType.ordinal()]);
    }

    // Helper for TreeSet: orders by (sum asc, senderId asc)
    private static class Sender implements Comparable<Sender> {
        long sum;
        int id;

        Sender(long sum, int id) {
            this.sum = sum;
            this.id = id;
        }

        @Override
        public int compareTo(Sender o) {
            int cmp = Long.compare(this.sum, o.sum);
            return (cmp != 0 ? cmp : Integer.compare(this.id, o.id));
        }
    }
}

/*
--------------------------------------------
Sample Input:
4
makePayment 0 2 100 P2P
makePayment 1 4 18 P2M
makePayment 2 2 50 P2M
getNumberOfTransactions 2 P2P

Sample Output:
0 false
1 true
2 true
1
--------------------------------------------
*/