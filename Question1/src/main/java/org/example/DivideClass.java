package org.example;

public class DivideClass {

    /**
     * Divides two integers.
     *
     * @param a The numerator.
     * @param b The denominator.
     * @return The result of dividing a by b.
     */
    public double divide(int a, int b) {
        if (b != 0) {
            return (double) a / b;
        } else {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
    }

    /**
     * Prints a message to the console.
     */
    public void printMessage() {
        System.out.println("This is a message from DivideClass.");
    }
}

