package com.interview.notes.code.months.july24.pagination;

public class PaginationCalculator {
    private static final int PAGE_SIZE = 25;

    public static PaginationResult calculatePagination(int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        PaginationResult result = new PaginationResult();

        result.totalMembers = totalActiveMembers + totalInactiveMembers;
        result.totalPages = (int) Math.ceil((double) result.totalMembers / PAGE_SIZE);
        result.currentPageIndex = currentPageIndex;

        int totalOffset = (currentPageIndex - 1) * PAGE_SIZE;

        if (totalOffset < totalActiveMembers) {
            result.activeStartIndex = totalOffset;
            result.activeRecordsToFetch = Math.min(PAGE_SIZE, totalActiveMembers - totalOffset);

            int remainingSpace = PAGE_SIZE - result.activeRecordsToFetch;
            result.inactiveStartIndex = 0;
            result.inactiveRecordsToFetch = Math.min(remainingSpace, totalInactiveMembers);
        } else {
            result.activeStartIndex = 0;
            result.activeRecordsToFetch = 0;

            result.inactiveStartIndex = totalOffset - totalActiveMembers + 1;
            result.inactiveRecordsToFetch = Math.min(PAGE_SIZE, totalInactiveMembers - (result.inactiveStartIndex - 1));
        }

        result.totalRecordsToFetch = result.activeRecordsToFetch + result.inactiveRecordsToFetch;

        return result;
    }


    public static PaginationResult calculatePagination5(int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        PaginationResult result = new PaginationResult();

        result.totalMembers = totalActiveMembers + totalInactiveMembers;
        result.totalPages = (int) Math.ceil((double) result.totalMembers / PAGE_SIZE);
        result.currentPageIndex = currentPageIndex;

        int totalOffset = (currentPageIndex - 1) * PAGE_SIZE;

        if (totalOffset < totalActiveMembers) {
            result.activeStartIndex = totalOffset;
            result.activeRecordsToFetch = Math.min(PAGE_SIZE, totalActiveMembers - totalOffset);

            int remainingSpace = PAGE_SIZE - result.activeRecordsToFetch;
            result.inactiveStartIndex = 0;
            result.inactiveRecordsToFetch = Math.min(remainingSpace, totalInactiveMembers);
        } else {
            result.activeStartIndex = 0;
            result.activeRecordsToFetch = 0;

            result.inactiveStartIndex = totalOffset - totalActiveMembers + 1;
            result.inactiveRecordsToFetch = Math.min(PAGE_SIZE, totalInactiveMembers - (result.inactiveStartIndex - 1));
        }

        result.totalRecordsToFetch = result.activeRecordsToFetch + result.inactiveRecordsToFetch;

        return result;
    }


    public static PaginationResult calculatePagination4(int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        PaginationResult result = new PaginationResult();

        result.totalMembers = totalActiveMembers + totalInactiveMembers;
        result.totalPages = (int) Math.ceil((double) result.totalMembers / PAGE_SIZE);
        result.currentPageIndex = currentPageIndex;

        int totalOffset = (currentPageIndex - 1) * PAGE_SIZE;

        if (totalOffset < totalActiveMembers) {
            result.activeStartIndex = totalOffset;
            result.activeRecordsToFetch = Math.min(PAGE_SIZE, totalActiveMembers - totalOffset);

            int remainingSpace = PAGE_SIZE - result.activeRecordsToFetch;
            result.inactiveStartIndex = 0;
            result.inactiveRecordsToFetch = Math.min(remainingSpace, totalInactiveMembers);
        } else {
            result.activeStartIndex = 0;
            result.activeRecordsToFetch = 0;

            int inactiveOffset = totalOffset - totalActiveMembers;
            result.inactiveStartIndex = inactiveOffset;
            result.inactiveRecordsToFetch = Math.min(PAGE_SIZE, totalInactiveMembers - inactiveOffset);
        }

        result.totalRecordsToFetch = result.activeRecordsToFetch + result.inactiveRecordsToFetch;

        return result;
    }

