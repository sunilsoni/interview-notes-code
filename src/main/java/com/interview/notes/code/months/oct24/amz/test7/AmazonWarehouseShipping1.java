package com.interview.notes.code.months.oct24.amz.test7;

import java.util.*;

class AmazonWarehouseShipping1 {
    private Map<String, List<Warehouse>> regionWarehouses;
    private Map<String, Map<String, Integer>> warehouseItems;

    public AmazonWarehouseShipping1() {
        regionWarehouses = new HashMap<>();
        warehouseItems = new HashMap<>();
    }

    private static class Warehouse {
        String name;
        String region;

        Warehouse(String name, String region) {
            this.name = name;
            this.region = region;
        }
    }

    public void addWarehouse(String region, String warehouseName) {
        regionWarehouses.computeIfAbsent(region, k -> new ArrayList<>()).add(new Warehouse(warehouseName, region));
        warehouseItems.putIfAbsent(warehouseName, new HashMap<>());
    }

    public void addItemToWarehouse(String warehouseName, String item, int quantity) {
        warehouseItems.get(warehouseName).put(item, quantity);
    }

    public Map<String, List<String>> getShippingWarehouses(String customerRegion, List<String> orderedItems) {
        Map<String, List<String>> result = new HashMap<>();
        Set<String> remainingItems = new HashSet<>(orderedItems);
        List<String> unavailableItems = new ArrayList<>();

        // Try to find a single warehouse with all items
        String singleWarehouse = findSingleWarehouse(customerRegion, remainingItems);
        if (singleWarehouse != null) {
            result.put(singleWarehouse, new ArrayList<>(remainingItems));
            return result;
        }

        // If not possible, try to fulfill from multiple warehouses
        List<Warehouse> allWarehouses = new ArrayList<>(regionWarehouses.get(customerRegion));
        for (List<Warehouse> warehouses : regionWarehouses.values()) {
            if (!warehouses.equals(regionWarehouses.get(customerRegion))) {
                allWarehouses.addAll(warehouses);
            }
        }

        for (Warehouse warehouse : allWarehouses) {
            List<String> availableItems = new ArrayList<>();
            for (String item : new ArrayList<>(remainingItems)) {
                if (warehouseItems.get(warehouse.name).containsKey(item) && warehouseItems.get(warehouse.name).get(item) > 0) {
                    availableItems.add(item);
                    remainingItems.remove(item);
                }
            }
            if (!availableItems.isEmpty()) {
                result.put(warehouse.name, availableItems);
            }
            if (remainingItems.isEmpty()) {
                break;
            }
        }

        // Add unavailable items to the result
        if (!remainingItems.isEmpty()) {
            unavailableItems.addAll(remainingItems);
        }
        if (!unavailableItems.isEmpty()) {
            result.put("UNAVAILABLE", unavailableItems);
        }

        return result;
    }

    private String findSingleWarehouse(String customerRegion, Set<String> items) {
        List<Warehouse> regionalWarehouses = regionWarehouses.getOrDefault(customerRegion, new ArrayList<>());
        for (Warehouse warehouse : regionalWarehouses) {
            if (hasAllItems(warehouse.name, items)) {
                return warehouse.name;
            }
        }
        for (List<Warehouse> warehouses : regionWarehouses.values()) {
            for (Warehouse warehouse : warehouses) {
                if (hasAllItems(warehouse.name, items)) {
                    return warehouse.name;
                }
            }
        }
        return null;
    }

    private boolean hasAllItems(String warehouseName, Set<String> items) {
        Map<String, Integer> inventory = warehouseItems.get(warehouseName);
        return items.stream().allMatch(item -> inventory.containsKey(item) && inventory.get(item) > 0);
    }
    private Map<String, List<String>> findMultiWarehouseFulfillment(String customerRegion, List<String> items) {
        Map<String, List<String>> result = new HashMap<>();
        List<String> remainingItems = new ArrayList<>(items);

        // First, try to fulfill from warehouses in the customer's region
        fulfillFromRegion(customerRegion, remainingItems, result);

        // If there are still remaining items, try other regions
        if (!remainingItems.isEmpty()) {
            for (String region : regionWarehouses.keySet()) {
                if (!region.equals(customerRegion)) {
                    fulfillFromRegion(region, remainingItems, result);
                    if (remainingItems.isEmpty()) {
                        break;
                    }
                }
            }
        }

        // If we couldn't fulfill all items, throw an exception or handle as needed
        if (!remainingItems.isEmpty()) {
            throw new RuntimeException("Unable to fulfill order. Missing items: " + remainingItems);
        }

        return result;
    }

    private void fulfillFromRegion(String region, List<String> remainingItems, Map<String, List<String>> result) {
        for (Warehouse warehouse : regionWarehouses.get(region)) {
            List<String> fulfilledItems = new ArrayList<>();
            Map<String, Integer> inventory = null;//warehouseItems.get(warehouse.getId());

            Iterator<String> iterator = remainingItems.iterator();
            while (iterator.hasNext()) {
                String item = iterator.next();
                if (inventory.containsKey(item) && inventory.get(item) > 0) {
                    fulfilledItems.add(item);
                    iterator.remove();
                    inventory.put(item, inventory.get(item) - 1);
                }
            }

            if (!fulfilledItems.isEmpty()) {
               // result.put(warehouse.getId(), fulfilledItems);
            }

            if (remainingItems.isEmpty()) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        AmazonWarehouseShipping1 aws = new AmazonWarehouseShipping1();

        // Setup warehouses and inventory
        aws.addWarehouse("North", "AB1");
        aws.addWarehouse("North", "BD1");
        aws.addWarehouse("West", "XY3");
        aws.addWarehouse("West", "WD2");
        aws.addWarehouse("South", "IU9");

        aws.addItemToWarehouse("AB1", "item1", 5);
        aws.addItemToWarehouse("AB1", "item2", 3);
        aws.addItemToWarehouse("AB1", "item3", 2);
        aws.addItemToWarehouse("BD1", "item3", 4);
        aws.addItemToWarehouse("BD1", "item4", 1);
        aws.addItemToWarehouse("XY3", "item1", 2);
        aws.addItemToWarehouse("XY3", "item4", 3);
        aws.addItemToWarehouse("WD2", "item2", 1);
        aws.addItemToWarehouse("WD2", "item5", 4);
        aws.addItemToWarehouse("IU9", "item4", 2);
        aws.addItemToWarehouse("IU9", "item5", 3);

        // Test cases
        testCase(aws, "North", Arrays.asList("item1", "item2", "item3"));
        testCase(aws, "West", Arrays.asList("item1", "item4", "item5"));
        testCase(aws, "South", Arrays.asList("item4", "item5", "item6"));
        testCase(aws, "North", Arrays.asList("item1", "item2", "item3", "item4", "item5"));
    }

    private static void testCase(AmazonWarehouseShipping1 aws, String customerRegion, List<String> orderedItems) {
        System.out.println("Customer Region: " + customerRegion);
        System.out.println("Ordered Items: " + orderedItems);
        Map<String, List<String>> result = aws.getShippingWarehouses(customerRegion, orderedItems);
        System.out.println("Result:");
        for (Map.Entry<String, List<String>> entry : result.entrySet()) {
            if (entry.getKey().equals("UNAVAILABLE")) {
                System.out.println("  Unavailable items: " + entry.getValue());
            } else {
                System.out.println("  Warehouse " + entry.getKey() + ": " + entry.getValue());
            }
        }
        System.out.println();
    }
}
