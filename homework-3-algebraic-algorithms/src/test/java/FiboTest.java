import impl.Fibo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;

public class FiboTest {

    private final Fibo fibo = new Fibo();

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\4.Fibo")
    void shouldReturnWithRecursionAllTrue(Integer input, Long expected) {
        System.out.printf("input1=%s, expected=%s", input, expected);
        assertEquals(expected, TestUtil.timed(() -> fibo.fiboWithRecursion(input)));
    }

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\4.Fibo")
    void shouldReturnWithIterAllTrue(Integer input, Long expected) {
        System.out.printf("input1=%s, expected=%s", input, expected);
        assertEquals(expected, TestUtil.timed(() -> fibo.fiboIteration(input)));
    }
}
