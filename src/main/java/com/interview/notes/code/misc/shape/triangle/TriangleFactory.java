package com.interview.notes.code.misc.shape.triangle;

import com.interview.notes.code.misc.shape.Shape;
import com.interview.notes.code.misc.shape.ShapeFactory;
import com.interview.notes.code.misc.shape.ShapeValidator;

public class TriangleFactory extends ShapeFactory {

	@Override
	public Shape getShape(double... args) {

		throwIf(args == null || args.length != 3, "Triangle needs 3 sides. So, please pass 3 parameters");

		throwIf(ShapeValidator.isAnySideZero(args[0], args[1], args[2]),
				"Length of sides cannot be equal to or less than zero");

		throwIf(!ShapeValidator.isTriangle(args), "Sum of the 2 sides should be greater than 3rd side");

		return getTriangle(args);
	}

	/**
	 * This method gives back appropriate triangle with given 3 sides.
	 * 
	 * @param sides  array of sides
	 *
	 * @return Triangle instance.
	 */
	private Triangle getTriangle(double[] sides) {

		if (Double.compare(sides[0], sides[1]) == 0 && Double.compare(sides[1], sides[2]) == 0)
			return new EquilateralTriangle(sides);
		else if (Double.compare(sides[0], sides[1]) == 0 || Double.compare(sides[1], sides[2]) == 0
				|| Double.compare(sides[0], sides[2]) == 0)
			return new IsoscelesTriangle(sides);
		else
			return new ScaleneTriangle(sides);

	}

	private static void throwIf(boolean condition, String message) {
		if (condition) {
			throw new IllegalArgumentException(message);
		}
	}

}