package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        checkSumInRange();
        checkPosAndNegNum();
        checkNum();
        stringNum();
        leapYear();
        InvertArray();
        FillArray();
        ArrayMultiplier();
        DiagonalArray();
        Arrays();

        scanner.close();
    }

    // 1. Вывод трех слов
    static void printThreeWords() {
        System.out.println("Apple\nBanana\nOrange");
    }

    // 2. Проверка суммы
    static void checkSumSign() {
        int a = 10, b = -5;
        System.out.println((a + b) >= 0 ? "Сумма положительная" : "Сумма отрицательная");
    }

    // 3. Вывод цвета
    static void printColor() {
        int value = 50;
        System.out.println(value <= 0 ? "Красный" :
                value <= 100 ? "Желтый" : "Зеленый");
    }

    // 4. Сравнение чисел
    static void compareNumbers() {
        int a = 15, b = 10;
        System.out.println(a >= b ? "a >= b" : "a < b");
    }

    // 5. Проверка суммы в диапазоне
    static void checkSumInRange() {
        int a = 7, b = 8;
        System.out.println((a + b) >= 10 && (a + b) <= 20);
    }

    // 6. Проверка знака числа
    static void checkPosAndNegNum() {
        int num = -5;
        System.out.println(num >= 0 ? "Положительное" : "Отрицательное");
    }

    // 7. Проверка положительности
    static void checkNum() {
        int num = 0;
        System.out.println(num >= 0);
    }

    // 8. Повтор строки
    static void stringNum() {
        String str = "Hello, Aston! ";
        int count = 3;
        System.out.println(str.repeat(count));
    }

    // 9. Високосный год
    static void leapYear() {
        int year = 2024;
        System.out.println((year % 400 == 0) ||
                (year % 100 != 0 && year % 4 == 0));
    }

    // 10. Инверсия массива
    static void InvertArray() {
        int[] arr = {0, 1, 1, 0, 1};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : 0;
        }
        System.out.println(Arrays.toString(arr));
    }

    // 11. Заполнение массива
    static void FillArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    // 12. Умножение элементов
    static void ArrayMultiplier() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] *= 2;
        }
        System.out.println(Arrays.toString(arr));
    }

    // 13. Диагональная матрица
    static void DiagonalArray() {
        int size = 3;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size-1-i] = 1;
        }
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // 14. Создание массива
    static void Arrays() {
        int[] arr = new int[5];
        Arrays.fill(arr, 777);
        System.out.println(Arrays.toString(arr));
    }
}