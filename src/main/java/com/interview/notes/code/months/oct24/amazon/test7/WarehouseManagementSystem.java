package com.interview.notes.code.months.oct24.amazon.test7;

import java.util.*;

public class WarehouseManagementSystem {

    // Data structures
    private Map<String, Region> regions;
    private Map<String, Warehouse> warehouses;
    private Map<String, Item> items;

    // Constructor
    public WarehouseManagementSystem() {
        regions = new HashMap<>();
        warehouses = new HashMap<>();
        items = new HashMap<>();
        initializeData();
    }

    // Main method for testing
    public static void main(String[] args) {
        WarehouseManagementSystem system = new WarehouseManagementSystem();
        List<String> orderItems = Arrays.asList("Item1", "Item2", "Item3");
        Map<String, List<String>> result = system.getShippingWarehouses("North", orderItems);
        System.out.println("Order fulfillment: " + result);
    }

    // Initialize sample data
    private void initializeData() {
        // Create regions
        String[] regionNames = {"North", "South", "East", "West"};
        for (String name : regionNames) {
            regions.put(name, new Region(name));
        }

        // Create warehouses and add them to regions
        addWarehouse("AB1", "North");
        addWarehouse("BD1", "North");
        addWarehouse("XY3", "West");
        addWarehouse("WD2", "West");
        addWarehouse("LN3", "West");
        addWarehouse("IU9", "South");

        // Add some sample items
        addItem("Item1", 10);
        addItem("Item2", 20);
        addItem("Item3", 30);

        // Distribute items to warehouses (simplified)
        distributeItems();
    }

    // Method to determine shipping warehouses
    public Map<String, List<String>> getShippingWarehouses(String customerRegion, List<String> orderItems) {
        Map<String, List<String>> result = new HashMap<>();
        List<String> remainingItems = new ArrayList<>(orderItems);

        // Try to fulfill from the customer's region first
        fulfillFromRegion(customerRegion, remainingItems, result);

        // If items remain, try other regions
        if (!remainingItems.isEmpty()) {
            for (String region : regions.keySet()) {
                if (!region.equals(customerRegion)) {
                    fulfillFromRegion(region, remainingItems, result);
                    if (remainingItems.isEmpty()) break;
                }
            }
        }

        // If we couldn't fulfill all items, consider item transfers (bonus requirement)
        if (!remainingItems.isEmpty()) {
            considerItemTransfers(remainingItems, result);
        }

        // If still unfulfilled, throw an exception
        if (!remainingItems.isEmpty()) {
            throw new RuntimeException("Unable to fulfill order. Missing items: " + remainingItems);
        }

        return result;
    }

    private void fulfillFromRegion(String regionName, List<String> remainingItems, Map<String, List<String>> result) {
        Region region = regions.get(regionName);
        for (Warehouse warehouse : region.getWarehouses()) {
            List<String> fulfilledItems = new ArrayList<>();
            Iterator<String> iterator = remainingItems.iterator();
            while (iterator.hasNext()) {
                String itemName = iterator.next();
                if (warehouse.hasItem(itemName)) {
                    fulfilledItems.add(itemName);
                    iterator.remove();
                    warehouse.removeItem(itemName);
                }
            }
            if (!fulfilledItems.isEmpty()) {
                result.put(warehouse.getName(), fulfilledItems);
            }
            if (remainingItems.isEmpty()) break;
        }
    }

    private void considerItemTransfers(List<String> remainingItems, Map<String, List<String>> result) {
        for (String itemName : new ArrayList<>(remainingItems)) {
            for (Warehouse sourceWarehouse : warehouses.values()) {
                if (sourceWarehouse.hasItem(itemName)) {
                    // Find the nearest warehouse that already has items for this order
                    Warehouse nearestWarehouse = findNearestWarehouse(result.keySet(), sourceWarehouse);
                    if (nearestWarehouse != null) {
                        // Transfer item
                        sourceWarehouse.removeItem(itemName);
                        nearestWarehouse.addItem(itemName);
                        result.get(nearestWarehouse.getName()).add(itemName);
                        remainingItems.remove(itemName);
                        break;
                    }
                }
            }
        }
    }

    private Warehouse findNearestWarehouse(Set<String> warehouseNames, Warehouse sourceWarehouse) {
        // This is a simplified version. In reality, you'd use actual distances or a more sophisticated method
        for (String name : warehouseNames) {
            return warehouses.get(name);
        }
        return null;
    }

    // Helper methods for data management
    public void addWarehouse(String name, String regionName) {
        Warehouse warehouse = new Warehouse(name);
        warehouses.put(name, warehouse);
        regions.get(regionName).addWarehouse(warehouse);
    }

    public void addItem(String name, int quantity) {
        items.put(name, new Item(name, quantity));
    }

    private void distributeItems() {
        // Simplified item distribution
        for (Item item : items.values()) {
            for (Warehouse warehouse : warehouses.values()) {
                warehouse.addItem(item.getName());
            }
        }
    }

    // Inner classes for data structures
    private class Region {
        private String name;
        private List<Warehouse> warehouses;

        public Region(String name) {
            this.name = name;
            this.warehouses = new ArrayList<>();
        }

        public void addWarehouse(Warehouse warehouse) {
            warehouses.add(warehouse);
        }

        public List<Warehouse> getWarehouses() {
            return warehouses;
        }
    }

    private class Warehouse {
        private String name;
        private Map<String, Integer> inventory;

        public Warehouse(String name) {
            this.name = name;
            this.inventory = new HashMap<>();
        }

        public void addItem(String itemName) {
            inventory.put(itemName, inventory.getOrDefault(itemName, 0) + 1);
        }

        public boolean hasItem(String itemName) {
            return inventory.getOrDefault(itemName, 0) > 0;
        }

        public void removeItem(String itemName) {
            if (hasItem(itemName)) {
                inventory.put(itemName, inventory.get(itemName) - 1);
            }
        }

        public String getName() {
            return name;
        }
    }

    private class Item {
        private String name;
        private int quantity;

        public Item(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }
    }
}
