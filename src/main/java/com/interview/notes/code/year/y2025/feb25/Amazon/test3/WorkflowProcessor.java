package com.interview.notes.code.year.y2025.feb25.Amazon.test3;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WorkflowProcessor {
    public static void main(String[] args) {
        // List of workflow steps (each step is a Supplier returning a result message)
        List<Supplier<String>> steps = Arrays.asList(
            () -> processStep(1, true, "Data validation successful"),
            () -> processStep(2, false, "Missing required field"),
            () -> processStep(3, true, "Authentication passed"),
            () -> processStep(4, true, "Authorization check passed"),
            () -> processStep(5, false, "Database connection failed"),
            () -> processStep(6, true, "Processing completed"),
            () -> processStep(7, true, "Response sent")
        );

        // Execute all steps using Stream and collect results
        String resultSummary = IntStream.range(0, steps.size())
            .mapToObj(i -> steps.get(i).get()) // Execute each step
            .collect(Collectors.joining("\n")); // Join results

        // Print final response summary
        System.out.println("Workflow Execution Summary:\n" + resultSummary);
    }

    // Simulated step processor
    private static String processStep(int stepNumber, boolean isSuccess, String message) {
        return "Step " + stepNumber + (isSuccess ? " PASSED: " : " FAILED: ") + message;
    }
}
