package com.java6369.lesson03;

import static org.junit.Assert.*;

import org.junit.Test;

	
public class MathExam6369Test {  
 

public void testMathExamOne() throws Exception  {  
	String[] input = new String[] {"-n", "10", "-grade", "1"};
	MathExam6369.main(input);
	assertEquals(true,MathExam6369.judge()); 
	@Test  
	public void testMathExamTwo() throws Exception  {  
		String[] input = new String[] {"-n", "10", "-grade", "2"};  
		MathExam6369.main(input);  
		assertEquals(true,MathExam6369.judge());  
		@Test  
		public void testMathExamThree() throws Exception  {  
			String[] input = new String[] {"-n", "10", "-grade", "3"}; 
			MathExam6369.main(input);  
			assertEquals(true,MathExam6369.judge());
		}
	}

	
	
