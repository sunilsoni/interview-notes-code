package com.interview.notes.code.months.july24.pagination;

import java.util.ArrayList;
import java.util.List;

public class PaginationExample1 {
    private static final int PAGE_SIZE = 25;

    public static void main(String[] args) {
        int currentPageIndex = 1;
        int totalActiveMembers = 36;//36-25=11
        int totalInactiveMembers = 38;//11-25=14=> 38-14=24

        List<Member> members = fetchMembersForPage(currentPageIndex, totalActiveMembers, totalInactiveMembers);
        System.out.println("Page " + currentPageIndex + " Members fetched: " + members.size());
        for (Member member : members) {
            System.out.println(member);
        }

        currentPageIndex = 2;
        members = fetchMembersForPage(currentPageIndex, totalActiveMembers, totalInactiveMembers);
        System.out.println("\nPage " + currentPageIndex + " Members fetched: " + members.size());
        for (Member member : members) {
            System.out.println(member);
        }

        currentPageIndex = 3;
        members = fetchMembersForPage(currentPageIndex, totalActiveMembers, totalInactiveMembers);
        System.out.println("\nPage " + currentPageIndex + " Members fetched: " + members.size());
        for (Member member : members) {
            System.out.println(member);
        }
    }

    private static List<Member> fetchMembersForPage(int currentPageIndex, int totalActiveMembers, int totalInactiveMembers) {
        List<Member> members = new ArrayList<>();
        List<Member> activeMembersList = fetchActiveMembersData(totalActiveMembers);
        List<Member> inactiveMembersList = fetchInactiveMembersData(totalInactiveMembers);

        int activeStartIndex = (currentPageIndex - 1) * PAGE_SIZE + 1;
        int inactiveStartIndex = Math.max(1, activeStartIndex - totalActiveMembers);
        int activeEndIndex = Math.min(totalActiveMembers, activeStartIndex + PAGE_SIZE - 1);
        int inactiveEndIndex = inactiveStartIndex + PAGE_SIZE - (activeEndIndex - activeStartIndex + 1);

        members.addAll(getPagedMembers(activeMembersList, activeStartIndex, activeEndIndex));
        members.addAll(getPagedMembers(inactiveMembersList, inactiveStartIndex, inactiveEndIndex));

        return members;
    }

    private static List<Member> getPagedMembers(List<Member> membersList, int startIndex, int endIndex) {
        List<Member> pagedMembers = new ArrayList<>();
        for (int i = startIndex - 1; i < endIndex; i++) {
            if (i < membersList.size()) {
                pagedMembers.add(membersList.get(i));
            }
        }
        return pagedMembers;
    }

    private static List<Member> fetchActiveMembersData(int totalActiveMembers) {
        List<Member> activeMembersList = new ArrayList<>();
        for (int i = 0; i < totalActiveMembers; i++) {
            activeMembersList.add(new Member(i + 1, "Active Member " + (i + 1), false));
        }
        return activeMembersList;
    }

    private static List<Member> fetchInactiveMembersData(int totalInactiveMembers) {
        List<Member> inactiveMembersList = new ArrayList<>();
        for (int i = 0; i < totalInactiveMembers; i++) {
            inactiveMembersList.add(new Member(totalInactiveMembers + i + 1, "Inactive Member " + (i + 1), true));
        }
        return inactiveMembersList;
    }

    private static class Member {
        private int id;
        private String name;
        private boolean deactivated;

        public Member(int id, String name, boolean deactivated) {
            this.id = id;
            this.name = name;
            this.deactivated = deactivated;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", deactivated=" + deactivated +
                    '}';
        }
    }
}
