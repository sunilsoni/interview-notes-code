package com.interview.notes.code.year.y2025.may.common.test7;

public class CharacterCounter {
    
    public static void main(String[] args) {
        // Test cases with detailed output
        testCountCharacters("Hello World");  
        testCountCharacters("");             
        testCountCharacters("AEIOU");        
        testCountCharacters("BCDFG");        
        testCountCharacters("123");          
        testCountCharacters("A@#$%");        
        
        // Large data test
        StringBuilder largeInput = new StringBuilder();
        for(int i = 0; i < 100; i++) {  // Reduced size for readable output
            largeInput.append("HelloWorld");
        }
        testCountCharacters(largeInput.toString());
    }
    
    // Enhanced Result class to store actual characters
    static class Result {
        long vowelCount;
        long consonantCount;
        String foundVowels;
        String foundConsonants;
        
        Result(long vowelCount, long consonantCount, String foundVowels, String foundConsonants) {
            this.vowelCount = vowelCount;
            this.consonantCount = consonantCount;
            this.foundVowels = foundVowels;
            this.foundConsonants = foundConsonants;
        }
    }
    
    // Enhanced counting method to track actual characters
    public static Result countCharacters(String input) {
        StringBuilder vowels = new StringBuilder();
        StringBuilder consonants = new StringBuilder();
        
        // Process each character
        input.chars().mapToObj(ch -> (char) ch)
             .forEach(ch -> {
                 char lowercaseChar = Character.toLowerCase(ch);
                 if ("aeiou".indexOf(lowercaseChar) >= 0) {
                     vowels.append(ch);
                 } else if (Character.isLetter(ch)) {
                     consonants.append(ch);
                 }
             });
        
        return new Result(
            vowels.length(),
            consonants.length(),
            vowels.toString(),
            consonants.toString()
        );
    }
    
    // Enhanced test method with detailed output
    private static void testCountCharacters(String input) {
        System.out.println("\n========= TEST CASE =========");
        System.out.println("Input: " + 
            (input.length() > 50 ? input.substring(0, 47) + "..." : input));
        
        Result result = countCharacters(input);
        
        // Detailed output
        System.out.println("\nResults:");
        System.out.println("Vowel count: " + result.vowelCount);
        System.out.println("Vowels found: " + formatOutput(result.foundVowels));
        
        System.out.println("\nConsonant count: " + result.consonantCount);
        System.out.println("Consonants found: " + formatOutput(result.foundConsonants));
        
        // Validation
        boolean isValid = validateResults(input, result);
        System.out.println("\nTest Status: " + (isValid ? "✅ PASSED" : "❌ FAILED"));
        System.out.println("============================");
    }
    
    // Helper method to format output for better readability
    private static String formatOutput(String chars) {
        if (chars.isEmpty()) return "[]";
        return "[" + String.join(", ", chars.split("")) + "]";
    }
    
    // Validation method
    private static boolean validateResults(String input, Result result) {
        if (input.isEmpty()) {
            return result.vowelCount == 0 && result.consonantCount == 0;
        }
        
        return (result.vowelCount + result.consonantCount) <= input.length();
    }
}
