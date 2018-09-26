public class Two_Numbers {
	static String[] str_symbol = {"+","-","x","÷"};
	static int remainder = 0;
	/**
	 * 计算两个数字
	 * **/
	public int Calculate_Two_Numbers(int cal_number1,int cal_number2,int symbol,int grade) {
		int upper_limit = 100, sum = 0;
		remainder = 0;
		if(grade == 3)
			upper_limit = 1000;
		if(str_symbol[symbol].equals("+") && cal_number1 + cal_number2 <= upper_limit)
			sum = cal_number1 + cal_number2;
		else if(str_symbol[symbol].equals("-")&& cal_number1 - cal_number2 >= 0)
			sum = cal_number1 - cal_number2;
		else if(str_symbol[symbol].equals("x") && cal_number1 * cal_number2 <= upper_limit)
			sum = cal_number1 * cal_number2;
		else if(str_symbol[symbol].equals("÷") && cal_number2 != 0)
		{	
			sum = cal_number1 / cal_number2;
			remainder = cal_number1 % cal_number2;
		}
		else if(sum < 0 || sum > 10000)
			return -1;
		else
			return -1;
		return sum;	
	}
	/**
	 * @return remainder
	 */
	public int getRemainder() {
		return remainder;
	}
	
}
