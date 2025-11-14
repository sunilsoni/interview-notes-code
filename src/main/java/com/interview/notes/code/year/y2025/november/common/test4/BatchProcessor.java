package com.interview.notes.code.year.y2025.november.common.test4;

import java.util.ArrayList;
import java.util.List;

public class BatchProcessor {

    public static void main(String[] args) {
        // Sample list of 10 documents
        List<String> documents = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            documents.add("Document_" + i);
        }

        int batchSize = 5;
        int total = documents.size();

        for (int i = 0; i < total; i += batchSize) {
            // Get current batch
            int end = Math.min(i + batchSize, total);
            List<String> batch = documents.subList(i, end);

            // Process batch
            System.out.println("Processing batch: " + batch);
            for (String doc : batch) {
                processDocument(doc);
                deleteDocument(doc);
            }

            System.out.println("Batch completed.\n");
        }
    }

    private static void processDocument(String doc) {
        System.out.println("Processed: " + doc);
    }

    private static void deleteDocument(String doc) {
        System.out.println("Deleted: " + doc);
    }
}
