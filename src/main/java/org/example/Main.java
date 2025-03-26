package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        runAllTests();
    }

    private static void runAllTests() {
        testWordOperations();
        testNumberOperations();
        testArrayOperations();
        testMatrixOperations();
    }

    /* ========== Тестовые методы ========== */

    private static void testWordOperations() {
        printThreeWords();
        printStringMultipleTimes("Hello, Java!", 2);
    }

    private static void testNumberOperations() {
        checkSumSign(5, -3);
        printColor(75);
        compareNumbers(15, 10);

        System.out.println("Сумма в диапазоне [10,20]: " + isSumInRange(7, 8));
        System.out.println("Число отрицательное: " + isNegative(-5));
        System.out.println("Високосный год: " + isLeapYear(2024));
    }

    private static void testArrayOperations() {
        // Тестирование операций с массивами
        int[] binaryArray = {1, 0, 1, 1, 0};
        System.out.println("Инвертированный массив: " + Arrays.toString(invertArray(binaryArray)));

        int[] numbers = createRangeArray(1, 100);
        System.out.println("Первые 10 элементов: " + Arrays.toString(Arrays.copyOf(numbers, 10)) + "...");

        int[] arrayToMultiply = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("После умножения: " + Arrays.toString(multiplyIfLessThanSix(arrayToMultiply)));

        System.out.println("Заполненный массив: " + Arrays.toString(createFilledArray(5, 10)));
    }

    private static void testMatrixOperations() {
        int[][] diagonalMatrix = createDiagonalMatrix(5);
        System.out.println("Матрица с диагональными единицами:");
        printMatrix(diagonalMatrix);
    }

    /* ========== Основные методы ========== */

    // 1. Вывод трех слов
    public static void printThreeWords() {
        Arrays.asList("Orange", "Banana", "Apple").forEach(System.out::println);
    }

    // 2. Проверка суммы чисел
    public static void checkSumSign(int a, int b) {
        System.out.println((a + b) >= 0 ? "Сумма положительная" : "Сумма отрицательная");
    }

    // 3. Определение цвета по значению
    public static void printColor(int value) {
        String color = value <= 0 ? "Красный" :
                value <= 100 ? "Желтый" : "Зеленый";
        System.out.println(color);
    }

    // 4. Сравнение чисел
    public static void compareNumbers(int a, int b) {
        System.out.println(a >= b ? "a >= b" : "a < b");
    }

    // 5. Проверка суммы в диапазоне
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. Проверка отрицательного числа
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 7. Повтор строки
    public static void printStringMultipleTimes(String text, int count) {
        if (count < 0) throw new IllegalArgumentException("Количество повторений не может быть отрицательным");
        IntStream.range(0, count).forEach(i -> System.out.println(text));
    }

    // 8. Инвертирование массива
    public static int[] invertArray(int[] array) {
        if (array == null) throw new IllegalArgumentException("Массив не может быть null");
        return Arrays.stream(array)
                .map(x -> {
                    if (x != 0 && x != 1) throw new IllegalArgumentException("Массив должен содержать только 0 и 1");
                    return x == 0 ? 1 : 0;
                })
                .toArray();
    }

    // 9. Проверка високосного года
    public static boolean isLeapYear(int year) {
        if (year <= 0) throw new IllegalArgumentException("Год должен быть положительным");
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }

    // 10. Создание массива с диапазоном значений
    public static int[] createRangeArray(int start, int end) {
        return IntStream.rangeClosed(start, end).toArray();
    }

    // 11. Умножение элементов меньше 6
    public static int[] multiplyIfLessThanSix(int[] array) {
        if (array == null) throw new IllegalArgumentException("Массив не может быть null");
        return Arrays.stream(array).map(x -> x < 6 ? x * 2 : x).toArray();
    }

    // 12. Создание диагональной матрицы
    public static int[][] createDiagonalMatrix(int size) {
        if (size <= 0) throw new IllegalArgumentException("Размер должен быть > 0");
        int[][] matrix = new int[size][size];
        IntStream.range(0, size).forEach(i -> matrix[i][i] = 1);
        return matrix;
    }

    // 13. Создание заполненного массива
    public static int[] createFilledArray(int len, int initialValue) {
        if (len < 0) throw new IllegalArgumentException("Длина массива не может быть отрицательной");
        int[] array = new int[len];
        Arrays.fill(array, initialValue);
        return array;
    }

    // 14. Вспомогательный метод для печати матрицы
    private static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix).map(Arrays::toString).forEach(System.out::println);
    }
}