package com.java6369.lesson03;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

public class MathExam6369 {
	private static char [] Operator = {'+', '-', '��', '��'};
	private static int grade;
	private static int range;
	//rangeΪ��Χ
	private static StringBuffer[] Topic;
	private static StringBuffer[] Answer;
	public static Map<Integer,Character> choose = new HashMap<Integer,Character>();
	//choose��������������ȼ�
	private static String wrong = "��������";
	public static void main(String[] args) throws Exception {
		if(judge(args)) {
			choose.put(1,'+');
			choose.put(1,'-');
			choose.put(2,'��');
			choose.put(2,'��');
			range = Integer.parseInt(args["-n".equals(args[0]) ? 1 : 3]);
			grade = Integer.parseInt(args["-n".equals(args[0]) ? 3 : 1]);
			//����Ҫ������Ŀ�����1��3�꼶����Ŀ��˳��ɱ仯
			for(int i = 0;i<range;i++) {
				Topic[i]= new StringBuffer();
				Answer[i] = new StringBuffer();
			}
			if(grade==3) {
				Exam(range,grade);
			}else {
				Exam2(range,grade);
			}
			try {
				write();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Exam(range, grade);
			Exam2(range, grade);
			write();
			System.out.println("Сѧ" + grade + "�꼶��ѧ����Ŀ�����ɣ���鿴out.txt�ļ�");
		}else {
			System.out.println(wrong);
		}
	}

	private static boolean judge(String[] args) {
		if(args.length < 4 || args.length > 4) {
			return false;
		}
			else {
			if(!(("-n".equals(args[0]) && "-grade".equals(args[2]) )|| ("-grade".equals(args[0]) && "-n".equals(args[2]))))
			{
				return false;
			}
			//�����������꼶�����������꼶������������
			Pattern pattern = Pattern.compile("[0-9]*");
			//��������������
			boolean matches = pattern.matcher(args["-n".equals(args[0]) ? 1 : 3]).matches();
			
			if (matches && args["-n".equals(args[0]) ? 3 : 1].length() > 4) {
				wrong = "��������������";
				return false;
			} else if (!matches) {
				wrong = "���������֣�лл";
				return false;
			}
			Pattern compile = Pattern.compile("[1-3]?");
			boolean matches2 = compile.matcher(args["-n".equals(args[0]) ? 3 : 1]).matches();
			//�꼶�Ĳ���ֻ����1-3
			if (!matches2) {
				wrong = "Ŀǰֻ֧��1~3�꼶�����������У�����1~3�е�һ������";
				return false;
			}
			return true;
			}
	}
			
		
	private static void Exam(int range,int grade) throws Exception {
		int i,n;
		for( i = 0;i < range;i++) {
			n=5+(int)(Math.random()*3);
			if(n%2 == 0)
				n++;
			Character[] infixExpression = new Character[n];
			char[] str = new char[(n-1)/2];
			//��������
			char c ;
			infixExpression[n-1] = c = Operator[(int)(Math.random()*4)];
			for(int j = 1,k = n-2;j<(n-1)/2;j++,k--) {
				infixExpression[k] = Operator[(int)(Math.random()*4)];
				c ^= infixExpression[k].charValue();
				}
				
				if(c == 0) {
					int q = (int)(Math.random()*4);
					infixExpression[n-1] = Operator[q] != infixExpression[n-1].charValue() ? Operator[q] : Operator[(q+1)%4];
				}
		        
		        try {
		        	Topic[i].append("(" + (i+1) + ")");
		        	Answer[i].append("(" + (i+1) + ")");
		        	int result= CalPoland(infixExpression,i,str);
		        	Topic[i].append(System.lineSeparator());
		        	Answer[i].append(" = " + result);
		        	if(i != range - 1){
		        		Answer[i].append(System.lineSeparator());
		        	}
		        }
		        catch (Exception e) {
					// �׳��쳣
					Topic[i].setLength(0);
					Answer[i].setLength(0);
					i--;
				}
			}
		}
	@SuppressWarnings("unused")
	private static boolean Houzhui(Character[] houzuhui) {
		for(int i = houzuhui.length-1;i>=0;i--) {
			if(houzuhui[i] != null) {
				int temp = 0;
				int count = 0;
				for(int j = i-1;j>=0;j--) {
					if(houzuhui[j] != null) {
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

	static int CalPoland(Character houzuhui[],int n,char[] str) throws Exception{
		Stack<Object> s = new Stack<Object>();
		Stack<String> tops = new Stack<String>();
		for(int i = 0; i < houzuhui.length; i++)
		{
			if(houzuhui[i] == null) {
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
				
				switch(houzuhui[i])
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
						s.push(b-a);
						break;
					case '��':
						s.push(b*a);
						break;
					case '��':
						if(b % a != 0) {
							throw new Exception();
						}
						s.push(b/a);
						break;
				}
				tops.push(str2 + " " + houzuhui[i] + str1);
				int j = 0;
				if(j<str.length) {
					String s1 = tops.pop();
					tops.push(" (" + s1 + " )");
				}
			}
		}
		Topic[n].append(tops.peek());
		Answer[n].append(tops.pop());
		return (int) s.pop();
	}	
	private static void Exam2(int range,int grade) {
		//һ���꼶����Ŀ
		for (int i = 0; i < range; i++) {
			int n1=(int) (Math.random() * 101);
			int n2=(int) (Math.random() * 101);
			// ��ȡ���������num1,num2
			int index=(grade==1)?((int) (Math.random()*10))%2:((int) (Math.random()*10)) %4;
			char fuhao=Operator[index];
			while(index==0 && n1+n2 >= 101) {
				n1 = (int) (Math.random()*101);
				n2 = (int) (Math.random()*101);
				}
			int result = 0;
			int yushu = 0; 
			switch (fuhao) {
			case '+':
				if(grade == 1 && n1<10 && n2<10 && n1%10 != 0 && n2%10 !=0) {
					//Сѧһ�꼶�������ʱ����С��10��Ϊ����
					n2 = n2/10*10;
					}
				result = n1 + n2;
				//�������
				break;
				case '-':
					if (n1 < n2) {
						int temp = n1;
						n1 = n2;
						n2 = temp;
						//һ���Ǵ����-С���������ܳ��ָ���
						}
				case '��':
					n1=n1%10;
					n2=n2%10;
					//������Ϊ����
					result=n1*n2;
					break;
				case '��':
					while(n2==0) {
						n2=(int)(Math.random() * 101);
					}
					//��������Ϊ0����Ϊ0������ѡ��һ�������
					if(n2>10) {
						n2 /=10 ; 
					}
					result= n1/n2;
					yushu = n1%n2;
					//���������������
					break;
				}
			Topic[i].append("(" + (i+1) + ") " + n1 + " " + fuhao + " " + n2 + System.lineSeparator());
			if (fuhao == '/') {
				Answer[i].append("(" + (i+1) + ") " + n1 + " " + fuhao + " " + n2 + " = " + result
						+ (yushu != 0 ? ("..." + yushu) : ""));
				} else
				{
					Answer[i].append("(" + (i+1) + ") " + n1 + " " + fuhao + " " + n2 + " = " + result);
				}
		}
	}
    public static void write() throws IOException {
    	  File file= new File("out.txt");
    	  //��֤·������
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		//�����ļ������
		byte[] question=Topic.toString().getBytes();
		byte[] answer=Answer.toString().getBytes();
		out.write(question);
		out.write(System.lineSeparator().getBytes());
		out.write(answer);
		//д������
		out.close();
		//�ر�
    }
}


