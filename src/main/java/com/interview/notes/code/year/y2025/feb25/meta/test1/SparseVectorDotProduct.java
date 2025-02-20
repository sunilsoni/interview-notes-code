package com.interview.notes.code.year.y2025.feb25.meta.test1;

import java.util.*;
import java.util.stream.Collectors;

/*
• Given an array of an increasing integer sequence, • find the Kth • number in the missing sequence between these integers.
• Input: • [2,4,7,8,9,13] -›•missing number sequence is• [3,5,6,10,11,12]
• K•=• 2
• Output : • 5
•Since 5•is • the 2nd • number in that missing sequence

 */
public class SparseVectorDotProduct {

    /**
     * Computes the dot product of two sparse vectors.
     * The vectors are represented as a Map from index to nonzero value.
     * It uses Java 8 streams to filter only the indices that exist in both vectors,
     * multiply their values, and sum the results.
     *
     * @param vectorA the first sparse vector
     * @param vectorB the second sparse vector
     * @return the dot product as a double
     */
    public static double dotProduct(Map<Integer, Double> vectorA, Map<Integer, Double> vectorB) {
        // Iterate over entries of vectorA, filter common indices, multiply and sum.
        return vectorA.entrySet().stream()
                .filter(e -> vectorB.containsKey(e.getKey()))
                .mapToDouble(e -> e.getValue() * vectorB.get(e.getKey()))
                .sum();
    }

    /**
     * Helper method to create a sparse vector from arrays of indices and values.
     *
     * @param indices an array of indices (assumed to be non-negative)
     * @param values  an array of corresponding nonzero values
     * @return a Map representing the sparse vector
     */
    public static Map<Integer, Double> createSparseVector(int[] indices, double[] values) {
        if (indices.length != values.length) {
            throw new IllegalArgumentException("Indices and values arrays must have the same length");
        }
        Map<Integer, Double> vector = new HashMap<>();
        for (int i = 0; i < indices.length; i++) {
            vector.put(indices[i], values[i]);
        }
        return vector;
    }

    /**
     * Runs various tests on the dotProduct method.
     * Uses simple print statements to indicate PASS/FAIL for each test.
     */
    public static void main(String[] args) {
        // List to keep track of test results
        List<String> testResults = new ArrayList<>();

        // Test Case 1: Simple test with common indices.
        {
            int[] indicesA = {0, 2, 4};
            double[] valuesA = {1.0, 3.0, -5.0};
            Map<Integer, Double> vectorA = createSparseVector(indicesA, valuesA);

            int[] indicesB = {0, 2, 3};
            double[] valuesB = {4.0, -2.0, 6.0};
            Map<Integer, Double> vectorB = createSparseVector(indicesB, valuesB);

            // Expected dot product: 1.0*4.0 + 3.0*(-2.0) = 4.0 - 6.0 = -2.0
            double expected = -2.0;
            double result = dotProduct(vectorA, vectorB);
            if (Double.compare(result, expected) == 0) {
                testResults.add("Test Case 1 (Simple common indices): PASS");
            } else {
                testResults.add("Test Case 1 (Simple common indices): FAIL (Expected " + expected + ", got " + result + ")");
            }
        }

        // Test Case 2: No common indices.
        {
            int[] indicesA = {1, 3, 5};
            double[] valuesA = {2.0, 4.0, 6.0};
            Map<Integer, Double> vectorA = createSparseVector(indicesA, valuesA);

            int[] indicesB = {0, 2, 4};
            double[] valuesB = {1.0, 3.0, 5.0};
            Map<Integer, Double> vectorB = createSparseVector(indicesB, valuesB);

            // Expected dot product: 0.0 since no index is common.
            double expected = 0.0;
            double result = dotProduct(vectorA, vectorB);
            if (Double.compare(result, expected) == 0) {
                testResults.add("Test Case 2 (No common indices): PASS");
            } else {
                testResults.add("Test Case 2 (No common indices): FAIL (Expected " + expected + ", got " + result + ")");
            }
        }

        // Test Case 3: One vector is empty.
        {
            Map<Integer, Double> vectorA = new HashMap<>(); // empty vector

            int[] indicesB = {0, 1, 2};
            double[] valuesB = {1.0, 2.0, 3.0};
            Map<Integer, Double> vectorB = createSparseVector(indicesB, valuesB);

            double expected = 0.0;
            double result = dotProduct(vectorA, vectorB);
            if (Double.compare(result, expected) == 0) {
                testResults.add("Test Case 3 (One empty vector): PASS");
            } else {
                testResults.add("Test Case 3 (One empty vector): FAIL (Expected " + expected + ", got " + result + ")");
            }
        }

        // Test Case 4: Both vectors are empty.
        {
            Map<Integer, Double> vectorA = new HashMap<>();
            Map<Integer, Double> vectorB = new HashMap<>();

            double expected = 0.0;
            double result = dotProduct(vectorA, vectorB);
            if (Double.compare(result, expected) == 0) {
                testResults.add("Test Case 4 (Both vectors empty): PASS");
            } else {
                testResults.add("Test Case 4 (Both vectors empty): FAIL (Expected " + expected + ", got " + result + ")");
            }
        }

        // Test Case 5: Large data input simulation.
        {
            // Simulate two large sparse vectors with 1,000,000 possible indices but only 10,000 nonzero entries each.
            int size = 1_000_000;
            int nonZeroCount = 10_000;
            Random rand = new Random(42); // fixed seed for reproducibility

            Map<Integer, Double> vectorA = new HashMap<>();
            Map<Integer, Double> vectorB = new HashMap<>();

            // Create random nonzero indices for vectorA and vectorB.
            Set<Integer> indicesASet = new HashSet<>();
            while (indicesASet.size() < nonZeroCount) {
                indicesASet.add(rand.nextInt(size));
            }
            for (Integer index : indicesASet) {
                // Values between -10 and 10
                vectorA.put(index, rand.nextDouble() * 20 - 10);
            }

            Set<Integer> indicesBSet = new HashSet<>();
            while (indicesBSet.size() < nonZeroCount) {
                indicesBSet.add(rand.nextInt(size));
            }
            for (Integer index : indicesBSet) {
                vectorB.put(index, rand.nextDouble() * 20 - 10);
            }

            // Compute dot product using our method
            double result = dotProduct(vectorA, vectorB);

            // To validate, compute dot product manually over the intersection.
            Set<Integer> commonIndices = vectorA.keySet().stream()
                    .filter(vectorB::containsKey)
                    .collect(Collectors.toSet());
            double expected = commonIndices.stream()
                    .mapToDouble(i -> vectorA.get(i) * vectorB.get(i))
                    .sum();

            if (Double.compare(result, expected) == 0) {
                testResults.add("Test Case 5 (Large sparse vectors): PASS");
            } else {
                testResults.add("Test Case 5 (Large sparse vectors): FAIL (Expected " + expected + ", got " + result + ")");
            }
        }

        // Output all test results
        System.out.println("Sparse Vector Dot Product Test Results:");
        testResults.forEach(System.out::println);
    }
}