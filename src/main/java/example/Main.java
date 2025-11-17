package example;

import org.example.Calculator;
import org.example.ErrorLogger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.print("Введіть перше число: ");
            double a = scanner.nextDouble();

            System.out.print("Введіть друге число (для sqrt можна ввести 0): ");
            double b = scanner.nextDouble();

            System.out.print("Оберіть операцію (+, -, *, /, sqrt): ");
            String operation = scanner.next();

            double result;

            try {
                switch (operation) {
                    case "+" -> result = calculator.add(a, b);
                    case "-" -> result = calculator.subtract(a, b);
                    case "*" -> result = calculator.multiply(a, b);
                    case "/" -> result = calculator.divide(a, b);
                    default -> throw new InvalidInputException("Невідома операція.");
                }

                System.out.println("Результат: " + result);


            } catch (ArithmeticException e) {
                System.out.println("Помилка арифметики: " + e.getMessage());
                ErrorLogger.log("ArithmeticException: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Помилка введення: " + e.getMessage());
                ErrorLogger.log("InvalidInputException: " + e.getMessage());
            }

        } catch (InputMismatchException e) {
            System.out.println("Некоректний формат числа!");
            ErrorLogger.log("InputMismatchException: некоректний формат числа.");
        } finally {
            System.out.println("Обробка завершена.");
            scanner.close();
        }

    }
}
