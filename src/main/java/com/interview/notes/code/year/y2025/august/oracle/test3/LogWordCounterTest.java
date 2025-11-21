package com.interview.notes.code.year.y2025.august.oracle.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @param key   word
 * @param value frequency
 */ /*
Here’s the **final combined question** from your screenshot:

---

**Question:**
Problem: **Real-Time Log Word Counter (stable ties)**

Implement a class `LogAggregator` with the following API:

```java
void ingest(String line)                      // add one log line
int count(String word)                        // current frequency of an exact word
List<Pair<String, Integer>> topK(int k)       // top-k words by frequency, stable ties
```

**Rules:**

* **Tokenization:** Convert input to lowercase. Treat any non-letter (`[^a-z]`) as a delimiter.

  * Examples: `"Error: Disk I/O #42!" → ["error","disk","io"]`
    `"re-enter" → ["re","enter"]`
* **Counting:** Track the total frequency of each word across all ingested lines.
* **Stable tie-break:** If two words have the same count, rank the word that appeared first in the stream earlier.
* **Return order:** `topK(k)` returns words sorted by count (descending), then by first appearance (ascending).

---

 */
// Simple Pair class to hold word and frequency
record Pair<K, V>(K key, V value) {

    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }
}

class LogAggregator {
    // Map to store frequency of each word
    private final Map<String, Integer> freqMap = new HashMap<>();
    // Map to store first appearance order of each word
    private final Map<String, Integer> firstSeen = new HashMap<>();
    // Sequence number for stable tie-breaking
    private final AtomicInteger sequence = new AtomicInteger(0);

    // Ingests one log line and updates counts
    public void ingest(String line) {
        // Split on non-letters, allow both upper and lowercase
        String[] rawTokens = line.split("[^A-Za-z]+");

        List<String> tokens = new ArrayList<>();
        StringBuilder acronym = new StringBuilder();

        // Merge consecutive single-letter tokens into acronyms like IO, USA
        for (String raw : rawTokens) {
            if (raw.isEmpty()) continue;
            if (raw.length() == 1) {
                acronym.append(raw);
            } else {
                if (acronym.length() > 0) {
                    tokens.add(acronym.toString().toLowerCase());
                    acronym.setLength(0);
                }
                tokens.add(raw.toLowerCase());
            }
        }
        // Add last acronym if any
        if (acronym.length() > 0) {
            tokens.add(acronym.toString().toLowerCase());
        }

        // Update counts and firstSeen order
        for (String token : tokens) {
            freqMap.put(token, freqMap.getOrDefault(token, 0) + 1);
            firstSeen.putIfAbsent(token, sequence.getAndIncrement());
        }
    }

    // Returns current frequency of a word
    public int count(String word) {
        return freqMap.getOrDefault(word.toLowerCase(), 0);
    }

    // Returns top-k words by frequency with stable tie-breaking
    public List<Pair<String, Integer>> topK(int k) {
        return freqMap.entrySet()
                .stream()
                .sorted((a, b) -> {
                    int cmp = Integer.compare(b.getValue(), a.getValue());
                    if (cmp != 0) return cmp;
                    return Integer.compare(firstSeen.get(a.getKey()), firstSeen.get(b.getKey()));
                })
                .limit(k)
                .map(e -> new Pair<>(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}

public class LogWordCounterTest {
    public static void main(String[] args) {
        // ===== Test Case 1: Basic with I/O =====
        LogAggregator log1 = new LogAggregator();
        log1.ingest("Error: Disk I/O #42!");
        log1.ingest("Warning: Disk space low.");
        log1.ingest("Error occurred at Disk Controller.");
        List<Pair<String, Integer>> top3 = log1.topK(3);
        boolean test1 = log1.count("disk") == 3
                && log1.count("error") == 2
                && log1.count("io") == 1
                && top3.get(0).key().equals("disk");
        System.out.println("Test 1 (I/O handling) -> " + (test1 ? "PASS" : "FAIL") + " | " + top3);

        // ===== Test Case 2: Stable Tie =====
        LogAggregator log2 = new LogAggregator();
        log2.ingest("alpha beta");
        log2.ingest("gamma beta");
        List<Pair<String, Integer>> top2 = log2.topK(2);
        boolean test2 = top2.get(0).key().equals("beta") && top2.get(1).key().equals("alpha");
        System.out.println("Test 2 (stable tie) -> " + (test2 ? "PASS" : "FAIL") + " | " + top2);

        // ===== Test Case 3: Empty Input =====
        LogAggregator log3 = new LogAggregator();
        log3.ingest("!!!???");
        boolean test3 = log3.topK(1).isEmpty();
        System.out.println("Test 3 (empty input) -> " + (test3 ? "PASS" : "FAIL"));

        // ===== Test Case 4: Large Input =====
        LogAggregator log4 = new LogAggregator();
        for (int i = 0; i < 100_000; i++) {
            log4.ingest("server log error disk cpu memory");
        }
        boolean test4 = log4.count("server") == 100_000
                && log4.topK(1).get(0).key().equals("server");
        System.out.println("Test 4 (large input) -> " + (test4 ? "PASS" : "FAIL"));

        // ===== Test Case 5: Case Insensitivity =====
        LogAggregator log5 = new LogAggregator();
        log5.ingest("Error ERROR error");
        boolean test5 = log5.count("error") == 3;
        System.out.println("Test 5 (case insensitive) -> " + (test5 ? "PASS" : "FAIL"));

        // ===== Test Case 6: Acronym Handling =====
        LogAggregator log6 = new LogAggregator();
        log6.ingest("U S A rocks!");
        log6.ingest("USA is big.");
        boolean test6 = log6.count("usa") == 2;
        System.out.println("Test 6 (acronym merge) -> " + (test6 ? "PASS" : "FAIL"));
    }
}