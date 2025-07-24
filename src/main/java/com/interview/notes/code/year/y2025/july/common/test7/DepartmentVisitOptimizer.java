package com.interview.notes.code.year.y2025.july.common.test7;/*
 * Java 8 Version
 * DepartmentVisitOptimizer: calculates how many department visits you save by grouping shopping list items
 */
import java.util.*;               // import utilities like List, Map, Arrays
import java.util.stream.*;        // import Stream API for processing collections

public class DepartmentVisitOptimizer {
    public static void main(String[] args) {
        // define products with their departments as a list of string arrays
        List<String[]> products = Arrays.asList(new String[][] {
            {"Cheese",          "Dairy"},
            {"Carrots",         "Produce"},  
            {"Potatoes",        "Produce"},  
            {"Canned Tuna",     "Pantry"},  
            {"Romaine Lettuce", "Produce"},
            {"Chocolate Milk",  "Dairy"},   
            {"Flour",           "Pantry"},  
            {"Iceberg Lettuce", "Produce"},
            {"Coffee",          "Pantry"},  
            {"Pasta",           "Pantry"},  
            {"Milk",            "Dairy"},   
            {"Blueberries",     "Produce"},
            {"Pasta Sauce",     "Pantry"}
        });                                           
        
        // build a map from product name to department using Stream API
        Map<String, String> deptMap = products.stream()     
            .collect(Collectors.toMap(item -> item[0],    // map key: product name
                                      item -> item[1]));  // map value: department

        // example shopping list from problem statement
        List<String> shoppingList1 = Arrays.asList(
            "Blueberries", "Milk", "Coffee", "Flour", "Cheese", "Carrots"
        );  
        // run the example test and expect 2 departments saved
        runTest("Example Test", shoppingList1, deptMap, 2);

        // test with a single item: no visits saved (1 original vs 1 optimized)
        List<String> singleItemList = Collections.singletonList("Milk");
        runTest("Single Item Test", singleItemList, deptMap, 0);

        // test with repeated department changes: should save 2 visits
        List<String> patternList = Arrays.asList(
            "Cheese", "Carrots", "Cheese", "Carrots", "Cheese"
        );  
        // here departments: Dairy->Produce->Dairy->Produce->Dairy (5 vs 2 visits)
        runTest("Pattern Test", patternList, deptMap, 5 - 2);

        // large data test: 10000 items alternating between Milk (Dairy) and Coffee (Pantry)
        int N = 10000;                                                                                  
        List<String> largeList = IntStream.range(0, N)
            .mapToObj(i -> i % 2 == 0 ? "Milk" : "Coffee")  // alternate product names
            .collect(Collectors.toList());                     
        // expected saved visits = original visits (10000) - optimized visits (2)
        runTest("Large Data Test", largeList, deptMap, N - 2);
    }

    // method to calculate number of department visits in original shopping order
    public static long calculateDeptVisits(List<String> shoppingList, Map<String, String> deptMap) {
        return IntStream.range(0, shoppingList.size())             // create a stream of indices
            .filter(i -> i == 0                                          // include first item always
                      || !deptMap.get(shoppingList.get(i))               // if current department
                             .equals(deptMap.get(shoppingList.get(i-1)))) // differs from previous
            .count();                                                   // count how many times condition is true
    }

    // method to calculate number of visits when grouping by department
    public static long calculateOptimizedVisits(List<String> shoppingList, Map<String, String> deptMap) {
        return shoppingList.stream()                     // stream all shopping items
            .map(deptMap::get)                           // map each item to its department
            .distinct()                                  // remove duplicate departments
            .count();                                    // count unique departments
    }

    // helper method to run a test and print PASS/FAIL with details
    public static void runTest(String testName, List<String> shoppingList,
                               Map<String, String> deptMap, long expectedSaved) {
        long original = calculateDeptVisits(shoppingList, deptMap);       // visits in original order
        long optimized = calculateOptimizedVisits(shoppingList, deptMap); // visits when grouped
        long saved = original - optimized;                                // visits eliminated
        String result = (saved == expectedSaved) ? "PASS" : "FAIL";    // compare with expected
        System.out.println(testName + ": " + result);                  // print test result
        System.out.println("  Original Visits: " + original);          // show original count
        System.out.println("  Optimized Visits: " + optimized);        // show optimized count
        System.out.println("  Visits Saved: " + saved);                // show difference
    }
}