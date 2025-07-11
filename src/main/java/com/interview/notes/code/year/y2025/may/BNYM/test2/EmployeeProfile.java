package com.interview.notes.code.year.y2025.may.BNYM.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This version’s main() method directly runs all built-in test cases
 * (including the provided samples) without expecting any user input.
 * <p>
 * To compile:
 * javac EmployeeProfile.java
 * <p>
 * To run:
 * java EmployeeProfile
 * <p>
 * The program will immediately execute PASS/FAIL tests and exit.
 */

///////////////////////
// 1. Abstract class //
///////////////////////
abstract class Employee {
    public abstract int getSalary();

    public abstract void setSalary(int salary);

    public abstract String getGrade();

    public abstract void setGrade(String grade);

    // Prints the label for an employee
    public void label() {
        System.out.println("Employee's data:");
    }
}

/// ///////////////////
// 2. Engineer class //

/// ///////////////////
class Engineer extends Employee {
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
     * Helper method: Parses the list of input lines and returns output lines.
     * - lines.get(0) should be an integer n
     * - next n lines are of the form: "TYPE GRADE SALARY"
     */
    public static List<String> processLines(List<String> lines) {
        if (lines.isEmpty()) {
            return Collections.emptyList();
        }

        int n;
        try {
            n = Integer.parseInt(lines.get(0).trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("First line must be an integer (number of employees).");
        }

        if (n <= 0) {
            return Collections.emptyList();
        }

        if (lines.size() < 1 + n) {
            throw new IllegalArgumentException(
                    "Expected " + n + " employee lines but got only " + (lines.size() - 1) + "."
            );
        }

        List<String> output = new ArrayList<>(n * 3);

        // Use Java 8 stream to process exactly n lines after the first
        lines.stream()
                .skip(1)
                .limit(n)
                .forEach(line -> {
                    String trimmed = line.trim();
                    if (trimmed.isEmpty()) {
                        throw new IllegalArgumentException("Employee line cannot be empty.");
                    }

                    String[] parts = trimmed.split("\\s+");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException(
                                "Each employee line must have 3 tokens: TYPE GRADE SALARY. Got: \"" + line + "\""
                        );
                    }

                    String type = parts[0];   // "ENGINEER" or "MANAGER"
                    String gradeInput = parts[1];   // e.g. "A"
                    String salaryStr = parts[2];   // e.g. "50000"

                    int salaryValue;
                    try {
                        salaryValue = Integer.parseInt(salaryStr);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Invalid salary: \"" + salaryStr + "\"");
                    }

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

                    emp.setGrade(gradeInput);
                    emp.setSalary(salaryValue);

                    output.add("Employee's data:");
                    output.add("GRADE : " + emp.getGrade());
                    output.add("SALARY : " + emp.getSalary());
                });

        return output;
    }

    /**
     * main() directly runs all built-in test cases (PASS/FAIL).
     * No user input is expected.
     */
    public static void main(String[] args) {
        runAllTests();
    }

    ////////////////////////////
    // 5. Testing harness     //

    /// /////////////////////////
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

        // ---- Additional Custom Test: n = 0 ----
        // If zero employees, output should be empty
        String zeroInputStr = "0\n";
        List<String> zeroInputLines = Collections.singletonList(zeroInputStr.trim());
        List<String> zeroResult = processLines(zeroInputLines);
        boolean passZero = zeroResult.isEmpty();
        System.out.println("Custom Case (n=0): " + (passZero ? "PASS" : "FAIL"));
        if (!passZero) {
            System.out.println("  Expected: <empty>");
            System.out.println("  Actual:");
            zeroResult.forEach(s -> System.out.println("    > " + s));
        }
        System.out.println();

        // ---- Additional Custom Test: All ENGINEER lines ----
        String multiEngineerStr =
                "4\n" +
                        "ENGINEER C 30000\n" +
                        "ENGINEER C 40000\n" +
                        "ENGINEER D 50000\n" +
                        "ENGINEER E 60000\n";
        List<String> multiEngineerLines = Arrays.stream(multiEngineerStr.split("\\R"))
                .collect(Collectors.toList());
        List<String> multiEngineerResult = processLines(multiEngineerLines);
        List<String> multiEngineerExpected = Arrays.asList(
                "Employee's data:",
                "GRADE : C",
                "SALARY : 30000",
                "Employee's data:",
                "GRADE : C",
                "SALARY : 40000",
                "Employee's data:",
                "GRADE : D",
                "SALARY : 50000",
                "Employee's data:",
                "GRADE : E",
                "SALARY : 60000"
        );
        boolean passMultiEng = multiEngineerResult.equals(multiEngineerExpected);
        System.out.println("Custom Case (4 ENGINEERS): " + (passMultiEng ? "PASS" : "FAIL"));
        if (!passMultiEng) {
            System.out.println("  Expected:");
            multiEngineerExpected.forEach(s -> System.out.println("    > " + s));
            System.out.println("  Actual:");
            multiEngineerResult.forEach(s -> System.out.println("    > " + s));
        }
        System.out.println();

        // ---- Large-Data Test ----
        final int LARGE_N = 100_000;
        List<String> largeInputLines = new ArrayList<>(1 + LARGE_N);
        largeInputLines.add(String.valueOf(LARGE_N));
        for (int i = 0; i < LARGE_N; i++) {
            largeInputLines.add("ENGINEER Z 100000");
        }

        long startTime = System.currentTimeMillis();
        List<String> largeResult = processLines(largeInputLines);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime; // in milliseconds

        boolean passLarge = (largeResult.size() == LARGE_N * 3);
        System.out.println("Large Data Test (n = " + LARGE_N + "): " + (passLarge ? "PASS" : "FAIL"));
        System.out.println("  Expected output lines: " + (LARGE_N * 3));
        System.out.println("  Actual   output lines: " + largeResult.size());
        System.out.println("  Time taken (ms): " + elapsed);
        System.out.println();

        System.out.println("===== Tests Finished =====");
    }
}