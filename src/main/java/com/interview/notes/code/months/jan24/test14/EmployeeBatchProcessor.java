package com.interview.notes.code.months.jan24.test14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeBatchProcessor {
    
    public static class BatchDetails {
        private String batchName;
        private List<String> processedEmployees;

        public BatchDetails(String batchName, List<String> processedEmployees) {
            this.batchName = batchName;
            this.processedEmployees = processedEmployees;
        }

        public String getBatchName() {
            return batchName;
        }

        public List<String> getProcessedEmployees() {
            return processedEmployees;
        }
    }
    
    public static List<BatchDetails> processEmployees(List<String> employees) {
        List<BatchDetails> batches = new ArrayList<>();
        int batchSize = 10;
        int batchCount = (int) Math.ceil((double) employees.size() / batchSize);

        for (int i = 0; i < batchCount; i++) {
            int startIdx = i * batchSize;
            int endIdx = Math.min((i + 1) * batchSize, employees.size());
            List<String> batchEmployees = employees.subList(startIdx, endIdx);

            // Process batch here (e.g., medical checkup)
            BatchDetails batchDetails = new BatchDetails("Batch " + (i + 1), batchEmployees);
            batches.add(batchDetails);
        }

        return batches;
    }

    public static void main(String[] args) {
        List<String> employees = new ArrayList<>();
        // Populate employees list with employee names

        // Example employees list
        for (int i = 1; i <= 30; i++) {
            employees.add("Employee" + i);
        }

        List<BatchDetails> processedBatches = processEmployees(employees);
        
        // Print details of processed batches
        for (BatchDetails batch : processedBatches) {
            System.out.println("Batch Name: " + batch.getBatchName());
            System.out.println("Processed Employees: " + batch.getProcessedEmployees());
            System.out.println();
        }
    }
}
