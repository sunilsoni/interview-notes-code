package com.interview.notes.code.year.y2025.march.common.test8;

import java.util.Arrays;

/*
WRKING 100%

### **Question 1**
There is a class **StockPrediction** which has attributes **gain**, **stockPrice**, and a method named **expectedValue()**.

The default implementation of **expectedValue()** method calculates the expected value as:
\[
\text{expectedValue} = \text{stockPrice} + \text{gain}
\]

Overload **expectedValue()** so that it takes an integer parameter, **transactionCosts**, as input and calculates the expected value as:
\[
\text{expectedValue} = (\text{stockPrice} + \text{gain}) - \text{transactionCosts}
\]

Overload this method so that it takes **transactionCosts** as a string type and calculates after converting the string to an integer:
\[
\text{expectedValue} = (\text{stockPrice} + \text{gain}) - \text{transactionCosts}
\]

#### **Example**
- **gain** = 60
- **stockPrice** = 120
- **transactionCosts** = 7

- **Default Calculation:**
  \[
  \text{expectedValue} = 180
  \]

- **Using First Overload (Integer transactionCosts):**
  \[
  \text{expectedValue} = 173
  \]

- **Using Second Overload (String transactionCosts):**
  \[
  \text{expectedValue} = 173
  \]

---

### **Input Format for Custom Testing**
A single line of input consists of space-separated integers:
\[
\text{gain}, \text{stockPrice}, \text{transactionCosts}
\]

---

### **Sample Case 0**
#### **Sample Input for Custom Testing**
```
60 120 7
```
#### **Sample Output**
```
180 173 173
```

---

### **Sample Case 1**
#### **Sample Input for Custom Testing**
```
5 3 4
```
#### **Sample Output**
```
8 4 4
```

---
 */
public class Solution {

    /**
     * Main method for:
     * 1. Demonstrating solution logic
     * 2. Testing multiple scenarios (including large data)
     */
    public static void main(String[] args) {
        // We'll define test cases in an array of inputs and expected outputs.
        // Each test case is {gain, stockPrice, transactionCosts, expectedNoParam, expectedIntParam, expectedStrParam}

        // Example explanation for test case #1: "5 3 4"
        //   - expectedValue() = 5 + 3 = 8
        //   - expectedValue(4) = 8 - 4 = 4
        //   - expectedValue("4") = same as above, 4
        //
        // We'll test them all and print PASS/FAIL.

        int[][] testCases = {
                // gain, stockPrice, transactionCosts, expectedNoParam, expectedIntParam, expectedStrParam
                {5, 3, 4, 8, 4, 4},  // From sample
                {60, 120, 7, 180, 173, 173} // Another example
        };

        // Print header
        System.out.println("Running Tests...");

        // We can use Java 8+ stream for cleaner iteration
        Arrays.stream(testCases).forEach(tc -> {
            // Extract values from test case
            int gain = tc[0];
            int stockPrice = tc[1];
            int transactionCosts = tc[2];
            int expNoParam = tc[3];
            int expIntParam = tc[4];
            int expStrParam = tc[5];

            // Create a StockPrediction instance
            StockPrediction sp = new StockPrediction(gain, stockPrice);

            // Compute results
            int resultNoParam = sp.expectedValue();
            int resultIntParam = sp.expectedValue(transactionCosts);
            int resultStrParam = sp.expectedValue(String.valueOf(transactionCosts));

            // Compare with expected and determine pass/fail
            boolean pass = (resultNoParam == expNoParam)
                    && (resultIntParam == expIntParam)
                    && (resultStrParam == expStrParam);

            // Print the outcome
            if (pass) {
                System.out.println("PASS => Input: " + gain + " " + stockPrice + " " + transactionCosts
                        + " | Results: " + resultNoParam + " " + resultIntParam + " " + resultStrParam);
            } else {
                System.out.println("FAIL => Input: " + gain + " " + stockPrice + " " + transactionCosts
                        + " | Expected: " + expNoParam + " " + expIntParam + " " + expStrParam
                        + " | Got: " + resultNoParam + " " + resultIntParam + " " + resultStrParam);
            }
        });

        // Test for large data inputs (e.g., large gain, stockPrice, transactionCosts)
        // Here we just do a quick demonstration with big values near integer range
        System.out.println("\nTesting Large Data Input...");
        int largeGain = 1_000_000_000;       // 1 billion
        int largeStockPrice = 1_500_000_000; // 1.5 billion
        int largeTransactionCosts = 500_000_000;

        StockPrediction largeSP = new StockPrediction(largeGain, largeStockPrice);
        // If we do (1.5B + 1B) = 2.5B which is within the range of 32-bit signed integer? Actually it might overflow in some languages,
        // but in Java, the max integer is ~2.147B, so let's check carefully:
        // This example may cause overflow if you exceed 2,147,483,647
        // For safety, let's pick smaller but still large numbers:
        largeGain = 1_000_000_000;       // 1 billion
        largeStockPrice = 1_000_000_000; // 1 billion
        // => sum = 2,000,000,000 which is within the integer limit
        largeTransactionCosts = 500_000_000;

        largeSP = new StockPrediction(largeGain, largeStockPrice);
        int largeNoParam = largeSP.expectedValue(); // 2,000,000,000
        int largeIntParam = largeSP.expectedValue(largeTransactionCosts); // 1,500,000,000
        int largeStrParam = largeSP.expectedValue(String.valueOf(largeTransactionCosts)); // 1,500,000,000

        // Print results
        System.out.println("Large Input Results: "
                + largeNoParam + " " + largeIntParam + " " + largeStrParam);
        // We won't do pass/fail for large here, just a demonstration.

        System.out.println("\nAll tests completed.");
    }

    /**
     * Class: StockPrediction
     * - Holds 'gain' and 'stockPrice'.
     * - Provides three overloaded 'expectedValue' methods.
     */
    static class StockPrediction {
        // 'gain' represents the profit or loss to add to 'stockPrice'
        private int gain;

        // 'stockPrice' represents the base price of the stock
        private int stockPrice;

        /**
         * Constructor to initialize 'gain' and 'stockPrice'.
         */
        public StockPrediction(int gain, int stockPrice) {
            // Store the given gain
            this.gain = gain;
            // Store the given stockPrice
            this.stockPrice = stockPrice;
        }

        /**
         * Default expectedValue() = stockPrice + gain
         *
         * @return integer result of the sum
         */
        public int expectedValue() {
            // Simply return the sum of stockPrice and gain
            return stockPrice + gain;
        }

        /**
         * Overloaded expectedValue(int transactionCosts)
         *
         * @param transactionCosts cost subtracted from the sum
         * @return integer result of (stockPrice + gain - transactionCosts)
         */
        public int expectedValue(int transactionCosts) {
            // First call the default expectedValue() then subtract transactionCosts
            return expectedValue() - transactionCosts;
        }

        /**
         * Overloaded expectedValue(String transactionCosts)
         *
         * @param transactionCosts string to be converted to integer
         * @return integer result of (stockPrice + gain - parsed transactionCosts)
         */
        public int expectedValue(String transactionCosts) {
            // Convert the string to an integer and reuse the integer version
            return expectedValue(Integer.parseInt(transactionCosts));
        }
    }
}