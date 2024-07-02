package com.latihan.java.datastructure.list;

import com.latihan.java.datastructure.list.model.Composite;
import com.latihan.java.datastructure.list.model.Square;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaReferenceTest {

    @Test
    void javaReference(){
        Square square = new Square();
        Square duplicate = square;

        duplicate.x = 25;

        assertEquals(25, square.x);
        assertEquals(25, duplicate.x);

    }

    @Test
    void reference(){
        Composite composite = new Composite();
        composite.calculateA(5);
        assertEquals(5, composite.getFirstSquare().getA());
        assertEquals(5, composite.getSecondSquare().getA());
    }

}
