package rxwriter.drug;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import rxwriter.drug.database.DrugRecord;
import rxwriter.drug.database.DrugSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrugServiceTest implements DrugSource {

    private DrugService service;

    @BeforeEach
    void setup() {
        service = new DrugService(this);
    }

    @Test
    void drugAreReturnedSorted() {
        //DrugService service = new DrugService(this);
        List<DispensableDrug> foundDrugs = service.findDrugsStartingWith("as");
        assertNotNull(foundDrugs);
        assertEquals(2, foundDrugs.size());
        assertEquals("asmanex", foundDrugs.get(0).drugName());
        assertEquals("aspirin", foundDrugs.get(1).drugName());
    }

    /*
    AssertEquals not possible with DispensableDrug Object, because of the DrugClassification Array.
    Those Arrays have not the same signature in Memmory, therefore the equals comparator will fail

    @Test
    void drugReturnedSorted() {
        List<DispensableDrug> expectedList = new ArrayList<>();
        expectedList.add(new DispensableDrug("asmanex", new DrugClassification[] {DrugClassification.NASAL_CORTICOSTEROIDS}, false));
        expectedList.add(new DispensableDrug("aspirin", new DrugClassification[] {DrugClassification.ANALGESIC, DrugClassification.PLATELET_AGGREGATION_INHIBITORS}, false));
        DrugService service = new DrugService();
        List<DispensableDrug> foundDrugs = service.findDrugsStartingWith("as");
        assertEquals(expectedList, foundDrugs);
    }*/

    @Test
    void throwsExceptionOnEmptyStringStartsWith() {
        //DrugService service = new DrugService(this);
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.findDrugsStartingWith("  "));
        System.out.println(exception.getMessage());
    }

    @Test
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
        } else if (startingString.equals("aspirin")) {
            records.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
        }
        return records;
    }
}