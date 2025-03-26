package Lesson_12;

public class MyArraySizeException extends Exception {
    public MyArraySizeException() {
        super("Массив должен быть размером 4x4");
    }
}
