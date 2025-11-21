package com.interview.notes.code.datastructure.Set;

public class CustomHashset<T> {

    private static final int SIZE = 100;
    private final Entry<T>[] buckets;
    private int size;

    public CustomHashset() {
        this.buckets = new Entry[SIZE];
        this.size = 0;

    }

    private int hash(T element) {
        return element.hashCode() % buckets.length;
    }

    public Boolean add(T element) {
        int index = hash(element);

        Entry<T> current = buckets[index];
        while (current != null) {
            if (current.key.equals(element)) return false;
            current = current.next;
        }
        Entry<T> entry = new Entry<T>();
        entry.setKey(element);
        entry.setNext(buckets[index]);
        buckets[index] = entry;
        size++;

        return true;
    }

    public int size() {
        return size;
    }

    private static class Entry<T> {
        private T key;
        private Entry next;

        public T getKey() {
            return key;
        }

        public void setKey(T element) {
            this.key = element;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }
    }
}