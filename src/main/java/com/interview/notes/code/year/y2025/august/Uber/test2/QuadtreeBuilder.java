package com.interview.notes.code.year.y2025.august.Uber.test2;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
/*

**Question:**
A Quadtree is a tree data structure in which each internal node is either a leaf node or has exactly 4 children. Quadtrees are most often used to partition a two-dimensional space by recursively subdividing it into four quadrants or regions. One of the common use cases of a quadtree is image compression.

**Example:**

**Input Image:**

```
+---+---+---+---+
| 2 | 2 | 3 | 3 |
+---+---+---+---+
| 2 | 2 | 3 | 3 |
+---+---+---+---+
| 4 | 2 | 5 | 5 |
+---+---+---+---+
| 2 | 3 | 5 | 5 |
+---+---+---+---+
```

**Quadtree Representation:**

```
+-------+-------+
|       |   3   |
|   2   |       |
|       |-------|
|-------|   5   |
|4   2  |       |
|---|---|       |
|2  | 3 |       |
+-------+-------+
```

**Task:**
Design the **quadtree data structure** and write a **function** that builds a quadtree for an input image, where the image is given as a 2D array of integers.

---


 */

/**
 * QuadtreeBuilder
 * - Builds a quadtree from a 2D integer image.
 * - Decompresses the quadtree back to a 2D image for verification.
 * - Runs PASS/FAIL tests (no JUnit; simple main).
 */
public class QuadtreeBuilder {

    /**
     * Public API: Build quadtree for the entire image.
     *
     * @param img input 2D array
     * @return root QuadNode
     */
    public static QuadNode buildQuadtree(int[][] img) {
        // Validate input to avoid NPEs and undefined behavior
        if (img == null || img.length == 0 || img[0].length == 0) {
            // For empty image, return null to represent an empty tree
            return null;
        }
        // Call recursive builder on full region [0..h) x [0..w)
        return build(img, 0, 0, img.length, img[0].length);
    }

    /**
     * Recursive builder for region [y..y+h), [x..x+w).
     */
    private static QuadNode build(int[][] img, int x, int y, int h, int w) {
        // Base: if region is empty (defensive for odd splits), return null
        if (h <= 0 || w <= 0) return null;

        // If region has single pixel, trivially uniform → leaf
        if (h == 1 && w == 1) {
            return new QuadNode(img[y][x], x, y, h, w);
        }

        // If region is uniform (all pixels equal), make a leaf
        if (isUniform(img, x, y, h, w)) {
            int val = img[y][x]; // pick the first pixel as value
            return new QuadNode(val, x, y, h, w);
        }

        // Not uniform → split region into four quadrants
        // Compute midpoints; left/top get floor size, right/bottom get the remainder
        int hTop = h / 2;       // height of top half
        int hBot = h - hTop;    // height of bottom half
        int wLeft = w / 2;      // width of left half
        int wRight = w - wLeft; // width of right half

        // Build children in NW, NE, SW, SE order
        QuadNode nw = build(img, x, y, hTop, wLeft);  // top-left
        QuadNode ne = build(img, x + wLeft, y, hTop, wRight); // top-right
        QuadNode sw = build(img, x, y + hTop, hBot, wLeft);  // bottom-left
        QuadNode se = build(img, x + wLeft, y + hTop, hBot, wRight); // bottom-right

        // Create internal node with these children
        return new QuadNode(nw, ne, sw, se, x, y, h, w);
    }

    /**
     * Check if a region is uniform (all pixels same).
     * Java 8 Streams used for clarity.
     */
    private static boolean isUniform(int[][] img, int x, int y, int h, int w) {
        // Get reference value from first cell
        final int ref = img[y][x];

        // For each row r in [y..y+h), ensure all columns c in [x..x+w) equal ref
        // Using Streams with nested IntStream and allMatch
        return IntStream.range(y, y + h).allMatch(r ->
                IntStream.range(x, x + w).allMatch(c -> img[r][c] == ref)
        );
    }

