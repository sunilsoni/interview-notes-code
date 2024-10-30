package com.interview.notes.code.months.oct24.wallmart.test3.test;

import java.util.Collections;
import java.util.List;

/*
Here's the full text for the "Parking Dilemma" question based on the screenshots you provided:

---

### Parking Dilemma

#### Description
There are many cars parked in a parking lot. The parking lot is a straight line with a parking spot for every meter. There are \( n \) cars currently parked, and a roofer wants to cover them with a roof. The requirement is that at least \( k \) cars currently in the lot are covered by the roof. Determine the minimum length of the roof to cover \( k \) cars.

#### Example
**Input**:
- \( n = 4 \)
- `cars = [6, 2, 12, 7]`
- \( k = 3 \)

**Output**:
- `6`

**Explanation**:
Two roofs that cover three cars are possible: one covering spots 2 through 7 with a length of 6, and another covering slots 6 through 12 with a length of 7. The shortest roof that meets the requirement is of length 6.

---

### Function Description

Complete the function `carParkingRoof` in the editor below.

The function `carParkingRoof` has the following parameters:

- `List<Long> cars`: the parking spots where cars are parked.
- `int k`: the number of cars that have to be covered by the roof.

**Returns**:
- `int`: the minimum length of a roof that covers \( k \) cars where they are parked currently.

---

### Constraints
- \( 1 \leq n \leq 10^5 \)
- \( 1 \leq k \leq n \)
- \( 1 \leq cars[i] \leq 10^{14} \)
- All spots taken by cars are unique.

---

### Input Format for Custom Testing

Input from `stdin` will be processed as follows and passed to the function:

1. In the first line, there is a single integer, \( n \), the size of `cars`.
2. Then, \( n \) lines follow. In the \( i^{th} \) of them, there is a single integer `cars[i]`.
3. In the last line, there is a single integer \( k \).

---

### Sample Case 0

**Sample Input**
```
4
2
10
8
17
3
```

**Sample Output**
```
6
```

**Explanation**:
Two roofs that cover three cars are possible: one covering spots 2 through 7 with a length of 6, and another covering slots 6 through 12 with a length of 7. The shortest roof that meets the requirement is of length 6.

---

### Sample Case 1

**Sample Input**
```
4
1
2
3
10
4
```

**Sample Output**
```
10
```

**Explanation**:
All of the cars must be covered. The shortest roof that can cover them has a length of 10 and starts at slot 1 and ends at slot 10.

---

This is the full question, including all descriptions, input format, constraints, and sample cases. Let me know if you need further assistance with solving it!
 */
public class ParkingRoof {

    public static long carParkingRoof(List<Long> cars, int k) {
        // Step 1: Sort the list of parking slot positions
        Collections.sort(cars);

        // Step 2: Initialize the minimum length with a large number
        long minRoofLength = Long.MAX_VALUE;

        // Step 3: Use a sliding window of size `k` to calculate the roof length
        for (int i = 0; i <= cars.size() - k; i++) {
            // Calculate the roof length needed to cover `k` cars in the current window
            long currentRoofLength = cars.get(i + k - 1) - cars.get(i) + 1;

            // Step 4: Update the minimum roof length if a smaller length is found
            minRoofLength = Math.min(minRoofLength, currentRoofLength);
        }

        // Step 5: Return the minimum roof length found
        return minRoofLength;
    }

    public static void main(String[] args) {
        // Example usage
        List<Long> cars = List.of(2L, 10L, 8L, 17L);
        int k = 3;
        System.out.println("Minimum roof length: " + carParkingRoof(cars, k));
    }
}
