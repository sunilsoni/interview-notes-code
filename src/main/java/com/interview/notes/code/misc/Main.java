package com.interview.notes.code.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.IntStream;


public class Main {
	// The index represents a number 0->N where N < 37, the boolean represents if it's a prime number. (a.k.a mapping: number -> isPrime(number)) 
	private static final boolean[] VALID_PRIMES = { false, false, true, true, false, true, false, true, false, false, false, true, false, true, false, false, false, true, false, true, false, false, false, true, false, false, false, false, false, true, false, true, false, false, false, false, false, true, false, false, false };
	
	// Testing configuration
	private static final boolean PRINT_TIMINGS = false;
	private static final boolean PRINT_PERMUTATIONS = false;


	/**
	 * Calculates the bead permutations necklace.
	 * Interesting things to note:
	 * 1. Circular -> 1,2,3,4 == 2,3,4,1 == 3,4,1,2 == 4,1,2,3
	 * 2. Must alternate even / odd. (odd + odd) == even == (even + even) and any number that is even is not a prime. (excl. 2, which we cannot sum anyway).
	 * 3. Must be of even length -> This is because it must alternate. (This is in the question specification)
	 * 4. The inverse is equivalent -> 1,2,3 == 3,2,1. (Wearing the necklace from the other-side of the entrance)
	 * 
	 * There are n! linear permutations
	 * There are (n!/n) == (n-1)! circular permutations. (Because we can turn the necklace n iterations and it's still the same necklace)
	 * There are (n-1)!/2 permutations w.r.t. each circular permutation having a reflection.
	 * *-> Note: The actual question examples doesn't take this into consideration. So I will not consider this.
	 * *-> e.g. The first example: 1 2 3 4 is the equivalent to 4 3 2 1 if necklace is worn from the inverted side.
	 * 			The second example: 1 4 3 2 is a circular equivalent to 4 3 2 1.
	 *          Therefore the provided example answer is incorrect for N=4, because they are both the same permutation
	 * 
	 * Information on circular permutations from:  N. F. Taussig on StackExchange
	 * @see: <a href='https://math.stackexchange.com/a/2395407'>Stack Overflow - </a>
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader in = new BufferedReader(reader);

		String line;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line); // The input is always an int as per specification. No need for checks.
			
			long startTime = System.currentTimeMillis();
			int count = calculatePermutations(n);

			long endTime = System.currentTimeMillis();
			System.out.println(count);
			if (PRINT_TIMINGS) System.err.print("algorithm took: " + (endTime - startTime) + "ms");
		}
	}

	
	private static int calculatePermutations(int n) {
		int[] necklace = IntStream.rangeClosed(1, n).toArray();
		int[] beads = IntStream.rangeClosed(1, n).toArray();

		int beadIndex = 1; // Default the bead with the first value. Iterate once to beadIndex=1;
		
		boolean[] used = new boolean[necklace.length+1];
		used[0] = true; // No 0 value
		used[1] = true; // Already used 1 as the first value.
		
		return countBeadPermutations(necklace, beads, used, beadIndex);
	}
	
	private static int countBeadPermutations(int[] necklace, int[] beads, boolean[] used, int beadIndex) {
		if (beadIndex == necklace.length) {
			boolean isNecklaceValid = isValidPermutation(necklace);
			if (PRINT_PERMUTATIONS) System.out.println(Arrays.toString(necklace) + ": " + isNecklaceValid);
			
			return isNecklaceValid ? 1 : 0;
		}
		
		// Check the latest 2 values for primality. No reason to keep going if they are not primes.
		// Note: This is an extremely important check. This saves an immense amount of time for high N values.
		if ((beadIndex > 1) &&
			(!isPrimeNumber(necklace[beadIndex-1] + necklace[beadIndex-2]))) {
			return 0;
		}

		
		int count = 0;
		boolean isEvenBead = beadIndex % 2 != 0; // Every odd index is an even bead because we always defined necklace[0] as bead-1

		for (int i=(isEvenBead ? 1 : 0); i < necklace.length; i+=2) { // 
			if (used[beads[i]]) continue;
			
			necklace[beadIndex] = beads[i];
			used[beads[i]] = true;
			count += countBeadPermutations(necklace, beads, used, beadIndex+1);
			used[beads[i]] = false;
		}

		
		return count;
	}

	private static boolean isValidPermutation(int[] beads) {
		for (int i = 0; i < beads.length; i++) {
			int j = (i + 1) % beads.length;

			if (!isPrimeNumber(beads[i] + beads[j])) return false;
		}
		return true;
	}

	private static boolean isPrimeNumber(int number) {
		return VALID_PRIMES[number];
	}
}