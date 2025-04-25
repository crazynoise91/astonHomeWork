import Lesson_14_testng.FactorialCalculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialCalculatorTestNG {

    @DataProvider(name = "validFactorialData")
    public Object[][] provideValidFactorialData() {
        return new Object[][]{
                {0, 1L, "Факториал 0 должен быть 1"},
                {1, 1L, "Факториал 1 должен быть 1"},
                {5, 120L, "Факториал 5 должен быть 120"}
        };
    }

    @DataProvider(name = "invalidFactorialData")
    public Object[][] provideInvalidFactorialData() {
        return new Object[][]{
                {-5, "Число не может быть отрицательным"}
        };
    }

    @Test(dataProvider = "validFactorialData",
            testName = "Проверка корректного вычисления факториала: {2}")
    public void testValidFactorials(int input, long expected, String description) {
        assertEquals(FactorialCalculator.calculateFactorial(input), expected);
    }

    @Test(dataProvider = "invalidFactorialData",
            expectedExceptions = IllegalArgumentException.class,
            testName = "Проверка невалидного ввода: {1}")
    public void testInvalidFactorials(int input, String expectedMessage) {
        FactorialCalculator.calculateFactorial(input);
    }
}
