package com.interview.notes.code.year.y2024.aug24.test30;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*

LRU Cache Operations
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
Your LRU Cache should support the following operations:
LUCache(int capacity): Initialize the LRU ca che with a positive capacity.
No keys should be pre
sent in the cache initially.
int get(int key): Return the value of the ke
Y, if it exists.
Otherwise, return -1.
void put(int key, int value): Update the valu e of the key if the key exists.
Otherwise, add t
he key-value pair to the cache.
If the number o
f keys exceeds the capacity from this operati on,
evict the least
recently used key.
Note: Any key that is accessed for a valid get) OR put() operation can be considered a recently used key.
Input
• The first line of input contains an integer N, representing the capacity of the cache.
• The second line of input contains an integer M, representing the number of operations.
• The third line of input contains M space-separated strings, each representing an operation. The format of an operation can be one of these:


one of these:
• GET, x: Get the value of key x present in
Cache
• PUT, x, y: Update the value of the key x if x exists; else, add the key-value (x, y) pair to the cache.
Output
An array of values returned by the GET operations
Constraints
1 <= N <= 20


Example #1
Input
2
6
T,3
GET, 2 PUT, 1,100 PUT, 2,125 PUT, 3,150 GET, 1 GE
Output
-1 -1 150
Explanation: So the operations on the LRU cache with capacity 2 are:
GET, 2 -> The Cache is initially empty. i.e., Key 2
does not exist, so return -1
PUT, 1,100 -> Insert Key 1 with value 100 -> [ (1,100)
PUT,2,125 -> Insert Key 2 with value 125 -> [ ( 1,100
), (2, 125 )]
PUT,3,150 -> Cache is full, so delete the least recently used key 1 and insert the new pair -> [2, 125 ) (3, 150) ]
GET, 1 -> Key 1 does not exist, so return -1
GET, 3 -> Key 3 exists, so return its value 150
So, the final array will be [-1, -1, 150]

Example #2
Input
3
5
PUT, 11, 25 PUT, 22, 50 PUT,11,75 GET,11 GET,22
Output
75 50
Explanation: The operations on the LRU cache with capacity 3 are:
PUT, 11,25 -> Insert Key 11 with value 25 -> [ (11,25)
]
PUT,22,50 -> Insert Key 22 with value 50 -> [ (11,
25), (22, 50 ) J
PUT, 11,75 -> Key 11 exists in the cache, so update the Key 11 with value 75 -> [ (22, 50 ), (11, 75) ] (Here, LRU will be key 22 since 11 is updated with the new value).
GET, 11 -> Key 11 exists, so return its value 75
GET, 22 -> Key 22 exists, so return its value 50
So, the final array will be [75, 50].



 */
class LRUCache {
    private final int capacity;
    private final HashMap<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            add(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        if (cache.size() == capacity) {
            remove(tail.prev);
        }
        add(new Node(key, value));
    }

    private void add(Node node) {
        cache.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void remove(Node node) {
        cache.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

public class LRUCacheOperations {
    public static List<Integer> solve(int capacity, List<String> ar) {
        LRUCache cache = new LRUCache(capacity);
        List<Integer> result = new ArrayList<>();

        for (String operation : ar) {
            String[] parts = operation.split(", ");
            if (parts[0].equals("GET")) {
                int key = Integer.parseInt(parts[1]);
                result.add(cache.get(key));
            } else if (parts[0].equals("PUT")) {
                int key = Integer.parseInt(parts[1]);
                int value = Integer.parseInt(parts[2]);
                cache.put(key, value);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        int capacity1 = 2;
        List<String> operations1 = Arrays.asList("GET, 2", "PUT, 1, 100", "PUT, 2, 125", "PUT, 3, 150", "GET, 1", "GET, 3");
        System.out.println(solve(capacity1, operations1));

        // Example 2
        int capacity2 = 3;
        List<String> operations2 = Arrays.asList("PUT, 11, 25", "PUT, 22, 50", "PUT, 11, 75", "GET, 11", "GET, 22");
        System.out.println(solve(capacity2, operations2));
    }
}
