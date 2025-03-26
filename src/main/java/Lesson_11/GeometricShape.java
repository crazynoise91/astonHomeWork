package Lesson_11;

public interface GeometricShape {
    double calculateArea();

    String getFillColor();

    String getBorderColor();

    default double calculatePerimeter() {
        return 0;
    }

    default void printInfo() {
        System.out.println("Площадь: " + calculateArea() + " см");
        System.out.println("Периметр: " + calculatePerimeter() + " см");
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границ: " + getBorderColor());

    }
}
