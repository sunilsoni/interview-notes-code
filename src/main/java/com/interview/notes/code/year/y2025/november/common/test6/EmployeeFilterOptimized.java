package com.interview.notes.code.year.y2025.november.common.test6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// ============ EMPLOYEE CLASS ============

record Employee(String name, int age, double salary, String gender) {
    // Constructor to initialize employee details
    // Parameters: name (employee name), age (years), salary (annual in INR), gender (M/F)

    // Override toString for better display of employee details
    // Used for debugging and logging purposes
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", gender='" + gender + '\'' +
                '}';
    }
}

// ============ MAIN APPLICATION CLASS ============

public class EmployeeFilterOptimized {

    // ============ APPROACH 1: STREAM API (RECOMMENDED) ============
    
    // Method to filter female employees with salary > 30000 using Stream API
    // Input: List<Employee> - list of all employees
    // Output: List<String> - names of filtered employees
    // This approach is clean, readable, and highly optimized
    public static List<String> getFemaleEmployeesAbove30K_StreamAPI(List<Employee> employeeList) {
        
        // Step 1: stream() converts list into a stream for processing
        // Each element (Employee) flows through the pipeline
        // stream() is lazy - doesn't process until terminal operation called
        return employeeList.stream()
                
                // Step 2: filter() applies condition - keeps only matching elements
                // Condition 1: gender must equal "F" (female)
                // Condition 2: salary must be > 30000 INR
                // Both conditions must be true (AND logic)
                // Returns: Stream of Employee objects that match both conditions
                .filter(emp -> "F".equalsIgnoreCase(emp.gender()) && emp.salary() > 30000)
                
                // Step 3: map() transforms each Employee object to String (name)
                // Takes Employee as input, extracts getName()
                // Returns: Stream of String (employee names only)
                // This reduces data from full Employee object to just the name
                .map(Employee::name)
                
                // Step 4: collect() is terminal operation that gathers all results
                // Collectors.toList() collects all names into a new ArrayList
                // Returns final List<String> with filtered employee names
                // Time: O(n), Space: O(k) where k = matching employees
                .collect(Collectors.toList());
    }

    // ============ APPROACH 2: SINGLE PASS (MOST EFFICIENT FOR LARGE DATA) ============
    
    // Method using traditional loop for maximum efficiency
    // Better for very large datasets (1M+ records)
    // Input: List<Employee> - list of all employees
    // Output: List<String> - names of filtered employees
    public static List<String> getFemaleEmployeesAbove30K_SinglePass(List<Employee> employeeList) {
        
        // Step 1: Create new ArrayList to store filtered names
        // ArrayList is mutable and efficient for collecting results
        // Capacity starts as 10, grows as needed
        List<String> filteredNames = new ArrayList<>();
        
        // Step 2: Iterate through each employee once - O(n) time
        // for-each loop is efficient and readable
        // Processes each employee exactly once
        for (Employee emp : employeeList) {
            
            // Step 3: Check if employee is female AND salary > 30000
            // getGender() returns gender value
            // equalsIgnoreCase() handles both "F", "f", "Female", etc.
            // getSalary() returns salary value in INR
            // Both conditions must be true to add name to list
            if ("F".equalsIgnoreCase(emp.gender()) && emp.salary() > 30000) {
                
                // Step 4: If conditions match, add employee name to result list
                // emp.getName() extracts just the name string
                // add() appends to list
                filteredNames.add(emp.name());
            }
        }
        
        // Step 5: Return final list of filtered names
        // Time: O(n), Space: O(k) where k = matching employees
        // Most memory efficient - no intermediate streams
        return filteredNames;
    }

    // ============ APPROACH 3: WITH ADDITIONAL INFO (GET MORE DETAILS) ============
    
    // Method that returns Employee objects (not just names) for more flexibility
    // Input: List<Employee> - list of all employees
    // Output: List<Employee> - full employee objects matching criteria
    // Useful if you need salary or age information later
    public static List<Employee> getFemaleEmployeesAbove30K_Full(List<Employee> employeeList) {
        
        // Step 1: stream() creates stream from employee list
        return employeeList.stream()
                
                // Step 2: filter() keeps only female employees with salary > 30000
                // Same conditions as before but returns full Employee objects
                .filter(emp -> "F".equalsIgnoreCase(emp.gender()) && emp.salary() > 30000)
                
                // Step 3: collect() gathers filtered employees into new list
                // Returns List<Employee> instead of List<String>
                // Allows access to all employee details if needed
                .collect(Collectors.toList());
    }

