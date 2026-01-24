package com.interview.notes.code.year.y2026.jan.common.test2;

import java.util.HashMap;
import java.util.Map;

public class VerifyMapObjects {

    public static void main(String[] args) {

        Map<StringWrapper, String> dataMap = new HashMap<>();

        StringWrapper w1 = new StringWrapper("one");
        StringWrapper w2 = new StringWrapper("two");
        StringWrapper w3 = new StringWrapper("three");

        dataMap.put(w1, "1");
        dataMap.put(w2, "2");
        dataMap.put(w3, "3");

        // 1️⃣ Verify number of entries
        System.out.println("Map size = " + dataMap.size());

        // 2️⃣ Verify each entry (proves separate nodes)
        for (Map.Entry<StringWrapper, String> e : dataMap.entrySet()) {
            System.out.println(
                "Key object = " + System.identityHashCode(e.getKey())
                + ", Value = " + e.getValue()
            );
        }
    }

    static class StringWrapper {
        String data;

        StringWrapper(String data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            return true;   // always true
        }

        @Override
        public int hashCode() {
            return 1;      // constant hash
        }
    }
}
