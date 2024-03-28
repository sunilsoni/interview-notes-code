package com.interview.notes.code.months.march24.test21;


public class Image implements Media {
    private String imagePath;

    // Constructor, getters, and setters

    @Override
    public void render(MediaRenderer renderer) {
        renderer.renderImage(this);
    }
}