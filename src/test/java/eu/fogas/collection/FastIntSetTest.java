package eu.fogas.collection;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FastIntSetTest {

    @Test
    void add_shouldAddElement_whenValueIsNotPresent() {
        FastIntSet set = new FastIntSet(3);

        assertTrue(set.isEmpty());
        assertTrue(set.add(0));
        assertTrue(set.contains(0));
        assertEquals(1, set.size());
    }

    @Test
    void add_shouldNotAddElement_whenValueIsPresent() {
        FastIntSet set = new FastIntSet(3);

        assertTrue(set.isEmpty());
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertEquals(1, set.size());
        assertFalse(set.add(1));
        assertTrue(set.contains(1));
        assertEquals(1, set.size());
    }

    @Test
    void add_shouldReturnTrue_whenValueIsNotPresent() {
        FastIntSet set = new FastIntSet(3);
        assertTrue(set.isEmpty());

        set.add(1);

        assertTrue(set.contains(1));
        assertFalse(set.contains(0));
        assertFalse(set.isEmpty());
    }

    @Test
    void remove_shouldReturnFalse_whenValueIsNotPresent() {
        FastIntSet set = new FastIntSet(3);

        assertTrue(set.isEmpty());
        assertFalse(set.remove(1));
        assertTrue(set.isEmpty());
    }

    @Test
    void remove_shouldReturnTrue_whenValueIsPresent() {
        FastIntSet set = new FastIntSet(3);
        assertTrue(set.isEmpty());

        set.add(1);

        assertTrue(set.remove(1));
        assertFalse(set.contains(1));
        assertTrue(set.isEmpty());
    }

    @Test
    void contains_shouldReturnTrue_whenValueIsPresent() {
        FastIntSet set = new FastIntSet(3);
        set.add(1);

        assertTrue(set.contains(1));
    }

    @Test
    void contains_shouldWork_whenClearWasCalledBefore() {
        FastIntSet set = new FastIntSet(5);
        set.add(0);
        set.add(1);
        set.add(2);
        set.clear();

        set.add(1);
        set.add(0);

        assertTrue(set.contains(1));
        assertFalse(set.contains(2));
    }

    @Test
    void contains_shouldReturnFalse_whenValueIsNotPresent() {
        FastIntSet set = new FastIntSet(3);

        assertFalse(set.contains(1));
    }

    @Test
    void iterate() {
        FastIntSet set = new FastIntSet(3);
        set.add(1);
        set.add(2);
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);

        set.iterate(e -> assertTrue(expected.remove(e), "Cannot find: " + e));

        assertTrue(expected.isEmpty());
    }

    @Test
    void clear_thenIterate_shouldIterateOnEmpty() {
        FastIntSet set = new FastIntSet(3);
        set.add(1);
        set.add(2);
        set.add(0);
        set.clear();
        Set<Integer> expected = new HashSet<>();

        set.iterate(e -> assertTrue(expected.remove(e), "Cannot find: " + e));

        assertTrue(expected.isEmpty());
    }

    @Test
    void size_shouldReturnZero_whenSetIsEmpty() {
        FastIntSet set = new FastIntSet(3);

        assertEquals(0, set.size());
    }

    @Test
    void size_shouldReturnSize_whenSetHasValues() {
        FastIntSet set = new FastIntSet(3);
        set.add(0);
        set.add(1);

        assertEquals(2, set.size());
    }

    @Test
    void isEmpty_shouldReturnTrue_whenSetIsEmpty() {
        FastIntSet set = new FastIntSet(3);

        assertTrue(set.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalse_whenSetHasValues() {
        FastIntSet set = new FastIntSet(3);
        set.add(0);
        set.add(1);

        assertFalse(set.isEmpty());
    }

    @Test
    void clear_shouldSetSizeToZeroAndRemoveElements() {
        FastIntSet set = new FastIntSet(3);
        set.add(0);
        set.add(1);

        set.clear();

        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        assertFalse(set.contains(0));
        assertFalse(set.contains(1));
    }

    @Test
    void clear_shouldWorkAfterClear() {
        FastIntSet set = new FastIntSet(3);
        set.add(0);
        set.add(1);

        set.clear();
        set.add(1);
        set.add(2);

        assertFalse(set.isEmpty());
        assertEquals(2, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertFalse(set.contains(0));
    }

    @Test
    void clear_shouldWorkAfterClear2() {
        FastIntSet set = new FastIntSet(3);
        set.add(1);
        set.add(0);

        set.clear();
        set.add(1);


        assertFalse(set.isEmpty());
        assertEquals(1, set.size());
        assertTrue(set.contains(1));
        assertFalse(set.contains(2));
        assertFalse(set.contains(0));
    }

    @Test
    void toArray_shouldReturnAnArrayViewOfTheSet() {
        FastIntSet set = new FastIntSet(3);
        set.add(1);
        set.add(0);

        var result = set.toArray();
        set.add(2);

        assertArrayEquals(new int[]{1, 0}, result);
    }
}