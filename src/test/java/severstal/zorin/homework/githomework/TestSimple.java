package severstal.zorin.homework.githomework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("HW")
public class TestSimple {

    @Test
    static void simpleTest() {
        Assertions.assertEquals(1,1);
        System.out.println("This is simple test");
    }

    @Test
    static void simpleTest2() {
        Assertions.assertEquals(1,1);
        System.out.println("This is simple test");
    }

    @Test
    static void simpleTest3() {
        Assertions.assertEquals(1,1);
        System.out.println("This is simple test");
    }
}
