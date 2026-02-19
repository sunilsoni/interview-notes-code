package com.interview.notes.code.year.y2026.feb.microsoft.test14;

import java.util.ArrayDeque;
import java.util.stream.Stream;

public class HtmlValidator { // Defining our main class to hold the HTML validation logic
    
    public static boolean isValidHtml(String html) { // Method signature accepting the HTML string and returning a boolean
        if (html == null || html.isBlank()) return true; // Edge case check: null or blank strings have no unclosed tags, so they are valid
        
        var stack = new ArrayDeque<String>(); // Using 'var' (Java 10+) to create our Stack for tracking open tags in O(1) time
        int i = 0; // Initializing an index pointer to traverse the HTML string character by character
        
        while (i < html.length()) { // Looping continuously until our pointer reaches the end of the HTML string
            int openIdx = html.indexOf('<', i); // Finding the index of the next opening bracket starting from our current pointer
            if (openIdx == -1) break; // If no more opening brackets exist, we break out of the loop as parsing is done
            
            int closeIdx = html.indexOf('>', openIdx); // Finding the matching closing bracket for the tag we just found
            if (closeIdx == -1) return false; // If an opening bracket lacks a closing bracket, the HTML is instantly malformed/invalid
            
            var content = html.substring(openIdx + 1, closeIdx).trim(); // Extracting the string inside the brackets and trimming outer spaces
            i = closeIdx + 1; // Moving our main traversal pointer just past the current tag's closing bracket
            
            if (content.isEmpty() || content.startsWith("!")) continue; // Skipping empty brackets `<>` or HTML comments/Doctypes like `<!DOCTYPE>`
            
            var isClosing = content.startsWith("/"); // Determining if this is a closing tag (e.g., `</div`) by checking the first character
            var isSelfClosing = content.endsWith("/"); // Determining if this is a self-closing tag (e.g., `div />`) by checking the last character
            
            var spaceIdx = content.indexOf(' '); // Finding the first space inside the tag to separate the tag name from its attributes
            var firstWord = spaceIdx == -1 ? content : content.substring(0, spaceIdx); // Extracting just the first word (the tag name plus potential slashes)
            
            var tagName = firstWord.replace("/", "").toLowerCase(); // Removing slash characters manually (no regex) and converting to lowercase for case-insensitivity
            
            if (isSelfClosing && !isClosing) { // Checking if it is a standard self-closing tag (like `<br/>` or `<img />`)
                continue; // Self-closing tags are complete, so we skip further stack logic and move to the next tag
            } // Closing the self-closing check block
            
            if (isClosing) { // If our earlier check determined this is a standard closing tag (like `</div>`)
                if (stack.isEmpty() || !stack.pop().equals(tagName)) { // If stack is empty (extra closing tag) OR the popped tag doesn't match
                    return false; // The sequence of tags is broken, meaning the HTML string is definitively invalid
                } // Closing the mismatch check block
            } else { // If it is not self-closing and not a closing tag, it must be an opening tag (like `<div>`)
                stack.push(tagName); // Pushing the cleanly formatted, lowercase tag name onto the stack to await its closing tag
            } // Closing the else block
        } // Closing the while loop
        
        return stack.isEmpty(); // If the stack is completely empty at the end, every opening tag was properly closed
    } // Closing the validation method
    
    public static void main(String[] args) { // Main method functioning as our test runner without relying on JUnit

        var validHtml = """ 
            <html>
            <head>
            </head>
            <body>
                <DiV>
                    <SPAN></span >
                    <div />
                    <h1></h1>
                </dIv>
            </body>
            </HTML>
            """; // Text Block (Java 15+) holding valid HTML but with mixed casing (e.g., <DiV> and </dIv>) to test case insensitivity

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
            """; // Text Block holding the invalid HTML example where the <span> tag is left unclosed

        var largeData = new StringBuilder(); // Using StringBuilder to efficiently build a massive string without memory overhead
        for (int i = 0; i < 50000; i++) largeData.append("<DiV><sPaN></sPaN></dIv>"); // Adding 50,000 mixed-case nested tags to ensure no StackOverflow

        Stream.of( // Using Java 8 Stream API to initialize our test suite in a declarative, functional manner
            new TestCase("Case-Insensitive Valid", validHtml, true), // Test 1: Verifies correct nesting and ignores upper/lower case differences
            new TestCase("Invalid HTML Example", invalidHtml, false), // Test 2: Verifies failure when a tag is left unclosed
            new TestCase("Large Data Input", largeData.toString(), true) // Test 3: Verifies performance and scale without stack overflow issues
        ).forEach(test -> { // Iterating sequentially through each TestCase record within the stream
            var result = isValidHtml(test.html()); // Invoking our custom parsing method against the current test's HTML payload
            var status = (result == test.expected()) ? "PASS" : "FAIL"; // Using a ternary operator to verify actual output against expected output
            System.out.printf("%-25s -> %s%n", test.name(), status); // Printing the formatted test results to the system console
        }); // Closing the lambda and the forEach operation
    } // Closing the main method
    
    record TestCase(String name, String html, boolean expected) {} // Using Java 16+ 'record' feature to define a clean, immutable test object
} // Closing the main class