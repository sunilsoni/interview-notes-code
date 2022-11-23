package com.interview.notes.code.misc.shape.triangle;

public class ScaleneTriangle extends Triangle {

    public ScaleneTriangle(double[] sides) {
        super.sides = sides;
    }

    @Override
    public double getArea() {
        double s = (sides[0] + sides[1] + sides[2]) / 2;
        double area = Math.sqrt(s * (s - sides[0]) * (s - sides[1]) * (s - sides[2]));
        return area;
    }

    @Override
    public double getCircumference() {
        return 0;
    }

    @Override
    public TriangleTypes getTriangleType() {
        return TriangleTypes.SCALENE;
    }
}