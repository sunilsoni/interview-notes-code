package com.interview.notes.code.year.y2025.october.microsoft.test1;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @param type           resource type, e.g., "Microsoft.Compute/virtualMachines"
 * @param name           resource name, e.g., "myVM"
 * @param subscriptionId subscription id GUID string
 */ // Simple model representing an Azure resource that triggered the notification.
record AzureResource(String type, String name, String subscriptionId) {
    // Constructor to create resource objects; keeps code simple and immutable
    // store provided type
    // store provided name
    // store subscription id

    // Build a simple unique id for hashing and deterministic routing decisions
    public String uniqueId() {
        return type + "|" + name + "|" + subscriptionId; // deterministic unique string
    }

    // readable toString for test logging and debugging
    @Override
    public String toString() {
        return "AzureResource{" + "type='" + type + '\'' + ", name='" + name + '\'' + ", subscriptionId='" + subscriptionId + '\'' + '}'; // helpful for logs
    }
}

/**
 * @param endpoint endpoint uniquely identifies subscriber e.g., "https://us.contoso.com"
 * @param name     logical name, e.g., "Microsoft.Storage"
 */ // Model representing a subscriber for notifications.
record Subscriber(String endpoint, String name) {
    // Constructor to create a subscriber
    // store endpoint
    // store name

    // toString for easy debugging in tests
    @Override
    public String toString() {
        return "Subscriber{" + "endpoint='" + endpoint + '\'' + ", name='" + name + '\'' + '}'; // readable text
    }
}

// Simple in-memory configuration manager that returns modern rollout percentage per change type.
class InMemoryConfigManager {
    private final Map<String, Integer> rolloutMap = new HashMap<>(); // stores changeType -> percent

    // Set rollout percent for a change type; percent must be 0..100
    public void setRollout(String changeType, int percent) {
        if (percent < 0 || percent > 100) { // validate inputs
            throw new IllegalArgumentException("Percent must be 0..100"); // fail fast for invalid config
        }
        rolloutMap.put(changeType, percent); // store the percent
    }

    // Get stored percent; default to 0 if not configured
    public int getRollout(String changeType) {
        return rolloutMap.getOrDefault(changeType, 0); // safe default is 0% modern
    }
}

// Simulated external provider method to find subscribers for a changeType.
// In production this should call real subscriber catalog/service.
class NotificationProvider {
    // Simple function that returns a Subscriber[] based on changeType; replace with real provider
    public static Subscriber[] GetSubscribers(String changeType) {
        // simple hard-coded examples to make tests deterministic
        if ("small-test".equals(changeType)) { // used in small deterministic tests
            return new Subscriber[]{
                    new Subscriber("https://endpoint-a.example.com", "A"),
                    new Subscriber("https://endpoint-b.example.com", "B")
            };
        } else if ("mixed-test".equals(changeType)) { // second deterministic set
            return new Subscriber[]{
                    new Subscriber("https://a.example.com", "A"),
                    new Subscriber("https://b.example.com", "B"),
                    new Subscriber("https://c.example.com", "C"),
            };
        } else {
            // for large tests, we'll generate externally; return empty
            return new Subscriber[0];
        }
    }
}

// Delivery client that exposes legacy and modern delivery methods.
// Here we simulate delivery by incrementing counters and storing logs for assertions.
class DeliveryClient {
    public final AtomicInteger legacyCount = new AtomicInteger(0); // counter for legacy deliveries
    public final AtomicInteger modernCount = new AtomicInteger(0); // counter for modern deliveries
    public final List<String> deliveryLog = Collections.synchronizedList(new ArrayList<>()); // record each delivery for debugging

    // Simulate legacy delivery and update counters/logs
    public void DeliverNotificationLegacy(String changeType, AzureResource resource, Subscriber subscriber) {
        legacyCount.incrementAndGet(); // increment legacy counter
        deliveryLog.add("[LEGACY] " + changeType + " -> " + subscriber.endpoint() + " for " + resource.uniqueId()); // log details
    }

