package org.example.mockito.advanced;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpyTest {
    @Spy
    private ArrayList arrayListMock;

    @Test
    void test() {
        assertEquals(0, arrayListMock.size());
        arrayListMock.add("fika");
        assertEquals(1, arrayListMock.size());
    }
}
