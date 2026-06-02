package com.interview.notes.code.year.y2026.june.citi.test5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum MembershipStatus { BRONZE, SILVER, GOLD }

class Member {
    public int memberId; public String name; public MembershipStatus membershipStatus;
    public Member(int id, String n, MembershipStatus s) { memberId = id; name = n; membershipStatus = s; }
}

class Workout {
    public int id, startTime, endTime;
    public Workout(int i, int s, int e) { id = i; startTime = s; endTime = e; }
}

class MembershipStatistics {
    public int totalMembers, totalPaidMembers; public double conversionRate;
    public MembershipStatistics(int t, int p, double c) { totalMembers = t; totalPaidMembers = p; conversionRate = c; }
}

class Membership {
    private final Map<Integer, List<Workout>> workouts = new HashMap<>();
    public List<Member> members = new ArrayList<>();

    public void addMember(Member m) { members.add(m); }

    public void updateMembership(int id, MembershipStatus s) {
        members.stream().filter(m -> m.memberId == id).findFirst().ifPresent(m -> m.membershipStatus = s);
    }

    public MembershipStatistics getMembershipStatistics() {
        int paid = (int) members.stream().filter(m -> m.membershipStatus != MembershipStatus.BRONZE).count();
        return new MembershipStatistics(members.size(), paid, paid * 100.0 / members.size());
    }

    public boolean addWorkout(int id, Workout w) {
        if (members.stream().noneMatch(m -> m.memberId == id)) return false;
        workouts.computeIfAbsent(id, k -> new ArrayList<>()).add(w);
        return true;
    }

    public Map<Integer, Double> getAverageWorkoutDurations() {
        var res = new HashMap<Integer, Double>();
        members.forEach(m -> {
            var w = workouts.get(m.memberId);
            res.put(m.memberId, w == null || w.isEmpty() ? null : w.stream().mapToDouble(x -> x.endTime - x.startTime).average().orElse(0));
        });
        return res; // Note: Collectors.toMap fails on null values, so forEach is required here.
    }
}

public class Solution {
    public static void main(String[] args) {
        testMember(); testMembership(); testAddWorkout(); testGetAverageWorkoutDurations();
        System.out.println("Success: All tests passed!");
    }

    public static void testMember() {
        var m = new Member(1, "John", MembershipStatus.BRONZE);
        assert m.memberId == 1 && m.name.equals("John") && m.membershipStatus == MembershipStatus.BRONZE;
    }

    public static void testMembership() {
        var ms = new Membership();
        var m1 = new Member(1, "John", MembershipStatus.BRONZE);
        ms.addMember(m1);
        
        assert ms.members.size() == 1 && ms.members.getFirst().equals(m1); // Uses Java 21 getFirst()
        ms.updateMembership(1, MembershipStatus.SILVER);
        assert ms.members.getFirst().membershipStatus == MembershipStatus.SILVER;

        ms.addMember(new Member(2, "A", MembershipStatus.BRONZE));
        ms.addMember(new Member(3, "M", MembershipStatus.GOLD));
        ms.addMember(new Member(4, "J", MembershipStatus.SILVER));
        ms.addMember(new Member(5, "R", MembershipStatus.BRONZE));
        ms.addMember(new Member(6, "W", MembershipStatus.SILVER));

        var stats = ms.getMembershipStatistics();
        assert stats.totalMembers == 6 && stats.totalPaidMembers == 4 && Math.abs(stats.conversionRate - 66.67) < 0.1;
    }

    public static void testAddWorkout() {
        var ms = new Membership();
        ms.addMember(new Member(12, "John", MembershipStatus.SILVER));
        ms.addMember(new Member(22, "Alex", MembershipStatus.BRONZE));

        assert ms.addWorkout(12, new Workout(111, 10, 20));
        assert ms.addWorkout(22, new Workout(112, 15, 35));
        assert !ms.addWorkout(404, new Workout(999, 1, 2));
    }

    public static void testGetAverageWorkoutDurations() {
        var ms = new Membership();
        ms.addMember(new Member(12, "John", MembershipStatus.SILVER));
        ms.addMember(new Member(22, "Alex", MembershipStatus.BRONZE));
        ms.addMember(new Member(31, "Marie", MembershipStatus.GOLD));
        ms.addMember(new Member(37, "George", MembershipStatus.SILVER));

        ms.addWorkout(12, new Workout(101, 10, 20)); ms.addWorkout(22, new Workout(102, 15, 35));
        ms.addWorkout(31, new Workout(103, 45, 90)); ms.addWorkout(12, new Workout(104, 100, 155));
        ms.addWorkout(22, new Workout(105, 120, 200)); ms.addWorkout(31, new Workout(106, 300, 400));
        ms.addWorkout(12, new Workout(107, 1000, 1010)); ms.addWorkout(404, new Workout(108, 1010, 1045));

        var avg = ms.getAverageWorkoutDurations();
        assert Math.abs(avg.get(12) - 25.0) < 0.1 && Math.abs(avg.get(22) - 50.0) < 0.1 && Math.abs(avg.get(31) - 72.5) < 0.1;
        assert avg.containsKey(37) && avg.get(37) == null;
        assert !avg.containsKey(404);
    }
}