import java.io.File;
import java.util.*;
import java.text.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

//import com.sun.org.apache.xml.internal.security.Init;

public class MathExam6356 {
	public static int getN() {
		return n;
	}

	public static int getGrade() {
		return grade;
	}

	public static void setN(int n) {
		MathExam6356.n = n;
	}

	public static void setGrade(int grade) {
		MathExam6356.grade = grade;
	}

	static private int n;
	static private int grade;
	static int[] a;
	static int[] b;
	static int[] c;
	static String question[];
	static String answer[];
	static String fuhao[] = { "+", "-", "×", "÷" };
	static Random num = new Random();// 种子相同的Random对象，生成的随机数序列是一样的。

	public static void main(String[] args) throws FileNotFoundException {
		String message=init(args);
		if (!message.equals("正常运行")) {
			System.out.println(message);
			System.exit(0);
		}else if (grade == 1) {
			gradeOne();
		} else if (grade == 2) {
			gradeTwo();
		} else if (grade == 3) {
			gradeThree();
		} 
		outPut();

	}

	public static String init(String[] input) throws FileNotFoundException {
		String errorMes = "正常运行";
		if (input.length == 0) {
			setGrade(3);
			setN(10);
		}
		else if (input.length == 4) {
			if (input[0].equals("-n") && input[2].equals("-grade")) {
				try {
					setN(Integer.parseInt(input[1]));
					setGrade(Integer.parseInt(input[3]));
				} catch (NumberFormatException e) {
					errorMes="请输入正整数！";
					return errorMes;
				}

			} else if (input[2].equals("-n") && input[0].equals("-grade")) {
				try {
					setN(Integer.parseInt(input[3]));
					setGrade(Integer.parseInt(input[1]));
				} catch (NumberFormatException e) {
					errorMes="请输入正整数！";
					return errorMes;
				}
			}
		}if (n<=0||grade<=0) {
			errorMes="您输入了小于等于0的数，请输入正整数！";
			return errorMes;
		}if (grade>3) {
			errorMes="目前只支持1-3年级，请重新输入";
		}
		
			return errorMes;
		
	
	}

	public static void gradeThree() {
		question = new String[n];
		answer = new String[n];
		a = new int[n];
		b = new int[n];
		c = new int[n];
		for (int i = 0; i < n; i++) {
			int n1 = num.nextInt(2) + 3;// 随机符号的个数
			a = new int[n1];// 运算符个数
			b = new int[n1 + 1];// 数字的个数
			String fuhao1[] = new String[4]; // 判断符号
			for (int j = 0; j < n1; j++) {
				a[j] = num.nextInt(4);
				fuhao1[j] = fuhao[a[j]];// 随机生成n1个运算符
			}

			for (int k = 0; k < n1 + 1; k++) {
				b[k] = num.nextInt(100) + 1;// 根据运算符个数随机生成n+1个数字
			}
			String[] str2 = new String[n1 + 1];
			int[] flag = new int[n1 + 1];// 标记数组0(,1无符号,2)
			for (int j = 0; j < n1 + 1; j++) {
				str2[j] = Integer.toString(b[j]);
				flag[j] = 1;
			}
			int front = -2;
			String ss = null;// 用来保存一个算式，让它先为一个数字
			for (int x = 0; x < n1; x++) {
				if (fuhao1[x].equals("+") || fuhao1[x].equals("-")) {
					int n2 = (int) (0 + Math.random() * (2 - 1 + 1));// 随机生成0或1，用来随机生成加减法的括号
					boolean tag = false;
					if (n2 == 0) {
						if (front == x - 1) {
							if ((flag[x - 1] == 1 || flag[x - 1] == 0) && flag[x - 1] != 2 && !tag) {
								str2[x - 1] = "(" + str2[x - 1];
								flag[x - 1] = 0;
							} else {
								tag = true;
							}
							if ((flag[x + 1] == 1 || flag[x + 1] == 2) && flag[x + 1] != 0 && !tag) {
								str2[x + 1] = str2[x + 1] + ")";
								flag[x + 1] = 2;
							} else {
								tag = true;
							}
						} else {
							if ((flag[x] == 1 || flag[x] == 0) && flag[x] != 2 && !tag) {
								str2[x] = "(" + str2[x];
								flag[x] = 0;
							} else {
								tag = true;
							}
							if ((flag[x + 1] == 1 || flag[x + 1] == 2) && flag[x + 1] != 0 && !tag) {
								str2[x + 1] = str2[x + 1] + ")";
								flag[x + 1] = 2;
							} else {
								tag = true;
							}
							front = x;
						}

					}
				}
			}
			ss = str2[0];
			for (int j = 0; j < n1; j++) {
				ss = ss + fuhao1[j] + str2[j + 1];
			}
			question[i] = ss;
			System.out.println();
		}
		List<String> infixorder = null;
		for (int i = 0; i < question.length; i++) {
			infixorder = toInfixExpression(question[i]);// 中序表达式
			List<String> suffix = parseSuffixExpression(infixorder);// 后续
			try {
				answer[i] = question[i] + "=" + answer(suffix);
			} catch (java.lang.ArithmeticException e) {
				i--;
			}
			
		}

	}

