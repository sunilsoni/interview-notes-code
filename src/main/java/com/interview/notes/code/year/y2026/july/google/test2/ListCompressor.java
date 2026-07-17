package com.interview.notes.code.year.y2026.july.google.test2;

import java.util.*; // Import standard utility classes
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListCompressor { 

    public static boolean canCompress(List<List<Integer>> lists) { 
        // 1. Setup our trackers with easy-to-understand names
        var followersMap = new HashMap<Integer, Set<Integer>>(); // Tracks: Number -> Set of numbers that must come AFTER it
        var prerequisiteCountMap = new HashMap<Integer, Integer>(); // Tracks: Number -> How many numbers must come BEFORE it

        // 2. Find every unique number across all lists and set their initial values
        lists.stream().flatMap(List::stream).distinct().forEach(number -> { 
            followersMap.put(number, new HashSet<>()); // Start with an empty set of followers
            prerequisiteCountMap.put(number, 0); // Start with 0 prerequisites
        });

        // 3. Look at adjacent pairs to build our rules
        lists.forEach(list -> { 
            IntStream.range(0, list.size() - 1).forEach(i -> { 
                var beforeNum = list.get(i);     // The number on the left
                var afterNum = list.get(i + 1);  // The number on the right

                // Attempt to add 'afterNum' as a follower of 'beforeNum'
                // The .add() method returns true if this is a new, unique rule we haven't seen yet
                if (followersMap.get(beforeNum).add(afterNum)) { 
                    // Since 'afterNum' has a new number that must come before it, increase its prerequisite count by 1
                    prerequisiteCountMap.merge(afterNum, 1, Integer::sum); 
                } 
            }); 
        }); 

        // 4. Find all numbers that have ZERO prerequisites (they are ready to be placed first)
        var readyToProcessQueue = prerequisiteCountMap.entrySet().stream() 
                .filter(entry -> entry.getValue() == 0) // Keep only entries with 0 prerequisites
                .map(Map.Entry::getKey) // Extract just the numbers themselves
                .collect(Collectors.toCollection(LinkedList::new)); // Put them in a waiting line (Queue)

        var successfullyProcessedCount = 0; // Track how many numbers we successfully resolve

        // 5. Process the numbers in the queue one by one
        while (!readyToProcessQueue.isEmpty()) { 
            var currentNumber = readyToProcessQueue.poll(); // Take the next ready number out of the line
            successfullyProcessedCount++; // We successfully processed it!

            // 6. Tell all the numbers waiting for 'currentNumber' that it has been processed
            followersMap.get(currentNumber).forEach(follower -> { 
                // Reduce the prerequisite count of the follower by 1
                var remainingPrerequisites = prerequisiteCountMap.merge(follower, -1, Integer::sum); 
                
                // If the follower now has 0 remaining prerequisites, it is ready to be processed!
                if (remainingPrerequisites == 0) { 
                    readyToProcessQueue.add(follower); // Add it to the waiting line
                } 
            }); 
        } 

        // 7. Final Check: Did we process every unique number?
        // If yes, return true (no contradictions). If no, there was an impossible cycle, return false.
        return successfullyProcessedCount == prerequisiteCountMap.size(); 
    } 

    public static void main(String[] args) { 
        // Providing exactly ONE example (the one from the screenshot)
        var list1 = List.of(1, 2, 3, 6, 9, 15, 12); 
        var list2 = List.of(2, 6, 8, 10, 11); 
        var inputLists = List.of(list1, list2); 

        System.out.println("Evaluating the lists from the image...\n");
        var isCompressible = canCompress(inputLists);
        
        System.out.println("Can these lists be compressed while preserving order?");
        System.out.println("Result: " + isCompressible); // Expected output: true
    } 
}