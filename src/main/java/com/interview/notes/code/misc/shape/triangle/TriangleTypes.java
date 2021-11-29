package com.interview.notes.code.misc.shape.triangle;


/**
 * A triangle is a polygon with three sides.
 *
 * @Ref https://www.math-only-math.com/triangle-a.html
 * A triangle has
 * <p>
 * three line-segments or sides
 * three vertices
 * three angles
 * There are six types of triangles, 3 with respect to sides and 3 with respect to angles.
 */
public enum TriangleTypes {
    //A triangle having all the three line-segments or sides equal is called an equilateral triangle.
    //Here AB = BC = CA.
    EQUILATERAL,

    //A triangle having a pair of its sides or two line-segments equal is called an isosceles triangle.
    //Here AB = AC.
    ISOSCELES,

    //A triangle having all the three line-segments or sides unequal is called a scalene triangle.
    SCALENE;
}