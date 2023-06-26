package com.interview.notes.code.june23.test8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningByExample {
    public static void main(String[] args) {
        List<String> fileList = new ArrayList<>();
        // Assume you have a list of 25 file names
        for (int i = 1; i <= 25; i++) {
            fileList.add("File" + i);
        }

        int batchSize = 10;

        // Splitting the list into groups of batchSize using partitioningBy
        Map<Boolean, List<List<String>>> partitionedFiles = fileList.stream()
                .collect(Collectors.partitioningBy(file -> fileList.indexOf(file) / batchSize == 0))
                .values().stream()
                .collect(Collectors.groupingBy(files -> files.size() == batchSize));

        // Printing the grouped files
        partitionedFiles.forEach((isFullBatch, files) -> {
            if (isFullBatch) {
                System.out.println("Full Batch:");
            } else {
                System.out.println("Partial Batch:");
            }
            System.out.println(files);
            System.out.println("---------------------");
        });
    }
}
