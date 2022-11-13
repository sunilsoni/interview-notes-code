package com.interview.notes.code.datastructure.Map.ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Demo2 illustrates how initial capacity is associated with Entry table array.
 * We have created ConcurrentHashMap using the syntax
 * ConcurrentHashMap<String, String> cmap = new ConcurrentHashMap<String, String>(63);
 * In this case , the number of segments does not change it is the default 16 Segments whereas the number of elements in
 * Entry table array is 04 in each segement(Four buckets in each Segment) so as the ConcurrentHashmap has 64 buckets.
 * These Entry table array has load factor 0.75 , so when the individual Entry table reaches the threshold ie 3 in our case the
 * Entry table array size will double and become 08 for that Segment.
 */
public class ConcurrentHashMap2 {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> cmap = new ConcurrentHashMap<String, String>(63);
    }
}
