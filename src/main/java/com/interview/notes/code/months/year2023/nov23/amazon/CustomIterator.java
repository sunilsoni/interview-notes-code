package com.interview.notes.code.months.year2023.nov23.amazon;

import java.util.Iterator;
import java.util.List;

public class CustomIterator<T> {
    private Iterator<T> iterator;
    private T nextElement;
    private boolean hasNext;

    public CustomIterator(Iterator<T> iterator) {
        this.iterator = iterator;
        // Initialize the nextElement and hasNext flags
        moveToNext();
    }

    public static void main(String[] args) {
        // Example usage
        Iterator<Integer> iterator = List.of(1, 2, 3, 4, 5).iterator();
        CustomIterator<Integer> customIterator = new CustomIterator<>(iterator);

        System.out.println(customIterator.hasNext(3)); // true
        System.out.println(customIterator.peek(3)); // 1
        customIterator.skip(2);
        System.out.println(customIterator.next()); // 3
    }

    public boolean hasNext() {
        return hasNext;
    }

    public T next() {
        if (!hasNext) {
            throw new IllegalStateException("No more elements");
        }
        T current = nextElement;
        moveToNext();
        return current;
    }

    public boolean hasNext(int m) {
        int count = 0;
        Iterator<T> tempIterator = iterator;
        while (count < m && tempIterator.hasNext()) {
            tempIterator.next();
            count++;
        }
        return count == m;
    }

    public T peek(int m) {
        if (!hasNext(m)) {
            throw new IllegalStateException("Not enough elements to peek");
        }

        Iterator<T> tempIterator = iterator;
        T result = nextElement;
        int count = 0;
        while (count < m) {
            result = tempIterator.next();
            count++;
        }
        return result;
    }

    public void skip(int m) {
        if (!hasNext(m)) {
            throw new IllegalStateException("Not enough elements to skip");
        }

        int count = 0;
        while (count < m) {
            next();
            count++;
        }
    }

    private void moveToNext() {
        hasNext = iterator.hasNext();
        if (hasNext) {
            nextElement = iterator.next();
        }
    }
}
