import static org.junit.Assert.*;

import org.junit.Test;

public class MathExamTest {

	/*@Test
	public void testCheckOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckTwo() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNum() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testCheckInput() {
		String[] a=new String[] {"-n","50","-grade","3"};
		String[] b=new String[] {"-n","50","-grade","4"};
		String[] c=new String[] {"-n","10000","-grade","3"};
		String[] d=new String[] {"-nn","50","-grade","3"};
		String[] e=new String[] {"-n","50","-gg","3"};
		String[] f=new String[] {"-grade","3","-n","50"};
		assertEquals(true, MathExam.checkInput(a));
		assertEquals(false, MathExam.checkInput(b));
		assertEquals(false, MathExam.checkInput(c));
		assertEquals(false, MathExam.checkInput(d));
		assertEquals(false, MathExam.checkInput(e));
		assertEquals(true, MathExam.checkInput(f));
	}

	/* @Test
	public void testInform() {
		
	}

	@Test
	public void testMathOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testMathTwo() {
		fail("Not yet implemented");
	}

	@Test
	public void testMathThr() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}*/

}
