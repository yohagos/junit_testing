package rxwriter.prescription;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DurationParserTest {

    @Disabled("not implemented")
    @ParameterizedTest
    @ValueSource(strings = {"2 weeks", "1 month"})
    public void parseDurationWithValidUnitAndQuantity(String durationString) {
        // assertNotEquals to check for a specific Test which should return a fail response
        // assertNotEquals(14, DurationParser.parseDays("two weeks"));

        //assertEquals(14, DurationParser.parseDays("2 weeks"));
        //assertEquals(30, DurationParser.parseDays("1 month"));

        assertEquals(14, DurationParser.parseDays(durationString));
    }

    @ParameterizedTest
    @CsvSource({"14, 2 weeks", "30, 1 month", "5, 5 days", "1, once"})
    public void parseDurationWithValidUnitAndQuantity(int expectedDays, String durationString) {
        assertEquals(expectedDays, DurationParser.parseDays(durationString));
    }



}