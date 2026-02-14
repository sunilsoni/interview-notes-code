public class HitCounter {
    // Fixed size of 60 seconds as per requirements
    private final int[] times = new int[60];
    private final int[] counts = new int[60];

    public static void main(String[] args) {
        var counter = new HitCounter();

        // Example Trace: Hit(1)x4, Hit(61)x2, GetHits(61)
        counter.hit(1); counter.hit(1); counter.hit(1); counter.hit(1); // Bucket[1] = 4 hits
        counter.hit(61); counter.hit(61); // Bucket[1] resets because 61 != 1. Bucket[1] = 2 hits.

        System.out.println("Hits at 61: " + counter.getHits(61)); // Expect 2
    }

    /**
     * Records a hit at the given timestamp.
     * Guaranteed O(1) Worst-Case Time.
     */
    public void hit(int timestamp) {
        // Map the timestamp to an index 0-59 using modulo
        int idx = timestamp % 60;

        // If the timestamp at this index is different, it means
        // the old data is from a previous minute and should be reset
        if (times[idx] != timestamp) {
            times[idx] = timestamp; // Update to the current second
            counts[idx] = 1;        // Reset the count for this new second
        } else {
            // If we are still in the same second, just increment the count
            counts[idx]++;
        }
    }

    /**
     * Returns the number of hits in the past 60 seconds.
     * Guaranteed O(1) Worst-Case Time (always exactly 60 iterations).
     */
    public int getHits(int timestamp) {
        int total = 0;
        // Loop through all 60 buckets to sum hits within the window
        for (int i = 0; i < 60; i++) {
            // A bucket is valid only if it happened within the last 60 seconds
            // Logic: current_time - bucket_time < 60
            if (timestamp - times[i] < 60) {
                total += counts[i]; // Add this second's hits to the total
            }
        }
        return total;
    }
}