import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class PolishNotationTest{
	String expected = null;
	StringBuffer input1 = new StringBuffer("");
//	int input2  =0;

	@Parameters
	public static Collection<Object[]>t(){
		return Arrays.asList(new Object[][] {
			{"6",new StringBuffer("5 + 1")},
			{"15",new StringBuffer("16 + ( 33 + 10 ) - 44")},
			{"6",new StringBuffer("( 155 - 56 ) / 9 - 5")},
			{"1",new StringBuffer("( 242 - 77 ) / 15 - 10")},
			{"902",new StringBuffer("28 + ( 38 * 23 )")},
			{"6664",new StringBuffer("15 * ( 40 * 11 ) + 64")}
		});
	}
	
	public PolishNotationTest(String expected,StringBuffer input1) {
		this.expected=expected;
		this.input1 = input1;
	}
	
	@Test
	public void testPolish() {
		assertEquals(expected,new PolishNotation(input1).calculate());
	}

}