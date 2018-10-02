import java.util.ArrayList;

import org.junit.Test;

public class testPartGrade2 {

	@Test // 测试二年级乘法，能否产生符合要求的二年级乘法题目
	public void testMultiplication() {
		ArrayList<String> answerSet= new ArrayList<>();
		@SuppressWarnings("unused")
		Multiplication mul=new Multiplication(answerSet, 3);
		System.out.print("二年级乘法：");
		System.out.println(answerSet);
	}
	

	@Test // 测试二年级乘法，能否产生符合要求的二年级除法题目
	public void testDivision() {
		ArrayList<String> answerSet= new ArrayList<>();
		@SuppressWarnings("unused")
		Division div=new Division(answerSet, 5);
		System.out.print("二年级除法：");
		System.out.println(answerSet);
	}

}
