package com.interview.notes.code.months.july24.pagination;

import java.util.HashMap;
import java.util.Map;

public class PageTest1 {
    public static void main(String[] args) {
        int PAGE_SIZE = 25;
        int currentPageIndex = 3; // starting page number from 1
        int totalActiveMembers = 36;
        int totalInactiveMembers = 38;
        Map<String, Integer> result = calculateOffsetAndLimit(PAGE_SIZE, currentPageIndex, totalActiveMembers, totalInactiveMembers);
        System.out.println("Active member query offset: " + result.get("activeOffset"));
        System.out.println("Active member query limit: " + result.get("activeLimit"));
        System.out.println("Inactive member query offset: " + result.get("inactiveOffset"));
        System.out.println("Inactive member query limit: " + result.get("inactiveLimit"));
    }

    public static Map<String, Integer> calculateOffsetAndLimit(int pageSize, int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        int totalMembers = totalActiveMembers + totalInactiveMembers;
        int totalPages = (int) Math.ceil((double) totalMembers / pageSize);
        // Calculate the starting index based on currentPageIndex (page number)
        int startIndex = (currentPageIndex - 1) * pageSize;
        // Calculate the number of records to fetch for active members
        int activeOffset = Math.min(startIndex, totalActiveMembers);
        int activeLimit = (startIndex < totalActiveMembers) ? Math.min(pageSize, totalActiveMembers - activeOffset) : 0;
        // Calculate the number of records to fetch for inactive members
        int inactiveOffset = (startIndex < totalActiveMembers) ? 0 : startIndex - totalActiveMembers;
        int remainingPageSize = pageSize - activeLimit;
        // If the active list is exhausted, all remaining page size is for inactive members
        int inactiveLimit = (startIndex < totalActiveMembers) ? Math.min(remainingPageSize, totalInactiveMembers) : Math.min(pageSize, totalInactiveMembers - inactiveOffset);

        Map<String, Integer> result = new HashMap<>();
        result.put("activeOffset", activeOffset);
        result.put("activeLimit", activeLimit);
        result.put("inactiveOffset", inactiveOffset);
        result.put("inactiveLimit", inactiveLimit);
        return result;
    }
}
