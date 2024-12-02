package com.interview.notes.code.year.y2024.july24.pagination;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        int PAGE_SIZE = 25;
        int totalActiveMembers = 36;
        int totalInactiveMembers = 38;

        for (int currentPageIndex = 1; currentPageIndex <= 3; currentPageIndex++) {
            Map<String, Integer> result = calculateOffsetAndLimit(PAGE_SIZE, currentPageIndex, totalActiveMembers, totalInactiveMembers);
            System.out.println("Page " + currentPageIndex + ":");
            System.out.println("Active member query offset: " + result.get("activeOffset"));
            System.out.println("Active member query limit: " + result.get("activeLimit"));
            System.out.println("Inactive member query offset: " + result.get("inactiveOffset"));
            System.out.println("Inactive member query limit: " + result.get("inactiveLimit"));
            System.out.println("-----");
        }
    }

    public static Map<String, Integer> calculateOffsetAndLimit(int pageSize, int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        int totalMembers = totalActiveMembers + totalInactiveMembers;
        int totalPages = (int) Math.ceil((double) totalMembers / pageSize);

        // Calculate the starting index based on currentPageIndex (page number)
        int startIndex = (currentPageIndex - 1) * pageSize;

        // Calculate the number of records to fetch for active members
        int activeOffset = Math.min(startIndex, totalActiveMembers);
        int activeLimit = Math.min(pageSize, totalActiveMembers - activeOffset);

        // Calculate the remaining page size after fetching active records
        int remainingPageSize = pageSize - activeLimit;

        // Calculate the offset and limit for inactive members
        int inactiveOffset = Math.max(0, startIndex - totalActiveMembers);
        int inactiveLimit = Math.min(remainingPageSize, totalInactiveMembers - inactiveOffset);

        // Prepare the result map
        Map<String, Integer> result = new HashMap<>();
        result.put("activeOffset", activeOffset);
        result.put("activeLimit", activeLimit);
        result.put("inactiveOffset", inactiveOffset);
        result.put("inactiveLimit", inactiveLimit);

        return result;
    }
}
