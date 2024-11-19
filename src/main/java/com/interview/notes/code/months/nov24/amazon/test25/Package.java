package com.interview.notes.code.months.nov24.amazon.test25;

import java.util.List;

public class Package {
    private String name;
    private String version;
    private List<String> dependencies;

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
