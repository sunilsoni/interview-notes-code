package com.interview.notes.code.year.y2024.july24.pagination;

import java.util.HashMap;
import java.util.Map;

public class PageTest3 {

    public static void main(String[] args) {
        int PAGE_SIZE = 25;
        int currentPageIndex = 2; // starting page number from 1
        int totalActiveMembers = 36;
        int totalInactiveMembers = 38;

        Map<String, Integer> result = calculateRecordsToFetch(PAGE_SIZE, currentPageIndex, totalActiveMembers, totalInactiveMembers);

        System.out.println("Active member query offset: " + result.get("activeOffset"));
        System.out.println("Active member query limit: " + result.get("activeLimit"));
        System.out.println("Inactive member query offset: " + result.get("inactiveOffset"));
        System.out.println("Inactive member query limit: " + result.get("inactiveLimit"));
    }

    public static Map<String, Integer> calculateRecordsToFetch(int pageSize, int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        int totalMembers = totalActiveMembers + totalInactiveMembers;
        int totalPages = (int) Math.ceil((double) totalMembers / pageSize);

        // Calculate the starting index based on currentPageIndex
        int startIndex = (currentPageIndex - 1) * pageSize;

        // Determine active members to fetch
        int activeStartIndex = Math.min(startIndex, totalActiveMembers);
        int activeRecordsToFetch = Math.min(pageSize, totalActiveMembers - activeStartIndex);

        // Calculate remaining slots in the page after fetching active members
        int remainingPageSize = pageSize - activeRecordsToFetch;

        // Determine inactive members to fetch
        int inactiveStartIndex = Math.max(0, startIndex - totalActiveMembers);
        int inactiveRecordsToFetch = Math.min(remainingPageSize, totalInactiveMembers - inactiveStartIndex);

        // Extra logic for edge cases
        if (currentPageIndex * pageSize > totalActiveMembers + totalInactiveMembers) {
            activeRecordsToFetch = 0;
            inactiveRecordsToFetch = 0;
        }

        // Prepare result map based on calculated offsets and limits
        Map<String, Integer> result = new HashMap<>();
        result.put("activeOffset", activeStartIndex);
        result.put("activeLimit", activeRecordsToFetch);
        result.put("inactiveOffset", inactiveStartIndex);
        result.put("inactiveLimit", inactiveRecordsToFetch);

        return result;
    }

}
