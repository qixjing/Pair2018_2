import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class testMain {

	boolean expected=true;
	String test;
	
	@Parameters
	public static Collection<Object[]> t(){
		return Arrays.asList(new Object[][] {
								
			{true,"-n 1000 -grade 3"},
			{true,"-n 100 -grade 2"},
			{true,"-grade 1 -n 10"},
			{false,"100"},			
			{false,"-n 1.1 -grade 1"},
			{false,"-n a1 -grade 2"},			
			{true,"-n 1 -grade 1"}, 
			// �����з��ֵ�Ǳ��bug,��������inputTest�����У�
			// û�а�isError,errorMessage�ָ��ɳ�ʼ״̬��������·���ֵ��Ԥ�ڽ����ͬ
			// ����ǰ��������ж�Ϊ��false,��ôisError�ͻᱻ�޸�Ϊfalse,���ڶ���ʹ��ʱ��
			// ����´�����ȷ����������isError���ᱻ�޸ģ�������һֱΪfalse
			// ʹ���޷���������
		
			{false,"-grade 1 -n 002"},
			{false,"-grade 0.1 -n 0.1"},
			{false,"-n a1 -grade a1"},
			{false,"a a a a"},
			{false,"1 1 1 1"}
			
			
		});
	}
	
	public testMain(boolean expected,String test){
		this.expected=expected;
		this.test=test;
		
	}
	
	@Test
	public void result() {
		MathExam.main(test.split(" "));	
		assertEquals(expected, MathExam.isError);
	}

}
