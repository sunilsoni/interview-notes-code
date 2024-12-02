package com.interview.notes.code.year.y2024.july24.test11;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        int PAGE_SIZE = 25;
        int currentPageIndex = 2; // starting page no from 1
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
        System.out.println("totalMembers: " + totalMembers);
        int totalPages = (int) Math.ceil((double) totalMembers / pageSize);
        System.out.println("totalPages: " + totalPages); // working fine 4
        // Calculate the starting index based on currentPageIndex (page number)
        int startIndex = (currentPageIndex - 1) * pageSize;
        System.out.println("startIndex: " + startIndex);
        // Calculate the number of records to fetch for active and inactive members
        int activeStartIndex = Math.min(startIndex, totalActiveMembers);
        System.out.println("activeStartIndex: " + activeStartIndex);
        int activeRecordsToFetch = Math.min(pageSize, totalActiveMembers - activeStartIndex);
        System.out.println("activeRecordsToFetch: " + activeRecordsToFetch);
        int remainingPageSize = pageSize - activeRecordsToFetch;
        System.out.println("remainingPageSize: " + remainingPageSize);
        int inactiveStartIndex = Math.max(0, startIndex - totalActiveMembers);
        System.out.println("inactiveStartIndex: " + inactiveStartIndex);
        int inactiveRecordsToFetch = Math.min(remainingPageSize, totalInactiveMembers - inactiveStartIndex);
        System.out.println("inactiveRecordsToFetch: " + inactiveRecordsToFetch);
        int totalRecordsToFetch = activeRecordsToFetch + inactiveRecordsToFetch;
        System.out.println("totalRecordsToFetch: " + totalRecordsToFetch);
        Map<String, Integer> result = new HashMap<>();
        result.put("activeRecordsToFetch", activeRecordsToFetch);
        result.put("inactiveRecordsToFetch", inactiveRecordsToFetch);
        return result;
    }

    public static Map<String, Integer> calculateOffsetAndLimit(int pageSize, int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        int totalMembers = totalActiveMembers + totalInactiveMembers;
        int startIndex = currentPageIndex * pageSize;
        int activeOffset = Math.min(startIndex, totalActiveMembers);
        int activeLimit = Math.min(pageSize, totalActiveMembers - activeOffset);
        int remainingPageSize = pageSize - activeLimit;
        int inactiveOffset = Math.max(0, startIndex - totalActiveMembers);
        int inactiveLimit = Math.min(remainingPageSize, totalInactiveMembers - inactiveOffset);
        Map<String, Integer> result = new HashMap<>();
        result.put("activeOffset", activeOffset);
        result.put("activeLimit", activeLimit);
        result.put("inactiveOffset", inactiveOffset);
        result.put("inactiveLimit", inactiveLimit);
        return result;
    }
}