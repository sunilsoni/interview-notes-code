package com.interview.notes.code.year.y2024.sept24.test1;

import java.util.*;

/*

FINAL WORKING
We are working on a security system for a badged-access room in our company's building.
Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns
two collections:
1. All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)
2. All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)
Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.


records1 = l


[ "Paul"

enter"],
"Pauline"

exit"].
["Paul"

enter"],
[ "Paul"

exit"],
[ "Martha"

exit"],
[ "Joe"

"enter"],
[ "Martha"

enter"],
["Steve"

enter"]
["Martha"

"exit"],
["Jennifer"

"enter"]
[ "Joe"

enter"],
["Curtis"

exit"],
[ "Curtis"

enter"],
[ "Joe"

"exit" ],
["Martha"

"enter"],
["Martha"

"exit"],
["Jennifer"

"exit"],
[" Joe"

"enter"]
[ "Joe"

"enter"],
["Martha"

"exit" ],
[ "Joe"

exit"],
[ "Joe"

"exit"]
Expected output: ["Steve", "Curtis"
, "Paul"
• "Joe"], ["Martha", "Pauline"
, "Curtis"
, "Joe" ]
Other test cases:
records2 = [
[ "Paul"
, "enter"].
[ "Paul"
, "exit"],
Expected output: [1, [l


Other test cases:
records2 = [
["Paul", "enter"],
["Paul", "exit"].
Expected output: 11. [I
records3 = [
[ "Paul"
, "enter"],
[ "Paul"
, "enter"],
[ "Paul"
- “exit"］.
[ "Paul"
, "exit"].
Expected output: ["Paul"], ["Paul"]
records4 = I
["Raj"
, "enter"],
["Paul", "enter"], ["Paul", "exit"],
["Paul",
, "exit"],
["Paul"
["Raj"
"enter"], , "enter"],
Expected output: ["Raj"
, "Paul"]. ["Paul"]
All Test Cases:
mismatches (records1) => ["Steve"
', "Curtis"
, "Paul"
mismatches(records2) => [1, [1 mismatches(records3) => ["Paul"], ["Paul"] mismatches(records4) => ["Raj"
', "Paul"], ["Paul"]
n: length of the badge records array
"Joe"], ["Martha"
, "Pauline"
, "Curtis"
, "Joe" ]
 */
public class BadgeCheckerWorking {

    public static List<Set<String>> findMismatches(List<List<String>> records) {
        Map<String, Boolean> inRoom = new HashMap<>();
        Set<String> didntExit = new HashSet<>();
        Set<String> didntEnter = new HashSet<>();

        for (List<String> record : records) {
            String name = record.get(0);
            String action = record.get(1);

            if (action.equals("enter")) {
                if (inRoom.getOrDefault(name, false)) {
                    didntExit.add(name);
                }
                inRoom.put(name, true);
            } else { // exit
                if (!inRoom.getOrDefault(name, false)) {
                    didntEnter.add(name);
                }
                inRoom.put(name, false);
            }
        }

        // Check for any employees still in the room at the end
        for (Map.Entry<String, Boolean> entry : inRoom.entrySet()) {
            if (entry.getValue()) {
                didntExit.add(entry.getKey());
            }
        }

        return Arrays.asList(didntExit, didntEnter);
    }

    public static void main(String[] args) {
        // Test cases
        List<List<String>> records1 = Arrays.asList(
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Pauline", "exit"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Martha", "exit"),
                Arrays.asList("Joe", "enter"),
                Arrays.asList("Martha", "enter"),
                Arrays.asList("Steve", "enter"),
                Arrays.asList("Martha", "exit"),
                Arrays.asList("Jennifer", "enter"),
                Arrays.asList("Joe", "enter"),
                Arrays.asList("Curtis", "exit"),
                Arrays.asList("Curtis", "enter"),
                Arrays.asList("Joe", "exit"),
                Arrays.asList("Martha", "enter"),
                Arrays.asList("Martha", "exit"),
                Arrays.asList("Jennifer", "exit"),
                Arrays.asList("Joe", "enter"),
                Arrays.asList("Joe", "enter"),
                Arrays.asList("Martha", "exit"),
                Arrays.asList("Joe", "exit"),
                Arrays.asList("Joe", "exit")
        );

        List<List<String>> records2 = Arrays.asList(
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit")
        );

        List<List<String>> records3 = Arrays.asList(
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Paul", "exit")
        );

        List<List<String>> records4 = Arrays.asList(
                Arrays.asList("Raj", "enter"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Raj", "enter")
        );

        System.out.println("Test case 1: " + findMismatches(records1));
        System.out.println("Test case 2: " + findMismatches(records2));
        System.out.println("Test case 3: " + findMismatches(records3));
        System.out.println("Test case 4: " + findMismatches(records4));
    }
}
