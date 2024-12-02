package com.interview.notes.code.year.y2024.nov24.test10;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class AdvancedParenthesesValidator {

    // Map to store bracket pairs
    private static final Map<Character, Character> BRACKET_PAIRS = new HashMap<>();
    // Validation rules as predicates
    private static final Map<String, Predicate<String>> VALIDATION_RULES = new HashMap<>();

    static {
        BRACKET_PAIRS.put('(', ')');
        BRACKET_PAIRS.put('{', '}');
        BRACKET_PAIRS.put('[', ']');
        // Can easily add more bracket pairs here
        // BRACKET_PAIRS.put('<', '>');
    }

    static {
        // Rule: No three consecutive same brackets
        VALIDATION_RULES.put("NO_TRIPLE_BRACKETS",
                s -> !Pattern.compile("(\\(\\(\\()|(\\)\\)\\))|(\\[\\[\\[)|(\\]\\]\\])|" +
                        "(\\{\\{\\{)|(\\}\\}\\})").matcher(s).find());

        // Rule: Must start with opening bracket if not empty
        VALIDATION_RULES.put("VALID_START",
                s -> s.isEmpty() || BRACKET_PAIRS.containsKey(s.charAt(0)));

        // Rule: Equal number of opening and closing brackets
        VALIDATION_RULES.put("BALANCED_COUNT",
                s -> s.chars().filter(ch -> BRACKET_PAIRS.containsKey((char) ch)).count() ==
                        s.chars().filter(ch -> BRACKET_PAIRS.containsValue((char) ch)).count());

        // Rule: Maximum nesting depth
        VALIDATION_RULES.put("MAX_DEPTH",
                s -> getMaxDepth(s) <= 100); // configurable depth limit
    }

    private static int getMaxDepth(String s) {
        int depth = 0;
        int maxDepth = 0;
        for (char c : s.toCharArray()) {
            if (BRACKET_PAIRS.containsKey(c)) {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            } else if (BRACKET_PAIRS.containsValue(c)) {
                depth--;
            }
        }
        return maxDepth;
    }

    public static boolean isValid(String s) {
        if (s == null) return false;
        s = s.trim();
        if (s.isEmpty()) return true;

        // Apply all validation rules
        for (Map.Entry<String, Predicate<String>> rule : VALIDATION_RULES.entrySet()) {
            if (!rule.getValue().test(s)) {
                System.out.println("Failed rule: " + rule.getKey());
                return false;
            }
        }

        // Main bracket matching logic using stack
        Stack<Character> stack = new Stack<>();

        for (char currentChar : s.toCharArray()) {
            if (BRACKET_PAIRS.containsKey(currentChar)) {
                stack.push(currentChar);
            } else if (BRACKET_PAIRS.containsValue(currentChar)) {
                if (stack.isEmpty()) return false;

                char lastOpening = stack.pop();
                if (BRACKET_PAIRS.get(lastOpening) != currentChar) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // Enhanced test cases
        TestCase[] testCases = {
                new TestCase("()", true, "Simple valid case"),
                new TestCase("()[]{}", true, "Multiple valid pairs"),
                new TestCase("(((", false, "Triple brackets - invalid"),
                new TestCase("({[]})", true, "Nested brackets"),
                new TestCase(")]", false, "Invalid start"),
                new TestCase("((()))", true, "Multiple nested"),
                new TestCase("((())", false, "Unbalanced"),
                new TestCase("([]){}", true, "Mixed valid"),
                new TestCase("({[)}]", false, "Cross matching"),
                // Complex test cases
                new TestCase("(" + "[]".repeat(50) + ")", true, "Large nested"),
                new TestCase("(((" + "[]".repeat(10) + ")))", false, "Triple nested start"),
                new TestCase(null, false, "Null input"),
                new TestCase("   ", true, "Whitespace only")
        };

        runTests(testCases);
    }

    private static void runTests(TestCase[] testCases) {
        int passed = 0;
        System.out.println("Running Tests...\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase test = testCases[i];
            long startTime = System.nanoTime();
            boolean result = isValid(test.input);
            long endTime = System.nanoTime();
            boolean isPassed = result == test.expected;

            System.out.printf("Test %d: %s\n", i + 1, test.description);
            System.out.printf("Input: %s\n",
                    test.input == null ? "null" :
                            test.input.length() > 50 ? test.input.substring(0, 47) + "..." :
                                    test.input);
            System.out.printf("Expected: %b, Got: %b\n", test.expected, result);
            System.out.printf("Time: %.3f ms\n", (endTime - startTime) / 1_000_000.0);
            System.out.printf("Status: %s\n\n", isPassed ? "✓ PASSED" : "✗ FAILED");

            if (isPassed) passed++;
        }

        System.out.printf("Final Results: %d/%d tests passed (%.2f%%)\n",
                passed, testCases.length, (passed * 100.0 / testCases.length));
    }

    static class TestCase {
        String input;
        boolean expected;
        String description;

        TestCase(String input, boolean expected, String description) {
            this.input = input;
            this.expected = expected;
            this.description = description;
        }
    }
}