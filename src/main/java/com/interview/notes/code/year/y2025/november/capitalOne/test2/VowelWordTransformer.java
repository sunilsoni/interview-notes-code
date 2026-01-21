package com.interview.notes.code.year.y2025.november.capitalOne.test2;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.IntStream;

class VowelWordTransformer {

    public static void main(String[] args) {
        var transformer = new VowelWordTransformer();

        record TestCase(String[] text, String[] expected) {
        }

        var testCases = new TestCase[]{
                new TestCase(
                        new String[]{"apple", "banana", "OranGe"},
                        new String[]{"alppe", "banana", "OGnare"}
                ),
                new TestCase(
                        new String[]{"AE", "CodeSignal"},
                        new String[]{"AE", "CodeSignal"}
                ),
                new TestCase(
                        new String[]{"a", "e", "i", "o", "u"},
                        new String[]{"a", "e", "i", "o", "u"}
                ),
                new TestCase(
                        new String[]{"hello", "world"},
                        new String[]{"hello", "world"}
                ),
                new TestCase(
                        new String[]{"AbcdE", "ORANGE", "umbrella"},
                        new String[]{"AdcbE", "OGNARE", "ullerbma"}
                ),
                new TestCase(
                        new String[]{""},
                        new String[]{""}
                ),
                new TestCase(
                        new String[]{"aa", "ee", "ii"},
                        new String[]{"aa", "ee", "ii"}
                ),
                new TestCase(
                        new String[]{"aXa", "eYe", "iZi"},
                        new String[]{"aXa", "eYe", "iZi"}
                ),
                new TestCase(
                        new String[]{"abcdefghijklmnopqrstuvwxyza"},
                        new String[]{"azyxwvutsrqponmlkjihgfedcba"}
                ),
                new TestCase(
                        generateLargeTestCase(100, "apple"),
                        generateLargeTestCase(100, "alppe")
                ),
                new TestCase(
                        generateLargeTestCase(1000, "ORANGE"),
                        generateLargeTestCase(1000, "OGNARE")
                )
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            var tc = testCases[i];
            String[] result = transformer.solution(tc.text);
            boolean isPass = Arrays.equals(result, tc.expected);

            System.out.printf("Test %2d: %s%n", i + 1, isPass ? "PASS" : "FAIL");

            if (isPass) {
                passed++;
            } else {
                failed++;
                if (tc.text.length <= 5) {
                    System.out.printf("  Expected: %s%n  Got: %s%n",
                            Arrays.toString(tc.expected),
                            Arrays.toString(result));
                }
            }
        }

        System.out.printf("%nResults: %d PASS, %d FAIL%n", passed, failed);
    }

    static String[] generateLargeTestCase(int size, String word) {
        return IntStream.range(0, size)
                .mapToObj(i -> word)
                .toArray(String[]::new);
    }

    String[] solution(String[] text) {
        var vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        return Arrays.stream(text)
                .map(word -> {
                    if (word.length() <= 2 ||
                            !vowels.contains(word.charAt(0)) ||
                            !vowels.contains(word.charAt(word.length() - 1))) {
                        return word;
                    }

                    return word.charAt(0) +
                            new StringBuilder(word.substring(1, word.length() - 1))
                                    .reverse()
                                    .toString() +
                            word.charAt(word.length() - 1);
                })
                .toArray(String[]::new);
    }
}