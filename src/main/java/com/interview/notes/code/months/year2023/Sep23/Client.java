package com.interview.notes.code.months.year2023.Sep23;

// Client code
public class Client {
    public static void main(String[] args) {
        // Create a factory for Windows components
        GUIFactory windowsFactory = new WindowsFactory();

        // Create Windows button and checkbox
        Button windowsButton = windowsFactory.createButton();
        Checkbox windowsCheckbox = windowsFactory.createCheckbox();

        // Render Windows components
        windowsButton.paint();
        windowsCheckbox.paint();

        // Create a factory for macOS components
        GUIFactory macosFactory = new MacOSFactory();

        // Create macOS button and checkbox
        Button macosButton = macosFactory.createButton();
        Checkbox macosCheckbox = macosFactory.createCheckbox();

        // Render macOS components
        macosButton.paint();
        macosCheckbox.paint();
    }
}

