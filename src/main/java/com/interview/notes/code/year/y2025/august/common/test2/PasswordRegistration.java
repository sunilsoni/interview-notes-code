package com.interview.notes.code.year.y2025.august.common.test2;/*
Here's the **combined and formatted version of the problem statement and implementation instructions** extracted from the screenshots:

---

### ✅ Problem: Authentication System Password Limit

In an authentication system, in the *i<sup>th</sup>* second, a new user registers with the password `passwords[i]`.

* The password is **rejected** if **k** registered users already have the same password.
* Otherwise, the registration is **accepted**.

---

### 🔍 Function Signature

```java
public static List<String> getRegistrationStatus(List<String> passwords, int k)
```

---

### 📥 Input

* A list of `n` passwords: `passwords[0]` to `passwords[n-1]`
* An integer `k`: maximum number of users allowed to share the same password

---

### 📤 Output

* A list of strings: "ACCEPT" or "REJECT" for each password in order

---

### 🔄 Example

```text
Input:
passwords = ["password1", "password1", "password1"]
k = 2

Output:
["ACCEPT", "ACCEPT", "REJECT"]
```

**Explanation**:

* The first two users with `"password1"` are accepted.
* The third user is **rejected** because `k = 2` users already have it.

---

### 📌 Constraints

* 1 ≤ n ≤ 10⁵
* 1 ≤ length of passwords\[i] ≤ 10
* 1 ≤ k ≤ 100

---

### ✅ Sample Case 0

**Input:**

```text
5
lpohsktrkr
nknpnmkurp
mnoazheszh
mnoazheszh
lpohsktrkr
1
```

**Output:**

```text
ACCEPT
ACCEPT
ACCEPT
REJECT
REJECT
```

**Explanation:** Only 1 user (`k=1`) is allowed to use the same password.

---

### ✅ Sample Case 1

**Input:**

```text
5
dwoprpjaes
dwoprpjaes
plhsnlegec
dwoprpjaes
plhsnlegec
2
```

**Output:**

```text
ACCEPT
ACCEPT
ACCEPT
REJECT
ACCEPT
```

**Explanation:** Up to 2 users can have the same password.

---

### ✅ Java Method Skeleton

```java
public static List<String> getRegistrationStatus(List<String> passwords, int k) {
    // Write your code here
}
```

Would you like me to implement this method in Java using simple logic and Java 8 Stream API?


1. Version: Java 8 using Stream API

2. Problem Analysis:
   - We receive a list of passwords (strings) in chronological order.
   - Each new user is allowed to register with a password only if fewer than k users
     have already registered with the same password.
   - We must output for each password whether it was "ACCEPT" or "REJECT".
   - We need an O(n) solution in time and O(n) in space to handle up to 10^5 entries.

3. Solution Design:
   - Use a HashMap<String, AtomicInteger> to track how many times each password
     has been accepted so far.
   - Process the input list in order. For each password:
       * Lookup (or insert) its count in the map.
       * If count < k, increment and mark "ACCEPT";
         otherwise, mark "REJECT".
   - Use Java 8 Stream API with a stateful map to transform the list in one pass.

4. Implementation:
   - The getRegistrationStatus method implements the above logic using streams.
   - A main method drives tests, printing PASS/FAIL for each scenario.

5. Testing:
   - We test provided sample cases and additional edge cases.
   - We simulate a large dataset (100,000 entries) to ensure performance.
   - Each test compares actual vs expected and prints the result.

6. Code Review:
   - Uses clear naming and simple logic.
   - Stream API ensures concise transformation.
   - HashMap with AtomicInteger handles concurrent-style increments safely in single thread.
   - No external libraries beyond the Java standard library.
*/

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PasswordRegistration {

    public static List<String> getRegistrationStatus(List<String> passwords, int k) {
        Map<String, AtomicInteger> counts = new HashMap<>();
        return passwords.stream()
                .map(p -> {
                    AtomicInteger cnt = counts.computeIfAbsent(p, key -> new AtomicInteger(0));
                    if (cnt.get() < k) {
                        cnt.incrementAndGet();
                        return "ACCEPT";
                    } else {
                        return "REJECT";
                    }
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Sample Case 0
        List<String> pw0 = Arrays.asList(
                "lpohsktrkr", "nknpmnkurp", "mnoazheszh", "mnoazheszh", "lpohsktrkr"
        );
        int k0 = 1;
        List<String> expect0 = Arrays.asList("ACCEPT", "ACCEPT", "ACCEPT", "REJECT", "REJECT");
        runTest(pw0, k0, expect0, "Sample Case 0");

        // Sample Case 1
        List<String> pw1 = Arrays.asList(
                "dwoprpjaes", "dwoprpjaes", "plhsnelgec", "dwoprpjaes", "plhsnelgec"
        );
        int k1 = 2;
        List<String> expect1 = Arrays.asList("ACCEPT", "ACCEPT", "ACCEPT", "REJECT", "ACCEPT");
        runTest(pw1, k1, expect1, "Sample Case 1");

        // Edge Case: all unique passwords, k=1
        List<String> pw2 = Arrays.asList("a", "b", "c", "d");
        int k2 = 1;
        List<String> expect2 = Arrays.asList("ACCEPT", "ACCEPT", "ACCEPT", "ACCEPT");
        runTest(pw2, k2, expect2, "All Unique, k=1");

        // Edge Case: all same, k=3
        List<String> pw3 = Arrays.asList("x", "x", "x", "x");
        int k3 = 3;
        List<String> expect3 = Arrays.asList("ACCEPT", "ACCEPT", "ACCEPT", "REJECT");
        runTest(pw3, k3, expect3, "All Same, k=3");

        // Large Data Test
        int nLarge = 100_000;
        String common = "password";
        List<String> pwLarge = Collections.nCopies(nLarge, common);
        int kLarge = 100;
        List<String> resLarge = getRegistrationStatus(pwLarge, kLarge);
        long acceptedCount = resLarge.stream().filter(s -> s.equals("ACCEPT")).count();
        long rejectedCount = resLarge.stream().filter(s -> s.equals("REJECT")).count();
        boolean largePass = (acceptedCount == kLarge) && (rejectedCount == (nLarge - kLarge));
        System.out.printf("Large Data Test: %s (Accepted=%d, Rejected=%d)\n",
                largePass ? "PASS" : "FAIL", acceptedCount, rejectedCount);
    }

    private static void runTest(List<String> passwords, int k,
                                List<String> expected, String testName) {
        List<String> output = getRegistrationStatus(passwords, k);
        boolean ok = output.equals(expected);
        System.out.printf("%s: %s\n", testName, ok ? "PASS" : "FAIL");
        if (!ok) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual:   " + output);
        }
    }
}