import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Two_Numbers {
	String[] str_symbol = {"+","-","x","��"};
	int remainder = 0;
	int sum = 0;
	String word = "";
	List<String> Word_Set = new ArrayList<String>();

	/**
	 * ������������
	 * **/
	public int Calculate_Two_Numbers(int cal_number1, int cal_number2, int symbol, int grade) {
		int upper_limit = 100, two_number_sum = 0;
		if(grade == 3)
			upper_limit = 1000;
		if(str_symbol[symbol].equals("��") && cal_number2 == 0)
		{
			return -1;
		}
		else if(str_symbol[symbol].equals("+") && cal_number1 + cal_number2 <= upper_limit)
			two_number_sum = cal_number1 + cal_number2;
		else if(str_symbol[symbol].equals("-") && cal_number1 - cal_number2 >= 0)
			two_number_sum = cal_number1 - cal_number2;
		else if(str_symbol[symbol].equals("x") && cal_number1 * cal_number2 <= upper_limit)
			two_number_sum = cal_number1 * cal_number2;
		else if(str_symbol[symbol].equals("��") && cal_number2 != 0)
		{	
			two_number_sum = cal_number1 / cal_number2;
			remainder = cal_number1 % cal_number2;
		}
		else
			return -1;
		if(two_number_sum < 0 || two_number_sum > 10000)
			return -1;
		return two_number_sum;	
	}
	

	/**
	 * �ж���ʽ�Ƿ��ظ���
	 * û�к���ʽ�ظ����ͽ�����ʽ�洢������true��
	 * ����ʽ�ظ��ͷ���false
	 * **/
	public String Judge_Repetition(int cal_number1, int cal_number2, String strSymbol) {
		String word = Integer.toString(cal_number1) + strSymbol + Integer.toString(cal_number2);
		if(Word_Set.contains(word))
		{
			return null;
		}
		else
		{
			Word_Set.add(word);
			if(strSymbol.equals("+") || strSymbol.equals("x"))
				Word_Set.add(Integer.toString(cal_number2) + strSymbol + Integer.toString(cal_number1));
			word = Integer.toString(cal_number1) + " " + strSymbol + " " + Integer.toString(cal_number2);
			return word;
		}
	}
	
	/**
		Сѧ���꼶�����������Ҫ�����£�
		�������2��4��
		���Լ�����
		��������Ľ�������и���--����Ҫ���沨������ʱ�����ж�
		���������������Ϊ0������������
		������0-1000���ڣ����˵�
		��Ȼ��Ϊһ�꼶�����꼶����Ĺ��ܻ���Ҫ����
		������ѯ�����꼶�����������������С��,���������ڵ�����ֻ������
	 * 
	 * **/
	public void Iteration(int grade) {
		Random ranNum = new Random();
		int cal_number1 = 0, cal_number2 = 0, symbol;
		if(grade == 1)
		{
			symbol = ranNum.nextInt(2);
			cal_number1 = ranNum.nextInt(101);
			cal_number2 = ranNum.nextInt(101);
			if((sum = Calculate_Two_Numbers(cal_number1, cal_number2, symbol, grade)) == -1)
				Iteration(grade);
			word = Integer.toString(cal_number1) + " " + str_symbol[symbol] + " " + Integer.toString(cal_number2);
			if((word = Judge_Repetition(cal_number1, cal_number2, str_symbol[symbol])) == null)
			{
				Iteration(grade);
			}
			else
				return ;
		}	
		else if(grade == 2)
		{	
			symbol = ranNum.nextInt(2) + 2;
			cal_number1 = ranNum.nextInt(101);
			cal_number2 = ranNum.nextInt(101);
			if(str_symbol[symbol].equals("��"))
				cal_number2 = ranNum.nextInt(100) + 1;
			if((sum = Calculate_Two_Numbers(cal_number1, cal_number2, symbol, grade)) == -1)
				Iteration(grade);
			if(str_symbol[symbol].equals("��"))
				remainder = getRemainder();
			word = Integer.toString(cal_number1)+" "+str_symbol[symbol]+" "+Integer.toString(cal_number2);
			if((word = Judge_Repetition(cal_number1, cal_number2, str_symbol[symbol])) == null)
			{
				Iteration(grade);
			}
			else
				return ;
		}
		else if(grade == 3)
		{		
			cal_number1 = ranNum.nextInt(1001);
			int ran_symbol_num = ranNum.nextInt(3)+2;
			word = Integer.toString(cal_number1);
			for(int j = 1;j <= ran_symbol_num;j++)
			{
				cal_number1 = cal_number2;
				cal_number2 = ranNum.nextInt(1001);
				symbol = ranNum.nextInt(4);
				int ran_left_parenthesis_num = ranNum.nextInt(2);
				if(Calculate_Two_Numbers(cal_number1, cal_number2, symbol, grade) == -1
					|| (str_symbol[symbol].equals("��") && getRemainder() != 0))
				{
					j--;
					continue;
				}
				else if(ran_left_parenthesis_num % 2 == 1 && j <= ran_symbol_num-1)
				{	
					if(j == 1)
					{
						word = "(" + word;
						symbol = ranNum.nextInt(2);
						cal_number2 = ranNum.nextInt(1001);
						word += str_symbol[symbol] + Integer.toString(cal_number2) + ")";
						symbol = ranNum.nextInt(2) + 2;
						cal_number2 = ranNum.nextInt(1001);
						word += str_symbol[symbol] + Integer.toString(cal_number2);
					}
					else if(j <= ran_symbol_num-1)
					{
						word += str_symbol[symbol] + "("+Integer.toString(cal_number2);
						if(symbol == 0)
							symbol = 1;
						else if(symbol == 1)
							symbol = ranNum.nextInt(2);
						else if(symbol == 2)
						{
							while(symbol == 2)
							{
								symbol = ranNum.nextInt(4);
							}
						}
						else if(symbol == 3)
							symbol = ranNum.nextInt(4);
						cal_number2 = ranNum.nextInt(1001);
						word += str_symbol[symbol] + Integer.toString(cal_number2) + ")";
					}
					j++;
					ran_left_parenthesis_num = 0;
					continue;
				}
				word += str_symbol[symbol] + Integer.toString(cal_number2);
			}
			Calculation calculation = new Calculation(word, Word_Set);
			calculation.Infix_Expression_To_Word(calculation.getInffix_expression());
			calculation.To_Suffix_Expression(calculation.getInffix_expression());
			if(calculation.Suffix_Expression_Summation(calculation.getSuffix_expression()))
			{				
				word = calculation.getword();
				sum = calculation.getSum();
				return ;
			}
			else
				Iteration(grade);
		}
	}
	
	/**
	 * @param word_Set Ҫ���õ� word_Set
	 * @return 
	 */
	public void setWord_Set(List<String> word_Set) {
		this.Word_Set = word_Set;
	}
	
	/**
	 * @return word_Set
	 */
	public List<String> getWord_Set() {
		return Word_Set;
	}

	/**
	 * @return sum
	 */
	public int getSum() {
		return sum;
	}

	
	/**
	 * @return word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param Remainder Ҫ���õ� remainder
	 */
	public void setRemainder(int Remainder) {
		this.remainder = Remainder;
	}

	/**
	 * @return remainder
	 */
	public int getRemainder() {
		return remainder;
	}
	
}