    public static PaginationResult calculatePagination3(int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        PaginationResult result = new PaginationResult();

        result.totalMembers = totalActiveMembers + totalInactiveMembers;
        result.totalPages = (int) Math.ceil((double) result.totalMembers / PAGE_SIZE);
        result.currentPageIndex = currentPageIndex;

        int totalOffset = (currentPageIndex - 1) * PAGE_SIZE;

        if (totalOffset < totalActiveMembers) {
            result.activeStartIndex = totalOffset;
            result.activeRecordsToFetch = Math.min(PAGE_SIZE, totalActiveMembers - totalOffset);

            int remainingSpace = PAGE_SIZE - result.activeRecordsToFetch;
            result.inactiveStartIndex = 0;
            result.inactiveRecordsToFetch = Math.min(remainingSpace, totalInactiveMembers);
        } else {
            result.activeStartIndex = totalActiveMembers;
            result.activeRecordsToFetch = 0;

            int inactiveOffset = totalOffset - totalActiveMembers;
            result.inactiveStartIndex = inactiveOffset;
            result.inactiveRecordsToFetch = Math.min(PAGE_SIZE, totalInactiveMembers - inactiveOffset);
        }

        result.totalRecordsToFetch = result.activeRecordsToFetch + result.inactiveRecordsToFetch;

        return result;
    }

    public static PaginationResult calculatePagination2(int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        PaginationResult result = new PaginationResult();

        result.totalMembers = totalActiveMembers + totalInactiveMembers;
        result.totalPages = (int) Math.ceil((double) result.totalMembers / PAGE_SIZE);
        result.currentPageIndex = currentPageIndex;

        int offset = (currentPageIndex - 1) * PAGE_SIZE;

        if (offset < totalActiveMembers) {
            result.activeStartIndex = offset;
            result.activeRecordsToFetch = Math.min(PAGE_SIZE, totalActiveMembers - offset);

            int remainingPageSize = PAGE_SIZE - result.activeRecordsToFetch;
            result.inactiveStartIndex = 0;
            result.inactiveRecordsToFetch = Math.min(remainingPageSize, totalInactiveMembers);
        } else {
            result.activeStartIndex = totalActiveMembers;
            result.activeRecordsToFetch = 0;

            int inactiveOffset = offset - totalActiveMembers;
            result.inactiveStartIndex = inactiveOffset;
            result.inactiveRecordsToFetch = Math.min(PAGE_SIZE, totalInactiveMembers - inactiveOffset);
        }

        result.totalRecordsToFetch = result.activeRecordsToFetch + result.inactiveRecordsToFetch;

        return result;
    }

    public static PaginationResult calculatePagination1(int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        PaginationResult result = new PaginationResult();

        result.totalMembers = totalActiveMembers + totalInactiveMembers;
        result.totalPages = (int) Math.ceil((double) result.totalMembers / PAGE_SIZE);
        result.currentPageIndex = currentPageIndex;

        int offset = (currentPageIndex - 1) * PAGE_SIZE;

        if (offset < totalActiveMembers) {
            result.activeStartIndex = offset;
            result.activeRecordsToFetch = Math.min(PAGE_SIZE, totalActiveMembers - offset);

            int remainingPageSize = PAGE_SIZE - result.activeRecordsToFetch;
            result.inactiveStartIndex = 0;
            result.inactiveRecordsToFetch = Math.min(remainingPageSize, totalInactiveMembers);
        } else {
            result.activeStartIndex = totalActiveMembers;
            result.activeRecordsToFetch = 0;

            int inactiveOffset = offset - totalActiveMembers;
            result.inactiveStartIndex = inactiveOffset;
            result.inactiveRecordsToFetch = Math.min(PAGE_SIZE, totalInactiveMembers - inactiveOffset);
        }

        result.totalRecordsToFetch = result.activeRecordsToFetch + result.inactiveRecordsToFetch;

        return result;
    }

    public static void main(String[] args) {
        int totalActiveMembers = 36;
        int totalInactiveMembers = 38;

        for (int i = 1; i <= 3; i++) {
            PaginationResult result = calculatePagination(i, totalActiveMembers, totalInactiveMembers);
            System.out.println("Page " + i + ": " + result);
        }
    }
}
