import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;

public class LuckyTicketsSolutionTest {

    private final LuckyTicketsSolution luckyTicketsSolution = new LuckyTicketsSolution();

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\A01_Счастливые_билеты-1801-057a77")
    void check(Integer input, Long expected) {
        System.out.printf("input=%s, expected=%s%n", input, expected);
        assertEquals(luckyTicketsSolution.countLuckyTicketsWithDigit(input), expected);
    }
}
