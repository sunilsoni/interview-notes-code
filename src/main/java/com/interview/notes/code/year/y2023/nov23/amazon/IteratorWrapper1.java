package com.interview.notes.code.year.y2023.nov23.amazon;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a simple iterator that only has a next() method.
 * Write a wrapper that takes a constructor value n and provides
 * next(m), hasNext(m), peek(m) and skip(m), where m <= n.
 *
 * @param <T>
 */
public class IteratorWrapper1<T> {
    private Iterator<T> iterator;
    private Queue<T> queue;
    private int n;

    public IteratorWrapper1(Iterator<T> iterator, int n) {
        this.iterator = iterator;
        this.n = n;
        this.queue = new LinkedList<>();
        // Preload the queue with up to n elements
        for (int i = 0; i < n; i++) {
            if (iterator.hasNext()) {
                queue.add(iterator.next());
            } else {
                break;
            }
        }
    }

    // Main method for demonstration
    public static void main(String[] args) {
        // Example usage of IteratorWrapper
    }

    public T[] next(int m) {
        T[] result = (T[]) new Object[Math.min(m, queue.size())];
        for (int i = 0; i < result.length; i++) {
            result[i] = queue.poll();
            // Replenish the queue from the iterator
            if (iterator.hasNext()) {
                queue.add(iterator.next());
            }
        }
        return result;
    }

    public boolean hasNext(int m) {
        return queue.size() >= m;
    }

    public T peek(int m) {
        if (m <= 0 || m > queue.size()) {
            throw new IllegalArgumentException("Invalid argument for peek method");
        }
        return (T) queue.toArray()[m - 1];
    }

    public void skip(int m) {
        for (int i = 0; i < m && !queue.isEmpty(); i++) {
            queue.poll();
            // Replenish the queue from the iterator
            if (iterator.hasNext()) {
                queue.add(iterator.next());
            }
        }
    }
}
