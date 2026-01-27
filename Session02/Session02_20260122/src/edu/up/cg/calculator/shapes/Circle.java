package edu.up.cg.calculator.shapes;

import edu.up.cg.calculator.tools.IOHandler;

public class Circle extends Shape {
    private float radius;
    
    /**
     * Creates a Circle and immediately prompts for its parameters using the provided IOHandler.
     *
     * @param console the IOHandler used to request input from the user
     */
    public Circle(IOHandler console) {
        super(console);
        obtainParameters();
    }

    /**
     * Returns the radius of the circle.
     *
     * @return the radius as a float
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Sets the radius for this circle. If a non-positive value is provided, the radius is
     * corrected to 1 and the user is informed via the associated IOHandler.
     *
     * @param radius the radius to set; corrected to 1 if <= 0
     */
    public void setRadius(float radius) {
        if (radius <= 0) {
            radius = 1f;
            getInputOutput().showInfo("The radius was negative, it was changed to 1");
        }
        this.radius = radius;
    }

    /**
     * Request the radius value from the user through the IOHandler.
     */
    @Override
    protected void obtainParameters() {
        setRadius(getInputOutput().getFloat("Please provide the radius", "Please put only a float value"));
    }

    /**
     * Compute and return the area of the circle using πr².
     *
     * @return the area as a float
     */
    @Override
    public float getArea() {
        return (float) Math.PI * getRadius() * getRadius();
    }

    /**
     * Compute and return the circumference (perimeter) of the circle using 2πr.
     *
     * @return the circumference as a float
     */
    @Override
    public float getPerimeter() {
        return 2f * (float) Math.PI * getRadius();
    }
}
