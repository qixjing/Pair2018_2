import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class MultiplyTest{
	int expected = 0;
	int input1 = 0;
	int input2  =0;

	@Parameters
	public static Collection<Object[]>t(){
		return Arrays.asList(new Object[][] {
			{27,9,3},
			{16,8,2},
			{640,80,8},
			{4,2,2},
			{50,10,5},
			{5000,100,50},
			{320,80,4}
		});
	}
	
	public MultiplyTest(int expected,int input1,int input2) {
		this.expected=expected;
		this.input1 = input1;
		this.input2 = input2;
	}
	
	@Test
	public void testMultiply() {
		assertEquals(expected,new OperationMultiply().calculate(input1, input2));
	}

}