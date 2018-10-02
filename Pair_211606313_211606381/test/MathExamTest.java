import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MathExamTest {
	boolean expected = false;
	String[] args;
	String errorMessage;
	Character[] t;
	boolean expected2 = false;
	int len;
	int grade;
	
	@Parameterized.Parameters
	public static Collection<Object[]> t(){
		return Arrays.asList(new Object[][]{

			{false,new String[] {"-n", "-1","-grade","3"},"题目数量不是正整数，请重新运行，输入一个正整数",new Character[] {null,null,'+',null,null},true,3},
			{false,new String[] {},"输入的参数形式有误，请按照 -n 题量 -grade 数量   或者 -grade 数量   -n 题量  格式输入。",new Character[] {null,null,'+'},false,1},
			{false, new String[] {"100"},"输入的参数形式有误，请按照 -n 题量 -grade 数量   或者 -grade 数量   -n 题量  格式输入。",new Character[] {null,null,'÷',null,null},true,2},
			{false,new String[] {"-n", "10.5","-grade","1"},"题目数量不是正整数，请重新运行，输入一个正整数",new Character[] {null,null,'÷','+',null,null,'+'},true,2},
			{false,new String[] {"-n", "ascc", "-grade", "2"},"题目数量不是正整数，请重新运行，输入一个正整数",new Character[] {null,null,null,'+','÷',null,'+'},false,3},
			{false,new String[] {"-n", "10", "-grade", "vsdv"},"目前只支持1~3年级，请重新运行，输入1~3中的一个数字",new Character[] {null,null,'÷','÷',null,null,null},true,1},
			{true,new String[] {"-n", "00001","-grade", "3"},"小学3年级数学题题目已生成，请查看out.txt文件",new Character[] {null,null,'÷','×',null,null,'+'},true,1},
			{false,new String[] {"-n", "1000","-grade","2.3"},"目前只支持1~3年级，请重新运行，输入1~3中的一个数字",new Character[] {null,null,'×'},false,2},
			{true,new String[] {"-n", "10","-grade","002"},"小学2年级数学题题目已生成，请查看out.txt文件",new Character[] {null,null,null,'+','÷',null,null},true,3},
			{false,new String[] {"-n", "10000","-grade","3"},"题目数量过大，请重新运行，输入参数",new Character[] {null,null,'×','×',null,null,'+'},true,1},
			{false,new String[] {"1000", "-n","-grade","2"},"输入的参数形式有误，请按照 -n 题量 -grade 数量   或者 -grade 数量   -n 题量  格式输入。",new Character[] {null,null,null,'-','-',null,'×'},false,3},
			{false,new String[] {"-n", "10","-grade","-3"},"目前只支持1~3年级，请重新运行，输入1~3中的一个数字",new Character[] {null,null,'-'},false,1},
			{false,new String[] {"-n", "1000","2","-grade"},"输入的参数形式有误，请按照 -n 题量 -grade 数量   或者 -grade 数量   -n 题量  格式输入。",new Character[] {null,null,'×','×',null,null,'+'},true,3},
			{true,new String[] {"-grade", "2", "-n", "1000"},"小学2年级数学题题目已生成，请查看out.txt文件",new Character[] {null,null,'-','×',null,null,'+'},true,3},
			{false,new String[] {"-grade", "0.1", "-n", "800"},"目前只支持1~3年级，请重新运行，输入1~3中的一个数字",new Character[] {null,null,'+','+',null,null,'÷'},true,3},
			{false,new String[] {"-grade", "a1", "-n", "10"},"目前只支持1~3年级，请重新运行，输入1~3中的一个数字",new Character[] {null,null,'÷'},false,2},
			{true,new String[] {"-grade", "001", "-n", "20"},"小学1年级数学题题目已生成，请查看out.txt文件",new Character[] {null,null,null,null,'×','÷',null},true,2},
			{true,new String[] {"-grade", "1", "-n", "0000000002"},"小学1年级数学题题目已生成，请查看out.txt文件",new Character[] {null,null,null,null,'+','×',null},true,3},
			{false,new String[] {"-grade", "0.1", "-n", "0.1"},"题目数量不是正整数，请重新运行，输入一个正整数",new Character[] {null,null,'-','×',null,null,'-'},true,1},
			{false,new String[] {"-n", "a1","-grade","a1"},"题目数量不是正整数，请重新运行，输入一个正整数",new Character[] {null,null,'×',null,'-',null,'-'},false,3},
			{true,new String[] {"-n", "10","-grade","3"},"小学3年级数学题题目已生成，请查看out.txt文件",new Character[] {null,null,'÷',null,null,'+',null},true,3}

		});
	}
	
	public MathExamTest(boolean expected,String[] args,String errorMessage,Character[] t,boolean expected2,int grade) {
		this.expected = expected;
		this.args = args;
		this.errorMessage = errorMessage;
		this.t = t;
		this.expected2 = expected2;
		this.grade = grade;
	}
	
	@Test
	public void testJudgmentParameter() {
		
		assertEquals(expected, MathExam.judgmentParameter(args));
	}
	
	@Test(timeout = 200)
	public void testGeneratingTopic() {
		Character[] topic = MathExam.generatingTopic(3);

		assertEquals(true, (topic != null));
	}
	
	@Test
	public void testIsInfixExpre() {
		assertEquals(expected2, MathExam.isInfixExpre(t));
	}
	
	@Test 
	public void testCalPoland() {
		if(!expected2) {
			boolean f = false;
			do {
				f = false;
				MathExam.topic = new StringBuffer[] {new StringBuffer()};
				MathExam.standAnswer = new StringBuffer[] {new StringBuffer()};
				int re = 0;
				int res = 0;
				try {
					res = MathExam.calPoland(t,0,grade);
					String str = MathExam.topic[0].toString().replace(" ", ""); 
					str = str.replace("×", "*"); 
					str = str.replace("÷", "/"); 
					re = (int) new ShuntingYard().shuntYardAlgo(str.toCharArray());
					assertEquals(re , res);

				} catch (ArithmeticException e) {
					f = true;
				}
			}while(f);
		}
	}
	
	
	@Test 
	public void testWrite() throws Exception {
		File file = new File("test.txt");
		if(file.exists() && file.isFile()) {
			file.delete();
		}
		MathExam.topic = new StringBuffer[] {new StringBuffer("(1) 19 × ( ( 89 + 88 ) - 23 )" + System.lineSeparator())};
		MathExam.standAnswer = new StringBuffer[] {new StringBuffer("(1) 19 × ( ( 89 + 88 ) - 23 ) = 2926")};
		

		MathExam.write("test.txt");
		file = new File("test.txt");
		assertEquals(true , file.exists() && file.isFile());

	}
	
	
	@Test 
	public void testMain() throws Exception {
		File file = new File("out.txt");
		if(file.exists() && file.isFile()) {
			file.delete();
		}
		
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    MathExam.errorMessage = "输入的参数形式有误，请按照 -n 题量 -grade 数量   或者 -grade 数量   -n 题量  格式输入。";
		MathExam.main(args);
		assertEquals(errorMessage + System.lineSeparator(),outContent.toString());
		assertEquals(expected , file.exists() && file.isFile());


	}
	
	
	



}
