package com.interview.notes.code.year.y2025.march.amazon.test3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class Package {
    String name;
    List<Package> dependencies;

    Package(String name) {
        this.name = name;
        this.dependencies = new ArrayList<>();
    }

    void addDependency(Package dependency) {
        dependencies.add(dependency);
    }
}

public class PackageInstaller {

    // Set to keep track of installed packages and avoid repetition
    private Set<String> installed = new LinkedHashSet<>();

    // Main method to test the installation logic
    public static void main(String[] args) {
        PackageInstaller installer = new PackageInstaller();

        // Create packages as per provided structure
        Package A = new Package("A");
        Package B = new Package("B");
        Package C = new Package("C");
        Package D = new Package("D");
        Package E = new Package("E");
        Package F = new Package("F");
        Package G = new Package("G");

        // Define dependencies
        A.addDependency(B);
        A.addDependency(C);
        B.addDependency(D);
        B.addDependency(E);
        B.addDependency(F);
        C.addDependency(F);
        F.addDependency(G);

        // Test installing package A
        installer.installPackage(A);

        // Test results
        installer.verifyInstallationOrder(List.of("D", "E", "G", "F", "B", "C", "A"));

        // Additional test for edge case: Already installed package
        System.out.println("\nTesting re-installation of package B:");
        installer.installPackage(B);
    }

    // Method to perform package installation
    public void installPackage(Package pkg) {
        if (!installed.contains(pkg.name)) {
            pkg.dependencies.forEach(this::installPackage); // Install dependencies first (recursive)
            installed.add(pkg.name);                        // Then install the current package
            System.out.println("Installed package: " + pkg.name);
        }
    }

    // Verify correct installation order
    public void verifyInstallationOrder(List<String> expectedOrder) {
        List<String> installedOrder = new ArrayList<>(installed);
        if (installedOrder.equals(expectedOrder)) {
            System.out.println("TEST PASS: Installation order is correct.");
        } else {
            System.out.println("TEST FAIL: Installation order incorrect.");
            System.out.println("Expected: " + expectedOrder);
            System.out.println("Actual: " + installedOrder);
        }
    }
}
