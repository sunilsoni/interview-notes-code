package com.codesignal.bankingsystem;

import java.util.*;
import java.util.stream.Collectors;

class BankingSystemImpl {

    private final Map<String, Long> balances = new HashMap<>();
    private final Map<String, Long> outgoing = new HashMap<>();
    // ---- Level 3 scheduling state ----
    private final Map<String, Payment> paymentsById = new HashMap<>();
    // Bucket payments by due timestamp; list order preserves creation order
    private final TreeMap<Integer, List<Payment>> dueBuckets = new TreeMap<>();
    private int paymentSeq = 0;
    private int creationSeq = 0;
    private int lastProcessedTime = Integer.MIN_VALUE;

    // Process all scheduled payments due up to (and including) 'timestamp'.
    // Must run BEFORE any other operations at that timestamp.
    private void advanceTo(int timestamp) {
        // no-op if we already processed this moment
        if (timestamp < lastProcessedTime) return;
        NavigableMap<Integer, List<Payment>> dueNow =
                dueBuckets.headMap(timestamp, true);
        if (!dueNow.isEmpty()) {
            // collect keys to remove after processing to avoid concurrent modification
            List<Integer> keys = new ArrayList<>(dueNow.keySet());
            for (Integer ts : keys) {
                List<Payment> batch = dueBuckets.remove(ts);
                if (batch == null) continue;
                // payments within the same timestamp must respect creation order
                for (Payment p : batch) {
                    if (p.processed || p.canceled) continue;
                    Long bal = balances.get(p.accountId);
                    if (bal == null) continue; // account no longer exists (defensive)
                    if (bal >= p.amount) {
                        long newBal = bal - p.amount;
                        balances.put(p.accountId, newBal);
                        outgoing.put(p.accountId, outgoing.getOrDefault(p.accountId, 0L) + p.amount);
                    }
                    // whether skipped or executed, mark processed (it was “attempted”)
                    p.processed = true;
                }
            }
        }
        lastProcessedTime = timestamp;
    }

    // ---------- Level 1 ----------
    // @Override
    public boolean createAccount(int timestamp, String accountId) {
        advanceTo(timestamp);
        if (accountId == null || accountId.trim().isEmpty()) return false;
        boolean created = balances.putIfAbsent(accountId, 0L) == null;
        // keep outgoing map in sync for ranking
        outgoing.putIfAbsent(accountId, 0L);
        return created;
    }

    //   @Override
    public Optional<Integer> deposit(int timestamp, String accountId, int amount) {
        advanceTo(timestamp);
        if (amount < 0) return Optional.empty();
        Long bal = balances.get(accountId);
        if (bal == null) return Optional.empty();
        long newBal = bal + (long) amount;
        balances.put(accountId, newBal);
        return Optional.of((int) newBal);
    }

    //  @Override
    public Optional<Integer> transfer(int timestamp, String sourceAccountId, String targetAccountId, int amount) {
        advanceTo(timestamp);
        if (amount < 0) return Optional.empty();
        if (Objects.equals(sourceAccountId, targetAccountId)) return Optional.empty();
        Long sb = balances.get(sourceAccountId);
        Long tb = balances.get(targetAccountId);
        if (sb == null || tb == null) return Optional.empty();
        if (sb < amount) return Optional.empty();
        long newSb = sb - amount;
        long newTb = tb + amount;
        balances.put(sourceAccountId, newSb);
        balances.put(targetAccountId, newTb);
        outgoing.put(sourceAccountId, outgoing.getOrDefault(sourceAccountId, 0L) + amount);
        return Optional.of((int) newSb);
    }

    // ---------- Level 2 ----------
    //  @Override
    public List<String> topSpenders(int timestamp, int n) {
        advanceTo(timestamp);
        if (n <= 0) return Collections.emptyList();
        return balances.keySet().stream()
                .map(id -> Map.entry(id, outgoing.getOrDefault(id, 0L)))
                .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue).reversed()
                        .thenComparing(Map.Entry::getKey))
                .limit(n)
                .map(e -> e.getKey() + "(" + e.getValue().intValue() + ")")
                .collect(Collectors.toList());
    }

    // ---------- Level 3 ----------
    // @Override
    public Optional<String> schedulePayment(int timestamp, String accountId, int amount, int delay) {
        advanceTo(timestamp); // process older dues first
        if (!balances.containsKey(accountId) || amount < 0 || delay < 0) return Optional.empty();

        String id = "payment" + (++paymentSeq);
        Payment p = new Payment(id, accountId, amount, timestamp + delay, ++creationSeq);
        paymentsById.put(id, p);
        dueBuckets.computeIfAbsent(p.dueAt, k -> new ArrayList<>()).add(p);
        return Optional.of(id);
    }

    // @Override
    public boolean cancelPayment(int timestamp, String accountId, String paymentId) {
        advanceTo(timestamp); // payments due now must fire before we cancel anything at this ts
        Payment p = paymentsById.get(paymentId);
        if (p == null || p.processed || p.canceled) return false;
        if (!Objects.equals(p.accountId, accountId)) return false;
        p.canceled = true;
        return true;
    }

    private static final class Payment {
        final String id;
        final String accountId;
        final int amount;
        final int dueAt;          // timestamp when it should run
        final int creationOrder;  // tie-breaker for “same moment” payments
        boolean canceled = false;
        boolean processed = false;

        Payment(String id, String accountId, int amount, int dueAt, int creationOrder) {
            this.id = id;
            this.accountId = accountId;
            this.amount = amount;
            this.dueAt = dueAt;
            this.creationOrder = creationOrder;
        }
    }
}