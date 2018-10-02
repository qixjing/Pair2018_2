package com.Pair2.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.Pair2.MathExam.MathExam6335;

public class MathExam6335Test {
	
//	MathExam6335 math = new MathExam6335();
//	@Before
//	public void setUp() throws Exception {
//		
//	}

	@Test
	public void testMathExam6335() {
		String[] str = {"-n","5","-grade","3"};
		MathExam6335 math = new MathExam6335(str);
		int count = math.count;
		int grade = math.grade;
		assertEquals(5, count);
		assertEquals(3, grade);
	}


	@Test
	public void testInPut_00() { 
		String[] str = {"-n","5","-grade","3"};
		MathExam6335 math = new MathExam6335();
		math.inPut(str);
		assertEquals(5, math.count); 
		assertEquals(3, math.grade); 
	}
	
	@Test
	public void testInPut_01() {
		String[] str = {"-grade","3","-n","5"};
		MathExam6335 math = new MathExam6335();
		math.inPut(str);
		assertEquals(5, math.count); 
		assertEquals(3, math.grade); 
	} 
	
	@Test
	public void testInPut_10() {
		String[] str = {"-n","5","-grade","2"};
		MathExam6335 math = new MathExam6335();
		math.inPut(str);
		assertEquals(5, math.count); 
		assertEquals(2, math.grade); 
	}
	
	@Test
	public void testInPut_11() {
		String[] str = {"-grade","2","-n","5"};
		MathExam6335 math = new MathExam6335();
		math.inPut(str);
		assertEquals(5, math.count); 
		assertEquals(2, math.grade); 
	}
	
	@Test
	public void testInPut_20() {
		String[] str = {"-n","5","-grade","1"};
		MathExam6335 math = new MathExam6335();
		math.inPut(str);
		assertEquals(5, math.count); 
		assertEquals(1, math.grade); 
	}
	
	@Test
	public void testInPut_21() {
		String[] str = {"-grade","1","-n","5"};
		MathExam6335 math = new MathExam6335();
		math.inPut(str);
		assertEquals(5, math.count); 
		assertEquals(1, math.grade); 
	}
	
	@Test
	public void testInPut_30() {
		String[] str = {"-r","5","-grade","3"};
		MathExam6335 math = new MathExam6335();
		math.inPut(str);
		assertEquals("false", String.valueOf(math.w1));  
	}
	
	@Test
	public void testInPut_31() {
		String[] str = {"-n","5","-gr","3"};
		MathExam6335 math = new MathExam6335();
		math.inPut(str);
		assertEquals("false", String.valueOf(math.w1)); 
	}
	
	@Test
	public void testInPut_32() {
		String[] str = {"-n","a","-grade","3"};
		MathExam6335 math = new MathExam6335(); 
		math.inPut(str);
		assertEquals("false", String.valueOf(math.w2)); 
	}
	
	@Test
	public void testInPut_33() {
		String[] str = {"-n","5","-grade","5"};
		MathExam6335 math = new MathExam6335();
		math.inPut(str);
		assertEquals("false", String.valueOf(math.w2)); 
	}

	@Test
	public void testOutPut() {
		String[] str = {"-n","5","-grade","2"};
		MathExam6335 math = new MathExam6335(str);
		assertEquals("true", String.valueOf(math.w3));  
	}
	
	@Test
	public void testIsOperator_true() {
		assertEquals(true, new MathExam6335().isOperator("+")); 
		assertEquals(true, new MathExam6335().isOperator("-"));
		assertEquals(true, new MathExam6335().isOperator("¡Á"));
		assertEquals(true, new MathExam6335().isOperator("¡Â")); 
		assertEquals(true, new MathExam6335().isOperator("("));
		assertEquals(true, new MathExam6335().isOperator(")"));
	}
	@Test
	public void testIsOperator_false() { 
		assertEquals(false, new MathExam6335().isOperator("jia"));
		assertEquals(false, new MathExam6335().isOperator("09")); 
		assertEquals(false, new MathExam6335().isOperator("x"));
		assertEquals(false, new MathExam6335().isOperator("X")); 
		assertEquals(false, new MathExam6335().isOperator("/"));
		assertEquals(false, new MathExam6335().isOperator(" "));
	}


	@Test
	public void testPriority() {
		assertEquals(1, new MathExam6335().priority("+"));
		assertEquals(1, new MathExam6335().priority("-"));
		assertEquals(2, new MathExam6335().priority("¡Á"));
		assertEquals(2, new MathExam6335().priority("¡Â"));
		assertEquals(3, new MathExam6335().priority("("));
		assertEquals(3, new MathExam6335().priority(")"));
		assertEquals(0, new MathExam6335().priority(" "));
	}


	@Test
	public void testCalculation_true() {
		assertEquals(1, new MathExam6335().Calculation(3, 2, "-"));
		assertEquals(5, new MathExam6335().Calculation(3, 2, "+")); 
		assertEquals(6, new MathExam6335().Calculation(3, 2, "¡Á"));
		assertEquals(0, new MathExam6335().Calculation(0, 2, "¡Á"));
		assertEquals(1, new MathExam6335().Calculation(3, 2, "¡Â"));
		assertEquals(0, new MathExam6335().Calculation(0, 2, "¡Â"));
	
	}
	
	@Test
	public void testCalculation_false() {
		assertEquals(-2, new MathExam6335().Calculation(3, 5, "-"));
		assertEquals(-1, new MathExam6335().Calculation(3, 0, "¡Â")); 
	}


	@Test
	public void testToPostfixExpression_0() {
		MathExam6335 math = new MathExam6335();
		String str = "32+5+4-";
		assertEquals(str,math.toPostfixExpression("3+2+5-4"));
	}

	@Test
	public void testToPostfixExpression_1() {
		MathExam6335 math = new MathExam6335();
		String str = "3025+¡Á4-";
		assertEquals(str,math.toPostfixExpression("30¡Á(2+5)-4"));
	}
	
	@Test
	public void testreversePolish() {
		String[] str = {"-n","5","-grade","2"};
		MathExam6335 math = new MathExam6335(str);
		assertEquals("false",String.valueOf(math.w4));
	}

	@Test
	public void testAdd() {
		
		assertEquals(4, new MathExam6335().add(2,2,1)); 
	}
	
	@Test
	public void testSub_0() {
		
		assertEquals(2, new MathExam6335().sub(2,4,1)); 
	}

	@Test
	public void testSub_1() {
		assertEquals(0, new MathExam6335().sub(2,2,1));
	}


	@Test
	public void testMul_0() {
		assertEquals(4, new MathExam6335().mul(2,2,1));
	}
	
	@Test
	public void testMul_1() {
		assertEquals(0, new MathExam6335().mul(0,2,1));
	}


	@Test
	public void testDiv_0() { 
		assertEquals("1", new MathExam6335().div(3,3,1));
	}
	
	@Test
	public void testDiv_1() {
		assertEquals("0", new MathExam6335().div(0,3,1));
	}
	
	@Test
	public void testDiv_3() {
		assertEquals("1...1", new MathExam6335().div(3,2,1));
	}


}
