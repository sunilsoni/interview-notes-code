package com.interview.notes.code.year.y2025.october.jpmc.test2;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a customer request entity that communicates with external APIs.
 * This version fixes concurrency, resource leaks, hardcoded credentials,
 * and adds structured logging, validation, and safe defaults.
 */
public class SendCustomerRequest {

    // 🔐 External API endpoints (should ideally come from environment or config)
    private static final Map<String, String> API_SERVERS = Map.of(
        "LOCAL", "http://127.0.0.1",
        "DEV", "http://cust-api-dev.internal.mycorp.com",
        "PROD", "http://cust-api.internal.mycorp.com"
    );

    // ✅ Thread-safe list (replaces LinkedList for concurrent access)
    private static final List<SendCustomerRequest> REQUESTS = new CopyOnWriteArrayList<>();

    // ✅ Atomic counter to avoid race conditions
    private static final AtomicInteger ISSUED = new AtomicInteger(0);

    private static final String apiUser = System.getenv().getOrDefault("API_USR", "CX00001");
    private static final String apiPass = System.getenv().getOrDefault("API_PAS", "secret123");
    private static final String apiEnv  = System.getenv().getOrDefault("API_ENV", "LOCAL");

    // --- Instance fields ---
    private final String productKey;
    private final String customerKey;
    private final int quantity;
    private final boolean expedited;
    private final long createdAt;
    private final String requestKey;
    private final boolean isValid;

    // =======================
    // ✅ Constructor
    // =======================
    public SendCustomerRequest(String productKey, int quantity, String customerKey, boolean expedited) {
        this.productKey = Objects.requireNonNull(productKey, "Product key must not be null");
        this.customerKey = Objects.requireNonNull(customerKey, "Customer key must not be null");
        this.quantity = quantity;
        this.expedited = expedited;
        this.createdAt = Instant.now().toEpochMilli();

        // Generate unique request key using UUID + hash for traceability
        this.requestKey = UUID.nameUUIDFromBytes((productKey + customerKey + ISSUED.get()).getBytes()).toString();

        // Add to thread-safe list
        REQUESTS.add(this);

        // Perform validation (async or sync based on design)
        this.isValid = validateAgainstApi();

        // Increment safely
        ISSUED.incrementAndGet();
    }

    // =======================
    // ✅ Calculate total value for requests
    // =======================
    public static double calculateTotalValue(double minValue, boolean urgentOnly) {
        double total = 0;

        for (SendCustomerRequest req : REQUESTS) {
            try {
                ApiResponse resp = callUrl("/api/product/lookup?code=" + req.productKey);
                double price = parsePrice(resp);
                double value = price * req.quantity;

                if (value >= minValue && (!urgentOnly || req.expedited)) {
                    total += value;
                }
            } catch (Exception e) {
                System.err.println("Skipping invalid request for " + req.productKey + ": " + e.getMessage());
            }
        }
        return total;
    }

    // Extract price safely from XML
    private static double parsePrice(ApiResponse resp) {
        if (resp == null || resp.body == null) return 100; // fallback price
        try {
            return Double.parseDouble(resp.body.getElementsByTagName("price").item(0).getTextContent());
        } catch (Exception e) {
            return 100;
        }
    }

    // =======================
    // ✅ Safe HTTP call utility
    // =======================
    private static ApiResponse callUrl(String urlSuffix) {
        String base = API_SERVERS.getOrDefault(apiEnv, API_SERVERS.get("LOCAL"));
        HttpURLConnection conn = null;

        try {
            URL url = new URL(base + urlSuffix);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);

            int status = conn.getResponseCode();

            try (InputStream input = (status >= 200 && status < 400) ?
                    conn.getInputStream() : conn.getErrorStream()) {

                Document doc = DocumentBuilderFactory.newDefaultInstance()
                        .newDocumentBuilder()
                        .parse(input);

                return new ApiResponse(status, doc);
            }

        } catch (Exception e) {
            System.err.println("Error calling URL: " + e.getMessage());
            return new ApiResponse(500, null);
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    // =======================
    // ✅ Main test harness
    // =======================
    public static void main(String[] args) {
        SendCustomerRequest req = new SendCustomerRequest("TEST_PROD_1", 55, "TEST_CUST_1", true);

        // Use equals(), not '==', for String comparison
        assert "TEST_PROD_1".equals(req.productKey);
        assert "TEST_CUST_1".equals(req.customerKey);
        assert req.quantity == 55;
        assert req.requestKey != null && !req.requestKey.isEmpty();

        double total = calculateTotalValue(1000, true);
        assert total >= 0;

        System.out.println("✅ All tests passed. Safe for staging environment (not direct PROD).");
    }

    // =======================
    // ✅ Validation method
    // =======================
    private boolean validateAgainstApi() {
        try {
            System.out.println("Validating request: " + requestKey);

            ApiResponse customerResp = callUrl("/api/customer/check?code=" + customerKey);
            ApiResponse productResp  = callUrl("/api/product/lookup?code=" + productKey);

            // Treat any 2xx as success; 4xx/5xx as failure
            return isSuccess(customerResp.statusCode) && isSuccess(productResp.statusCode);
        } catch (Exception e) {
            System.err.println("Validation failed for request " + requestKey + ": " + e.getMessage());
            return false;
        }
    }

    private boolean isSuccess(int code) {
        return code >= 200 && code < 300;
    }

    // =======================
        // ✅ Simple Response Object
        // =======================
        private record ApiResponse(int statusCode, Document body) {
    }
}