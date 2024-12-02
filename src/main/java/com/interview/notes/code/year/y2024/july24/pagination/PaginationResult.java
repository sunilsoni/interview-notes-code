package com.interview.notes.code.year.y2024.july24.pagination;

class PaginationResult {
    public int totalMembers;
    public int totalPages;
    public int currentPageIndex;
    public int activeStartIndex;
    public int activeRecordsToFetch;
    public int inactiveStartIndex;
    public int inactiveRecordsToFetch;
    public int totalRecordsToFetch;

    @Override
    public String toString() {
        return "PaginationResult{" +
                "totalMembers=" + totalMembers +
                ", totalPages=" + totalPages +
                ", currentPageIndex=" + currentPageIndex +
                ", activeStartIndex=" + activeStartIndex +
                ", activeRecordsToFetch=" + activeRecordsToFetch +
                ", inactiveStartIndex=" + inactiveStartIndex +
                ", inactiveRecordsToFetch=" + inactiveRecordsToFetch +
                ", totalRecordsToFetch=" + totalRecordsToFetch +
                '}';
    }
}
