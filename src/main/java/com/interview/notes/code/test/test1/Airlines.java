package com.interview.notes.code.test.test1;

import java.util.HashMap;
import java.util.Map;

/**
 * Various travel routes are available as strings (ex. "Mangalore -> Bangalore", "Bangalore -> Chennai", "Chennai -> Bhubaneshwar")
 * <p>
 * find if the given paths help us to realize a travel route
 * Good : "Bangalore->Bhubaneshwar"
 * Invalid: "Patna->Mumbai"
 */
public class Airlines {
    public static void main(String[] args) {

        //Travel routes: "Mangalore -> Bangalore", "Bangalore -> Kanyakumari", "Chennai -> Bhubaneshwar"
        String[] routes = {
                "Mangalore -> Bangalore",
                "Bangalore -> Chennai",
                "Chennai -> Bhubaneshwar"
        };

        String goodRoute = "Bangalore -> Bhubaneshwar";
        String invalidRoute = "Patna -> Mumbai";

        Map<String, String> routeMap = new HashMap<>();
        for (String route : routes) {
            String[] cities = route.split(" -> ");
            routeMap.put(cities[0], cities[1]);
        }

        System.out.println("Is good route possible? " + isRoutePossible(goodRoute, routeMap));
        System.out.println("Is invalid route possible? " + isRoutePossible(invalidRoute, routeMap));
    }

    private static boolean isRoutePossible(String targetRoute, Map<String, String> routeMap) {
        String[] cities = targetRoute.split(" -> ");
        String from = cities[0];
        String to = cities[1];

        while (routeMap.containsKey(from)) {
            if (routeMap.get(from).equals(to)) {
                return true;
            }
            from = routeMap.get(from);
        }

        return false;
    }
}
