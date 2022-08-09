package eu.fogas.collection;

import java.util.Arrays;
import java.util.function.Consumer;

public class FastIntSet {
    private static final int NOT_PRESENT = -1;
    private final int[] indexes;
    private final int[] values;
    private int size;

    public FastIntSet(int capacity) {
        values = new int[capacity];
        indexes = new int[capacity];
        Arrays.fill(indexes, NOT_PRESENT);
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
        int index = indexes[value];
        if (isPresent(index, value)) {
            indexes[value] = NOT_PRESENT;
            values[index] = values[size--];
            indexes[values[index]] = index;
            return true;
        }
        return false;
    }

    public boolean contains(int value) {
        return isPresent(indexes[value], value);
    }

    public void iterate(Consumer<Integer> function) {
        for (int value : values) {
            function.accept(value);
        }
    }

    private boolean isPresent(int index, int value) {
        return index != NOT_PRESENT && index < size && values[index] == value;
    }
}
