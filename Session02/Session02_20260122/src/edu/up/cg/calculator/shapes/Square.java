package edu.up.cg.calculator.shapes;

import edu.up.cg.calculator.tools.IOHandler;

public class Square extends Shape {
    private float side;
    
    /**
     * Creates a Square and prompts the user for its side length using the provided IOHandler.
     *
     * @param console the IOHandler used to obtain user input
     */
    public Square(IOHandler console) {
        super(console);
        obtainParameters();
    }

    /**
     * Returns the length of the square's side.
     *
     * @return the side length as a float
     */
    public float getSide() {
        return side;
    }

    /**
     * Sets the side length of the square.
     *
     * @param side the length of the side to set
     */
    public void setSide(float side) {
        this.side = side;
    }

    /**
     * Prompt the user to provide the side length for the square.
     */
    @Override
    public void obtainParameters() {
        setSide(getInputOutput().getFloat("Please provide the side for the square:", "Please put only a float value"));
    }

    /**
     * Compute and return the perimeter of the square (4 * side).
     *
     * @return the perimeter as a float
     */
    @Override
    public float getPerimeter() {
        return getSide() * 4;
    }

    /**
     * Compute and return the area of the square (side * side).
     *
     * @return the area as a float
     */
    @Override
    public float getArea() {
        return getSide() * getSide();
    }
}