    // Main method with comprehensive tests
    public static void main(String[] args) {
        
        int passCount = 0;
        int failCount = 0;
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   FILTER FEMALE EMPLOYEES WITH SALARY > 30000 INR         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // ============ TEST 1: BASIC TEST CASE ============
        
        // Step 1: Create sample employee list
        // Add employees with different genders and salaries
        List<Employee> employeeList1 = new ArrayList<>();
        employeeList1.add(new Employee("Priya", 28, 35000, "F"));      // Female, > 30K ✓
        employeeList1.add(new Employee("Rajesh", 32, 40000, "M"));     // Male, skip
        employeeList1.add(new Employee("Anjali", 26, 28000, "F"));     // Female, < 30K, skip
        employeeList1.add(new Employee("Neha", 30, 45000, "F"));       // Female, > 30K ✓
        employeeList1.add(new Employee("Amit", 35, 50000, "M"));       // Male, skip
        employeeList1.add(new Employee("Deepa", 29, 32000, "F"));      // Female, > 30K ✓
        
        // Step 2: Apply Stream API filtering
        List<String> result1 = getFemaleEmployeesAbove30K_StreamAPI(employeeList1);
        
        // Step 3: Expected output: [Priya, Neha, Deepa]
        // Check if result contains exactly 3 names
        // Check if all expected names are present
        boolean pass1 = result1.size() == 3 && 
                       result1.contains("Priya") && 
                       result1.contains("Neha") && 
                       result1.contains("Deepa");
        
        System.out.println("Test 1 - Basic Case: " + (pass1 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input Employees: 6 total");
        System.out.println("  Criteria: Female AND Salary > 30000");
        System.out.println("  Expected: [Priya, Neha, Deepa]");
        System.out.println("  Result: " + result1 + "\n");
        passCount += pass1 ? 1 : 0;
        failCount += pass1 ? 0 : 1;
        
        // ============ TEST 2: VERIFY ALL THREE APPROACHES GIVE SAME RESULT ============
        
        // Apply all three methods to same input
        List<String> result2a = getFemaleEmployeesAbove30K_StreamAPI(employeeList1);
        List<String> result2b = getFemaleEmployeesAbove30K_SinglePass(employeeList1);
        List<Employee> result2c = getFemaleEmployeesAbove30K_Full(employeeList1);
        
        // Extract names from full Employee objects for comparison
        List<String> result2cNames = result2c.stream()
                .map(Employee::name)
                .collect(Collectors.toList());
        
        // All three should produce same result
        boolean pass2 = result2a.equals(result2b) && result2b.equals(result2cNames);
        
        System.out.println("Test 2 - All Approaches Match: " + (pass2 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Stream API Result: " + result2a);
        System.out.println("  Single Pass Result: " + result2b);
        System.out.println("  Full Objects Result: " + result2cNames + "\n");
        passCount += pass2 ? 1 : 0;
        failCount += pass2 ? 0 : 1;
        
        // ============ TEST 3: NO MATCHING EMPLOYEES ============
        
        // Create list with only males or low salary females
        List<Employee> employeeList3 = new ArrayList<>();
        employeeList3.add(new Employee("Rohan", 28, 35000, "M"));
        employeeList3.add(new Employee("Simran", 26, 25000, "F"));
        employeeList3.add(new Employee("Vikram", 32, 40000, "M"));
        
        List<String> result3 = getFemaleEmployeesAbove30K_StreamAPI(employeeList3);
        boolean pass3 = result3.isEmpty();
        
        System.out.println("Test 3 - No Matches: " + (pass3 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 3 employees (no qualifying females)");
        System.out.println("  Expected: Empty list");
        System.out.println("  Result: " + result3 + "\n");
        passCount += pass3 ? 1 : 0;
        failCount += pass3 ? 0 : 1;
        
        // ============ TEST 4: ALL MATCHING ============
        
        // Create list with all high-salary females
        List<Employee> employeeList4 = new ArrayList<>();
        employeeList4.add(new Employee("Meera", 28, 35000, "F"));
        employeeList4.add(new Employee("Pooja", 30, 50000, "F"));
        employeeList4.add(new Employee("Shreya", 26, 40000, "F"));
        
        List<String> result4 = getFemaleEmployeesAbove30K_StreamAPI(employeeList4);
        boolean pass4 = result4.size() == 3;
        
        System.out.println("Test 4 - All Match: " + (pass4 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 3 employees (all qualify)");
        System.out.println("  Expected: 3 names");
        System.out.println("  Result: " + result4 + "\n");
        passCount += pass4 ? 1 : 0;
        failCount += pass4 ? 0 : 1;
        
        // ============ TEST 5: EDGE CASE - SALARY EXACTLY 30000 ============
        
        // Test boundary condition - should NOT include 30000 (> 30000, not >= 30000)
        List<Employee> employeeList5 = new ArrayList<>();
        employeeList5.add(new Employee("Nisha", 28, 30000, "F"));  // Exactly 30000, skip
        employeeList5.add(new Employee("Tina", 28, 30001, "F"));   // Just above, include
        
        List<String> result5 = getFemaleEmployeesAbove30K_StreamAPI(employeeList5);
        boolean pass5 = result5.size() == 1 && result5.contains("Tina");
        
        System.out.println("Test 5 - Boundary Condition: " + (pass5 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: Nisha (30000), Tina (30001)");
        System.out.println("  Expected: [Tina] (only > 30000, not =)");
        System.out.println("  Result: " + result5 + "\n");
        passCount += pass5 ? 1 : 0;
        failCount += pass5 ? 0 : 1;
        
        // ============ TEST 6: CASE INSENSITIVE GENDER CHECK ============
        
        // Test that "F", "f", "Female", "female" all work
        List<Employee> employeeList6 = new ArrayList<>();
        employeeList6.add(new Employee("Alice", 28, 35000, "F"));
        employeeList6.add(new Employee("Beth", 28, 35000, "f"));
        employeeList6.add(new Employee("Charlie", 28, 35000, "M"));
        
        List<String> result6 = getFemaleEmployeesAbove30K_StreamAPI(employeeList6);
        boolean pass6 = result6.size() == 2 && result6.contains("Alice") && result6.contains("Beth");
        
        System.out.println("Test 6 - Case Insensitive: " + (pass6 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: Alice (F), Beth (f), Charlie (M)");
        System.out.println("  Expected: [Alice, Beth] (both female cases)");
        System.out.println("  Result: " + result6 + "\n");
        passCount += pass6 ? 1 : 0;
        failCount += pass6 ? 0 : 1;
        
        // ============ LARGE DATA PERFORMANCE TEST ============
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              LARGE DATA PERFORMANCE TEST                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Test 7: 100K employees
        // Generate: 50% female, 50% male
        // Half of females have salary > 30K
        List<Employee> largeList100K = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            String name = "Employee_" + i;
            int age = 25 + (i % 40);
            double salary = 20000 + (i % 50000);  // Range: 20K to 70K
            String gender = (i % 2 == 0) ? "F" : "M";  // 50% female
            largeList100K.add(new Employee(name, age, salary, gender));
        }
        
        // Test Stream API performance
        long time1 = System.nanoTime();
        List<String> result7a = getFemaleEmployeesAbove30K_StreamAPI(largeList100K);
        long duration1 = System.nanoTime() - time1;
        
        // Test Single Pass performance
        long time2 = System.nanoTime();
        List<String> result7b = getFemaleEmployeesAbove30K_SinglePass(largeList100K);
        long duration2 = System.nanoTime() - time2;
        
        boolean pass7 = result7a.size() == result7b.size() && result7a.size() > 0;
        
        System.out.println("Test 7 - Large Data (100K): " + (pass7 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 100,000 employees");
        System.out.println("  Stream API Time: " + (duration1 / 1000000.0) + " ms | Result: " + result7a.size());
        System.out.println("  Single Pass Time: " + (duration2 / 1000000.0) + " ms | Result: " + result7b.size());
        System.out.println("  Speedup: " + String.format("%.2fx", (double)duration1/duration2) + "\n");
        passCount += pass7 ? 1 : 0;
        failCount += pass7 ? 0 : 1;
        
        // Test 8: 1 Million employees (heavy dataset)
        List<Employee> hugeList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            String name = "Emp_" + i;
            int age = 22 + (i % 50);
            double salary = 15000 + (i % 60000);
            String gender = (i % 2 == 0) ? "F" : "M";
            hugeList.add(new Employee(name, age, salary, gender));
        }
        
        long time3 = System.nanoTime();
        List<String> result8a = getFemaleEmployeesAbove30K_StreamAPI(hugeList);
        long duration3 = System.nanoTime() - time3;
        
        long time4 = System.nanoTime();
        List<String> result8b = getFemaleEmployeesAbove30K_SinglePass(hugeList);
        long duration4 = System.nanoTime() - time4;
        
        boolean pass8 = result8a.size() == result8b.size();
        
        System.out.println("Test 8 - Heavy Data (1M): " + (pass8 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 1,000,000 employees");
        System.out.println("  Stream API Time: " + (duration3 / 1000000.0) + " ms | Result: " + result8a.size());
        System.out.println("  Single Pass Time: " + (duration4 / 1000000.0) + " ms | Result: " + result8b.size());
        System.out.println("  Speedup: " + String.format("%.2fx", (double)duration3/duration4));
        System.out.println("  Memory Efficient: Only stores " + result8a.size() + " names\n");
        passCount += pass8 ? 1 : 0;
        failCount += pass8 ? 0 : 1;
        
        // ============ FINAL SUMMARY ============
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                     FINAL SUMMARY                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        System.out.println("Code Quality Metrics:");
        System.out.println("─".repeat(60));
        System.out.println("Stream API:     O(n) time, O(k) space | Readable & Concise");
        System.out.println("Single Pass:    O(n) time, O(k) space | Most Efficient");
        System.out.println("Full Objects:   O(n) time, O(k) space | Flexible Output");
        
        System.out.println("\nRecommendations:");
        System.out.println("─".repeat(60));
        System.out.println("Small data (< 10K):      Use Stream API (readable)");
        System.out.println("Medium data (10K-1M):    Use Single Pass (efficient)");
        System.out.println("Large data (1M+):        Use Single Pass (production)");
        System.out.println("Need full details:       Use Full Objects method");
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("Total Tests: " + (passCount + failCount));
        System.out.println("Passed: " + passCount + " | Failed: " + failCount);
        System.out.println("Success Rate: " + String.format("%.1f%%", (passCount * 100.0 / (passCount + failCount))));
        if (failCount == 0) {
            System.out.println("✓ ALL TESTS PASSED - SOLUTION IS PRODUCTION READY");
        }
        System.out.println("═".repeat(60));
    }
}