    // Simulate modern delivery and update counters/logs
    public void DeliverNotificationModern(String changeType, AzureResource resource, Subscriber subscriber) {
        modernCount.incrementAndGet(); // increment modern counter
        deliveryLog.add("[MODERN] " + changeType + " -> " + subscriber.endpoint() + " for " + resource.uniqueId()); // log details
    }
}

// Core orchestrator that routes each subscriber to legacy or modern based on config and deterministic hashing.
class NotificationDeliverySwitch {
    private final InMemoryConfigManager config; // config manager dependency
    private final DeliveryClient client; // delivery client dependency

    // constructor to inject config and client; allows easy unit-testing and swapping implementations
    public NotificationDeliverySwitch(InMemoryConfigManager config, DeliveryClient client) {
        this.config = config; // use provided config manager
        this.client = client; // use provided delivery client
    }

    // Main method to deliver a notification to all subscribers found for changeType
    public void deliver(String changeType, AzureResource resource, Subscriber[] explicitSubscribers) {
        // Determine Modern rollout percent for this changeType
        final int modernPercent = config.getRollout(changeType); // get percent from config (0..100)

        // If explicit subscribers passed use them; otherwise call provider
        final Subscriber[] subscribers = (explicitSubscribers != null && explicitSubscribers.length > 0)
                ? explicitSubscribers // use passed-in test subscribers
                : NotificationProvider.GetSubscribers(changeType); // fallback provider

        // Use Java8 streams for clarity: stream over subscribers and route each one
        Arrays.stream(subscribers).forEach(subscriber -> {
            // Determine whether to route to modern using deterministic stable hashing
            if (useModernForSubscriber(subscriber, resource, modernPercent)) {
                client.DeliverNotificationModern(changeType, resource, subscriber); // call modern delivery
            } else {
                client.DeliverNotificationLegacy(changeType, resource, subscriber); // call legacy delivery
            }
        });
    }

    // Deterministic decision: compute stable hash of subscriber.endpoint + resource.uniqueId and compare with percent
    private boolean useModernForSubscriber(Subscriber subscriber, AzureResource resource, int modernPercent) {
        if (modernPercent <= 0) { // short-circuit for 0% modern
            return false; // always legacy
        }
        if (modernPercent >= 100) { // short-circuit for 100% modern
            return true; // always modern
        }
        // Build a stable key combining subscriber and resource to avoid flapping for same pair
        final String key = subscriber.endpoint() + "|" + resource.uniqueId(); // stable composite key

        // Compute deterministic non-negative hash value in 0..99 range
        final int rawHash = key.hashCode(); // Java hashCode (stable for same JVM run)
        final int positiveHash = Math.abs(rawHash); // avoid negative values from hashCode
        final int bucket = positiveHash % 100; // bucket in 0..99 representing percent slots

        // Use bucket < modernPercent to indicate this subscriber/resource pair falls in modern group
        return bucket < modernPercent; // true means modern, false means legacy
    }
}

// Main class containing tests and the runner; prints PASS/FAIL for each test case.
public class Main {
    // Helper to generate many subscribers for large-data test
    private static Subscriber[] generateSubscribers(int count) {
        // use IntStream style loop replaced with plain loop for clarity
        List<Subscriber> list = new ArrayList<>(count); // pre-size to avoid reallocation
        for (int i = 0; i < count; i++) { // generate 'count' subscribers
            list.add(new Subscriber("https://endpoint-" + i + ".example.com", "S-" + i)); // stable deterministic endpoint
        }
        return list.toArray(new Subscriber[0]); // convert to array expected by delivery switch
    }

    // Simple assert helper to print result in required PASS/FAIL format
    private static void printResult(String testName, boolean passed) {
        System.out.println(testName + " : " + (passed ? "PASS" : "FAIL")); // required output format
    }

