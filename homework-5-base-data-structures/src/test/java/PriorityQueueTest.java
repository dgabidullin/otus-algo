import model.PriorityQueue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PriorityQueueTest {

    @Test
    void shouldReturnTrue() {
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.enqueue(1, 3);
        pq.enqueue(3, 2);
        pq.enqueue(99, 1);
        assertEquals(3, pq.dequeue());
        assertEquals(2, pq.dequeue());
        assertEquals(1, pq.dequeue());
    }
}
