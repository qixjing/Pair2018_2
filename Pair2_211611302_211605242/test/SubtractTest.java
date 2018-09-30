import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class SubtractTest{
	int expected = 0;
	int input1 = 0;
	int input2  =0;

	@Parameters
	public static Collection<Object[]>t(){
		return Arrays.asList(new Object[][] {
			{6,9,3},
			{6,8,2},
			{72,80,8},
			{0,2,2},
			{5,10,5},
			{50,100,50},
			{76,80,4}
		});
	}
	
	public SubtractTest(int expected,int input1,int input2) {
		this.expected=expected;
		this.input1 = input1;
		this.input2 = input2;
	}
	
	@Test
	public void testSubtract() {
		assertEquals(expected,new OperationSubtract().calculate(input1, input2));
	}

}