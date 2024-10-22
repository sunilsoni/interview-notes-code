package com.interview.notes.code.months.oct24.test16;

import java.util.Stack;

public class XMLValidator {

    public static boolean validateXML(String xml) {
        Stack<String> stack = new Stack<>();
        int index = 0;

        while (index < xml.length()) {
            // Find the next tag
            int startTagIndex = xml.indexOf('<', index);
            if (startTagIndex == -1) break; // No more tags

            int endTagIndex = xml.indexOf('>', startTagIndex);
            if (endTagIndex == -1) return false; // Malformed tag

            String tag = xml.substring(startTagIndex + 1, endTagIndex);
            index = endTagIndex + 1;

            // Check if it's a closing tag
            if (tag.startsWith("/")) {
                String closingTag = tag.substring(1); // Remove '/' from tag
                if (stack.isEmpty() || !stack.peek().equals(closingTag)) {
                    return false; // Unmatched closing tag
                }
                stack.pop(); // Valid match; pop from stack
            } else if (!tag.endsWith("/")) { // Ignore self-closing tags
                stack.push(tag); // It's an opening tag
            }
        }

        return stack.isEmpty(); // Valid if stack is empty at the end
    }

    public static void main(String[] args) {
        String xml1 = "<root><child></child></root>"; // Valid
        String xml2 = "<root><child></root>";          // Invalid
        String xml3 = "<root><child/></root>";         // Valid
        String xml4 = "<root><child></child>";          // Valid

        System.out.println(validateXML(xml1)); // Output: true
        System.out.println(validateXML(xml2)); // Output: false
        System.out.println(validateXML(xml3)); // Output: true
        System.out.println(validateXML(xml4)); // Output: true
    }
}
