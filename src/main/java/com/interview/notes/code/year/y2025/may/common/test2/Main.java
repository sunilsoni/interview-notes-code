package com.interview.notes.code.year.y2025.may.common.test2;

import java.util.*;
import java.util.stream.*;

class SessionDetail {
    String user;
    int startTime;
    int endTime;
    public SessionDetail(String user, int startTime, int endTime) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

public class Main {
    public static void main(String[] args) {
        List<SessionDetail> sessions = Arrays.asList(
            new SessionDetail("u1", 100, 200),
            new SessionDetail("u2", 200, 300),
            new SessionDetail("u1", 300, 600),
            new SessionDetail("u2", 400, 600),
            new SessionDetail("u1", 500, 700),
            new SessionDetail("u1", 800, 1000)
        );
        // Test cases: {user, Qstart, Qend, expected}
        Object[][] tests = {
            {"u1", 100, 700, 500},   // 100–200(100) + 300–700(400)
            {"u1", 200, 800, 400},   // 300–700(400)
            {"u1", 400, 800, 300},   // from example: 400–700(300)
            {"u2", 0,   1000, 200},  // u2 has 200–300(100) + 400–600(200) but no overlap → 300
            {"u3", 0,   1000, 0},    // no sessions
            {"u1", 600, 650, 50},    // inside one session
        };
        for (int i = 0; i < tests.length; i++) {
            String u      = (String)tests[i][0];
            int    qs     = (int)tests[i][1];
            int    qe     = (int)tests[i][2];
            int    expect = (int)tests[i][3];
            int    actual = totalLoginTime(sessions, u, qs, qe);
            System.out.printf("Test %d: expect=%d, actual=%d → %s%n",
                i+1, expect, actual,
                (expect == actual ? "PASS" : "FAIL"));
        }
        // Large data test (random sessions)
        Random rnd = new Random(42);
        List<SessionDetail> big = IntStream.range(0, 100_000)
            .mapToObj(i -> new SessionDetail("uX",
                rnd.nextInt(1_000_000),
                rnd.nextInt(1_000_000) + 1))
            .collect(Collectors.toList());
        // Should run quickly
        System.out.println("Big test runtime start");
        long t0 = System.currentTimeMillis();
        int total = totalLoginTime(big, "uX", 0, 1_000_000);
        long t1 = System.currentTimeMillis();
        System.out.printf("Big test: total=%d in %dms%n", total, t1-t0);
    }

    public static int totalLoginTime(
        List<SessionDetail> sessionDetails,
        String user,
        int queryStartTime,
        int queryEndTime
    ) {
        List<int[]> clips = sessionDetails.stream()
            .filter(s -> s.user.equals(user))
            .map(s -> new int[]{
                Math.max(s.startTime, queryStartTime),
                Math.min(s.endTime, queryEndTime)
            })
            .filter(a -> a[0] < a[1])
            .sorted(Comparator.comparingInt(a -> a[0]))
            .collect(Collectors.toList());

        int total = 0;
        if (!clips.isEmpty()) {
            int curStart = clips.get(0)[0];
            int curEnd   = clips.get(0)[1];
            for (int i = 1; i < clips.size(); i++) {
                int[] c = clips.get(i);
                if (c[0] <= curEnd) {
                    curEnd = Math.max(curEnd, c[1]);
                } else {
                    total += curEnd - curStart;
                    curStart = c[0];
                    curEnd   = c[1];
                }
            }
            total += curEnd - curStart;
        }
        return total;
    }
}