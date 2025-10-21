package com.interview.notes.code.year.y2025.october.jpmc.test1;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.zip.CRC32;

/**
 * Interface that abstracts backend calls. We inject a fake in main to avoid flakey HTTP in tests.
 */
interface ApiGateway {
    // Returns true if customer exists/valid; false otherwise
    boolean isCustomerValid(String customerKey);

    // Returns Optional price for a product; empty if not found or temporarily unavailable
    Optional<Double> findProductPrice(String productKey);
}

/**
 * Immutable DTO representing a single customer request.
 *
 * @param productKey     Public final fields to keep DTO simple and immutable Product code (e.g., "TEST_PROD_1")
 * @param quantity       Quantity requested (must be > 0)
 * @param customerKey    Customer identifier (e.g., "TEST_CUST_1")
 * @param expedited      True if expedited handling required
 * @param createdEpochMs Creation time in epoch milliseconds
 * @param requestId      Deterministic ID computed from fields
 * @param valid          Validation result at creation time
 */
record SendCustomerRequest(String productKey, int quantity, String customerKey, boolean expedited, boolean valid,
                           String requestId, long createdEpochMs) {
    // Constructor builds an immutable instance, validates inputs, and computes derived fields
    SendCustomerRequest(
            String productKey,          // Input: product id
            int quantity,               // Input: quantity
            String customerKey,         // Input: customer id
            boolean expedited,          // Input: expedited flag
            boolean valid,              // Input: validation outcome computed by service
            String requestId,           // Input: deterministic id computed by service
            long createdEpochMs         // Input: timestamp captured by service
    ) {
        this.productKey = Objects.requireNonNull(productKey, "productKey");          // Null-safety for productKey
        this.quantity = quantity;                                                 // Store quantity (validated in service)
        this.customerKey = Objects.requireNonNull(customerKey, "customerKey");       // Null-safety for customerKey
        this.expedited = expedited;                                                // Store expedited flag
        this.createdEpochMs = createdEpochMs;                                       // Store creation instant
        this.requestId = Objects.requireNonNull(requestId, "requestId");           // Must be set by service
        this.valid = valid;                                                    // Store validation result
    }
}

/**
 * Service that creates, validates, stores, and aggregates requests.
 * Uses Streams for totalValue() and caches prices to be fast on large inputs.
 */
final class SendCustomerRequestService {
    private final ApiGateway api;                                       // Backend abstraction (fake in tests)
    private final List<SendCustomerRequest> requests;                   // Storage for requests (thread-safe list)
    private final Map<String, Double> priceCache;                       // Cache product prices to avoid repeated calls
    private final AtomicLong issuedCounter = new AtomicLong(0L);        // Deterministic sequence for IDs

    // Constructor wires the gateway and initializes thread-safe state
    public SendCustomerRequestService(ApiGateway api) {
        this.api = Objects.requireNonNull(api, "api");                  // Null-safety
        this.requests = new CopyOnWriteArrayList<>();                   // Safe for concurrent reads/writes
        this.priceCache = new ConcurrentHashMap<>();                    // Thread-safe price cache
    }

    // Deterministic short ID builder using CRC32 of a canonical string; stable across JVMs
    private static String buildStableId(String productKey, String customerKey, int qty, long seq) {
        final String canonical = productKey + "|" + customerKey + "|" + qty + "|" + seq; // Canonical unique string
        final CRC32 crc = new CRC32();                                   // Create a CRC32 instance
        crc.update(canonical.getBytes(StandardCharsets.UTF_8));          // Feed canonical bytes (UTF-8)
        final long v = crc.getValue();                                   // Compute CRC value
        return Long.toHexString(v);                                      // Return lower-case hex (short, deterministic)
    }

    // Public factory method: validates, assigns id, stores, and returns the immutable DTO
    public SendCustomerRequest create(String productKey, int qty, String customerKey, boolean expedited) {
        if (qty <= 0) {                                                 // Basic defensive check for quantity
            throw new IllegalArgumentException("quantity must be > 0");
        }
        final long now = System.currentTimeMillis();                    // Capture time once for consistency
        final long seq = issuedCounter.incrementAndGet();               // Get a deterministic sequence number
        final String reqId = buildStableId(productKey, customerKey, qty, seq); // Build stable request id

        // Compute validity by querying backend via the gateway
        final boolean customerOk = api.isCustomerValid(customerKey);    // Ask API if customer is valid
        final boolean productOk  = api.findProductPrice(productKey).isPresent(); // Product is valid if it has a price
        final boolean valid = customerOk && productOk;                  // Valid only if both checks pass

        // Build immutable DTO
        final SendCustomerRequest dto = new SendCustomerRequest(
                productKey,                                             // Keep original product code
                qty,                                                    // Store requested quantity
                customerKey,                                            // Store original customer key
                expedited,                                              // Store expedited flag
                valid,                                                  // Store validation result
                reqId,                                                  // Store deterministic id
                now                                                     // Store creation time
        );

        requests.add(dto);                                              // Persist into in-memory store
        return dto;                                                     // Return for immediate use by caller
    }

