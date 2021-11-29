package com.interview.notes.code.misc.shape.triangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.interview.notes.code.misc.shape.Shape;
import com.interview.notes.code.misc.shape.ShapeFactory;
import com.interview.notes.code.misc.shape.ShapeTypes;

class TriangleTest {

    private static ShapeFactory shapeFactory = null;

    @BeforeClass
    public static void initShapeTest() {
        shapeFactory = new TriangleFactory();
    }

    @Before
    public void beforeEachTest() {
    }

    @After
    public void afterEachTest() {
    }

    @Test
    public void testInvalidTriangle1() {
        try {
            Triangle triangle = (Triangle) shapeFactory.getShape(1, 2, 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testInvalidTriangle2() {
        try {
            Triangle triangle = (Triangle) shapeFactory.getShape(5, 1, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testInvalidTriangle3() {
        try {
            Triangle triangle = (Triangle) shapeFactory.getShape(1, 5, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testInvalidTriangle4() {
        try {
            Triangle triangle = (Triangle) shapeFactory.getShape();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testInvalidTriangle5() {
        try {
            Shape shape = shapeFactory.getShape(1, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testZeroSizeTriangle1() {
        try {
            Triangle triangle = (Triangle) shapeFactory.getShape(0, 0, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testZeroSizeTriangle2() {
        try {
            Triangle triangle = (Triangle) shapeFactory.getShape(1, 2, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMinusSizeTriangle2() {
        try {
            Triangle triangle = (Triangle) shapeFactory.getShape(1, 2, -4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testEquilateralTriangle() {
        Triangle triangle = (Triangle) shapeFactory.getShape(3, 3, 3);
        assertEquals(triangle.getTriangleType(), TriangleTypes.EQUILATERAL);
    }

    @Test
    public void testValidTriangle() {
        Shape shape = (Triangle) shapeFactory.getShape(3, 4, 5);
        assertEquals(shape.getShapeType(), ShapeTypes.TRIANGLE);
    }

    @Test
    public void testInvalidEquilateralTriangle1() {
        Triangle triangle = (Triangle) shapeFactory.getShape(3, 4, 5);
        assertNotEquals(triangle.getTriangleType(), TriangleTypes.EQUILATERAL);
    }

    @Test
    public void testInvalidEquilateralTriangle2() {
        Triangle triangle = (Triangle) shapeFactory.getShape(3, 3, 5);
        assertNotEquals(triangle.getTriangleType(), TriangleTypes.EQUILATERAL);
    }

    @Test
    public void testIsoscelesTriangle1() {
        Triangle triangle = (Triangle) shapeFactory.getShape(3, 3, 5);
        assertEquals(triangle.getTriangleType(), TriangleTypes.ISOSCELES);
    }

    @Test
    public void testIsoscelesTriangle2() {
        Triangle triangle = (Triangle) shapeFactory.getShape(5, 3, 3);
        assertEquals(triangle.getTriangleType(), TriangleTypes.ISOSCELES);
    }

    @Test
    public void testIsoscelesTriangle3() {
        Triangle triangle = (Triangle) shapeFactory.getShape(3, 5, 3);
        assertEquals(triangle.getTriangleType(), TriangleTypes.ISOSCELES);
    }

    @Test
    public void testInvalidIsoscelesTriangle1() {
        Triangle triangle = (Triangle) shapeFactory.getShape(3, 4, 5);
        assertNotEquals(triangle.getTriangleType(), TriangleTypes.ISOSCELES);
    }

    @Test
    public void testInvalidIsoscelesTriangle2() {
        Triangle triangle = (Triangle) shapeFactory.getShape(1, 1, 1);
        assertNotEquals(triangle.getTriangleType(), TriangleTypes.ISOSCELES);
    }

    @Test
    public void testScaleneTriangle() {
        Triangle triangle = (Triangle) shapeFactory.getShape(3, 4, 5);
        assertEquals(triangle.getTriangleType(), TriangleTypes.SCALENE);
    }

    @Test
    public void testInvalidScaleneTriangle1() {
        Triangle triangle = (Triangle) shapeFactory.getShape(3, 3, 3);
        assertNotEquals(triangle.getTriangleType(), TriangleTypes.SCALENE);
    }

    @Test
    public void testInvalidScaleneTriangle2() {
        Triangle triangle = (Triangle) shapeFactory.getShape(3, 3, 5);
        assertNotEquals(triangle.getTriangleType(), TriangleTypes.SCALENE);
    }

    @Test
    public void testInvalidScaleneTriangle3() {
        Triangle triangle = (Triangle) shapeFactory.getShape(3, 5, 3);
        assertNotEquals(triangle.getTriangleType(), TriangleTypes.SCALENE);
    }

    @Test
    public void testInvalidScaleneTriangle4() {
        Triangle triangle = (Triangle) shapeFactory.getShape(5, 3, 3);
        assertNotEquals(triangle.getTriangleType(), TriangleTypes.SCALENE);
    }
}