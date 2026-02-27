package com.latihan.java.logic.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

class CollectionTest {

    @Test
    void supplier() {
        Supplier<String> supplier = () -> "A-Ant Sultan R";
        String name = supplier.get();
        Assertions.assertEquals("A-Ant Sultan R", name);
    }

}
