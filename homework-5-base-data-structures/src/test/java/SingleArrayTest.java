import model.SingleArray;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class SingleArrayTest {

    @Test
    void shouldReturnTrueWithAddWithIndexAndRemove() {
        final SingleArray<Integer> sa = new SingleArray<>();
        sa.add(1, 0);
        sa.add(2);
        sa.add(3, 0);
        assertEquals(3, sa.size());
        assertEquals(3, sa.get(0));
        sa.remove(0);
        assertEquals(2, sa.size());
        sa.add(4, 1);
        assertEquals(1, sa.get(0));
        assertEquals(4, sa.get(1));
    }

    @Test
    void shouldThrowOutOfBoundsException() {
        final SingleArray<Integer> sa = new SingleArray<>();
        assertThrows(IndexOutOfBoundsException.class, () -> sa.add(4, 99));
        assertThrows(IndexOutOfBoundsException.class, () -> sa.add(4, -99));
        assertThrows(IndexOutOfBoundsException.class, () -> sa.remove(99));
    }
}
