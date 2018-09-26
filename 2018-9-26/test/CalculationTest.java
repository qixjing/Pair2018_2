import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CalculationTest {

	@Test
	public void testCalculation() {
		List<String> inffix_expression = new ArrayList<String>();
		inffix_expression.add("(");
		inffix_expression.add("123");
		inffix_expression.add("+");
		inffix_expression.add("123");
		inffix_expression.add(")");
		inffix_expression.add("x");
		inffix_expression.add("123");
		assertEquals(inffix_expression, new Calculation("(123+123)x123", new ArrayList<String>()).getInffix_expression());
	}

	@Test
	public void testTo_Suffix_Expression() {
		List<String> inffix_expression = new ArrayList<String>(),
				Suffix_Expression = new ArrayList<String>();
		inffix_expression.add("(");
		inffix_expression.add("123");
		inffix_expression.add("+");
		inffix_expression.add("123");
		inffix_expression.add(")");
		inffix_expression.add("x");
		inffix_expression.add("123");
		Suffix_Expression.add("123");
		Suffix_Expression.add("123");
		Suffix_Expression.add("+");
		Suffix_Expression.add("123");
		Suffix_Expression.add("x");
		assertEquals(Suffix_Expression,new Calculation("(123+123)x123", new ArrayList<String>()).To_Suffix_Expression(inffix_expression));
	}

	@Test
	public void testOper() {
		assertEquals(false, new Calculation("(123+123)x123", new ArrayList<String>()).Oper("+", "x"));
		assertEquals(false, new Calculation("(123+123)x123", new ArrayList<String>()).Oper("+", "¡Â"));
		assertEquals(true, new Calculation("(123+123)x123", new ArrayList<String>()).Oper("¡Â", "+"));
	}

	@Test
	public void testSuffix_Expression_Summation() {
		List<String> Suffix_Expression = new ArrayList<String>();
		Suffix_Expression.add("123");
		Suffix_Expression.add("123");
		Suffix_Expression.add("+");
		Suffix_Expression.add("123");
		Suffix_Expression.add("x");
		assertEquals(false,new Calculation("(123+123)x123", new ArrayList<String>()).Suffix_Expression_Summation(Suffix_Expression));
	}

	@Test
	public void testInfix_Expression_To_Word() {
		List<String> inffix_expression = new ArrayList<String>();
		inffix_expression.add("(");
		inffix_expression.add("123");
		inffix_expression.add("+");
		inffix_expression.add("123");
		inffix_expression.add(")");
		inffix_expression.add("x");
		inffix_expression.add("123");
		assertEquals("( 123 + 123 ) x 123",new Calculation("(123+123)x123", new ArrayList<String>()).Infix_Expression_To_Word(inffix_expression));
	}

	@Test
	public void testGetInffix_expression() {
		List<String> inffix_expression = new ArrayList<String>();
		inffix_expression.add("(");
		inffix_expression.add("123");
		inffix_expression.add("+");
		inffix_expression.add("123");
		inffix_expression.add(")");
		inffix_expression.add("x");
		inffix_expression.add("123");
		assertEquals(inffix_expression, new Calculation("(123+123)x123", new ArrayList<String>()).getInffix_expression());
	}

	@Test
	public void testGetSuffix_expression() {
		List<String> Suffix_Expression = new ArrayList<String>();
		assertEquals(Suffix_Expression, new Calculation("(123+123)x123", new ArrayList<String>()).getSuffix_expression());
	}

	@Test
	public void testGetword() {
		assertEquals("", new Calculation("(123+123)x123", new ArrayList<String>()).getword());
	}

	@Test
	public void testGetSum() {
		assertEquals(0, new Calculation("(123+123)x123", new ArrayList<String>()).getSum());
	}

	@Test
	public void testGetWord_Set() {
		List<String> word_set = new ArrayList<String>();
		word_set.add("123+123");
		assertEquals(word_set, new Calculation("(123+123)x123", word_set).getWord_Set());
	}

}
