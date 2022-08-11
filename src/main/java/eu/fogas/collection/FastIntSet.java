package eu.fogas.collection;

import java.util.function.Consumer;

/**
 * Fast int set implementation to store ints between 0 (inclusive) and N (exclusive).
 */
public class FastIntSet {
    private final int[] indexes;
    private final int[] values;
    private int size;

    /**
     * Constructs a new FastIntSet to store ints between 0 (inclusive) and capacity (exclusive).
     *
     * @param capacity the capacity of the set
     */
    public FastIntSet(int capacity) {
        values = new int[capacity];
        indexes = new int[capacity];
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this set contains no elements.
     *
     * @return {@code true} if this set contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds the specified int to this set if it is not already present.
     *
     * @param value to be added to this set
     * @return {@code true} if this set did not already contain the specified int
     */
    public boolean add(int value) {
        if (!contains(value)) {
            values[size] = value;
            indexes[value] = size++;
            return true;
        }
        return false;
    }

    /**
     * Removes all the elements from this set and makes it empty.
     */
    public void clear() {
        size = 0;
    }

    /**
     * Removes the specified element from this set if it is present
     *
     * @param value to remove
     * @return {@code true} if this set contained the specified int
     */
    public boolean remove(int value) {
        if (contains(value)) {
            int index = indexes[value];
            values[index] = values[size--];
            indexes[values[index]] = index;
            return true;
        }
        return false;
    }

    /**
     * Returns {@code true} if this set contains the specified element.
     *
     * @param value element whose presence in this set is to be tested
     * @return {@code true} if this set contains the specified element
     */
    public boolean contains(int value) {
        int index = indexes[value];
        return index < size && values[index] == value;
    }

    /**
     * Calls the int consumer with each element in this set.
     *
     * @param consumer integer consumer
     */
    public void iterate(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept(values[i]);
        }
    }

    /**
     * Returns a new array, containing all the elements in this set.
     *
     * @return a new array, containing all the elements in this set.
     */
    public int[] toArray() {
        int[] copy = new int[size];
        System.arraycopy(values, 0, copy, 0, size);
        return copy;
    }
}
