package Lesson_14_junit_5;

public class FactorialCalculator {
    public static long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число не может быть отрицательным");
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
