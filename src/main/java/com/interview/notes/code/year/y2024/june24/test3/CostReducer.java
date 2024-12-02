package com.interview.notes.code.year.y2024.june24.test3;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a function to take a string and decrease all costs in the string by 15% and returns the modified string.
 * Example:
 * Input: "Mary spent $5.25 and bo spent $5.25 on books."
 * Output: "Mary spent $4.46 and bo spent $4.46 on books."
 */
public class CostReducer {

    public static String reduceCosts(String input) {
        // Regular expression to match dollar-signed and unsigned monetary values
        String regex = "(\\$?)(\\d+\\.?\\d*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Decimal format to ensure two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String dollarSign = matcher.group(1);
            String numberPart = matcher.group(2);
            double value = Double.parseDouble(numberPart);
            double reducedValue = value * 0.85;
            String formattedValue = decimalFormat.format(reducedValue);
            matcher.appendReplacement(result, dollarSign + formattedValue);
        }
        matcher.appendTail(result);

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "Mary spent $5.25 and bo spent 5.25 on books.";
        String output = reduceCosts(input);
        System.out.println(output);  // Output: "Mary spent \$4.46 and bo spent 4.46 on books."
    }
}
