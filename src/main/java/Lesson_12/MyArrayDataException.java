package Lesson_12;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(int row, int col) {
        super(String.format("Невозможно преобразовать данные в ячейке [%d][%d]", row, col));
    }
}
