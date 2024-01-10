package com.interview.notes.code.months.year2023.Aug23.test1;

public class LabAllocation {
    public static void main(String[] args) {
        // Sample input: Seating capacities of L1, L2, L3, and number of trainees
        String input = "30,40,20,25";

        // Split the input and convert to integers
        String[] parts = input.split(",");
        int l1 = Integer.parseInt(parts[0]);
        int l2 = Integer.parseInt(parts[1]);
        int l3 = Integer.parseInt(parts[2]);
        int trainees = Integer.parseInt(parts[3]);

        // Initialize a variable to keep track of the minimal unused systems
        int minUnused = Integer.MAX_VALUE;

        // Store the labs that meet the criteria
        String selectedLabs = "";

        // Check if L1 meets the criteria
        if (trainees <= l1) {
            if (l1 - trainees < minUnused) {
                minUnused = l1 - trainees;
                selectedLabs = "L1";
            }
        }

        // Check if L2 meets the criteria
        if (trainees <= l2) {
            if (l2 - trainees < minUnused) {
                minUnused = l2 - trainees;
                selectedLabs = "L2";
            } else if (l2 - trainees == minUnused) {
                selectedLabs += ",L2";
            }
        }

        // Check if L3 meets the criteria
        if (trainees <= l3) {
            if (l3 - trainees < minUnused) {
                minUnused = l3 - trainees;
                selectedLabs = "L3";
            } else if (l3 - trainees == minUnused) {
                selectedLabs += ",L3";
            }
        }

        // Print the result
        if (selectedLabs.isEmpty()) {
            System.out.println("No labs");
        } else {
            System.out.println("Lab - " + selectedLabs);
        }
    }
}
