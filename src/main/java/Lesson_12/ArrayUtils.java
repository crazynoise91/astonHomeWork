package Lesson_12;

public class ArrayUtils {
    public static int sumStringArray(String[][] array) throws MyArraySizeException, MyArrayDataException {

        // Проверка размера массива
        if (array.length != 4) {
            throw new MyArraySizeException();
        }

        for (String[] row : array) {
            if (row == null || row.length != 4) {
                throw new MyArraySizeException();
            }
        }

        // Подсчет суммы
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}
