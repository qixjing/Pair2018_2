package Pupil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class RPNcompute {
	
//	static List<String> RPN = new ArrayList<String>();
	static List<String> RPN;
//	定义一个stack纪录运算符
	static Stack<String> ops = new Stack<String>();

//	首先完成一下初始化工作：
//	定义一下op的优先级
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
//	下面将中缀表达式转化为后缀表达式
	/**
	 * 中缀表达式换成后缀表达式
	 *
	 * @param in
	 */
	
	private static boolean fixPosition(String[] in) {
	    for (int i = 0; i < in.length; i++) {
	        if (isNumber(in[i])) {
	            //如果是数字,直接进入数组
	            RPN.add(in[i]);
	        } else {
	 
	            if (ops.isEmpty()) {
	                ops.push(in[i]);
	            } else if (in[i].equals("(")) {
	                ops.push(in[i]);
	            } else if (in[i].equals(")")) {
	                //括号出栈
	                bracketsPop();
	            } else {
	                //如果需要添加的优先级小于或者等于栈顶的优先级 则栈顶的优先级出栈 新的元素入栈,否则的话 新的元素进栈
	                int newpriority = priorityInfo(in[i]);
	                int stackTopPriority = priorityInfo(ops.peek());
	                //需要排除栈顶元素不是括号
	                if (stackTopPriority != 2 && newpriority <= stackTopPriority) {
	                    RPN.add(ops.pop());
	                }
	                ops.push(in[i]);
	            }
	        }
	    }
	//for循环执行完毕，将op  stack剩余的op移到list中
	    while (!ops.isEmpty())
	        RPN.add(ops.pop());
	    return true;
	}
	private static boolean bracketsPop() {
	 
	    while (!ops.peek().equals("(")) {
	        RPN.add(ops.pop());
	    }
	    //最后需要把最上层的"("给去掉
	    ops.pop();
	    return true;
	}
	private static double calculate() {
		double sum1=0;
	    for (int i = 0; i < RPN.size(); i++) {
	        if (!isNumber(RPN.get(i))) {
	            //需要讲前面两个数字进行运算,从新排序
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
	
	
//	运用逆波兰算法计算结果
	public static double RPNcalculate(String str) {
		RPN = new ArrayList<String>();
		String[] RPNStr= str.split(" ");
		fixPosition(RPNStr);
		return calculate();
	}
}
