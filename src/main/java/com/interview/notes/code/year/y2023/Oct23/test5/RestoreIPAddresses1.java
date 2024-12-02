package com.interview.notes.code.year.y2023.Oct23.test5;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses1 {

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }
        backtrack(s, "", 0, result);
        return result;
    }

    private void backtrack(String s, String currentIP, int currentSegment, List<String> result) {
        if (currentSegment == 4 && s.isEmpty()) {
            result.add(currentIP.substring(1));  // Remove the leading dot
            return;
        }
        if (currentSegment == 4 || s.isEmpty()) {
            return;
        }
        for (int i = 1; i <= 3 && i <= s.length(); i++) {
            String segment = s.substring(0, i);
            if (isValidSegment(segment)) {
                backtrack(s.substring(i), currentIP + "." + segment, currentSegment + 1, result);
            }
        }
    }

    private boolean isValidSegment(String segment) {
        if (segment.length() > 1 && segment.startsWith("0")) {
            return false;
        }
        int value = Integer.parseInt(segment);
        return value >= 0 && value <= 255;
    }
}
