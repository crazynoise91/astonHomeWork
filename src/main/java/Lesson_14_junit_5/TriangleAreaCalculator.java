package Lesson_14_junit_5;

public class TriangleAreaCalculator {
    public static double calculate(double base, double height) {
        if (base <= 0 || height <= 0)
            throw new IllegalArgumentException("Длины сторон должны быть положительными числами");
        return (base * height) / 2;
    }
}
