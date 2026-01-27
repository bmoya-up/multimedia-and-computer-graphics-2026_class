package edu.up.cg.calculator;

import edu.up.cg.calculator.shapes.Circle;
import edu.up.cg.calculator.shapes.Shape;
import edu.up.cg.calculator.shapes.Square;
import edu.up.cg.calculator.shapes.Triangle;
import edu.up.cg.calculator.tools.IOConsole;
import edu.up.cg.calculator.tools.IOHandler;

public class Main {

    /**
     * The entry point of the application.
     * This method initializes the console-based IO handler, prompts the user to select a shape,
     * creates the selected shape, and displays its perimeter and area.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        IOHandler console = new IOConsole();
        console.showInfo("Welcome to the Shape Calculator!");
        int option = console.getInt("1) Square\n2) Circle\n3) Triangle", "Please select a valid option.");
        Shape shape = null;
        
        switch (option) {
            case 1:
                shape = new Square(console);
                break;
            case 2:
                shape = new Circle(console);
                break;
            case 3:
                shape = new Triangle(console);
                break;
        }

        if (shape != null) {
            console.showInfo("The perimeter is " + shape.getPerimeter());
            console.showInfo("The area is " + shape.getArea());
        }
    }
}
