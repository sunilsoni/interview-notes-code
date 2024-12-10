package com.interview.notes.code.months.dec24.Amazon.test5;

import java.util.HashMap;
import java.util.Map;

/*

### Problem Statement:

You are given an **informal stream of strings/characters**, and the task is to write an API that returns the **most recent non-repeating string/character** at any moment when called.

The stream is processed in real-time, and the API must simulate a scenario where the stream grows dynamically. The API should correctly determine the most recent non-repeating string/character, ensuring that previously repeated strings are excluded.

---

### Example:

#### Input and Output at Each Time Step:
| Time | Stream         | Most Recent Non-Repeating Character (API Output) |
|------|----------------|--------------------------------------------------|
| 1    | a              | a                                                |
| 2    | a, b           | b                                                |
| 3    | a, b, c        | c                                                |
| 4    | a, b, c, a     | c                                                |

---

### Important Details:
1. **Complexity**: The task becomes complex due to the need to handle a continuously growing stream and dynamically exclude characters that are repeated.
2. **Solution Requirement**:
   - At any given time, the API must analyze the current state of the stream and return the correct result in **O(1)** time.
   - Efficiently handle updates as new characters are added.
   - The solution should optimize both memory and processing for large-scale streams.

---

**Key Clarification**:
At **time 4**, even though "a" is repeated, "c" remains the most recent **non-repeating character** in the stream. Thus, the API must handle such cases correctly.


 */
public class MostRecentNonRepeating {
    // Head and tail of our linked list of unique characters
    private Node head = new Node('\0');
    private Node tail = new Node('\0');
    // Maps to track counts and positions
    private Map<Character, Integer> countMap = new HashMap<>();
    private Map<Character, Node> nodeMap = new HashMap<>();

    public MostRecentNonRepeating() {
        head.next = tail;
        tail.prev = head;
    }

    public static void main(String[] args) {
        MostRecentNonRepeating streamAPI = new MostRecentNonRepeating();

        // Test cases
        char[] input = {'a', 'b', 'c', 'a'}; // from example
        Character[] expected = {'a', 'b', 'c', 'c'};

        System.out.println("Testing:");
        for (int i = 0; i < input.length; i++) {
            streamAPI.addChar(input[i]);
            Character result = streamAPI.getMostRecentNonRepeating();
            boolean pass = (result == expected[i]);
            System.out.println("Input: " + input[i] + " | Output: " + result + " | Expected: " + expected[i] + " | Pass: " + pass);
        }

        // Additional tests (large data)
        // For performance check: adding multiple characters
        // Just ensure it runs efficiently without errors
        for (int i = 0; i < 100000; i++) {
            streamAPI.addChar('x');
        }
        System.out.println("Large data test done. Most recent non-repeating: " + streamAPI.getMostRecentNonRepeating());
    }

    // Insert a character from the stream
    public void addChar(char c) {
        countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        int cnt = countMap.get(c);

        // If new char, add to linked list
        if (cnt == 1) {
            Node newNode = new Node(c);
            insertAtEnd(newNode);
            nodeMap.put(c, newNode);
        }
        // If char now repeated, remove from list if present
        else if (cnt == 2) {
            Node n = nodeMap.get(c);
            if (n != null) {
                removeNode(n);
                nodeMap.remove(c);
            }
        }
    }

    // Get the most recent non-repeating character
    public Character getMostRecentNonRepeating() {
        if (head.next == tail) return null; // no unique chars
        return tail.prev.ch;
    }

    // Insert node at the end of the doubly-linked list
    private void insertAtEnd(Node n) {
        Node prev = tail.prev;
        prev.next = n;
        n.prev = prev;
        n.next = tail;
        tail.prev = n;
    }

    // Remove a node from the linked list
    private void removeNode(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    // Node class for doubly-linked list
    static class Node {
        char ch;
        Node prev, next;

        Node(char c) {
            ch = c;
        }
    }
}