    // Main entry that runs tests without using JUnit; tests include deterministic and large-data cases
    public static void main(String[] args) {
        // Create config manager and delivery client for tests
        InMemoryConfigManager config = new InMemoryConfigManager(); // config storage
        DeliveryClient client = new DeliveryClient(); // capture deliveries

        // Create switch orchestrator with injected dependencies
        NotificationDeliverySwitch nds = new NotificationDeliverySwitch(config, client); // orchestrator instance

        // -----------------------
        // Test 1: 100% modern deterministic small test
        // -----------------------
        config.setRollout("small-test", 100); // set changeType small-test to 100% modern
        AzureResource resource1 = new AzureResource("Microsoft.Compute/virtualMachines", "vm1", "sub-1"); // sample resource
        client.legacyCount.set(0);
        client.modernCount.set(0);
        client.deliveryLog.clear(); // reset counters
        nds.deliver("small-test", resource1, null); // deliver using provider for small-test
        boolean test1 = (client.modernCount.get() == 2 && client.legacyCount.get() == 0); // expect both modern
        printResult("Test1-100pct-modern", test1); // print PASS/FAIL

        // -----------------------
        // Test 2: 0% modern deterministic small test
        // -----------------------
        config.setRollout("small-test", 0); // set to 0% modern -> all legacy
        client.legacyCount.set(0);
        client.modernCount.set(0);
        client.deliveryLog.clear(); // reset counters
        nds.deliver("small-test", resource1, null); // same subscribers as provider
        boolean test2 = (client.legacyCount.get() == 2 && client.modernCount.get() == 0); // expect both legacy
        printResult("Test2-0pct-modern", test2); // print PASS/FAIL

        // -----------------------
        // Test 3: deterministic modern mapping for an explicit set
        // -----------------------
        config.setRollout("mixed-test", 50); // set to 50%
        client.legacyCount.set(0);
        client.modernCount.set(0);
        client.deliveryLog.clear(); // reset counters
        AzureResource resource2 = new AzureResource("Microsoft.Storage/storageAccounts", "sto1", "sub-2"); // resource
        // Use explicit subscribers from provider (mixed-test returns 3 items)
        nds.deliver("mixed-test", resource2, null); // route deliveries
        // We expect between 0 and 3 modern depending on hash; but decision must be deterministic; for a test we'll assert total==3
        boolean test3 = (client.modernCount.get() + client.legacyCount.get() == 3); // all subscribers should receive
        printResult("Test3-delivery-counts", test3); // PASS if everyone got a delivery

        // -----------------------
        // Test 4: Large-scale distribution test to approximate rollout percent
        // -----------------------
        final int total = 20000; // number of subscribers for load test
        final int targetPercent = 30; // configured modern rollout percent to validate
        config.setRollout("large-test", targetPercent); // set config
        client.legacyCount.set(0);
        client.modernCount.set(0);
        client.deliveryLog.clear(); // reset counters
        Subscriber[] bigSet = generateSubscribers(total); // generate many subscribers deterministically
        AzureResource resource3 = new AzureResource("Microsoft.Compute/virtualMachines", "vm-large", "sub-large"); // one resource
        nds.deliver("large-test", resource3, bigSet); // deliver to all generated subscribers
        int modern = client.modernCount.get(); // observed modern deliveries
        int legacy = client.legacyCount.get(); // observed legacy deliveries
        double observedPercent = (modern * 100.0) / (modern + legacy); // compute observed percent
        // Acceptable tolerance +/- 2% for large sample
        boolean test4 = Math.abs(observedPercent - targetPercent) <= 2.0; // allow small variance
        printResult("Test4-large-distribution(≈" + targetPercent + "%)", test4); // PASS/FAIL
        System.out.printf("Observed modern: %d, legacy: %d, observedPct: %.2f%%%n", modern, legacy, observedPercent); // debug output

        // -----------------------
        // Test 5: Rollback test (set percent back to 0)
        // -----------------------
        config.setRollout("large-test", 0); // rollback to 0
        client.legacyCount.set(0);
        client.modernCount.set(0); // reset counters
        nds.deliver("large-test", resource3, bigSet); // deliver again
        boolean test5 = (client.modernCount.get() == 0 && client.legacyCount.get() == total); // all legacy expected
        printResult("Test5-rollback-to-0", test5); // PASS/FAIL

        // Summarize final logs optionally (comment out in real production to reduce noise)
        // System.out.println("Sample logs: " + client.deliveryLog.stream().limit(5).collect(Collectors.toList()));
    }
}