    // Compute the total value using Streams; supports filters and uses cached prices for speed
    public double totalValue(double minValue, boolean urgentOnly) {
        // Stream over stored requests, filter validity and urgency, map to value, filter by minValue, sum
        return requests.stream()                                         // Start a sequential stream (fast enough here)
                .filter(r -> r.valid())                                   // Only valid requests count
                .filter(r -> !urgentOnly || r.expedited())                // If urgentOnly, keep only expedited
                .mapToDouble(r -> priceOf(r.productKey()) * r.quantity())   // Convert each request to its monetary value
                .filter(v -> v >= minValue)                             // Keep only high-value items
                .sum();                                                 // Sum as double
    }

    // Expose an unmodifiable view of requests for diagnostics/tests
    public List<SendCustomerRequest> all() {
        return Collections.unmodifiableList(requests);                  // Prevent external mutation
    }

    // Internal helper that pulls price from cache or gateway
    private double priceOf(String productKey) {
        // Use computeIfAbsent to fill cache atomically and only once per product
        return priceCache.computeIfAbsent(productKey, key ->
                api.findProductPrice(key).orElse(0.0)                   // Missing price -> treat as zero
        );
    }
}

/**
 * A fast, deterministic FakeApiGateway for tests and local runs.
 * - Customers valid if key starts with "TEST_CUST_"
 * - Products valid if key starts with "TEST_PROD_"
 * - Price is derived from trailing digits of product key (or a default)
 */
final class FakeApiGateway implements ApiGateway {
    private final Map<String, Double> priceOverrides = new HashMap<>(); // Optional explicit prices for tests

    public FakeApiGateway withPrice(String productKey, double price) {  // Fluent setup helper
        priceOverrides.put(productKey, price);                          // Store override
        return this;                                                    // Enable chaining
    }

    @Override
    public boolean isCustomerValid(String customerKey) {                // Simple rule for validity
        return customerKey != null && customerKey.startsWith("TEST_CUST_");
    }

    @Override
    public Optional<Double> findProductPrice(String productKey) {       // Compute a stable price
        if (productKey == null || !productKey.startsWith("TEST_PROD_")) // Invalid product naming -> empty
            return Optional.empty();
        if (priceOverrides.containsKey(productKey))                     // Return override if present
            return Optional.of(priceOverrides.get(productKey));
        // Derive price from trailing digits; if no digits -> default 100
        String digits = productKey.replaceAll("\\D+", "");              // Keep only digits
        double price = digits.isEmpty() ? 100.0 : Double.parseDouble(digits);
        return Optional.of(price);                                      // Wrap in Optional
    }
}

/**
 * A tiny test utility to print PASS/FAIL lines without JUnit.
 */
final class T {
    // Asserts equality for doubles with tolerance and prints a label
    public static void eq(String label, double actual, double expected, double eps) {
        boolean ok = Math.abs(actual - expected) <= eps;                // Check within epsilon
        System.out.println((ok ? "PASS" : "FAIL") + " | " + label +
                " | actual=" + actual + " expected=" + expected);       // Print result line
        if (!ok) throw new AssertionError(label + " mismatch");         // Fail fast to surface issues
    }

    // Asserts equality for objects (Strings, integers, booleans)
    public static void eq(String label, Object actual, Object expected) {
        boolean ok = Objects.equals(actual, expected);                  // Safe equals for possibly-null objects
        System.out.println((ok ? "PASS" : "FAIL") + " | " + label +
                " | actual=" + actual + " expected=" + expected);       // Print result line
        if (!ok) throw new AssertionError(label + " mismatch");         // Fail fast to surface issues
    }
}

/**
 * Single-file runnable demo with PASS/FAIL tests and a large-data performance check.
 */
