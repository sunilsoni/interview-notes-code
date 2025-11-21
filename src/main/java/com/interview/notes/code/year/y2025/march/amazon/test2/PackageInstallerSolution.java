package com.interview.notes.code.year.y2025.march.amazon.test2;

import java.util.*;

// Main class containing the solution and test cases.
public class PackageInstallerSolution {

    /**
     * Test method to run a test case.
     * It installs a root package and compares the installation order against the expected order.
     *
     * @param testCaseName  The name of the test case.
     * @param rootPackage   The starting package for installation.
     * @param expectedOrder The expected installation order.
     */
    public static void runTestCase(String testCaseName, Package rootPackage, List<String> expectedOrder) {
        System.out.println("Running test case: " + testCaseName);
        // Create a new installer instance for the test.
        PackageInstaller installer = new PackageInstaller();
        // Install the root package (this will recursively install dependencies).
        installer.install(rootPackage);
        // Retrieve the actual installation order.
        List<String> actualOrder = installer.getInstallationOrder();
        // Check if the actual order matches the expected order.
        if (actualOrder.equals(expectedOrder)) {
            System.out.println("Test " + testCaseName + " PASSED.");
        } else {
            System.out.println("Test " + testCaseName + " FAILED.");
            System.out.println("Expected: " + expectedOrder);
            System.out.println("Actual: " + actualOrder);
        }
        System.out.println("--------------");
    }

    /**
     * Main method to build sample dependency graphs and run tests.
     */
    public static void main(String[] args) {
        // === Sample Dependency Graph ===
        // Create packages A, B, C, D, E, F, G.
        Package pkgA = new Package("A");
        Package pkgB = new Package("B");
        Package pkgC = new Package("C");
        Package pkgD = new Package("D");
        Package pkgE = new Package("E");
        Package pkgF = new Package("F");
        Package pkgG = new Package("G");

        // Define dependencies as per the given example:
        // A depends on B and C.
        pkgA.addDependency(pkgB);
        pkgA.addDependency(pkgC);
        // B depends on D, E, and F.
        pkgB.addDependency(pkgD);
        pkgB.addDependency(pkgE);
        pkgB.addDependency(pkgF);
        // C depends on F.
        pkgC.addDependency(pkgF);
        // F depends on G.
        pkgF.addDependency(pkgG);

        // Expected installation order:
        // Since dependencies are installed first:
        // B: D, E, (F: G) then B; C: F (already installed) then C; finally A.
        // Thus the order should be: D, E, G, F, B, C, A.
        List<String> expectedOrder = Arrays.asList("D", "E", "G", "F", "B", "C", "A");
        // Run the sample test case.
        runTestCase("Sample Test", pkgA, expectedOrder);

        // === Large Data Test: Dependency Chain ===
        // Create a chain of 1000 packages (each depends on the next).
        Package chainRoot = new Package("P0");
        Package prev = chainRoot;
        // Build the chain.
        for (int i = 1; i <= 1000; i++) {
            Package next = new Package("P" + i);
            prev.addDependency(next);
            prev = next;
        }
        // The expected installation order is the reverse of the chain: starting from the last package to the first.
        List<String> expectedChainOrder = new ArrayList<>();
        // Add names from P0 to P1000.
        for (int i = 0; i <= 1000; i++) {
            expectedChainOrder.add("P" + i);
        }
        // Reverse the order so that dependencies come before dependents.
        Collections.reverse(expectedChainOrder);
        // Run the large chain test case.
        runTestCase("Large Chain Test", chainRoot, expectedChainOrder);
    }

    /**
     * Represents a software package with a name and its dependent packages.
     */
    public static class Package {
        // Name of the package.
        private final String name;
        // List of dependent packages.
        private final List<Package> dependencies;

        /**
         * Constructor initializes the package with a name and an empty list for dependencies.
         */
        public Package(String name) {
            this.name = name;
            this.dependencies = new ArrayList<>();
        }

        /**
         * Adds a dependency to this package.
         *
         * @param dependency The package that this package depends on.
         */
        public void addDependency(Package dependency) {
            this.dependencies.add(dependency);
        }

        /**
         * Getter for package name.
         */
        public String getName() {
            return name;
        }

        /**
         * Getter for package dependencies.
         */
        public List<Package> getDependencies() {
            return dependencies;
        }
    }

    /**
     * PackageInstaller is responsible for installing a package and all its dependencies.
     * It simulates installation by printing out the package names.
     */
    public static class PackageInstaller {
        // List to record installation order (useful for testing the correct sequence).
        private final List<String> installationOrder = new ArrayList<>();

        /**
         * Public method to install a package.
         * It initializes a set to track already installed packages to avoid duplicates.
         *
         * @param pkg The package to install.
         */
        public void install(Package pkg) {
            // Set to track installed package names.
            Set<String> installed = new HashSet<>();
            // Begin recursive installation.
            installRecursive(pkg, installed);
        }

        /**
         * Recursively installs the given package and its dependencies.
         * It uses a visited set to avoid re-installing packages.
         *
         * @param pkg       The current package to install.
         * @param installed Set containing names of packages that have been installed.
         */
        private void installRecursive(Package pkg, Set<String> installed) {
            // If already installed, skip this package.
            if (installed.contains(pkg.getName())) {
                return;
            }
            // Use Java 8 streams to iterate over each dependency.
            pkg.getDependencies().stream().forEach(dependency -> {
                // Recursively install each dependency.
                installRecursive(dependency, installed);
            });
            // Simulate the installation of the current package.
            performInstallation(pkg);
            // Mark the current package as installed.
            installed.add(pkg.getName());
            // Record the installation order for testing.
            installationOrder.add(pkg.getName());
        }

        /**
         * Simulates the installation logic.
         * In a real scenario, the platform-specific team would implement actual installation steps.
         *
         * @param pkg The package being installed.
         */
        private void performInstallation(Package pkg) {
            System.out.println("Installing package: " + pkg.getName());
        }

        /**
         * Getter for the recorded installation order.
         *
         * @return List of package names in the order they were installed.
         */
        public List<String> getInstallationOrder() {
            return installationOrder;
        }
    }
}