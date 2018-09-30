
public class TaskMake {

	//����һ�꼶��Ŀ
	public String MakeGradeOne(int num) {
		//�ļ�������
		File file = new File();
		//���ȳ��㷨��
		ShuntingYard calc = new ShuntingYard();
		String Question = "";
		String Answer = "";
		String Expression = "";
		for (int i = 0; i < num; i++) {
			int Temp = Random(1, 100);
			if (Random()) {
				// ���ʽ
				Expression = GetExpression(Temp, Random(1, 100 - Temp), "+");
				// System.out.println(Expression);
				// ��Ŀ
				Question = Question + GetNumber(i) + Expression + "\r\n";
				// ��Ŀ�ʹ�
				Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression, 1) + "\r\n";
				
			} else {
				Expression = GetExpression(Temp, Random(1, Temp), "-");
				// System.out.println(Expression);
				Question = Question + GetNumber(i) + Expression + "\r\n";
				Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression, 1) + "\r\n";
			}
		}
		System.out.println(Question + "\r\n" + Answer);
		file.Write("out.txt", Question + "\r\n" + Answer);
		return Question;
	}
	
	//���ɶ��꼶��Ŀ
	public String MakeGradeTwo(int num){
		File file = new File();
		ShuntingYard calc = new ShuntingYard();
		String Question = "";
		String Answer = "";
		String Expression = "";
		for (int i = 0; i < num; i++) {
			int mul1 = Random(1, 10);
			if (Random()) {
				Expression = GetExpression(mul1, Random(1, 10), "��");
				Question = Question + GetNumber(i) + Expression + "\r\n";
				Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression, 2) + "\r\n";
			} else {
				int roo;
				// ���ݷ�Χ��Ҫע��
				int mul2 = Random(1, mul1);
				roo = mul1 % mul2;
				if (roo == 0) {
					Expression = GetExpression(mul1, mul2, "��");
					Question = Question + GetNumber(i) + Expression + "\r\n";
					Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression, 2) + "\r\n";
				} else {
					Expression = GetExpression(mul1, mul2, "��");
					Question = Question + GetNumber(i) + Expression + "\r\n";
					Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression, 2) + "..." + String.valueOf(roo) + "\r\n";
				}
			}
		}
		System.out.println(Question + "\r\n" + Answer);
		file.Write("out.txt", Question + "\r\n" + Answer);
		return Question;
	}
	
	//�������꼶��Ŀ
	public String MakeGradeThree(int num) {
		File file = new File();
		ShuntingYard calc = new ShuntingYard();
		String Question = "";
		String Answer = "";
		String Expression = "";
		for(int i = 0; i < num; i++) {
			int OperatorNumber = Random(2, 4);
			Expression = String.valueOf(Random(1, 100));
			boolean ReMake = false;
			while(CheckOperator(Expression) == false || ReMake == true) {
				//��������
				if(ReMake == true) {
					Expression = String.valueOf(Random(1, 100));
					ReMake = false;
				}
				for(int j = 0; j < OperatorNumber; j++) {
					String TempOperator = RandomOperator();
					int TempRandom = Random(1, 100);
					
					//�������ܳ��ָ��� ��������������
					if(Integer.valueOf(calc.Calc(Expression, 3)) < 0) {
						ReMake=true;
						break;
					}
					//�������
					if(Random() == true && j != OperatorNumber - 1 && OperatorNumber != 2) {
						Expression = "( " + Expression + " " + TempOperator + " " + String.valueOf(TempRandom) + " )";
					}else {
						Expression = Expression + " " + TempOperator + " " + String.valueOf(TempRandom);
					}
					//�������ܳ��ָ��� ��������������
					if(Integer.valueOf(calc.Calc(Expression, 3)) < 0) {
						ReMake=true;
						break;
					}
				}
				
				
			}

			Question = Question + GetNumber(i) + Expression + "\r\n";
			Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression, 3) + "\r\n";
		}
		System.out.println(Question + "\r\n" + Answer);
		file.Write("out.txt", Question + "\r\n" + Answer);
		return Question;
	}
	
	//��������
	public boolean CheckOperator(String Expression) {
		char[] Array = Expression.toCharArray();
		char Operator = '0';
		for(int i = 0; i < Expression.length(); i++) {
			if(Array[i] == '+' || Array[i] == '-' || Array[i] == '��' || Array[i] == '��') {
				if(Operator == '0') {
					Operator = Array[i];
				}else {
					if(Operator != Array[i]) {
						return true;
					}
				}
				
			}
		}
		if(Operator == '0') {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	// ȡ��ʽ
	// ��:1 + 2
	public String GetExpression(int one, int two, String Operator) {
		return String.valueOf(one) + " " + Operator + " " + String.valueOf(two);
	}
	
	// ȡ���
	// ��:(1) 
	// ע:�пո�
	public String GetNumber(int n) {
		return "("+String.valueOf(n+1)+") ";
	}
	
	// ȡ�������
	public String RandomOperator() {
        switch(Random(0, 3)){
        case 0:{
        	return "+";
        }
        case 1:{
        	return "-";
        }
        case 2:{
        	return "��";
        }
        case 3:{
        	return "��";
        }
        }
        return "";
	}
	
	// ȡ�����
	public int Random(int Min, int Max) {
		return (int) (Min + Math.random() * (Max - Min + 1));
	}
	
	// ȡ����߼�ֵ
	public boolean Random() {
		if((int) (0 + Math.random() * (1 - 0 + 1)) == 0) {
			return true;
		}else {
			return false;
		}
	}
	

}
