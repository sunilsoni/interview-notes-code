package com.interview.notes.code.year.y2026.june.apple.test4;

import java.util.List; // Required for collection handling
import java.util.Map; // Required to map Targets to their specific invalid dependencies
import java.util.Set; // Required for O(1) known target lookups
import java.util.stream.Collectors; // Required to collect stream results into our Map

// Immutable data carrier for our build targets
record Target(String name, List<String> dependencies) {}

public class BuildValidator {

    // Pure function: Calculates business logic and returns data. No side effects. No console printing.
    public static Map<String, List<String>> findInvalidDependencies(Set<String> knownTargets, List<Target> targets) {
        
        // Fast-fail safety check to prevent NullPointerExceptions on bad input
        if (knownTargets == null || targets == null) return Map.of();
        
        return targets.stream() // Open a stream over the incoming targets
            .filter(t -> t != null && t.dependencies() != null) // Drop completely null targets or targets with null dependency lists
            .collect(Collectors.toMap( // Begin grouping our results into a Map structure
                Target::name, // The Map Key is the name of the Target requesting the dependencies
                t -> t.dependencies().stream() // The Map Value requires opening a sub-stream of the dependencies
                    .filter(dep -> dep != null && !dep.isBlank()) // Drop empty string junk data
                    .filter(dep -> !knownTargets.contains(dep)) // Retain only the dependencies that DO NOT exist in our known set
                    .toList() // Collect these broken dependencies into a List for this specific Target
            )) 
            // At this point, valid targets have an empty list: { "App"=[], "UI"=["Logger"] }
            // We need to clean up the map to ONLY return targets that actually have errors.
            .entrySet().stream() // Stream the key-value pairs of our freshly built map
            .filter(entry -> !entry.getValue().isEmpty()) // Filter out any Target that has an empty list (meaning it had 0 errors)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); // Repackage the remaining broken entries back into a final clean Map
    }

    // Caller function: Handles environment context and presentation/I-O
    public static void main(String[] args) {
        
        Set<String> known = Set.of("App", "DB", "UI", "API"); // Our master list of valid targets
        Target uiTarget = new Target("UI", List.of("API", "Logger", "Auth")); // Target asking for 2 invalid things
        Target appTarget = new Target("App", List.of("DB")); // Perfectly valid target
        
        // 1. EXECUTE BUSINESS LOGIC (Separated from presentation)
        Map<String, List<String>> diagnosticData = findInvalidDependencies(known, List.of(uiTarget, appTarget));
        
        // 2. HANDLE PRESENTATION AND I/O (Caller decides what to do with the data)
        if (diagnosticData.isEmpty()) { // Check if the build passed
            System.out.println("[SUCCESS] All build targets are valid."); // Emit success state
        } else {
            System.err.println("==================================================="); // Start formatting error block
            System.err.println("BUILD FAILED: Invalid Dependencies Detected"); // Emit failure header
            System.err.println("==================================================="); // Format separator
            
            // Iterate through our perfectly grouped data structure to present it cleanly to the user
            diagnosticData.forEach((targetName, missingDeps) -> { // Loop through the Map keys and values
                System.err.println("\nTarget: [" + targetName + "]"); // Print the specific target that failed
                missingDeps.forEach(dep -> System.err.println("  ❌ Unknown dependency: '" + dep + "'")); // Print all things it wrongly asked for
            });
            
            System.exit(1); // Standard practice to halt the build process with a non-zero failure code
        }
    }
}