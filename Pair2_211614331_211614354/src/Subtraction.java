import java.util.ArrayList;
import java.util.Random;

//��������
public class Subtraction extends Operation {
	
	public Subtraction(ArrayList<String> answerSet,int testNumber) {
		
			Random rand = new Random();
			firstNumber=rand.nextInt(100);
			if((firstNumber/10)>0)   //����λ��  (���ݽ�ѧ��٣���λ��ֻ�ܼ���λ��������ʮ��)
			{
				do {
					secondNumber=rand.nextInt(10);        //�����λ��
					int n=0;
					n=rand.nextInt(2);         //�ж��Ƿ���Ҫ�����ʮ��   0:����  1����
					if(n==0) 
						answerNumber=firstNumber-secondNumber;
					else 
					{
						secondNumber=secondNumber*10;
						answerNumber=firstNumber-secondNumber;
					}
								
				}while(answerNumber<0);
			}
			else {                 //��һλ��
					int n;
					secondNumber=rand.nextInt(9);
					if(secondNumber>firstNumber) // bug�޸�������ڶ������ȵ�һ�����󣬾ͽ���λ��
					{
						n=firstNumber;
						firstNumber=secondNumber;
						secondNumber=n;
						
					}
					answerNumber=firstNumber-secondNumber;

			}
			answerSet.add("("+testNumber+")"+" "+firstNumber+" - "+secondNumber);//�ѽ������ļ�����
			answerSet.add(" = "+answerNumber); //�Ѵ������������
			
		
	}
}
