package com.interview.notes.code.year.y2026.may.common.test8;

// A standard class representing a node in a singly-linked list
class ListNode {
    int val; // Stores the integer digit for this specific node
    ListNode next; // Holds the reference pointer to the next node in the sequence
    
    // Constructor to quickly initialize a node with a specific value
    ListNode(int val) { 
        this.val = val; // Assigns the passed integer to the node's value property
    }
}

public class AddTwoNumbers {

    // The main method that performs the addition of two linked lists
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var dummy = new ListNode(0); // Creates a placeholder node to safely anchor the head of our resulting list
        var current = dummy; // Initializes a pointer we will use to build the new list node by node
        int carry = 0; // Tracks any overflow value (1) to add to the next column's sum

        // Loop continues as long as there are digits left in l1, l2, or a leftover carry to process
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry; // Starts the current column's total sum with the carry from the previous step

            if (l1 != null) { // Checks if the first list still has remaining digits to process
                sum += l1.val; // Adds the value of the current node from the first list to our running sum
                l1 = l1.next; // Advances the pointer of the first list to its next node
            }

            if (l2 != null) { // Checks if the second list still has remaining digits to process
                sum += l2.val; // Adds the value of the current node from the second list to our running sum
                l2 = l2.next; // Advances the pointer of the second list to its next node
            }

            carry = sum / 10; // Calculates the new carry by dividing sum by 10 (e.g., 14 / 10 = 1)
            current.next = new ListNode(sum % 10); // Creates a new node using only the ones-place digit of the sum (e.g., 14 % 10 = 4)
            current = current.next; // Moves our result builder pointer forward to the newly created node
        }

        return dummy.next; // Returns the actual start of our calculated list, bypassing the initial 0 placeholder
    }

    // Main method acting as our test runner instead of JUnit
    public static void main(String[] args) {
        // Test 1: The example from the prompt (342 + 465 = 807)
        runTest(new int[]{2, 4, 3}, new int[]{5, 6, 4}, new int[]{7, 0, 8}, "Test 1: Standard Input");

        // Test 2: Zeros (0 + 0 = 0)
        runTest(new int[]{0}, new int[]{0}, new int[]{0}, "Test 2: Single Zeros");

        // Test 3: Different lengths and multiple carries (9999 + 99 = 10098)
        runTest(new int[]{9, 9, 9, 9}, new int[]{9, 9}, new int[]{8, 9, 0, 0, 1}, "Test 3: Different Lengths & Carries");

        // Test 4: Extremely large data input to prove overflow protection (Simulating numbers with 20+ digits)
        runTest(
            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 20 ones
            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, // 20 twos
            new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, // Expected: 20 threes
            "Test 4: Large Data Input (20 digits)"
        );

        runTest(new int[]{9, 9, 9}, new int[]{1}, new int[]{0, 0, 0, 1}, "Test 5: Ripple Carry");
    }

    // Helper method to execute a test, evaluate the result, and print PASS/FAIL
    private static void runTest(int[] arr1, int[] arr2, int[] expectedArr, String testName) {
        var l1 = buildList(arr1); // Converts the first raw integer array into a linked list
        var l2 = buildList(arr2); // Converts the second raw integer array into a linked list
        var expected = buildList(expectedArr); // Converts the expected answer array into a linked list
        
        var result = addTwoNumbers(l1, l2); // Calls our core algorithm to get the calculated result list
        
        boolean passed = compareLists(expected, result); // Compares the expected list with the calculated list
        System.out.println(testName + " -> " + (passed ? "PASS" : "FAIL")); // Prints the final outcome of the test to the console
    }

    // Utility method to convert an array of integers into a linked list
    private static ListNode buildList(int[] arr) {
        var dummy = new ListNode(0); // Creates a placeholder node to start building the list
        var current = dummy; // Initializes a pointer to track the end of our growing list
        for (int num : arr) { // Iterates through each integer in the provided array
            current.next = new ListNode(num); // Creates a new node for the integer and attaches it to the list
            current = current.next; // Moves the pointer forward to the newly added node
        }
        return dummy.next; // Returns the constructed list, skipping the dummy head
    }

    // Utility method to check if two linked lists contain exactly the same values in order
    private static boolean compareLists(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) { // Loops as long as both lists still have nodes to compare
            if (l1.val != l2.val) return false; // Returns false immediately if the values at the current position don't match
            l1 = l1.next; // Advances the pointer of the first list
            l2 = l2.next; // Advances the pointer of the second list
        }
        return l1 == null && l2 == null; // Returns true only if both lists finished at the exact same time (meaning they are the same length)
    }
}