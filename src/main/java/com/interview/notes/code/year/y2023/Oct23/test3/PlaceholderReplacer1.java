package com.interview.notes.code.year.y2023.Oct23.test3;

import java.util.HashMap;
import java.util.Map;

/**
 * Please clarify the questions and requirements, and provide a step-by-step plan for solving them.
 * <p>
 * Additionally, include code with explanations, and calculate time and space complexity at each step.
 * <p>
 * IN Java
 * <p>
 * Write a function that takes 2 arguments:
 * <p>
 * For exmaple like below
 * -String ; "this is a {foo} {bar}"
 * -•HashMap : {"foo": "template”, "bar": "string"}
 * Returns a string:
 * this is a template” string
 * <p>
 * it replace placeholder with values from map
 */
public class PlaceholderReplacer1 {

    public static String replacePlaceholders(String str, HashMap<String, String> mappings) {
        for (Map.Entry<String, String> entry : mappings.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String placeholder = "{" + key + "}";
            str = str.replace(placeholder, value);
        }
        return str;
    }

    public static void main(String[] args) {
        String str = "this is a {foo} {bar}";
        HashMap<String, String> mappings = new HashMap<>();
        mappings.put("foo", "template");
        mappings.put("bar", "string");
        String result = replacePlaceholders(str, mappings);
        System.out.println(result);  // Output: this is a template string
    }
}
