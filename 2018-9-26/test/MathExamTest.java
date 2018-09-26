import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MathExamTest {

	@Test
	public void testMain() {
		String[] args = {"100","3"};
		assertEquals(0, new MathExam().main(args));
	}

	@Test
	public void testProduct_Problem_Answer() {
		assertEquals(true, new MathExam().Product_Problem_Answer(100, 3));	
		assertEquals(false, new MathExam().Product_Problem_Answer(100, 0));	
	}

	@Test
	public void testJudge_Repetition() {
		String[] str_symbol= {"+","-","x","¡Â"};
		List<String> Word_Set = new ArrayList<String>();
		Word_Set.add("2x3");
		assertEquals(null, new MathExam().Judge_Repetition(2, 3,str_symbol, 2, Word_Set));	
	}

	public void testIteration() {
		assertEquals("123", new MathExam().Iteration(3));	
	}

	@Test
	public void testInput_Message() {
		String[] args = {"-n", "100", "-grade", "3"};
		List<String> output = new ArrayList<String>();
		output.add("100");
		output.add("3");
		assertEquals(output, new MathExam().Input_Message(args));	
	}

	@Test
	public void testFile_Write_Answer() {
		List<String> Calculation_Problem = new ArrayList<String>();
		assertEquals(true, new MathExam().File_Write_Answer(Calculation_Problem));	
	}

	@Test
	public void testFile_Write_Problem() {
		List<String> Calculation_Problem = new ArrayList<String>();
		assertEquals(true, new MathExam().File_Write_Problem(Calculation_Problem, "(123+123)x123"));	
	}

	@Test
	public void testFile_Initialization() {
		assertEquals(true, new MathExam().File_Initialization());	
	}

}
