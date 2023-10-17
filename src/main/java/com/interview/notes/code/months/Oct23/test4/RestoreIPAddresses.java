package com.interview.notes.code.months.Oct23.test4;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    public static void main(String[] args) {
        RestoreIPAddresses solution = new RestoreIPAddresses();
        String s1 = "25525511135";
        String s2 = "0000";
        String s3 = "101023";

        List<String> result1 = solution.restoreIpAddresses(s1);
        System.out.println(result1); // ["255.255.11.135","255.255.111.35"]

        List<String> result2 = solution.restoreIpAddresses(s2);
        System.out.println(result2); // ["0.0.0.0"]

        List<String> result3 = solution.restoreIpAddresses(s3);
        System.out.println(result3); // ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> validIPs = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return validIPs; // Invalid input, return empty list
        }
        restoreIPHelper(s, 0, "", 0, validIPs);
        return validIPs;
    }

    private void restoreIPHelper(String s, int start, String current, int parts, List<String> validIPs) {
        if (parts == 4 && start == s.length()) {
            validIPs.add(current);
            return;
        }

        if (parts == 4 || start == s.length()) {
            return;
        }

        for (int i = 1; i <= 3 && start + i <= s.length(); i++) {
            String part = s.substring(start, start + i);
            if ((part.length() > 1 && part.charAt(0) == '0') || Integer.parseInt(part) > 255) {
                continue; // Skip invalid parts
            }
            String newCurrent = current.isEmpty() ? part : current + "." + part;
            restoreIPHelper(s, start + i, newCurrent, parts + 1, validIPs);
        }
    }
}
