package Lesson_12;

public class Main {
    public static void main(String[] args) {

        // Корректный массив 4x4 (все элементы - числа)
        String[][] correctArray1 = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // Массивы с неправильным размером
        String[][] wrongSizeArray1 = new String[3][4];  // 3x4 (не хватает одной строки)
        String[][] wrongSizeArray2 = new String[4][5];  // 4x5 (лишний столбец)

        // Массив с некорректными данными (содержит нечисловое значение)
        String[][] wrongDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "!", "11", "12"}, // Символ
                {"13", "14", "15", "16"}
        };

        // Массив для демонстрации ArrayIndexOutOfBoundsException
        String[][] outOfBoundsArray = new String[4][];
        outOfBoundsArray[0] = new String[4];  // Строка 0: 4 элемента
        outOfBoundsArray[1] = new String[4];  // Строка 1: 4 элемента
        outOfBoundsArray[2] = new String[4];  // Строка 2: 4 элемента
        outOfBoundsArray[3] = new String[3];  // Строка 3: 3 элемента (специально)

        // Тестирование
        System.out.println("Тестирование метода:");

        testArray(correctArray1, "Корректный массив 4x4");
        testArray(wrongSizeArray1, "Массив 3x4");
        testArray(wrongSizeArray2, "Массив 4x5");
        testArray(wrongDataArray, "Массив с некорректными данными");

        // Тест ArrayIndexOutOfBoundsException
        System.out.println("\nТест ArrayIndexOutOfBoundsException:");
        try {
            // Обращаемся к несуществующему элементу в outOfBoundsArray
            String value = outOfBoundsArray[3][3]; // Вызовет исключение!
            System.out.println("Значение: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано ArrayIndexOutOfBoundsException: " + e.getMessage());
        }


    }

    private static void testArray(String[][] array, String description) {
        System.out.println("\nТест: " + description);
        try {
            int sum = ArrayUtils.sumStringArray(array);
            System.out.println("Сумма элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }
    }
}