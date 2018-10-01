import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class RegexT{
	boolean expected ;
	String input1 = null;

	@Parameters
	public static Collection<Object[]>t(){
		return Arrays.asList(new Object[][] {
			{false,"-grade 100 -n 100"},
			{true,"-grade 3 -n 3"},
			{false,"-grade 0 -n 3"},
			{true,"-n 100 -grade 2"},
			{false,"-n 1"},
			{false,"-na 1"},
			{false,"-n 100 -grade 2.3"},
			{false,"-n 0 -grade 3"},
			{true,"-n 1 -grade 2"},
		});
	}
	
	public RegexT(boolean expected,String input1) {
		this.expected= expected;
		this.input1 = input1;
	}
	
	@Test
	public void regexAdd() {
		assertEquals(expected, new RegexTest().test(input1));
	}

}