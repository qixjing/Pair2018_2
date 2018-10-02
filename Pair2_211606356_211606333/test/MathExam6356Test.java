
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


class MathExam6356Test {

	@Test
	void testInit1() throws FileNotFoundException {
		// ������������-grade x -n x
		String[] str= {"-grade","3","-n","10"};
		assertEquals("��������", MathExam6356.init(str));
	}
	@Test
	void testInit2() throws FileNotFoundException {
		// ������������-n x -grade x
		String[] str= {"-n","11","-grade","3"};
		assertEquals("��������", MathExam6356.init(str));
	}
	@Test
	void testInit3() throws FileNotFoundException {
		// ����������
		String[] str= {};
		assertEquals("��������", MathExam6356.init(str));
	}
	@Test
	void testInit4() throws FileNotFoundException {
		// ������Ŀ��Ϊ����
		String[] str= {"-grade","3","-n","-10"};
		assertEquals("��������С�ڵ���0��������������������", MathExam6356.init(str));
	}
	@Test
	void testInit5() throws FileNotFoundException {
		// �����꼶Ϊ����
		String[] str= {"-grade","-3","-n","10"};
		assertEquals("��������С�ڵ���0��������������������", MathExam6356.init(str));
	} 
	@Test
	void testInit6() throws FileNotFoundException {
		// ���Բ�����Ŀ����Ϊ������
		String[] str= {"-grade","3","-n","10.1"};
		assertEquals("��������������", MathExam6356.init(str));
	}
	@Test
	void testInit7() throws FileNotFoundException {
		// �����꼶�������˷�Χ
		String[] str= {"-grade","5","-n","10"};
		assertEquals("Ŀǰֻ֧��1-3�꼶������������", MathExam6356.init(str));
	}
	@Test
	void testInit8() throws FileNotFoundException {
		// �����꼶����������
		String[] str= {"-grade","3.1","-n","10"};
		assertEquals("��������������", MathExam6356.init(str));
	}
	
	
	@Test
	void testGradeThree() throws FileNotFoundException {
		// �������꼶��������
		MathExam6356.setN(100);
		MathExam6356.gradeThree();
		assertEquals(100, MathExam6356.question.length);
		assertEquals(100, MathExam6356.answer.length);
	}

	@Test
	void testGradeTwo() {
		// ���Զ��꼶��������
		MathExam6356.setN(11);
		MathExam6356.gradeTwo();
		assertEquals(11, MathExam6356.question.length);
		assertEquals(11, MathExam6356.answer.length);
	}

	@Test
	void testOutPut() throws FileNotFoundException {
		// ��������ܷ�����
		assertEquals(true, MathExam6356.outPut());
	}

	@Test
	void testGradeOne() {
		// ����һ�꼶��������
		MathExam6356.setN(12);
		MathExam6356.gradeOne();
		assertEquals(12, MathExam6356.question.length);
		assertEquals(12, MathExam6356.answer.length);
	}

	@Test
	void testParseSuffixExpression() {
		// ��������ת����
        List<String> test=new ArrayList<String>();
        test.add("1");
        test.add("+");
        test.add("2");
        test.add("-");
        test.add("3");
        test.add("��");
        test.add("4");
        test.add("��");
        test.add("5");
        List<String> answer=new ArrayList<String>();
        answer.add("1");
        answer.add("2");
        answer.add("+");
        answer.add("3");
        answer.add("4");
        answer.add("��");
        answer.add("5");
        answer.add("��");
        answer.add("-");
        assertEquals(answer, MathExam6356.parseSuffixExpression(test));
	}

	@Test
	void testToInfixExpression() {
		// ����������Ŀ�ַ������뼯��
		String test= "1+2-3��4��5";
        List<String> answer=new ArrayList<String>();
        answer.add("1");
        answer.add("+");
        answer.add("2");
        answer.add("-");
        answer.add("3");
        answer.add("��");
        answer.add("4");
        answer.add("��");
        answer.add("5");
        assertEquals(answer, MathExam6356.toInfixExpression(test));
	}

	@Test
	void testAnswer() {
		//���Դ�
        List<String> test=new ArrayList<String>();
        test.add("1");
        test.add("2");
        test.add("+");
        test.add("3");
        test.add("4");
        test.add("��");
        test.add("5");
        test.add("��");
        test.add("-");
        String answer="1";
        assertEquals(answer, MathExam6356.answer(test));
	}

}
