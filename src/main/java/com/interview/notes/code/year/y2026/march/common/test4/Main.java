package com.interview.notes.code.year.y2026.march.common.test4;// Import classes for Input/Output operations

// Interface defining the required structure for any drink object
interface Drink {
    // Contract method to return the price
    int getPrice();
    // Contract method to return the drink's name
    String getName();
    // Contract method to check how many are left
    int getQuantityLeft();
    // Contract method to decrease the quantity by one
    void serveDrink();
}

// Wrapper class to store the results of a successful purchase
class ServedDrinkSummary {
    // Holds the specific drink object that was dispensed
    Drink servedDrink;
    // Holds the calculated change returned to the user
    int change;
}

// Custom exception to trigger when inventory is empty
class OutOfStockException extends Exception {
    // Constructor requiring a specific formatted message
    public OutOfStockException(String message) {
        // Passes the custom message up to the parent Exception class
        super(message);
    }
}

// Custom exception to trigger when payment is too low
class InsufficientMoneyException extends Exception {
    // Default constructor for insufficient funds
    public InsufficientMoneyException() {
        // Calls the parent Exception constructor with no message
        super();
    }
}

// Concrete class representing a Coke drink
class Coke implements Drink {
    // Private field storing the cost
    private final int price;
    // Private field storing the label
    private final String name;
    // Private field storing current stock
    private int quantityLeft;

    // Constructor to setup the Coke item
    public Coke(int price, String name, int quantityLeft) {
        // Assign provided price to instance variable
        this.price = price;
        // Assign provided name to instance variable
        this.name = name;
        // Assign provided quantity to instance variable
        this.quantityLeft = quantityLeft;
    }

    // Getter returning the assigned price
    public int getPrice() { return this.price; }
    // Getter returning the assigned name
    public String getName() { return this.name; }
    // Getter returning the available stock
    public int getQuantityLeft() { return this.quantityLeft; }
    
    // Method to handle dispensing the drink
    public void serveDrink() {
        // Safety check to prevent negative inventory
        if (this.quantityLeft > 0) {
            // Subtract exactly 1 from the total stock
            this.quantityLeft -= 1;
        }
    }
}

// Concrete class representing a Fanta drink
class Fanta implements Drink {
    // Private field storing the cost
    private final int price;
    // Private field storing the label
    private final String name;
    // Private field storing current stock
    private int quantityLeft;

    // Constructor to setup the Fanta item
    public Fanta(int price, String name, int quantityLeft) {
        // Assign provided price to instance variable
        this.price = price;
        // Assign provided name to instance variable
        this.name = name;
        // Assign provided quantity to instance variable
        this.quantityLeft = quantityLeft;
    }

    // Getter returning the assigned price
    public int getPrice() { return this.price; }
    // Getter returning the assigned name
    public String getName() { return this.name; }
    // Getter returning the available stock
    public int getQuantityLeft() { return this.quantityLeft; }
    
    // Method to handle dispensing the drink
    public void serveDrink() {
        // Safety check to prevent negative inventory
        if (this.quantityLeft > 0) {
            // Subtract exactly 1 from the total stock
            this.quantityLeft -= 1;
        }
    }
}

// Concrete class representing a Sprite drink
class Sprite implements Drink {
    // Private field storing the cost
    private final int price;
    // Private field storing the label
    private final String name;
    // Private field storing current stock
    private int quantityLeft;

    // Constructor to setup the Sprite item
    public Sprite(int price, String name, int quantityLeft) {
        // Assign provided price to instance variable
        this.price = price;
        // Assign provided name to instance variable
        this.name = name;
        // Assign provided quantity to instance variable
        this.quantityLeft = quantityLeft;
    }

    // Getter returning the assigned price
    public int getPrice() { return this.price; }
    // Getter returning the assigned name
    public String getName() { return this.name; }
    // Getter returning the available stock
    public int getQuantityLeft() { return this.quantityLeft; }
    
    // Method to handle dispensing the drink
    public void serveDrink() {
        // Safety check to prevent negative inventory
        if (this.quantityLeft > 0) {
            // Subtract exactly 1 from the total stock
            this.quantityLeft -= 1;
        }
    }
}

// Core class managing the machine's state and processing
class VendingMachine {
    // Array holding exactly 3 slots mapping direct to button numbers 0,1,2
    private final Drink[] drinks = new Drink[3];

    // Method to link a specific drink object to a specific button
    void registerDrink(int buttonIdx, Drink drink) {
        // Place the drink reference into the array at the chosen index
        drinks[buttonIdx] = drink;
    }

