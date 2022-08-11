package eu.fogas.collection;

import java.util.function.Consumer;

public class FastIntSet {
    private final int[] indexes;
    private final int[] values;
    private int size;

    public FastIntSet(int capacity) {
        values = new int[capacity];
        indexes = new int[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(int value) {
        if (!contains(value)) {
            values[size] = value;
            indexes[value] = size++;
            return true;
        }
        return false;
    }

    public void clear() {
        size = 0;
    }

    public boolean remove(int value) {
        if (contains(value)) {
            int index = indexes[value];
            values[index] = values[size--];
            indexes[values[index]] = index;
            return true;
        }
        return false;
    }

    public boolean contains(int value) {
        int index = indexes[value];
        return index < size && values[index] == value;
    }

    public void iterate(Consumer<Integer> function) {
        for (int i = 0; i < size; i++) {
            function.accept(values[i]);
        }
    }
}
