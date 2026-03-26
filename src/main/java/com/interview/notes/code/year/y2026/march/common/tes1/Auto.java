package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.ArrayList;
import java.util.List;

public class Auto {

    public static void main(String[] args) {
        // 1. Setup Graph 1: The known, labeled reference points
        List<LabeledPoint> referencePoints = new ArrayList<>();
        referencePoints.add(new LabeledPoint("Point1", 1.0, 1.0));
        referencePoints.add(new LabeledPoint("Point2", 1000.0, 1000.0));

        // 2. Setup Graph 2: The new points we want to label
        List<LabeledPoint> unlabeledPoints = new ArrayList<>();
        unlabeledPoints.add(new LabeledPoint("PointA", 1.1, 1.1));       // Close to Point1
        unlabeledPoints.add(new LabeledPoint("PointB", 1000.1, 1000.1)); // Close to Point2
        unlabeledPoints.add(new LabeledPoint("PointC", 500.0, 500.0));   // Outlier test

        // 3. Define our matching rule
        double toleranceInMicrons = 5.0;

        System.out.println("--- Starting Auto Labelling Process ---");
        matchAndLabel(referencePoints, unlabeledPoints, toleranceInMicrons);
    }

    public static void matchAndLabel(List<LabeledPoint> references, List<LabeledPoint> targets, double tolerance) {

        // Loop through every point that needs a label
        for (LabeledPoint target : targets) {
            String bestLabel = "No Match";
            double minDistance = Double.MAX_VALUE; // Start with the highest possible distance

            // Compare this target against every known reference point
            for (LabeledPoint ref : references) {

                // Math.hypot calculates the straight-line distance between two coordinates
                double distance = Math.hypot(target.x() - ref.x(), target.y() - ref.y());

                // If this is the closest point we've seen so far, remember it
                if (distance < minDistance) {
                    minDistance = distance;
                    bestLabel = ref.label();
                }
            }

            // Finally, check if the absolute closest point is within our 5-micron tolerance
            if (minDistance <= tolerance) {
                System.out.printf("Target '%s' at (%.1f, %.1f) is matched to '%s' (Distance: %.3f microns)%n",
                        target.label(), target.x(), target.y(), bestLabel, minDistance);
            } else {
                System.out.printf("Target '%s' at (%.1f, %.1f) failed tolerance check. Closest was %.3f microns away.%n",
                        target.label(), target.x(), target.y(), minDistance);
            }
        }
    }

    // A simple, modern way to hold a point's label and its X/Y coordinates
    public record LabeledPoint(String label, double x, double y) {}
}