package com.interview.notes.code.year.y2026.march.common.test5;

// --- PROVIDED LOCKED STUB CLASSES (Included for compilation/testing) ---

// Interface defining the behavior of a drink
interface Drink {
    // Returns the price of the drink
    int getPrice();
    // Returns the name of the drink
    String getName();
    // Returns the current remaining quantity
    int getQuantityLeft();
    // Decrements the quantity of the drink when served
    void serveDrink();
}

// Class representing the successful output of a vending machine transaction
class ServedDrinkSummary {
    // The specific drink that was dispensed
    Drink servedDrink;
    // The amount of change returned to the user
    int change;
}

// Exception thrown when a requested drink has 0 quantity
class OutOfStockException extends Exception {
    // Constructor taking a custom error message
    public OutOfStockException(String message) {
        // Passes the message to the parent Exception class
        super(message);
    }
}

// Exception thrown when user provides less money than the drink's price
class InsufficientMoneyException extends Exception {
    // Default constructor for insufficient funds
    public InsufficientMoneyException() {
        // Calls the parent Exception class constructor
        super();
    }
}

// --- CONCRETE DRINK IMPLEMENTATION (Needed to run tests) ---

// A simple implementation of the Drink interface to use in our test cases
class SimpleDrink implements Drink {
    // Store the drink's name
    private final String name;
    // Store the drink's price
    private final int price;
    // Store the drink's available inventory
    private int quantity;

    // Constructor to initialize a new drink item
    public SimpleDrink(String name, int price, int quantity) {
        // Assign the passed name
        this.name = name;
        // Assign the passed price
        this.price = price;
        // Assign the passed quantity
        this.quantity = quantity;
    }

    // Return the stored price
    public int getPrice() { return price; }
    // Return the stored name
    public String getName() { return name; }
    // Return the current available quantity
    public int getQuantityLeft() { return quantity; }
    // Reduce the quantity by 1 when a drink is successfully bought
    public void serveDrink() { this.quantity--; }
}

// --- VENDING MACHINE IMPLEMENTATION (Your required solution) ---

// The main class handling the vending machine logic
class VendingMachine {
    
    // Array to hold exactly 3 drinks, mapping directly to button numbers 0, 1, and 2
    private final Drink[] drinks = new Drink[3];

    // Method to assign a drink to a specific button on the machine
    void registerDrink(int buttonIdx, Drink drink) {
        // Store the provided drink object at the specified array index
        drinks[buttonIdx] = drink;
    }

    // Method to process a purchase attempt
    ServedDrinkSummary dispatch(int buttonPressed, int money) throws OutOfStockException, InsufficientMoneyException {
        // Retrieve the requested drink from the array using the button number
        var drink = drinks[buttonPressed];

        // Check inventory FIRST, as out-of-stock errors override money errors
        if (drink.getQuantityLeft() == 0) {
            // Throw exception with the exact requested string format if empty
            throw new OutOfStockException(drink.getName() + " is out of stock");
        }

        // Check if the user inserted enough money to cover the price
        if (money < drink.getPrice()) {
            // Throw exception if the funds are too low
            throw new InsufficientMoneyException();
        }

        // Both checks passed, so trigger the drink's internal dispense logic
        drink.serveDrink();

        // Create a new summary object to package our successful result
        var summary = new ServedDrinkSummary();
        // Assign the successfully purchased drink to the summary
        summary.servedDrink = drink;
        // Calculate the difference between money given and price for the change
        summary.change = money - drink.getPrice();

        // Return the finalized transaction details back to the caller
        return summary;
    }
}

// --- TESTING FRAMEWORK ---

// Main class to execute and verify our logic
public class Main {
    // Main execution method
    public static void main(String[] args) {
        // Initialize the vending machine instance
        var machine = new VendingMachine();
        
        // Setup based exactly on Sample Case 0
        // Register Coke to button 0: price 5, quantity 2
        machine.registerDrink(0, new SimpleDrink("Coke", 5, 2));
        // Register Fanta to button 1: price 10, quantity 1
        machine.registerDrink(1, new SimpleDrink("Fanta", 10, 1));
        // Register Sprite to button 2: price 15, quantity 0
        machine.registerDrink(2, new SimpleDrink("Sprite", 15, 0));

        // Print a header for the test results
        System.out.println("--- Running Test Cases ---");

        // Test 1: Valid purchase (Coke)
        try {
            // Attempt to buy Coke (button 0) with 20 money
            var res = machine.dispatch(0, 20);
            // Verify the correct drink and correct change (20 - 5 = 15) were returned
            if (res.servedDrink.getName().equals("Coke") && res.change == 15) {
                // Print success if expectations are met
                System.out.println("Test 1 (Valid Purchase): PASS");
            } else {
                // Print failure if logic is wrong
                System.out.println("Test 1: FAIL - Wrong output");
            }
        // Catch block to fail test if an unexpected exception occurs
        } catch (Exception e) { System.out.println("Test 1: FAIL - Unexpected Exception"); }

        // Test 2: Out of stock (Sprite)
        try {
            // Attempt to buy Sprite (button 2) with 20 money
            machine.dispatch(2, 20);
            // If no exception is thrown, the test failed
            System.out.println("Test 2: FAIL - Expected OutOfStockException");
        // Catch the specific expected exception
        } catch (OutOfStockException e) {
            // Verify the exact error message matches requirements
            if (e.getMessage().equals("Sprite is out of stock")) {
                // Print success if the correct exception and message are generated
                System.out.println("Test 2 (Out of Stock): PASS");
            } else {
                // Print failure if the message format is wrong
                System.out.println("Test 2: FAIL - Wrong exception message");
            }
        // Catch block to fail test if the WRONG exception type occurs
        } catch (Exception e) { System.out.println("Test 2: FAIL - Wrong Exception"); }

        // Test 3: Out of stock AND Insufficient money (Sprite) -> Should throw OutOfStock
        try {
            // Attempt to buy Sprite (button 2) with 5 money (less than price 15)
            machine.dispatch(2, 5);
            // If no exception is thrown, the test failed
            System.out.println("Test 3: FAIL - Expected Exception");
        // Catch the expected OutOfStockException (priority over InsufficientMoney)
        } catch (OutOfStockException e) {
            // Print success since the precedence logic worked perfectly
            System.out.println("Test 3 (Out of Stock precedence): PASS");
        // Catch block to fail test if InsufficientMoneyException is wrongly prioritized
        } catch (Exception e) { System.out.println("Test 3: FAIL - Wrong Exception Prioritized"); }

        // Test 4: Insufficient money (Fanta)
        try {
            // Attempt to buy Fanta (button 1) with 5 money (less than price 10)
            machine.dispatch(1, 5);
            // If no exception is thrown, the test failed
            System.out.println("Test 4: FAIL - Expected InsufficientMoneyException");
        // Catch the specifically expected InsufficientMoneyException
        } catch (InsufficientMoneyException e) {
            // Print success because the correct exception was triggered
            System.out.println("Test 4 (Insufficient Money): PASS");
        // Catch block to fail test if the wrong exception type occurs
        } catch (Exception e) { System.out.println("Test 4: FAIL - Wrong Exception"); }
    }
}