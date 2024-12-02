package com.interview.notes.code.year.y2023.june23.test11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

1. Price Check

There is a shop with old-style cash registers. Rather
than scanning items and pulling the price from a
database, the price of each item is typed in manually.
This method sometimes leads to errors. Given a list
of items and their correct prices, compare the prices
to those entered when each item was sold.
Determine the number of errors in selling prices.

Example

products  = ['eggs', 'milk', 'cheese']
productPrices = [2.89, 3.29, 5.79]
productSold = ['eggs', 'eggs', 'cheese', 'milk']
sold Price = [2.89, 2.99, 5.97, 3.29].



Product    PriceActual    Expected   Error
eggs       2.89           2.89
cheese     2.99           2.89       1
eggs       5.97           5.79       1
milk       3.29           3.29


The second sale of eggs has a wrong price, as does
the sale of cheese. There are 2 errors in pricing.


Function Description
Complete the function priceCheck in the editor below.

 */
public class PriceCheck {

    public static void main(String[] args) {
        List<String> products = Arrays.asList("eggs", "milk", "cheese");
        List<Float> productPrices = Arrays.asList(2.89f, 3.29f, 5.79f);
        List<String> productSold = Arrays.asList("eggs", "eggs", "cheese", "milk");
        List<Float> soldPrice = Arrays.asList(2.89f, 2.99f, 5.97f, 3.29f);

        System.out.println(priceCheck1(products, productPrices, productSold, soldPrice));

        System.out.println(priceCheck2(products, productPrices, productSold, soldPrice));

    }

    /**
     * The above Java solution to the pricing error problem uses a HashMap to efficiently check the price discrepancies. Here is a step-by-step explanation of the solution:
     * <p>
     * 1. First, a HashMap is created where each product name is a key and its corresponding price is the value. This operation takes O(n) time complexity where n is the number of products. This is because we are iterating through each product once to create the HashMap.
     * <p>
     * 2. Next, the program iterates through the list of sold products and their corresponding prices. For each product sold, the solution checks if the price it was sold at matches the price in the HashMap. Since HashMap operations (like get and put) have a time complexity of O(1), this step also takes O(m) time complexity, where m is the number of sold products.
     * <p>
     * 3. If there is a discrepancy between the price at which the product was sold and the actual price of the product, it increments an error counter.
     * <p>
     * 4. Finally, the program returns the number of errors which is a constant time operation, O(1).
     * <p>
     * Therefore, the overall time complexity of the solution is O(n + m), where n is the number of products and m is the number of sold products. This is because the creation of the HashMap and the iteration through the sold products are the most costly operations in terms of time complexity.
     * <p>
     * The space complexity is O(n) because we are storing all products and their prices in a HashMap. The other variables used (like the error counter and the iterator in the loop) only take constant space.
     *
     * @param products
     * @param productPrices
     * @param productSold
     * @param soldPrice
     * @return
     */
    public static int priceCheck1(List<String> products, List<Float> productPrices, List<String> productSold, List<Float> soldPrice) {
        Map<String, Float> productPriceMap = new HashMap<>();

        for (int i = 0; i < products.size(); i++) {
            productPriceMap.put(products.get(i), productPrices.get(i));
        }

        int errors = 0;

        for (int i = 0; i < productSold.size(); i++) {
            if (!productPriceMap.get(productSold.get(i)).equals(soldPrice.get(i))) {
                errors++;
            }
        }

        return errors;
    }

    public static int priceCheck2(List<String> products, List<Float> productprices, List<String> productSold, List<Float> soldPrice) {
        Map<String, Float> actualPricesMap = new HashMap<>();
        int errors = 0;
        for (int i = 0; i < products.size(); i++) {
            String k = products.get(i);
            Float v = productprices.get(i);
            actualPricesMap.put(k, v);
        }
        for (int i = 0; i < productSold.size(); i++) {
            String k = productSold.get(i);
            Float v = soldPrice.get(i);
            int d = v.compareTo(actualPricesMap.get(k));
            if (d != 0) errors++;
        }
        return errors;
    }
}

