package com.interview.notes.code.year.y2025.october.meta.test2;

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
}

class InMemoryDBImpl implements InMemoryDB {
    private final Map<String, Map<String, NavigableMap<Integer, Integer>>> store = new ConcurrentHashMap<>();

    private NavigableMap<Integer, Integer> fieldHistory(String key, String field) {
        return store.computeIfAbsent(key, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(field, f -> new TreeMap<>());
    }

    private Optional<Integer> currentBefore(int timestamp, String key, String field) {
        Map<String, NavigableMap<Integer, Integer>> m = store.get(key);
        if (m == null) return Optional.empty();
        NavigableMap<Integer, Integer> t = m.get(field);
        if (t == null) return Optional.empty();
        Map.Entry<Integer, Integer> e = t.lowerEntry(timestamp);
        if (e == null) return Optional.empty();
        return Optional.ofNullable(e.getValue());
    }

    @Override
    public void set(int timestamp, String key, String field, int value) {
        fieldHistory(key, field).put(timestamp, value);
    }

    @Override
    public boolean compareAndSet(int timestamp, String key, String field, int expectedValue, int newValue) {
        Optional<Integer> cur = currentBefore(timestamp, key, field);
        if (cur.isPresent() && cur.get() == expectedValue) {
            fieldHistory(key, field).put(timestamp, newValue);
            return true;
        }
        return false;
    }

    @Override
    public boolean compareAndDelete(int timestamp, String key, String field, int expectedValue) {
        Optional<Integer> cur = currentBefore(timestamp, key, field);
        if (cur.isPresent() && cur.get() == expectedValue) {
            fieldHistory(key, field).put(timestamp, null);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Integer> get(int timestamp, String key, String field) {
        Map<String, NavigableMap<Integer, Integer>> m = store.get(key);
        if (m == null) return Optional.empty();
        NavigableMap<Integer, Integer> t = m.get(field);
        if (t == null) return Optional.empty();
        Map.Entry<Integer, Integer> e = t.floorEntry(timestamp);
        if (e == null) return Optional.empty();
        Integer v = e.getValue();
        return v == null ? Optional.empty() : Optional.of(v);
    }

    @Override
    public List<String> scan(int timestamp, String key) {
        Map<String, NavigableMap<Integer, Integer>> fields = store.get(key);
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
        Map<String, NavigableMap<Integer, Integer>> fields = store.get(key);
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
}

public class InMemoryDBMain {
    public static void main(String[] args) {
        InMemoryDB db = new InMemoryDBImpl();
        db.set(0, "employee", "age", 20);
        db.set(1, "employee", "article", 30);
        db.set(2, "employee", "particle", 40);
        System.out.println("Scan prefix 'a' -> " + db.scanByPrefix(3, "employee", "a"));
        db.set(10, "dept4", "floors", 8);
        db.set(23, "dept4", "flower", 230);
        System.out.println("Scan prefix 'flo' -> " + db.scanByPrefix(25, "dept4", "flo"));
        db.set(31, "dept4", "floors", 19);
        System.out.println("Scan prefix 'flo' -> " + db.scanByPrefix(39, "dept4", "flo"));
    }
}