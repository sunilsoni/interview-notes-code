package com.interview.notes.code.year.y2023.nov23.test1;

public class CheerfulnessRanking1 {

    public static void main(String[] args) {
        String words = "a joyous morning to you companion";
        System.out.println(mostCheerfulWord(words));


        String words1 = "Akin to cheat";
        System.out.println(mostCheerfulWord(words1));


        String words2 = "Atrides still with deep resentment raged To wait his will two sacred heralds stood Talthibius and Eurybates the good";
        System.out.println(mostCheerfulWord(words2));
    }

    public static String mostCheerfulWord(String words) {
        String[] wordArr = words.split(" ");
        int maxRanking = Integer.MIN_VALUE;
        String mostCheerful = "";

        for (String word : wordArr) {
            int ranking = getCheerfulnessRanking(word);
            if (ranking > maxRanking) {
                maxRanking = ranking;
                mostCheerful = word;
            }
        }
        return mostCheerful;
    }

    private static int getCheerfulnessRanking(String word) {
        int ranking = 0;
        for (char c : word.toCharArray()) {
            switch (c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    ranking += 1;
                    break;
                case 'b':
                    ranking -= 1;
                    break;
                case 'v':
                    ranking -= 2;
                    break;
                default:
                    break;
            }
        }
        return ranking;
    }
}
