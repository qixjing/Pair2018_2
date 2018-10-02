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
	//mp��������������ȼ�
	private static HashMap<Character, Integer> mp = new HashMap<Character, Integer>();
	private static String errorMessage = "����Ĳ�����ʽ�з��Ϲ淶���밴�ո�ʽ���� ��-n ���� -grade ����   ���� -grade ����   -n ����  ��";
	private static int grade;
	private static StringBuffer[] questions;
	private static StringBuffer[] answers;
	
	private static char[] Operator = { '+', '-', '��', '��' };

	public static void main(String[] args) throws Exception {
		if(textParameter(args)) {
			mp.put('-',1);
			mp.put('+',1);
			mp.put('��',2);
			mp.put('��',2);
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
			System.out.println("Сѧ" + grade + "�꼶��ѧ����Ŀ�����ɣ���鿴out.txt�ļ�");
		}else {
			System.out.println(errorMessage);
		}
	}

	/**
	 * 
	 * @param args �û�����Ĳ���
	 * @return     ������Ҫ��ʱ���� true�����򷵻�false
	 */
	private static boolean textParameter(String[] args) {
		if(args.length < 4 || args.length > 4) {
			return false;
		}else {
			// �����������Ƿ����� -n �� -grade����ʶ
			if(!(("-n".equals(args[0]) && "-grade".equals(args[2]) )|| ("-grade".equals(args[0]) && "-n".equals(args[2])))) {
				return false;
			}
			// ȥ�����ֲ�����ǰ��0
			String str1 = args[1].replaceFirst("^0*", "");
			String str3 = args[3].replaceFirst("^0*", "");
			// 3 ���������Ƿ�������
			String str_number = "-n".equals(args[0]) ? str1 : str3;
			if (str_number.length() > 4) {
				errorMessage = "������Ŀ��������";
				return false;
			}
			for (int i = str_number.length(); --i >= 0;) {
				if (!Character.isDigit(str_number.charAt(i))) {
					errorMessage = "��Ŀ����������������������һ��������";
					return false;
				}
			}
			String str_grade = "-grade".equals(args[0]) ? str1 : str3;
			if(str_grade.charAt(0) != '1' && str_grade.charAt(0) != '2' && str_grade.charAt(0) != '3') {
				errorMessage = "������1~3�е�һ������";
				return false;
	        }    
		}
		return true;
	}
	/**
	 * ���ã���¼��Ŀ���
	 */
	private static void printInfo(int num1,int num2,int i,int remainder,char symbol,int res) {
		questions[i].append("(" + (i + 1) + ") " + num1 + " " + symbol + " " + num2 + "\r\n");
		if ((symbol == '��') && (num1 % num2 != 0)) {
			if (remainder != 0 || (remainder == 0 && num1 == 0)) {
				answers[i].append("(" + i + ") " + num1 + " " + symbol + " " + num2 + " " + "= " + res + "..."
						+ remainder + "\r\n");
			}
		} else {
			answers[i].append("(" + (i+1) + ") " + num1 + " " + symbol + " " + num2 + " " + "= " + res + "\r\n");
		}
	}
	
	
	/**
	 * ���ã�����һ�꼶��Ŀ
	 * @param len ���ɵ���Ŀ����
	 * @param grade �꼶
	 */
	private static void generatingTitle_One(int len,int grade) {
		for (int i = 0; i < len; i++) {
			// ��ȡ�����������num1,num2��ʾ����������������;
			int num1 = (int) (Math.random() * 100);
			int num2 = (int) (Math.random() * 100);
			
			// symbol�����������;
			int index = (int) (Math.random() * 2) ;
			char symbol = Operator[index];
			
			//ȷ���������ĺͲ�����100
			while(index == 0 && num1 + num2 >= 100) {
				num1 = (int) (Math.random() * 100);
				num2 = (int) (Math.random() * 100);
			}
			int res = 0;
			switch (symbol) {
			case '+':
				// ������2λ��-��λ��
				// ������2λ��-����10����
				if(1 == grade && num1>10 && num2 >10 && num1%10 != 0 && num2%10 !=0) {
					num2 = num2/10*10;
				}
				res = num1 + num2;
				break;
			case '-':		
				// ȷ����һ�����ȵڶ������󣬴𰸲��ɳ��ָ���
				if (num1 < num2) {
					int temp = num1;
					num1 = num2;
					num2 = temp;
				}
				// ������2λ��-��λ��
				// ������2λ��-����10����
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
	 * ���ã����ɶ��꼶��Ŀ
	 * @param len ���ɵ���Ŀ����
	 * @param grade �꼶
	 */
	private static void generatingTitle_Two(int len,int grade) {
		for (int i = 0; i < len; i++) {
			// ��ȡ�����������num1,num2��ʾ����������������;
			int num1 = (int) (Math.random() * 100);
			int num2 = (int) (Math.random() * 100);
			
			// symbol�����������;
			int index = (int) (Math.random() * 2)+2 ;
			char symbol = Operator[index];
			
			//ȷ���������ĺͲ�����100
			while(index == 0 && num1 + num2 >= 100) {
				num1 = (int) (Math.random() * 100);
				num2 = (int) (Math.random() * 100);
			}
			
			int res = 0;
			int remainder = 0; // ����
			switch (symbol) {
			case '��':
				if(num1 == 0 && num2 == 0) {
					num2 = (int) (Math.random() * 10);
				}
				//ȷ���ھžų˷�����
				num1 = num1%10;
				num2 = num2%10;
				res = num1 * num2;
				break;
			case '��':
				//��ֹ����Ϊ0
				while(num2 == 0) {
					num2 = (int) (Math.random() * 100);
				}
				
				//ȷ��Ϊ�žų˷�����
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
	 * ���ã��������꼶��Ŀ
	 * @param len
	 * @param grade
	 * @throws Exception 
	 */
	private static void generatingTitle_three(int len,int grade) throws Exception {
		for(int i = 0;i < len;i++) {
			// �趨�������Ͳ��������ܸ���
			int n=5+(int)(Math.random()*3);
			// ȷ���������Ͳ��������ܸ���Ϊ����
			if(n%2 == 0) n++;
			
			//Character���ǶԵ����ַ����в���
			Character[] infixOperator = new Character[n];
			// ����(n-1)/2��������
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
 			
 			// �����ɵĲ�����������[2,n-2]��Χ���������
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
				// ���ɵ���Ŀ���Ϸ������StringBuffer
				questions[i].setLength(0);
				answers[i].setLength(0);
				i--;
			}
		}
	}

	/**
	 * ���ã��жϴ˺�׺���ʽ�Ƿ����
	 * @param infix ֻ���������ĺ�׺���ʽ����
	 * @return ���󷵻�true����ȷ����false
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
     * ͨ���沨�����ʽ������
	 * @param infix ��׺���ʽ����
	 * @param n �ڼ�����Ŀ
	 * @param str ����������
	 * @return ���ؼ�����
	 * @throws Exception �����ʽ��������г���ʱ�׳��쳣
	 */
	static int calculate(Character infix[],int n,char[] str) throws Exception{
		Stack<Object> s = new Stack<Object>();
		Stack<String> tops = new Stack<String>();
		int k = 1;//�����������
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
					case '��':
						s.push(b * a);
						break;
					case '��':
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
	 * ���ã������ɵ���Ŀ�ͺʹ�д��ָ���ļ�
	 * @param filename       ��Ҫд����ļ���
	 * @throws IOException   ���ļ������Ϸ�ʱ�׳��쳣
	 */
	private static void write(String filename) throws IOException {
		// ȷ��������ļ���Ŀ�ĵأ�
		// ���filename�а���·��������ȷ��·���Ѵ���
		File file = new File(filename);
		File parentFile = file.getParentFile();
		if (parentFile != null && !parentFile.exists()) {
			parentFile.mkdirs();
			System.out.println("����Ŀ¼��" + parentFile);
		}
		file.createNewFile();
		// ����ָ���ļ��������
		OutputStream out = new FileOutputStream(file);
		// д������
		byte[] question=questions.toString().getBytes();
		byte[] answer=answers.toString().getBytes();
		out.write(question);
		out.write(answer);
		// ����4���ر�
		out.close();
	}
} 