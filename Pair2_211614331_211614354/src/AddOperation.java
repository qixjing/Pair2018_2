

import java.util.ArrayList;
import java.util.Random;

//�ӷ�����
public class AddOperation extends Operation{
	
	
	public AddOperation(ArrayList<String> answerSet,int testNumber) {
		Random rand = new Random();
		firstNumber=rand.nextInt(100);
		if((firstNumber/10)>0) //����λ��  (���ݽ�ѧ��٣���λ��ֻ�ܼӸ�λ��������ʮ��)
		{
			do {
				secondNumber=rand.nextInt(10);        //�����λ��
				int n=0;
				n=rand.nextInt(2);         //�ж��Ƿ���Ҫ�����ʮ��   0:����  1����
				if(n==0) 
					answerNumber=firstNumber+secondNumber;
				else 
				{
					secondNumber=secondNumber*10;
					answerNumber=firstNumber+secondNumber;
				}
				
				
			}while(answerNumber>=100);
		}
		else {                 //��һλ��
			do {
				secondNumber=rand.nextInt(100);
				answerNumber=firstNumber+secondNumber;
				}while(answerNumber>=100);
			

		}
		answerSet.add("("+testNumber+")"+" "+firstNumber+" + "+secondNumber);//�ѽ������ļ�����
		answerSet.add(" = "+answerNumber); //�Ѵ������������
		
	}
	
	
	
}
