package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Pupil.RPNcompute;

class RPNcomputeTest {

	@Test
	void testRPNcalculate1() {
		assertEquals(116, new RPNcompute().RPNcalculate("85 - 0 / 21 - 31"));
	}
	@Test
	void testRPNcalculate2() {
		assertEquals(85, new RPNcompute().RPNcalculate("85 - 0 / ( 31 - 21 )"));
	}
	@Test
	void testRPNcalculate3() {
		assertEquals(Double.NEGATIVE_INFINITY,
				new RPNcompute().RPNcalculate("85 - 21 / 0 - 31"));
	}
	@Test
	void testRPNcalculate4() {
		assertEquals(Double.NEGATIVE_INFINITY, 
				new RPNcompute().RPNcalculate("^ * # + &"));
	}

}
