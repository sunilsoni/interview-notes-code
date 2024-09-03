package com.interview.notes.code.months.aug24.test32;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPv4Validator {

    // Regular expression to validate IPv4 addresses
    final static String regularExpression = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.)?){3}" +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    public static void main(String[] args) {
        // Test Case 1
        String[] testCase1 = {
                "000.12.12.034",
                "121.234.12.12",
                "23.45.12.56",
                "00.12.123.123123.123",
                "122.23",
                "Hello.IP"
        };
        System.out.println("Test Case 1 Results:");
        for (String ip : testCase1) {
            System.out.println(validateIPAddress(ip));
        }

        // Test Case 2
        String[] testCase2 = {
                "222.030.151.166",
                "186.116.66.090",
                "56.224.195.010",
                "59.153.14.022",
                "65.217.244.236",
                "124.255.114.193",
                "253.110.43.190",
                "768540180.9506559.974145554.987289718",
                "499235237.88055889.436631985.957967894.957967894",
                "475974065.826049509.610050395.822874346.822874346",
                "74748765.592816709.897246293.334470435",
                "561983629.88164781.712443660.543233267",
                "82659376.679462273.361182727.522428069",
                "562551020.186224077.361204300.858079639",
                "199394305.598510508.83518696.863140814.863140814",
                ".1.1.",
                ".1.1.1",
                "1.1..1",
                "1...",
                "999.999.99.9",
                "111.111.1.1.11",
                "001a100b11.123",
                "100x234.255.0",
                "0000.00.00.00",
                "0034.0.0.0"
        };
        System.out.println("\nTest Case 2 Results:");
        for (String ip : testCase2) {
            System.out.println(validateIPAddress(ip));
        }
    }

    public static String validateIPAddress(String ip) {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(ip);
        if (matcher.matches()) {
            return "Valid";
        } else {
            return "Invalid";
        }
    }
}
