import static org.junit.Assert.*;

import org.junit.Test;
import java.util.EmptyStackException;

public class TaskMakeTest {

	@Test
	public void MakeGradeOne() {
		String Questions = new TaskMake().MakeGradeOne(1);
		ShuntingYard Calc = new ShuntingYard();
		int Result;
		try {
			Result = Integer.valueOf(Calc.Calc(Questions, 1));
    	}
    	catch(EmptyStackException e){
    		Result = -1;
    	}
		assertTrue(Result >= 0);
	}
	@Test
	public void MakeGradeTwo() {
		String Questions = new TaskMake().MakeGradeTwo(1);
		ShuntingYard Calc = new ShuntingYard();
		int Result;
		try {
			Result = Integer.valueOf(Calc.Calc("8 ¡Â 7", 1));
    	}
    	catch(EmptyStackException e){
    		Result = -1;
    	}
		assertTrue(Result > 0);
	}
	@Test
	public void MakeGradeThree() {
		String Questions = new TaskMake().MakeGradeThree(1);
		ShuntingYard Calc = new ShuntingYard();
		int Result;
		try {
			Result = Integer.valueOf(Calc.Calc(Questions, 1));
    	}
    	catch(EmptyStackException e){
    		Result = -1;
    	}
		assertTrue(Result > 0);
	}
	@Test
	public void Random() {
		int random = new TaskMake().Random(1, 100);
		assertTrue(random >= 1 && random <= 100);
	}
	@Test
	public void RandomOperator() {
		String random = new TaskMake().RandomOperator();
		assertTrue(random == "+" || random == "-" || random == "¡Á" || random == "¡Â");
	}
	@Test
	public void GetNumber(){
		assertEquals("(1) ", new TaskMake().GetNumber(0));
	}
	@Test
	public void GetExpression() {
		assertEquals("1 + 2", new TaskMake().GetExpression(1, 2, "+"));
	}
	@Test
	public void CheckOperator() {
		assertTrue( new TaskMake().CheckOperator("+"));
	}

}
