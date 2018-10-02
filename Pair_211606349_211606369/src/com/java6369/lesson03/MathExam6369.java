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
	private static char [] Operator = {'+', '-', '×', '÷'};
	private static int grade;
	private static int range;
	//range为范围
	private static StringBuffer[] Topic;
	private static StringBuffer[] Answer;
	public static Map<Integer,Character> choose = new HashMap<Integer,Character>();
	//choose保存操作符的优先级
	private static String wrong = "输入有误";
	public static void main(String[] args) throws Exception {
		if(judge(args)) {
			choose.put(1,'+');
			choose.put(1,'-');
			choose.put(2,'×');
			choose.put(2,'÷');
			range = Integer.parseInt(args["-n".equals(args[0]) ? 1 : 3]);
			grade = Integer.parseInt(args["-n".equals(args[0]) ? 3 : 1]);
			//根据要求，输入的可以是1到3年级的题目，顺序可变化
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
			System.out.println("小学" + grade + "年级数学题题目已生成，请查看out.txt文件");
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
			//输入题数，年级，或者输入年级，题数都可以
			Pattern pattern = Pattern.compile("[0-9]*");
			//参数必须是数字
			boolean matches = pattern.matcher(args["-n".equals(args[0]) ? 1 : 3]).matches();
			
			if (matches && args["-n".equals(args[0]) ? 3 : 1].length() > 4) {
				wrong = "参数过大，请重输";
				return false;
			} else if (!matches) {
				wrong = "请输入数字，谢谢";
				return false;
			}
			Pattern compile = Pattern.compile("[1-3]?");
			boolean matches2 = compile.matcher(args["-n".equals(args[0]) ? 3 : 1]).matches();
			//年级的参数只能是1-3
			if (!matches2) {
				wrong = "目前只支持1~3年级，请重新运行，输入1~3中的一个数字";
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
			//操作符数
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
					// 抛出异常
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
					case '×':
						s.push(b*a);
						break;
					case '÷':
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
		//一二年级的题目
		for (int i = 0; i < range; i++) {
			int n1=(int) (Math.random() * 101);
			int n2=(int) (Math.random() * 101);
			// 获取两个随机数num1,num2
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
					//小学一年级两数相加时，都小于10且为整数
					n2 = n2/10*10;
					}
				result = n1 + n2;
				//两数相加
				break;
				case '-':
					if (n1 < n2) {
						int temp = n1;
						n1 = n2;
						n2 = temp;
						//一定是大的数-小的数，不能出现负数
						}
				case '×':
					n1=n1%10;
					n2=n2%10;
					//两数都为整数
					result=n1*n2;
					break;
				case '÷':
					while(n2==0) {
						n2=(int)(Math.random() * 101);
					}
					//除数不能为0，若为0，重新选择一个随机数
					if(n2>10) {
						n2 /=10 ; 
					}
					result= n1/n2;
					yushu = n1%n2;
					//求出余数和运算结果
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
    	  //保证路径存在
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		//创建文件输出流
		byte[] question=Topic.toString().getBytes();
		byte[] answer=Answer.toString().getBytes();
		out.write(question);
		out.write(System.lineSeparator().getBytes());
		out.write(answer);
		//写入数据
		out.close();
		//关闭
    }
}


