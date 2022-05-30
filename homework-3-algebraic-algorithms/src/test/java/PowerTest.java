import impl.Power;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;

public class PowerTest {

    private final Power power = new Power();

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\3.Power", twoInputArguments = true)
    void shouldReturnTruePowerLogN(Double input1, Long input2, Double expected) {
        System.out.printf("input1=%s, input2=%s, expected=%s%n", input1, input2, expected);
        double epsilon = 0.000001d;
        assertEquals(expected, TestUtil.timed(() -> power.powLogN(input1, input2)), epsilon);
    }

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\3.Power", twoInputArguments = true)
    void shouldReturnTruePowerMultiplication(Double input1, Long input2, Double expected) {
        System.out.printf("input1=%s, input2=%s, expected=%s%n", input1, input2, expected);
        double epsilon = 0.000001d;
        assertEquals(expected, TestUtil.timed(() -> power.powMultiplication(input1, input2)), epsilon);
    }

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\3.Power", twoInputArguments = true)
    void shouldReturnTruePowerIter(Double input1, Long input2, Double expected) {
        System.out.printf("input1=%s, input2=%s, expected=%s%n", input1, input2, expected);
        double epsilon = 0.000001d;
        assertEquals(expected, TestUtil.timed(() -> power.powIter(input1, input2)), epsilon);
    }
}
