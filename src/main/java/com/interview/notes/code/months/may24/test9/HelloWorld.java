package com.interview.notes.code.months.may24.test9;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HelloWorld {
    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.findItemDetails(123); // Assuming 123 is the item ID to filter
    }

    public void findItemDetails(int itemId) {
        HashMap<ItemDetails, String> map = new HashMap<>();
        // Populate map with data
        map.put(new ItemDetails(123, 5.0), "Item 123 details");
        map.put(new ItemDetails(456, 10.0), "Item 456 details");
        map.put(new ItemDetails(789, 15.0), "Item 789 details");

        // Filter the map based on item ID
        Map<ItemDetails, String> result = map.entrySet().stream()
                .filter(entry -> entry.getKey().getItemId() == itemId)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Display the filtered result
        result.forEach((key, value) -> System.out.println("Item: " + key + ", Value: " + value));
    }
}

class ItemDetails {
    int itemId;
    double quantity;

    public ItemDetails(int itemId, double quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemId=" + itemId +
                ", quantity=" + quantity +
                '}';
    }
}
