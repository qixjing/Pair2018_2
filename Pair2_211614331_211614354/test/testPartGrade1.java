import java.util.ArrayList;

import org.junit.Test;


public class testPartGrade1 {
	// ����һ�꼶�Ƿ�ͨ��Ҫ��

	@Test // �����Ƿ�����������һ��һ�꼶�ӷ�����ʽ��
	public void testAddOperation() { 
		ArrayList<String> answerSet= new ArrayList<>();
		@SuppressWarnings("unused")
		AddOperation add=new AddOperation(answerSet, 3);
		System.out.print("һ�꼶�ӷ���");
		System.out.println(answerSet);
	}
	
	@Test // �����ܷ���������һ��һ�꼶��������ʽ��
	public void testSubtraction() { 
		ArrayList<String> answerSet= new ArrayList<>();
		@SuppressWarnings("unused")
		Subtraction sub=new Subtraction(answerSet, 6);
		System.out.print("һ�꼶������");
		System.out.println(answerSet);
	}

}
