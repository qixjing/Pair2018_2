import java.util.ArrayList;

import org.junit.Test;

public class testPartGrade2 {

	@Test // ���Զ��꼶�˷����ܷ��������Ҫ��Ķ��꼶�˷���Ŀ
	public void testMultiplication() {
		ArrayList<String> answerSet= new ArrayList<>();
		@SuppressWarnings("unused")
		Multiplication mul=new Multiplication(answerSet, 3);
		System.out.print("���꼶�˷���");
		System.out.println(answerSet);
	}
	

	@Test // ���Զ��꼶�˷����ܷ��������Ҫ��Ķ��꼶������Ŀ
	public void testDivision() {
		ArrayList<String> answerSet= new ArrayList<>();
		@SuppressWarnings("unused")
		Division div=new Division(answerSet, 5);
		System.out.print("���꼶������");
		System.out.println(answerSet);
	}

}
