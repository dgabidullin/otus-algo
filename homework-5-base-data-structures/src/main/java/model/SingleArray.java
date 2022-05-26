package model;

import java.util.Objects;

public class SingleArray<T> implements IArray<T> {

    private Object[] array;

    public SingleArray() {
        array = new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        resize();
        array[size() - 1] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void add(T item, int index) {
        checkRangeAdd(index);
        resize();
        System.arraycopy(array, index,
                array, index + 1,
                size() - 1 - index);
        array[index] = item;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size());
        final int newSize = size() - 1;
        Object[] newArray = new Object[newSize];
        @SuppressWarnings("unchecked") T oldValue = (T) array[index];
        System.arraycopy(array, index + 1, newArray, index, newSize - index);
        array = newArray;
        return oldValue;
    }

    private void resize() {
        Object[] newArray = new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, size());
//        for (int j = 0; j < size(); j ++)
//            newArray[j] = array[j];
        array = newArray;
    }

    private void checkRangeAdd(int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
