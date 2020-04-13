package aTest0404.test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import exam0407.CommonUtilities;

class CommonUtilitiesTest {

	CommonUtilities cu = new CommonUtilities();
	
	@Test
	//[문자열이 숫자 형식이면 true,아니면 false반환]
	void testIsNumber() {
		assertTrue(cu.isNumber("9"));
	}

	@Test
	void testGetDifferenceDates() throws ParseException {
		assertEquals((long)24, cu.getDifferenceDates("2020-04-01", "2020-04-02", "yyyy-MM-dd", 'H'));

	}

	@Test
	void testGetFirstCharacter() {
	//이름에서 초성을 구해 초성을 반환하는 메소드]
		assertEquals('ㄱ', cu.getFirstCharacter("김"));

	}

}
