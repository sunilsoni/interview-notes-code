package com.interview.notes.code.year.y2025.august.HackerRank.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static List<String> tableOfContents(List<String> text) {
        List<String> toc = new ArrayList<>();
        int chapter = 0, section = 0;
        for (String line : text) {
            if (line.startsWith("# ")) {
                chapter++;
                section = 0;
                toc.add(chapter + ". " + line.substring(2));
            } else if (line.startsWith("## ")) {
                section++;
                toc.add(chapter + "." + section + ". " + line.substring(3));
            }
        }
        return toc;
    }

    public static void runTest(String name, List<String> input, List<String> expected) {
        List<String> result = tableOfContents(input);
        String status = result.equals(expected) ? "PASS" : "FAIL";
        System.out.println(name + ": expected=" + expected + ", got=" + result + " => " + status);
    }

    public static void main(String[] args) {
        runTest("Sample Case 0",
                Arrays.asList(
                        "# Cars",
                        "Cars came into global use during the 20th century",
                        "Most definitions of car say they run primarily on roads",
                        "## Sedan",
                        "Sedan's first recorded use as a name for a car body was in 1912",
                        "## Coupe",
                        "A coupe is a passenger car with a sloping rear roofline and generally two doors",
                        "## SUV",
                        "The predecessors to SUVs date back to military and low-volume models from the late 1930s",
                        "There is no commonly agreed definition of an SUV, and usage varies between countries."
                ),
                Arrays.asList(
                        "1. Cars",
                        "1.1. Sedan",
                        "1.2. Coupe",
                        "1.3. SUV"
                )
        );

        runTest("Sample Case 1",
                Arrays.asList(
                        "# Games",
                        "## Board",
                        "## Computer",
                        "## Zero sum",
                        "## Multiplayer",
                        "# Strategies",
                        "## Greedy",
                        "## Tree pruning",
                        "## Others",
                        "# Summary"
                ),
                Arrays.asList(
                        "1. Games",
                        "1.1. Board",
                        "1.2. Computer",
                        "1.3. Zero sum",
                        "1.4. Multiplayer",
                        "2. Strategies",
                        "2.1. Greedy",
                        "2.2. Tree pruning",
                        "2.3. Others",
                        "3. Summary"
                )
        );

        int n = 1000;
        List<String> largeInput = IntStream.rangeClosed(1, n)
                .mapToObj(i -> "# Chapter" + i)
                .collect(Collectors.toList());
        List<String> largeExpected = IntStream.rangeClosed(1, n)
                .mapToObj(i -> i + ". Chapter" + i)
                .collect(Collectors.toList());
        runTest("Large Chapters", largeInput, largeExpected);
    }
}