import static org.junit.Assert.*;

import org.junit.Test;

public class ParameterTest {

	@Test
	public void DealData() {
		String[] args = {"-n","10","-Grade","3"};
		//≤‚ ‘boolean
		assertTrue(new Parameter(args).DealData(args));
	}
	@Test
	public void GetNumber() {
		String[] args = {"-n","10","-Grade","3"};
		//≤‚ ‘boolean,long,intµ»
		assertEquals(10, new Parameter(args).GetNumber());
	}
	@Test
	public void GetGrade() {
		String[] args = {"-n","10","-Grade","3"};
		//≤‚ ‘boolean,long,intµ»
		assertEquals(3, new Parameter(args).GetGrade());
	}
}

