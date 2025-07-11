package com.interview.notes.code.year.y2025.may.BNYM.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. Abstract class Employee:
 * - abstract void setSalary(int salary)
 * - abstract int getSalary()
 * - abstract void setGrade(String grade)
 * - abstract String getGrade()
 * - concrete void label(): prints "Employee's data:"
 * <p>
 * 2. Engineer extends Employee
 * - private int salary
 * - private String grade
 * - implements all abstract methods
 * <p>
 * 3. Manager extends Employee
 * - private int salary
 * - private String grade
 * - implements all abstract methods
 * <p>
 * 4. EmployeeProfile:
 * - static helper processLines(...) that takes List<String> input-lines
 * (first element = "n", next n lines = "TYPE GRADE SALARY"), and returns
 * a List<String> of output lines in the specified format.
 * - main(...) method:
 * - If run with "--test", execute internal tests (sample cases + large-data).
 * - Otherwise, read from System.in and print processed output to System.out.
 */

///////////////////////
// 1. Abstract class //
///////////////////////
abstract class Employee {
    // Get the salary
    public abstract int getSalary();

    // Set the salary
    public abstract void setSalary(int salary);

    // Get the grade
    public abstract String getGrade();

    // Set the grade
    public abstract void setGrade(String grade);

    // Concrete helper: prints the label line
    public void label() {
        System.out.println("Employee's data:");
    }
}

/// ///////////////////
// 2. Engineer class //

/// ///////////////////
class Engineer extends Employee {
    // PER-EMPLOYEE fields
    private int salary;
    private String grade;

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String getGrade() {
        return this.grade;
    }

    @Override
    public void setGrade(String grade) {
        this.grade = grade;
    }
}

/// ///////////////////
// 3. Manager class //

/// ///////////////////
class Manager extends Employee {
    // PER-EMPLOYEE fields
    private int salary;
    private String grade;

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String getGrade() {
        return this.grade;
    }

    @Override
    public void setGrade(String grade) {
        this.grade = grade;
    }
}

/// ////////////////////////
// 4. EmployeeProfile    //

/// ////////////////////////
public class EmployeeProfile {

