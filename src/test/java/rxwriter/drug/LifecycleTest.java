package rxwriter.drug;

import org.junit.jupiter.api.*;

class LifecycleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each " + this);
    }

    @Test
    void testA() {
        System.out.println("Test A "  + this);
    }

    @Test
    void testB() {
        System.out.println("Test B " + this);
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each " + this);
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All");
    }

}
