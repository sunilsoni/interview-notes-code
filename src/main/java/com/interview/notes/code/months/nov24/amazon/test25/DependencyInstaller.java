package com.interview.notes.code.months.nov24.amazon.test25;

import java.util.*;

public class DependencyInstaller {
    public static void main(String[] args) {
        // Define packages
        List<Package> packages = Arrays.asList(
            new Package("A", "1.0", Arrays.asList("B", "C")),
            new Package("B", "1.0", Arrays.asList("D", "E", "F")),
            new Package("C", "1.0", Arrays.asList("F")),
            new Package("D", "1.0", Collections.emptyList()),
            new Package("E", "1.0", Collections.emptyList()),
            new Package("F", "1.0", Arrays.asList("G")),
            new Package("G", "1.0", Collections.emptyList())
        );

        DependencyResolver resolver = new DependencyResolver(packages);

        // Test Case 1: Install Package A
        boolean result1 = resolver.resolveDependencies("A");
        if (result1) {
            List<String> order = resolver.getInstallationOrder();
            System.out.println("Test Case 1 PASS: Installation Order - " + order);
        } else {
            System.out.println("Test Case 1 FAIL: Unable to resolve dependencies for Package A");
        }

        // Test Case 2: Install Package F
        resolver = new DependencyResolver(packages); // Reset resolver
        boolean result2 = resolver.resolveDependencies("F");
        if (result2) {
            List<String> order = resolver.getInstallationOrder();
            System.out.println("Test Case 2 PASS: Installation Order - " + order);
        } else {
            System.out.println("Test Case 2 FAIL: Unable to resolve dependencies for Package F");
        }

        // Test Case 3: Install Package with Cycle
        List<Package> packagesWithCycle = Arrays.asList(
            new Package("A", "1.0", Arrays.asList("B")),
            new Package("B", "1.0", Arrays.asList("C")),
            new Package("C", "1.0", Arrays.asList("A"))
        );
        resolver = new DependencyResolver(packagesWithCycle);
        boolean result3 = resolver.resolveDependencies("A");
        if (!result3) {
            System.out.println("Test Case 3 PASS: Detected cycle in dependencies");
        } else {
            System.out.println("Test Case 3 FAIL: Cycle not detected");
        }

        // Test Case 4: Install Package with Missing Dependency
        List<Package> packagesWithMissing = Arrays.asList(
            new Package("A", "1.0", Arrays.asList("B")),
            new Package("B", "1.0", Arrays.asList("C"))
            // Package C is missing
        );
        resolver = new DependencyResolver(packagesWithMissing);
        boolean result4 = resolver.resolveDependencies("A");
        if (!result4) {
            System.out.println("Test Case 4 PASS: Detected missing dependency");
        } else {
            System.out.println("Test Case 4 FAIL: Missing dependency not detected");
        }

        // Test Case 5: Large Data Input
        List<Package> largePackageList = new ArrayList<>();
        int numPackages = 10000;
        for (int i = 1; i <= numPackages; i++) {
            String pkgName = "Pkg" + i;
            List<String> deps = (i < numPackages) ? Arrays.asList("Pkg" + (i + 1)) : Collections.emptyList();
            largePackageList.add(new Package(pkgName, "1.0", deps));
        }
        resolver = new DependencyResolver(largePackageList);
        long startTime = System.currentTimeMillis();
        boolean result5 = resolver.resolveDependencies("Pkg1");
        long endTime = System.currentTimeMillis();
        if (result5 && resolver.getInstallationOrder().size() == numPackages) {
            System.out.println("Test Case 5 PASS: Large data input handled successfully in " + (endTime - startTime) + "ms");
        } else {
            System.out.println("Test Case 5 FAIL: Large data input handling issue");
        }
    }
}
