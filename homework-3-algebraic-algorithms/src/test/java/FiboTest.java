import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;

public class FiboTest {

    private final Fibo fibo = new Fibo();

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\4.Fibo")
    void shouldReturnAllTrue(Integer input, Long expected) {
        assertEquals(expected, fibo.fibo2(input));
    }
}
