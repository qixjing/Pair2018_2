import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MathExam6360Test {

	@Test   //测试优先级的判断是否正确
	public void testLevel_1() {
		String operation = "+";
		 assertEquals(1, MathExam6360.Level(operation));
	}
	@Test   //测试优先级的判断是否正确
	public void testLevel_2() {
		String operation = "-";
		 assertEquals(1, MathExam6360.Level(operation));
	}
	@Test   //测试优先级的判断是否正确
	public void testLevel_3() {
		String operation = "×";
		 assertEquals(2, MathExam6360.Level(operation));
	}
	@Test   //测试优先级的判断是否正确
	public void testLevel_4() {
		String operation = "÷";
		 assertEquals(2, MathExam6360.Level(operation));
	}
	@Test   //测试优先级的判断是否正确
	public void testLevel_5() {
		String operation = "";
		 assertEquals(0, MathExam6360.Level(operation));
	}
	
	@Test   //测试传入的参数格式是否正确
	public void testMathExam_1(){
		String[] str = new String[] {"-n", "10", "-grade", "3"};
		assertEquals(true,MathExam6360.MathExam(str));
	}
	@Test  //测试传入的参数格式是否正确
	public void testMathExam_2(){
		String[] str = new String[] {"-grade", "3", "-n", "10"};
		assertEquals(true,MathExam6360.MathExam(str));
	}
	@Test  //测试传入的参数格式是否正确
	public void testMathExam_3(){
		String[] str = new String[] {"-n", "10000", "-grade", "3"};
		assertEquals(false,MathExam6360.MathExam(str));
	}
	@Test  //测试传入的参数格式是否正确
	public void testMathExam_4(){
		String[] str= {"-grade","3.0","-n","10"};
		assertEquals(false,MathExam6360.MathExam(str));
	}
	@Test  //测试传入的参数格式是否正确
	public void testMathExam_5(){
		String[] str= {"-grade","-3","-n","10"};
		assertEquals(false,MathExam6360.MathExam(str));
	}
	@Test  //测试传入的参数格式是否正确
	public void testMathExam_6(){
		String[] str= {"-grade","a","-n","b"};
		assertEquals(false,MathExam6360.MathExam(str));
	}

	@Test  //测试传入的题目数量和年级是否符合大小限定以及格式
	public void testJudge_1() {
		String count = "10";
		String grade = "3";
		assertEquals(true,MathExam6360.judge(count, grade));
	}
	
	@Test   //测试传入的题目数量和年级是否符合大小限定以及格式
	public void testJudge_2() {
		String count = "-10";
		String grade = "2";
		assertEquals(false,MathExam6360.judge(count, grade));
	}
	
	@Test   //测试传入的题目数量和年级是否符合大小限定以及格式
	public void testJudge_3() {
		String count = "10";
		String grade = "5";
		assertEquals(false,MathExam6360.judge(count, grade));
	}
	
	@Test   //测试传入的题目数量和年级是否符合大小限定以及格式
	public void testJudge_4() {
		String count = "10000";
		String grade = "3";
		assertEquals(false,MathExam6360.judge(count, grade));
	}
	
	@Test   //测试传入的题目数量和年级是否符合大小限定以及格式
	public void testJudge_5() {
		String count = "10";
		String grade = "-3";
		assertEquals(false,MathExam6360.judge(count, grade));
	}
	
	@Test   //测试传入的题目数量和年级是否符合大小限定以及格式
	public void testJudge_6() {
		String count = "10.2";
		String grade = "3";
		assertEquals(false,MathExam6360.judge(count, grade));
	}
	
	@Test   //测试传入的题目数量和年级是否符合大小限定以及格式
	public void testJudge_7() {
		String count = "ab";
		String grade = "ac";
		assertEquals(false,MathExam6360.judge(count, grade));
	}

	@Test  //测试逆波兰算法是否正确
	public void testParseSuffixExpression() {
		 List<String> test=new ArrayList<String>();
	        test.add("1");
	        test.add("+");
	        test.add("2");
	        test.add("-");
	        test.add("3");
	        test.add("×");
	        test.add("4");
	        test.add("÷");
	        test.add("5");
	        List<String> answer=new ArrayList<String>();
	        answer.add("1");
	        answer.add("2");
	        answer.add("+");
	        answer.add("3");
	        answer.add("4");
	        answer.add("×");
	        answer.add("5");
	        answer.add("÷");
	        answer.add("-");
	        assertEquals(answer, MathExam6360.parseSuffixExpression(test));
	}

	@Test  //测试中序表达式是否正确
	public void testToInfixExpression() {
		String test= "1+2-3×4÷5";
        List<String> answer=new ArrayList<String>();
        answer.add("1");
        answer.add("+");
        answer.add("2");
        answer.add("-");
        answer.add("3");
        answer.add("×");
        answer.add("4");
        answer.add("÷");
        answer.add("5");
        assertEquals(answer, MathExam6360.toInfixExpression(test));
	}

	@Test  //测试计算方式是否正确
	public void testReckon() {
		List<String> test=new ArrayList<String>();
	    test.add("1");
	    test.add("2");
	    test.add("+");
	    test.add("3");
	    test.add("4");
	    test.add("×");
	    test.add("5");
	    test.add("÷");
	    test.add("-");
	    String answer="1";
	    assertEquals(answer, MathExam6360.reckon(test));
	}
	
	@Test  //测试生成题目数量是否正确
	public void testCalculate_1() {
		int count = 100;
		assertEquals(count, MathExam6360.calculate_1(count));
	}
	
	@Test  //测试生成题目数量是否正确
	public void testCalculate_2() {
		int count = 100;
		assertEquals(count, MathExam6360.calculate_2(count));
	}
	
	@Test  //测试生成题目数量是否正确
	public void testCalculate_3() {
		int count = 100;
		assertEquals(count, MathExam6360.calculate_3(count));
	}

}
