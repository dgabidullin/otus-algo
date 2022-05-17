import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;

public class PowerTest {

    private final Power power = new Power();

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\3.Power", twoInputArguments = true)
    void shouldReturnAllTrue(Double input1, Long input2, Double expected) {
        double epsilon = 0.000001d;
        assertEquals(expected, power.powLogN(input1, input2), epsilon);
    }
}
