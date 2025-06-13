package com.interview.notes.code.year.y2025.may.amazon.test11;

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
            
        List<String> assignedRules = new ArrayList<>();
        StringBuilder currentContainer = new StringBuilder();
        
        for (String rule : sortedRules) {
            // Skip rules that are too long
            if (rule.length() > maxCharsPerContainer) {
                continue;
            }
            
            // Calculate space needed including comma if container not empty
            int neededSpace = rule.length() + (currentContainer.length() > 0 ? 1 : 0);
            
            // If rule doesn't fit in current container, start a new one
            if (currentContainer.length() + neededSpace > maxCharsPerContainer) {
                currentContainer = new StringBuilder();
                // If we've used all containers, stop
                if (assignedRules.size() >= containerCount) {
                    break;
                }
            }
            
            // Add comma if needed
            if (currentContainer.length() > 0) {
                currentContainer.append(",");
            }
            currentContainer.append(rule);
            assignedRules.add(rule);
        }
        
        return assignedRules;
    }

    // Test method remains the same
    public static void main(String[] args) {
        // Test case with descending order
        List<String> rules = Arrays.asList(
            "VeryLongRule",    // 11 chars
            "MediumRule",      // 9 chars
            "Short",           // 5 chars
            "Tiny"            // 4 chars
        );
        
        testCase("Sequential Container Fill Test", rules, 20, 2);
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
