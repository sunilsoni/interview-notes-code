package com.interview.notes.code.year.y2025.november.oci.test6;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This program processes sign-in and sign-out logs for multiple users.
 * It stores every session per user (not overwriting previous sessions).
 * A user appears in the final result if any of their sessions
 * have (signOut - signIn) ≤ maxSpan.
 */
public class SignInSignOutMultiSession {

    /**
     * Processes all logs and returns a list of valid user IDs.
     *
     * @param logs    list of log entries: "userId timestamp action"
     * @param maxSpan max time (in seconds) allowed between sign-in and sign-out
     * @return list of valid user IDs sorted ascending
     */
    public static List<String> processLogs(List<String> logs, int maxSpan) {

        // activeSignIn tracks latest unmatched sign-in for each user
        Map<String, Integer> activeSignIn = new HashMap<>();

        // userSessions stores full history of sessions per user
        Map<String, List<Session>> userSessions = new HashMap<>();

        System.out.println("📜 Parsing and recording sessions...");

        for (String log : logs) {
            String[] parts = log.split(" ");
            String userId = parts[0];
            int timestamp = Integer.parseInt(parts[1]);
            String action = parts[2];

            if (action.equals("sign-in")) {
                // Start a new session
                activeSignIn.put(userId, timestamp);
                System.out.println("➡️  " + userId + " signed in at " + timestamp);
            } else if (action.equals("sign-out")) {
                // Pair with most recent unmatched sign-in
                if (activeSignIn.containsKey(userId)) {
                    int signIn = activeSignIn.get(userId);
                    int signOut = timestamp;

                    if (signOut > signIn) {
                        Session session = new Session(signIn, signOut);
                        userSessions
                                .computeIfAbsent(userId, k -> new ArrayList<>())
                                .add(session);
                        System.out.println("⬅️  " + userId + " signed out at " + signOut +
                                " | Session " + session);
                    } else {
                        System.out.println("⚠️  Ignored invalid session for " + userId +
                                ": sign-out before sign-in");
                    }

                    // End this session
                    activeSignIn.remove(userId);
                } else {
                    System.out.println("⚠️  " + userId + " sign-out ignored — no active sign-in");
                }
            }
        }

        System.out.println("\n🧮 Evaluating valid users (Δ ≤ " + maxSpan + ")...");

        // Determine which users have at least one valid session
        List<String> validUsers = userSessions.entrySet().stream()
                .filter(entry ->
                        entry.getValue().stream().anyMatch(s -> s.getDuration() <= maxSpan))
                .peek(entry -> {
                    List<Session> sessions = entry.getValue();
                    System.out.println("👤 User " + entry.getKey() +
                            " → " + sessions +
                            " | Durations=" + sessions.stream().map(Session::getDuration).collect(Collectors.toList()));
                })
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.toList());

        System.out.println("\n✅ Valid Users: " + validUsers);
        return validUsers;
    }

    // --------------------------------------------------------------------
    // 🧪 TESTING METHOD — Simple main() for PASS/FAIL
    // --------------------------------------------------------------------
    public static void main(String[] args) {

        java.util.function.BiConsumer<List<String>, List<String>> test = (actual, expected) -> {
            boolean pass = actual.equals(expected);
            System.out.println("Expected: " + expected + " | Actual: " + actual +
                    " | Result: " + (pass ? "PASS ✅" : "FAIL ❌"));
        };

        // -------------------------------------------------------------
        // TEST 1 — Standard Example
        // -------------------------------------------------------------
        System.out.println("\n===== TEST 1 =====");
        List<String> logs1 = Arrays.asList(
                "99 1 sign-in", "100 10 sign-in", "50 20 sign-in",
                "100 15 sign-out", "50 26 sign-out", "99 2 sign-out"
        );
        test.accept(processLogs(logs1, 5), Arrays.asList("99", "100"));

        // -------------------------------------------------------------
        // TEST 2 — Multiple Sessions per User
        // -------------------------------------------------------------
        System.out.println("\n===== TEST 2 =====");
        List<String> logs2 = Arrays.asList(
                "30 0 sign-in", "30 10 sign-out",     // Δ=10 ✅
                "30 15 sign-in", "30 50 sign-out",    // Δ=35 ❌
                "60 12 sign-in", "60 20 sign-out"     // Δ=8 ✅
        );
        test.accept(processLogs(logs2, 20), Arrays.asList("30", "60"));

        // -------------------------------------------------------------
        // TEST 3 — Random Users Mixed
        // -------------------------------------------------------------
        System.out.println("\n===== TEST 3 =====");
        List<String> logs3 = Arrays.asList(
                "30 99 sign-in", "30 105 sign-out",
                "12 100 sign-in", "20 80 sign-in",
                "12 120 sign-out", "20 101 sign-out", "21 110 sign-in"
        );
        test.accept(processLogs(logs3, 20), Arrays.asList("12", "30"));

        // -------------------------------------------------------------
        // TEST 4 — Large Data Performance
        // -------------------------------------------------------------
        System.out.println("\n===== TEST 4 (Large Data Performance) =====");
        List<String> largeLogs = new ArrayList<>();
        int n = 100000;
        for (int i = 1; i <= n; i++) {
            largeLogs.add(i + " " + (i * 10) + " sign-in");
            largeLogs.add(i + " " + (i * 10 + 5) + " sign-out");
        }
        List<String> actualLarge = processLogs(largeLogs, 10);
        System.out.println("Large Data Test → Users Found: " + actualLarge.size() +
                " | PASS ✅ if efficient");
    }

    // Represents one session for a user
    static class Session {
        int signIn;
        int signOut;

        Session(int signIn, int signOut) {
            this.signIn = signIn;
            this.signOut = signOut;
        }

        int getDuration() {
            return signOut - signIn;
        }

        public String toString() {
            return "[" + signIn + "→" + signOut + "]";
        }
    }
}
