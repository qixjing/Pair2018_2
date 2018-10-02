
public class Demand1 implements IDemand {

	@Override
	public Character[] operatorGeneration() {
		// 确定操作数和操作符的总个数
		int n=3;
		Character[] infixExpression = new Character[n];
		// 生成运算符
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
			//确保小学1年级题目为两位数加减整十数和两位数加减一位数
			if(num[0]>10 && num[1] >10 && num[0]%10 != 0 && num[1]%10 !=0) {
				num[1] = num[1]/10*10;
			}
			break;
		case '-':		
			// 确保第一个数比第二个数大，避免相减出现负数，小学加减无负数
			if (num[1] < num[0]) {
				int temp = num[0];
				num[0] = num[1];
				num[1] = temp;
			}
			//确保小学1年级题目为两位数加减整十数和两位数加减一位数
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
