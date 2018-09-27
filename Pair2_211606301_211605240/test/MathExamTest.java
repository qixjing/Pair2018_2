import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MathExamTest {
	int expected=0;
	int sum;
	int answer=0;
	boolean expected1=true;
	
	static String[] args = new String[10];
	static int[] str = new int[3];
	static int[] sub = new int[6];
	static Collection<String> list=new HashSet<String>();
	
	MathExam math = new MathExam();
	
	@Parameters
	public static Collection<Object[]> t(){
		int[] str1 = new int[]{1,2,9,4,4,6};
		int[] str2 = new int[]{6,2,1,4,3};
		int[] str3 = new int[]{6,4,9,2,4,1};
		String[] in = new String[] {"-n","10","-grade","2"};
		String[] in1 = new String[] {"-grade","3","-n","10"};
		String[] in2 = new String[] {"-n","10000","-grade","1"};
		String[] in3 = new String[] {"-n","10","-grade"};
		String[] in4 = new String[] {"-n","10","-grade","10"};
		String[] in5 = new String[] {"-n","asdas","-grade","dfdf"};
		return Arrays.asList(new Object[][]{
			{1,in,str,true,str1,5,list},
			{1,in1,str,false,str1,5,list},
			{0,in2,str,true,str1,6,list},
			{0,in3,str,true,str2,5,list},
			{0,in4,str,false,str3,5,list},
			{0,in5,str,false,str3,6,list}
		});
		}
	
	public MathExamTest(int expected,String[]str2,int[] str,boolean expected1,int[]str1,int answer,Collection<String> list) {
		this.expected=expected;
		this.args=str2;
		this.str=str;
		this.expected1=expected1;
		this.sub=str1;
		this.answer=answer;
		this.list=list;
	}
	//
	@Test
	public void testInput() {
		assertEquals(expected,math.input(args,str)[0]);
	}
	@Test
	public void testexamine() {
		assertEquals(expected1,math.examine(sub,answer,list));
	}
//	@Test
//	public void testAdd() {
//		assertEquals();
//
//}

}
	
	

//	@Test
//	void testExamine() {
//		fail("尚未实现");
//	}
//
//	@Test
//	void testMain() {
//		fail("尚未实现");
//	}

