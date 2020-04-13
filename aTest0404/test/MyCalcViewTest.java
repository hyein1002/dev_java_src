package aTest0404.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import aTest0404.MyCalcView;

class MyCalcViewTest {
	MyCalcView mv = new MyCalcView();

	@Test
	void testInitDisplay() {
	}
	@Test
	void testMethodA() {
		assertEquals(0,mv.methodA(13, 3));
	}

}
