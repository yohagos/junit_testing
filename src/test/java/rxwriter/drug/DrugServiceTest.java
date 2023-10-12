package rxwriter.drug;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.mockito.Mockito;
import rxwriter.drug.database.DrugRecord;
import rxwriter.drug.database.DrugSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DrugService should")
class DrugServiceTest implements DrugSource {

    private DrugService service;

    @BeforeEach
    void setup() {
        service = new DrugService(this);
    }

    @Test
    @DisplayName("return drugs from database sorted by drug name")
    void drugAreReturnedSorted() {
        //DrugService service = new DrugService(this);
        List<DispensableDrug> foundDrugs = service.findDrugsStartingWith("as");
        assertNotNull(foundDrugs);
        assertEquals(2, foundDrugs.size(), () -> "two drugs with 'as' should be returned from test data.");
        assertEquals("asmanex", foundDrugs.get(0).drugName());
        assertEquals("aspirin", foundDrugs.get(1).drugName());
    }

    /*
    AssertEquals not possible with DispensableDrug Object, because of the DrugClassification Array.
    Those Arrays have not the same signature in Memmory, therefore the equals comparator will fail
*/
    //@Disabled
    /*DrugService@Test
    void drugReturnedSorted() {
        List<DispensableDrug> expectedList = new ArrayList<>();
        expectedList.add(new DispensableDrug("asmanex", new DrugClassification[] {DrugClassification.NASAL_CORTICOSTEROIDS}, false));
        expectedList.add(new DispensableDrug("aspirin", new DrugClassification[] {DrugClassification.ANALGESIC, DrugClassification.PLATELET_AGGREGATION_INHIBITORS}, false));
         service = new DrugService();
        List<DispensableDrug> foundDrugs = service.findDrugsStartingWith("as");
        assertEquals(expectedList, foundDrugs);
    }*/

    // @EnabledOnJre(JRE.JAVA_9) enables test for Java 9
    // @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_18) // enables text if jre version between Range
    //@EnabledForJreRange(min = JRE.JAVA_17)
    // @EnabledOnOs(OS.SOLARIS) // enables test for specific

    @Nested
    @DisplayName("throw an illegal argument exception")
    class ThrowsExceptionTests {
        @Test
        @DisplayName("when passed a blank string for startingWith")
        void throwsExceptionOnBlankStringStartsWith() {
            //DrugService service = new DrugService(this);
            Exception exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> service.findDrugsStartingWith("  "));
            System.out.println(exception.getMessage());
        }

        @Test
        @DisplayName("when passed a empty string for startingWith")
        void throwsExceptionOnEmptyStringStartsWith() {
            //DrugService service = new DrugService(this);
            Exception exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> service.findDrugsStartingWith(""));
            System.out.println(exception.getMessage());
        }
    }

    @Test
    @DisplayName("return dispensable drugs with all properties set correctly from database")
    void setDrugPropertiesCorrectly() {
        //DrugService service = new DrugService(this);
        List<DispensableDrug> foundDrugs = service.findDrugsStartingWith("aspirin");
        DrugClassification[] expectedClassification = new DrugClassification[] {
          DrugClassification.ANALGESIC, DrugClassification.PLATELET_AGGREGATION_INHIBITORS
        };
        DispensableDrug drug = foundDrugs.get(0);

        // If one of the first assertions fail, the following assertions will be skipped, therefore not an ideal test pattern

        /*assertEquals("aspirin", drug.drugName());
        assertFalse(drug.isControlled());
        assertEquals(2, drug.drugClassifications().length);
        assertArrayEquals(expectedClassification, drug.drugClassifications());*/

        assertAll("DispensableDrug properties",
                () -> assertEquals("aspirin", drug.drugName()),
                () -> assertFalse(drug.isControlled()),
                () -> assertEquals(2, drug.drugClassifications().length),
                () -> assertArrayEquals(expectedClassification, drug.drugClassifications())
        );
    }

    /*
    Mockito example: in which Mockito generates the mock information

    @Test
    void drugAreReturnedSortedMockito() {
        List<DrugRecord> records = new ArrayList<>();
        records.add(new DrugRecord("asmanex", new int[] {301}, 0));
        records.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
        DrugSource mockDrugSource = Mockito.mock(DrugSource.class);
        Mockito.when(mockDrugSource.findDrugsStartingWith("as"))
                .thenReturn(records);
        DrugService service = new DrugService(mockDrugSource);

        List<DispensableDrug> foundDrugs = service.findDrugsStartingWith("as");
        assertNotNull(foundDrugs);
        assertEquals(2, foundDrugs.size());
        assertEquals("asmanex", foundDrugs.get(0).drugName());
        assertEquals("aspirin", foundDrugs.get(1).drugName());
    }*/

    @Override
    public List<DrugRecord> findDrugsStartingWith(String startingString) {
        List<DrugRecord> records = new ArrayList<>();
        if (startingString.equals("as")) {
            records.add(new DrugRecord("asmanex", new int[] {301}, 0));
            records.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
            //records.add(new DrugRecord("aspirin, children's", new int[] {3645, 3530}, 0));
        } else if (startingString.equals("aspirin")) {
            records.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
        }
        return records;
    }
}