package com.interview.notes.code.months.aug24.test5;

/*
Implement the RunningAverage class to produce the running average of a series of numbers.
Reminder: average is defined as a sum of all items divided by their count.


 */
public class RunningAverage {
    private int count;
    private long sum;

    public RunningAverage() {
        this.count = 0;
        this.sum = 0;
    }

    public float add(int number) {
        count++;
        sum += number;
        return getAverage();
    }

    public float getAverage() {
        if (count == 0) {
            return 0f;
        }
        return (float) sum / count;
    }

    public static void main(String[] args) {
        RunningAverage ra = new RunningAverage();
        
        System.out.println("Initial average: " + ra.getAverage());
        
        System.out.println("Add 1: " + ra.add(1));
        System.out.println("Add 2: " + ra.add(2));
        System.out.println("Add 3: " + ra.add(3));
        System.out.println("Add 76: " + ra.add(76));
        System.out.println("Add 7: " + ra.add(7));
        
        System.out.println("Final average: " + ra.getAverage());
        
        // Test with large number
        RunningAverage ra2 = new RunningAverage();
        System.out.println("Add MAX_VALUE: " + ra2.add(Integer.MAX_VALUE));
    }
}
