package example;

public class Calculator {

    private void validateInput(double a, double b) throws InvalidInputException {
        if (Double.isNaN(a) || Double.isNaN(b)) {
            throw new InvalidInputException("Вхідні дані повинні бути числовими!");
        }
    }

    public double add(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        return a + b;
    }

    public double subtract(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        return a - b;
    }

    public double multiply(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль неможливе!");
        }
        return a / b;
    }

    public double sqrt(double a) throws InvalidInputException {
        if (a < 0) {
            throw new InvalidInputException("Неможливо обчислити квадратний корінь з від’ємного числа!");
        }
        return Math.sqrt(a);
    }


}

