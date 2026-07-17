package com.interview.notes.code.year.y2026.july.oracle.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Result { // Define the main class that encapsulates the migration logic

    // Use Java Text Blocks for clean, readable mock target data mimicking the external system
    private static final String TARGET_DATA = """
        MRN,SSN,DOB,NAME_LAST,NAME_FIRST
        MRN000001,123-45-6789,1986-03-14,Lee,Jordan
        MRN000002,987-65-4321,1982-07-09,Morgan,Taylor
        MRN-A1CKAZT,969-82-4841,1980-05-17,Moore,Jordan
        """; // Close Text Block for target data
    // Use Java Text Blocks for mock source data mimicking the messy laboratory output
    private static final String SOURCE_DATA = """
        LAB_ID|SSN|DOB|NAME
        LAB00001|123-45-6789|03/14/1986|Jordan Lee
        LAB00002|987654321|1982-07-09|Morgan, Taylor
        LAB-OBJW7F5||1980-05-17|Jordan Moore
        LAB-7WDPQNW||not-a-date|Smith
        """; // Close Text Block for source data

    public static String migrate_lab_results() { // Main method to execute the migration and return CSV string
        var targets = Arrays.stream(TARGET_DATA.split("\\R")).skip(1) // Split target text by newline and skip header row
            .map(line -> line.split(",")) // Split each CSV row by comma delimiter into an array
            .map(p -> new Target(p[0], p[1], p[2], p[3], p[4])) // Map string arrays into Target objects
            .toList(); // Collect the processed stream into an immutable List (Java 16+)

        String results = Arrays.stream(SOURCE_DATA.split("\\R")).skip(1) // Split source text by newline and skip header row
            .map(line -> line.split("\\|", -1)) // Split by pipe delimiter, keeping empty trailing fields using -1
            .map(p -> new Source(p[0], p[1], p[2], p.length > 3 ? p[3] : "")) // Map into Source objects, handling missing name column
            .map(src -> matchPatient(src, targets)) // Attempt to map the parsed source to a target MRN
            .filter(Objects::nonNull) // Filter out source records that returned null (couldn't be reliably matched)
            .collect(Collectors.joining("\n")); // Join the successfully matched rows with newline characters

        return "MRN,LAB_ID\n" + results; // Prepend the final output string with the required CSV header
    } // End of migrate_lab_results method

    private static String matchPatient(Source src, List<Target> targets) { // Helper method to map a single source to the target list
        String nSsn = normalizeSsn(src.ssn()); // Normalize the source SSN to match the target's dashed format
        String nDob = normalizeDob(src.dob()); // Normalize the source DOB to match the target's YYYY-MM-DD format
        String[] nName = normalizeName(src.name()); // Parse the source name into a [lastName, firstName] array

        return targets.stream() // Convert target list to a stream to search for a match
            .filter(t -> isMatch(t, nSsn, nDob, nName[0], nName[1])) // Apply our reliability logic to each target
            .findFirst() // Take the first valid match found (assuming patients are unique in target)
            .map(t -> t.mrn() + "," + src.labId()) // If matched, format the string precisely as "MRN,LAB_ID"
            .orElse(null); // If no match is found for this source, return null to drop it from the final list
    } // End of matchPatient method

    private static boolean isMatch(Target t, String ssn, String dob, String last, String first) { // Defines match reliability
        boolean ssnMatch = ssn != null && ssn.equals(t.ssn()); // Check if SSNs match exactly (strongest possible identifier)
        boolean dobMatch = dob != null && dob.equals(t.dob()); // Check if normalized Date of Births match exactly
        boolean lastMatch = last != null && last.equalsIgnoreCase(t.last()); // Check if last names match, ignoring case variations
        boolean firstMatch = first != null && first.equalsIgnoreCase(t.first()); // Check if first names match, ignoring case variations

        return ssnMatch || (dobMatch && lastMatch && (first == null || firstMatch)); // Return true if SSN matches OR (DOB + Last match)
    } // End of isMatch method

    private static String normalizeSsn(String ssn) { // Method to clean and safely format messy SSNs
        if (ssn == null || ssn.isBlank()) return null; // Return null immediately if the SSN field is missing or whitespace
        String digits = ssn.replaceAll("\\D", ""); // Use regex to strip away any character that is not a digit
        if (digits.length() != 9) return null; // Return null if we don't end up with exactly 9 digits (invalid SSN)
        return digits.substring(0, 3) + "-" + digits.substring(3, 5) + "-" + digits.substring(5); // Reformat to clean NNN-NN-NNNN
    } // End of normalizeSsn method

    private static String normalizeDob(String dob) { // Method to parse and standardize various Date of Birth strings
        if (dob == null || dob.isBlank()) return null; // Return null immediately if DOB is missing or empty
        String[] parts = dob.trim().split("[/-]"); // Split the date string by either a forward slash or a hyphen
        if (parts.length != 3) return null; // If we don't have exactly 3 parts (month/day/year), the date is malformed
        try { // Use a try-catch block to handle errors gracefully if text was entered instead of numbers
            if (parts[0].length() == 4) { // If the first element is 4 characters long, assume the format is YYYY-MM-DD
                return String.format("%04d-%02d-%02d", Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])); // Pad with zeros
            } else if (parts[2].length() == 4) { // If the last element is 4 characters long, assume the format is MM-DD-YYYY
                return String.format("%04d-%02d-%02d", Integer.parseInt(parts[2]), Integer.parseInt(parts[0]), Integer.parseInt(parts[1])); // Rearrange and pad
            } // End of if-else logic
        } catch (NumberFormatException e) { // Catch exceptions if the parsed string parts cannot be converted to integers
            return null; // Return null to indicate normalization failed safely
        } // End of try-catch block
        return null; // Fallback return null if no standard year length was detected
    } // End of normalizeDob method

    private static String[] normalizeName(String name) { // Method to split full messy names into [last, first]
        if (name == null || name.isBlank()) return new String[]{null, null}; // Return an array of nulls if name is empty
        String cleanName = name.trim(); // Remove leading and trailing whitespace from the name
        if (cleanName.contains(",")) { // Check if the name utilizes the "Last, First" convention
            String[] parts = cleanName.split(","); // Split the string at the comma
            return new String[]{parts[0].trim(), parts.length > 1 ? parts[1].trim() : null}; // Return Last name, and First if present
        } else { // Handle the "First Last" or solo "Last" formats
            String[] parts = cleanName.split("\\s+"); // Split the string by one or more spaces
            if (parts.length == 1) return new String[]{parts[0], null}; // If only one word exists, assume it is the Last name
            return new String[]{parts[parts.length - 1], parts[0]}; // Last word is Last name, first word is First name
        } // End of if-else block
    } // End of normalizeName method

    public static void main(String[] args) { // Simple main method used specifically for validation (replaces JUnit)
        System.out.println("Starting Lab Result Migration Tests..."); // Print initialization message
        String result = migrate_lab_results(); // Execute the primary migration logic on our hardcoded mock data

        // Define the expected output string manually based on evaluating our mock data and matching logic rules
        String expected = "MRN,LAB_ID\nMRN000001,LAB00001\nMRN000002,LAB00002\nMRN-A1CKAZT,LAB-OBJW7F5"; // LAB-7WDPQNW should fail to match

        if (result.trim().equals(expected.trim())) { // Compare actual result with expected result, trimming trailing whitespace
            System.out.println("PASS - Migration produced correct CSV output."); // Print success message if they match exactly
        } else { // Triggered if the output does not match expectation
            System.out.println("FAIL - Migration output did not match expected."); // Print failure message
            System.out.println("Expected:\n" + expected); // Show the developer what was expected for quick debugging
            System.out.println("Actual:\n" + result); // Show the developer what was actually produced by the method
        } // End of validation if-else block
    } // End of main method

    record Target(String mrn, String ssn, String dob, String last, String first) {} // Java Record for concise, immutable target data

    // ------------------- 5. Testing ------------------- 
    
    record Source(String labId, String ssn, String dob, String name) {} // Java Record for concise, immutable source data
} // End of Result class