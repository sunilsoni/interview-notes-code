package com.interview.notes.code.year.y2026.jan.microsoft.test1;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;

public class Main {                        // Program entry class.

    // Checks if x is sparse: no consecutive 1 bits.
    static boolean isSparse(long x) {       // Method to test sparsity.
        return (x & (x >> 1)) == 0;         // If any adjacent 1s exist, this becomes non-zero.
    }

    // Returns smallest sparse number >= n (inclusive).
    static long nextSparse(long n) {        // Core solver method.
        if (n < 0) throw new IllegalArgumentException("n must be >= 0"); // Keep domain simple.
        if (isSparse(n)) return n;          // If already sparse, answer is itself.
        if (n == 0) return 0;               // Edge case.

        int[] b = new int[65];              // Bits array (0..64), 1 extra bit for carry.
        int L = 0;                          // Current number of bits used.
        long x = n;                         // Copy n so we can shift it.

        while (x > 0) {                     // Convert n to bit array (LSB first).
            b[L++] = (int) (x & 1L);        // Store current lowest bit.
            x >>= 1;                        // Move to next bit.
        }
        b[L] = 0;                           // Extra 0 bit to allow carry without bounds issues.

        int last = 0;                       // Lowest index we must clear from when fixing "11".
        for (int i = 1; i < L; i++) {       // Scan bits to find consecutive ones.
            if (b[i] == 1 && b[i - 1] == 1 && b[i + 1] == 0) { // Pattern: ...0110...
                b[i + 1] = 1;               // Carry: set the next higher bit.
                for (int j = i; j >= last; j--) b[j] = 0;     // Clear lower bits to minimize answer.
                last = i + 1;               // Update boundary so we don't re-clear older safe region.
                L = Math.max(L, i + 2);     // Ensure length includes the new carried bit.
            }
        }

        long ans = 0;                       // Rebuild number from bit array.
        for (int i = 0; i <= L; i++) {      // Walk all bits we may have touched.
            if (b[i] == 1) {                // If this bit is set,
                if (i == 63) throw new ArithmeticException("overflow"); // Avoid signed long overflow.
                ans |= (1L << i);           // Set that bit in the answer.
            }
        }
        return ans;                          // Return the computed sparse number.
    }

    // Slow brute for small numbers (used only in testing).
    static long bruteNextSparse(long n) {    // Reference solver for validation.
        for (long x = n; ; x++) {            // Increment until sparse (OK for small ranges).
            if (isSparse(x)) return x;       // Return first sparse.
        }
    }

    static void runTests() {                 // Runs PASS/FAIL tests.
        var tests = List.of(                // Given + extra edge cases.
                new T(5, 5),                // 101 is sparse => answer 5 (inclusive definition).
                new T(6, 8),                // 110 => next sparse 1000.
                new T(7, 8),                // 111 => next sparse 1000.
                new T(8, 8),                // 1000 is sparse => 8.
                new T(0, 0),                // Edge.
                new T(1, 1),                // 1.
                new T(2, 2),                // 10.
                new T(3, 4),                // 11 => 100.
                new T(4, 4),                // 100.
                new T(9, 9),                // 1001 is sparse.
                new T(10, 10)               // 1010 is sparse.
        );

        System.out.println("Fixed tests:");  // Header.
        tests.forEach(t -> {                // Run each case.
            long got = nextSparse(t.in);    // Compute answer.
            boolean ok = got == t.expected; // Check exact match.
            System.out.printf("n=%d got=%d expected=%d => %s%n",
                    t.in, got, t.expected, ok ? "PASS" : "FAIL"); // Print result.
        });

        System.out.println("\nRandom small-range cross-check:"); // Header for brute checks.
        int limit = 50_000;                 // Range size (safe for brute).
        boolean allOk = IntStream.range(0, limit)                // Stream API range.
                .allMatch(i -> nextSparse(i) == bruteNextSparse(i)); // Compare fast vs brute.
        System.out.println(allOk ? "PASS" : "FAIL");             // Print status.

        System.out.println("\nLarge input sanity checks:");      // Header for large cases.
        long[] big = {3_000_000_000L, 4_000_000_000L, 9_999_999_999L}; // Big examples.
        for (long n : big) {                                     // Loop big values.
            long ans = nextSparse(n);                             // Compute.
            boolean ok = ans >= n && isSparse(ans);              // Basic correctness properties.
            System.out.printf("n=%d ans=%d sparse=%s => %s%n",
                    n, ans, isSparse(ans), ok ? "PASS" : "FAIL"); // Print.
        }

        // Note: If someone expects "next sparse strictly greater than n",
        // use: nextSparse(n + 1). Example: for 8 => 9.
    }

    public static void main(String[] args) throws Exception {    // Main entry point.
        var fs = new FastScanner(System.in);                     // Create fast scanner.
        Long first = fs.nextLongOrNull();                        // Try read first number.
        if (first == null) {                                     // No input => run tests.
            runTests();                                          // Run internal PASS/FAIL suite.
            return;                                              // Exit.
        }

        var sb = new StringBuilder();                            // Buffer output for speed.
        long n = first;                                          // Use the first number read.
        while (true) {                                           // Process all numbers.
            sb.append(nextSparse(n)).append('\n');               // Print result per line.
            Long next = fs.nextLongOrNull();                     // Read next value.
            if (next == null) break;                             // Stop on EOF.
            n = next;                                            // Update current input.
        }
        System.out.print(sb);                                     // Flush output once (fast).
    }

    // Very fast input for large data (better than Scanner).
    static final class FastScanner {         // Simple fast scanner.
        private final InputStream in;        // Underlying stream.
        private final byte[] buf = new byte[1 << 16]; // Buffer for speed.
        private int ptr = 0, len = 0;        // Buffer pointers.

        FastScanner(InputStream in) {        // Constructor.
            this.in = in;                    // Store stream.
        }

        private int read() throws IOException { // Read one byte.
            if (ptr >= len) {               // If buffer empty,
                len = in.read(buf);         // Refill buffer.
                ptr = 0;                    // Reset pointer.
                if (len <= 0) return -1;    // EOF.
            }
            return buf[ptr++];              // Return next byte.
        }

        Long nextLongOrNull() throws IOException { // Read next long or null on EOF.
            int c;                          // Current char.
            do {                            // Skip spaces/newlines.
                c = read();                 // Read char.
                if (c == -1) return null;   // EOF => no more numbers.
            } while (c <= ' ');             // Keep skipping whitespace.

            long sign = 1;                  // Sign multiplier.
            if (c == '-') {                 // If negative,
                sign = -1;                  // Mark sign.
                c = read();                 // Move to first digit.
            }

            long val = 0;                   // Parsed value.
            while (c > ' ') {               // While not whitespace,
                val = val * 10 + (c - '0'); // Build number.
                c = read();                 // Read next char.
            }
            return val * sign;              // Return signed value.
        }
    }

    // Test record (Java 21 feature).
    record T(long in, long expected) {
    }      // Holds one test case.
}
