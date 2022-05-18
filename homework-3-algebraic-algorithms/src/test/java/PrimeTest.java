import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;

public class PrimeTest {

    Prime prime = new Prime();

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\5.Primes")
    void shouldReturnAllTrue(Integer input, Long expected) {
        assertEquals(expected, prime.Eratosthenes(input));
    }
}
