import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class TestUtil {

    public static <T> T timed(Supplier<T> supplier) {
        final long start = System.nanoTime();

        T result = supplier.get();

        final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        System.out.println();
        System.out.println("timed: " + millis);

        return result;
    }
}
