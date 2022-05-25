import model.VectorArray;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class VectorArrayTest {

    @Test
    void shouldReturnTrueWithAddWithIndexAndRemove() {
        final VectorArray<Integer> va = new VectorArray<>();
        va.add(1, 0);
        va.add(2);
        va.add(3, 0);
        assertEquals(3, va.size());
        assertEquals(3, va.get(0));
        va.remove(0);
        assertEquals(2, va.size());
        va.add(4, 1);
        assertEquals(1, va.get(0));
        assertEquals(4, va.get(1));
    }

    @Test
    void shouldThrowOutOfBoundsException() {
        final VectorArray<Integer> va = new VectorArray<>();
        assertThrows(IndexOutOfBoundsException.class, () -> va.add(4, 99));
        assertThrows(IndexOutOfBoundsException.class, () -> va.add(4, -99));
        assertThrows(IndexOutOfBoundsException.class, () -> va.remove(99));
    }
}
