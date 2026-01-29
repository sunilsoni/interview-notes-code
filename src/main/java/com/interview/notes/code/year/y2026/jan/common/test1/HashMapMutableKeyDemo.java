package com.interview.notes.code.year.y2026.jan.common.test1;

import java.util.HashMap;
import java.util.Map;

public class HashMapMutableKeyDemo {

    // Main method to execute
    public static void main(String[] args) {

        final Map<StringWrapper, String> dataMap = new HashMap<>();

        StringWrapper wrapper1 = new StringWrapper("one");
        StringWrapper wrapper2 = new StringWrapper("two");
        StringWrapper wrapper3 = new StringWrapper("three");

        dataMap.put(wrapper1, "1");
        dataMap.put(wrapper2, "2");
        dataMap.put(wrapper3, "3");

        // Step 1
        System.out.println(wrapper1.equals(wrapper3)); // false

        // Step 2 - mutate key
        wrapper1.setData("three");

        // Step 3
        System.out.println(wrapper1.hashCode()); // same as wrapper3
        System.out.println(wrapper3.hashCode()); // same as wrapper1

        // Step 4
        System.out.println(wrapper1.equals(wrapper3)); // true

        // Step 5 - lookup fails
        System.out.println(dataMap.get(wrapper1)); // null
    }

    // Mutable key class
    static class StringWrapper {

        private String data;

        public StringWrapper(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            return true;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }
}
