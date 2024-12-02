package com.interview.notes.code.year.y2024.june24.test4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataReconciliation {
    public static void main(String[] args) {
        String csvFile = "system1_data.csv";
        String textFile = "system2_data.txt";

        Map<String, List<String>> system1Data = readCSV(csvFile);
        Map<String, List<String>> system2Data = readTextFile(textFile);

        // Perform reconciliation
        reconcileData(system1Data, system2Data);
    }

    // Method to read CSV file and store data in a Map
    private static Map<String, List<String>> readCSV(String csvFile) {
        Map<String, List<String>> dataMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean headerSkipped = false;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip header line
                }

                String[] parts = line.split(",");
                String name = parts[0].trim();
                String age = parts[1].trim();
                String email = parts[2].trim();

                List<String> rowData = new ArrayList<>();
                rowData.add(age);
                rowData.add(email);

                dataMap.put(name, rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataMap;
    }

    // Method to read space-delimited text file and store data in a Map
    private static Map<String, List<String>> readTextFile(String textFile) {
        Map<String, List<String>> dataMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                String name = parts[0].trim();
                String email = parts[1].trim();
                String age = parts[2].trim();

                List<String> rowData = new ArrayList<>();
                rowData.add(age);
                rowData.add(email);

                dataMap.put(name, rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataMap;
    }

    // Method to reconcile data between two systems
    private static void reconcileData(Map<String, List<String>> system1, Map<String, List<String>> system2) {
        Set<String> allNames = new HashSet<>(system1.keySet());
        allNames.addAll(system2.keySet());

        for (String name : allNames) {
            List<String> data1 = system1.get(name);
            List<String> data2 = system2.get(name);

            if (data1 != null && data2 != null) {
                if (!data1.equals(data2)) {
                    System.out.println("Mismatch for Employee: " + name);
                    System.out.println("System 1: " + data1);
                    System.out.println("System 2: " + data2);
                    System.out.println();
                }
            } else {
                System.out.println("Data missing for Employee: " + name);
                if (data1 == null) {
                    System.out.println("System 1: No data found");
                    System.out.println("System 2: " + data2);
                } else {
                    System.out.println("System 1: " + data1);
                    System.out.println("System 2: No data found");
                }
                System.out.println();
            }
        }
    }
}
