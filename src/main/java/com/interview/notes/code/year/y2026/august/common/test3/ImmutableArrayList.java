package com.interview.notes.code.year.y2026.august.common.test3;

import java.util.Iterator;                 // Needed to support for-each iteration.
import java.util.NoSuchElementException;   // Used when iterator has no more values.

public final class ImmutableArrayList<T>   // final prevents this class from being extended.
        implements Iterable<T> {           // Iterable lets us use the collection in a for-each loop.

    private final T[] data;                // Private final array stores all elements internally.

    public ImmutableArrayList(T[] input) { // Constructor receives the initial elements.
        data = input.clone();              // Defensive copy prevents caller from changing our array.
    }                                      // Constructor ends here.

    public static void main(String[] args) { // Simple testing without JUnit.

        String[] input = {"Java", "Spring", "MongoDB"}; // Normal test data.

        var list = new ImmutableArrayList<>(input); // Create immutable collection.

        check("Size",                        // Name of test.
                list.size() == 3);           // Expected size is 3.

        check("Get element",                 // Test get().
                list.get(1).equals("Spring"));// Index 1 should contain Spring.

        input[0] = "Changed";                // Modify the original input array.

        check("Immutable after input change",// Verify defensive copying.
                list.get(0).equals("Java")); // Internal value must still be Java.

        var text = new StringBuilder();      // Used to test iterator output.

        for (var value : list)               // Calls our custom iterator().
            text.append(value).append(",");  // Add each value to result.

        check("Iterator",                    // Iterator test.
                text.toString().equals(      // Compare with expected order.
                        "Java,Spring,MongoDB,")); // Expected iterator result.

        var empty = new ImmutableArrayList<>(new String[0]); // Create empty collection.

        check("Empty list",                  // Empty collection test.
                empty.size() == 0);          // Expected size is zero.

        checkException("Negative index",     // Test invalid negative index.
                () -> list.get(-1));         // This should throw an exception.

        checkException("Large index",        // Test index greater than size.
                () -> list.get(100));        // This should also throw.

        Integer[] largeInput =               // Create array for large-data testing.
                new Integer[1_000_000];      // One million elements.

        for (int i = 0; i < largeInput.length; i++) // Fill large array.
            largeInput[i] = i;               // Store index as the value.

        var largeList =                      // Create immutable large collection.
                new ImmutableArrayList<>(largeInput); // Constructor copies all elements.

        check("Large data size",             // Test large collection size.
                largeList.size() == 1_000_000); // Expected one million elements.

        check("Large data last value",       // Test O(1) access near the end.
                largeList.get(999_999) == 999_999); // Verify final value.

        largeInput[999_999] = -1;            // Modify original large array.

        check("Large data immutable",        // Verify defensive copy for large input.
                largeList.get(999_999) == 999_999); // Internal value must not change.
    }                                       // main method ends here.

    private static void check(              // Small helper for PASS/FAIL tests.
            String name,                    // Test name.
            boolean result) {               // True means test passed.
        System.out.println(                 // Print result to console.
                (result ? "PASS" : "FAIL")  // Select PASS or FAIL.
                        + " - " + name);     // Add test name.
    }                                       // check method ends here.

    private static void checkException(     // Helper for exception tests.
            String name,                    // Test name.
            Runnable test) {                // Code that should throw an exception.

        try {                               // Start protected execution.
            test.run();                     // Execute the test.
            System.out.println("FAIL - " + name); // No exception means failure.
        } catch (IndexOutOfBoundsException e) { // Expected exception.
            System.out.println("PASS - " + name); // Correct exception means pass.
        }                                   // Catch ends here.
    }                                       // checkException ends here.

    public T get(int index) {              // Returns the element at the requested index.
        if (index < 0 || index >= data.length) // Check whether index is outside the valid range.
            throw new IndexOutOfBoundsException(index); // Throw standard exception for invalid index.

        return data[index];                // Array access is O(1).
    }                                      // get method ends here.

    public int size() {                    // Returns the number of elements.
        return data.length;                // Array length already stores the size.
    }                                      // size method ends here.

    @Override                              // Indicates that we implement Iterable.iterator().
    public Iterator<T> iterator() {        // Creates a read-only iterator.
        return new Iterator<>() {          // Java 9+ diamond syntax keeps the code small.

            private int index;             // Tracks the current iterator position; starts at 0.

            @Override                      // Implements Iterator.hasNext().
            public boolean hasNext() {     // Checks whether another element exists.
                return index < data.length;// True until we reach the end.
            }                              // hasNext ends here.

            @Override                      // Implements Iterator.next().
            public T next() {              // Returns the next element.
                if (!hasNext())            // Check if iteration is already finished.
                    throw new NoSuchElementException(); // Standard iterator behavior.

                return data[index++];      // Return current value, then move index forward.
            }                              // next ends here.
        };                                 // Anonymous iterator object ends here.
    }                                      // iterator method ends here.
}                                           // ImmutableArrayList class ends here.