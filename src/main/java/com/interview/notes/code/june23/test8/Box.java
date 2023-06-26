package com.interview.notes.code.june23.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Box {
    private int[] dimensions;

    public Box(int length, int width, int height) {
        this.dimensions = new int[]{length, width, height};
        Arrays.sort(this.dimensions);
    }

    public boolean canFit(int[] itemDimensions) {
        for (int i = 0; i < 3; i++) {
            if (itemDimensions[i] > dimensions[i]) {
                return false;
            }
        }
        return true;
    }
}

class BoxManager {
    private List<Box> boxes;

    public BoxManager() {
        this.boxes = new ArrayList<>();
    }

    public void addBox(Box box) {
        this.boxes.add(box);
    }

    public List<Box> getBoxes() {
        return this.boxes;
    }
}

class FitChecker {
    private BoxManager boxManager;

    public FitChecker(BoxManager boxManager) {
        this.boxManager = boxManager;
    }

    public boolean canItemBeHidden(int[] itemDimensions) {
        for (Box box : this.boxManager.getBoxes()) {
            if (box.canFit(itemDimensions)) {
                return true;
            }
        }
        return false;
    }
}
