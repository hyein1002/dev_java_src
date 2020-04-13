package aTest0404.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateInfoTest {

	DateInfo di = new DateInfo();

	@Test
	void testGetDifferenceDates() {
		assertEquals((long)24, di.getDifferenceDates("2020-03-29", "2020-03-31", "yyyy-MM-dd", 'H'));
	}

}
