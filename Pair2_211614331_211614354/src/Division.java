import java.util.ArrayList;
import java.util.Random;

//��������
//���꼶������������һλ������Ҳ��һλ�����������ĳ��������ڳ���
public class Division extends Operation{

	public Division(ArrayList<String> answerSet,int testNumber) {
		Random rand=new Random();
		
		do {
			
		firstNumber=rand.nextInt(99)+1;   //������
		secondNumber=rand.nextInt(9)+1;      //����
		remainder=firstNumber%secondNumber;   //����
		answerNumber=firstNumber/secondNumber; //��
				
		}while(answerNumber>=10 || firstNumber<secondNumber);
		
		if(remainder==0) {
			answerSet.add("("+testNumber+")"+" "+firstNumber+" �� "+secondNumber);//�ѽ������ļ�����
			answerSet.add(" = "+answerNumber); //�Ѵ������������
		}
		else {
			answerSet.add("("+testNumber+")"+" "+firstNumber+" �� "+secondNumber);//�ѽ������ļ�����
			answerSet.add(" = "+answerNumber+"..."+remainder); //�Ѵ������������
		}
		
		
		
	}
}

