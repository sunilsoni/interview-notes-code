package com.interview.notes.code.year.y2023.dec23.test6;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelApiCalls {

    private static final int MAX_THREADS = 4;

    public static void main(String[] args) {
        List<String> employeeIds = List.of("123", "456", "789", "101", "112"); // Example list

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        List<Future<EmployeeDetails>> futures = new ArrayList<>();

        for (String employeeId : employeeIds) {
            Future<EmployeeDetails> future = executorService.submit(() -> fetchEmployeeDetails(employeeId));
            futures.add(future);
        }

        // Collect results as they become available
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        for (Future<EmployeeDetails> future : futures) {
            try {
                employeeDetails.add(future.get()); // Handle potential exceptions here
            } catch (Exception e) {
                // Handle exceptions gracefully, e.g., log errors or retry
            }
        }

        executorService.shutdown(); // Shutdown the executor after all tasks are completed

        // Process the collected employee details
        System.out.println("Employee details:");
        for (EmployeeDetails details : employeeDetails) {
            System.out.println(details); // Assuming EmployeeDetails has a meaningful toString() method
        }
    }

    private static EmployeeDetails fetchEmployeeDetails(String employeeId) {
        // Replace with your actual API call logic to fetch employee details
        try {
            Thread.sleep(1000); // Simulate API call delay
        } catch (InterruptedException e) {
            // Handle interruption if needed
        }
        return new EmployeeDetails(employeeId, "John Doe", "Software Engineer"); // Example data
    }
}

// Assuming a simple EmployeeDetails class
@Data
@AllArgsConstructor
class EmployeeDetails {
    private String employeeId;
    private String name;
    private String designation;

    // Constructor and getters...
}
