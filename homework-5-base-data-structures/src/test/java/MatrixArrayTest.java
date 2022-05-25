import model.MatrixArray;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MatrixArrayTest {

    @Test
    void shouldReturnTrueWithAddWithIndexAndRemove() {
        final MatrixArray<Integer> ma = new MatrixArray<>();
        ma.add(1, 0);
        ma.add(2);
        ma.add(3, 0);
        assertEquals(3, ma.size());
        assertEquals(3, ma.get(0));
        ma.remove(0);
        assertEquals(2, ma.size());
        ma.add(4, 1);
        assertEquals(1, ma.get(0));
        assertEquals(4, ma.get(1));
    }

    @Test
    void shouldThrowOutOfBoundsException() {
        final MatrixArray<Integer> ma = new MatrixArray<>();
        assertThrows(IndexOutOfBoundsException.class, () -> ma.add(4, 99));
        assertThrows(IndexOutOfBoundsException.class, () -> ma.add(4, -99));
        assertThrows(IndexOutOfBoundsException.class, () -> ma.remove(99));
    }
}