    // Primary method called by the locked stub to process a user's request
    ServedDrinkSummary dispatch(int buttonPressed, int money) throws OutOfStockException, InsufficientMoneyException {
        // Use Java local variable inference to fetch the requested drink
        var drink = drinks[buttonPressed];

        // Validate stock FIRST: Empty stock takes priority over insufficient funds
        if (drink.getQuantityLeft() == 0) {
            // Throw the exact required string format stating it is out of stock
            throw new OutOfStockException(drink.getName() + " is out of stock");
        }

        // Validate payment SECOND: Check if inserted money covers the cost
        if (money < drink.getPrice()) {
            // Throw generic exception for low funds
            throw new InsufficientMoneyException();
        }

        // Initialize a new summary wrapper to return successful data
        var summary = new ServedDrinkSummary();
        // Attach the validated drink object to the summary output
        summary.servedDrink = drink;
        // Calculate the difference between payment and price for change
        summary.change = money - drink.getPrice();

        // Return the payload (the external locked stub calls serveDrink() next)
        return summary;
    }
}

// Class strictly for testing our implementations
public class Main {
    // Application entry point to run our validations
    public static void main(String[] args) {
        // Announce test suite start
        System.out.println("--- Starting Tests ---");
        // Run standard provided test
        runSampleTest();
        // Run edge case high volume test
        runLargeDataTest();
    }

    // Method recreating Sample Case 0 logic
    static void runSampleTest() {
        // Create new machine instance
        var machine = new VendingMachine();
        
        // Setup quantities and prices from Sample Case 0
        // Register Coke to button 0: price 5, quantity 2
        machine.registerDrink(0, new Coke(5, "Coke", 2));
        // Register Fanta to button 1: price 10, quantity 1
        machine.registerDrink(1, new Fanta(10, "Fanta", 1));
        // Register Sprite to button 2: price 15, quantity 0
        machine.registerDrink(2, new Sprite(15, "Sprite", 0));

        // Output initialization text like the sample
        System.out.println("Vending machine set up");

        // Request 1: Button 0 (Coke), Money 20. Expect Success.
        try {
            // Dispatch request
            var res = machine.dispatch(0, 20);
            // Simulate locked stub decrementing inventory
            res.servedDrink.serveDrink();
            // Verify output matches expected "Coke 15"
            if (res.servedDrink.getName().equals("Coke") && res.change == 15) {
                // Pass check
                System.out.println("Req 1 (Coke 20): PASS");
            } else {
                // Fail check
                System.out.println("Req 1: FAIL");
            }
        // Catch any failure to mark test
        } catch (Exception e) { System.out.println("Req 1: FAIL"); }

        // Request 2: Button 2 (Sprite), Money 20. Expect OutOfStock.
        try {
            // Dispatch request
            machine.dispatch(2, 20);
            // Fail if no exception
            System.out.println("Req 2: FAIL");
        // Catch specifically for stock issue
        } catch (OutOfStockException e) {
            // Verify exact message
            if (e.getMessage().equals("Sprite is out of stock")) {
                // Pass check
                System.out.println("Req 2 (Sprite OutOfStock): PASS");
            }
        // Catch wrong exceptions
        } catch (Exception e) { System.out.println("Req 2: FAIL"); }

        // Request 3: Button 2 (Sprite), Money 5. Expect OutOfStock Priority.
        try {
            // Dispatch request with low money AND empty stock
            machine.dispatch(2, 5);
            // Fail if no exception
            System.out.println("Req 3: FAIL");
        // Catch the correct priority exception
        } catch (OutOfStockException e) {
            // Pass check because stock error correctly overrode money error
            System.out.println("Req 3 (Out of stock priority): PASS");
        // Catch wrong exceptions
        } catch (Exception e) { System.out.println("Req 3: FAIL"); }

        // Request 4: Button 1 (Fanta), Money 5. Expect InsufficientMoney.
        try {
            // Dispatch request with low money
            machine.dispatch(1, 5);
            // Fail if no exception
            System.out.println("Req 4: FAIL");
        // Catch specific money error
        } catch (InsufficientMoneyException e) {
            // Pass check
            System.out.println("Req 4 (Insufficient Money): PASS");
        // Catch wrong exceptions
        } catch (Exception e) { System.out.println("Req 4: FAIL"); }
    }

    // Method to verify system handles max constraints (10,000 queries)
    static void runLargeDataTest() {
        // Create machine for bulk testing
        var machine = new VendingMachine();
        // Put exactly 10,000 Cokes in the machine
        machine.registerDrink(0, new Coke(5, "Coke", 10000));
        
        // Track successfully processed items
        var successCount = 0;
        
        // Loop 10,000 times (max limit allowed by constraints)
        for(int i = 0; i < 10000; i++) {
            // Use try-catch for safety during execution
            try {
                // Buy one coke with exact change
                var res = machine.dispatch(0, 5);
                // Reduce the inventory
                res.servedDrink.serveDrink();
                // Increment counter on success
                successCount++;
            // Catch and ignore exceptions for bulk test logic flow
            } catch(Exception e) {}
        }
        
        // Check if all 10,000 were processed without timeout or failure
        if (successCount == 10000) {
            // Pass large data verification
            System.out.println("Large Data Test (10,000 reqs): PASS");
        } else {
            // Fail if counts don't match
            System.out.println("Large Data Test: FAIL");
        }
    }
}