import org.junit.jupiter.params.ParameterizedTest
import kotlin.test.assertEquals

class ChessBitboardMovesTest {

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\1.Bitboard - Король", twoOutputStringArguments = true)
    fun shouldReturnAllKingTrue(input: String, expected1: String, expected2: String) {
        val mask: ULong = ChessBitboardMoves.getKingMoves(input.toInt())
        assertEquals(expected1.toInt(), ChessBitboardMoves.popCnt(mask))
        assertEquals(expected2.toULong(), mask)
    }

    @ParameterizedTest
    @OtusAlgoDataSource(path = "C:\\Users\\Дмитрий\\work\\2.Bitboard - Конь", twoOutputStringArguments = true)
    fun shouldReturnAllKnightTrue(input: String, expected1: String, expected2: String) {
        val mask: ULong = ChessBitboardMoves.getKnightMoves(input.toInt())
        assertEquals(expected1.toInt(), ChessBitboardMoves.popCnt2(mask))
        assertEquals(expected2.toULong(), mask)
    }
}
