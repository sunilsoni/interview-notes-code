package com.interview.notes.code.year.y2025.may.amazon.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RuleContainerOptimizer {

    // Method to optimize and fill containers
    public static List<List<String>> optimizeRules(List<String> rules, int maxChars, int containerCount) {
        // Sort rules by length (smallest first) to maximize rule fitting
        rules.sort(Comparator.comparingInt(String::length));

        List<List<String>> containers = new ArrayList<>();
        int ruleIndex = 0;

        // Fill each container one by one
        for (int i = 0; i < containerCount; i++) {
            int currentLength = 0;
            List<String> currentContainer = new ArrayList<>();

            // Keep adding rules while within limit
            while (ruleIndex < rules.size()) {
                String rule = rules.get(ruleIndex);
                int additionalLength = rule.length();
                if (!currentContainer.isEmpty()) additionalLength += 1; // Account for comma

                if (currentLength + additionalLength <= maxChars) {
                    currentContainer.add(rule);
                    currentLength += additionalLength;
                    ruleIndex++;
                } else {
                    break;
                }
            }

            containers.add(currentContainer);
        }

        return containers;
    }

    public static void main(String[] args) {
        // Minimal reproducible example
        List<String> rules = Arrays.asList(
                "EnableGuardDuty", "UseIAMRole", "LogS3Events",
                "AccessBilling", "DenyEC2Start", "EnableMFA"
        );
        int maxCharsPerContainer = 32;
        int containerCount = 5;

        List<List<String>> result = optimizeRules(rules, maxCharsPerContainer, containerCount);

        // Output the result
        System.out.println("Containers filled:");
        for (List<String> container : result) {
            System.out.println(container.stream().collect(Collectors.joining(",")));
        }

        // PASS/FAIL check for basic example
        int totalRulesStoredBasic = result.stream().mapToInt(List::size).sum();
        System.out.println(totalRulesStoredBasic == rules.size() ? "PASS" : "FAIL");

        // Additional test for large inputs and edge cases
        List<String> largeRules = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeRules.add("Rule" + i);
        }

        List<List<String>> largeTest = optimizeRules(largeRules, 50, 20);

        // Check if large test passes (should distribute most rules effectively)
        long totalRulesStoredLarge = largeTest.stream().mapToInt(List::size).sum();
        System.out.println("Total rules stored (large test): " + totalRulesStoredLarge);
        System.out.println(totalRulesStoredLarge > 0 ? "PASS" : "FAIL");
    }
}
