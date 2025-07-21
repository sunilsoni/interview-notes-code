package com.interview.notes.code.year.y2025.july.oracle.test5;

import java.util.*;
import java.util.concurrent.*;

public class StatePopulationCounter {
    // Simulate state data
    static class State {
        String name;
        int population;

        State(String name, int population) {
            this.name = name;
            this.population = population;
        }
    }

    public static long calculateTotalPopulation(List<State> states, int threadCount) {
        // Break states into chunks for parallel processing
        int chunkSize = states.size() / threadCount;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Long>> futures = new ArrayList<>();

        // Submit tasks for each chunk
        for (int i = 0; i < threadCount; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == threadCount - 1) ? states.size() : (i + 1) * chunkSize;
            
            futures.add(executor.submit(() -> {
                long sum = 0;
                for (int j = startIndex; j < endIndex; j++) {
                    // Simulate some processing time
                    Thread.sleep(100);
                    sum += states.get(j).population;
                }
                return sum;
            }));
        }

        // Collect results
        long totalPopulation = 0;
        try {
            for (Future<Long> future : futures) {
                totalPopulation += future.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
        return totalPopulation;
    }

    public static void main(String[] args) {
        // Test with sample data
        List<State> states = new ArrayList<>();
        
        // Add sample states (normally would be 50)
        states.add(new State("California", 39538223));
        states.add(new State("Texas", 29145505));
        states.add(new State("Florida", 21538187));
        states.add(new State("New York", 20201249));
        // ... add more states as needed

        // Test with different thread counts
        int[] threadCounts = {1, 2, 4};
        
        for (int threadCount : threadCounts) {
            long startTime = System.currentTimeMillis();
            long totalPop = calculateTotalPopulation(states, threadCount);
            long endTime = System.currentTimeMillis();
            
            System.out.println("Thread count: " + threadCount);
            System.out.println("Total population: " + totalPop);
            System.out.println("Time taken: " + (endTime - startTime) + "ms");
            System.out.println("------------------------");
        }
    }
}
