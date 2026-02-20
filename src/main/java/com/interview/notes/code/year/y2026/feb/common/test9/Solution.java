package com.interview.notes.code.year.y2026.feb.common.test9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Course {
    /* Data about a particular course. */
    public String title; // The name of the obstacle course
    public int obstacleCount; // The number of obstacles in the course

    public Course(String courseTitle, int obstacles) {
        title = courseTitle;
        obstacleCount = obstacles;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Course c)) { return false; }
        // Used .equals for String comparison instead of == for safety
        return c.title.equals(this.title) && c.obstacleCount == this.obstacleCount;
    }

    @Override
    public int hashCode() {
        return (title == null ? 0 : title.hashCode()) * obstacleCount;
    }
}

class Run {
    /* Data and methods about a single run of the obstacle course */
    public Course course; // The Course object this run is for
    public boolean complete; // true if the run is a full run of the course
                             // false if the run is in progress or was aborted
    public List<Integer> obstacleTimes; // The times it took to complete each obstacle

    public Run(Course runCourse) {
        course = runCourse;
        complete = false;
        obstacleTimes = new ArrayList<>();
    }

    public void addObstacleTime(int obstacleTime) {
        // When an obstacle is completed, add the time to the current run.
        if(complete) {
            throw new IllegalStateException("Cannot add obstacle to complete run");
        }
        obstacleTimes.add(obstacleTime);
        if(obstacleTimes.size() == course.obstacleCount) {
            complete = true;
        }
    }

    public int getRunTime() {
        // Returns the total time this run has taken.
        // If the run is not complete, it returns the time taken so far.
        return obstacleTimes.stream().mapToInt(Integer::intValue).sum();
    }
}

class RunCollection {
    public Course course; // the Course this RunCollection is for
    public List<Run> runs; // the Run objects for this particular course

    public RunCollection(Course collectionCourse) {
        course = collectionCourse;
        runs = new ArrayList<>();
    }

    public int getNumRuns() {
        // Returns the number of runs in this collection
        return runs.size();
    }

    public void addRun(Run run) {
        // Adds a run to this collection
        if (!run.course.equals(course)) {
            throw new IllegalArgumentException("run's Course is not the same as the RunCollection's");
        }
        runs.add(run);
    }

    public int personalBest() {
        // PREVIOUS FIX: Added .filter(v -> v.complete) so incomplete runs don't break the personal best calculation
        return runs.stream()
                   .filter(v -> v.complete) 
                   .mapToInt(v -> v.getRunTime())
                   .min()
                   .orElse(Integer.MAX_VALUE);
    }

    // --- NEW METHOD START: bestOfBests() ---
    public int bestOfBests() {
        int totalBest = 0;
        
        // Loop through each obstacle column (0 to obstacleCount - 1)
        for (int i = 0; i < course.obstacleCount; i++) {
            int minForObstacle = Integer.MAX_VALUE;
            boolean hasTimeForObstacle = false;
            
            // Look at every run to find the fastest time for this specific obstacle
            for (Run run : runs) {
                // We must check if the run actually reached and recorded this obstacle
                if (run.obstacleTimes.size() > i) {
                    minForObstacle = Math.min(minForObstacle, run.obstacleTimes.get(i));
                    hasTimeForObstacle = true;
                }
            }
            
            // If at least one run completed this obstacle, add the best time to our theoretical total
            if (hasTimeForObstacle) {
                totalBest += minForObstacle;
            }
        }
        return totalBest;
    }

