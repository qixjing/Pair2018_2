
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.nio.file.attribute.FileTime;
import java.text.Collator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MathExam1253 {
	static int grade;
	static int n;
	public static  void main(String[] args) {
		if(check(args)) {
			
		}
		else{
			System.out.println("输入有误，程序结束");
			System.exit(0);
		}
		Random rand = new Random();
		if (grade==1) {
			int d = 0;
			String x = "";
			String y = "";
			for (int i = 1; i <= n; i++) {
				int c = (rand.nextInt(2));// 0表示加法，1表示减法
				if (c == 0) {
					int a = (rand.nextInt(100));// 随机生成两个0-99的相加减的数,且之和不大于100
					int b = (rand.nextInt(100 - a));
					d = a + b;
					if (i != 1) {
						x = x + "\n";
						y = y + "\n";
					}
					x = x + "(" + i + ")" + " " + a + " " + "+" + " " + b + " " + "=";// 将题目存入x
					y = y + "(" + i + ")" + " " + a + " " + "+" + " " + b + " " + "=" + " " + d;// 将题目及答案存入y

				} else{

					int a = (rand.nextInt(100));// 随机生成两个0-99的相加减的数,且b不大于a
					int b = (rand.nextInt(10));
					d = a - b;
					if (i != 1) {
						x = x + "\n";
						y = y + "\n";
					}
					x = x + "(" + i + ")" + " " + a + " " + "-" + " " + b + " " + "=";// 将题目存入x
					y = y + "(" + i + ")" + " " + a + " " + "-" + " " + b + " " + "=" + " " + d;// 将题目及答案存入y

				}
			}
			String z = x;
			String k = y;
			try {
				File f = new File("out.txt");
				PrintStream f1 = new PrintStream(new FileOutputStream(f));
				System.setOut(f1);
				System.out.println(z);
				System.out.println(k);
			} catch (Exception e) {
			}
		} 
		else if(grade==2){
			int d = 0;
			int d1 = 0;
			String x = "";
			String y = "";
			for (int i = 1; i <= n; i++) {
				int c = (rand.nextInt(2));// 0表示乘法法，1表示除法
				if (c == 0) {
					int a = (rand.nextInt(9)+1);// 随机生成两个1-9的相乘除的数
					int b = (rand.nextInt(9)+1);
					d = a * b;
					if (i != 1) {
						x = x + "\n";
						y = y + "\n";
					}
					x = x + "(" + i + ")" + " " + a + " " + "*" + " " + b + " " + "=";// 将题目存入x
					y = y + "(" + i + ")" + " " + a + " " + "*" + " " + b + " " + "=" + " " + d;// 将题目及答案存入y
				} else {
					int a = (rand.nextInt(100));// 随机生成两个1-9的相乘除的数,且b不大于a,b不为0
					int b = (rand.nextInt(9)+1);
					d = a / b;
					d1 = a % b;
					if(a%b!=0) {
					if (i != 1) {
						x = x + "\n";
						y = y + "\n";
					}
					x = x + "(" + i + ")" + " " + a + " " + "/" + " " + b + " " + "=";// 将题目存入x
					y = y + "(" + i + ")" + " " + a + " " + "/" + " " + b + " " + "=" + " " + d+"..."+d1;// 将题目及答案存入y

				}else {
					if (i != 1) {
						x = x + "\n";
						y = y + "\n";
					}
					x = x + "(" + i + ")" + " " + a + " " + "/" + " " + b + " " + "=";// 将题目存入x
					y = y + "(" + i + ")" + " " + a + " " + "/" + " " + b + " " + "=" + " " + d;// 将题目及答案存入y
				}
				}
			}
			String z = x;
			String k = y;
			try {
				File f = new File("out.txt");
				PrintStream f1 = new PrintStream(new FileOutputStream(f));
				System.setOut(f1);
				System.out.println(z);
				System.out.println(k);
			} catch (Exception e) {
			}
		}
		else {
			int num = (rand.nextInt(3)+3);
			ArrayList<String> s1 = new ArrayList<>();
			ArrayList<String> s2 = new ArrayList<>();
			int i;
			int j;
			int d=0;
			String c;
			String x1="";
			String x2="";
			String x3="";
			String y1="";
			String y2="";
			for(i=1;i<=n;i++) {
			do {
				x1="";
				x2="";
				x3="";
				for(j=1;j<=num;j++) {
				int a = (rand.nextInt(100)+1);
				c = String.valueOf(getOperator());
				if(j==num) {
					c ="";
				}
				x1 +=" "+a + " "+ c;
				x2 +=" "+a + " "+ c;
				x3 +=a+c;	
			}
			 anwser(x3);
			 d = Integer.valueOf(anwser(x3));
				}while(d<0);
			y1="("+i+")"+x1;
			y2="("+i+")"+x2+" "+"="+" "+d;

			s1.add(y1+"");
			s2.add(y2+"");
			}
			try {
				File f = new File("out.txt");
				PrintStream f1 = new PrintStream(new FileOutputStream(f));
				System.setOut(f1);
				for(int k=0;k<s1.size();k++){
					System.out.println(s1.get(k));
				}
				System.out.println("\n");
				for(int k=0;k<s1.size();k++){
					System.out.println(s2.get(k));
				}	
			} catch (Exception e) {
			}
			
		}
	}
	public static boolean check(String[] args) {
		if (args.length!=4) {
		
			return false;
		}
		boolean r = args[1].matches("[0-9]+");
		boolean l = args[3].matches("[0-9]+");
		 grade = 1;
		 n = 0;
		if(args[0].equals("-n") && args[2].equals("-grade"))
 		{
			if (r == true && l== true) {
				n = Integer.valueOf(args[1]);
	 			grade = Integer.valueOf(args[3]);
			}
			else {
				
				return false;
	 			}
 		}
 		else if(args[0].equals("-grade") && args[2].equals("-n"))
 		{
 			if (r == true && l== true) {
 	 			grade = Integer.valueOf(args[1]);
 	 			n = Integer.valueOf(args[3]);
			}
 			else {
		
				return false;
	 			}
 		}
 		else {
 		
 			return false;
 		}
		if (grade!=1 && grade!=2 && grade!=3) {
			
			return false;
		}
		if ( n >= 10000 || n == 0) {
		
			return false;
		}
		return true;
	}
	private static char getOperator() {
		// TODO Auto-generated method stub
		char c = 0;  
        Random ran = new Random();  
        int i = ran.nextInt(4);  
        switch(i){  
            case 0:  
            	c = '+';  
                break;  
            case 1:  
            	c = '-';  
                break;  
            case 2:  
            	c = '*';  
                break;  
            case 3:  
            	c = '/';  
                break;  
        }  
        return c;  
	}//生成随机符号
	public static String anwser(String x3) {
		Matcher sz = Pattern.compile("(\\d+)").matcher(x3);
		Matcher fh = Pattern.compile("[\\+\\-\\*\\/]").matcher(x3);
		
		LinkedList<String> num = new LinkedList<String>();
		LinkedList<String> sym = new LinkedList<String>();
		
		while(sz.find()) {
			num.add(sz.group());
		}
		while(fh.find()) {
			sym.add(fh.group());
		}
		int s=0;
		int num1=0;
		int num2=0;	
		for(int i=0;i<sym.size();i++) {
			if(sym.get(i).equals("*")||sym.get(i).equals("/")) {
				num1=Integer.valueOf(num.get(i));
				num2=Integer.valueOf(num.get(i+1));
				if(sym.get(i).equals("*")) {
					s=num1*num2;
				}else if(sym.get(i).equals("/")){
					s=num1/num2;
				}
				num.set(i, String.valueOf(s));
				num.remove(i+1);
				sym.remove(i);
				i--;
			}
		}
		for(int i=0;i<sym.size();i++) {
			if(sym.get(i).equals("+")||sym.get(i).equals("-")) {
				num1=Integer.valueOf(num.get(i));
				num2=Integer.valueOf(num.get(i+1));
				if(sym.get(i).equals("+")) {
					s=num1+num2;
				}else if(sym.get(i).equals("-")) {
					s=num1-num2;
				}	
				num.set(i, String.valueOf(s));
				num.remove(i+1);
				sym.remove(i);
				i--;

			}
		}
		return num.get(0);
	}
	
}
	

