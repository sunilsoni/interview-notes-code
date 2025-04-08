package com.interview.notes.code.year.y2025.april.amazon.test2;

import java.util.*;
import java.util.stream.Collectors;

// Defines a Package and its dependencies
class Package {
    String name;
    List<String> dependencies;

    Package(String name) {
        this.name = name;
        this.dependencies = new ArrayList<>();
    }

    void addDependency(String dependency) {
        dependencies.add(dependency);
    }
}

public class PackageInstaller {

    Map<String, Package> packageMap = new HashMap<>();
    Set<String> visited = new HashSet<>();
    Set<String> visiting = new HashSet<>();
    List<String> installationOrder = new ArrayList<>();

    // Build the package structure
    void addPackageDependency(String pkg, String... dependencies) {
        packageMap.putIfAbsent(pkg, new Package(pkg));
        for (String dep : dependencies) {
            packageMap.putIfAbsent(dep, new Package(dep));
            packageMap.get(pkg).addDependency(dep);
        }
    }

    // Resolve dependencies and generate installation order
    boolean buildInstallationOrder() {
        for (String pkg : packageMap.keySet()) {
            if (!visited.contains(pkg)) {
                if (!resolve(pkg)) {
                    System.out.println("Cycle detected! Cannot install packages.");
                    return false;
                }
            }
        }
        Collections.reverse(installationOrder);  // Reverse for correct install order
        return true;
    }

    // Helper method to resolve dependencies recursively
    boolean resolve(String pkg) {
        if (visiting.contains(pkg)) return false;  // Detect cycles
        if (visited.contains(pkg)) return true;

        visiting.add(pkg);
        for (String dep : packageMap.get(pkg).dependencies) {
            if (!resolve(dep)) return false;
        }
        visiting.remove(pkg);
        visited.add(pkg);
        installationOrder.add(pkg);
        return true;
    }

    // Execute installation
    void executeInstallation() {
        installationOrder.forEach(pkg -> System.out.println("Installing: " + pkg));
    }

    // Test cases
    public static void main(String[] args) {
        PackageInstaller installer = new PackageInstaller();

        // Minimal reproducible example based on provided dependencies
        installer.addPackageDependency("A", "B", "C");
        installer.addPackageDependency("B", "D", "E", "F");
        installer.addPackageDependency("C", "F");
        installer.addPackageDependency("D", "G");

        boolean result = installer.buildInstallationOrder();
        if (result) {
            installer.executeInstallation();
            System.out.println("Test Case 1: PASS\n");
        } else {
            System.out.println("Test Case 1: FAIL\n");
        }

        // Edge Case: Cyclic Dependency
        PackageInstaller installer2 = new PackageInstaller();
        installer2.addPackageDependency("X", "Y");
        installer2.addPackageDependency("Y", "Z");
        installer2.addPackageDependency("Z", "X");  // Cyclic

        if (!installer2.buildInstallationOrder()) {
            System.out.println("Test Case 2 (Cycle Detection): PASS\n");
        } else {
            System.out.println("Test Case 2 (Cycle Detection): FAIL\n");
        }

        // Large data input test
        PackageInstaller installer3 = new PackageInstaller();
        for (int i = 1; i <= 10000; i++) {
            installer3.addPackageDependency("Pkg" + i, "Pkg" + (i + 1));
        }
        installer3.addPackageDependency("Pkg10001");

        if (installer3.buildInstallationOrder()) {
            System.out.println("Test Case 3 (Large Data): PASS");
        } else {
            System.out.println("Test Case 3 (Large Data): FAIL");
        }
    }
}
