package org.example.mockito.stubbing_variations_04;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    void testListSizeMock() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2, listMock.size());
    }

    @Test
    void testListSizeMockMultipleTimes() {
        List listMock = mock(List.class);
        when(listMock.size())
                .thenReturn(2)
                .thenReturn(3);

        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }

    @Test
    void testListGetMethodMock() {
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("something"));

        assertThrows(RuntimeException.class, () -> {
            listMock.get(0);
        });
    }
}
