package com.interview.notes.code.months.Oct23.test9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportCodes {

    public static void main(String[] args) {
        AirportCodes ac = new AirportCodes();
        String[] airportNames = {"NEWYORK", "NEWJERSEY", "NEWPORT", "NEUSTADT"};
        String[] generatedCodes = ac.generateAirportCodes(airportNames);

        System.out.println("Airport Codes:");
        for (int i = 0; i < airportNames.length; i++) {
            System.out.println(airportNames[i] + " -> " + generatedCodes[i]);
        }
    }

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
        String[] codes = new String[airportNames.length];
        Map<String, Integer> codeFrequency = new HashMap<>();
        Map<String, List<String>> airportCodePossibilities = new HashMap<>();

        // 1. Generate all possible codes for each airport and track frequency
        for (String airportName : airportNames) {
            for (String code : possibleCodes(airportName)) {
                codeFrequency.put(code, codeFrequency.getOrDefault(code, 0) + 1);

                if (!airportCodePossibilities.containsKey(airportName)) {
                    airportCodePossibilities.put(airportName, new ArrayList<>());
                }
                airportCodePossibilities.get(airportName).add(code);
            }
        }

        // 2. Assign unique codes to airports
        for (int i = 0; i < airportNames.length; i++) {
            for (String code : airportCodePossibilities.get(airportNames[i])) {
                if (codeFrequency.get(code) == 1) {
                    codes[i] = code;
                    break;
                }
            }
        }
        return codes;
    }
}
