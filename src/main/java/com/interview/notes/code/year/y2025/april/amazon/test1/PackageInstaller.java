package com.interview.notes.code.year.y2025.april.amazon.test1;

import java.util.*;



public class PackageInstaller {
    private Map<String, Package> packages = new HashMap<>();
    private Set<String> installed = new HashSet<>();
    private Set<String> visiting = new HashSet<>();

    // Add a package with its dependencies
    public void addPackage(String name, List<String> dependencies) {
        packages.put(name, new Package(name, dependencies));
    }

    // Install a package and its dependencies
    public List<String> install(String packageName) {
        List<String> installationOrder = new ArrayList<>();
        installPackage(packageName, installationOrder);
        return installationOrder;
    }

    private void installPackage(String packageName, List<String> installationOrder) {
        // Skip if already installed
        if (installed.contains(packageName)) return;
        
        // Check for circular dependency
        if (visiting.contains(packageName)) {
            throw new RuntimeException("Circular dependency detected: " + packageName);
        }

        // Mark as being visited
        visiting.add(packageName);

        Package pkg = packages.get(packageName);
        if (pkg == null) {
            throw new RuntimeException("Package not found: " + packageName);
        }

        // Install dependencies first
        for (String dep : pkg.dependencies) {
            installPackage(dep, installationOrder);
        }

        // Install the package
        if (!installed.contains(packageName)) {
            installationOrder.add(packageName);
            installed.add(packageName);
        }

        visiting.remove(packageName);
    }

    // Main method for testing
    public static void main(String[] args) {
        PackageInstaller installer = new PackageInstaller();

        // Test Case 1: Basic installation
        System.out.println("Test Case 1: Basic Installation");
        try {
            installer = new PackageInstaller();
            installer.addPackage("A", Arrays.asList("B", "C"));
            installer.addPackage("B", Arrays.asList("D", "E", "F"));
            installer.addPackage("C", Arrays.asList("F"));
            installer.addPackage("D", Arrays.asList("G"));
            installer.addPackage("E", new ArrayList<>());
            installer.addPackage("F", new ArrayList<>());
            installer.addPackage("G", new ArrayList<>());

            List<String> result = installer.install("A");
            System.out.println("Installation order: " + result);
            System.out.println("PASS");
        } catch (Exception e) {
            System.out.println("FAIL: " + e.getMessage());
        }

        // Test Case 2: Circular Dependency
        System.out.println("\nTest Case 2: Circular Dependency");
        try {
            installer = new PackageInstaller();
            installer.addPackage("X", Arrays.asList("Y"));
            installer.addPackage("Y", Arrays.asList("X"));
            installer.install("X");
            System.out.println("FAIL: Should detect circular dependency");
        } catch (Exception e) {
            System.out.println("PASS: Detected circular dependency");
        }

        // Test Case 3: Missing Package
        System.out.println("\nTest Case 3: Missing Package");
        try {
            installer = new PackageInstaller();
            installer.install("NonExistent");
            System.out.println("FAIL: Should detect missing package");
        } catch (Exception e) {
            System.out.println("PASS: Detected missing package");
        }
    }
}
