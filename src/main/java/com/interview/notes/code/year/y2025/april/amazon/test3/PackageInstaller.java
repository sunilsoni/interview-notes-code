package com.interview.notes.code.year.y2025.april.amazon.test3;

import java.util.*;

public class PackageInstaller {
    private Map<String, Package> packages = new HashMap<>();
    // Key: package name, Value: set of packages that depend on this package (parents)
    private Map<String, Set<String>> parentMap = new HashMap<>();

    // When adding a package, update the parent map
    public void addPackage(String name, List<String> dependencies) {
        packages.put(name, new Package(name, dependencies));
        
        // For each dependency, add the current package as its parent
        for (String dep : dependencies) {
            parentMap.computeIfAbsent(dep, k -> new HashSet<>()).add(name);
        }
    }

    // O(1) operation to get parents
    public List<String> getParents(String pkgName) {
        return new ArrayList<>(parentMap.getOrDefault(pkgName, new HashSet<>()));
    }
}
