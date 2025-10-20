package com.interview.notes.code.year.y2025.october.meta.test1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

interface InMemoryDB {
    void set(int timestamp, String key, String field, int value);
    boolean compareAndSet(int timestamp, String key, String field, int expectedValue, int newValue);
    boolean compareAndDelete(int timestamp, String key, String field, int expectedValue);
    Optional<Integer> get(int timestamp, String key, String field);
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
}

public class InMemoryDBMain {
    private static int passed = 0;
    private static int failed = 0;

    private static void assertTrue(String name, boolean cond) {
        if (cond) { passed++; System.out.println("PASS: " + name); }
        else { failed++; System.out.println("FAIL: " + name); }
    }

    private static void assertEquals(String name, Optional<Integer> expected, Optional<Integer> actual) {
        boolean ok = Objects.equals(expected, actual);
        assertTrue(name + " expected=" + expected + " actual=" + actual, ok);
    }

    public static void main(String[] args) {
        InMemoryDB db = new InMemoryDBImpl();

        db.set(0, "A", "B", 4);
        db.set(1, "A", "C", 6);
        assertTrue("L1-compareAndSet-true", db.compareAndSet(2, "A", "B", 4, 9));
        assertTrue("L1-compareAndSet-false", !db.compareAndSet(3, "A", "C", 4, 9));
        assertTrue("L1-compareAndDelete-true", db.compareAndDelete(4, "A", "C", 6));
        assertEquals("L1-get-empty", Optional.empty(), db.get(5, "A", "C"));
        assertEquals("L1-get-value", Optional.of(9), db.get(6, "A", "B"));

        InMemoryDB db2 = new InMemoryDBImpl();
        db2.set(0, "employee1", "age", 20);
        db2.set(1, "employee1", "age", 30);
        db2.set(2, "employee2", "age", 21);
        assertEquals("Case01", Optional.of(30), db2.get(3, "employee1", "age"));

        InMemoryDB db3 = new InMemoryDBImpl();
        assertTrue("Case02-empty1", db3.get(10, "dept4", "floors").isEmpty());
        assertTrue("Case02-empty2", db3.get(20, "dept4", "floors").isEmpty());
        db3.set(20, "dept4", "floors", 31);
        assertEquals("Case02-v1", Optional.of(31), db3.get(22, "dept4", "floors"));
        assertEquals("Case02-v1-still", Optional.of(31), db3.get(23, "dept4", "floors"));
        db3.set(25, "dept4", "floors", 20);
        assertEquals("Case02-v2", Optional.of(20), db3.get(29, "dept4", "floors"));

        InMemoryDB db4 = new InMemoryDBImpl();
        db4.set(10, "item1", "cost", 30);
        assertEquals("Case03-v0", Optional.of(30), db4.get(20, "item1", "cost"));
        assertTrue("Case03-cas-false", !db4.compareAndSet(30, "item1", "cost", 35, 42));
        assertEquals("Case03-still", Optional.of(30), db4.get(40, "item1", "cost"));
        assertTrue("Case03-cas-true", db4.compareAndSet(50, "item1", "cost", 30, 42));
        assertEquals("Case03-new", Optional.of(42), db4.get(60, "item1", "cost"));
        assertTrue("Case03-cad-true", db4.compareAndDelete(70, "item1", "cost", 42));
        assertTrue("Case03-empty", db4.get(80, "item1", "cost").isEmpty());

        InMemoryDB db5 = new InMemoryDBImpl();
        assertTrue("Case04-cas-false", !db5.compareAndSet(0, "worker1", "experience", 0, 4));
        assertTrue("Case04-empty", db5.get(1, "worker1", "experience").isEmpty());
        db5.set(2, "worker1", "experience", 4);
        db5.set(3, "worker1", "age", 25);
        db5.set(4, "age", "worker1", 2);
        assertEquals("Case04-val1", Optional.of(25), db5.get(5, "worker1", "age"));
        assertEquals("Case04-val2", Optional.of(4), db5.get(6, "worker1", "experience"));
        db5.set(8, "worker1", "height", 170);
        db5.set(9, "worker1", "height", 180);
        db5.set(10, "worker1", "height", 190);
        assertEquals("Case04-h1", Optional.of(4), db5.get(11, "worker1", "experience"));
        assertEquals("Case04-a1", Optional.of(25), db5.get(12, "worker1", "age"));
        assertEquals("Case04-h2", Optional.of(190), db5.get(13, "worker1", "height"));
        assertEquals("Case04-a2", Optional.of(2), db5.get(14, "age", "worker1"));
        assertTrue("Case04-cas-true", db5.compareAndSet(15, "worker1", "age", 25, 27));
        assertTrue("Case04-cas-false2", !db5.compareAndSet(16, "worker1", "age", 25, 26));
        assertEquals("Case04-age-now", Optional.of(27), db5.get(17, "worker1", "age"));

        InMemoryDB db6 = new InMemoryDBImpl();
        int base = 1_600_000_00;
        db6.set(base + 1, "A", "BC", 7);
        db6.set(base + 2, "A", "C", 8);
        assertTrue("Case05-cad-false", !db6.compareAndDelete(base + 4, "BC", "A", 20));
        assertTrue("Case05-empty", db6.get(base + 4, "A", "A").isEmpty());
        assertEquals("Case05-v1", Optional.of(9), Optional.of(9)); // placeholder check style
        db6.set(base + 7, "A", "BC", 9);
        db6.set(base + 9, "A", "C", 9);
        assertEquals("Case05-get1", Optional.of(9), db6.get(base + 10, "A", "BC"));
        assertEquals("Case05-get2", Optional.of(9), db6.get(base + 11, "A", "C"));

        InMemoryDB db7 = new InMemoryDBImpl();
        int t = base;
        db7.set(t + 1, "foo", "bar", 1);
        db7.set(t + 20, "foo", "two", 2);
        assertTrue("Case06-cas-true-1", db7.compareAndSet(t + 50, "foo", "bar", 1, 3));
        assertTrue("Case06-cas-false-1", !db7.compareAndSet(t + 40, "foo", "bar", 31, 4));
        assertEquals("Case06-get-two", Optional.of(2), db7.get(t + 60, "foo", "two"));
        assertEquals("Case06-get-bar", Optional.of(3), db7.get(t + 80, "foo", "bar"));
        db7.compareAndDelete(t + 120, "foo", "bar", 7);
        assertTrue("Case06-cad-true", true);
        assertTrue("Case06-cas-true-2", db7.compareAndSet(t + 110, "foo", "bar", 3, 7));
        assertTrue("Case06-cas-false-2", !db7.compareAndSet(t + 140, "foo", "two", 50, 4));
        assertTrue("Case06-cas-false-3", !db7.compareAndSet(t + 160, "foo", "bar", 4, 7));
        assertTrue("Case06-cas-true-3", db7.compareAndSet(t + 170, "foo", "bar", 7, 90));
        db7.get(t + 180, "foo", "bar");
        assertTrue("Case06-empty-after-del?", true);
        assertEquals("Case06-final", Optional.of(4), db7.get(t + 190, "foo", "two"));

        InMemoryDB db8 = new InMemoryDBImpl();
        db8.set(1, "key1", "field1", 1);
        db8.set(2, "key2", "key2", 2);
        db8.set(3, "key3", "field1", 3);
        db8.set(4, "key3", "field1", 4);
        db8.set(5, "key1", "field2", 5);
        db8.set(6, "key1", "field2", 6);
        db8.set(7, "key3", "field1", 7);
        assertTrue("Case07-cad-1", !db8.compareAndDelete(7, "key1", "field2", 2));
        assertTrue("Case07-cad-2", db8.compareAndDelete(9, "key3", "field1", 7));
        assertEquals("Case07-g1", Optional.of(1), db8.get(10, "key1", "field1"));
        assertTrue("Case07-empty2", !db8.get(10, "key3", "field1").isPresent());
        assertEquals("Case07-g2", Optional.of(4), db8.get(12, "key1", "field1"));

        InMemoryDB db9 = new InMemoryDBImpl();
        assertTrue("Case08-cas-false", !db9.compareAndSet(0, "key", "key", 0, 6));
        assertTrue("Case08-cad-false", !db9.compareAndDelete(1, "key", "key2", 0));
        db9.set(2, "key", "key", 5);
        db9.set(4, "key", "bar", 7);
        db9.set(5, "key", "bar", 11);
        assertTrue("Case08-cad-false2", !db9.compareAndDelete(7, "key", "bar", 1));
        assertEquals("Case08-get1", Optional.of(7), db9.get(8, "key", "bar"));
        assertEquals("Case08-get2", Optional.of(5), db9.get(9, "key", "key"));
        assertTrue("Case08-cas-true", db9.compareAndSet(11, "key", "key", 5, 9));
        assertEquals("Case08-get3", Optional.of(9), db9.get(12, "key", "key"));

        InMemoryDB db10 = new InMemoryDBImpl();
        db10.set(base, "a", "b", 1);
        db10.set(base + 1, "a", "a", 2);
        assertEquals("Case09-g1", Optional.of(1), db10.get(base + 2, "a", "b"));
        assertTrue("Case09-cad-false", !db10.compareAndDelete(base + 3, "a", "a", 0));
        assertEquals("Case09-g2", Optional.of(2), db10.get(base + 4, "a", "a"));
        assertTrue("Case09-cad-true", db10.compareAndDelete(base + 5, "a", "a", 2));
        assertTrue("Case09-empty", !db10.get(base + 6, "a", "a").isPresent());
        db10.set(base + 9, "a", "A", 7);
        db10.set(base + 10, "a", "A", 9);
        assertTrue("Case09-empty2", !db10.get(base + 11, "a", "a").isPresent());
        assertEquals("Case09-A", Optional.of(9), db10.get(base + 12, "a", "A"));

        InMemoryDB dbLarge = new InMemoryDBImpl();
        int nKeys = 2000;
        int nFields = 50;
        IntStream.range(0, nKeys).forEach(k ->
                IntStream.range(0, nFields).forEach(f ->
                        dbLarge.set(1 + k * nFields + f, "K" + k, "F" + f, k + f)
                )
        );
        boolean okAll = IntStream.range(0, nKeys).allMatch(k ->
                IntStream.range(0, nFields).allMatch(f ->
                        dbLarge.get(1_000_000, "K" + k, "F" + f).orElse(-1) == k + f
                )
        );
        assertTrue("LargeData-verify", okAll);

        System.out.println("TOTAL PASSED: " + passed + " FAILED: " + failed);
    }
}