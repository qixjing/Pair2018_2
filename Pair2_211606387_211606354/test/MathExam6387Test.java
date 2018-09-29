import static org.junit.jupiter.api.Assertions.*;

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
}
