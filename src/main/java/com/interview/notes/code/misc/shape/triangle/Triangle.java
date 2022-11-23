package com.interview.notes.code.misc.shape.triangle;

import com.interview.notes.code.misc.shape.Shape;
import com.interview.notes.code.misc.shape.ShapeTypes;

public abstract class Triangle implements Shape {

    // todoe
    protected double[] sides;

    public Enum<ShapeTypes> getShapeType() {
        return ShapeTypes.TRIANGLE;
    }

    public abstract double getArea();

    public abstract double getCircumference();

    public abstract TriangleTypes getTriangleType();

}