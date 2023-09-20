package com.interview.notes.code.months.july23.test11;

class Solution4 {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        System.out.println(solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"})); // Output: "fl"
        System.out.println(solution.longestCommonPrefix(new String[]{"dog", "racecar", "car"})); // Output: ""

    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Find the minimum length string to set up our search boundaries.
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }

        int low = 0;
        int high = minLength;

        // Perform binary search on the minimum length string.
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return strs[0].substring(0, low);
    }

    private boolean isCommonPrefix(String[] strs, int len) {
        // Check if a substring of the first string is a common prefix.
        String prefix = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(prefix)) {
                return false;
            }
        }
        return true;
    }
}
