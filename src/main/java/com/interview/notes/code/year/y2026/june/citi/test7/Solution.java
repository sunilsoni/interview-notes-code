package com.interview.notes.code.year.y2026.june.citi.test7;

import java.util.*;

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
            if (member.membershipStatus == MembershipStatus.GOLD || member.membershipStatus == MembershipStatus.SILVER) {
                totalPaidMembers++;
            }
        }
        double conversionRate = (totalPaidMembers / (double) totalMembers) * 100.0;
        return new MembershipStatistics(totalMembers, totalPaidMembers, conversionRate);
    }

    public boolean addWorkout(int memberId, Workout workout) {
        boolean exists = members.stream().anyMatch(m -> m.memberId == memberId);
        if (!exists) return false;
        
        memberWorkouts.computeIfAbsent(memberId, k -> new ArrayList<>()).add(workout);
        return true;
    }

    public Map<Integer, Double> getAverageWorkoutDurations() {
        Map<Integer, Double> averages = new HashMap<>();
        for (Member member : members) {
            List<Workout> workouts = memberWorkouts.get(member.memberId);
            if (workouts == null || workouts.isEmpty()) {
                averages.put(member.memberId, null);
            } else {
                double avg = workouts.stream().mapToDouble(Workout::getDuration).average().orElse(0.0);
                averages.put(member.memberId, avg);
            }
        }
        return averages;
    }

    public Map<Integer, Integer> getDuePayments() {
        Map<Integer, Integer> duePayments = new HashMap<>();

        for (Member member : members) {
            int memberId = member.memberId;
            List<Workout> workouts = memberWorkouts.get(memberId);
            
            // If member has no workouts, due payment is 0
            if (workouts == null || workouts.isEmpty()) {
                duePayments.put(memberId, 0);
                continue;
            }

            // Create a copy to sort so we don't mutate the original list state
            List<Workout> sortedWorkouts = new ArrayList<>(workouts);
            sortedWorkouts.sort(Comparator.comparingInt(Workout::id));

            int freeLimit = 0;
            int hourlyRate = 0;

            switch (member.membershipStatus) {
                case BRONZE:
                    freeLimit = 1;
                    hourlyRate = 10;
                    break;
                case SILVER:
                    freeLimit = 3;
                    hourlyRate = 8;
                    break;
                case GOLD:
                    freeLimit = 5;
                    hourlyRate = 6;
                    break;
            }

            int totalPayment = 0;
            
            for (int i = 0; i < sortedWorkouts.size(); i++) {
                if (i < freeLimit) {
                    continue; // Skip the free workouts
                }
                
                Workout w = sortedWorkouts.get(i);
                int durationInMinutes = w.getDuration();
                
                // Math.ceil rounds up to the nearest hour
                int billableHours = (int) Math.ceil(durationInMinutes / 60.0);
                totalPayment += (billableHours * hourlyRate);
            }

            duePayments.put(memberId, totalPayment);
        }

        return duePayments;
    }
}

public class Solution {
    public static void main(String[] args) {
        testMember();
        testMembership();
        testAddWorkout();
        testGetAverageWorkoutDurations();
        testGetDuePayments();
        System.out.println("Success: All tests passed!");
    }

    public static void testMember() {
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        assert testMember.memberId == 1;
        assert testMember.name.equals("John Doe");
        assert testMember.membershipStatus == MembershipStatus.BRONZE;
    }

    public static void testMembership() {
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
        Membership ms = new Membership();
        ms.addMember(new Member(12, "John Doe", MembershipStatus.SILVER));
        ms.addMember(new Member(22, "Alex Cleeve", MembershipStatus.BRONZE));

        assert ms.addWorkout(12, new Workout(111, 10, 20));
        assert ms.addWorkout(22, new Workout(112, 15, 35));
        assert ms.addWorkout(12, new Workout(113, 20, 25));
        assert !ms.addWorkout(404, new Workout(999, 1, 2));
    }

    public static void testGetAverageWorkoutDurations() {
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
        
        assert avg != null;
        assert Math.abs(avg.get(12) - 25.0) < 0.1;
        assert Math.abs(avg.get(22) - 50.0) < 0.1;
        assert Math.abs(avg.get(31) - 72.5) < 0.1;
        assert avg.containsKey(37) && avg.get(37) == null;
        assert !avg.containsKey(404);
    }

    public static void testGetDuePayments() {
        System.out.println("Running testGetDuePayments...");
        Membership testMembership = new Membership();
        testMembership.addMember(new Member(1, "John Doe", MembershipStatus.BRONZE));
        testMembership.addMember(new Member(2, "Alex C", MembershipStatus.SILVER));
        testMembership.addMember(new Member(3, "Marie C", MembershipStatus.GOLD));

        Map<Integer, List<Workout>> memberWorkouts = new HashMap<>();
        // Note: Included the missing Workout(10, 300, 350) to perfectly match the screenshot logic
        memberWorkouts.put(1, Arrays.asList(
            new Workout(1, 500, 700), new Workout(10, 300, 350), new Workout(12, 10, 20),
            new Workout(3, 50, 90), new Workout(6, 130, 150), new Workout(15, 900, 920)
        ));
        memberWorkouts.put(2, Arrays.asList(
            new Workout(13, 510, 540), new Workout(14, 600, 700), new Workout(2, 15, 35),
            new Workout(4, 100, 155), new Workout(18, 200, 225), new Workout(8, 1050, 1155)
        ));
        memberWorkouts.put(3, Arrays.asList(
            new Workout(5, 120, 135), new Workout(17, 140, 190), new Workout(9, 210, 255),
            new Workout(11, 400, 450), new Workout(16, 910, 940), new Workout(7, 1000, 1100)
        ));

        for (Map.Entry<Integer, List<Workout>> entry : memberWorkouts.entrySet()) {
            int memberId = entry.getKey();
            List<Workout> workoutList = entry.getValue();
            for (Workout workout : workoutList) {
                testMembership.addWorkout(memberId, workout);
            }
        }

        Map<Integer, Integer> duePayments = testMembership.getDuePayments();
        
        assert duePayments.get(1) == 50 : "due payment for member 1 should be 50, was " + duePayments.get(1);
        assert duePayments.get(2) == 32 : "due payment for member 2 should be 32, was " + duePayments.get(2);
        assert duePayments.get(3) == 6  : "due payment for member 3 should be 6, was " + duePayments.get(3);

        testMembership.addMember(new Member(4, "Ron Burgundy", MembershipStatus.SILVER));
        Map<Integer, Integer> duePayments2 = testMembership.getDuePayments();
        
        assert duePayments2.get(4) == 0 : "due payment for member 4 should be 0, was " + duePayments2.get(4);
    }
}