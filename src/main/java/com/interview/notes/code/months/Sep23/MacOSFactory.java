package com.interview.notes.code.months.Sep23;

import java.awt.*;

// Concrete Factory for macOS
class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}