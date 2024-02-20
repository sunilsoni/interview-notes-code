package com.interview.notes.code.months.jan24.test14;

import java.util.HashMap;
import java.util.Map;

public class DiscountedPriceFetcher1 {

    // Simulate the API response
    private static final Map<Integer, Integer> priceMap = new HashMap<>();
    private static final Map<Integer, Integer> discountMap = new HashMap<>();

    static {
        // Simulating the API response for barcode 74002314
        priceMap.put(74002314, 3705);
        discountMap.put(74002314, 20);

        // Add more barcodes and their corresponding price and discount if necessary
        // Example: priceMap.put(<barcode>, <price>); discountMap.put(<barcode>, <discount>);
    }

    public static int getDiscountedPrice(int barcode) {
        if (!priceMap.containsKey(barcode)) {
            return -1;
        }
        int price = priceMap.get(barcode);
        int discount = discountMap.get(barcode);
        int discountedPrice = price - (discount * price / 100);
        return Math.round(discountedPrice); // Round to nearest integer as specified
    }

    public static void main(String[] args) {
        // Example barcode 74002314
        int barcode1 = 74002314;
        System.out.println(getDiscountedPrice(barcode1));

        // Example barcode that is not in the simulated API response
        int barcode2 = 74005364;
        System.out.println(getDiscountedPrice(barcode2));

        // Add more test cases if necessary
    }
}

