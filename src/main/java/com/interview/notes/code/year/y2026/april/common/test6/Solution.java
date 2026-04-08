package com.interview.notes.code.year.y2026.april.common.test6;

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

interface TournamentObserver {
    void onGamesPlayed(Bot botA, Bot botB, int winsA, int winsB);
}

class Tournament {
    private final List<Bot> bots;
    private final List<TournamentObserver> observers = new ArrayList<>();

    Tournament(List<Bot> bots) { this.bots = bots; }
    public void addObserver(TournamentObserver observer) { observers.add(observer); }

    public void playTournament() {
        for (int i = 0; i < bots.size(); ++i) {
            for (int j = i + 1; j < bots.size(); ++j) {
                Bot a = bots.get(i);
                Bot b = bots.get(j);
                Map<Bot, Integer> wins = playGames(a, b);
                observers.forEach(obs -> obs.onGamesPlayed(a, b, wins.get(a), wins.get(b)));
            }
        }
    }

    private Map<Bot, Integer> playGames(Bot playerA, Bot playerB) {
        Map<Bot, Integer> wins = new HashMap<>(Map.of(playerA, 0, playerB, 0));
        playerA.onNewOpponent();
        playerB.onNewOpponent();
        
        for (int i = 0; i < 1000; ++i) {
            RPSMove moveA = playerA.makeMove();
            RPSMove moveB = playerB.makeMove();

            if (RPSMove.handIsBetter(moveA, moveB)) wins.merge(playerA, 1, Integer::sum);
            else if (RPSMove.handIsBetter(moveB, moveA)) wins.merge(playerB, 1, Integer::sum);

            playerA.onHandPlayed(moveB);
            playerB.onHandPlayed(moveA);
        }
        return wins;
    }
}

class TournamentResults implements TournamentObserver {
    private final Map<Bot, Integer> botWins = new LinkedHashMap<>();
    private final Map<Bot, Integer> botLosses = new LinkedHashMap<>();

    @Override
    public void onGamesPlayed(Bot botA, Bot botB, int winsA, int winsB) {
        botWins.merge(botA, winsA, Integer::sum);
        botWins.merge(botB, winsB, Integer::sum);
        botLosses.merge(botA, winsB, Integer::sum);
        botLosses.merge(botB, winsA, Integer::sum);
    }

    void printToConsole() {
        System.out.printf("%20s | %7s | %7s | %4s\n", "Name", "Wins", "Losses", "Overall");
        System.out.println("------------------------------------------------------");
        botWins.forEach((bot, wins) -> {
            int losses = botLosses.getOrDefault(bot, 0);
            System.out.printf("%20s | %7d | %7d | %4d\n", bot.name(), wins, losses, wins - losses);
        });
    }
}

class RandomBot implements Bot {
    private final Random random = new Random();
    @Override public String name() { return "RandomBot"; }
    @Override public RPSMove makeMove() { return RPSMove.fromOrdinal(random.nextInt(3)); }
    @Override public void onNewOpponent() {} 
    @Override public void onHandPlayed(RPSMove opponentsMove) {} 
}

class OneMoveBot implements Bot {
    private final RPSMove myMove;
    public OneMoveBot(RPSMove move) { this.myMove = move; }
    @Override public String name() { return "OneMoveBot"; }
    @Override public RPSMove makeMove() { return myMove; }
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

// --- NEW CHANGES START HERE ---

// A bot that chooses moves based on fixed percentages (weights).
// Avoids bit masking or complex math; uses simple 0-99 random range.
class WeightedRandomBot implements Bot {
    private final Random random = new Random();
    private final int rockWeight;
    private final int paperWeight;

    // Takes percentages out of 100.
    public WeightedRandomBot(int rockWeight, int paperWeight, int scissorsWeight) {
        this.rockWeight = rockWeight;
        this.paperWeight = paperWeight;
    }

    @Override public String name() { return "WeightedRandomBot"; }
    @Override public void onNewOpponent() {}
    @Override public void onHandPlayed(RPSMove opponentsMove) {}

    @Override 
    public RPSMove makeMove() {
        // Generate number between 0 and 99
        int rand = random.nextInt(100); 
        
        // Simple boundary checks to determine move
        if (rand < rockWeight) {
            return RPSMove.ROCK;
        } else if (rand < rockWeight + paperWeight) {
            return RPSMove.PAPER;
        } else {
            return RPSMove.SCISSORS; 
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        // Initializing the new bot with 20% Rock, 30% Paper, 50% Scissors as requested
        WeightedRandomBot weightedBot = new WeightedRandomBot(20, 30, 50);
        
        List<Bot> bots = List.of(
            new RandomBot(),
            new OneMoveBot(RPSMove.SCISSORS),
            new BasicStatsBot(),
            weightedBot // Added the newly created bot to the tournament list
        );

        Tournament tourny = new Tournament(bots);
        TournamentResults results = new TournamentResults();
        tourny.addObserver(results);
        
        tourny.playTournament();
        results.printToConsole();
    }
}
// --- NEW CHANGES END HERE ---