package com.interview.notes.code.months.march24.test7;

import java.util.*;
import java.util.stream.*;

public class TestMyCollector {
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
