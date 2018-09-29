import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class MathExam6387Test {

	@Test
	public void testChoiceGrade() {
		new MathExam6387();
		assertEquals(1, MathExam6387.ChoiceGrade(5, 1));
	}

	@Test
	public void testJudgementRemainder() {
		new MathExam6387();
		assertEquals("..." + (4%5) + " ", MathExam6387.JudgementRemainder(4, 5));;
	}
	
	@Test
	public void testSymbolicJudgment() {
		new MathExam6387();
		assertEquals("*", MathExam6387.SymbolicJudgment(2));
	}
	
	@Test
	public void testReversePolish() {
		new MathExam6387();
		String[] strArr = {"1","2","+"};
		assertEquals("3", MathExam6387.ReversePolish(strArr));
	}
	
	@Test
	public void GradeOne() {
		MathExam6387.GradeOne(5);
		assertEquals(5,MathExam6387.str.length);
	}
	
	@Test
	public void GradeTow() {
		MathExam6387.GradeTwo(5);
		assertEquals(5, MathExam6387.str.length);
	}
	
	@Test
	public void GradeThree() {
		MathExam6387.GradeThree(5);
		assertEquals(5, MathExam6387.str.length);
	}
	
	@Test
	public void testRandomNumber() {
		new MathExam6387();
		String[] strArr = {"1","2","+"};
		assertEquals("3", MathExam6387.ReversePolish(strArr));
	}
}
