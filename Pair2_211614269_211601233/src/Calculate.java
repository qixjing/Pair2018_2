
import java.util.Stack;

//ʹ���沨�����ʽ����
public class Calculate {
	// ���췽��������Ŀ

	public static int[] ans(String str) {
		// TODO Auto-generated constructor stub
		// ����жϽ����������
		int[] a = new int[2];
		squestion = str;
		cutStr();
		reversePolishNotation();
		int com = compute();
		if (com < 0) {
			// 0Ϊ���������Ϸ�,1Ϊ�Ϸ�
			a[0] = 0;
			a[1] = com;
			// ��ż�����
		} else {
			a[0] = 1;
			a[1] = com;
		}
		return a;
	}

	// ��Ŀ���ַ���
	public static int slen;
	public static String[] scut = new String[100];
	public static String squestion;
	// ���������ջ
	public static Stack<String> soperators = new Stack<>();
	// ���ת������沨��ʽ
	public static Stack<String> srpn = new Stack<>();
	// ���ڵ����沨��ʽ��˳��
	public static Stack<String> srpnb = new Stack<>();
	// �����沨��ʽ�õ�ջ
	public static Stack<String> cal = new Stack<>();

	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// ���ַ�������Ŀ�и����ֵ�ͷ���
	public static void cutStr() {
		int n = 0;
		for (int i = 0; i < squestion.length(); i++) {
			if (Character.isDigit(squestion.charAt(i))) {
				if ((i + 1) < squestion.length() && Character.isDigit(squestion.charAt(i + 1))) {
					int num = (squestion.charAt(i) - '0') * 10 + (squestion.charAt(i + 1) - '0');
					scut[n] = String.valueOf(num);
					n++;
					i++;
				} else if (squestion.charAt(i) == ' ') {
				} else {
					int num = squestion.charAt(i) - '0';
					scut[n] = String.valueOf(num);
					n++;
				}
			} else {
				if(squestion.charAt(i)!=' ') {
					scut[n] = String.valueOf(squestion.charAt(i));
					n++;
				}
				
			}
		}
		slen = n;
	}

	public static int priority(String str) {
		switch (str) {
		case "(":
			return 0;
		case "+":
		case "-":
			return 1;
		case "*":
		case "/":
			return 2;
		default:
			return -1;
		}

	}

	// ת�����沨��ʽ
	public static void reversePolishNotation() {

		for (int i = 0; i < slen; i++) {
			if (!(scut[i].equals("+") || scut[i].equals("-") || scut[i].equals("*") || scut[i].equals("/")
					|| scut[i].equals("(") || scut[i].equals(")"))) {
				srpn.push(scut[i]);
			} else {
				if (soperators.isEmpty() || scut[i].equals("(")) {
					soperators.push(scut[i]);
				} else {
					if (priority(scut[i]) > priority(soperators.peek())) {
						soperators.push(scut[i]);
					} else {
						if (scut[i].equals(")")) {
							while (!soperators.peek().equals("(")) {
								srpn.push(soperators.pop());
							}
							soperators.pop();
						} else {
							while ((!soperators.isEmpty()) && (priority(soperators.peek()) >= priority(scut[i]))) {
								srpn.push(soperators.pop());
							}
							soperators.push(scut[i]);
						}

					}
				}
			}
		}
		while (!soperators.isEmpty()) {
			srpn.push(soperators.pop());
		}
	}

	public static int compute() {
		// �����沨��ʽ˳��
		while (!srpn.isEmpty()) {
			srpnb.push(srpn.pop());
		}

		while (!srpnb.isEmpty()) {
			if (srpnb.peek().equals("+") || srpnb.peek().equals("-") || srpnb.peek().equals("*")
					|| srpnb.peek().equals("/")) {
				String sym = srpnb.pop();
				int a = Integer.parseInt(cal.pop());
				int b = Integer.parseInt(cal.pop());
				if (sym.equals("+")) {
					cal.push(String.valueOf(b + a));
				} else if (sym.equals("-")) {
					cal.push(String.valueOf(b - a));
				} else if (sym.equals("*")) {
					cal.push(String.valueOf(b * a));
				} else {
					cal.push(String.valueOf(b / a));
				}

			} else {
				cal.push(srpnb.pop());
			}
		}
		return Integer.parseInt(cal.pop());

	}

}
