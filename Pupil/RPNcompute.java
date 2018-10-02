package Pupil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class RPNcompute {
	
//	static List<String> RPN = new ArrayList<String>();
	static List<String> RPN;
//	����һ��stack��¼�����
	static Stack<String> ops = new Stack<String>();

//	�������һ�³�ʼ��������
//	����һ��op�����ȼ�
	private static int priorityInfo(String symbol) {
	    HashMap<String, Integer> precedence = new HashMap<>();
	    precedence.put("(", 2);  
	    precedence.put(")", 2);
	    precedence.put("+", 0);  
	    precedence.put("-", 0);
	    precedence.put("*", 1);
	    precedence.put("/", 1);
	    return precedence.get(symbol);
	}
	private static boolean isNumber(String s) {
	    if (s.equals("(") || s.equals(")") || s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
	        return false;
	    return true;
	}
	 
	private static double eval(String op, String val1, String val2) {
//	    if (op.equals("+")) return Integer.parseInt(val1) + Integer.parseInt(val2);
//	    if (op.equals("-")) return Integer.parseInt(val1) - Integer.parseInt(val2);
//	    if (op.equals("/")) return Integer.parseInt(val1) / Integer.parseInt(val2);
//	    if (op.equals("*")) return Integer.parseInt(val1) * Integer.parseInt(val2);
	    if (op.equals("+")) return Double.parseDouble(val1) + Double.parseDouble(val2);
	    if (op.equals("-")) return Double.parseDouble(val1) - Double.parseDouble(val2);
	    if (op.equals("/")) return Double.parseDouble(val1) / Double.parseDouble(val2);
	    if (op.equals("*")) return Double.parseDouble(val1) * Double.parseDouble(val2);
	 
	    throw new RuntimeException("Invalid operator");
	}
//	���潫��׺���ʽת��Ϊ��׺���ʽ
	/**
	 * ��׺���ʽ���ɺ�׺���ʽ
	 *
	 * @param in
	 */
	
	private static boolean fixPosition(String[] in) {
	    for (int i = 0; i < in.length; i++) {
	        if (isNumber(in[i])) {
	            //���������,ֱ�ӽ�������
	            RPN.add(in[i]);
	        } else {
	 
	            if (ops.isEmpty()) {
	                ops.push(in[i]);
	            } else if (in[i].equals("(")) {
	                ops.push(in[i]);
	            } else if (in[i].equals(")")) {
	                //���ų�ջ
	                bracketsPop();
	            } else {
	                //�����Ҫ��ӵ����ȼ�С�ڻ��ߵ���ջ�������ȼ� ��ջ�������ȼ���ջ �µ�Ԫ����ջ,����Ļ� �µ�Ԫ�ؽ�ջ
	                int newpriority = priorityInfo(in[i]);
	                int stackTopPriority = priorityInfo(ops.peek());
	                //��Ҫ�ų�ջ��Ԫ�ز�������
	                if (stackTopPriority != 2 && newpriority <= stackTopPriority) {
	                    RPN.add(ops.pop());
	                }
	                ops.push(in[i]);
	            }
	        }
	    }
	//forѭ��ִ����ϣ���op  stackʣ���op�Ƶ�list��
	    while (!ops.isEmpty())
	        RPN.add(ops.pop());
	    return true;
	}
	private static boolean bracketsPop() {
	 
	    while (!ops.peek().equals("(")) {
	        RPN.add(ops.pop());
	    }
	    //�����Ҫ�����ϲ��"("��ȥ��
	    ops.pop();
	    return true;
	}
	private static double calculate() {
		double sum1=0;
	    for (int i = 0; i < RPN.size(); i++) {
	        if (!isNumber(RPN.get(i))) {
	            //��Ҫ��ǰ���������ֽ�������,��������
	        	try {
					double newTmp = eval(RPN.get(i), RPN.get(i - 2), RPN.get(i - 1));
		            RPN.remove(i);
		            RPN.remove(i - 1);
		            RPN.remove(i - 2);
		            RPN.add(i - 2, newTmp + "");
		            if (RPN.size() == 1) {
//		                System.out.println(RPN.get(0));
		                sum1=Double.parseDouble(RPN.get(0));
		                return sum1;
		            }
				} catch (Exception e) {
					// TODO: handle exception
						sum1=Double.NEGATIVE_INFINITY;
					return sum1;
				}
	            break;
	        }
	    }
	    return calculate();
	}	
	
	
//	�����沨���㷨������
	public static double RPNcalculate(String str) {
		RPN = new ArrayList<String>();
		String[] RPNStr= str.split(" ");
		fixPosition(RPNStr);
		return calculate();
	}
}
