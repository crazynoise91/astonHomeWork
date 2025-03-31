package org.example;

public class Main {

    // 1. Метод, который печатает три слова в столбец
    public void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2. Метод, который проверяет сумму двух чисел и печатает результат
    public void checkSumSign(int a, int b) {
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Метод, который выводит цвет в зависимости от значения
    public void printColor(int value) {
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Метод, который сравнивает два числа и печатает результат
    public void compareNumbers(int a, int b) {
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Метод, который проверяет, лежит ли сумма в пределах от 10 до 20
    public boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. Метод, который определяет, положительное ли число и печатает результат
    public void printPositiveOrNegative(int number) {
        if (number >= 0) {
            System.out.println("Положительное");
        } else {
            System.out.println("Отрицательное");
        }
    }

    // 7. Метод, который проверяет, является ли число отрицательным
    public boolean isNegative(int number) {
        return number < 0;
    }

    // 8. Метод, который печатает строку указанное количество раз
    public void printStringMultipleTimes(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    // 9. Метод, который определяет, является ли год високосным
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 10. Метод, который заменяет 0 на 1 и 1 на 0 в массиве
    public void invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0) ? 1 : 0;
        }
    }

    // 11. Метод, который заполняет массив значениями от 1 до 100
    public int[] fillArray() {
        int[] array = new int[100]; // Создаем массив длиной 100
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // Заполняем массив значениями от 1 до 100
        }
        return array;
    }

    // 12. Метод, который умножает числа меньше 6 на 2
    public void multiplyLessThanSix(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    // 13. Метод, который заполняет диагональные элементы единицами
    public int[][] fillDiagonal(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            array[i][i] = 1;
        }
        return array;
    }

    // 14. Метод, который возвращает массив заданной длины и значения
    public int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }

    public static void main(String[] args) {
        Main hw = new Main();

        // Вызов методов и печать результатов
        hw.printThreeWords();
        hw.checkSumSign(5, 3);
        hw.printColor(50);
        hw.compareNumbers(5, 3);
        System.out.println(hw.isSumInRange(5, 7));
        hw.printPositiveOrNegative(-3);
        System.out.println(hw.isNegative(-1));
        hw.printStringMultipleTimes("Hello", 3);
        System.out.println(hw.isLeapYear(2024));

        int[] array = hw.fillArray();
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] filledArray = hw.createArray(5, 10);
        for (int num : filledArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[][] diagonalArray = hw.fillDiagonal(5);
        for (int i = 0; i < diagonalArray.length; i++) {
            for (int j = 0; j < diagonalArray[i].length; j++) {
                System.out.print(diagonalArray[i][j] + " ");
            }
            System.out.println();
        }
    }
}