public class CustomerRequestModuleDemo {
    public static void main(String[] args) {
        // Build a fake API with known prices to make tests deterministic
        FakeApiGateway api = new FakeApiGateway()
                .withPrice("TEST_PROD_1", 55.0)                         // Explicit price for PROD_1
                .withPrice("TEST_PROD_2", 99.5);                        // Explicit price for another product

        // Create the service
        SendCustomerRequestService svc = new SendCustomerRequestService(api);

        // --- Test Case 1: Basic creation correctness ------------------------------------------
        SendCustomerRequest r1 = svc.create("TEST_PROD_1", 1, "TEST_CUST_1", true); // Create a valid, expedited request
        T.eq("TC1/productKey", r1.productKey(), "TEST_PROD_1");                        // Validate product key stored
        T.eq("TC1/customerKey", r1.customerKey(), "TEST_CUST_1");                      // Validate customer key stored
        T.eq("TC1/quantity", r1.quantity(), 1);                                        // Validate quantity stored
        T.eq("TC1/expedited", r1.expedited(), true);                                   // Validate expedited flag
        T.eq("TC1/valid", r1.valid(), true);                                           // Must be valid (product+customer ok)
        // The ID is deterministic but sequence-based; just check it's non-empty hex
        boolean idLooksHex = r1.requestId().matches("[0-9a-f]+");                      // Simple sanity for id format
        T.eq("TC1/requestIdFormat", idLooksHex, true);                               // Expect hex id

        // --- Test Case 2: totalValue for a single valid request -------------------------------
        double total1 = svc.totalValue(0, false);                                    // No min filter, include all valid
        T.eq("TC2/totalValue", total1, 55.0, 1e-9);                                  // 55.0 * 1 = 55.0

        // --- Test Case 3: Add more requests and filter with minValue and urgentOnly -----------
        SendCustomerRequest r2 = svc.create("TEST_PROD_1", 100, "TEST_CUST_1", false); // valid, not expedited
        SendCustomerRequest r3 = svc.create("TEST_PROD_2", 10, "TEST_CUST_2", true);    // valid, expedited
        // total (min=0, urgentOnly=false): 55*1 + 55*100 + 99.5*10 = 55 + 5500 + 995 = 6550
        double totalAll = svc.totalValue(0.0, false);                                 // Aggregate of all valid requests
        T.eq("TC3/totalAll", totalAll, 6550.0, 1e-9);                                 // Check exact sum

        // total (min=500, urgentOnly=true): only expedited, each >= 500
        // r1: 55 (expedited) < 500 -> filtered out
        // r3: 99.5*10 = 995 (expedited) -> kept -> sum = 995
        double totalUrgentHigh = svc.totalValue(500.0, true);
        T.eq("TC3/urgentHigh", totalUrgentHigh, 995.0, 1e-9);

        // --- Test Case 4: Invalid customer gets valid=false and contributes 0 value ------------
        SendCustomerRequest r4 = svc.create("TEST_PROD_1", 5, "BAD_CUST", true);      // Invalid customer
        T.eq("TC4/validFlag", r4.valid(), false);                                       // Should be invalid
        double totalAfterInvalid = svc.totalValue(0.0, false);                         // Recompute all
        T.eq("TC4/ignoreInvalid", totalAfterInvalid, 6550.0, 1e-9);                    // Same as before

        // --- Test Case 5: Large data performance test (Streams + caching) ----------------------
        final int N = 200_000;                                                        // Number of extra requests
        long t0 = System.currentTimeMillis();                                         // Start timer
        // Create many requests using streams for variety; half expedited, two products
        IntStream.range(0, N).forEach(i -> {
            boolean fast = (i & 1) == 0;                                             // Every other request expedited
            String prod = (i % 3 == 0) ? "TEST_PROD_1" : "TEST_PROD_2";               // Two hot products
            int qty = (i % 5) + 1;                                                   // Quantities 1..5
            svc.create(prod, qty, "TEST_CUST_" + (i % 50), fast);                     // Reuse a small set of customers
        });
        double bigSum = svc.totalValue(500.0, false);                                 // Compute with min filter
        long tookMs = System.currentTimeMillis() - t0;                                // Capture elapsed time
        System.out.println("Large data test: N=" + N + " total=" + bigSum + " timeMs=" + tookMs);
        // Sanity: just require it to finish reasonably fast (target < 4000 ms on typical laptops)
        if (tookMs > 4000) {                                                          // Soft performance guard
            throw new AssertionError("Performance regression: took " + tookMs + " ms");
        }

        // --- Summary line so it’s obvious the suite finished -----------------------------------
        System.out.println("ALL TESTS PASSED ✅");
    }
}
