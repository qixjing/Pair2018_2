import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


	@RunWith(Parameterized.class)
	public class ParameterTest{
		boolean expected;
		String sz;
		
		@Parameters
		public static Collection<Object[]> t(){
			return (Collection<Object[]>) Arrays.asList(new Object[][]{
				{false,"-n g -grade 3"},
				{false,"-n 10 -grade g"},
				{false,"-n 10000 -grade 3"},
				{false,"-n 10 -grade 10000"},
				{false,"-n 0.1 -grade 3"},
				{false,"-n 10 -grade 0.1"},
				
			});
	}
	public ParameterTest(boolean expected,String sz) {
		this.expected = expected;
		this.sz = sz;
	}
	
	@Test
	public void testAdd() {
		assertEquals(expected,MathExam1253.check(sz.split(" ")));
		
	}
	
	}

