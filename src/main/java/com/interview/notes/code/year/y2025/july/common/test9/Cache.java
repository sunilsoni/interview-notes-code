package com.interview.notes.code.year.y2025.july.common.test9;

// Abstract base class
abstract class Cache {
    // Evict according to policy
    abstract void evictNode();

    abstract void write(int key, int value);

    abstract int get(int key);
}