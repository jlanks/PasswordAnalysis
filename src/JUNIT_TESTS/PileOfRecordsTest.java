package JUNIT_TESTS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Pile_Of_Records;
import main.Record;

class PileOfRecordsTest {

	@Test
	void test() {
		Pile_Of_Records pile_Of_Records = new Pile_Of_Records();
		Record rec = new Record(); 
		
		pile_Of_Records.add(rec); 
		assertEquals(1,pile_Of_Records.size());
	}

}
