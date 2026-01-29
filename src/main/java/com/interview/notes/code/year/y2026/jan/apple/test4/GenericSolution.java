package com.interview.notes.code.year.y2026.jan.apple.test4;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class GenericSolution {

    public static void main(String[] args) {

        // Example 1: Doubles
        // Input: [1.5, [2.5, 3.5]]
        List<Object> doublesInput = List.of(1.5, List.of(2.5, 3.5));

        // Usage: Pass 'Double.class' to tell the iterator we want Doubles.
        var doubleIter = new NestedIterator<>(doublesInput, Double.class);

        System.out.print("Doubles: ");
        doubleIter.forEachRemaining(d -> System.out.print(d + " "));
        // Output: 1.5 2.5 3.5
        System.out.println();


        // Example 2: Strings
        // Input: ["Hello", ["World", "!"]]
        List<Object> stringInput = List.of("Hello", List.of("World", "!"));

        // Usage: Pass 'String.class'
        var stringIter = new NestedIterator<>(stringInput, String.class);

        System.out.print("Strings: ");
        stringIter.forEachRemaining(s -> System.out.print(s + " "));
        // Output: Hello World !
    }

    // --- TEST MAIN METHOD ---

    // 1. Add <T> to make the class Generic.
    static class NestedIterator<T> implements Iterator<T> {

        private final Iterator<T> flatIter;
        private final Class<T> type; // We store the type to check it at runtime.

        // 2. Pass the specific class type in the constructor (e.g., Double.class).
        public NestedIterator(List<Object> nestedList, Class<T> type) {
            this.type = type;
            this.flatIter = flatten(nestedList).iterator();
        }

        private Stream<T> flatten(Object input) {
            // 3. Check if input matches our desired type T.
            if (type.isInstance(input)) {
                return Stream.of(type.cast(input)); // Safe cast and return.
            }

            // Standard recursion for Lists.
            if (input instanceof List<?> list) {
                return list.stream().flatMap(this::flatten);
            }

            return Stream.empty();
        }

        @Override
        public boolean hasNext() {
            return flatIter.hasNext();
        }

        @Override
        public T next() {
            return flatIter.next();
        }
    }
}