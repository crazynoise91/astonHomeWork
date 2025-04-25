import Lesson_14_testng.ArithmeticOperations;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArithmeticOperationsTestNG {

    @DataProvider(name = "operationData")
    public Object[][] provideOperationData() {
        return new Object[][]{
                {7, 3, "+", 10, "7 + 3 должно быть 10"},
                {10, 3, "-", 7, "10 - 3 должно быть 7"},
                {7, 3, "*", 21, "7 * 3 должно быть 21"},
                {21, 3, "/", 7, "21 / 3 должно быть 7"},
                {-7, -3, "+", -10, "-7 + -3 должно быть 10"},
                {-10, 3, "*", -30, "-10 * 3 должно быть -30"}
        };
    }

    @DataProvider(name = "divisionByZeroData")
    public Object[][] provideDivisionByZeroData() {
        return new Object[][]{
                {5, 0, "Деление на ноль"}
        };
    }

    @DataProvider(name = "invalidOperationData")
    public Object[][] provideInvalidOperationData() {
        return new Object[][]{
                {2, 3, "%", "Недопустимая операция"}
        };
    }

    @Test(dataProvider = "operationData",
            testName = "Проверка операции: {4}")
    public void testOperations(int a, int b, String operation, int expected, String description) {
        assertEquals(ArithmeticOperations.calculate(a, b, operation), expected);
    }

    @Test(dataProvider = "divisionByZeroData",
            expectedExceptions = ArithmeticException.class,
            testName = "Проверка деления на ноль: {2}")
    public void testDivisionByZero(int a, int b, String expectedMessage) {
        ArithmeticOperations.calculate(a, b, "/");
    }

    @Test(dataProvider = "invalidOperationData",
            expectedExceptions = IllegalArgumentException.class,
            testName = "Проверка недопустимой операции: {3}")
    public void testInvalidOperations(int a, int b, String operation, String expectedMessage) {
        ArithmeticOperations.calculate(a, b, operation);
    }
}