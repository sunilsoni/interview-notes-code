package com.interview.notes.code.year.y2025.october.meta.test3;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

interface InMemoryDB {
    void set(int timestamp, String key, String field, int value);
    boolean compareAndSet(int timestamp, String key, String field, int expectedValue, int newValue);
    boolean compareAndDelete(int timestamp, String key, String field, int expectedValue);
    Optional<Integer> get(int timestamp, String key, String field);
    List<String> scan(int timestamp, String key);
    List<String> scanByPrefix(int timestamp, String key, String prefix);
    void setWithTTL(int timestamp, String key, String field, int value, int ttl);
    boolean compareAndSetWithTTL(int timestamp, String key, String field, int expectedValue, int newValue, int ttl);
}

class InMemoryDBImpl implements InMemoryDB {
    private final Map<String, Map<String, NavigableMap<Integer, ValueRecord>>> store = new ConcurrentHashMap<>();

    private NavigableMap<Integer, ValueRecord> fieldHistory(String key, String field) {
        return store.computeIfAbsent(key, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(field, f -> new TreeMap<>());
    }

    private Optional<ValueRecord> currentBefore(int timestamp, String key, String field) {
        Map<String, NavigableMap<Integer, ValueRecord>> m = store.get(key);
        if (m == null) return Optional.empty();
        NavigableMap<Integer, ValueRecord> t = m.get(field);
        if (t == null) return Optional.empty();
        Map.Entry<Integer, ValueRecord> e = t.lowerEntry(timestamp);
        if (e == null) return Optional.empty();
        return Optional.ofNullable(e.getValue());
    }

    private boolean isAlive(ValueRecord v, int timestamp) {
        return v != null && (v.expireAt == -1 || timestamp < v.expireAt);
    }

    @Override
    public void set(int timestamp, String key, String field, int value) {
        fieldHistory(key, field).put(timestamp, new ValueRecord(value, -1));
    }

    @Override
    public boolean compareAndSet(int timestamp, String key, String field, int expectedValue, int newValue) {
        Optional<ValueRecord> cur = currentBefore(timestamp, key, field);
        if (cur.isPresent() && cur.get().value == expectedValue && isAlive(cur.get(), timestamp)) {
            fieldHistory(key, field).put(timestamp, new ValueRecord(newValue, -1));
            return true;
        }
        return false;
    }

    @Override
    public boolean compareAndDelete(int timestamp, String key, String field, int expectedValue) {
        Optional<ValueRecord> cur = currentBefore(timestamp, key, field);
        if (cur.isPresent() && cur.get().value == expectedValue && isAlive(cur.get(), timestamp)) {
            fieldHistory(key, field).put(timestamp, null);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Integer> get(int timestamp, String key, String field) {
        Map<String, NavigableMap<Integer, ValueRecord>> m = store.get(key);
        if (m == null) return Optional.empty();
        NavigableMap<Integer, ValueRecord> t = m.get(field);
        if (t == null) return Optional.empty();
        Map.Entry<Integer, ValueRecord> e = t.floorEntry(timestamp);
        if (e == null || e.getValue() == null) return Optional.empty();
        ValueRecord v = e.getValue();
        if (!isAlive(v, timestamp)) return Optional.empty();
        return Optional.of(v.value);
    }

    @Override
    public List<String> scan(int timestamp, String key) {
        Map<String, NavigableMap<Integer, ValueRecord>> fields = store.get(key);
        if (fields == null) return Collections.emptyList();
        return fields.entrySet().stream()
                .map(e -> {
                    Optional<Integer> v = get(timestamp, key, e.getKey());
                    return v.map(val -> e.getKey() + "(" + val + ")").orElse(null);
                })
                .filter(Objects::nonNull)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> scanByPrefix(int timestamp, String key, String prefix) {
        Map<String, NavigableMap<Integer, ValueRecord>> fields = store.get(key);
        if (fields == null) return Collections.emptyList();
        return fields.entrySet().stream()
                .filter(e -> e.getKey().startsWith(prefix))
                .map(e -> {
                    Optional<Integer> v = get(timestamp, key, e.getKey());
                    return v.map(val -> e.getKey() + "(" + val + ")").orElse(null);
                })
                .filter(Objects::nonNull)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void setWithTTL(int timestamp, String key, String field, int value, int ttl) {
        fieldHistory(key, field).put(timestamp, new ValueRecord(value, timestamp + ttl));
    }

    @Override
    public boolean compareAndSetWithTTL(int timestamp, String key, String field, int expectedValue, int newValue, int ttl) {
        Optional<ValueRecord> cur = currentBefore(timestamp, key, field);
        if (cur.isPresent() && cur.get().value == expectedValue && isAlive(cur.get(), timestamp)) {
            fieldHistory(key, field).put(timestamp, new ValueRecord(newValue, timestamp + ttl));
            return true;
        }
        return false;
    }

    private static class ValueRecord {
        int value;
        int expireAt;
        ValueRecord(int value, int expireAt) { this.value = value; this.expireAt = expireAt; }
    }
}

public class InMemoryDBMain {
    public static void main(String[] args) {
        InMemoryDB db = new InMemoryDBImpl();
        db.setWithTTL(10, "foo", "bar", 150, 50);
        System.out.println(db.get(20, "foo", "bar")); 
        System.out.println(db.get(70, "foo", "bar"));
        db.setWithTTL(40, "foo", "two", 20, 60);
        System.out.println(db.scan(60, "foo"));
        db.compareAndSetWithTTL(41, "foo", "two", 20, 30, 80);
        System.out.println(db.scan(80, "foo"));
        db.setWithTTL(95, "foo", "bar", 7, 10);
        System.out.println(db.scan(100, "foo"));
    }
}