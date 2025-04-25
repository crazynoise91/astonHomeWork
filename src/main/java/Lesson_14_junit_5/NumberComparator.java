package Lesson_14_junit_5;

public class NumberComparator {
    public static String compare(int a, int b) {
        if (a == b) return "Числа равны";
        return a > b ? "Первое число больше" : "Второе число больше";
    }
}
