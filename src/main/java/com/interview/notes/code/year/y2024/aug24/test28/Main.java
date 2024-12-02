package com.interview.notes.code.year.y2024.aug24.test28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        String s1 = "Alice";
        String s2 = "Alice";
        String s3 = "Bob";


        // Define multiple lists
        List<String> team1 = Arrays.asList("Alice", "Bob", "Charlie", "David");
        List<String> team2 = Arrays.asList("Eva", "Frank", "George", "Anna");
        List<String> team3 = Arrays.asList("Henry", "Irene", "Jack", "Adam");

        // Combine all lists into a list of lists
        List<List<String>> allTeams = Arrays.asList(team1, team2, team3);

        // Process all teams in one stream operation
        List<String> allNamesStartingWithA = allTeams.stream()
                .flatMap(Collection::stream)
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println("All names starting with A across all teams: " + allNamesStartingWithA);

        // Process each team separately
        for (int i = 0; i < allTeams.size(); i++) {
            List<String> teamNamesStartingWithA = allTeams.get(i).stream()
                    .filter(name -> name.startsWith("A"))
                    .collect(Collectors.toList());
            System.out.println("Team " + (i + 1) + " names starting with A: " + teamNamesStartingWithA);
        }

        // Additional test case: empty team
        List<String> emptyTeam = new ArrayList<>();
        allTeams.add(emptyTeam);
        List<String> emptyTeamResult = emptyTeam.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Empty team result: " + emptyTeamResult);
    }
}
