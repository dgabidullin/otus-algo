package model;

import java.util.ArrayList;

public class ArrayListWrapper<T> implements IArray<T> {

    private final ArrayList<T> arrayList;

    public ArrayListWrapper(int capacity) {
        arrayList = new ArrayList<>(capacity);
    }

    public ArrayListWrapper() {
        arrayList = new ArrayList<>();
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public void add(T item) {
        arrayList.add(item);
    }

    @Override
    public T get(int index) {
        return arrayList.get(index);
    }

    @Override
    public void add(T item, int index) {
        arrayList.add(index, item);
    }

    @Override
    public T remove(int index) {
        return arrayList.remove(index);
    }
}
