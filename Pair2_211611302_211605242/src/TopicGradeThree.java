

import java.util.Random;

public class TopicGradeThree {
	private int SymbolNumber = 0; // 运算符的个数
	private StringBuffer topic;
	private Random random = new Random();
	private int a1, a2, a3, a4;

	public StringBuffer createTopic() {
		topic = new StringBuffer("");
		// 运算符的个数取值范围为[2,3]
		SymbolNumber = Topic.createRandomNumber(2, 3);
		if(random.nextBoolean()){
			if(random.nextBoolean()){
				// a1取值为30到40 ，a2 
				a1 = Topic.createRandomNumber(30, 40);
				a2 = Topic.createRandomNumber(10, 30);
				// 运算符号1和2，只生成 “加”或“乘”
				String symbol = randomSymbol("+*");
				a3 = Topic.createRandomNumber(10, 30);
				String symbol2 = randomSymbol("+*");
				topic.append(a3 + " " + symbol2 + " ( " + a1 + " " + symbol + " " + a2 + " )");
				if (SymbolNumber==2)
					return topic;
				else if(SymbolNumber==3){
					String symbol3;
					// 运算符号3，只生成 “减”或“加”
					symbol3 = randomSymbol("-+");
					a4 = Topic.createRandomNumber(30, a1+a2+a3);
					topic.append(" " + symbol3 + " " + a4);
					return topic;
				}
			}
			else{
				/*
				 * 题目形式为： 
				 * (1) (a1-a2)/a3=a4
				 * (2) (a1-a2)/a3 [*-] a4;
				 * 
				*/
				String symbol1 ="-";
				a3 = Topic.createRandomNumber(5, 20);
				a4 = Topic.createRandomNumber(10, 20);
				a2 = Topic.createRandomNumber(20, 100);
				a1 = a3 * a4 + a2;
				String symbol2 = "/";
				topic.append("( " + a1 + " " + symbol1 + " " + a2 + " )" + " " + symbol2 + " " + a3);
				if (SymbolNumber==2)
					return topic;
				else{
					String symbol3 = randomSymbol("*-");
					if(symbol3.equals("-")){
						int asum = (a1-a2)/a3;
						int a4 = Topic.createRandomNumber(1, asum);
						topic.append(" " + symbol3 + " " + a4);
					}
					else{
						int a4 = Topic.createRandomNumber(2, 10);
						topic.append(" " + symbol3 + " " + a4);
					}
					return topic;
				}
			}
		}
		else{
			a1 = Topic.createRandomNumber(25, 50);
			String s [] = {"+","*"};
			String symbol1 = s[Topic.createRandomNumber(0, 1)];
			a2 = Topic.createRandomNumber(5, 10);
			a3 = Topic.createRandomNumber(5, 10);
			String symbol2 = s[Topic.createRandomNumber(0, 1)];
			topic.append(a1 + " " + symbol1 + " " + a2 +  " " + symbol2 + " " + a3);
			return topic;
		}
		return topic;
	}

	private String randomSymbol(String symbol) {
		if (symbol.equals("+*")) {
			if (random.nextBoolean()) {
				return "*";
			} else
				return "+";
		}
		else if (symbol.equals("-+")) {
			if (random.nextBoolean()) {
				return "-";
			} else
				return "+";
		}
		else if (symbol.equals("*-")) {
			if (random.nextBoolean()) {
				return "-";
			} else
				return "*";
		}
		return null;
	}
}
