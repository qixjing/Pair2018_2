import java.util.Stack;
//import java.util.EmptyStackException;
// Shunting Yard Algorithm(���ȳ��㷨)
public class ShuntingYard {
	
	 // ����(��׺)���ʽ
	 public String Calc(String Expression, int Grade) {
		 // ����ջ
         Stack<Integer> Numbers = new Stack<Integer>();
         // ����(�����)ջ
         Stack<Character> Operators = new Stack<Character>();
         // ȥ�����ʽ�пո�
         // Expression = Expression.replace(" ", "");
         // �����λ����(������,���ܳ���0)
         int HighNumber = 0;
         // �ַ������ʽת��Ϊ�ַ�����
         // char[] CharArray = TempExpression.toCharArray();
         // �����ַ�����
         for (int i = 0; i < Expression.length(); i++) {
        	// �жϵ�ǰchar�Ƿ�Ϊ����
            if (Character.isDigit(Expression.charAt(i))) {
            	HighNumber = 10 * HighNumber + Integer.parseInt(String.valueOf(Expression.charAt(i))); //��ʱ�������10������
            } else {
                if (HighNumber != 0) {
                	// ������ջ
                	Numbers.push(HighNumber);
                	// ����
                	HighNumber = 0;
                 }
                // ���Ŵ���
                if (Expression.charAt(i) == '(') {
                	// ��������ջ
                	Operators.push(Expression.charAt(i));
                } else if (Expression.charAt(i) == ')') {
                	// ��������������(�鿴��ջ�����Ķ��󣬵����Ӷ�ջ���Ƴ�)
                    while (Operators.peek() != '(') { 
                    	// ���ֳ�ջ�����ֳ�ջ�����ų�ջ������
                        int TempResult = TempCalc(Numbers.pop(), Numbers.pop(), Operators.pop(), Grade);
                        // ��������ջ
                        Numbers.push(TempResult);
                    }
                    // �����ų�ջ
                    Operators.pop();
                } else if (GetPriority(Expression.charAt(i)) > 0) {
                	// ջ�Ƿ�Ϊ��
                    if (Operators.isEmpty()) {
                    	// ������ջ
                    	Operators.push(Expression.charAt(i));
                    } else {
                        // ��ջ��Ԫ�����ȼ����ڻ����Ҫ��ջ��Ԫ��,��ջ��Ԫ�ص���������,Ȼ����ջ
                        if (GetPriority(Operators.peek()) >= GetPriority(Expression.charAt(i))) {
                        	// ���ֳ�ջ�����ֳ�ջ�����ų�ջ������
                            int TempResult = TempCalc(Numbers.pop(), Numbers.pop(), Operators.pop(), Grade);
                            if(TempResult < 0) {
                            	return "-1";
                            }
                            // ��������ջ
                            Numbers.push(TempResult);
                        }
                        // ������ջ
                        Operators.push(Expression.charAt(i));
                    }
                }
            }
        }
        // ���һ���ַ���������,δ��ջ
        if (HighNumber != 0) {
        	Numbers.push(HighNumber);
        }
        // ˳�����
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
        	int TempResult = TempCalc(Numbers.pop(), Numbers.pop(), Operators.pop(), Grade);//������������
            if(TempResult < 0) {
            	return "-1";
            }
        	Numbers.push(TempResult);

            
            
        }
        return String.valueOf(Numbers.pop());
    }

    // ��������������ȼ�,���ֺ��������迼��
    private static int GetPriority(char Operator) {
        if (Operator == '+' || Operator == '-') {
            return 1;
        } else if (Operator == '��' || Operator == '��') {
            return 2;
        } else {
            return 0;
        }
    }

    // ��������Ƿ���,����ջ��ջ�����й�
    private static int TempCalc(int two, int one, char Operator, int Grade) {
        int Result = -1;
        if (Operator == '+') {
        	Result = one + two;
        } else if (Operator == '-') {
        	Result = one - two;
        } else if (Operator == '��') {
        	Result = one * two;
        } else if (Operator == '��') {
        	// ���꼶���ж�
      	  if(one % two != 0 && Grade == 3) {
      		  return -1;
      	  }
      	Result = one / two;
        }
        return Result;
    }
}