package com.interview.notes.code.year.y2024.nov24.amazon.test25;

import java.util.List;

public class Package {
    private final String name;
    private final String version;
    private final List<String> dependencies;

    public Package(String name, String version, List<String> dependencies) {
        this.name = name;
        this.version = version;
        this.dependencies = dependencies;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    // toString method for better readability
    @Override
    public String toString() {
        return "Package{name='" + name + "', version='" + version + "', dependencies=" + dependencies + "}";
    }
}
