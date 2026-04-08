package com.interview.notes.code.year.y2026.april.common.test7;

import java.util.*;

enum RPSMove {
    ROCK, SCISSORS, PAPER;
    public static RPSMove fromOrdinal(int ordinal) { return values()[ordinal]; }
    public static boolean handIsBetter(RPSMove a, RPSMove b) {
        return ((a.ordinal() + 1) % 3) == b.ordinal();
    }
}

interface Bot {
    String name();
    RPSMove makeMove();
    void onNewOpponent();
    void onHandPlayed(RPSMove opponentsMove);
}

class RandomBot implements Bot {
    private final Random random = new Random();
    @Override public String name() { return "RandomBot"; }
    @Override public RPSMove makeMove() { return RPSMove.fromOrdinal(random.nextInt(3)); }
    @Override public void onNewOpponent() {} 
    @Override public void onHandPlayed(RPSMove opponentsMove) {} 
}

class BasicStatsBot implements Bot {
    private final Map<RPSMove, Integer> playedCounts = new EnumMap<>(RPSMove.class);
    @Override public String name() { return "BasicStatsBot"; }
    @Override public void onNewOpponent() { playedCounts.clear(); }
    @Override public void onHandPlayed(RPSMove opponentsMove) { playedCounts.merge(opponentsMove, 1, Integer::sum); }
    @Override public RPSMove makeMove() {
        RPSMove mostPlayedMove = playedCounts.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey).orElse(RPSMove.ROCK); 
        for (RPSMove move : RPSMove.values()) {
            if (RPSMove.handIsBetter(move, mostPlayedMove)) return move;
        }
        throw new IllegalStateException("Counter not found");
    }
}

class WeightedRandomBot implements Bot {
    private final Random random = new Random();
    private final int rockW, paperW;
    public WeightedRandomBot(int rockW, int paperW, int scissorsW) {
        this.rockW = rockW; this.paperW = paperW;
    }
    @Override public String name() { return "WeightedBot"; }
    @Override public void onNewOpponent() {}
    @Override public void onHandPlayed(RPSMove opponentsMove) {}
    @Override public RPSMove makeMove() {
        int r = random.nextInt(100); 
        if (r < rockW) return RPSMove.ROCK;
        if (r < rockW + paperW) return RPSMove.PAPER;
        return RPSMove.SCISSORS; 
    }
}

// --- NEW CHANGES START HERE ---

// 3-Way Tournament logic. Simple approach, no complex math or bit masking.
class ThreeWayTournament {
    private final List<Bot> bots;

    public ThreeWayTournament(Bot b1, Bot b2, Bot b3) {
        this.bots = List.of(b1, b2, b3);
    }

    public void play(int numGames) {
        bots.forEach(Bot::onNewOpponent);
        Map<Bot, Integer> wins = new LinkedHashMap<>();
        bots.forEach(b -> wins.put(b, 0));

        for (int i = 0; i < numGames; i++) {
            while (true) { // Keep playing round until someone wins (handles ties natively)
                RPSMove m0 = bots.get(0).makeMove();
                RPSMove m1 = bots.get(1).makeMove();
                RPSMove m2 = bots.get(2).makeMove();

                // Notify bots of BOTH opponents' moves so they can continue to learn
                bots.get(0).onHandPlayed(m1); bots.get(0).onHandPlayed(m2);
                bots.get(1).onHandPlayed(m0); bots.get(1).onHandPlayed(m2);
                bots.get(2).onHandPlayed(m0); bots.get(2).onHandPlayed(m1);

                // Simple check: does one move beat BOTH of the other moves?
                boolean b0Wins = RPSMove.handIsBetter(m0, m1) && RPSMove.handIsBetter(m0, m2);
                boolean b1Wins = RPSMove.handIsBetter(m1, m0) && RPSMove.handIsBetter(m1, m2);
                boolean b2Wins = RPSMove.handIsBetter(m2, m0) && RPSMove.handIsBetter(m2, m1);

                if (b0Wins) { wins.merge(bots.get(0), 1, Integer::sum); break; }
                if (b1Wins) { wins.merge(bots.get(1), 1, Integer::sum); break; }
                if (b2Wins) { wins.merge(bots.get(2), 1, Integer::sum); break; }
                // If no one won, it was a tie. The while loop continues to replay the round.
            }
        }

        System.out.printf("%20s | %7s\n", "Name", "Wins");
        System.out.println("--------------------------------");
        wins.forEach((bot, count) -> System.out.printf("%20s | %7d\n", bot.name(), count));
    }
}

public class Solution {
    public static void main(String[] args) {
        // Run the 3-way tournament
        ThreeWayTournament tournament = new ThreeWayTournament(
            new RandomBot(),
            new BasicStatsBot(),
            new WeightedRandomBot(20, 30, 50)
        );
        tournament.play(1000);
    }
}