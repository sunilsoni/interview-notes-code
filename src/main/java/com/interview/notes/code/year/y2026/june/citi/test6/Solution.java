package com.interview.notes.code.year.y2026.june.citi.test6;

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
    // Data structure to map a member's ID to their list of workouts
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

    public boolean addWorkout(int memberId, Workout workout) {
        // 1. Check if the member exists in the system
        boolean exists = members.stream().anyMatch(m -> m.memberId == memberId);
        
        // 2. If member does not exist, ignore and return false
        if (!exists) {
            return false;
        }
        
        // 3. If member exists, add the workout to their specific list
        memberWorkouts.computeIfAbsent(memberId, k -> new ArrayList<>()).add(workout);
        return true;
    }

    public Map<Integer, Double> getAverageWorkoutDurations() {
        Map<Integer, Double> averages = new HashMap<>();

        // Loop through ALL registered members to ensure everyone is included
        for (Member member : members) {
            List<Workout> workouts = memberWorkouts.get(member.memberId);
            
            // If the member has no workouts mapped to them, set their average to null
            if (workouts == null || workouts.isEmpty()) {
                averages.put(member.memberId, null);
            } else {
                // Calculate the average duration using Java Streams
                double averageDuration = workouts.stream()
                    .mapToDouble(Workout::getDuration)
                    .average()
                    .orElse(0.0);
                
                averages.put(member.memberId, averageDuration);
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
        System.out.println("Running testMember...");
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        assert testMember.memberId == 1;
        assert testMember.name.equals("John Doe");
        assert testMember.membershipStatus == MembershipStatus.BRONZE;
    }

    public static void testMembership() {
        System.out.println("Running testMembership...");
        Membership testMembership = new Membership();
        testMembership.addMember(new Member(1, "John Doe", MembershipStatus.BRONZE));
        testMembership.updateMembership(1, MembershipStatus.SILVER);
        
        testMembership.addMember(new Member(2, "Alex C", MembershipStatus.BRONZE));
        testMembership.addMember(new Member(3, "Marie C", MembershipStatus.GOLD));
        testMembership.addMember(new Member(4, "Joe D", MembershipStatus.SILVER));
        testMembership.addMember(new Member(5, "June R", MembershipStatus.BRONZE));
        testMembership.addMember(new Member(6, "Westley D", MembershipStatus.SILVER));

        MembershipStatistics stats = testMembership.getMembershipStatistics();
        assert stats.totalMembers == 6;
        assert stats.totalPaidMembers == 4;
        assert Math.abs(stats.conversionRate - 66.67) < 0.1;
    }

    public static void testAddWorkout() {
        System.out.println("Running testAddWorkout...");
        Membership ms = new Membership();
        ms.addMember(new Member(12, "John Doe", MembershipStatus.SILVER));
        ms.addMember(new Member(22, "Alex Cleeve", MembershipStatus.BRONZE));

        assert ms.addWorkout(12, new Workout(111, 10, 20)) : "Should return true for existing member";
        assert ms.addWorkout(22, new Workout(112, 15, 35)) : "Should return true for existing member";
        assert ms.addWorkout(12, new Workout(113, 20, 25)) : "Should return true for existing member";
        assert !ms.addWorkout(404, new Workout(999, 1, 2)) : "Should return false for non-existent member";
    }

    public static void testGetAverageWorkoutDurations() {
        System.out.println("Running testGetAverageWorkoutDurations...");
        Membership ms = new Membership();
        ms.addMember(new Member(12, "John Doe", MembershipStatus.SILVER));
        ms.addMember(new Member(22, "Alex Cleeve", MembershipStatus.BRONZE));
        ms.addMember(new Member(31, "Marie Cardiff", MembershipStatus.GOLD));
        ms.addMember(new Member(37, "George Costanza", MembershipStatus.SILVER));

        ms.addWorkout(12, new Workout(101, 10, 20)); 
        ms.addWorkout(22, new Workout(102, 15, 35)); 
        ms.addWorkout(31, new Workout(103, 45, 90)); 
        ms.addWorkout(12, new Workout(104, 100, 155)); 
        ms.addWorkout(22, new Workout(105, 120, 200)); 
        ms.addWorkout(31, new Workout(106, 300, 400)); 
        ms.addWorkout(12, new Workout(107, 1000, 1010)); 

        Map<Integer, Double> avg = ms.getAverageWorkoutDurations();
        
        assert avg != null : "Return map should not be null";
        assert Math.abs(avg.get(12) - 25.0) < 0.1 : "Average for 12 should be 25.0";
        assert Math.abs(avg.get(22) - 50.0) < 0.1 : "Average for 22 should be 50.0";
        assert Math.abs(avg.get(31) - 72.5) < 0.1 : "Average for 31 should be 72.5";
        assert avg.containsKey(37) && avg.get(37) == null : "Average for member with no workouts should be null";
        assert !avg.containsKey(404) : "Workout for non-existent member should not appear in averages";
    }
}