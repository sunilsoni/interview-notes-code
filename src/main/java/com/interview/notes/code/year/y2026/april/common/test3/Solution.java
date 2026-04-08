package com.interview.notes.code.year.y2026.april.common.test3;

import java.util.*;

// Defines the available moves and the winning logic
enum RPSMove {
    ROCK, SCISSORS, PAPER;

    // Helper to get move from an integer index
    public static RPSMove fromOrdinal(int ordinal) {
        return values()[ordinal];
    }

    // Mathematical logic to determine if move 'a' beats move 'b'
    public static boolean handIsBetter(RPSMove a, RPSMove b) {
        return ((a.ordinal() + 1) % 3) == b.ordinal();
    }
}

// Contract that all Rock, Paper, Scissors bots must follow
interface Bot {
    String name(); // Identifies the bot
    RPSMove makeMove(); // Returns the next throw
    void onNewOpponent(); // Resets state for a new opponent
    void onHandPlayed(RPSMove opponentsMove); // Records what the opponent just played
}

// Interface for receiving tournament updates
interface TournamentObserver {
    void onGamesPlayed(Bot botA, Bot botB, int winsA, int winsB);
}

// Manages the execution of the tournament between bots
class Tournament {
    private final List<Bot> bots;
    private final List<TournamentObserver> observers = new ArrayList<>();

    Tournament(List<Bot> bots) {
        this.bots = bots;
    }

    // Registers an observer to listen for match results
    public void addObserver(TournamentObserver observer) {
        observers.add(observer);
    }

    // Executes a round-robin tournament
    public void playTournament() {
        for (int i = 0; i < bots.size(); ++i) {
            for (int j = i + 1; j < bots.size(); ++j) {
                Bot a = bots.get(i);
                Bot b = bots.get(j);
                Map<Bot, Integer> wins = playGames(a, b);
                // Notify observers of the match outcome
                observers.forEach(obs -> obs.onGamesPlayed(a, b, wins.get(a), wins.get(b)));
            }
        }
    }

    // Plays 1000 games between two specific bots
    private Map<Bot, Integer> playGames(Bot playerA, Bot playerB) {
        Map<Bot, Integer> wins = new HashMap<>(Map.of(playerA, 0, playerB, 0));
        playerA.onNewOpponent();
        playerB.onNewOpponent();
        int numGamesToPlay = 1000;

        for (int i = 0; i < numGamesToPlay; ++i) {
            RPSMove moveA = playerA.makeMove();
            RPSMove moveB = playerB.makeMove();

            // Update win counts based on who had the better hand
            if (RPSMove.handIsBetter(moveA, moveB)) {
                wins.merge(playerA, 1, Integer::sum);
            } else if (RPSMove.handIsBetter(moveB, moveA)) {
                wins.merge(playerB, 1, Integer::sum);
            }

            // Inform bots of the opponent's move
            playerA.onHandPlayed(moveB);
            playerB.onHandPlayed(moveA);
        }
        return wins;
    }
}

// Tracks and displays the overall tournament results
class TournamentResults implements TournamentObserver {
    private final Map<Bot, Integer> botWins = new LinkedHashMap<>();
    private final Map<Bot, Integer> botLosses = new LinkedHashMap<>();

    // Updates win/loss trackers after a match completes
    @Override
    public void onGamesPlayed(Bot botA, Bot botB, int winsA, int winsB) {
        botWins.merge(botA, winsA, Integer::sum);
        botWins.merge(botB, winsB, Integer::sum);
        botLosses.merge(botA, winsB, Integer::sum); // A's losses equal B's wins
        botLosses.merge(botB, winsA, Integer::sum); // B's losses equal A's wins
    }

    // Prints formatted results to the console
    void printToConsole() {
        System.out.printf("%20s | %7s | %7s | %4s\n", "Name", "Wins", "Losses", "Overall");
        System.out.println("------------------------------------------------------");
        botWins.forEach((bot, wins) -> {
            int losses = botLosses.getOrDefault(bot, 0);
            System.out.printf("%20s | %7d | %7d | %4d\n", bot.name(), wins, losses, wins - losses);
        });
    }
}

// A bot that throws a random move every time
class RandomBot implements Bot {
    private final Random random = new Random();

    @Override public String name() { return "RandomBot"; }
    @Override public RPSMove makeMove() { return RPSMove.fromOrdinal(random.nextInt(3)); }
    @Override public void onNewOpponent() {} // No state to track
    @Override public void onHandPlayed(RPSMove opponentsMove) {} // Ignores opponent history
}

// A bot that stubbornly throws the exact same move every time
class OneMoveBot implements Bot {
    private final RPSMove myMove;

    public OneMoveBot(RPSMove move) { this.myMove = move; }
    @Override public String name() { return "OneMoveBot"; }
    @Override public RPSMove makeMove() { return myMove; }
    @Override public void onNewOpponent() {}
    @Override public void onHandPlayed(RPSMove opponentsMove) {}
}

// A reactive bot that counters the opponent's most frequent move
class BasicStatsBot implements Bot {
    // EnumMap is highly optimized for enum keys
    private final Map<RPSMove, Integer> playedCounts = new EnumMap<>(RPSMove.class);

    @Override public String name() { return "BasicStatsBot"; }

    // Clears history when facing someone new
    @Override public void onNewOpponent() { playedCounts.clear(); }

    // Tallies up the opponent's moves
    @Override public void onHandPlayed(RPSMove opponentsMove) {
        playedCounts.merge(opponentsMove, 1, Integer::sum);
    }

    // Calculates the counter-move using Stream API
    @Override public RPSMove makeMove() {
        // Find the move the opponent has played the most
        RPSMove mostPlayedMove = playedCounts.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(RPSMove.ROCK); // Default to ROCK if no history exists

        // Iterate through all moves to find the one that beats their most played move
        for (RPSMove move : RPSMove.values()) {
            if (RPSMove.handIsBetter(move, mostPlayedMove)) {
                return move;
            }
        }
        throw new IllegalStateException("Could not find counter");
    }
}

// Main execution class for testing
public class Solution {
    public static void main(String[] args) {
        // Initialize immutable list of participating bots
        List<Bot> bots = List.of(
            new RandomBot(),
            new OneMoveBot(RPSMove.SCISSORS),
            new BasicStatsBot()
        );

        // Set up, observe, and run the tournament
        Tournament tourny = new Tournament(bots);
        TournamentResults results = new TournamentResults();
        tourny.addObserver(results);
        
        tourny.playTournament();
        results.printToConsole();
    }
}