    /**
     * Decompress the quadtree back into an image of size h x w.
     * This is used to verify correctness in tests.
     */
    public static void decompress(QuadNode root, int[][] out, int x, int y, int h, int w) {
        // If root is null (empty region), do nothing
        if (root == null || h <= 0 || w <= 0) return;

        // If leaf, fill the whole region with leaf value
        if (root.isLeaf) {
            // Fill region [y..y+h), [x..x+w) with root.value
            for (int r = y; r < y + h; r++) {
                // Arrays.fill on each row segment for speed
                Arrays.fill(out[r], x, x + w, root.value);
            }
            return; // done
        }

        // Internal node → recurse into four children with the same splitting logic
        int hTop = h / 2;
        int hBot = h - hTop;
        int wLeft = w / 2;
        int wRight = w - wLeft;

        // Decompress each child into its quadrant if child is not null
        if (root.nw != null) decompress(root.nw, out, x, y, hTop, wLeft);
        if (root.ne != null) decompress(root.ne, out, x + wLeft, y, hTop, wRight);
        if (root.sw != null) decompress(root.sw, out, x, y + hTop, hBot, wLeft);
        if (root.se != null) decompress(root.se, out, x + wLeft, y + hTop, hBot, wRight);
    }

    /**
     * Compare two matrices for equality.
     */
    private static boolean equalsMatrix(int[][] a, int[][] b) {
        // Different shapes → not equal
        if (a == null || b == null) return a == b;
        if (a.length != b.length) return false;
        if (a.length == 0) return b.length == 0;
        if (a[0].length != b[0].length) return false;

        // Compare row by row
        for (int r = 0; r < a.length; r++) {
            // Use Arrays.equals for row comparison
            if (!Arrays.equals(a[r], b[r])) return false;
        }
        return true; // all rows equal
    }

    /**
     * Utility: deep copy of matrix.
     */
    private static int[][] copyMatrix(int[][] m) {
        // Handle null
        if (m == null) return null;
        // Create new array of same size
        int[][] c = new int[m.length][m.length == 0 ? 0 : m[0].length];
        // Copy each row
        for (int i = 0; i < m.length; i++) {
            c[i] = Arrays.copyOf(m[i], m[i].length);
        }
        return c; // return copy
    }

    /**
     * Utility: print matrix (for small examples).
     */
    private static void printMatrix(int[][] a) {
        // Guard for null
        if (a == null) {
            System.out.println("(null)");
            return;
        }
        // Print each row
        for (int[] row : a) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Build a test image based on blocks to simulate compressible structure.
     * Example: fill blocks of size blockH x blockW with same value.
     */
    private static int[][] makeBlockyImage(int h, int w, int blockH, int blockW) {
        // Create array
        int[][] img = new int[h][w];
        // Fill with block pattern using nested loops
        for (int r = 0; r < h; r++) {
            // Compute block row group index
            int br = r / blockH;
            for (int c = 0; c < w; c++) {
                // Compute block col group index
                int bc = c / blockW;
                // Value derived from block indices for repeatability
                img[r][c] = (br + bc) % 7; // small palette to form uniform blocks
            }
        }
        return img; // return constructed image
    }

    /**
     * Main: runs tests (PASS/FAIL) including large data.
     */
    public static void main(String[] args) {
        // Test 1: Given 4x4 example
        int[][] example = {
                {2, 2, 3, 3},
                {2, 2, 3, 3},
                {4, 2, 5, 5},
                {2, 3, 5, 5}
        };
        runOneTest("Example 4x4", example);

        // Test 2: Uniform 5x7 (should be one leaf)
        int[][] uniform = new int[5][7];
        for (int r = 0; r < 5; r++) Arrays.fill(uniform[r], 9);
        runOneTest("Uniform 5x7", uniform);

        // Test 3: 1x1
        int[][] single = {{42}};
        runOneTest("Single 1x1", single);

        // Test 4: Checkerboard 6x6 (worst case: splits deep)
        int[][] checker = new int[6][6];
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 6; c++) {
                checker[r][c] = (r + c) % 2;
            }
        }
        runOneTest("Checkerboard 6x6", checker);

