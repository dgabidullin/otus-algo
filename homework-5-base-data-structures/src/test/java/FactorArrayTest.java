import model.FactorArray;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FactorArrayTest {

    @Test
    void shouldReturnTrueWithAddWithIndexAndRemove() {
        final FactorArray<Integer> fa = new FactorArray<>();
        fa.add(1, 0);
        fa.add(2);
        fa.add(3, 0);
        assertEquals(3, fa.size());
        assertEquals(3, fa.get(0));
        fa.remove(0);
        assertEquals(2, fa.size());
        fa.add(4, 1);
        assertEquals(1, fa.get(0));
        assertEquals(4, fa.get(1));
    }

    @Test
    void shouldThrowOutOfBoundsException() {
        final FactorArray<Integer> sa = new FactorArray<>();
        assertThrows(IndexOutOfBoundsException.class, () -> sa.add(4, 99));
        assertThrows(IndexOutOfBoundsException.class, () -> sa.add(4, -99));
        assertThrows(IndexOutOfBoundsException.class, () -> sa.remove(99));
    }
}
