import static org.junit.Assert.*;

import org.junit.Test;

public class tastPartGrade3 {

	@Test
	public void test() {
		RandomQuestions rQ=new RandomQuestions();
		ArithemticS aR=new ArithemticS();
		
		String problem=rQ.questionsShop(); //�������������󣬷�����Ŀ
		boolean isRight=aR.startProblem(problem); // ������Ŀ�������������
		
		if(aR.answerNumber>=9999) {
			isRight=false;						//�����Ʋ�����9999(���꼶Ҫ��)
		}
		if(isRight)
			System.out.println("�����꼶����������ԣ�"+problem+" = "+aR.answerNumber);// �жϳ���������Ƿ�����꼶�淶
		else
			System.out.println("��ǰ��Ŀ���󣡣����������꼶Ҫ��"+problem+" = "+aR.answerNumber);
	}

}
