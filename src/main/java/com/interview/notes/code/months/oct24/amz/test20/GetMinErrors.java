package com.interview.notes.code.months.oct24.amz.test20;

/*
10./15 Passing:



**Problem Statement:**

Amazon's database doesn’t support very large numbers, and hence, numbers are stored as a string of binary characters, '0' and '1'. Accidentally, a '!' was entered in some positions, and it is unknown whether they should be '0' or '1'.

The string of incorrect data consists of the characters '0', '1', and '!', where '!' represents an unknown character. The '!' can be replaced with either '0' or '1'. Due to some internal faults, errors are generated every time the characters '0' and '1' appear together as '01' or '10' in any subsequence of the string. It is observed that the number of errors a subsequence '01' generates is `x`, while a subsequence '10' generates `y` errors.

**Task:**
Determine the minimum total errors generated. Since the answer can be very large, return it modulo \(10^9 + 7\).

**Example:**

```
errorString = "101!1"
x = 2
y = 3
```

1. If the '!' at index 3 is replaced with '0', the string is "10101". The number of times the subsequence "01" occurs is 3 at indices (1, 2), (1, 4), and (3, 4). The number of times the subsequence "10" occurs is also 3 at indices (0, 1), (0, 3), and (2, 3). The number of errors is 3 * x + 3 * y = 6 + 9 = 15.

2. If the '!' is replaced with '1', the string is "10111". The subsequence "01" occurs 3 times and "10" occurs 1 time. The number of errors is 3 * x + y = 9.

The minimum number of errors is \( \min(9, 15) \mod (10^9 + 7) = 9 \).

---

**Function Description:**
Complete the function `getMinErrors`:

```
public static int getMinErrors(String errorString, int x, int y)
```

Parameters:
- `errorString`: A string of characters '0', '1', and '!'.
- `x`: The number of errors generated for every occurrence of subsequence "01".
- `y`: The number of errors generated for every occurrence of subsequence "10".

Returns:
- An integer representing the minimum number of errors possible, modulo \(10^9 + 7\).


 */
public class GetMinErrors {

    public static void main(String[] args) {
        testGetMinErrors();
    }

    public static void testGetMinErrors() {
        // User's test case
        String errorStringUser = "0!!1!1";
        int xUser = 2;
        int yUser = 3;
        int expectedUser = 10;
        int resultUser = getMinErrors(errorStringUser, xUser, yUser);
        System.out.println("User Test Case: " + (resultUser == expectedUser ? "PASS" : "FAIL"));

        // Include previous test cases as in the earlier response
        // ...
    }

    public static int getMinErrors(String errorString, int x, int y) {
        final int MOD = 1_000_000_007;
        int n = errorString.length();
        long zerosSoFar = 0;
        long onesSoFar = 0;
        long totalErrors = 0;

        char[] s = errorString.toCharArray();

        if (x <= y) {
            // Assign all '!' to '1's
            for (int i = 0; i < n; i++) {
                char c = s[i];
                if (c == '0') {
                    totalErrors = (totalErrors + onesSoFar * y) % MOD;
                    zerosSoFar++;
                } else {
                    if (c == '!') {
                        c = '1';
                    }
                    totalErrors = (totalErrors + zerosSoFar * x) % MOD;
                    onesSoFar++;
                }
            }
        } else {
            // Assign all '!' to '0's
            for (int i = 0; i < n; i++) {
                char c = s[i];
                if (c == '1') {
                    totalErrors = (totalErrors + zerosSoFar * x) % MOD;
                    onesSoFar++;
                } else {
                    if (c == '!') {
                        c = '0';
                    }
                    totalErrors = (totalErrors + onesSoFar * y) % MOD;
                    zerosSoFar++;
                }
            }
        }

        return (int) (totalErrors % MOD);
    }
}
