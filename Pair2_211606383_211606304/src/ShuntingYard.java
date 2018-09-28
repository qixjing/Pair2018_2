import java.util.Stack;
//import java.util.EmptyStackException;
// Shunting Yard Algorithm(调度场算法)
public class ShuntingYard {
	
	 // 计算(中缀)表达式
	 public String Calc(String Expression) {
		 // 数字栈
         Stack<Integer> Numbers = new Stack<Integer>();
         // 符号(运算符)栈
         Stack<Character> Operators = new Stack<Character>();
         // 去除表达式中空格
         String TempExpression = Expression.replace(" ", "");
         // 保存高位数字(有问题,不能出现0)
         int HighNumber = 0;
         // 字符串表达式转化为字符数组
         char[] CharArray = TempExpression.toCharArray();
         // 遍历字符数组
         for (int i = 0; i < CharArray.length; i++) {
        	// 判断当前char是否为数字
            if (Character.isDigit(CharArray[i])) {
            	HighNumber = 10 * HighNumber + Integer.parseInt(String.valueOf(CharArray[i])); //临时保存大于10的数字
            } else {
                if (HighNumber != 0) {
                	// 数字入栈
                	Numbers.push(HighNumber);
                	// 清零
                	HighNumber = 0;
                 }
                // 括号处理
                if (CharArray[i] == '(') {
                	// 左括号入栈
                	Operators.push(CharArray[i]);
                } else if (CharArray[i] == ')') {
                	// 括号里面运算完(查看堆栈顶部的对象，但不从堆栈中移除)
                    while (Operators.peek() != '(') { 
                    	// 数字出栈，数字出栈，符号出栈，计算
                        int TempResult = TempCalc(Numbers.pop(), Numbers.pop(),Operators.pop());
                        // 计算结果入栈
                        Numbers.push(TempResult);
                    }
                    // 左括号出栈
                    Operators.pop();
                } else if (GetPriority(CharArray[i]) > 0) {
                	// 栈是否为空
                    if (Operators.isEmpty()) {
                    	// 符号入栈
                    	Operators.push(CharArray[i]);
                    } else {
                        // 若栈顶元素优先级大于或等于要入栈的元素,将栈顶元素弹出并计算,然后入栈
                        if (GetPriority(Operators.peek()) >= GetPriority(CharArray[i])) {
                        	// 数字出栈，数字出栈，符号出栈，计算
                            int TempResult = TempCalc(Numbers.pop(), Numbers.pop(),Operators.pop());
                            if(TempResult < 0) {
                            	return "-1";
                            }
                            // 计算结果入栈
                            Numbers.push(TempResult);
                        }
                        // 符号入栈
                        Operators.push(CharArray[i]);
                    }
                }
            }
        }
        // 最后一个字符若是数字,未入栈
        if (HighNumber != 0) {
        	Numbers.push(HighNumber);
        }
        // 顺序计算
        /*
    	int TempOne=0;
    	int TempTwo=0;
    	char TempOp=0;
    	*/
        while (!Operators.isEmpty()) {

        	/*
        	try {
        		TempOne = Numbers.pop();
        	}
        	catch(EmptyStackException e){
        		System.out.println("Error:Number1");
        		break;
        	}
        	try {
        		TempTwo = Numbers.pop();
        	}
        	catch(EmptyStackException e){
        		Numbers.push(TempOne);
        		System.out.println("Error:Number2");
        		break;
        	}
        	try {
        		TempOp = Operators.pop();
        	}
        	catch(EmptyStackException e){
        		System.out.println("Error:Operator");
        		break;
        	}
        	int TempResult = TempCalc(TempOne, TempTwo,TempOp);
        	Numbers.push(TempResult);
        	*/
        	int TempResult = TempCalc(Numbers.pop(), Numbers.pop(),Operators.pop());//计算两个数据
            if(TempResult < 0) {
            	return "-1";
            }
        	Numbers.push(TempResult);

            
            
        }
        return String.valueOf(Numbers.pop());
    }

    // 返回运算符的优先级,数字和括号无需考虑
    private static int GetPriority(char Operator) {
        if (Operator == '+' || Operator == '-') {
            return 1;
        } else if (Operator == '×' || Operator == '÷') {
            return 2;
        } else {
            return 0;
        }
    }

    // 运算次序是反的,跟入栈出栈次序有关
    private static int TempCalc(int two, int one, char Operator) {
        int Result = -1;
        if (Operator == '+') {
        	Result = one + two;
        } else if (Operator == '-') {
        	Result = one - two;
        } else if (Operator == '×') {
        	Result = one * two;
        } else if (Operator == '÷') {
      	  if(one % two != 0) {
      		  return -1;
      	  }
      	Result = one / two;
        }
        return Result;
    }
}