package model;

import java.util.Objects;

public class FactorArray<T> implements IArray<T> {

    private Object[] array;
    private int factor;
    private int size;

    public FactorArray(int factor, int initLength) {
        this.factor = factor;
        array = new Object[initLength];
        size = 0;
    }

    public FactorArray() {
        this(50, 10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size() == array.length)
            resize();
        array[size] = item;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void add(T item, int index) {
        checkRangeAdd(index);
        if (size() == array.length)
            resize();
        System.arraycopy(array, index,
                array, index + 1,
                size() - index);
        array[index] = item;
        size++;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size());
        @SuppressWarnings("unchecked") T oldValue = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size() - 1 - index);
        size--;
        return oldValue;
    }

    private void resize() {
        Object[] newArray = new Object[array.length + array.length * factor / 100];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void checkRangeAdd(int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
