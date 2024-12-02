package com.interview.notes.code.year.y2024.jan24.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GetMatchingProducts {

    /* Complete the 'getMatchingProducts' function below.
     *
     * The function is expected to return a 2D_STRING_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_STRING_ARRAY products
     *  2. 2D_STRING_ARRAY queries
     */

    public static List<List<String>> getMatchingProducts(List<List<String>> products, List<List<String>> queries) {
        List<List<String>> result = new ArrayList<>();

        for (List<String> query : queries) {
            String type = query.get(0);
            String param = query.get(1);
            List<String> matchingProducts = new ArrayList<>();

            for (List<String> product : products) {
                String name = product.get(0);
                String price = product.get(1);
                String year = product.get(2);

                switch (type) {
                    case "Type1":
                        if (year.equals(param)) {
                            matchingProducts.add(name);
                        }
                        break;
                    case "Type2":
                        if (Integer.parseInt(price) < Integer.parseInt(param)) {
                            matchingProducts.add(name);
                        }
                        break;
                    case "Type3":
                        if (Integer.parseInt(price) > Integer.parseInt(param)) {
                            matchingProducts.add(name);
                        }
                        break;
                }
            }

            result.add(matchingProducts);
        }

        return result;
    }


    public static void main(String[] args) {
        // Example 1
        List<List<String>> products1 = new ArrayList<>();
        products1.add(Arrays.asList("item3", "3086", "1937"));
        products1.add(Arrays.asList("item2", "3435", "2082"));
        products1.add(Arrays.asList("item1", "3434", "1907"));

        List<List<String>> queries1 = new ArrayList<>();
        queries1.add(Arrays.asList("Type1", "1937"));
        queries1.add(Arrays.asList("Type3", "3434"));
        queries1.add(Arrays.asList("Type3", "3435"));
        queries1.add(Arrays.asList("Type2", "2082"));
        queries1.add(Arrays.asList("Type1", "1907"));

        List<List<String>> matchingProducts1 = getMatchingProducts(products1, queries1);
        System.out.println("Example 1 Results: " + matchingProducts1);

        // Example 2
        List<List<String>> products2 = new ArrayList<>();
        products2.add(Arrays.asList("item2", "510", "1947"));
        products2.add(Arrays.asList("item1", "4139", "1970"));
        products2.add(Arrays.asList("item3", "2089", "2097"));

        List<List<String>> queries2 = new ArrayList<>();
        queries2.add(Arrays.asList("Type3", "2089"));
        queries2.add(Arrays.asList("Type2", "4139"));
        queries2.add(Arrays.asList("Type2", "510"));
        queries2.add(Arrays.asList("Type1", "1970"));
        queries2.add(Arrays.asList("Type1", "2097"));

        List<List<String>> matchingProducts2 = getMatchingProducts(products2, queries2);
        System.out.println("Example 2 Results: " + matchingProducts2);
    }
}
