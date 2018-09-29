

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.Test;

public class MathExamTest {

@Test
public void testMathExamOne() throws Exception  {
	String[] input = new String[] {"-n", "10", "-grade", "1"};
	MathExam.main(input);
	assertEquals(true,MathExam.out_boolean());
	
}
@Test
public void testMathExamTwo() throws Exception  {
	String[] input = new String[] {"-n", "10", "-grade", "2"};
	MathExam.main(input);
	assertEquals(true,MathExam.out_boolean());
	
}

@Test
public void testMathExamThree() throws Exception  {
	String[] input = new String[] {"-n", "10", "-grade", "3"};
	MathExam.main(input);
	assertEquals(true,MathExam.out_boolean());
	
}

}
