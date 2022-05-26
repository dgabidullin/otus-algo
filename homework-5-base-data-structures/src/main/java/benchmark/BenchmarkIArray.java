package benchmark;

import model.ArrayListWrapper;
import model.FactorArray;
import model.IArray;
import model.MatrixArray;
import model.SingleArray;
import model.VectorArray;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BenchmarkIArray {
//
//    @Fork(value = 1, warmups = 1)
//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    public void singleArrayAdd() {
//        IArray<Date> singleArray = new SingleArray<>();
//        for (int j = 0; j < 100_000; j++)
//            singleArray.add(new Date());
//    }
//
//    @Fork(value = 1, warmups = 1)
//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    public void singleArrayAddWithIndexToTheBeginning() {
//        IArray<Date> singleArray = new SingleArray<>();
//        for (int j = 0; j < 100_000; j++)
//            singleArray.add(new Date(), 0);
//    }
//
//    @Fork(value = 1, warmups = 1)
//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    public void singleArrayAddWithIndexToTheEnd() {
//        IArray<Date> singleArray = new SingleArray<>();
//        for (int j = 0; j < 100_000; j++)
//            singleArray.add(new Date(), singleArray.size());
//    }
//
//    @Fork(value = 1, warmups = 1)
//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    public void vectorArrayAdd() {
//        IArray<Date> va = new VectorArray<>();
//        for (int j = 0; j < 100_000; j++)
//            va.add(new Date());
//    }
//
//    @Fork(value = 1, warmups = 1)
//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    public void vectorArrayAddWithIndexToTheBeginning() {
//        IArray<Date> va = new VectorArray<>();
//        for (int j = 0; j < 100_000; j++)
//            va.add(new Date(), 0);
//    }
//
//    @Fork(value = 1, warmups = 1)
//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    public void vectorArrayAddWithIndexToTheEnd() {
//        IArray<Date> va = new VectorArray<>();
//        for (int j = 0; j < 100_000; j++)
//            va.add(new Date(), va.size());
//    }

    public static void main(String[] args) throws IOException {
//        org.openjdk.jmh.Main.main(args);
        testAddBeginningArray(new SingleArray<>(), 100_000);
        testAddEndArray(new SingleArray<>(), 100_000);
        testRemoveFromBeginningArray(new SingleArray<>(), 100_000);
        testRemoveFromEndArray(new SingleArray<>(), 100_000);

        testAddBeginningArray(new VectorArray<>(), 100_000);
        testAddEndArray(new VectorArray<>(), 100_000);
        testRemoveFromBeginningArray(new VectorArray<>(), 100_000);
        testRemoveFromEndArray(new VectorArray<>(), 100_000);

        testAddBeginningArray(new FactorArray<>(), 100_000);
        testAddEndArray(new FactorArray<>(), 100_000);
        testRemoveFromBeginningArray(new FactorArray<>(), 100_000);
        testRemoveFromEndArray(new FactorArray<>(), 100_000);

        testAddBeginningArray(new MatrixArray<>(), 100_000);
        testAddEndArray(new MatrixArray<>(), 100_000);
        testRemoveFromBeginningArray(new MatrixArray<>(), 100_000);
        testRemoveFromEndArray(new MatrixArray<>(), 100_000);

        testAddBeginningArray(new ArrayListWrapper<>(), 100_000);
        testAddEndArray(new ArrayListWrapper<>(), 100_000);
        testRemoveFromBeginningArray(new ArrayListWrapper<>(), 100_000);
        testRemoveFromEndArray(new ArrayListWrapper<>(), 100_000);
    }

    private static void testAddBeginningArray(IArray<Integer> data, int total) {
        final long start = System.nanoTime();

        for (int j = 0; j < total; j++)
            data.add(total);

        final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        System.out.println(data + " testAddBeginningArray: " + millis);
    }

    private static void testRemoveFromBeginningArray(IArray<Integer> data, int total) {
        for (int j = 0; j < total; j++)
            data.add(total);

        final long start = System.nanoTime();

        while (data.size() != 0) {
            data.remove(0);
        }

        final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        System.out.println(data + " testRemoveFromBeginningArray: " + millis);
    }

    private static void testRemoveFromEndArray(IArray<Integer> data, int total) {
        for (int j = 0; j < total; j++)
            data.add(total);

        final long start = System.nanoTime();

        while (data.size() != 0) {
            data.remove(data.size() - 1);
        }

        final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        System.out.println(data + " testRemoveFromEndArray: " + millis);
    }

    private static void testAddEndArray(IArray<Integer> data, int total) {
        final long start = System.nanoTime();

        for (int j = 0; j < total; j++)
            data.add(1, data.size());

        final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        System.out.println(data + " testAddEndArray: " + millis);
    }
}
