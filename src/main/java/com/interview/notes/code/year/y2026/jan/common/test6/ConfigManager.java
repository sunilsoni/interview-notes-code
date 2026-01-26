package com.interview.notes.code.year.y2026.jan.common.test6;

public enum ConfigManager {
    INSTANCE;

    // -------- TEST --------
    public static void main(String[] args) {
        ConfigManager s1 = ConfigManager.INSTANCE;
        ConfigManager s2 = ConfigManager.INSTANCE;

        if (s1 == s2) {
            System.out.println("PASS: Same instance");
        } else {
            System.out.println("FAIL: Different instances");
        }

        s1.load();
    }

    public void load() {
        System.out.println("Config loaded");
    }
}
