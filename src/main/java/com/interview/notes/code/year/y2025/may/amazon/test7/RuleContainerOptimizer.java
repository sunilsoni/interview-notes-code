package com.interview.notes.code.year.y2025.may.amazon.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RuleContainerOptimizer {

    public static List<String> optimizeRuleDistribution(List<String> rules,
                                                        int maxCharsPerContainer,
                                                        int containerCount) {
        // Input validation
        if (rules == null || rules.isEmpty() || containerCount <= 0 || maxCharsPerContainer <= 0) {
            return new ArrayList<>();
        }

        // Sort rules by length (descending)
        List<String> sortedRules = rules.stream()
                .sorted((a, b) -> b.length() - a.length())
                .collect(Collectors.toList());

        // Initialize containers
        List<StringBuilder> containers = new ArrayList<>();
        for (int i = 0; i < containerCount; i++) {
            containers.add(new StringBuilder());
        }

        List<String> assignedRules = new ArrayList<>();

        for (String rule : sortedRules) {
            // Skip rules that are too long
            if (rule.length() > maxCharsPerContainer) {
                continue;
            }

            // Find best container
            int bestContainer = findBestContainer(containers, rule, maxCharsPerContainer);

            if (bestContainer >= 0) {
                StringBuilder container = containers.get(bestContainer);
                // Add comma if needed
                if (container.length() > 0) {
                    container.append(",");
                }
                container.append(rule);
                assignedRules.add(rule);
            }
        }

        return assignedRules;
    }

    private static int findBestContainer(List<StringBuilder> containers,
                                         String rule,
                                         int maxChars) {
        int bestFit = -1;
        int smallestSpace = Integer.MAX_VALUE;

        for (int i = 0; i < containers.size(); i++) {
            StringBuilder container = containers.get(i);
            // Calculate space needed including comma if container not empty
            int neededSpace = rule.length() + (container.length() > 0 ? 1 : 0);
            int availableSpace = maxChars - container.length();

            // Check if rule fits and this container has less remaining space
            if (neededSpace <= availableSpace && availableSpace < smallestSpace) {
                bestFit = i;
                smallestSpace = availableSpace;
            }
        }

        return bestFit;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic scenario
        List<String> rules1 = Arrays.asList(
                "EnableGuardDuty", "UseIAMRole", "LogS3Events",
                "AccessBilling", "DenyEC2Start", "EnableMFA"
        );
        testCase("Basic Test", rules1, 32, 5);

        // Test Case 2: Empty rules
        testCase("Empty Rules Test", new ArrayList<>(), 32, 5);

        // Test Case 3: Large rules
        List<String> rules3 = Arrays.asList(
                "VeryLongRuleNameThatExceedsLimit",
                "ShortRule",
                "MediumSizeRule"
        );
        testCase("Large Rules Test", rules3, 20, 3);

        // Test Case 4: Exact fit
        List<String> rules4 = Arrays.asList("Rule1", "Rule2", "Rule3");
        testCase("Exact Fit Test", rules4, 15, 2);
    }

    private static void testCase(String testName,
                                 List<String> rules,
                                 int maxChars,
                                 int containerCount) {
        System.out.println("\nRunning: " + testName);
        System.out.println("Input Rules: " + rules);
        System.out.println("Max chars per container: " + maxChars);
        System.out.println("Number of containers: " + containerCount);

        List<String> result = optimizeRuleDistribution(rules, maxChars, containerCount);

        System.out.println("Assigned Rules: " + result);
        System.out.println("Rules Assigned: " + result.size() + "/" + rules.size());
    }
}