	public static void gradeTwo() {
		question = new String[n];
		answer = new String[n];
		a = new int[n];
		b = new int[n];
		c = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = num.nextInt(9); // 0-100的随机数
			b[i] = num.nextInt(9);
			c[i] = num.nextInt(2) + 2;// 0是乘法1是除法
			if (c[i] == 1 || b[i] == 0) {// 除数是0重新随机
				b[i] = num.nextInt(8) + 1;
			}
			if (c[i] == 0 || (a[i] == 0 && b[i] == 0)) {
				a[i] = num.nextInt(8) + 1;
				b[i] = num.nextInt(8) + 1;
			}
			question[i] = Integer.toString(a[i]) + fuhao[c[i]] + Integer.toString(b[i]);
			List<String> infixorder = null;

			infixorder = toInfixExpression(question[i]);// 中序表达式
			List<String> suffix = parseSuffixExpression(infixorder);// 后续
			answer[i] = question[i] + "=" + answer(suffix);
		}

	}

	public static boolean outPut() throws FileNotFoundException {
		File file = new File("out.txt");
		PrintStream ps = new PrintStream(file);
		System.setOut(ps);// 把创建的打印输出流赋给系统。
		for (int i = 0; i < n; i++) {
			System.out.println("(" + (i + 1) + ") " + question[i]);
		}
		System.out.println("");
		for (int i = 0; i < n; i++) {
			System.out.println("(" + (i + 1) + ") " + answer[i]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		String time = sdf.format(new Date());
		System.out.println("211606356 陈宇 211606333温志铭 " + time);// 输出学号姓名时间
		return true;
	}

	public static void gradeOne() {
		question = new String[n];
		answer = new String[n];
		a = new int[n];
		b = new int[n];
		c = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = num.nextInt(100);// 第一个数
			b[i] = num.nextInt(100);// 第二个数
			c[i] = num.nextInt(2);// 符号
			if (c[i] == 0) {// 随机加法
				while (a[i] + b[i] > 100) {
					// 随机减法
					a[i] = num.nextInt(100);
					b[i] = num.nextInt(100);
				}

			} else {// 随机减法
				while (a[i] - b[i] < 0) {
					// 如果差小于0重新随机
					a[i] = num.nextInt(100);
					b[i] = num.nextInt(100);
				}
			}
			question[i] = Integer.toString(a[i]) + fuhao[c[i]] + Integer.toString(b[i]);
			List<String> infixorder = null;

			infixorder = toInfixExpression(question[i]);// 中序表达式
			List<String> suffix = parseSuffixExpression(infixorder);// 后续
			answer[i] = question[i] + "=" + answer(suffix);

		}
	}

	public static List<String> parseSuffixExpression(List<String> ls) {
		Stack<String> s1 = new Stack<String>();
		List<String> LS = new ArrayList<String>();
		for (String ssr : ls) {
			if (ssr.matches("\\d+")) {
				LS.add(ssr);
			} else if (ssr.equals("(")) {
				s1.push(ssr);
			} else if (ssr.equals(")")) {
				while (!s1.peek().equals("(")) {
					LS.add(s1.pop());
				}
				s1.pop();
			} else {
				Operation operation = new Operation();
				while (s1.size() != 0 && operation.getValue(s1.peek()) >= operation.getValue(ssr)) {
					LS.add(s1.pop());
				}
				s1.push(ssr);
			}
		}
		while (s1.size() != 0) {
			LS.add(s1.pop());
		}
		return LS;
	}

	public static List<String> toInfixExpression(String s) {
		List<String> infixorder = new ArrayList<String>();// 存储中序表达式
		int w = 0;
		String ssr;
		char c;
		do {
			if ((c = s.charAt(w)) < 48 || (c = s.charAt(w)) > 57) {
				infixorder.add("" + c);
				w++;
			} else {
				ssr = "";
				while (w < s.length() && (c = s.charAt(w)) >= 48 && (c = s.charAt(w)) <= 57) {
					ssr += c;
					w++;
				}
				infixorder.add(ssr);
			}

		} while (w < s.length());
		return infixorder;
	}

	public static String answer(List<String> last) {
		Stack<String> s = new Stack<String>();
		for (String ssr : last) {
			if (ssr.matches("\\d+")) {
				s.push(ssr);
			} else {
				int b = Integer.parseInt(s.pop());
				int a = Integer.parseInt(s.pop());
				String result = "";
				if (ssr.equals("+")) {
					result = Integer.toString(a + b);
				} else if (ssr.equals("-")) {
					result = Integer.toString(a - b);
				} else if (ssr.equals("×")) {
					result = Integer.toString(a * b);
				} else if (ssr.equals("÷")) {
					if (grade==2 && (a%b!=0)) {
						result = Integer.toString(a / b)+"..."+Integer.toString(a % b);
					}else {
						result = Integer.toString(a / b);
					}
					
				}
				s.push("" + result);
			}
		}
		return s.pop();
	}

	public static class Operation {// 优先级
		private int ADDITION = 1;
		private int SUBTRACTION = 1;
		private int MULTIPLICATION = 2;
		private int DIVISION = 2;

		Operation() {

		}

		public int getValue(String operation) {
			int result;
			switch (operation) {
			case "+":
				result = ADDITION;
				break;
			case "-":
				result = SUBTRACTION;
				break;
			case "×":
				result = MULTIPLICATION;
				break;
			case "÷":
				result = DIVISION;
				break;
			default:
				// System.out.println("不存在该运算符");
				result = 0;
			}
			return result;
		}
	}

}
