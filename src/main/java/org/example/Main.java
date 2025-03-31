package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        printThreeWords();
        System.out.println(checkSumSign(10, -5));
        System.out.println(printColor(50));
        System.out.println(compareNumbers(15, 10));
        System.out.println(checkSumInRange(5, 15));
        System.out.println(checkPosAndNegNum(5));
        System.out.println(isNegative(-3));
        stringNum("Hello, Aston!", 3);
        System.out.println(leapYear(2024));
        System.out.println(Arrays.toString(invertArray(new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0})));
        System.out.println(Arrays.toString(fillArray(100)));
        System.out.println(Arrays.toString(multiplyArrayElements(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1})));
        diagonalArray(3);
        System.out.println(Arrays.toString(createArray(5, 777)));
    }

    // 1. Вывод трех слов
    static void printThreeWords() {
        System.out.println("Apple\nBanana\nOrange");
    }

    // 2. Проверка суммы
    static String checkSumSign(int a, int b) {
        return (a + b) >= 0 ? "Сумма положительная" : "Сумма отрицательная";
    }

    // 3. Вывод цвета
    static String printColor(int value) {
        if (value <= 0) {
            return "Красный";
        } else if (value <= 100) {
            return "Желтый";
        } else {
            return "Зеленый";
        }
    }

    // 4. Сравнение чисел
    static String compareNumbers(int a, int b) {
        return a >= b ? "a >= b" : "a < b";
    }

    // 5. Проверка суммы в диапазоне
    static boolean checkSumInRange(int a, int b) {
        return (a + b) >= 10 && (a + b) <= 20;
    }

    // 6. Проверка знака числа
    static String checkPosAndNegNum(int num) {
        return num >= 0 ? "Положительное" : "Отрицательное";
    }

    // 7. Проверка на отрицательное число
    static boolean isNegative(int num) {
        return num < 0;
    }

    // 8. Повтор строки
    static void stringNum(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    // 9. Високосный год
    static boolean leapYear(int year) {
        return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }

    // 10. Инверсия массива
    static int[] invertArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : 0;
        }
        return arr;
    }

    // 11. Заполнение массива
    static int[] fillArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    // 12. Умножение элементов
    static int[] multiplyArrayElements(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        return arr;
    }

    // 13. Диагональная матрица
    static void diagonalArray(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1; // Заполнение главной диагонали
        }
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // 14. Создание массива
    static int[] createArray(int len, int initialValue) {
        int[] arr = new int[len];
        Arrays.fill(arr, initialValue);
        return arr;
    }
}