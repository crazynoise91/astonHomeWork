package Lesson_14_testng;

public class ArithmeticOperations {
    public static int calculate(int a, int b, String operation) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Деление на ноль");
                return a / b;
            default:
                throw new IllegalArgumentException("Недопустимая операция");
        }
    }
}