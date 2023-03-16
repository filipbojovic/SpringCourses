package org.example.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArraysCompareTest {
    @Test
    void testArraySort() {
        int[] numbers = {12, 3, 4, 1};
        int[] expected = {1, 3, 4, 12};
        Arrays.sort(numbers);
        assertArrayEquals(numbers, expected);
    }

    @Test
    void testArraySortWhenArrayIsNull() {
        int[] numbers = null;

        assertThrows(NullPointerException.class, () -> {
            Arrays.sort(numbers);
        });
    }

    @Test
    @Timeout(1)
    void testSortPerfromance() {
        int array[] = {12, 23, 4};
        for (int i = 0; i < 1000000; i++) {
            array[0] = i;
            Arrays.sort(array);
        }
    }
}
