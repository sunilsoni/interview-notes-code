package com.interview.notes.code.misc;

import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String guesses[] = {
                "reddit", "facebook", "java", "assignment",
                "game", "hello", "islam", "religion", "internet", "face"};

        boolean weArePlaying = true;
        while (weArePlaying) {
            System.out.println("Lets Start Playing Hangman ver 0.1");
            int randomNumber = random.nextInt(guesses.length); //random.nextInt(10);
            char randomWordToGuess[] = guesses[randomNumber].toCharArray(); // java -> j,a,v,a
            int ammountOfGuesses = randomWordToGuess.length; //total tries to guess a word.
            char playerGuess[] = new char[ammountOfGuesses]; // "_ _ _ _ _ _ _ _"

            for (int i = 0; i < playerGuess.length; i++) { // Assign empty dashes at start "_ _ _ _ _ _ _ _"
                playerGuess[i] = '_';
            }

            boolean wordIsGuessed = false;
            int tries = 0;

            while (!wordIsGuessed && tries != ammountOfGuesses) {
                System.out.println("Current Guesses: ");
                print(playerGuess);
                System.out.printf("You have %d ammount of tries left.\n", ammountOfGuesses - tries);
                System.out.println("Enter a single character: ");
                char input = scanner.nextLine().charAt(0);
                tries++;

                if (input == '-') {
                    wordIsGuessed = true;
                    weArePlaying = false;
                } else {
                    for (int i = 0; i < randomWordToGuess.length; i++) {
                        if (randomWordToGuess[i] == input) {
                            playerGuess[i] = input;
                        }
                    }

                    if (isTheWordGuessed(playerGuess)) {
                        wordIsGuessed = true;
                        System.out.println("Congratulations");
                    }
                }
            } /* End of wordIsGuessed */
            if (!wordIsGuessed) {
                System.out.println("You ran out of guesses.");
            }

            System.out.println("Would you like to play again? (yes/no) ");
            String choice = scanner.nextLine();
            if (choice.equals("no")) {
                weArePlaying = false;
            }

        }/*End of We Are Playing*/

        System.out.println("Game Over!");
    }

    public static void print(char array[]) {
        for (int i = 0; i < array.length; i++) { // Assign empty dashes at start "_ _ _ _ _ _ _ _"
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static boolean isTheWordGuessed(char[] array) {
        boolean condition = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '_') {
                condition = false;
            }
        }
        return condition;
    }
}