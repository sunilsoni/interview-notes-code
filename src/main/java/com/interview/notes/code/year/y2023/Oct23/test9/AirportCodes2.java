package com.interview.notes.code.year.y2023.Oct23.test9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implement class AirportCodes
 * method
 * public String[] generateAirportCodes(String[] airportNames) {
 * <p>
 * <p>
 * <p>
 * }
 * input will be AirportNames
 * <p>
 * without blankspaces and all letters in uppercase
 * <p>
 * each AirtportName will be atleast 3 chars to 30 chars (inclusive) long
 * <p>
 * You are required to implement the method generateAirportCodes with following constraints
 * <p>
 * Each airport code should be 3 unique chars appearing in the same order in the AirportName (may or may not be consecutive).
 * <p>
 * Each airport code should be unique across the given set of airportNames in the input criteria. That is it should NOT be possible to generate the same code for a different airport name in the given array of airportNames.
 */
public class AirportCodes2 {

    public static void main(String[] args) {
        AirportCodes2 ac = new AirportCodes2();

        // String[] airportNames = {"LONDON", "NEWYORK", "LOSANGELES", "SANFRANCISCO", "BOSTON"};

        String[] airportNames = {"NEWYORK", "NEWJERSEY", "NEWPORT", "NEUSTADT"};

        String[] codes = ac.generateAirportCodes(airportNames);

        System.out.println("Airport Codes:");
        for (int i = 0; i < airportNames.length; i++) {
            System.out.println(airportNames[i] + " -> " + codes[i]);
        }
    }

    // Generate all possible codes for a given airport name
    private List<String> possibleCodes(String airportName) {
        List<String> codes = new ArrayList<>();

        for (int j = 0; j < airportName.length() - 2; j++) {
            for (int k = j + 1; k < airportName.length() - 1; k++) {
                for (int l = k + 1; l < airportName.length(); l++) {
                    codes.add("" + airportName.charAt(j) + airportName.charAt(k) + airportName.charAt(l));
                }
            }
        }

        return codes;
    }

    public String[] generateAirportCodes(String[] airportNames) {
        Set<String> usedCodes = new HashSet<>();
        String[] result = new String[airportNames.length];

        for (int i = 0; i < airportNames.length; i++) {
            String airportName = airportNames[i];
            List<String> codes = possibleCodes(airportName);

            boolean found = false;
            for (String code : codes) {
                if (!usedCodes.contains(code)) {
                    usedCodes.add(code);
                    result[i] = code;
                    found = true;
                    break;
                }
            }

            if (!found) {
                result[i] = "N/A";  // Or any fallback mechanism if you want
            }
        }

        return result;
    }
}
