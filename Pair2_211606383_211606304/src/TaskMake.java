
public class TaskMake {

	//生成一年级题目
	public String MakeGradeOne(int num) {
		//文件操作类
		File file = new File();
		//调度场算法类
		ShuntingYard calc = new ShuntingYard();
		String Question = "";
		String Answer = "";
		String Expression = "";
		for (int i = 0; i < num; i++) {
			int Temp = Random(1, 100);
			if (Random()) {
				// 表达式
				Expression = GetExpression(Temp, Random(1, 100 - Temp), "+");
				// System.out.println(Expression);
				// 题目
				Question = Question + GetNumber(i) + Expression + "\r\n";
				// 题目和答案
				Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression) + "\r\n";
				
			} else {
				Expression = GetExpression(Temp, Random(1, Temp), "-");
				// System.out.println(Expression);
				Question = Question + GetNumber(i) + Expression + "\r\n";
				Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression) + "\r\n";
			}
		}
		System.out.println(Question + "\r\n" + Answer);
		file.Write("out.txt", Question + "\r\n" + Answer);
		return Question;
	}
	
	//生成二年级题目
	public String MakeGradeTwo(int num){
		File file = new File();
		ShuntingYard calc = new ShuntingYard();
		String Question = "";
		String Answer = "";
		String Expression = "";
		for (int i = 0; i < num; i++) {
			int mul1 = Random(1, 10);
			if (Random()) {
				Expression = GetExpression(mul1, Random(1, 10), "×");
				Question = Question + GetNumber(i) + Expression + "\r\n";
				Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression) + "\r\n";
			} else {
				int roo;
				// 数据范围需要注意
				int mul2 = Random(1, mul1);
				roo = mul1 % mul2;
				if (roo == 0) {
					Expression = GetExpression(mul1, mul2, "÷");
					Question = Question + GetNumber(i) + Expression + "\r\n";
					Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression) + "\r\n";
				} else {
					Expression = GetExpression(mul1, mul2, "÷");
					Question = Question + GetNumber(i) + Expression + "\r\n";
					Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression) + "..." + String.valueOf(roo) + "\r\n";
				}
			}
		}
		System.out.println(Question + "\r\n" + Answer);
		file.Write("out.txt", Question + "\r\n" + Answer);
		return Question;
	}
	
	//生成三年级题目
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
				//重新生成
				if(ReMake == true) {
					Expression = String.valueOf(Random(1, 100));
					ReMake = false;
				}
				for(int j = 0; j < OperatorNumber; j++) {
					String TempOperator = RandomOperator();
					int TempRandom = Random(1, 100);
					
					//减法不能出现负数 除法不能有余数
					if(Integer.valueOf(calc.Calc(Expression)) < 0) {
						ReMake=true;
						break;
					}
					//随机括号
					if(Random() == true && j != OperatorNumber - 1 && OperatorNumber != 2) {
						Expression = "( " + Expression + " " + TempOperator + " " + String.valueOf(TempRandom) + " )";
					}else {
						Expression = Expression + " " + TempOperator + " " + String.valueOf(TempRandom);
					}
					//减法不能出现负数 除法不能有余数
					if(Integer.valueOf(calc.Calc(Expression)) < 0) {
						ReMake=true;
						break;
					}
				}
				
				
			}

			Question = Question + GetNumber(i) + Expression + "\r\n";
			Answer = Answer + GetNumber(i) + Expression + " = " + calc.Calc(Expression) + "\r\n";
		}
		System.out.println(Question + "\r\n" + Answer);
		file.Write("out.txt", Question + "\r\n" + Answer);
		return Question;
	}
	
	//检查运算符
	public boolean CheckOperator(String Expression) {
		char[] Array = Expression.toCharArray();
		char Operator = '0';
		for(int i = 0; i < Expression.length(); i++) {
			if(Array[i] == '+' || Array[i] == '-' || Array[i] == '×' || Array[i] == '÷') {
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
	
	
	// 取算式
	// 如:1 + 2
	public String GetExpression(int one, int two, String Operator) {
		return String.valueOf(one) + " " + Operator + " " + String.valueOf(two);
	}
	
	// 取序号
	// 如:(1) 
	// 注:有空格
	public String GetNumber(int n) {
		return "("+String.valueOf(n+1)+") ";
	}
	
	// 取随机符号
	public String RandomOperator() {
        switch(Random(0, 3)){
        case 0:{
        	return "+";
        }
        case 1:{
        	return "-";
        }
        case 2:{
        	return "×";
        }
        case 3:{
        	return "÷";
        }
        }
        return "";
	}
	
	// 取随机数
	public int Random(int Min, int Max) {
		return (int) (Min + Math.random() * (Max - Min + 1));
	}
	
	// 取随机逻辑值
	public boolean Random() {
		if((int) (0 + Math.random() * (1 - 0 + 1)) == 0) {
			return true;
		}else {
			return false;
		}
	}
	

}
