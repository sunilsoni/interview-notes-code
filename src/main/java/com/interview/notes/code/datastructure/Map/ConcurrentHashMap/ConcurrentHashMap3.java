package com.interview.notes.code.datastructure.Map.ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 Now let us understand how concurrency level is accociated with Segment.
 We have created ConcurrentHashMap using the syntax
 ConcurrentHashMap<String, String> cmap = new ConcurrentHashMap<String, String>(65, 0.75F, 63);
 The number of Segments is 64 The number of Entry table array(buckets) in each segment is 02 which is taking care of initial capacity 65.
 To take care of the initial capacity of 65  ,
 128 buckets are created in all.

 Conclusion :
 The internal datastructure of ConcurrentHashMap is Segment array of Entry array. Entry array is bucket in concurrent hashmap and Segment
 is taking care of number of threads which is nothing but concurrency level.
 */
public class ConcurrentHashMap3 {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> cmap = new ConcurrentHashMap<String, String>(65, 0.75F, 63);
    }
}
