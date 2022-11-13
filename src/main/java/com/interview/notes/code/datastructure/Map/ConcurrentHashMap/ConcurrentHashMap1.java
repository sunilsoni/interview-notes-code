package com.interview.notes.code.datastructure.Map.ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/*
ConcurrentHashMap has Segment array which contains Entry array. Entry is singly linked list.
Let us understand the same in detail.

When you create a ConcurrentHashMap using its default constructor , the default initial capacity is 16  ,
load factor is 0.75 and concurrency level is also 16. Initial capacity and load factor are associated with Entry table [] and
not the segements. Concurrency level is associated with the number of segments.

 */
public class ConcurrentHashMap1 {
    public static void main(String[] args) {
        //What is the internal datastructure of ConcurrentHashMap
        ConcurrentHashMap<Integer, String> cmap = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            cmap.put(i, String.valueOf(i));
        }
    }
}
