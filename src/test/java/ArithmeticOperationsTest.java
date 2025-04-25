import Lesson_14_junit_5.ArithmeticOperations;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArithmeticOperationsTest {

    @ParameterizedTest(name = "Тест {index}: {0} {2} {1} = {3}")
    @CsvSource({

            "7, 3, +, 10",
            "10, 3, -, 7",
            "7, 3, *, 21",
            "21, 3, /, 7",
            "-7, -3, +, -10",
            "-10, 3, *, -30",
    })
    void testValidOperations(int a, int b, String operation, int expected) {
        assertEquals(expected, ArithmeticOperations.calculate(a, b, operation));
    }

    @ParameterizedTest(name = "Деление на ноль: {0} / {1}")
    @CsvSource({
            "5, 0",
    })
    void testDivisionByZero(int a, int b) {
        assertThrows(ArithmeticException.class, () ->
                ArithmeticOperations.calculate(a, b, "/")
        );
    }

    @ParameterizedTest(name = "Недопустимая операция: {0} {2} {1}")
    @CsvSource({
            "2, 3, %",
    })
    void testInvalidOperations(int a, int b, String operation) {
        assertThrows(IllegalArgumentException.class, () ->
                ArithmeticOperations.calculate(a, b, operation)
        );
    }

}