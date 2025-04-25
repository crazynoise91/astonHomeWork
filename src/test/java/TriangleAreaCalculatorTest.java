import Lesson_14_junit_5.TriangleAreaCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertEquals;

public class TriangleAreaCalculatorTest {

    @DisplayName("Вычисление результата")
    @Test
    void testCalculate() {
        assertEquals(10.0, TriangleAreaCalculator.calculate(5, 4));
    }

    @DisplayName("Недопустимые числа")
    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> TriangleAreaCalculator.calculate(-5, 4));
    }
}
