import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MathExam6356Test {

	@Test
	void testInit1() throws FileNotFoundException {
		// 测试正常输入-grade x -n x
		String[] str= {"-grade","3","-n","10"};
		assertEquals("正常运行", MathExam6356.init(str));
	}
	@Test
	void testInit2() throws FileNotFoundException {
		// 测试正常输入-n x -grade x
		String[] str= {"-n","11","-grade","3"};
		assertEquals("正常运行", MathExam6356.init(str));
	}
	@Test
	void testInit3() throws FileNotFoundException {
		// 测试无输入
		String[] str= {};
		assertEquals("正常运行", MathExam6356.init(str));
	}
	@Test
	void testInit4() throws FileNotFoundException {
		// 测试题目数为负数
		String[] str= {"-grade","3","-n","-10"};
		assertEquals("您输入了小于等于0的数，请输入正整数！", MathExam6356.init(str));
	}
	@Test
	void testInit5() throws FileNotFoundException {
		// 测试年级为负数
		String[] str= {"-grade","-3","-n","10"};
		assertEquals("您输入了小于等于0的数，请输入正整数！", MathExam6356.init(str));
	} 
	@Test
	void testInit6() throws FileNotFoundException {
		// 测试参数题目数量为非整数
		String[] str= {"-grade","3","-n","10.1"};
		assertEquals("请输入正整数！", MathExam6356.init(str));
	}
	@Test
	void testInit7() throws FileNotFoundException {
		// 测试年级数超出了范围
		String[] str= {"-grade","5","-n","10"};
		assertEquals("目前只支持1-3年级，请重新输入", MathExam6356.init(str));
	}
	@Test
	void testInit8() throws FileNotFoundException {
		// 测试年级数非正整数
		String[] str= {"-grade","3.1","-n","10"};
		assertEquals("请输入正整数！", MathExam6356.init(str));
	}
	
	
	@Test
	void testGradeThree() throws FileNotFoundException {
		// 测试三年级生成数组
		MathExam6356.setN(100);
		MathExam6356.gradeThree();
		assertEquals(100, MathExam6356.question.length);
		assertEquals(100, MathExam6356.answer.length);
	}

	@Test
	void testGradeTwo() {
		// 测试二年级生成数组
		MathExam6356.setN(11);
		MathExam6356.gradeOne();
		assertEquals(11, MathExam6356.question.length);
		assertEquals(11, MathExam6356.answer.length);
	}

	@Test
	void testOutPut() {
		// 测试输出能否运行
		assertEquals(true, MathExam6356.outPut());
	}

	@Test
	void testGradeOne() {
		// 测试一年级生成数组
		MathExam6356.setN(12);
		MathExam6356.gradeOne();
		assertEquals(12, MathExam6356.question.length);
		assertEquals(12, MathExam6356.answer.length);
	}

	@Test
	void testParseSuffixExpression() {
		// 测试中序转后续
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
        assertEquals(answer, MathExam6356.parseSuffixExpression(test));
	}

	@Test
	void testToInfixExpression() {
		// 测试中序题目字符串放入集合
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
        assertEquals(answer, MathExam6356.toInfixExpression(test));
	}

	@Test
	void testAnswer() {
		//测试答案
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
        assertEquals(answer, MathExam6356.answer(test));
	}

}
