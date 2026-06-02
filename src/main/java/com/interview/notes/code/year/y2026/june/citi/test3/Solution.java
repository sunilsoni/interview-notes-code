package com.interview.notes.code.year.y2026.june.citi.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum MembershipStatus {
    BRONZE,
    SILVER,
    GOLD
}

record Workout(int id, int startTime, int endTime) {

    public int getDuration() {
        return endTime - startTime;
    }
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
    // Added to store workouts by member id
    private final Map<Integer, List<Workout>> workoutsByMemberId;
    public List<Member> members;

    public Membership() {
        members = new ArrayList<>();
        workoutsByMemberId = new HashMap<>();
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

    public boolean addWorkout(int memberId, Workout workout) {
        boolean memberExists = false;

        for (Member member : members) {
            if (member.memberId == memberId) {
                memberExists = true;
                break;
            }
        }

        if (!memberExists) {
            return false;
        }

        workoutsByMemberId
                .computeIfAbsent(memberId, id -> new ArrayList<>())
                .add(workout);

        return true;
    }

    public Map<Integer, Double> getAverageWorkoutDurations() {
        Map<Integer, Double> averageDurations = new HashMap<>();

        for (Member member : members) {
            List<Workout> workouts = workoutsByMemberId.get(member.memberId);

            if (workouts == null || workouts.isEmpty()) {
                averageDurations.put(member.memberId, null);
            } else {
                double totalDuration = 0.0;

                for (Workout workout : workouts) {
                    totalDuration += workout.getDuration();
                }

                double average = totalDuration / workouts.size();
                averageDurations.put(member.memberId, average);
            }
        }

        return averageDurations;
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
        testAddWorkout();
        testGetAverageWorkoutDurations();

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

    public static void testAddWorkout() {
        System.out.println("Running testAddWorkout");

        Membership testMembership = new Membership();

        Member testMember1 = new Member(12, "John Doe", MembershipStatus.SILVER);
        testMembership.addMember(testMember1);

        Member testMember2 = new Member(22, "Alex Cleeve", MembershipStatus.BRONZE);
        testMembership.addMember(testMember2);

        Workout testWorkout1 = new Workout(111, 10, 20);
        Workout testWorkout2 = new Workout(112, 15, 35);
        Workout testWorkout3 = new Workout(113, 20, 25);
        Workout testWorkout99 = new Workout(999, 1, 2);

        check(testMembership.addWorkout(12, testWorkout1),
                "Workout should be added for member 12");

        check(testMembership.addWorkout(22, testWorkout2),
                "Workout should be added for member 22");

        check(testMembership.addWorkout(12, testWorkout3),
                "Second workout should be added for member 12");

        check(!testMembership.addWorkout(404, testWorkout99),
                "Workout should not be added for non-existing member 404");
    }

    public static void testGetAverageWorkoutDurations() {
        System.out.println("Running testGetAverageWorkoutDurations");

        Membership testMembership = new Membership();

        Member testMember1 = new Member(12, "John Doe", MembershipStatus.SILVER);
        testMembership.addMember(testMember1);

        Member testMember2 = new Member(22, "Alex Cleeve", MembershipStatus.BRONZE);
        testMembership.addMember(testMember2);

        Member testMember3 = new Member(31, "Marie Cardiff", MembershipStatus.GOLD);
        testMembership.addMember(testMember3);

        Member testMember4 = new Member(37, "George Costanza", MembershipStatus.SILVER);
        testMembership.addMember(testMember4);

        Workout testWorkout1 = new Workout(101, 10, 20);      // 10
        Workout testWorkout2 = new Workout(102, 15, 35);      // 20
        Workout testWorkout3 = new Workout(103, 45, 90);      // 45
        Workout testWorkout4 = new Workout(104, 100, 155);    // 55
        Workout testWorkout5 = new Workout(105, 120, 200);    // 80
        Workout testWorkout6 = new Workout(106, 300, 400);    // 100
        Workout testWorkout7 = new Workout(107, 1000, 1010);  // 10
        Workout testWorkout8 = new Workout(108, 1010, 1045);  // ignored

        testMembership.addWorkout(12, testWorkout1);
        testMembership.addWorkout(22, testWorkout2);
        testMembership.addWorkout(31, testWorkout3);
        testMembership.addWorkout(12, testWorkout4);
        testMembership.addWorkout(22, testWorkout5);
        testMembership.addWorkout(31, testWorkout6);
        testMembership.addWorkout(12, testWorkout7);
        testMembership.addWorkout(404, testWorkout8);

        Map<Integer, Double> averageDurations =
                testMembership.getAverageWorkoutDurations();

        check(Math.abs(averageDurations.get(12) - 25.0) < 0.1,
                "Average duration for member 12 should be 25.0");

        check(Math.abs(averageDurations.get(22) - 50.0) < 0.1,
                "Average duration for member 22 should be 50.0");

        check(Math.abs(averageDurations.get(31) - 72.5) < 0.1,
                "Average duration for member 31 should be 72.5");

        check(averageDurations.containsKey(37) && averageDurations.get(37) == null,
                "Average for member with no workouts should be null");

        check(!averageDurations.containsKey(404),
                "Workout for non-existing member should not appear in averages");
    }

    private static void check(boolean condition, String message) {
        if (condition) {
            System.out.println("PASS - " + message);
        } else {
            System.out.println("FAIL - " + message);
        }
    }
}