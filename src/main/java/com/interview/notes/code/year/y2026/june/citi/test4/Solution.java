package com.interview.notes.code.year.y2026.june.citi.test4;

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
        return "Member ID: " + memberId + ", Name: " + name + ", Membership Status: " + membershipStatus;
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

class Membership {
    // Map to associate a Member's ID with their list of Workout sessions
    private final Map<Integer, List<Workout>> memberWorkouts;
    public List<Member> members;

    public Membership() {
        members = new ArrayList<>();
        memberWorkouts = new HashMap<>();
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
            // Evaluates both GOLD and SILVER as paid members
            if (member.membershipStatus == MembershipStatus.GOLD || member.membershipStatus == MembershipStatus.SILVER) {
                totalPaidMembers++;
            }
        }
        double conversionRate = (totalPaidMembers / (double) totalMembers) * 100.0;
        return new MembershipStatistics(totalMembers, totalPaidMembers, conversionRate);
    }

    // --- NEW METHODS FOR WORKOUTS ---

    public boolean addWorkout(int memberId, Workout workout) {
        // First, check if the member exists in our system
        boolean memberExists = false;
        for (Member member : members) {
            if (member.memberId == memberId) {
                memberExists = true;
                break;
            }
        }

        // If the member doesn't exist, ignore and return false
        if (!memberExists) {
            return false;
        }

        // If they exist, add the workout to their specific list
        memberWorkouts.putIfAbsent(memberId, new ArrayList<>());
        memberWorkouts.get(memberId).add(workout);
        return true;
    }

    public Map<Integer, Double> getAverageWorkoutDurations() {
        Map<Integer, Double> averages = new HashMap<>();

        // Loop through ALL registered members to ensure everyone is included in the map
        for (Member member : members) {
            List<Workout> workouts = memberWorkouts.get(member.memberId);
            
            // If the member has no workouts mapped to them, set their average to null
            if (workouts == null || workouts.isEmpty()) {
                averages.put(member.memberId, null);
            } else {
                // Calculate the average duration
                double totalDuration = 0;
                for (Workout w : workouts) {
                    totalDuration += w.getDuration();
                }
                averages.put(member.memberId, totalDuration / workouts.size());
            }
        }

        return averages;
    }
}

public class Solution {
    public static void main(String[] args) {
        testMember();
        testMembership();
        testAddWorkout();
        testGetAverageWorkoutDurations();
        System.out.println("Success: All tests passed!");
    }

    public static void testMember() {
        System.out.println("Running testMember");
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        
        assert testMember.memberId == 1 : "Member ID should be 1";
        assert testMember.name.equals("John Doe") : "Member name should be \"John Doe\"";
        assert testMember.membershipStatus == MembershipStatus.BRONZE : "Membership status should be BRONZE";
    }

    public static void testMembership() {
        System.out.println("Running testMembership");
        Membership testMembership = new Membership();
        
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        testMembership.addMember(testMember);
        
        assert testMembership.members.size() == 1 : "Members size should be 1";
        assert testMembership.members.get(0).equals(testMember) : "First member should equal testMember";

        testMembership.updateMembership(1, MembershipStatus.SILVER);
        assert testMembership.members.get(0).membershipStatus == MembershipStatus.SILVER : "Membership status should be SILVER";

        testMembership.addMember(new Member(2, "Alex C", MembershipStatus.BRONZE));
        testMembership.addMember(new Member(3, "Marie C", MembershipStatus.GOLD));
        testMembership.addMember(new Member(4, "Joe D", MembershipStatus.SILVER));
        testMembership.addMember(new Member(5, "June R", MembershipStatus.BRONZE));
        testMembership.addMember(new Member(6, "Westley D", MembershipStatus.SILVER));

        MembershipStatistics attendanceStats = testMembership.getMembershipStatistics();
        
        assert attendanceStats.totalMembers == 6 : "Total members should be 6";
        assert attendanceStats.totalPaidMembers == 4 : "Total paid members should be 4";
        assert Math.abs(attendanceStats.conversionRate - 66.67) < 0.1 : "Conversion rate should be 66.67";
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

        assert testMembership.addWorkout(12, testWorkout1) : "Should return true for existing member";
        assert testMembership.addWorkout(22, testWorkout2) : "Should return true for existing member";
        assert testMembership.addWorkout(12, testWorkout3) : "Should return true for existing member";
        assert !testMembership.addWorkout(404, testWorkout99) : "Should return false for non-existent member";
    }

    public static void testGetAverageWorkoutDurations() {
        System.out.println("Running testGetAverageWorkoutDurations");
        Membership testMembership = new Membership();
        
        testMembership.addMember(new Member(12, "John Doe", MembershipStatus.SILVER));
        testMembership.addMember(new Member(22, "Alex Cleeve", MembershipStatus.BRONZE));
        testMembership.addMember(new Member(31, "Marie Cardiff", MembershipStatus.GOLD));
        testMembership.addMember(new Member(37, "George Costanza", MembershipStatus.SILVER));

        testMembership.addWorkout(12, new Workout(101, 10, 20)); // 10 mins
        testMembership.addWorkout(22, new Workout(102, 15, 35)); // 20 mins
        testMembership.addWorkout(31, new Workout(103, 45, 90)); // 45 mins
        testMembership.addWorkout(12, new Workout(104, 100, 155)); // 55 mins
        testMembership.addWorkout(22, new Workout(105, 120, 200)); // 80 mins
        testMembership.addWorkout(31, new Workout(106, 300, 400)); // 100 mins
        testMembership.addWorkout(12, new Workout(107, 1000, 1010)); // 10 mins
        testMembership.addWorkout(404, new Workout(108, 1010, 1045)); // Ignored (Member doesn't exist)

        Map<Integer, Double> averageDurations = testMembership.getAverageWorkoutDurations();
        
        assert Math.abs(averageDurations.get(12) - 25.0) < 0.1 : "Average for 12 should be 25.0";
        assert Math.abs(averageDurations.get(22) - 50.0) < 0.1 : "Average for 22 should be 50.0";
        assert Math.abs(averageDurations.get(31) - 72.5) < 0.1 : "Average for 31 should be 72.5";
        assert averageDurations.containsKey(37) && averageDurations.get(37) == null : "Average for member with no workouts should be null";
        assert !averageDurations.containsKey(404) : "Workout for non-existent member should not appear in averages";
    }
}