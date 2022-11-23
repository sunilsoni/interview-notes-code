package com.interview.notes.code.misc.shape.triangle;

public class IsoscelesTriangle extends Triangle {

    public IsoscelesTriangle(double[] sides) {
        super.sides = sides;
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getCircumference() {
        return 0;
    }

    @Override
    public TriangleTypes getTriangleType() {
        return TriangleTypes.ISOSCELES;
    }

}