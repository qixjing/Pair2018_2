import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat.Field;

import org.junit.Test;

public class MathExamTest {

	@Test
	public void testChoose_g1() throws IOException {
		String[] bbb= {"-n","10","-grade","1"};
		assertEquals(1, new MathExam().Choose(bbb));
		
	}
	@Test
	public void testChoose_g2() throws IOException {
		String[] bbb= {"-n","10","-grade","2"};
		assertEquals(2,new MathExam().Choose(bbb));
		
	}
	@Test
	public void testChoose_g3() throws IOException {
		String[] bbb= {"-n","10","-grade","3"};
		assertEquals(3, new MathExam().Choose(bbb));
		
	}
	@Test
	public void testGrade1() {
		int n = 10;
		new MathExam().Grade1(n);
		System.out.println(new MathExam().Grade1(n));
		assertEquals(10, new MathExam().Grade1(n));
	}

	@Test
	public void testGrade2() {
		int n = 10;
		new MathExam().Grade1(n);
		assertEquals(10, new MathExam().Grade2(n));
	}

	@Test
	public void testGrade3() {
		int n = 10;
		new MathExam().Grade1(n);
		assertEquals(10, new MathExam().Grade3(n));
	}

	@Test
	public void testFuhao_0(){
		int i = 0;
		assertEquals("+",new MathExam().fuhao(i));
	}
	@Test
	public void testFuhao_1(){
		int i = 1;
		assertEquals("-",new MathExam().fuhao(i));
	}
	@Test
	public void testFuhao_2(){
		int i = 2;
		assertEquals("*",new MathExam().fuhao(i) );
	}
	@Test
	public void testFuhao_3(){
		int i = 3;
		assertEquals("/",new MathExam().fuhao(i) );
	}
	@Test
	public void testFuhao_4(){
		int i = 4;
		assertEquals(null,new MathExam().fuhao(i) );
	}

	@Test
	public void testNumber() {
		assertEquals(3, new MathExam().number(3));
	}

	@Test
	public void testStringTransferChar() {
		String str ="1+2-3";
		char[] c = new char[5];
		c[0] = '1';
		c[1] = '+';
		c[2] = '2';
		c[3] = '-';
		c[4] = '3';
		assertArrayEquals(c,new MathExam().stringTransferChar(str));
	}

	@Test
	public void testGetPoland() {
		String str1 ="5*(3+4)";
		String[] str3 = {"*","+","4","3","5"};
		assertArrayEquals(str3,new MathExam().getPoland(str1));
	}

	@Test
	public void testGetResUsePoland() {
		String[] str3 = {"*","+","4","3","5"};
		String str4 ="35";
		assertEquals(str4, new MathExam().getResUsePoland(str3));
	}

	@Test
	public void testIsNum() {
		assertEquals(true, new MathExam().isNum("1"));
	}

	@Test
	public void testWrite() throws IOException {
		File file =new File("F:\\cyj\\Software\\src\\out.txt");
		String[] aaa= {"-n","10","-grade","1"};
		//new Text01(aaa).Write();
		assertEquals(true, file.exists());
	}


}
