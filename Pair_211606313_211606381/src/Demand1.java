
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
	public void operandGeneration() {
		// TODO 自动生成的方法存根

	}

}
