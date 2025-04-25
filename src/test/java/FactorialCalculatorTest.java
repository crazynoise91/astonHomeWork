import Lesson_14_junit_5.FactorialCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;


public class FactorialCalculatorTest {

    @DisplayName("Тест с факториалом числа 0")
    @Test
    void testFactorialOfZero() {
        assertEquals(1, FactorialCalculator.calculateFactorial(0));

    }

    @DisplayName("Тест с факториалом числа 1")
    @Test
    void testFactorialOfOne() {
        assertEquals(1, FactorialCalculator.calculateFactorial(1));

    }


    @DisplayName("Тест с факториалом числа 5")
    @Test
    void testFactorialOfFive() {
        assertEquals(120, FactorialCalculator.calculateFactorial(5));

    }

    @DisplayName("Тест с факториалом числа -5")
    @Test
    void testFactorialOfNegativeFive() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> FactorialCalculator.calculateFactorial(-5));

        assertEquals("Число не может быть отрицательным", exception.getMessage());
    }
}