    /**
     * Helper method to process input lines and return the output lines.
     *
     * @param lines A list of strings, where:
     *              - lines.get(0) is "n" (number of employees).
     *              - next n lines: "TYPE GRADE SALARY"
     * @return A List<String> containing the printed output (3 lines per employee).
     */
    public static List<String> processLines(List<String> lines) {
        // 1. Parse number of employees
        if (lines.isEmpty()) {
            // No input => nothing to process
            return Collections.emptyList();
        }

        int n;
        try {
            n = Integer.parseInt(lines.get(0).trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("First line must be an integer (number of employees).");
        }

        // 2. If n = 0, return empty
        if (n <= 0) {
            return Collections.emptyList();
        }

        // 3. We expect exactly n more lines
        if (lines.size() < 1 + n) {
            throw new IllegalArgumentException(
                    "Expected " + n + " employee lines, but got only " + (lines.size() - 1) + ".");
        }

        // 4. Prepare a list to hold all output lines
        List<String> output = new ArrayList<>(n * 3);

        // 5. Stream over the next n lines (Java 8 style),
        //    skip the first element (index 0) which was 'n'.
        lines.stream()
                .skip(1)                 // skip the line with 'n'
                .limit(n)                // only process exactly n lines
                .forEach(line -> {
                    // Each line should be: "TYPE GRADE SALARY"
                    String trimmed = line.trim();
                    if (trimmed.isEmpty()) {
                        throw new IllegalArgumentException("Employee line cannot be empty.");
                    }

                    // Split on whitespace
                    String[] parts = trimmed.split("\\s+");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException(
                                "Each employee line must have 3 tokens: TYPE GRADE SALARY. Got: \"" + line + "\""
                        );
                    }

                    String type = parts[0];          // "ENGINEER" or "MANAGER"
                    String gradeInput = parts[1];          // e.g. "A" or "B"
                    String salaryStr = parts[2];          // e.g. "50000"

                    // Parse salary
                    int salaryValue;
                    try {
                        salaryValue = Integer.parseInt(salaryStr);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Invalid salary: \"" + salaryStr + "\"");
                    }

                    // Instantiate correct subclass
                    Employee emp;
                    switch (type) {
                        case "ENGINEER":
                            emp = new Engineer();
                            break;
                        case "MANAGER":
                            emp = new Manager();
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown employee type: \"" + type + "\"");
                    }

                    // Set grade and salary
                    emp.setGrade(gradeInput);
                    emp.setSalary(salaryValue);

                    // Build output lines for this employee
                    // 1) label
                    // 2) GRADE : <grade>
                    // 3) SALARY : <salary>
                    output.add("Employee's data:");               // same as emp.label(), but add to list
                    output.add("GRADE : " + emp.getGrade());
                    output.add("SALARY : " + emp.getSalary());
                });

        return output;
    }

    /**
     * Main method:
     * - If run with "--test" as the first argument, perform internal test cases (PASS/FAIL).
     * - Otherwise, read from System.in, process input, and print to System.out.
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("--test")) {
            runAllTests();
            return;
        }

        // ----- Normal I/O mode -----
        // Read all lines from System.in
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> inputLines = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            inputLines.add(line);
        }

        // Process them
        List<String> result = processLines(inputLines);

        // Print to System.out
        result.forEach(System.out::println);
    }

    ////////////////////////////
    // 5. Testing harness     //
    ////////////////////////////

    /**
     * Utility to run sample & large-data tests and print PASS/FAIL.
     * We define:
     * - sampleCase0Input, sampleCase0Expected
     * - sampleCase1Input, sampleCase1Expected
     * - largeDataTest
     */
    private static void runAllTests() {
        System.out.println("===== Running Built-In Tests =====\n");

        // ---- Sample Case 0 ----
        String sampleCase0InputStr =
                "2\n" +
                        "ENGINEER B 50000\n" +
                        "MANAGER A 70000\n";
        List<String> sampleCase0InputLines = Arrays.stream(sampleCase0InputStr.split("\\R"))
                .collect(Collectors.toList());
        List<String> sampleCase0Result = processLines(sampleCase0InputLines);

        List<String> sampleCase0Expected = Arrays.asList(
                "Employee's data:",
                "GRADE : B",
                "SALARY : 50000",
                "Employee's data:",
                "GRADE : A",
                "SALARY : 70000"
        );

        boolean pass0 = sampleCase0Result.equals(sampleCase0Expected);
        System.out.println("Sample Case 0: " + (pass0 ? "PASS" : "FAIL"));
        if (!pass0) {
            System.out.println("  Expected:");
            sampleCase0Expected.forEach(s -> System.out.println("    > " + s));
            System.out.println("  Actual:");
            sampleCase0Result.forEach(s -> System.out.println("    > " + s));
        }
        System.out.println();

        // ---- Sample Case 1 ----
        String sampleCase1InputStr =
                "3\n" +
                        "ENGINEER B 50000\n" +
                        "MANAGER A 70000\n" +
                        "MANAGER A 90000\n";
        List<String> sampleCase1InputLines = Arrays.stream(sampleCase1InputStr.split("\\R"))
                .collect(Collectors.toList());
        List<String> sampleCase1Result = processLines(sampleCase1InputLines);

        List<String> sampleCase1Expected = Arrays.asList(
                "Employee's data:",
                "GRADE : B",
                "SALARY : 50000",
                "Employee's data:",
                "GRADE : A",
                "SALARY : 70000",
                "Employee's data:",
                "GRADE : A",
                "SALARY : 90000"
        );

        boolean pass1 = sampleCase1Result.equals(sampleCase1Expected);
        System.out.println("Sample Case 1: " + (pass1 ? "PASS" : "FAIL"));
        if (!pass1) {
            System.out.println("  Expected:");
            sampleCase1Expected.forEach(s -> System.out.println("    > " + s));
            System.out.println("  Actual:");
            sampleCase1Result.forEach(s -> System.out.println("    > " + s));
        }
        System.out.println();

        // ---- Large-Data Test ----
        // We generate n = 100_000 employees: all ENGINEER Z 100000
        // We expect the output list size to be 3 * n.
        final int LARGE_N = 100_000;
        List<String> largeInputLines = new ArrayList<>(1 + LARGE_N);
        largeInputLines.add(String.valueOf(LARGE_N));
        for (int i = 0; i < LARGE_N; i++) {
            // Every line: "ENGINEER Z 100000"
            largeInputLines.add("ENGINEER Z 100000");
        }

        long startTime = System.currentTimeMillis();
        List<String> largeResult = processLines(largeInputLines);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime; // in ms

        boolean passLarge = (largeResult.size() == LARGE_N * 3);
        System.out.println("Large Data Test (n = " + LARGE_N + "): " + (passLarge ? "PASS" : "FAIL"));
        System.out.println("  Expected output lines: " + (LARGE_N * 3));
        System.out.println("  Actual   output lines: " + largeResult.size());
        System.out.println("  Time taken (ms): " + elapsed);
        System.out.println();

        System.out.println("===== Tests Finished =====");
    }
}