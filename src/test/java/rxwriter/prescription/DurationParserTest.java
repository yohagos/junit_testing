package rxwriter.prescription;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DurationParserTest {

    @Test
    void parseDays() {
    }

    @Test
    public void parseDurationWithValidUnitAndQuantity() {
        // assertNotEquals to check for a specific Test which should return a fail response
        // assertNotEquals(14, DurationParser.parseDays("two weeks"));
        assertEquals(14, DurationParser.parseDays("2 weeks"));
        assertEquals(30, DurationParser.parseDays("1 month"));
    }



}