package com.interview.notes.code.year.y2024.may24.test10;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Product prod1 = new Product("P001", "Widget", 15.99);
        Product prod2 = new Product("P002", "Gadget", 23.49);

        inventory.addProduct(prod1);
        inventory.addProduct(prod2);

        inventory.restockProduct("P001", 20);
        inventory.restockProduct("P002", 15);

        inventory.sellProduct("P001", 5);
        inventory.sellProduct("P002", 2);

        inventory.displayInventory();  // Print current inventory
    }
}
