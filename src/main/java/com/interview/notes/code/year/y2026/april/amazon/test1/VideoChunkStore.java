package com.interview.notes.code.year.y2026.april.amazon.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

// --- Interface ---
public interface VideoChunkStore {

    void put(String fileName, long startTime, long endTime);

    List<VideoChunk> search(long t1, long t2);

    record VideoChunk(String fileName, long startTime, long endTime) {}
}

// --- Implementation ---
  class VideoChunkStoreImpl implements VideoChunkStore {

    private final NavigableMap<Long, VideoChunk> chunks = new ConcurrentSkipListMap<>();

    @Override
    public void put(String fileName, long startTime, long endTime) {
        chunks.put(startTime, new VideoChunk(fileName, startTime, endTime));
    }

    @Override
    public List<VideoChunk> search(long t1, long t2) {
        List<VideoChunk> result = new ArrayList<>();
        for (VideoChunk c : chunks.headMap(t2, true).values()) {
            if (c.endTime() > t1) {
                result.add(c);
            }
        }
        return result;
    }
}