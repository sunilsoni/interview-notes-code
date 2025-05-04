package com.interview.notes.code.year.y2025.may.codesignal.test3;

import java.util.*;
import java.util.stream.*;
/*
WORKING 100%
### Problem Statement

You are to program a bot for a bank to automate processing of incoming requests. Each request has a timestamp (in seconds) and the requests are strictly increasing by timestamp.

There are two request types:

1. `deposit <timestamp> <holder_id> <amount>`
2. `withdraw <timestamp> <holder_id> <amount>`

* `holder_id` is 1-based index of the account (i.e., account 1 refers to `balances[0]`).
* On **withdrawal**, the account also receives **2% cashback** (rounded down) of the withdrawn amount, exactly **24 hours (86400 seconds)** later.
* If a cashback and a withdrawal/deposit occur at the same timestamp, cashback is processed first.

---

### Invalid Requests:

* Invalid account number.
* Withdrawal amount exceeds the current balance.

---

### Input

```java
int[] balances;        // balances[i] is money in (i+1)th account
String[] requests;     // list of requests to process
```

Constraints:

* 1 ≤ balances.length ≤ 100
* 0 ≤ balances\[i] ≤ 100000
* 1 ≤ requests.length ≤ 100

---

### Output

```java
int[]                   // updated balances after all requests
OR
int[] {-<request_id>}  // if any request is invalid, return -1-based index of first invalid request
```

> Note: Cashback is only applied if its time comes before or at the last request’s timestamp.

---
 */
public class BankBot {

    public static int[] solution(int[] balances, String[] requests) {
        int n = balances.length;
        long[] bal = Arrays.stream(balances).asLongStream().toArray();
        Queue<long[]> cashbackQ = new LinkedList<>(); // [cashbackTime, holderIdx, amount]
        int lastTime = 0;

        for (int i = 0; i < requests.length; i++) {
            String[] parts = requests[i].split(" ");
            int t = Integer.parseInt(parts[1]);
            // process due cashbacks
            while (!cashbackQ.isEmpty() && cashbackQ.peek()[0] <= t) {
                long[] ev = cashbackQ.poll();
                bal[(int) ev[1]] += ev[2];
            }
            lastTime = t;

            String type = parts[0];
            int idx = Integer.parseInt(parts[2]) - 1;
            int amt = Integer.parseInt(parts[3]);
            if (idx < 0 || idx >= n) {
                return new int[] { -(i+1) };
            }
            if ("deposit".equals(type)) {
                bal[idx] += amt;
            } else if ("withdraw".equals(type)) {
                if (bal[idx] < amt) {
                    return new int[] { -(i+1) };
                }
                bal[idx] -= amt;
                long cb = (amt * 2L) / 100;
                cashbackQ.offer(new long[] { t + 86400, idx, cb });
            } else {
                return new int[] { -(i+1) };
            }
        }
        // apply any remaining cashbacks due by last request time
        while (!cashbackQ.isEmpty() && cashbackQ.peek()[0] <= lastTime) {
            long[] ev = cashbackQ.poll();
            bal[(int) ev[1]] += ev[2];
        }
        return Arrays.stream(bal).mapToInt(x -> (int) x).toArray();
    }

    public static void main(String[] args) {
        class Test {
            int[] balances;
            String[] reqs;
            int[] expected;
            Test(int[] b, String[] r, int[] e) { balances = b; reqs = r; expected = e; }
        }

        List<Test> tests = Arrays.asList(
            // provided example tests
            new Test(new int[] { 10, 100, 20, 50, 30 },
                     new String[] {
                         "withdraw 50 3 10",
                         "withdraw 50 3 10",
                         "deposit 100 5 20",
                         "withdraw 86450 2 30",
                         "withdraw 86450 2 30"
                     },
                     new int[] { 10, 40, 0, 50, 50 }),
            new Test(new int[] { 10, 100, 20, 50, 30 },
                     new String[] { "withdraw 50 3 30" },
                     new int[] { -1 }),
            // edge: invalid account
            new Test(new int[] { 5 },
                     new String[] { "deposit 10 2 5" },
                     new int[] { -1 }),
            // simple deposit-only
            new Test(new int[] { 0, 0 },
                     new String[] { "deposit 1 1 100", "deposit 2 2 200" },
                     new int[] { 100, 200 }),
            // cashback at same timestamp processed first
            new Test(new int[] { 100 },
                     new String[] { "withdraw 0 1 50", "deposit 86400 1 10" },
                     new int[] { 61 }), // 100-50 + cashback1 +10 = 61
            // large input test: 100 deposits of 1 on account 1
            new Test(
                new int[100],
                IntStream.range(1, 101)
                         .mapToObj(i -> "deposit " + i + " 1 1")
                         .toArray(String[]::new),
                // expected: first account 100, others 0
                IntStream.range(0, 100)
                         .map(i -> i == 0 ? 100 : 0)
                         .toArray()
            )
        );

        for (int i = 0; i < tests.size(); i++) {
            Test tc = tests.get(i);
            int[] result = solution(tc.balances, tc.reqs);
            boolean pass = Arrays.equals(result, tc.expected);
            System.out.printf("Test %d: %s%n", i + 1, pass ? "PASS" : "FAIL");
            if (!pass) {
                System.out.println("  Expected: " + Arrays.toString(tc.expected));
                System.out.println("  Got     : " + Arrays.toString(result));
            }
        }
    }
}
