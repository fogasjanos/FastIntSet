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
        clear();
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
        Arrays.fill(indexes, NOT_PRESENT);
        size = 0;
    }

    public boolean remove(int value) {
        int index = indexes[value];
        if (isPresent(index)) {
            indexes[value] = NOT_PRESENT;
            values[index] = values[size--];
            indexes[values[index]] = index;
            return true;
        }
        return false;
    }

    public boolean contains(int value) {
        return isPresent(indexes[value]);
    }

    public void iterate(Consumer<Integer> function) {
        int counter = 0;
        for (int index : indexes) {
            if (isPresent(index)) {
                function.accept(values[index]);
                counter++;
            }
            if (counter == size) {
                break;
            }
        }
    }

    private boolean isPresent(int index) {
        return index != NOT_PRESENT;
    }
}
