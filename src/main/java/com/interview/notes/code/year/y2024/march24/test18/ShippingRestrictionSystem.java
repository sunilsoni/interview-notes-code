package com.interview.notes.code.year.y2024.march24.test18;

/**
 * Amazon.com sells millions of merchandise globally, but sometimes, due to various reasons (legal, vendor requests, etc.), some items
 * have restrictions on where they can be sold. For example, Apple products can only be sold domestically because Apple has different
 * pricing strategies in different countries. Therefore, we need a shipping restriction system to limit where an order can be shipped based on the order's shipping address.|
 * The core functionalities of the shipping restriction system are as follows:
 * * The ability to manage geographical areas based on attributes from a shipping address (such as city, state, country, zipcode, etc.).
 * * Logic to check if a given address falls within a specific geographical area.
 * Today, we are going to implement this component. Your task is to design and implement proper interfaces, classes, or functions to achieve this goal.
 * Let's begin with a simple use case:
 * * Design interfaces or classes to represent geographical areas at the zipcode level.
 * * Write code to create an instance of a geographical area using the code from above step for the zipcode range: 198001, 98006] -
 * * Write code to evaluate if a shipping address falls within the given area.
 * * input address: 123 45th Ave, Some City, 98011, US
 * Please ensure that your code is written in a flexible manner so that we can easily add support for additional (such as state names, country codes, or combinations of them).
 */
// Main class with the main method
public class ShippingRestrictionSystem {
    public static void main(String[] args) {
        // Create an instance of a geographical area with the specified zipcode range.
        int startZipcode = 198001;
        int endZipcode = 98006;
        ZipcodeRange zipcodeArea = new ZipcodeRange(startZipcode, endZipcode);

        // Output the range for verification
        System.out.println("Created a geographical area with the zipcode range: [" +
                startZipcode + ", " + endZipcode + "]");
    }
}