import java.util.ArrayList;
import java.util.Random;

//�˷�������
//���꼶���ڳ˷�
public class Multiplication extends Operation{

	public  Multiplication(ArrayList<String> answerSet,int testNumber) {
		
		Random rand=new Random(); //���������
		
		firstNumber=rand.nextInt(9)+1;//�����������ΧΪ[0-9)�����������+1���ܱ������0��10�����
		secondNumber=rand.nextInt(9)+1;//��������ڶ����������ڳ˷�
		answerNumber=firstNumber*secondNumber;
		answerSet.add("("+testNumber+")"+" "+firstNumber+" �� "+secondNumber);//�ѽ������ļ�����
		answerSet.add(" = "+answerNumber); //�Ѵ������������
	
	}
	
}