        // Test 5: Non-square 3x5 random small
        int[][] randomSmall = new int[3][5];
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 5; c++) {
                randomSmall[r][c] = tlr.nextInt(0, 3);
            }
        }
        runOneTest("Random 3x5", randomSmall);

        // Test 6: Large 512x512 blocky (compressible)
        int[][] block512 = makeBlockyImage(512, 512, 16, 16);
        runLargeTest("Large Blocky 512x512", block512);

        // Test 7: Large 1024x768 blocky (rectangular)
        int[][] block1024x768 = makeBlockyImage(1024, 768, 32, 24);
        runLargeTest("Large Blocky 1024x768", block1024x768);

        // Done
        System.out.println("\nAll tests completed.");
    }

    /**
     * Helper to run a small/medium test with PASS/FAIL output and quick prints.
     */
    private static void runOneTest(String name, int[][] img) {
        System.out.println("\n--- " + name + " ---");
        // Build tree
        QuadNode root = buildQuadtree(img);
        // Decompress to compare
        int[][] out = new int[img.length][img[0].length];
        decompress(root, out, 0, 0, img.length, img[0].length);
        // Check equality
        boolean ok = equalsMatrix(img, out);
        // Print small matrices for visual confirmation if size is small
        if (img.length <= 8 && img[0].length <= 8) {
            System.out.println("Original:");
            printMatrix(img);
            System.out.println("Decompressed:");
            printMatrix(out);
            System.out.println("Tree (preorder):");
            System.out.print(root == null ? "(null)\n" : root.toString());
        }
        // Report PASS/FAIL
        System.out.println("Result: " + (ok ? "PASS" : "FAIL"));
    }

    /**
     * Helper to run a large test with timing.
     */
    private static void runLargeTest(String name, int[][] img) {
        System.out.println("\n--- " + name + " ---");
        // Time build
        long t1 = System.nanoTime();
        QuadNode root = buildQuadtree(img);
        long t2 = System.nanoTime();

        // Time decompress
        int[][] out = new int[img.length][img[0].length];
        long t3 = System.nanoTime();
        decompress(root, out, 0, 0, img.length, img[0].length);
        long t4 = System.nanoTime();

        // Validate
        boolean ok = equalsMatrix(img, out);

        // Print timings in ms
        double buildMs = (t2 - t1) / 1_000_000.0;
        double decompMs = (t4 - t3) / 1_000_000.0;

        System.out.printf("Build time: %.2f ms, Decompress time: %.2f ms%n", buildMs, decompMs);
        System.out.println("Result: " + (ok ? "PASS" : "FAIL"));
    }

    /**
     * QuadNode represents a node in the quadtree.
     * Each node is either a leaf (stores a value) or internal (has 4 children).
     */
    static final class QuadNode {
        // Indicates whether this node is a leaf (uniform region)
        final boolean isLeaf;
        // The pixel value if leaf; undefined for internal nodes
        final int value;
        // Children for internal node; null for leaf
        final QuadNode nw, ne, sw, se;
        // Optional: region metadata for debugging/pretty print (not required for correctness)
        final int x, y, h, w;

        // Constructor for a leaf node
        QuadNode(int value, int x, int y, int h, int w) {
            this.isLeaf = true;    // mark as leaf
            this.value = value;    // store uniform pixel value
            this.nw = this.ne = this.sw = this.se = null; // no children
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w; // store region meta for reference
        }

        // Constructor for an internal node with 4 children
        QuadNode(QuadNode nw, QuadNode ne, QuadNode sw, QuadNode se, int x, int y, int h, int w) {
            this.isLeaf = false;   // internal node
            this.value = 0;        // value not used for internal nodes
            this.nw = nw;
            this.ne = ne;
            this.sw = sw;
            this.se = se; // attach children
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;         // region meta
        }

        // Preorder string for light debugging/visual check
        @Override
        public String toString() {
            // Use a StringBuilder to build a compact representation
            StringBuilder sb = new StringBuilder();
            toString(sb, 0);
            return sb.toString();
        }

        // Helper: build indented tree string
        private void toString(StringBuilder sb, int depth) {
            // indent based on depth for readability
            for (int i = 0; i < depth; i++) sb.append("  ");
            if (isLeaf) {
                // print leaf with value and region size
                sb.append("Leaf(val=").append(value)
                        .append(", x=").append(x).append(", y=").append(y)
                        .append(", h=").append(h).append(", w=").append(w)
                        .append(")\n");
            } else {
                // print internal node then recurse into children
                sb.append("Node(x=").append(x).append(", y=").append(y)
                        .append(", h=").append(h).append(", w=").append(w)
                        .append(")\n");
                if (nw != null) nw.toString(sb, depth + 1);
                if (ne != null) ne.toString(sb, depth + 1);
                if (sw != null) sw.toString(sb, depth + 1);
                if (se != null) se.toString(sb, depth + 1);
            }
        }
    }
}