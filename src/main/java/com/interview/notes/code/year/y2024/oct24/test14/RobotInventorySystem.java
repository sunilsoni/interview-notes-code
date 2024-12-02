package com.interview.notes.code.year.y2024.oct24.test14;

import java.util.*;

/*
Your task is to implement an inventory tracking system for a robot retail store.
You are given a transaction log logs, where each log item corresponds to one of three transaction types: supply, sell, or upgrade . Log items are provided in the following format:
• ["supply", "<robot name>", "<count>", "‹price>"] - the store receives ‹count> units of ‹robot name > , and each robot costs ‹price>.
• ["sell", "<robot name>", "<count>"] - the store sells ‹count> units of ‹robot name>. If the specified robot is available at different prices, the cheapest ones should be sold first. It is guaranteed that the store will always have enough robots to satisfy all sell transactions.
• ["upgrade", "<robot name>", "<count>", "<old price>", "‹new price>"] - the store upgrades ‹count> units of ‹robot name> to a new version that should be sold with a higher price. It is guaranteed that there are ‹count> units of older version that were being sold at price <old price>
The tracking system should return the revenue from all sell transactions after processing the entire transaction log. Specifically, return an array representing the amount of money the store received from each sell transaction.
Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than o(logs. length?) will fit within the execution time limit.
Example
For
logs = 1
["supply", "robotl", "2", "100"], ["supply", "robot2", "3", "60"], ["sell", "robotl", "1"], ["sell", "robot2", "1"], ["upgrade", "robot2", "1",
"60", "100"],
["sell", "robot2", "1"], ["sell", "robot2", "1"]
the output should be
solution (logs) = [100, 60, 60, 100]
Let's look at all transactions in order:

 */
public class RobotInventorySystem {

    /**
     * Processes the transaction logs and returns the revenue from each sell transaction.
     *
     * @param logs An array of transaction logs.
     * @return An array of revenues from each sell transaction.
     */
    public static List<Integer> solution(List<List<String>> logs) {
        // Map to keep track of robot inventory: robotName -> price -> count
        Map<String, TreeMap<Integer, Integer>> inventory = new HashMap<>();
        // List to store the revenue from each sell transaction
        List<Integer> revenues = new ArrayList<>();

        for (List<String> log : logs) {
            String transactionType = log.get(0);
            String robotName = log.get(1);

            switch (transactionType) {
                case "supply":
                    int supplyCount = Integer.parseInt(log.get(2));
                    int supplyPrice = Integer.parseInt(log.get(3));
                    supplyRobot(inventory, robotName, supplyPrice, supplyCount);
                    break;

                case "sell":
                    int sellCount = Integer.parseInt(log.get(2));
                    int revenue = sellRobot(inventory, robotName, sellCount);
                    // If sellCount > 1, revenue will include the total revenue from all units sold
                    revenues.addAll(splitRevenue(revenue, sellCount));
                    break;

                case "upgrade":
                    int upgradeCount = Integer.parseInt(log.get(2));
                    int oldPrice = Integer.parseInt(log.get(3));
                    int newPrice = Integer.parseInt(log.get(4));
                    upgradeRobot(inventory, robotName, oldPrice, newPrice, upgradeCount);
                    break;

                default:
                    // Invalid transaction type
                    break;
            }
        }

        return revenues;
    }

    /**
     * Adds robots to the inventory.
     */
    private static void supplyRobot(Map<String, TreeMap<Integer, Integer>> inventory, String robotName, int price, int count) {
        inventory.putIfAbsent(robotName, new TreeMap<>());
        TreeMap<Integer, Integer> priceMap = inventory.get(robotName);
        priceMap.put(price, priceMap.getOrDefault(price, 0) + count);
    }

    /**
     * Sells robots from the inventory, starting from the cheapest price.
     *
     * @return The total revenue from the sale.
     */
    private static int sellRobot(Map<String, TreeMap<Integer, Integer>> inventory, String robotName, int count) {
        TreeMap<Integer, Integer> priceMap = inventory.get(robotName);
        int totalRevenue = 0;
        while (count > 0) {
            Map.Entry<Integer, Integer> entry = priceMap.firstEntry();
            int price = entry.getKey();
            int available = entry.getValue();
            int sellCount = Math.min(count, available);
            totalRevenue += sellCount * price;
            count -= sellCount;
            if (available == sellCount) {
                priceMap.pollFirstEntry();
            } else {
                priceMap.put(price, available - sellCount);
            }
        }
        return totalRevenue;
    }

    /**
     * Upgrades robots by moving them from old price to new price.
     */
    private static void upgradeRobot(Map<String, TreeMap<Integer, Integer>> inventory, String robotName, int oldPrice, int newPrice, int count) {
        TreeMap<Integer, Integer> priceMap = inventory.get(robotName);
        int available = priceMap.getOrDefault(oldPrice, 0);
        if (available >= count) {
            priceMap.put(oldPrice, available - count);
            if (priceMap.get(oldPrice) == 0) {
                priceMap.remove(oldPrice);
            }
            priceMap.put(newPrice, priceMap.getOrDefault(newPrice, 0) + count);
        }
    }

    /**
     * Splits the total revenue into individual revenues if multiple units were sold.
     */
    private static List<Integer> splitRevenue(int totalRevenue, int count) {
        List<Integer> revenues = new ArrayList<>();
        int unitRevenue = totalRevenue / count;
        for (int i = 0; i < count; i++) {
            revenues.add(unitRevenue);
        }
        return revenues;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test case provided in the example
        List<List<String>> logs = Arrays.asList(
                Arrays.asList("supply", "robot1", "2", "100"),
                Arrays.asList("supply", "robot2", "3", "60"),
                Arrays.asList("sell", "robot1", "1"),
                Arrays.asList("sell", "robot2", "1"),
                Arrays.asList("upgrade", "robot2", "1", "60", "100"),
                Arrays.asList("sell", "robot2", "1"),
                Arrays.asList("sell", "robot2", "1")
        );

        List<Integer> expectedOutput = Arrays.asList(100, 60, 60, 100);
        List<Integer> actualOutput = solution(logs);

        // Testing the output
        if (expectedOutput.equals(actualOutput)) {
            System.out.println("Test case passed.");
        } else {
            System.out.println("Test case failed.");
            System.out.println("Expected Output: " + expectedOutput);
            System.out.println("Actual Output: " + actualOutput);
        }

        // Additional test cases can be added here
    }
}
