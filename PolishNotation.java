
import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 中缀转后缀的规则：从左到右遍历中缀表达式的每个数字和符号
 * 若是数字就输出，即成为后缀表达式的一部分；
 * 若是符号，则判断其与栈顶符号的优先级，是右括号或优先级不
 * 高于栈顶符号（乘除优先加减）则栈顶元素依次出栈并输出，
 * 并将当前符号进栈，一直到最终输出后缀表达式
 */

public class PolishNotation {
	private StringBuffer expression = null;
	private StringBuffer Postfix = new StringBuffer("");
	private ArrayDeque<String> stack = new ArrayDeque<>();
	private Operation oper;

	public PolishNotation() {
		// 写个无参构造是一个好习惯
	}

	public PolishNotation(StringBuffer expression) {
		this.expression = expression;
		getPostfix();
	}

	public String calculate() {
		//计算后缀表达式
		stack = new ArrayDeque<>();
		String mod="";//专门用来添加二年级除法中的余数
		String s [] = (String.valueOf(Postfix)).split(" ");
		for (int i = 0; i < s.length; i++) {
			if(s[i].matches("-?\\d+")){
				stack.push(s[i]);
			}
			else{
				int numberB = Integer.valueOf(stack.pop());
				int numberA = Integer.valueOf(stack.pop());
				oper = OperationFactory.createOperate(s[i]);
				int result = oper.calculate(numberA,numberB);
				stack.push(String.valueOf(result));
				if(s[i].equals("/")) {
					//向下转型，为了调用OperationDiv中子类特有的方法
					OperationDiv oper2=(OperationDiv) oper; 
					if(oper2.returnMod()!=0) {
						mod = "..." + String.valueOf(oper2.returnMod());
					}
				}
			}
		}
		String result = stack.pop()+mod;
		return result;
		
	}

	// 将中缀表达式转为后缀表达式
	private void getPostfix() {
		Matcher m = Pattern.compile("(-?\\d+)|(\\+)|" + "(\\-)|(\\*)|(\\/)|(\\()|(\\))").matcher(expression);
		while (m.find()) {
			// 纯数字，直接添加到后缀表达式中
			if (m.group().matches("-?\\d+")) {
				Postfix.append(" "+ m.group());
			}
			// 栈为空，则直接进栈
			else if (stack.isEmpty()) {
				stack.push(m.group());
			}
			//当前符号为 “左括号”，直接进栈
			else if (m.group().equals("(")) {
				stack.push("(");
			}
			
			// 当前符号为“右括号”，不进栈，将栈顶元素依次添加到后缀表达式中
			// 直到遇到“左括号”（左括号不添加到后缀表达式中）
			else if (m.group().matches("\\)")) {
				while (true) {
					String pop = stack.pop();
					if (pop.equals("(")) {
						break;
					} else {
						Postfix.append(" " +pop);
					}
				}
			}
			// 运行到这里时，当前符号只可能是“加减乘除”中的某一个运算符了
			// 如果栈顶元素是“左括号”，m.group()直接进栈
			else if (stack.peek().equals("(")) {
				stack.push(m.group());
			} 
			else {
				// 比较运算符号优先级
				comparePriority(m.group(), stack.peek());
			}
		}
		while(!stack.isEmpty()){
			Postfix.append(" " + stack.pop());
		}
		Postfix.delete(0, 1);//删掉处于字符串首位的空格
	}

	private void comparePriority(String group, String peek) {
		if (group.equals("+") || group.equals("-")) {
			do {
				Postfix.append(" " +stack.pop());
				if(stack.isEmpty()){
					break;
				}
			} while (!(stack.peek().equals("(")));
			stack.push(group);
		} else {
			if (peek.equals("+") || peek.equals("-")) {
				stack.push(group);
			} else {
				do {
					Postfix.append(" " +stack.pop());
					if(stack.isEmpty()){
						break;
					}
					//当前符号为乘或者除，遇到左括号、+、-，都可以直接进栈
				} while (!(stack.peek().matches("(\\+)|(\\-)|(\\()")));
				stack.push(group);
			}
		}
	}
}