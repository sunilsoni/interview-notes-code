package com.interview.notes.code.test.test2;

import java.util.*;

public class Airlines {
    public static void main(String[] args) {
        // Available travel routes
        Set<String> routes = new HashSet<>(Arrays.asList(
            "Mangalore -> Bangalore",
            "Bangalore -> Chennai",
            "Chennai -> Bhubaneshwar"
        ));
        
        // Check if a given route is valid
        String route1 = "Bangalore -> Bhubaneshwar";
        String route2 = "Patna -> Mumbai";
        
        boolean isRoute1Valid = routes.contains(route1);
        boolean isRoute2Valid = routes.contains(route2);
        
        System.out.println(route1 + " is " + (isRoute1Valid ? "valid" : "invalid"));//Bangalore -> Bhubaneshwar is invalid
        System.out.println(route2 + " is " + (isRoute2Valid ? "valid" : "invalid"));//Patna -> Mumbai is invalid
    }
}
