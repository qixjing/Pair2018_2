
public class Demand3 implements IDemand {

	@Override
	public Character[] operatorGeneration() {
		// 确定操作数和操作符的总个数
		int n=5+(int)(Math.random()*3);
		if(n%2 == 0) n++;
		
		Character[] infixExpression = new Character[n];
		
		// 生成(n-1)/2个操作符，保证至少有两个不同的操作符
		char c ;
		infixExpression[n-1] = c = Operator[(int)(Math.random()*4)];
		for(int j = 1,k = n-2;j < (n-1)/2;j++,k--) {
			infixExpression[k] = Operator[(int)(Math.random()*4)];
			c ^= infixExpression[k].charValue();
		}
		
		if(c == 0) {
			int q = (int)(Math.random()*4);
			infixExpression[n-1] = Operator[q] != infixExpression[n-1].charValue() ? Operator[q] : Operator[(q+1)%4];
		}
		return infixExpression;
	}

	@Override
	public boolean operandGeneration(Object num1,Object num2,char symbol,int[] num)throws ArithmeticException {
		
		if(num1 instanceof Character) {
			num[0] = 1 + (int) (Math.random() * 100);
		}else {
			num[0] = (int)num1;
		}
		if(num2 instanceof Character) {
			num[1] = 1 + (int) (Math.random() * 100);
		}else {
			num[1] = (int)num2;
		}
		 
		switch(symbol)
		{
			case '-':
				if(num[1] < num[0] ) {
					int temp = num[1];
					num[1] = num[0];
					num[0] = temp;
					if(!(num2 instanceof Character && num1 instanceof Character))
						return true;
				}
				break;
			case '÷':
				if(num[1] % num[0] != 0 || num[0] == 0) {
					throw new ArithmeticException();
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
