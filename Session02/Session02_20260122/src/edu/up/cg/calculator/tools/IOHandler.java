package edu.up.cg.calculator.tools;

/**
 * Abstract handler for input/output operations used by shapes and the application.
 * Concrete implementations must provide ways to show information and read float/int values.
 */
public abstract class IOHandler {
    /**
     * Show informational text to the user (console, GUI, etc.).
     *
     * @param info the message to show
     */
    public abstract void showInfo(String info);

    /**
     * Prompt the user and return a float value. Implementations should handle validation
     * and re-prompting when the input is not a valid float.
     *
     * @param info the prompt message shown to the user
     * @param notValidInput the message shown when the provided input is invalid
     * @return the float value entered by the user
     */
    public abstract float getFloat(String info, String notValidInput);

    /**
     * Prompt the user and return an integer value. Implementations should handle validation
     * and re-prompting when the input is not a valid integer.
     *
     * @param info the prompt message shown to the user
     * @param notValidInput the message shown when the provided input is invalid
     * @return the integer value entered by the user
     */
    public abstract int getInt(String info, String notValidInput);
}
