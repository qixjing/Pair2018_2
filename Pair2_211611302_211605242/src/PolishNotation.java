
import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * ��׺ת��׺�Ĺ��򣺴����ұ�����׺���ʽ��ÿ�����ֺͷ���
 * �������־����������Ϊ��׺���ʽ��һ���֣�
 * ���Ƿ��ţ����ж�����ջ�����ŵ����ȼ����������Ż����ȼ���
 * ����ջ�����ţ��˳����ȼӼ�����ջ��Ԫ�����γ�ջ�������
 * ������ǰ���Ž�ջ��һֱ�����������׺���ʽ
 */

public class PolishNotation {
	private StringBuffer expression = null;
	private StringBuffer Postfix = new StringBuffer("");
	private ArrayDeque<String> stack = new ArrayDeque<>();
	private Operation oper;

	public PolishNotation() {
		// д���޲ι�����һ����ϰ��
	}

	public PolishNotation(StringBuffer expression) {
		this.expression = expression;
		getPostfix();
	}

	public String calculate() {
		//�����׺���ʽ
		stack = new ArrayDeque<>();
		String mod="";//ר��������Ӷ��꼶�����е�����
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
					//����ת�ͣ�Ϊ�˵���OperationDiv���������еķ���
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

	// ����׺���ʽתΪ��׺���ʽ
	private void getPostfix() {
		Matcher m = Pattern.compile("(-?\\d+)|(\\+)|" + "(\\-)|(\\*)|(\\/)|(\\()|(\\))").matcher(expression);
		while (m.find()) {
			// �����֣�ֱ����ӵ���׺���ʽ��
			if (m.group().matches("-?\\d+")) {
				Postfix.append(" "+ m.group());
			}
			// ջΪ�գ���ֱ�ӽ�ջ
			else if (stack.isEmpty()) {
				stack.push(m.group());
			}
			//��ǰ����Ϊ �������š���ֱ�ӽ�ջ
			else if (m.group().equals("(")) {
				stack.push("(");
			}
			
			// ��ǰ����Ϊ�������š�������ջ����ջ��Ԫ��������ӵ���׺���ʽ��
			// ֱ�������������š��������Ų���ӵ���׺���ʽ�У�
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
			// ���е�����ʱ����ǰ����ֻ�����ǡ��Ӽ��˳����е�ĳһ���������
			// ���ջ��Ԫ���ǡ������š���m.group()ֱ�ӽ�ջ
			else if (stack.peek().equals("(")) {
				stack.push(m.group());
			} 
			else {
				// �Ƚ�����������ȼ�
				comparePriority(m.group(), stack.peek());
			}
		}
		while(!stack.isEmpty()){
			Postfix.append(" " + stack.pop());
		}
		Postfix.delete(0, 1);//ɾ�������ַ�����λ�Ŀո�
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
					//��ǰ����Ϊ�˻��߳������������š�+��-��������ֱ�ӽ�ջ
				} while (!(stack.peek().matches("(\\+)|(\\-)|(\\()")));
				stack.push(group);
			}
		}
	}
}