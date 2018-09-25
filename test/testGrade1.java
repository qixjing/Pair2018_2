import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class testGrade1 {
	// 测试一年级是否通过要求

	@Test // 测试是否能正常产生一个一年级加法运算式子
	void testAddOperation() { 
		ArrayList<String> answerSet= new ArrayList<>();
		AddOperation add=new AddOperation(answerSet, 3);
		System.out.print("一年级加法：");
		System.out.println(answerSet);
	}
	
	@Test // 测试能否正常产生一个一年级减法运算式子
	void testSubtraction() { 
		ArrayList<String> answerSet= new ArrayList<>();
		Subtraction sub=new Subtraction(answerSet, 6);
		System.out.print("一年级减法：");
		System.out.println(answerSet);
	}

}
