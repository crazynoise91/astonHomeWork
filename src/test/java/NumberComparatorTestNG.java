import Lesson_14_testng.NumberComparator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NumberComparatorTestNG {

    @DataProvider(name = "comparisonData")
    public Object[][] provideComparisonData() {
        return new Object[][]{
                {7, 7, "Числа равны", "7 и 7 должны быть равны"},
                {77, 7, "Первое число больше", "77 должно быть больше 7"},
                {7, 77, "Второе число больше", "7 должно быть меньше 77"},
                {-7, -77, "Первое число больше", "-7 должно быть больше -77"},
                {-77, -7, "Второе число больше", "-77 должно быть меньше -7"}
        };
    }

    @Test(dataProvider = "comparisonData",
            testName = "Проверка сравнения: {3}")
    public void testNumberComparison(int a, int b, String expectedResult, String description) {
        assertEquals(NumberComparator.compare(a, b), expectedResult);
    }

}
