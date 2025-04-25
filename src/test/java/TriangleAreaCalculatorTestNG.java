import Lesson_14_testng.TriangleAreaCalculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleAreaCalculatorTestNG {

    @DataProvider(name = "validTriangleData")
    public Object[][] provideValidTriangleData() {
        return new Object[][]{
                {5.0, 4.0, 10.0, "Площадь треугольника 5x4 должна быть 10"}
        };
    }

    @DataProvider(name = "invalidTriangleData")
    public Object[][] provideInvalidTriangleData() {
        return new Object[][]{
                {-5.0, 4.0, "Длины сторон должны быть положительными числами"}
        };
    }

    @Test(dataProvider = "validTriangleData",
            testName = "Проверка результата: {3}")
    public void testValidTriangleAreas(double base, double height, double expected, String description) {
        assertEquals(TriangleAreaCalculator.calculate(base, height), expected);
    }

    @Test(dataProvider = "invalidTriangleData",
            expectedExceptions = IllegalArgumentException.class,
            testName = "Недопустимые числа: {2}")
    public void testInvalidTriangleAreas(double base, double height, String expectedMessage) {
        TriangleAreaCalculator.calculate(base, height);
    }
}
