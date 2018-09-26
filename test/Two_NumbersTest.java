import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Two_NumbersTest {

	@Test
	public void testJudge_Repetition() {
		String[] str_symbol= {"+","-","x","¡Â"};
		List<String> Word_Set = new ArrayList<String>();
		Two_Numbers calc = new Two_Numbers();
		Word_Set.add("2x3");
		calc.setWord_Set(Word_Set);
		assertEquals(null, calc.Judge_Repetition(2, 3, "x"));	
	}

	public void testIteration() {
		assertEquals("123", new Two_Numbers().Iteration(3));	
	}
	
	@Test
	public void testCalculate_Two_Numbers() {
		assertEquals(9, new Two_Numbers().Calculate_Two_Numbers(3,3,2,3));
	}

	@Test
	public void testSetWord_Set() {
		List<String> word_set = new ArrayList<String>();
		Two_Numbers calc = new Two_Numbers();
		word_set.add("123");
		word_set.add("+");
		word_set.add("123");
		calc.setWord_Set(word_set);
		assertEquals(word_set, calc.getWord_Set());
	}
	
	@Test
	public void testGetWord_Set() {
		List<String> word_set = new ArrayList<String>();
		assertEquals(word_set, new Two_Numbers().getWord_Set());
	}
	
	@Test
	public void testGetSum() {
		assertEquals(0, new Two_Numbers().getSum());
	}
	
	@Test
	public void testSetRemainder() {
		int remainder = 0;
		Two_Numbers calc = new Two_Numbers();
		calc.setRemainder(0);
		assertEquals(0, calc.getRemainder());
	}
	

	@Test
	public void testGetRemainder() {
		assertEquals(0, new Two_Numbers().getRemainder());
	}

}
