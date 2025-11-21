package com.interview.notes.code.year.y2024.nov24.amazon.test25;

import java.util.*;

public class DependencyResolver {
    private final Map<String, Package> packageMap;
    private final Set<String> installed;
    private final List<String> installationOrder;

    public DependencyResolver(List<Package> packages) {
        packageMap = new HashMap<>();
        for (Package pkg : packages) {
            packageMap.put(pkg.getName(), pkg);
        }
        installed = new HashSet<>();
        installationOrder = new ArrayList<>();
    }

    public boolean resolveDependencies(String packageName) {
        Set<String> visiting = new HashSet<>();
        return dfs(packageName, visiting);
    }

    private boolean dfs(String current, Set<String> visiting) {
        if (installed.contains(current)) {
            return true;
        }

        if (visiting.contains(current)) {
            // Cycle detected
            return false;
        }

        Package pkg = packageMap.get(current);
        if (pkg == null) {
            // Missing package
            return false;
        }

        visiting.add(current);
        for (String dep : pkg.getDependencies()) {
            if (!dfs(dep, visiting)) {
                return false;
            }
        }
        visiting.remove(current);
        installed.add(current);
        installationOrder.add(current);
        return true;
    }

    public List<String> getInstallationOrder() {
        return installationOrder;
    }
}
