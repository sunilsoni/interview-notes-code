package com.interview.notes.code.year.y2024.may24.test3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * I have the CSV file below and need to get the monthly sales. Create a method called MonthlySales to provide the name of the month and the corresponding sales amounts.
 * File Name: Sales.csv
 * Fields (Item_no,Name, date, unit price, qty)
 * Results:
 * January: 100,000
 * February :120000
 */
public class SalesAnalyzer {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Map<String, Double> monthlySales(String csvFilePath) {
        Map<String, Double> salesMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                LocalDate date = LocalDate.parse(fields[2], dateFormatter); // Assuming date is at index 2
                String month = date.getMonth().toString(); // Get the month name from the date
                double unitPrice = Double.parseDouble(fields[3]); // Assuming unit price is at index 3
                int quantity = Integer.parseInt(fields[4]); // Assuming quantity is at index 4
                double salesAmount = unitPrice * quantity;

                // Update monthly sales map
                salesMap.put(month, salesMap.getOrDefault(month, 0.0) + salesAmount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return salesMap;
    }

    public static void main(String[] args) {
        String csvFilePath = "Sales.csv";
        Map<String, Double> monthlySales = monthlySales(csvFilePath);

        // Print monthly sales
        for (Map.Entry<String, Double> entry : monthlySales.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
