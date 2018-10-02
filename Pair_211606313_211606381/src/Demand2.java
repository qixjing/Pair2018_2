
public class Demand2 implements IDemand {

	@Override
	public Character[] operatorGeneration() {
		// 确定操作数和操作符的总个数
		int n=3;
		Character[] infixExpression = new Character[n];
		// 生成运算符
		int index =  2 + (((int) (Math.random() * 7))%2);
		infixExpression[n-1] = Operator[index];
		return infixExpression;
	}

	@Override
	public boolean operandGeneration(Object num1,Object num2,char symbol,int[] num)throws ArithmeticException {

		num[0] = (int) (Math.random() * 100);

		num[1] = (int) (Math.random() * 100);


		switch (symbol) {
		case '×':
			//确保为表内乘法
			num[0] %= 10;
			num[1] %= 10;
			break;
		case '÷':
			//防止除数为0
			while(0 == num[0]) {
				num[0] = (int) (Math.random() * 100);
			}
			
			//确保为表内除法
			if(num[0]>10) {
				num[0] /=10 ;
			}
			break;
		}
		return false;

	}
	
	public boolean isRemainder() {
		return true;
	}

}
