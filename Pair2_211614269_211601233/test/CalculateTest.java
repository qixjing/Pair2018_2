import static org.junit.Assert.*;

import org.junit.Test;

public class CalculateTest {

	@Test
	public void testAns() {
		String a="( 3 + 2 ) * 5";
		String b="3 + 2 - 10 ";
		
		int[] aa=new int[]{1,25};		
		int[] bb=new int[]{0,-5};	
	
		assertArrayEquals(aa, Calculate.ans(a));
		assertArrayEquals(bb, Calculate.ans(b));
	
	}

}
