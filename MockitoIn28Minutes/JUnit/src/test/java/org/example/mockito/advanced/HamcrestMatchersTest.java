package org.example.mockito.advanced;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersTest {

    @Test
    void test() {
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);

        // assert that scores has 4 items
        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(99, 100));

        // every item in the list has some specific condition
        assertThat(scores, everyItem(greaterThan(90)));

        // string
        assertThat("", isEmptyString());
        assertThat(null, isEmptyOrNullString());

        // arrays
        Integer[] marks = {1, 2, 3};
    }
}
