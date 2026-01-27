package edu.up.cg.calculator.shapes;

import edu.up.cg.calculator.tools.IOHandler;

public abstract class Shape {
    private IOHandler inputOutput;

    /**
     * Returns the IOHandler used to communicate with the user.
     *
     * @return the IOHandler instance associated with this shape
     */
    public IOHandler getInputOutput() {
        return inputOutput;
    }

    /**
     * Sets the IOHandler used to communicate with the user.
     *
     * @param inputOutput the IOHandler to associate with this shape
     */
    public void setInputOutput(IOHandler inputOutput) {
        this.inputOutput = inputOutput;
    }

    /**
     * Constructs a Shape and associates an IOHandler for user interaction.
     *
     * @param inputOutput the IOHandler to use for obtaining parameters and showing info
     */
    public Shape(IOHandler inputOutput) {
        setInputOutput(inputOutput);
    }

    /**
     * Prompt and retrieve the parameters required to define the concrete shape.
     * Implementations should use getInputOutput() to interact with the user.
     */
    protected abstract void obtainParameters();

    /**
     * Compute and return the area of the shape.
     *
     * @return the area as a float
     */
    public abstract float getArea();

    /**
     * Compute and return the perimeter (or circumference) of the shape.
     *
     * @return the perimeter as a float
     */
    public abstract float getPerimeter();
}
