import impl.Prime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;

public class PrimeTest {

    private final Prime prime = new Prime();

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\5.Primes")
    void shouldReturnAllTrueEratosthenes(Integer input, Long expected) {
        System.out.printf("input1=%s, expected=%s", input, expected);
        assertEquals(expected, Long.valueOf(TestUtil.timed(() -> prime.Eratosthenes(input))));
    }

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\5.Primes")
    void shouldReturnAllTrueIterationNotOptimized(Integer input, Long expected) {
        System.out.printf("input1=%s, expected=%s", input, expected);
        assertEquals(expected, Long.valueOf(TestUtil.timed(() -> prime.countPrimesN2(input))));
    }

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\5.Primes")
    void shouldReturnAllTrueIterationOptimized(Integer input, Long expected) {
        System.out.printf("input1=%s, expected=%s", input, expected);
        assertEquals(expected, Long.valueOf(TestUtil.timed(() -> prime.countPrimes(input))));
    }
}
