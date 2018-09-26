import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MathExamTest {

	@Test
	public void testMain() {
		String[] args = {"-n", "100", "-grade", "3"};
		MathExam math = new MathExam();
		math.main(args);
		assertEquals(true, math.output_boolean);
	}

	@Test
	public void testProduct_Problem_Answer() {
		assertEquals(true, new MathExam().Product_Problem_Answer(100, 3));
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
		MathExam math = new MathExam();
		List<String> Calculation_Problem = new ArrayList<String>();
		assertEquals(true, math.File_Write_Answer(Calculation_Problem));	
	}

	@Test
	public void testFile_Write_Problem() {
		MathExam math = new MathExam();
		List<String> Calculation_Problem = new ArrayList<String>();
		int sum = (123+123) * 123;
		int remainder = 0;
		Calculation_Problem.add("(123+123) * 123");
		assertEquals(Calculation_Problem, math.File_Write_Problem(Calculation_Problem, sum, remainder, "(123+123)x123"));	
	}

	@Test
	public void testFile_Initialization() {
		assertEquals(null, new MathExam().File_Initialization());	
	}

}
