import Lesson_14_junit_5.NumberComparator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberComparatorTest {
    @ParameterizedTest
    @MethodSource("provideNumbers")
    void testCompare(int a, int b, String expected) {
        assertEquals(expected, NumberComparator.compare(a, b));
    }

    private static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of(7, 7, "Числа равны"),
                Arguments.of(77, 7, "Первое число больше"),
                Arguments.of(7, 77, "Второе число больше"),
                Arguments.of(-7, -77, "Первое число больше"),
                Arguments.of(-77, -7, "Второе число больше")
        );
    }
}
