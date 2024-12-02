package com.interview.notes.code.year.y2024.july24.test11;

import java.util.HashMap;
import java.util.Map;

public class PaginationTest {
    public static void main(String[] args) {
        int PAGE_SIZE = 25;
        int totalActiveMembers = 36;
        int totalInactiveMembers = 38;

        for (int currentPageIndex = 1; currentPageIndex <= 3; currentPageIndex++) {
            System.out.println("Page " + currentPageIndex + ":");
            Map<String, Integer> result = calculatePagination(PAGE_SIZE, currentPageIndex, totalActiveMembers, totalInactiveMembers);
            System.out.println("Active member query offset: " + result.get("activeOffset"));
            System.out.println("Active member query limit: " + result.get("activeLimit"));
            System.out.println("Inactive member query offset: " + result.get("inactiveOffset"));
            System.out.println("Inactive member query limit: " + result.get("inactiveLimit"));
            System.out.println("Total records to fetch: " + result.get("totalRecordsToFetch"));
            System.out.println();
        }
    }

    public static Map<String, Integer> calculatePagination(int pageSize, int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        int totalMembers = totalActiveMembers + totalInactiveMembers;
        int totalPages = (int) Math.ceil((double) totalMembers / pageSize);
        int startIndex = (currentPageIndex - 1) * pageSize;

        int activeOffset = Math.min(startIndex, totalActiveMembers);
        int activeLimit = Math.min(pageSize, Math.max(0, totalActiveMembers - activeOffset));

        int remainingPageSize = pageSize - activeLimit;
        int inactiveOffset = Math.max(0, startIndex - totalActiveMembers);
        int inactiveLimit = Math.min(remainingPageSize, totalInactiveMembers - inactiveOffset);

        int totalRecordsToFetch = activeLimit + inactiveLimit;

        Map<String, Integer> result = new HashMap<>();
        result.put("totalMembers", totalMembers);
        result.put("totalPages", totalPages);
        result.put("startIndex", startIndex);
        result.put("activeOffset", activeOffset);
        result.put("activeLimit", activeLimit);
        result.put("remainingPageSize", remainingPageSize);
        result.put("inactiveOffset", inactiveOffset);
        result.put("inactiveLimit", inactiveLimit);
        result.put("totalRecordsToFetch", totalRecordsToFetch);

        return result;
    }
}
