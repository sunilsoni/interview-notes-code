package com.interview.notes.code.months.nov24.test8;

import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

public class ArraySplitter {

    private static final Pattern INTEGER_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern STRING_PATTERN = Pattern.compile("^[a-zA-Z]$");

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
        
        System.out.println("integers = " + result.get("integers"));
        System.out.println("strings = " + result.get("strings"));
        System.out.println("chars = " + result.get("chars"));
        
        boolean passed = validateResult(result, input);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        System.out.println("Execution time: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println();
    }
//
//    public static Map<String, List<?>> splitArray(Object[] input) {
//        return Arrays.stream(input)
//            .collect(Collectors.groupingBy(
//                ArraySplitter::categorize,
//                Collectors.mapping(
//                    ArraySplitter::transform,
//                    Collectors.toList()
//                )
//            ));
//    }

    private static String categorize(Object obj) {
        String str = obj.toString();
        if (obj instanceof Integer || INTEGER_PATTERN.matcher(str).matches()) {
            return "integers";
        } else if (STRING_PATTERN.matcher(str).matches()) {
            return "strings";
        } else {
            return "chars";
        }
    }

    private static Object transform(Object obj) {
        String str = obj.toString();
        if (INTEGER_PATTERN.matcher(str).matches()) {
            return Integer.parseInt(str);
        } else if (STRING_PATTERN.matcher(str).matches()) {
            return str;
        } else {
            return str.charAt(0);
        }
    }

    private static boolean validateResult(Map<String, List<?>> result, Object[] input) {
        List<Integer> integers = (List<Integer>) result.get("integers");
        List<String> strings = (List<String>) result.get("strings");
        List<Character> chars = (List<Character>) result.get("chars");

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
        Random random = new Random();
        return random.ints(size, 0, 4)
            .mapToObj(type -> {
                switch (type) {
                    case 0: return random.nextInt(1000);
                    case 1: return String.valueOf((char) (random.nextInt(26) + 'A'));
                    case 2: return (char) (random.nextInt(10) + '0');
                    default: return (char) (random.nextInt(15) + 33);
                }
            })
            .toArray();
    }
}
