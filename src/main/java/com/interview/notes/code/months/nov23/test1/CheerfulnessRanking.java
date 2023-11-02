package com.interview.notes.code.months.nov23.test1;

public class CheerfulnessRanking {

    public static void main(String[] args) {
        String words1 = "Akin to cheat";
        String words2 = "Atrides still with deep resentment raged To wait his will two sacred heralds stood Talthibius and Eurybates the good";
        
        System.out.println(mostCheerfulWord(words1));  // Expected: Akin
        System.out.println(mostCheerfulWord(words2));  // Expected: Atrides
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
        for (char c : word.toLowerCase().toCharArray()) {  // convert to lowercase to handle cases
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
