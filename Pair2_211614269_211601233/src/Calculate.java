
import java.util.Stack;

//使用逆波兰表达式计算
public class Calculate {
	// 构造方法传入题目

	public static int[] ans(String str) {
		// TODO Auto-generated constructor stub
		// 存放判断结果及计算结果
		int[] a = new int[2];
		squestion = str;
		cutStr();
		reversePolishNotation();
		int com = compute();
		if (com < 0) {
			// 0为计算结果不合法,1为合法
			a[0] = 0;
			a[1] = com;
			// 存放计算结果
		} else {
			a[0] = 1;
			a[1] = com;
		}
		return a;
	}

	// 题目的字符串
	public static int slen;
	public static String[] scut = new String[100];
	public static String squestion;
	// 存操作符的栈
	public static Stack<String> soperators = new Stack<>();
	// 存放转换后的逆波兰式
	public static Stack<String> srpn = new Stack<>();
	// 用于调换逆波兰式的顺序
	public static Stack<String> srpnb = new Stack<>();
	// 计算逆波兰式用的栈
	public static Stack<String> cal = new Stack<>();

	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// 将字符串的题目切割成数值和符号
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

	// 转换成逆波兰式
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
		// 倒换逆波兰式顺序
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
