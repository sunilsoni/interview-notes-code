package com.interview.notes.code.year.y2026.june.citi.test1;

import java.util.ArrayList;
import java.util.List;

enum MembershipStatus {
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
        return "Member ID: " + memberId + ", Name: " + name + ", Membership Status: " + membershipStatus;
    }
}

class Membership {
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
            // FIXED: Now checks for both GOLD and SILVER memberships
            if (member.membershipStatus == MembershipStatus.GOLD || member.membershipStatus == MembershipStatus.SILVER) {
                totalPaidMembers++;
            }
        }
        double conversionRate = (totalPaidMembers / (double) totalMembers) * 100.0;
        return new MembershipStatistics(totalMembers, totalPaidMembers, conversionRate);
    }
}

class MembershipStatistics {
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
        System.out.println("Success: All tests passed!");
    }

    public static void testMember() {
        System.out.println("Running testMember");
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        
        assert testMember.memberId == 1 : 
            "Member ID should be 1, was " + testMember.memberId;
        assert testMember.name.equals("John Doe") : 
            "Member name should be \"John Doe\", was \"" + testMember.name + "\"";
        assert testMember.membershipStatus == MembershipStatus.BRONZE : 
            "Membership status should be BRONZE, was " + testMember.membershipStatus;
    }

    public static void testMembership() {
        System.out.println("Running testMembership");
        Membership testMembership = new Membership();
        
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        testMembership.addMember(testMember);
        
        assert testMembership.members.size() == 1 : 
            "Members size should be 1, was " + testMembership.members.size();
        assert testMembership.members.get(0).equals(testMember) : 
            "First member should equal testMember";

        testMembership.updateMembership(1, MembershipStatus.SILVER);
        assert testMembership.members.get(0).membershipStatus == MembershipStatus.SILVER : 
            "Membership status should be SILVER, was " + testMembership.members.get(0).membershipStatus;

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
        
        assert attendanceStats.totalMembers == 6 : 
            "Total members should be 6, was " + attendanceStats.totalMembers;
        assert attendanceStats.totalPaidMembers == 4 : 
            "Total paid members should be 4, was " + attendanceStats.totalPaidMembers;
        assert Math.abs(attendanceStats.conversionRate - 66.67) < 0.1 : 
            "Conversion rate should be 66.67, was " + attendanceStats.conversionRate;
    }
}