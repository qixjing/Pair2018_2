
public class Demand1 implements IDemand {

	@Override
	public Character[] operatorGeneration() {
		// ȷ���������Ͳ��������ܸ���
		int n=3;
		Character[] infixExpression = new Character[n];
		// ���������
		int index = (((int) (Math.random() * 10)) % 2);
		infixExpression[n-1] = Operator[index];
		return infixExpression;
	}

	@Override
	public boolean operandGeneration(Object num1,Object num2,char symbol,int[] num) throws ArithmeticException{

		num[0] = (int) (Math.random() * 100);
		num[1] = (int) (Math.random() * 100);
		
		while('+' == symbol && num[0] + num[1] >= 100) {
			num[0] = (int) (Math.random() * 100);
			num[1] = (int) (Math.random() * 100);
		}
		
		switch (symbol) {
		case '+':
			//ȷ��Сѧ1�꼶��ĿΪ��λ���Ӽ���ʮ������λ���Ӽ�һλ��
			if(num[0]>10 && num[1] >10 && num[0]%10 != 0 && num[1]%10 !=0) {
				num[1] = num[1]/10*10;
			}
			break;
		case '-':		
			// ȷ����һ�����ȵڶ������󣬱���������ָ�����Сѧ�Ӽ��޸���
			if (num[1] < num[0]) {
				int temp = num[0];
				num[0] = num[1];
				num[1] = temp;
			}
			//ȷ��Сѧ1�꼶��ĿΪ��λ���Ӽ���ʮ������λ���Ӽ�һλ��
			if(num[0]>10 && num[0] >10 && num[0]%10 !=0) {
				num[1] = num[1]/10*10;
			}
			break;
		}
		return false;

	}

	@Override
	public boolean isRemainder() {
		return false;
	}

}
