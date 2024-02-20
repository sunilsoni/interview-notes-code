package com.interview.notes.code.months.jan24.test14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeCheckupManager {
    private Map<Integer, List<Integer>> batchToEmployeesMap = new HashMap<>();
    private List<Integer> completedBatches = new ArrayList<>();

    // Method to create batches from the list of employee IDs
    public void createBatches(List<Integer> employeeIds) {
        int batchNumber = 1;
        for (int i = 0; i < employeeIds.size(); i += 10) {
            int end = Math.min(i + 10, employeeIds.size());
            List<Integer> batch = employeeIds.subList(i, end);
            batchToEmployeesMap.put(batchNumber++, new ArrayList<>(batch));
        }
    }

    // Mark a batch as completed
    public void completeBatch(int batchNumber) {
        completedBatches.add(batchNumber);
    }

    // Get details of a specific batch
    public Map<String, Object> getBatchDetails(int batchNumber) {
        Map<String, Object> details = new HashMap<>();
        details.put("BatchNumber", batchNumber);
        details.put("Employees", batchToEmployeesMap.get(batchNumber));
        details.put("Completed", completedBatches.contains(batchNumber));
        return details;
    }

    public static void main(String[] args) {
        EmployeeCheckupManager manager = new EmployeeCheckupManager();

        // Assuming employee IDs range from 1 to 30 for example
        List<Integer> employeeIds = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            employeeIds.add(i);
        }

        manager.createBatches(employeeIds);
        manager.completeBatch(1); // Mark the first batch as completed

        // Get details for batch 1
        System.out.println(manager.getBatchDetails(1));
    }
}
