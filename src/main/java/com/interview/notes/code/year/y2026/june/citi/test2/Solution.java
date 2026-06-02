package com.interview.notes.code.year.y2026.june.citi.test2;

import java.util.ArrayList;
import java.util.List;

enum MembershipStatus {
    /*
     Membership Status is of three types: BRONZE, SILVER and GOLD.
     BRONZE is the default membership a new member gets.
     SILVER and GOLD are paid memberships for the gym.
    */
    BRONZE,
    SILVER,
    GOLD
}

class Member {
    public int memberId;
    public String name;
    public MembershipStatus membershipStatus;

    public Member(int memberId, String name, MembershipStatus membershipStatus) {
        this.memberId = memberId;
        this.name = name;
        this.membershipStatus = membershipStatus;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId +
                ", Name: " + name +
                ", Membership Status: " + membershipStatus;
    }
}

class Membership {
    /*
     Data for managing a gym membership.
    */
    public List<Member> members;

    public Membership() {
        members = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void updateMembership(int memberId, MembershipStatus membershipStatus) {
        for (Member member : members) {
            if (member.memberId == memberId) {
                member.membershipStatus = membershipStatus;
                break;
            }
        }
    }

    public MembershipStatistics getMembershipStatistics() {
        int totalMembers = members.size();
        int totalPaidMembers = 0;

        for (Member member : members) {
            // FIX: SILVER and GOLD are paid memberships.
            // Earlier code counted only GOLD, so test was failing.
            if (member.membershipStatus == MembershipStatus.SILVER ||
                member.membershipStatus == MembershipStatus.GOLD) {
                totalPaidMembers++;
            }
        }

        double conversionRate = totalMembers == 0
                ? 0.0
                : (totalPaidMembers / (double) totalMembers) * 100.0;

        return new MembershipStatistics(totalMembers, totalPaidMembers, conversionRate);
    }
}

class MembershipStatistics {
    /*
     Class for returning the getMembershipStatistics result
    */
    public int totalMembers;
    public int totalPaidMembers;
    public double conversionRate;

    public MembershipStatistics(int totalMembers, int totalPaidMembers, double conversionRate) {
        this.totalMembers = totalMembers;
        this.totalPaidMembers = totalPaidMembers;
        this.conversionRate = conversionRate;
    }
}

public class Solution {

    public static void main(String[] args) {
        testMember();
        testMembership();
        testEmptyMembership();

        System.out.println("\nAll tests completed.");
    }

    public static void testMember() {
        System.out.println("Running testMember");

        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);

        check(testMember.memberId == 1,
                "Member ID should be 1");

        check(testMember.name.equals("John Doe"),
                "Member name should be John Doe");

        check(testMember.membershipStatus == MembershipStatus.BRONZE,
                "Membership status should be BRONZE");
    }

    public static void testMembership() {
        System.out.println("Running testMembership");

        Membership testMembership = new Membership();

        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        testMembership.addMember(testMember);

        check(testMembership.members.size() == 1,
                "Members size should be 1");

        check(testMembership.members.get(0).equals(testMember),
                "First member should equal testMember");

        testMembership.updateMembership(1, MembershipStatus.SILVER);

        check(testMembership.members.get(0).membershipStatus == MembershipStatus.SILVER,
                "Membership status should be SILVER");

        Member testMember2 = new Member(2, "Alex C", MembershipStatus.BRONZE);
        testMembership.addMember(testMember2);

        Member testMember3 = new Member(3, "Marie C", MembershipStatus.GOLD);
        testMembership.addMember(testMember3);

        Member testMember4 = new Member(4, "Joe D", MembershipStatus.SILVER);
        testMembership.addMember(testMember4);

        Member testMember5 = new Member(5, "June R", MembershipStatus.BRONZE);
        testMembership.addMember(testMember5);

        Member testMember6 = new Member(6, "Westley D", MembershipStatus.SILVER);
        testMembership.addMember(testMember6);

        MembershipStatistics attendanceStats = testMembership.getMembershipStatistics();

        check(attendanceStats.totalMembers == 6,
                "Total members should be 6");

        check(attendanceStats.totalPaidMembers == 4,
                "Total paid members should be 4");

        check(Math.abs(attendanceStats.conversionRate - 66.67) < 0.1,
                "Conversion rate should be 66.67");
    }

    public static void testEmptyMembership() {
        System.out.println("Running testEmptyMembership");

        Membership membership = new Membership();
        MembershipStatistics stats = membership.getMembershipStatistics();

        check(stats.totalMembers == 0,
                "Total members should be 0");

        check(stats.totalPaidMembers == 0,
                "Total paid members should be 0");

        check(stats.conversionRate == 0.0,
                "Conversion rate should be 0.0");
    }

    private static void check(boolean condition, String message) {
        if (condition) {
            System.out.println("PASS - " + message);
        } else {
            System.out.println("FAIL - " + message);
        }
    }
}