package com.interview.notes.code.year.y2024.dec24.ibm.test1;

/*
WORKING



**Problem Description:**
The challenge is to optimize the procurement of resources. The objective is to ensure the provision of the required quantities of edge devices and peripherals while minimizing costs. This involves allowing for flexibility in purchasing and accommodating extra equipment, as necessary. The environment requires:
- `x` edge devices.
- `y` input peripherals.

The primary goal is to minimize the total expenditure while ensuring that the environment is fully equipped.

---

### **Task**
Write a function `getMinimumCost` that determines the minimum cost to fulfill the requirements using the following options:
1. Purchase an edge device individually.
2. Purchase a peripheral individually.
3. Purchase a bundle containing both an edge device and a peripheral.

The function should compute and return the minimum expenditure.

---

### **Input Format**
1. The first line contains an integer `edgeDeviceCost`: The cost of an edge device.
2. The second line contains an integer `inputPeripheralCost`: The cost of a peripheral.
3. The third line contains an integer `bundleCost`: The cost of a bundle containing one edge device and one peripheral.
4. The fourth line contains an integer `x`: The number of edge devices required.
5. The fifth line contains an integer `y`: The number of peripherals required.

---

### **Output Format**
Return the minimum cost as a single integer.

---

### **Constraints**
- \( 1 \leq \text{edgeDeviceCost}, \text{inputPeripheralCost}, \text{bundleCost}, x, y \leq 10^9 \).

---

### **Examples**

#### **Sample Input 0**
```
3
2
1
4
3
```

#### **Sample Output 0**
```
4
```

**Explanation:**
The optimal strategy is to buy 4 bundles for a total cost of 4 units. This provides the environment with 4 edge devices and 4 peripherals, which satisfies the requirements (\(x \leq 4\) and \(y \leq 4\)).

---

#### **Sample Input 1**
```
1
20
5
9
1
```

#### **Sample Output 1**
```
13
```

**Explanation:**
The optimal strategy is to buy 8 edge devices individually and 1 bundle. This ensures the environment has 9 edge devices and 1 peripheral. The total cost is \(8 \times 1 + 1 \times 5 = 13\).

 */
public class MinimumCostCalculator {
    public static long getMinimumCost(int edgeDeviceCost, int inputPeripheralCost, int bundleCost, int x, int y) {
        // Convert to long for safety with large values
        long X = x, Y = y;
        long edc = edgeDeviceCost, ipc = inputPeripheralCost, bc = bundleCost;

        // Scenario A: No bundles
        long costA = X * edc + Y * ipc;

        // Scenario B: Bundles = min(x,y)
        long m = Math.min(X, Y);
        long costB = m * bc + (X - m) * edc + (Y - m) * ipc;

        // Scenario C: Bundles = max(x,y)
        long M = Math.max(X, Y);
        long costC = M * bc
                + Math.max(0, X - M) * edc
                + Math.max(0, Y - M) * ipc;

        return Math.min(costA, Math.min(costB, costC));
    }

    // Simple main method for testing (no JUnit)
    public static void main(String[] args) {
        // Test Case from provided sample (Sample Case 0):
        // edgeDeviceCost = 3, inputPeripheralCost = 2, bundleCost = 1, x=4, y=3
        // Expected output = 4
        long res1 = getMinimumCost(3, 2, 1, 4, 3);
        System.out.println("Test Case 0: " + (res1 == 4 ? "PASS" : "FAIL") + " (Expected 4, Got " + res1 + ")");

        // Sample Case 1:
        // edgeDeviceCost = 1, inputPeripheralCost = 20, bundleCost = 5, x=9, y=1
        // Expected output = 13
        // Explanation: best is 8 edge devices + 1 bundle = 8*1 + 1*5 = 13 total.
        long res2 = getMinimumCost(1, 20, 5, 9, 1);
        System.out.println("Test Case 1: " + (res2 == 13 ? "PASS" : "FAIL") + " (Expected 13, Got " + res2 + ")");

        // Another example:
        // edgeDeviceCost = 1, inputPeripheralCost = 2, bundleCost = 2, x=2, y=1
        // Expected minimal cost = 3 (from problem description)
        long res3 = getMinimumCost(1, 2, 2, 2, 1);
        System.out.println("Test Case 2: " + (res3 == 3 ? "PASS" : "FAIL") + " (Expected 3, Got " + res3 + ")");

        // Edge Case: x=y, large costs
        // If x=y, buying exactly x bundles might be optimal if bc < edc+ipc.
        // Let's test x=5, y=5, edc=3, ipc=4, bc=6
        // Scenario A: 5*3 + 5*4 = 15 + 20 =35
        // Scenario B: min=5, costB=5*6+(5-5)*3+(5-5)*4=30
        // Scenario C: same as B since x=y
        // result=30
        long res4 = getMinimumCost(3, 4, 6, 5, 5);
        System.out.println("Test Case 3: " + (res4 == 30 ? "PASS" : "FAIL") + " (Expected 30, Got " + res4 + ")");

        // Large Inputs test for performance (no expected exact value, just ensure no overflow or crash)
        long res5 = getMinimumCost(1000000000, 1000000000, 500000000, 1000000000, 1000000000);
        System.out.println("Test Case 4: Large input test completed. Result=" + res5);
    }
}
