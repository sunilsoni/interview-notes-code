package com.interview.notes.code.misc.shape.triangle;

//A triangle having all the three line-segments or sides equal is called an equilateral triangle.
//Here AB = BC = CA.
public class EquilateralTriangle extends Triangle {

    public EquilateralTriangle(double[] sides) {
        super.sides = sides;
    }

    @Override
    public double getArea() {
        return (double) (sides[0] * sides[0]) * ((Math.sqrt(3)) / 4);
    }

    @Override
    public double getCircumference() {
        return 0;
    }

    @Override
    public TriangleTypes getTriangleType() {
        return TriangleTypes.EQUILATERAL;
    }

}