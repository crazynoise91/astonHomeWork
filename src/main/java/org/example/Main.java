package org.example;

public class Main {

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColorBasedOnValue();
        compareTwoNumbers();

        System.out.println(isSumInRange(10, -3));
        System.out.println(isSumInRange(16, 4));

        checkIfPositiveOrNegative(-1);

        System.out.println(isNegative(7));
        System.out.println(isNegative(-1));

        printStringMultipleTimes("Hello, Aston!", 3);

        System.out.println(isLeapYear(2024));

        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println();
        invertArrayValues(arr);

        System.out.println();
        fillArrayWithSequentialNumbers(100);

        int[] arr2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println();
        doubleValuesLessThanSix(arr2);

        System.out.println();
        printDiagonalMatrix();

        System.out.println();
        initializeArrayWithValue(3, 4);
    }

    // 1. Метод для печати трех слов
    public static void printThreeWords() {
        System.out.println("Orange,\nBanana,\nApple.");
    }

    // 2. Метод для проверки знака суммы двух чисел
    public static void checkSumSign() {
        int x = -9;
        int y = 7;
        int sum = x + y;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Метод для печати цвета в зависимости от значения
    public static void printColorBasedOnValue() {
        int value = -50;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Метод для сравнения двух чисел
    public static void compareTwoNumbers() {
        int a = 10;
        int b = 20;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Метод для проверки, находится ли сумма в диапазоне от 10 до 20
    static boolean isSumInRange(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    // 6. Метод для проверки, положительное ли число
    public static void checkIfPositiveOrNegative(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    // 7. Метод для проверки, является ли число отрицательным
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8. Метод для печати строки несколько раз
    public static void printStringMultipleTimes(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    // 9. Метод для проверки, является ли год високосным
    public static boolean isLeapYear(int year) {
        return year % 100 != 0 && year % 4 == 0 || year % 400 == 0;
    }

    // 10. Метод для инвертирования значений массива
    public static void invertArrayValues(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] > 0) ? 0 : 1;
            System.out.print(arr[i] + " ");
        }
    }

    // 11. Метод для заполнения массива последовательными числами
    public static void fillArrayWithSequentialNumbers(int size) {
        int[] fillArr = new int[size];
        for (int i = 0; i < fillArr.length; i++) {
            fillArr[i] = i + 1;
            System.out.print(fillArr[i] + " ");
        }
    }

    // 12. Метод для удвоения значений в массиве, если они меньше 6
    public static void doubleValuesLessThanSix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
            System.out.print(arr[i] + " ");
        }
    }

    // 13. Метод для печати диагональной матрицы
    public static void printDiagonalMatrix() {
        int[][] arr = new int[3][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0, x = arr[i].length - 1; j < arr[i].length; j++, x--) {
                if (i == j || i == x) arr[i][j] = 1;
                else arr[i][j] = 0;
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    // 14. Метод для инициализации массива с заданным значением
    public static void initializeArrayWithValue(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
            System.out.print("[" + i + "]" + arr[i] + " ");
        }
    }
}