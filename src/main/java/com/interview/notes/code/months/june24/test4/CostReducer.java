package com.interview.notes.code.months.june24.test4;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CostReducer {

    public static String reduceCosts(String input) {
        // Regular expression to match dollar-signed and unsigned monetary values
        String regex = "\\$?\\d+\\.?\\d*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Decimal format to ensure two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String match = matcher.group();
            boolean hasDollarSign = match.startsWith("$");
            double value = Double.parseDouble(hasDollarSign ? match.substring(1) : match);
            double reducedValue = value * 0.85;
            String formattedValue = decimalFormat.format(reducedValue);
            matcher.appendReplacement(result, (hasDollarSign ? "$" : "") + formattedValue);
        }
        matcher.appendTail(result);

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "Mary spent \\$5.25 and bo spent 5.25 on books.";
        String output = reduceCosts(input);
        System.out.println(output);  // Output: "Mary spent \$4.46 and bo spent 4.46 on books."
    }
}
