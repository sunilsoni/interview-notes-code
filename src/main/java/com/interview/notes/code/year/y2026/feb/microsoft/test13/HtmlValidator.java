package com.interview.notes.code.year.y2026.feb.microsoft.test13;

import java.util.ArrayDeque;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class HtmlValidator { // Declaring the main class that encapsulates our HTML validation logic and tests
    
    public static boolean isValidHtml(String html) { // Defining the validation method that takes a String and returns a boolean
        if (html == null || html.isBlank()) return true; // Early exit: if the string is empty or null, it's technically valid (no unclosed tags)
        
        var stack = new ArrayDeque<String>(); // Using 'var' (Java 10+) to instantiate our Stack cleanly without repeating the type
        var regex = Pattern.compile("<(/?)(\\w+)([^>]*)>"); // Compiling regex: Group 1=closing slash, Group 2=tag name, Group 3=attributes
        var matcher = regex.matcher(html); // Creating a matcher object to find every tag instance within the provided HTML string
        
        while (matcher.find()) { // Looping continuously as long as the matcher finds another tag in the string
            var isClosing = !matcher.group(1).isEmpty(); // If Group 1 is not empty (it contains '/'), this is a closing tag like </div>
            var tagName = matcher.group(2); // Extracting the actual alphabetical tag name (e.g., 'div', 'html', 'span') from Group 2
            var attributes = matcher.group(3); // Extracting any trailing data before the '>' to check for self-closing slashes
            
            if (attributes.trim().endsWith("/")) { // Trimming whitespace and checking if the tag ends with '/' (e.g., <div />)
                continue; // Self-closing tags are immediately valid, so we skip further processing and move to the next tag
            } // Closing the if statement for the self-closing check
            
            if (isClosing) { // Checking if our earlier boolean flagged this as a standard closing tag
                if (stack.isEmpty() || !stack.pop().equals(tagName)) { // If stack is empty (no opening tag) OR the top tag doesn't match
                    return false; // The sequence is broken, so we immediately return false indicating invalid HTML
                } // Closing the inner if statement that checks for mismatched tags
            } else { // If it's not a self-closing tag and not a closing tag, it must be a standard opening tag
                stack.push(tagName); // We push the opening tag name onto the stack so we can match it later
            } // Closing the else block for opening tags
        } // Closing the while loop after the entire string has been parsed
        
        return stack.isEmpty(); // If the stack is empty at the end, all tags matched perfectly; if not, tags were left open
    } // Closing the isValidHtml method
    
    public static void main(String[] args) { // Defining the standard main method to serve as our simple test runner without JUnit
        var validHtml = """ 
            <html>
            <head>
            </head>
            <body>
                <div>
                    <span></span>
                    <div />
                    <h1></h1>
                </div>
            </body>
            </html>
            """; // Using Java 15+ Text Blocks for clean, highly readable multi-line string initialization for the valid example

        var invalidHtml = """
            <html>
            <head>
            </head>
            <body>
                <div>
                    <span>
                </div>
            </body>
            </html>
            """; // Using a Text Block to define the invalid HTML example provided in the original problem statement image

        var largeData = new StringBuilder(); // Instantiating a StringBuilder to efficiently construct a massive HTML payload
        for (int i = 0; i < 50000; i++) largeData.append("<div><span></span></div>"); // Appending 50,000 sets of valid nested tags to test scale

        Stream.of( // Using Java 8 Stream.of() to initialize and immediately process our array of test cases
            new TestCase("Valid HTML Example", validHtml, true), // Creating a test case for the valid input, expecting 'true'
            new TestCase("Invalid HTML Example", invalidHtml, false), // Creating a test case for the invalid input, expecting 'false'
            new TestCase("Large Data Input", largeData.toString(), true) // Creating a test case for massive data, expecting 'true'
        ).forEach(test -> { // Iterating through every test case in the stream using the functional forEach method
            var result = isValidHtml(test.html()); // Executing our core validation logic against the current test case's HTML
            var status = (result == test.expected()) ? "PASS" : "FAIL"; // Using a ternary operator to verify if the result matches expectations
            System.out.printf("%-22s -> %s%n", test.name(), status); // Printing the test name formatted cleanly alongside PASS/FAIL
        }); // Closing the lambda expression and the forEach stream operation
    } // Closing the main test method
    
    record TestCase(String name, String html, boolean expected) {} // Using Java 16+ 'record' to create a compact, immutable test case object
} // Closing the HtmlValidator class