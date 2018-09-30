import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class DivTest{
	int expected = 0;
	int input1 = 0;
	int input2  =0;

	@Parameters
	public static Collection<Object[]>t(){
		return Arrays.asList(new Object[][] {
			{3,9,3},
			{4,8,2},
			{10,80,8},
			{1,2,2},
			{2,10,5},
			{2,100,50},
			{20,80,4}
		});
	}
	
	public DivTest(int expected,int input1,int input2) {
		this.expected=expected;
		this.input1 = input1;
		this.input2 = input2;
	}
	
	@Test
	public void testDiv() {
		assertEquals(expected,new OperationDiv().calculate(input1, input2));
	}

}