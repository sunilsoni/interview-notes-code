package com.interview.notes.code.year.y2026.march.common.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Auto {

    public static void main(String[] args) {
        List<LabeledPoint> references = new ArrayList<>();
        references.add(new LabeledPoint("Ref_100", 100.0, 100.0));

        List<LabeledPoint> targets = new ArrayList<>();
        // Target A gets there first, but Target B is actually closer
        targets.add(new LabeledPoint("Target_A", 100.5, 100.5));
        targets.add(new LabeledPoint("Target_B", 100.0, 100.0));

        matchWithOverride(references, targets, 5.0);
    }

    public static void matchWithOverride(List<LabeledPoint> references, List<LabeledPoint> targets, double tolerance) {

        // This map remembers which Target currently owns the Reference
        Map<String, LabeledPoint> finalMatches = new HashMap<>();

        // This map remembers the winning distance for that Reference
        Map<String, Double> bestDistances = new HashMap<>();

        // ONE SINGLE PASS through the Targets
        for (LabeledPoint target : targets) {

            LabeledPoint bestRefForThisTarget = null;
            double minDistance = tolerance;

            // Find the closest Reference for this specific Target
            for (LabeledPoint ref : references) {
                double distance = Math.hypot(target.x() - ref.x(), target.y() - ref.y());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestRefForThisTarget = ref;
                }
            }

            // If we found a Reference within the 5-micron tolerance...
            if (bestRefForThisTarget != null) {
                String refName = bestRefForThisTarget.label();

                // Check if someone else already claimed this Reference point
                if (finalMatches.containsKey(refName)) {

                    // A collision! They fight for it based on distance.
                    if (minDistance < bestDistances.get(refName)) {
                        // The new Target is closer! Steal it and replace the old one.
                        finalMatches.put(refName, target);
                        bestDistances.put(refName, minDistance);
                    }
                    // If the new Target is further away, it loses and we do nothing.

                } else {
                    // It's completely free. Claim it!
                    finalMatches.put(refName, target);
                    bestDistances.put(refName, minDistance);
                }
            }
        }

        // Print the final, optimized matches
        System.out.println("--- Final One-to-One Matches ---");
        for (Map.Entry<String, LabeledPoint> entry : finalMatches.entrySet()) {
            System.out.printf("Reference '%s' is securely matched to '%s' (Winning Distance: %.3f)%n",
                    entry.getKey(), entry.getValue().label(), bestDistances.get(entry.getKey()));
        }
    }

    public record LabeledPoint(String label, double x, double y) {}
}