package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.*;
import java.util.stream.*;

public class MapInverter {
    /**
     * Inverts a Map<String,String>: keys→values, values→keys.
     * On duplicate values, the later entry wins.
     */
    public static Map<String,String> invert(Map<String,String> input) {
        return input.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getValue,       // new key
                Map.Entry::getKey,         // new value
                (oldKey, newKey) -> newKey // on duplicate, keep later
            ));
    }

    /** Simple main to run test cases and print PASS/FAIL */
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
            new TestCase(
                mapOf("A1","MCA","A2","BCA","A3","MCA","A4","BBA"),
                mapOf("BCA","A2","MCA","A3","BBA","A4")
            ),
            // edge: empty
            new TestCase(Collections.emptyMap(), Collections.emptyMap()),
            // single entry
            new TestCase(mapOf("X","Y"), mapOf("Y","X"))
        );

        // large test: 1e6 entries, keys K0…K999999 all mapping to "V"
        Map<String,String> big = new LinkedHashMap<>();
        for(int i=0; i<1_000_000; i++) big.put("K"+i, "V");
        Map<String,String> expectedBig = Collections.singletonMap("V","K999999");
        tests.add(new TestCase(big, expectedBig));

        // run
        for(int i=0; i<tests.size(); i++) {
            TestCase tc = tests.get(i);
            Map<String,String> result = invert(tc.input);
            boolean pass = result.equals(tc.expected);
            System.out.printf("Test %d: %s%n", i, pass? "PASS" : "FAIL");
            if(!pass) {
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Got:      " + result);
            }
        }
    }

    // helper for inline map creation
    @SafeVarargs
    private static <K,V> Map<K,V> mapOf(Object... kvs) {
        Map<K,V> m = new LinkedHashMap<>();
        for(int i=0; i<kvs.length; i+=2) {
            m.put((K)kvs[i], (V)kvs[i+1]);
        }
        return m;
    }

    // simple struct for tests
    private static class TestCase {
        final Map<String,String> input, expected;
        TestCase(Map<String,String> in, Map<String,String> exp) {
            this.input = in;
            this.expected = exp;
        }
    }
}