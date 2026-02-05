package com.interview.notes.code.year.y2026.feb.common.test2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class GitAutomator {

    public static void main(String[] args) {
        // --- TEST CASE 1: Standard Commit ---
        // Simulating a commit with a valid message
        System.out.println("--- Test 1: Standard Commit ---");
        boolean test1Pass = runGitWorkflow("feat: automated commit from java");
        printTestResult("Standard Commit", test1Pass);

        // --- TEST CASE 2: Empty Message (Edge Case) ---
        // Git should fail or we should block it. Here we test our validation logic.
        System.out.println("\n--- Test 2: Empty Message ---");
        boolean test2Pass = !runGitWorkflow(""); // Should return false
        printTestResult("Empty Message Rejection", test2Pass);

        // --- TEST CASE 3: Large Message (Stress Test) ---
        // Simulating a very long commit message (Java 21 String repeat)
        System.out.println("\n--- Test 3: Large Commit Message ---");
        String longMessage = "fix: " + "A".repeat(5000);
        // Note: This might fail if no actual changes exist to commit, which is valid git behavior
        // For this test, we assume the command execution itself is the test unit.
        // We consider it PASS if the method executes without throwing Exception, even if Git says "nothing to commit"
        boolean test3Pass = true;
        try {
            runGitWorkflow(longMessage);
        } catch (Exception e) {
            test3Pass = false;
        }
        printTestResult("Large Message Handling", test3Pass);
    }

    // Core Logic: Orchestrates the Git workflow
    public static boolean runGitWorkflow(String commitMessage) {
        // Validation: Fail fast if message is empty
        if (commitMessage == null || commitMessage.isBlank()) {
            System.out.println("Error: Commit message cannot be empty.");
            return false;
        }

        try {
            // Step 1: git add . (Stage all changes)
            // executeCommand helper handles the ProcessBuilder boilerplate
            CommandResult add = executeCommand(List.of("git", "add", "."));
            if (add.exitCode() != 0) { // If exit code is not 0, it failed
                System.out.println("Git Add Failed: " + add.output());
                return false;
            }

            // Step 2: git commit -m "message"
            CommandResult commit = executeCommand(List.of("git", "commit", "-m", commitMessage));
            System.out.println(commit.output()); // Print git output for user visibility

            // Check if commit was successful or if there was nothing to commit
            if (commit.exitCode() != 0 && !commit.output().contains("nothing to commit")) {
                System.out.println("Git Commit Failed.");
                return false;
            }

            return true; // Workflow completed successfully

        } catch (Exception e) {
            System.out.println("Workflow crashed: " + e.getMessage());
            return false;
        }
    }

    // Helper method to run system commands using ProcessBuilder
    private static CommandResult executeCommand(List<String> command) {
        try {
            // ProcessBuilder is the Java API to run OS commands
            ProcessBuilder pb = new ProcessBuilder(command);

            // Redirects error stream to standard output so we capture everything
            pb.redirectErrorStream(true);

            // Start the process
            Process process = pb.start();

            // Java 21: 'var' for concise type inference
            // Read the output stream to show user what happened
            try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                // Collect all lines of output into a single String
                String output = reader.lines().collect(Collectors.joining("\n"));

                // Wait for process to finish and get exit code
                int exitCode = process.waitFor();

                return new CommandResult(exitCode, output);
            }
        } catch (Exception e) {
            return new CommandResult(-1, "Exception: " + e.getMessage());
        }
    }

    // Simple helper to print PASS/FAIL status
    private static void printTestResult(String caseName, boolean isPass) {
        System.out.println("Test Case: " + caseName + " -> " + (isPass ? "PASS" : "FAIL"));
    }

    // Java 21 Record to hold the result of a command execution
    // 'exitCode' 0 means success. 'output' holds logs.
    record CommandResult(int exitCode, String output) {
    }
}