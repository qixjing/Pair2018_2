
public interface IDemand {
	char[] Operator = { '+', '-', '¡Á', '¡Â' };
	public Character[] operatorGeneration();
	public boolean operandGeneration(Object num1,Object num2,char symbol,int[] num)throws ArithmeticException;
	public boolean isRemainder();
}
