package com.interview.notes.code.year.y2025.august.oracle.test7;

import java.util.*;
import java.util.stream.Collectors;
/*
Here’s the final combined question extracted and cleaned from your screenshot:

---

**Question:**
Implement a document scanning function `wordCountEngine`, which receives a string `document` and returns a list of all unique words in it along with their number of occurrences, sorted by the number of occurrences in descending order.

* If two or more words have the same count, they should be sorted according to their order in the original sentence.
* The function should be case-insensitive (e.g., “Perfect” and “perfect” are the same).
* The engine should strip out punctuation (even in the middle of a word) and use whitespaces to separate words.

**Example:**
Input:

```
document = "Practice makes perfect. you'll only get Perfect by practice. just practice!"
```

Output:

```
[ ["practice", "3"], ["perfect", "2"], ["makes", "1"], ["youll", "1"],
  ["only", "1"], ["get", "1"], ["by", "1"], ["just", "1"] ]
```

---

Do you want me to also prepare **interview-ready solutions (Java/Python)** for this final question in the same concise + deep dive format you’re using for prep?

 */
public class WordCountEngine {

    public static List<List<String>> wordCountEngine(String document) {
        // Step 1: Normalize -> lowercase + remove punctuation by replacing with ""
        // keep only letters, digits and spaces
        String cleaned = document.toLowerCase().replaceAll("[^a-z0-9 ]", "");

        // Step 2: Split into words
        String[] words = cleaned.split("\\s+");

        // Step 3: Count frequencies while preserving first occurrence order
        Map<String, Integer> countMap = new LinkedHashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }

        // Step 4: Sort by frequency (descending), stable order keeps insertion order
        List<Map.Entry<String, Integer>> sortedList =
                countMap.entrySet()
                        .stream()
                        .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                        .collect(Collectors.toList());

        // Step 5: Convert to required format
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedList) {
            result.add(Arrays.asList(entry.getKey(), String.valueOf(entry.getValue())));
        }

        return result;
    }

    // ====== TESTING METHOD ======
    public static void main(String[] args) {
        // Test Case 1: Provided example
        String doc1 = "Practice makes perfect. you'll only get Perfect by practice. just practice!";
        List<List<String>> result1 = wordCountEngine(doc1);
        System.out.println("Output:   " + result1);
        System.out.println("Expected: [[practice, 3], [perfect, 2], [makes, 1], [youll, 1], [only, 1], [get, 1], [by, 1], [just, 1]]");
        System.out.println(result1.toString().equals("[[practice, 3], [perfect, 2], [makes, 1], [youll, 1], [only, 1], [get, 1], [by, 1], [just, 1]]") ? "PASS" : "FAIL");

        // Test Case 2: Single word
        String doc2 = "Hello!!!";
        System.out.println(wordCountEngine(doc2)); // [[hello, 1]]

        // Test Case 3: Mixed case + punctuation
        String doc3 = "Java, java; JAVA!";
        System.out.println(wordCountEngine(doc3)); // [[java, 3]]

        // Test Case 4: Large input
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) sb.append("test ");
        List<List<String>> resultLarge = wordCountEngine(sb.toString());
        System.out.println(resultLarge.size() == 1 && resultLarge.get(0).get(1).equals("100000") ? "PASS (Large Input)" : "FAIL (Large Input)");
    }
}