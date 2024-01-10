package com.interview.notes.code.months.year2023.Oct23.test9;

public class AirportCodes1 {

    // Check if the code can be derived from the airport name in the same order
    private boolean isValidCode(String airportName, String code) {
        int idx = 0;
        for (char c : airportName.toCharArray()) {
            if (c == code.charAt(idx)) {
                idx++;
                if (idx == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public String[] generateAirportCodes(String[] airportNames) {
        String[] result = new String[airportNames.length];

        for (int i = 0; i < airportNames.length; i++) {
            String airportName = airportNames[i];

            outer:
            for (int j = 0; j < airportName.length() - 2; j++) {
                for (int k = j + 1; k < airportName.length() - 1; k++) {
                    for (int l = k + 1; l < airportName.length(); l++) {
                        String code = "" + airportName.charAt(j) + airportName.charAt(k) + airportName.charAt(l);
                        boolean unique = true;
                        for (String otherAirport : airportNames) {
                            if (!otherAirport.equals(airportName) && isValidCode(otherAirport, code)) {
                                unique = false;
                                break;
                            }
                        }
                        if (unique) {
                            result[i] = code;
                            break outer;
                        }
                    }
                }
            }
        }

        return result;
    }
}
