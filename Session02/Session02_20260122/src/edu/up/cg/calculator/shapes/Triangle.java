package edu.up.cg.calculator.shapes;

import edu.up.cg.calculator.tools.IOHandler;

public class Triangle extends Shape {
    private float sideA;
    private float sideBase;
    private float sideC;
    private float height;

    /**
     * Creates a Triangle and obtains its dimensions from the provided IOHandler.
     *
     * @param inputOutput the IOHandler used to request triangle parameters
     */
    public Triangle(IOHandler inputOutput){
        super(inputOutput);
        obtainParameters();
    }

    /**
     * Returns side A of the triangle.
     *
     * @return side A as a float
     */
    public float getSideA() {
        return sideA;
    }

    /**
     * Sets side A of the triangle.
     *
     * @param sideA the length to set for side A
     */
    public void setSideA(float sideA) {
        this.sideA = sideA;
    }

    /**
     * Returns the base side of the triangle.
     *
     * @return the base side as a float
     */
    public float getSideBase() {
        return sideBase;
    }

    /**
     * Sets the base side of the triangle.
     *
     * @param sideBase the length to set for the base side
     */
    public void setSideBase(float sideBase) {
        this.sideBase = sideBase;
    }

    /**
     * Returns side C of the triangle.
     *
     * @return side C as a float
     */
    public float getSideC() {
        return sideC;
    }

    /**
     * Sets side C of the triangle.
     *
     * @param sideC the length to set for side C
     */
    public void setSideC(float sideC) {
        this.sideC = sideC;
    }

    /**
     * Returns the height of the triangle used to compute the area.
     *
     * @return the height as a float
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets the height of the triangle.
     *
     * @param height the height to set for the triangle
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Prompt the user for the triangle's sides and height using the IOHandler.
     */
    @Override
    public void obtainParameters() {
        String baseProvide = "Please provide the side";
        setSideA(getInputOutput().getFloat(baseProvide + " A for the triangle:", "Please put only a float value"));
        setSideBase(getInputOutput().getFloat(baseProvide + " which is the base for the triangle:", "Please put only a float value"));
        setSideC(getInputOutput().getFloat(baseProvide + " C for the triangle:", "Please put only a float value"));
        setHeight(getInputOutput().getFloat("Please provide the height for the triangle:", "Please put only a float value"));
    }

    /**
     * Compute and return the perimeter of the triangle (sum of its three sides).
     *
     * @return the perimeter as a float
     */
    @Override
    public float getPerimeter() {
        return getSideA() + getSideBase() + getSideC();
    }

    /**
     * Compute and return the area of the triangle using (base * height) / 2.
     *
     * @return the area as a float
     */
    @Override
    public float getArea() {
        return (getSideBase() * getHeight()) / 2.0f;
    }
}
