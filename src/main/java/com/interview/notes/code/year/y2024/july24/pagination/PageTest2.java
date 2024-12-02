package com.interview.notes.code.year.y2024.july24.pagination;

import java.util.HashMap;
import java.util.Map;

public class PageTest2 {
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
        int startIndex = (currentPageIndex - 1) * pageSize;
        int activeOffset = Math.min(startIndex, totalActiveMembers);
        int activeLimit = Math.min(pageSize, totalActiveMembers - activeOffset);

        int inactiveOffset = Math.max(0, startIndex - totalActiveMembers);
        int remainingPageSize = pageSize - activeLimit;
        int inactiveLimit = Math.min(remainingPageSize, totalInactiveMembers - inactiveOffset);

        Map<String, Integer> result = new HashMap<>();
        result.put("activeOffset", activeOffset);
        result.put("activeLimit", activeLimit);
        result.put("inactiveOffset", inactiveOffset);
        result.put("inactiveLimit", inactiveLimit);
        return result;
    }

    public static Map<String, Integer> calculateOffsetAndLimit1(int pageSize, int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        int startIndex = (currentPageIndex - 1) * pageSize;
        int activeOffset = Math.min(startIndex, totalActiveMembers);
        int activeLimit = Math.min(pageSize, totalActiveMembers - activeOffset);

        int inactiveOffset = Math.max(0, startIndex - totalActiveMembers);
        inactiveOffset = (activeLimit < pageSize) ? inactiveOffset : Math.min(inactiveOffset, totalInactiveMembers);
        int inactiveLimit = Math.max(0, pageSize - activeLimit);
        inactiveLimit = Math.min(inactiveLimit, totalInactiveMembers - inactiveOffset);

        Map<String, Integer> result = new HashMap<>();
        result.put("activeOffset", activeOffset);
        result.put("activeLimit", activeLimit);
        result.put("inactiveOffset", inactiveOffset);
        result.put("inactiveLimit", inactiveLimit);
        return result;
    }
}
