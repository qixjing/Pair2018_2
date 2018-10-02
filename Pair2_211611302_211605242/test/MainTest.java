import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MainTest {
	boolean expected = false;
	String  input1 =null;

	@Parameters
	public static Collection<Object[]> t() {
		return Arrays.asList(new Object[][] {
			{"-grade 3 -n 100"},
			{"-grade 2 -n 100"},
			{"-grade 1 -n 100"},
//			{6,8,2},
//			{72,80,8},
//			{0,2,2},
			
//			{5,10,5},
//			{50,100,50},
//			{76,80,4}
		});
	}

	public MainTest(String input1) {
		this.input1 = input1;
//		this.input2 = input2;
	}

	@Test
	public void test() {
//		String []args= {"2","3","2","1"};
		MathExam.main(input1.split(" "));
//		assertEquals(expected,MathExam.flag);
	}

}