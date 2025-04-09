package com.interview.notes.code.year.y2025.march.common.test2;

import java.util.*;

public class PreJava8Filter {

    /**
     * A generic method that filters any Collection of integers
     *
     * @param <T>     the specific Collection type
     * @param numbers the collection of integers to filter
     * @param isEven  true to keep even numbers, false to keep odd numbers
     * @return a new collection of the same type containing the filtered numbers
     */
    @SuppressWarnings("unchecked")
    public static <T extends Collection<Integer>> T filterNumbers(T numbers, boolean isEven) {
        Collection<Integer> result;

        // Create the same type of collection as the input
        if (numbers instanceof List) {
            result = new ArrayList<>();
        } else if (numbers instanceof Set) {
            result = new HashSet<>();
        } else {
            // Default to ArrayList if the collection type is unknown
            result = new ArrayList<>();
        }

        // Filter according to the isEven parameter
        for (Integer number : numbers) {
            if ((number % 2 == 0) == isEven) {  // Keep if: both even, or both odd
                result.add(number);
            }
        }

        // Cast to the original collection type
        return (T) result;
    }

    public static void main(String[] args) {
        // Test with a List
        List<Integer> numbersList = new ArrayList<>();
        numbersList.add(1);
        numbersList.add(2);
        numbersList.add(3);
        numbersList.add(4);
        numbersList.add(5);

        List<Integer> evenNumbersList = filterNumbers(numbersList, true);
        System.out.println("Even numbers from List: " + evenNumbersList);

        // Test with a Set
        Set<Integer> numbersSet = new HashSet<>();
        numbersSet.add(1);
        numbersSet.add(2);
        numbersSet.add(3);
        numbersSet.add(4);
        numbersSet.add(5);

        Set<Integer> oddNumbersSet = filterNumbers(numbersSet, false);
        System.out.println("Odd numbers from Set: " + oddNumbersSet);
    }
}
