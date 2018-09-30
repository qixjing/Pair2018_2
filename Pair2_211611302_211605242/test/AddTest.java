import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class AddTest{
	int expected = 0;
	int input1 = 0;
	int input2  =0;

	@Parameters
	public static Collection<Object[]>t(){
		return Arrays.asList(new Object[][] {
			{3,1,2},
			{4,2,2},
			{10,8,2},
			{2,2,0},
			{3,2,1},
			{10,2,8},
			{9,2,7}
		});
	}
	
	public AddTest(int expected,int input1,int input2) {
		this.expected=expected;
		this.input1 = input1;
		this.input2 = input2;
	}
	
	@Test
	public void testAdd() {
		OperationAdd o = new OperationAdd();
		assertEquals(expected,o.calculate(input1,input2));
	}

}