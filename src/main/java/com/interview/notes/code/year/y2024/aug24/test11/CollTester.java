package com.interview.notes.code.year.y2024.aug24.test11;

import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollTester {
    public static void main(String[] args) {
        Collector<String, ?, TreeMap<Integer, String>> myCollector =
                Collector.of(TreeMap::new,
                        (s, t) -> s.put(t.length(), t.toLowerCase()),
                        (x, y) -> {
                            x.putAll(y);
                            return x;
                        });

        TreeMap<Integer, String> coll = Stream.of("Bob", "Carol", "Ted", "Bob", "Alice")
                .collect(myCollector);

        coll.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
