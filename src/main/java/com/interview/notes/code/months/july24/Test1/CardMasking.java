package com.interview.notes.code.months.july24.Test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardMasking {

    public static void main(String[] args) {
        // List of card numbers
        List<String> cardNumbers = Arrays.asList("1111222233334444", "5555666677778888", "9999000011112222");

        // Mask each card number using a stream
        List<String> maskedCardNumbers = cardNumbers.stream()
                .map(CardMasking::maskCardNumber)
                .collect(Collectors.toList());

        // Print the masked card numbers
        maskedCardNumbers.forEach(System.out::println);
    }

    /**
     * Masks the middle 8 characters of a 16-digit card number.
     * 
     * @param cardNumber the card number to mask
     * @return a string with the middle 8 characters replaced by 'x'
     */
    public static String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16) {
            throw new IllegalArgumentException("Card number must be 16 digits long");
        }
        // Return the masked card number
        return cardNumber.substring(0, 4) + "xxxxxxxx" + cardNumber.substring(12);
    }
}
