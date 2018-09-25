import static org.junit.Assert.*;

import org.junit.Test;

public class Two_NumbersTest {

	@Test
	public void testCalculate_Two_Numbers() {
		assertEquals(9,new Two_Numbers().Calculate_Two_Numbers(3,3,2,3));
	}


	@Test
	public void testGetRemainder() {
		assertEquals(0,new Two_Numbers().getRemainder());
	}

}
