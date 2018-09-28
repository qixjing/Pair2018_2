import java.util.ArrayList;

import org.junit.Test;


public class testPartGrade1 {
	// 测试一年级是否通过要求

	@Test // 测试是否能正常产生一个一年级加法运算式子
	public void testAddOperation() { 
		ArrayList<String> answerSet= new ArrayList<>();
		@SuppressWarnings("unused")
		AddOperation add=new AddOperation(answerSet, 3);
		System.out.print("一年级加法：");
		System.out.println(answerSet);
	}
	
	@Test // 测试能否正常产生一个一年级减法运算式子
	public void testSubtraction() { 
		ArrayList<String> answerSet= new ArrayList<>();
		@SuppressWarnings("unused")
		Subtraction sub=new Subtraction(answerSet, 6);
		System.out.print("一年级减法：");
		System.out.println(answerSet);
	}

}
