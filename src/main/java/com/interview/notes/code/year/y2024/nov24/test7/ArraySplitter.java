package com.interview.notes.code.year.y2024.nov24.test7;

import java.util.*;

/*
Input = [ 'A', 'B','C', 1, 2, 3, '4', '5', 6, '@', '~', 'D' ]

                 * Split the string, integer, and special characters into new arrays.
                 * Make sure 4 and 5 which is a string should be in integer array list and convert it to integer.

         Console Output:
                 integer = [1,2,3,4,5,6]
                 string = [ 'A', 'B', 'C', 'D' ]
                 chars = [ '@', '~' ]
 */
public class ArraySplitter {

    public static void main(String[] args) {
        Object[] input = {'A', 'B', 'C', 1, 2, 3, '4', '5', 6, '@', '~', 'D'};

        testSplitArray(input);

        // Additional test cases
        testSplitArray(new Object[]{'X', 1, '!', "2", 3, 'Y', '@', '7', '8'});
        testSplitArray(new Object[]{});
        testSplitArray(new Object[]{1, 2, 3, 4, 5});
        testSplitArray(new Object[]{'A', 'B', 'C'});

        // Large input test
        Object[] largeInput = generateLargeInput(100000);
        testSplitArray(largeInput);
    }

    public static void testSplitArray(Object[] input) {
        System.out.println("Input: " + Arrays.toString(input));

        long startTime = System.nanoTime();
        Map<String, List<?>> result = splitArray(input);
        long endTime = System.nanoTime();

        List<Integer> integers = (List<Integer>) result.get("integers");
        List<String> strings = (List<String>) result.get("strings");
        List<Character> chars = (List<Character>) result.get("chars");

        System.out.println("integers = " + integers);
        System.out.println("strings = " + strings);
        System.out.println("chars = " + chars);

        boolean passed = validateResult(integers, strings, chars, input);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        System.out.println("Execution time: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println();
    }

    public static Map<String, List<?>> splitArray(Object[] input) {
        List<Integer> integers = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        List<Character> chars = new ArrayList<>();

        for (Object obj : input) {
            if (obj instanceof Integer) {
                integers.add((Integer) obj);
            } else if (obj instanceof String str) {
                try {
                    integers.add(Integer.parseInt(str));
                } catch (NumberFormatException e) {
                    strings.add(str);
                }
            } else if (obj instanceof Character) {
                char c = (Character) obj;
                if (Character.isDigit(c)) {
                    integers.add(Character.getNumericValue(c));
                } else if (Character.isLetter(c)) {
                    strings.add(String.valueOf(c));
                } else {
                    chars.add(c);
                }
            }
        }

        Map<String, List<?>> result = new HashMap<>();
        result.put("integers", integers);
        result.put("strings", strings);
        result.put("chars", chars);
        return result;
    }

    private static boolean validateResult(List<Integer> integers, List<String> strings, List<Character> chars, Object[] input) {
        Set<Object> originalSet = new HashSet<>(Arrays.asList(input));
        Set<Object> resultSet = new HashSet<>();
        resultSet.addAll(integers);
        resultSet.addAll(strings);
        resultSet.addAll(chars);

        return originalSet.size() == resultSet.size() &&
                integers.stream().allMatch(i -> i instanceof Integer) &&
                strings.stream().allMatch(s -> s instanceof String && s.length() == 1 && Character.isLetter(s.charAt(0))) &&
                chars.stream().allMatch(c -> c instanceof Character && !Character.isLetterOrDigit(c));
    }

    private static Object[] generateLargeInput(int size) {
        Object[] largeInput = new Object[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int type = random.nextInt(4);
            switch (type) {
                case 0:
                    largeInput[i] = random.nextInt(1000);
                    break;
                case 1:
                    largeInput[i] = String.valueOf((char) (random.nextInt(26) + 'A'));
                    break;
                case 2:
                    largeInput[i] = (char) (random.nextInt(10) + '0');
                    break;
                case 3:
                    largeInput[i] = (char) (random.nextInt(15) + 33);
                    break;
            }
        }
        return largeInput;
    }
}
