package com.interview.notes.code.months.Oct23.test3;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a function that takes 2 arguments:
 *
 * For exmaple like below
 * -String ; "this is a {foo} {bar}"
 * -•HashMap : {"foo": "template”, "bar": "string"}
 * Returns a string:
 * this is a template” string
 *
 * it replace placeholder with values from map
 */
public class PlaceholderReplacer {

    public static String replacePlaceholders(String str, HashMap<String, String> mappings) {
        StringBuilder result = new StringBuilder(str);
        for (Map.Entry<String, String> entry : mappings.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String placeholder = "{" + key + "}";
            
            int index = result.indexOf(placeholder);
            while (index != -1) {
                result.replace(index, index + placeholder.length(), value);
                index = result.indexOf(placeholder, index + value.length());
            }
        }
        return result.toString();
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
