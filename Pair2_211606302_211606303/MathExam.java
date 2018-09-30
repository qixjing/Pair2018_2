package com.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class MathExam {
	//mp保存操作符的优先级
	private static HashMap<Character, Integer> mp = new HashMap<Character, Integer>();
	private static String errorMessage = "输入的参数形式有符合规范，请按照格式输入 ：-n 题量 -grade 数量   或者 -grade 数量   -n 题量  。";
	private static int grade;
	private static StringBuffer[] questions;
	private static StringBuffer[] answers;
	
	private static char[] Operator = { '+', '-', '×', '÷' };

	public static void main(String[] args) throws Exception {
		if(textParameter(args)) {
			mp.put('-',1);
			mp.put('+',1);
			mp.put('×',2);
			mp.put('÷',2);
			int len = Integer.parseInt(args["-n".equals(args[0]) ? 1 : 3]);
			grade = Integer.parseInt(args["-n".equals(args[0]) ? 3 : 1]);
			questions = new StringBuffer[len];
			answers = new StringBuffer[len];
			for(int i = 0;i<len;i++) {
				questions[i] = new StringBuffer();
				answers[i] = new StringBuffer();
			}
			if(grade == 3) {
				generatingTitle_three(len,grade);
			}else if(grade == 2) {
				generatingTitle_Two(len,grade);
			}else if(grade == 1) {
				generatingTitle_One(len,grade);
			}
			try {
				write("out.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("小学" + grade + "年级数学题题目已生成，请查看out.txt文件");
		}else {
			System.out.println(errorMessage);
		}
	}

	/**
	 * 
	 * @param args 用户输入的参数
	 * @return     当符合要求时返回 true，否则返回false
	 */
	private static boolean textParameter(String[] args) {
		if(args.length < 4 || args.length > 4) {
			return false;
		}else {
			// 检测输入参数是否是用 -n ， -grade来标识
			if(!(("-n".equals(args[0]) && "-grade".equals(args[2]) )|| ("-grade".equals(args[0]) && "-n".equals(args[2])))) {
				return false;
			}
			// 去除数字参数的前导0
			String str1 = args[1].replaceFirst("^0*", "");
			String str3 = args[3].replaceFirst("^0*", "");
			// 3 检验题数是否都是数字
			String str_number = "-n".equals(args[0]) ? str1 : str3;
			if (str_number.length() > 4) {
				errorMessage = "输入题目数量过大！";
				return false;
			}
			for (int i = str_number.length(); --i >= 0;) {
				if (!Character.isDigit(str_number.charAt(i))) {
					errorMessage = "题目数量不是正整数，请输入一个正整数";
					return false;
				}
			}
			String str_grade = "-grade".equals(args[0]) ? str1 : str3;
			if(str_grade.charAt(0) != '1' && str_grade.charAt(0) != '2' && str_grade.charAt(0) != '3') {
				errorMessage = "请输入1~3中的一个数字";
				return false;
	        }    
		}
		return true;
	}
	/**
	 * 作用：记录题目与答案
	 */
	private static void printInfo(int num1,int num2,int i,int remainder,char symbol,int res) {
		questions[i].append("(" + (i + 1) + ") " + num1 + " " + symbol + " " + num2 + "\r\n");
		if ((symbol == '÷') && (num1 % num2 != 0)) {
			if (remainder != 0 || (remainder == 0 && num1 == 0)) {
				answers[i].append("(" + i + ") " + num1 + " " + symbol + " " + num2 + " " + "= " + res + "..."
						+ remainder + "\r\n");
			}
		} else {
			answers[i].append("(" + (i+1) + ") " + num1 + " " + symbol + " " + num2 + " " + "= " + res + "\r\n");
		}
	}
	
	
	/**
	 * 作用：生成一年级题目
	 * @param len 生成的题目数量
	 * @param grade 年级
	 */
	private static void generatingTitle_One(int len,int grade) {
		for (int i = 0; i < len; i++) {
			// 获取两个随机数，num1,num2表示参与计算的两个数字;
			int num1 = (int) (Math.random() * 100);
			int num2 = (int) (Math.random() * 100);
			
			// symbol代表运算符号;
			int index = (int) (Math.random() * 2) ;
			char symbol = Operator[index];
			
			//确保两个数的和不超过100
			while(index == 0 && num1 + num2 >= 100) {
				num1 = (int) (Math.random() * 100);
				num2 = (int) (Math.random() * 100);
			}
			int res = 0;
			switch (symbol) {
			case '+':
				// 可以是2位数-个位数
				// 可以是2位数-整的10倍数
				if(1 == grade && num1>10 && num2 >10 && num1%10 != 0 && num2%10 !=0) {
					num2 = num2/10*10;
				}
				res = num1 + num2;
				break;
			case '-':		
				// 确保第一个数比第二个数大，答案不可出现负数
				if (num1 < num2) {
					int temp = num1;
					num1 = num2;
					num2 = temp;
				}
				// 可以是2位数-个位数
				// 可以是2位数-整的10倍数
				if(grade == 1 && num1>10 && num2 >10 && num2%10 !=0) {
					num2 = num2/10*10;
				}
				res = num1 - num2;
				break;
			default:
				System.out.println("unsupported sign!");
			}
			printInfo(num1,num2,i,0,symbol, res);
		}
	}
	
	/**
	 * 作用：生成二年级题目
	 * @param len 生成的题目数量
	 * @param grade 年级
	 */
	private static void generatingTitle_Two(int len,int grade) {
		for (int i = 0; i < len; i++) {
			// 获取两个随机数，num1,num2表示参与计算的两个数字;
			int num1 = (int) (Math.random() * 100);
			int num2 = (int) (Math.random() * 100);
			
			// symbol代表运算符号;
			int index = (int) (Math.random() * 2)+2 ;
			char symbol = Operator[index];
			
			//确保两个数的和不超过100
			while(index == 0 && num1 + num2 >= 100) {
				num1 = (int) (Math.random() * 100);
				num2 = (int) (Math.random() * 100);
			}
			
			int res = 0;
			int remainder = 0; // 余数
			switch (symbol) {
			case '×':
				if(num1 == 0 && num2 == 0) {
					num2 = (int) (Math.random() * 10);
				}
				//确保在九九乘法表里
				num1 = num1%10;
				num2 = num2%10;
				res = num1 * num2;
				break;
			case '÷':
				//防止除数为0
				while(num2 == 0) {
					num2 = (int) (Math.random() * 100);
				}
				
				//确保为九九乘法表里
				if(num2>10) {
					num2 = num2/10 ;
				}
				res = num1 / num2;
				if (num1 % num2 == 0) {
					break;
				} else {
					if (num1 < num2) {
						remainder = num1;
					} else if (num1 > num2) {
						remainder = num1 % num2;
					}
				}
				break;
			default:
				System.out.println("unsupported sign!");
			}
			printInfo(num1, num2, i, remainder, symbol, res);
		}
	}
	
	/**
	 * 作用：生成三年级题目
	 * @param len
	 * @param grade
	 * @throws Exception 
	 */
	private static void generatingTitle_three(int len,int grade) throws Exception {
		for(int i = 0;i < len;i++) {
			// 设定操作数和操作符的总个数
			int n=5+(int)(Math.random()*3);
			// 确保操作数和操作符的总个数为奇数
			if(n%2 == 0) n++;
			
			//Character类是对单个字符进行操作
			Character[] infixOperator = new Character[n];
			// 生成(n-1)/2个操作符
			char[] str = new char[(n-1)/2];
			char c ;
 			infixOperator[n-1] = c = Operator[(int)(Math.random()*4)];
 			for(int j = 1,k = n-2;j < (n-1)/2;j++,k--) {
 				infixOperator[k] = Operator[(int)(Math.random()*4)];
 				c ^= infixOperator[k].charValue();
 			}
 			
 			if(c == 0) {
 				int q = (int)(Math.random()*4);
 				infixOperator[n-1] = Operator[q] != infixOperator[n-1].charValue() ? Operator[q] : Operator[(q+1)%4];
 			}
 			
 			// 将生成的操作符在数组[2,n-2]范围内随机排序
 			do {
 				List<Character> list = new ArrayList<Character>();  
 				for(int j = 2;j < infixOperator.length-1;j++){  
 		            list.add(infixOperator[j]);  
 		        }  
 		          
 		        Collections.shuffle(list);  
 		          
 		        Iterator<Character> ite = list.iterator();  
 		        int k = 2,l = 0;
 		        while(ite.hasNext()){  
 		            infixOperator[k] = ite.next();
 		            if(infixOperator[k] != null) {
 		            	str[l++] = infixOperator[k];
 		            }
 		            k++;
 		        }
 		        str[l] = infixOperator[k];
 		    }while(isInfixOper(infixOperator));
	        
	        try {
	        	questions[i].append("(" + (i+1) + ")");
	        	answers[i].append("(" + (i+1) + ")");
	        	int res = calculate(infixOperator,i,str);
	        	questions[i].append(System.lineSeparator());
	        	answers[i].append(" = " + res);
	        	if(i != len - 1){
	        		answers[i].append(System.lineSeparator());
	        	}
			} catch (Exception e) {
				// 生成的题目不合法，清空StringBuffer
				questions[i].setLength(0);
				answers[i].setLength(0);
				i--;
			}
		}
	}

	/**
	 * 作用：判断此后缀表达式是否错误
	 * @param infix 只含操作符的后缀表达式数组
	 * @return 错误返回true，正确返回false
	 */
	private static boolean isInfixOper(Character[] infix) {
		for(int i = infix.length-1;i>=0;i--) {
			if(infix[i] != null) {
				int temp = 0;
				int count = 0;
				for(int j = i-1;j>=0;j--) {
					if(infix[j] != null) {
						temp++;
					}else {
						count++;
					}
				}
				if(count - temp < 2) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
     * 通过逆波兰表达式计算结果
	 * @param infix 后缀表达式数组
	 * @param n 第几个题目
	 * @param str 操作符数组
	 * @return 返回计算结果
	 * @throws Exception 当表达式计算过程中出错时抛出异常
	 */
	static int calculate(Character infix[],int n,char[] str) throws Exception{
		Stack<Object> s = new Stack<Object>();
		Stack<String> tops = new Stack<String>();
		int k = 1;//运算符计数器
		for(int i = 0; i < infix.length; i++)
		{
			if(infix[i] == null) {
				s.push(' ');
			}else{				
				int a,b;
				String str1,str2;
				if(s.peek() instanceof Character) {
					a = 1 + (int) (Math.random() * 100);
					str1 = " " + a;
				}else {
					a = (int) s.peek();
					str1 = tops.peek();
					tops.pop();
				}
				s.pop();
				
				if(s.peek() instanceof Character) {
					b = 1 + (int) (Math.random() * 100);
					str2 = " " + b;
				}else {
					b = (int) s.peek();
					str2 = tops.peek();
					tops.pop();
				}
				s.pop();
				
				switch(infix[i])
				{
					case '+':
						s.push(b + a);
						break;
					case '-':
						if(b < a ) {
							int temp = b;
							b = a;
							a = temp;
							
							String st = str1;
							str1 = str2;
							str2 = st;
						}
						s.push(b - a);
						break;
					case '×':
						s.push(b * a);
						break;
					case '÷':
						if(b % a != 0) {
							throw new Exception();
						}
						s.push(b / a);
						break;
				}
				tops.push(str2 + " " + infix[i] + str1);
				int j;
				for(j = k;j<str.length;j++) {
					if(mp.get(infix[i]) < mp.get(str[k])||(mp.get(infix[i]) == mp.get(str[k]) && str[k] == '-')) {
						break;
					}
				}
				if(j<str.length) {
					String s1 = tops.pop();
					tops.push(" (" + s1 + " )");
				}
				k++;
			}
		}
		questions[n].append(tops.peek());
		answers[n].append(tops.pop());
		return (int) s.pop();
	}
	
	/**
	 * 作用：将生成的题目和和答案写入指定文件
	 * @param filename       将要写入的文件名
	 * @throws IOException   当文件名不合法时抛出异常
	 */
	private static void write(String filename) throws IOException {
		// 确定输出的文件（目的地）
		// 如果filename中包含路径，必须确保路径已存在
		File file = new File(filename);
		File parentFile = file.getParentFile();
		if (parentFile != null && !parentFile.exists()) {
			parentFile.mkdirs();
			System.out.println("创建目录：" + parentFile);
		}
		file.createNewFile();
		// 创建指向文件的输出流
		OutputStream out = new FileOutputStream(file);
		// 写入数据
		byte[] question=questions.toString().getBytes();
		byte[] answer=answers.toString().getBytes();
		out.write(question);
		out.write(answer);
		// 步骤4：关闭
		out.close();
	}
} 