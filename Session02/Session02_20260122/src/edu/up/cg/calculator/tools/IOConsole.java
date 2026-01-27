package edu.up.cg.calculator.tools;

import java.io.Console;
import java.util.Scanner;

public class IOConsole extends IOHandler {
    private Scanner scanner;

    /**
     * Creates an IOConsole using System.in and a Scanner for reading input.
     */
    public IOConsole() {
        setScanner(new Scanner(System.in));
    }

    /**
     * Returns the Scanner used to read user input.
     *
     * @return the Scanner instance
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Sets the Scanner used to read user input. Useful for testing or redirecting input.
     *
     * @param scanner the Scanner to use for reading input
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Print a message to the standard output.
     *
     * @param info the message to print
     */
    @Override
    public void showInfo(String info) {
        System.out.println(info);
    }

    /**
     * Prompt the user to enter a float value. Repeats until a valid float is entered.
     *
     * @param info the prompt message
     * @param notValidInput message displayed when the entered value is not a valid float
     * @return the parsed float value from the user's input
     */
    @Override
    public float getFloat(String info, String notValidInput) {
        boolean validInput = false;
        float data = 0;

        do {
            showInfo(info);
            try {
                String userInput = getScanner().nextLine();
                data = Float.parseFloat(userInput);
                validInput = true;
            } catch (Exception e) {
                showInfo(notValidInput);
            }
            
        } while (!validInput);
        return data;
    }

    /**
     * Prompt the user to enter an integer value. Repeats until a valid integer is entered.
     *
     * @param info the prompt message
     * @param notValidInput message displayed when the entered value is not a valid integer
     * @return the parsed integer value from the user's input
     */
    @Override
    public int getInt(String info, String notValidInput) {
        boolean validInput = false;
        int data = 0;

        do {
            showInfo(info);
            try {
                String userInput = getScanner().nextLine();
                data = Integer.parseInt(userInput);
                validInput = true;
            } catch (Exception e) {
                showInfo(notValidInput);
            }

        } while (!validInput);
        return data;
    }
}
