package com.interview.notes.code.year.y2026.june.codesignal.test2;

import java.nio.file.Files; // Used to read and write the CSV file.
import java.nio.file.Path; // Used to represent the input.csv file path.
import java.util.*;
import java.util.stream.Collectors; // Used to join large numbers into CSV text.
import java.util.stream.IntStream; // Used to generate large test numbers.

public class Main { // Main class for online compiler.

    static List<Integer> readCsv(Path file) throws Exception { // Reads integers from input.csv.
        var text = Files.readString(file).trim(); // Reads full file content and removes extra spaces.
        if (text.isBlank()) return List.of(); // Returns empty list when file has no data.

        return Arrays.stream(text.split("[,\\s]+")) // Splits by comma, space, or new line.
                .filter(s -> !s.isBlank()) // Removes empty values if any.
                .map(Integer::parseInt) // Converts each string value to integer.
                .toList(); // Collects all integers into a list.
    } // Ends readCsv method.

    static Node buildBST(List<Integer> numbers) { // Builds BST from integer list.
        Node root = null; // Starts with empty tree.
        for (var number : numbers) root = insert(root, number); // Inserts each number into BST.
        return root; // Returns final BST root.
    } // Ends buildBST method.

    static Node insert(Node root, int value) { // Inserts one value into BST.
        if (root == null) return new Node(value); // Creates node if current place is empty.

        if (value < root.value) root.left = insert(root.left, value); // Smaller value goes left.
        else if (value > root.value) root.right = insert(root.right, value); // Larger value goes right.

        return root; // Returns unchanged root after insert.
    } // Ends insert method.

    static List<Integer> inorder(Node root) { // Starts inorder traversal.
        var result = new ArrayList<Integer>(); // Stores sorted BST output.
        inorder(root, result); // Fills result using helper method.
        return result; // Returns traversal result.
    } // Ends inorder method.

    static void inorder(Node node, List<Integer> result) { // Recursive inorder helper.
        if (node == null) return; // Stops when there is no node.

        inorder(node.left, result); // Visits left side first.
        result.add(node.value); // Adds current node value.
        inorder(node.right, result); // Visits right side last.
    } // Ends helper method.

    static void test(String name, String csv, List<Integer> expected) throws Exception { // Runs one test case.
        var file = Path.of("input.csv"); // Creates path for input.csv.
        Files.writeString(file, csv); // Writes test CSV data into input.csv.

        var numbers = readCsv(file); // Reads numbers from input.csv.
        var root = buildBST(numbers); // Builds BST from CSV numbers.
        var actual = inorder(root); // Gets sorted output from BST.

        var status = actual.equals(expected) ? "PASS" : "FAIL"; // Compares actual and expected result.
        System.out.println(status + " - " + name + " -> " + actual.size() + " values"); // Prints result.
    } // Ends test method.

    public static void main(String[] args) throws Exception { // Program starts here.

        test( // Runs normal unsorted test.
                "Normal unsorted data", // Test name.
                "129,64,25,10,200,150", // CSV input.
                List.of(10, 25, 64, 129, 150, 200) // Expected sorted output.
        ); // Ends test call.

        test( // Runs duplicate value test.
                "Duplicate values ignored", // Test name.
                "5,2,5,8,2", // CSV input with duplicates.
                List.of(2, 5, 8) // Expected output without duplicates.
        ); // Ends test call.

        test( // Runs negative number test.
                "Negative values", // Test name.
                "0,-5,10,-2", // CSV input with negative values.
                List.of(-5, -2, 0, 10) // Expected sorted output.
        ); // Ends test call.

        test( // Runs empty file test.
                "Empty file", // Test name.
                "", // Empty CSV input.
                List.of() // Expected empty output.
        ); // Ends test call.

        var largeNumbers = IntStream.rangeClosed(1, 10_000) // Generates numbers from 1 to 10000.
                .boxed() // Converts int stream to Integer stream.
                .collect(Collectors.toCollection(ArrayList::new)); // Stores numbers in mutable list.

        Collections.shuffle(largeNumbers, new Random(1)); // Shuffles data to avoid sorted input issue.

        var largeCsv = largeNumbers.stream() // Creates stream from shuffled numbers.
                .map(String::valueOf) // Converts each number to string.
                .collect(Collectors.joining(",")); // Joins values as CSV text.

        var expectedLarge = IntStream.rangeClosed(1, 10_000) // Generates expected sorted numbers.
                .boxed() // Converts int values to Integer.
                .toList(); // Collects expected result into list.

        test( // Runs large input test.
                "Large 10,000 values", // Test name.
                largeCsv, // Large CSV input.
                expectedLarge // Expected sorted output.
        ); // Ends test call.
    } // Ends main method.

    static class Node { // Custom BST node class.
        int value; // Stores current node value.
        Node left; // Stores smaller values.
        Node right; // Stores larger values.

        Node(int value) { // Constructor to create a new node.
            this.value = value; // Assigns input value to this node.
        } // Ends constructor.
    } // Ends Node class.
} // Ends Main class.