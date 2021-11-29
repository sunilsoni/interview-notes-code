package com.interview.notes.code.misc.shape;

public abstract class ShapeFactory {

	/**
	 * This is abstract method which gives back Shape, given arguments.
	 * @param args variable number of params (3 argumanets are needed for triangle, 2 arguments are needed for rectangle...etc)
	 *
	 */
	public abstract Shape getShape(double ... args);
}