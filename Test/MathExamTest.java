package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Pupil.MathExam;

class MathExamTest {

	@Test
	void testCreateProblem() {
		assertEquals(true, new MathExam().createProblem());
	}
	
	@Test
	void testCreateFile() {
		assertEquals(false, new MathExam().createFile("out"));
	}

	@Test
	void testWaterFile() {
		assertEquals(true, new MathExam().waterFile("out"));
	}

}
