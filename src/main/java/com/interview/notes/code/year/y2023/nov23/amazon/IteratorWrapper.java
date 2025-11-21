package com.interview.notes.code.year.y2023.nov23.amazon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorWrapper {

    private final Iterator<Integer> iterator;
    private final int n;
    private final List<Integer> nextValues;

    public IteratorWrapper(Iterator<Integer> iterator, int n) {
        this.iterator = iterator;
        this.n = n;
        this.nextValues = new ArrayList<>();
    }

    public int next(int m) {
        if (m > n) {
            throw new IllegalArgumentException("m cannot be greater than n");
        }

        while (nextValues.size() < m) {
            if (iterator.hasNext()) {
                nextValues.add(iterator.next());
            } else {
                break;
            }
        }

        return nextValues.remove(0);
    }

    public boolean hasNext(int m) {
        if (m > n) {
            throw new IllegalArgumentException("m cannot be greater than n");
        }

        if (nextValues.size() >= m) {
            return true;
        }

        while (nextValues.size() < m) {
            if (!iterator.hasNext()) {
                return false;
            }

            nextValues.add(iterator.next());
        }

        return true;
    }

    public int peek(int m) {
        if (m > n) {
            throw new IllegalArgumentException("m cannot be greater than n");
        }

        if (nextValues.size() >= m) {
            return nextValues.get(m - 1);
        }

        while (nextValues.size() < m) {
            if (!iterator.hasNext()) {
                return -1;
            }

            nextValues.add(iterator.next());
        }

        return nextValues.get(m - 1);
    }

    public void skip(int m) {
        if (m > n) {
            throw new IllegalArgumentException("m cannot be greater than n");
        }

        for (int i = 0; i < m; i++) {
            if (iterator.hasNext()) {
                iterator.next();
            } else {
                break;
            }
        }

        nextValues.clear();
    }
}
