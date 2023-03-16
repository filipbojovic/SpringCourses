package org.example.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StringHelperTest {
    private StringHelper subject = new StringHelper();

    @Test
    void testTruncateAInFirst2Positions() {
        var res = subject.truncateAInFirst2Positions("AACD");
        String expected = "CD";
        assertEquals(expected, res);
    }

    @Test
    void testWhenTheFirstAndTheLastCharacterAreDifferent() {
        assertEquals(
                false,
                subject.areFirstAndLastTwoCharactersTheSame("ABCD")
        );

        assertFalse(subject.areFirstAndLastTwoCharactersTheSame("ABCD"));
    }

}