package com.interview.notes.code.year.y2026.jan.apple.test3;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class MixedTypeSolution {

    public static void main(String[] args) {
        System.out.println("--- Testing Mixed Types ---");

        // Input: [1, "Hello", [2.5, "World"], 100]
        // Contains: Integer, String, Double, Integer
        List<Object> mixedInput = List.of(
                1,
                "Hello",
                List.of(2.5, "World"),
                100
        );

        var iterator = new MixedNestedIterator(mixedInput);

        // Loop through and print whatever we find
        while (iterator.hasNext()) {
            Object item = iterator.next();

            // We can check what type we got
            if (item instanceof Integer) {
                System.out.println("Found Integer: " + item);
            } else if (item instanceof String) {
                System.out.println("Found String:  " + item);
            } else if (item instanceof Double) {
                System.out.println("Found Double:  " + item);
            }
        }
    }

    // --- TEST MAIN METHOD ---

    // Change <Integer> to <Object> to allow ANY data type.
    static class MixedNestedIterator implements Iterator<Object> {

        private final Iterator<Object> flatIter;

        public MixedNestedIterator(List<Object> nestedList) {
            this.flatIter = flatten(nestedList).iterator();
        }

        private Stream<Object> flatten(Object input) {
            // Case 1: If it is a List, flatten it recursively.
            if (input instanceof List<?> list) {
                return list.stream().flatMap(this::flatten);
            }

            // Case 2: If it is NOT a list (and not null), it is data.
            // This captures Integers, Strings, Doubles, etc.
            if (input != null) {
                return Stream.of(input);
            }

            // Case 3: Nulls or empty things disappear.
            return Stream.empty();
        }

        @Override
        public boolean hasNext() {
            return flatIter.hasNext();
        }

        @Override
        public Object next() {
            return flatIter.next();
        }
    }
}