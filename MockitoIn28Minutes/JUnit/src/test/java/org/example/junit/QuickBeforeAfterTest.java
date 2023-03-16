package org.example.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuickBeforeAfterTest {
    @BeforeAll
    static void beforeClass() {
        System.out.println("Before class");
    }

    @BeforeEach
    void setup() {
        System.out.println("Before test");
    }

    @Test
    void test1() {
        System.out.println("Test1 executed");
    }

    @Test
    void test2() {
        System.out.println("Test2 executed");
    }
}
