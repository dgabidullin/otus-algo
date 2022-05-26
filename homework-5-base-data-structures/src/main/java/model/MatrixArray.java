package model;

public class MatrixArray<T> implements IArray<T> {

    private int size;
    private int vector;
    private IArray<IArray<T>> array;

    public MatrixArray(int vector) {
        this.vector = vector;
        array = new VectorArray<>();
        size = 0;
    }

    public MatrixArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        add(item, size);
    }

    @Override
    public T get(int index) {
        return array.get(index / vector).get(index % vector);
    }

    @Override
    public void add(T item, int index) {
        if (size == array.size() * vector)
            array.add(new VectorArray<>(vector));
        int binToInsert = index / vector;
        for (int i = array.size() - 1; i > binToInsert; --i) {
            array.get(i).add(array.get(i - 1).get(array.get(i - 1).size() - 1), 0);
            array.get(i - 1).remove(array.get(i - 1).size() - 1);
        }
        array.get(binToInsert).add(item, index % vector);
        size++;
    }

    @Override
    public T remove(int index) {
        int binToRemove = index / vector;
        T oldValue = array.get(binToRemove).remove(index % vector);
        for (int i = binToRemove + 1; i < array.size(); ++i) {
            array.get(i - 1).add(array.get(i).get(0));
            array.get(i).remove(0);
        }
        if (array.get(array.size() - 1).size() == 0) {
            array.remove(array.size() - 1);
        }
        size--;
        return oldValue;
    }
}