    public double chanceOfPersonalBest(Run inProgressRun) {
        int TRIALS = 10000;
        int currentPB = personalBest();
        int completedObstaclesCount = inProgressRun.obstacleTimes.size();
        int timeSoFar = inProgressRun.getRunTime();

        // 1. Pre-gather historical times for the remaining obstacles
        // This is an optimization so we don't search through all runs 10,000 times.
        List<List<Integer>> historicalTimesForRemaining = new ArrayList<>();

        for (int i = completedObstaclesCount; i < course.obstacleCount; i++) {
            List<Integer> timesForObstacle = new ArrayList<>();
            for (Run pastRun : runs) {
                // Check if the past run reached this specific obstacle
                if (pastRun.obstacleTimes.size() > i) {
                    timesForObstacle.add(pastRun.obstacleTimes.get(i));
                }
            }
            historicalTimesForRemaining.add(timesForObstacle);
        }

        int successCount = 0;
        Random random = new Random();

        // 2. Run the simulation 10,000 times
        for (int t = 0; t < TRIALS; t++) {
            int simulatedTime = timeSoFar;
            boolean couldBePB = true;

            for (List<Integer> historicalTimes : historicalTimesForRemaining) {
                // If we don't have historical data for an obstacle, we can't simulate it
                if (historicalTimes.isEmpty()) {
                    couldBePB = false;
                    break;
                }

                // Randomly pick a time from past runs for this obstacle
                int randomIndex = random.nextInt(historicalTimes.size());
                int randomTime = historicalTimes.get(randomIndex);

                simulatedTime += randomTime;

                // Optimization: Stop this trial early if we've already exceeded the PB
                if (simulatedTime > currentPB) {
                    couldBePB = false;
                    break;
                }
            }

            // 3. Tally successes
            if (couldBePB && simulatedTime <= currentPB) {
                successCount++;
            }
        }

        // Return the probability (successes / total trials)
        return (double) successCount / TRIALS;
    }
    // --- NEW METHOD END ---
}

public class Solution {
    public static void main(String[] argv) {
        // IMPORTANT: Ensure you run this with the -ea flag (Enable Assertions) in your IDE/Terminal 
        // to actually test the assert statements.
        testRun();
        testRunCollection();
    }

    public static void testRun() {
        System.out.println("Running testRun");
        Course testCourse = new Course("Test course", 2);
        Run testRun = new Run(testCourse);
        testRun.addObstacleTime(3);
        assert !testRun.complete : "Test run should not be complete";
        testRun.addObstacleTime(5);
        assert testRun.complete : "Test run should be complete";
        assert testRun.obstacleTimes.equals(new ArrayList<Integer>(List.of(3, 5))) : "obstacleTimes should be [3, 5]";
        assert testRun.getRunTime() == 8 : "getRunTime should return 8";
        try {
            testRun.addObstacleTime(4);
            assert false : "adding obstacle time to complete run should throw";
        } catch (IllegalStateException e) {
            // expected
        }
    }

    public static RunCollection makeRunCollection(Course course, int[][] obstacleData) {
        RunCollection runCollection = new RunCollection(course);
        for(int[] runData : obstacleData) {
            Run run = new Run(course);
            for(int obstacleTime : runData) {
                run.addObstacleTime(obstacleTime);
            }
            runCollection.addRun(run);
        }
        return runCollection;
    }

    public static void testRunCollection() {
        System.out.println("Running testRunCollection");
        int[][] obstacleData = new int[][] {{3, 4, 5, 6},
                                            {4, 4, 4, 5},
                                            {4, 5, 4, 6},
                                            {5, 5, 3}};
        Course testCourse = new Course("Test course", 4);
        RunCollection runCollection = makeRunCollection(testCourse, obstacleData);

        int numRuns = obstacleData.length;
        assert runCollection.getNumRuns() == numRuns : "number of runs should be " + numRuns;
        
        // This will now pass due to the previous personalBest fix
        assert runCollection.personalBest() == 17 : "personalBest should be 17, was " + runCollection.personalBest();

        // --- NEW TEST START: verify bestOfBests ---
        assert runCollection.bestOfBests() == 15 : "bestOfBests should be 15, was " + runCollection.bestOfBests();
        // --- NEW TEST END ---

        // --- NEW TEST: chanceOfPersonalBest ---
        Run inProgress = new Run(testCourse);
        inProgress.addObstacleTime(3); // Completes obstacle 1 fast
        inProgress.addObstacleTime(4); // Completes obstacle 2 fast

        // Time so far: 7. Current PB: 17. Target for remaining 2 obstacles: <= 10.
        // It will randomly combine historical times for obstacles 3 and 4 to see how often the sum is <= 10.
        double chance = runCollection.chanceOfPersonalBest(inProgress);
        System.out.println("Chance of PB: " + chance);

        // We expect a value roughly around 0.50 (50%) based on the historical combinations
        assert chance >= 0.48 && chance <= 0.52 : "Chance should be roughly 50%, was " + chance;
        System.out.println("All tests passed!");
    }
}