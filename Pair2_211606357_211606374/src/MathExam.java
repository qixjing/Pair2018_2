import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import javax.sql.rowset.CachedRowSet;
public class MathExam {
	int stack2length = 0;
	public String[] answerlist = new String[10000];				//创建多个数组存储问题和答案
	public String[] questionlist = new String[10000];
	public String[] yushulist = new String[10000];
	public int n = 0;
	public int m = 0;
	int ab = (int)(Math.random()*4);
	int ac = (int)(Math.random()*20+1);
	MathExam(){
		
	}
	public MathExam(String[] args) throws IOException{
		Choose(args);
	}
	
	public int Choose(String[] args) throws IOException {
		
		//System.out.println("请输入问题数目和所需年级：");	
		for(;;) {
		Scanner input=new Scanner(System.in);
			
		
		int [] z = new int [args.length];									
		String [] x = new String[args.length];
//		n = input.nextInt();
//		m = input.nextInt();
		x[0] = args[0];
		x[1] = args[1];
		if(x[0].equals("-n")) {
			z[0] = Integer.parseInt(args[1]);
			z[1] = Integer.parseInt(args[3]);
			n = z[0];
			m = z[1];
		}
		else if(x[0].equals("-grade")){
			z[0] = Integer.parseInt(args[3]);
			z[1] = Integer.parseInt(args[1]);
		    n = z[0];
			m = z[1];
		}
		
		if(n <= 0 || m <= 0) {										//判断输入的数是否大于0
			System.out.println("请输入大于0的正整数");
			args[0] = input.next();
			args[1] = input.next();
			//n = input.nextInt();
			args[2] = input.next();
			args[3] = input.next();
			//m = input.nextInt();
			continue;
		}
		if(m==1) { 
			
			MathExam grade1 = new MathExam();
			grade1.Grade1(n);
			grade1.Write(n);
			return m;
		}
		else if(m==2) {
			MathExam grade2 = new MathExam();
			grade2.Grade2(n);
			grade2.
			Write(n);
			return m;
		}
		else if(m==3){
			//Text01 test = new Text01();
			MathExam grade3 = new MathExam();
			grade3.Grade3(n);
			grade3.Write(n);
			return m;
	}
//		int y = Continue_Exit(
//				args
//				//n,m
//				);
//		
//				
//		if(y==1){
//			continue;
//		}
//		else {
//			break;
//		}
	}	

	}
//	public int Continue_Exit(
//			String[] args
//			//int n,int m
//			) {
//		System.out.println("请选择退出(0)或者继续(1)！");
//		Scanner input=new Scanner(System.in);
//		int g = input.nextInt();
//		if(g == 1) {
//			System.out.println("请输入问题数目和所需年级：");	
//			args[1] = input.next();
//			args[3] = input.next();
//				return 1;
//			}else if(g == 0) {
//				System.out.println("谢谢使用！");
//				return 0;					
//			}
//			else {
//				boolean flag = true;
//				while(flag) {
//					if(g == 1 || g == 0) {
//						flag= false;
//					}
//					else {
//						System.out.println("请输入0或1之中的一个数字！");
//						g=input.nextInt();
//						if(g == 1 || g == 0)
//							break;
//					}
//					}
//				args[1] = input.next();
//				args[3] = input.next();
//				System.out.println("请输入问题数目和所需年级：");
//				return 1;
//
//			}
// 	}
	public int Grade1(int n){
		for (int i = 0; i < n; i++) {
			yushulist[i]="0";
		}
		int k=0;
		for(int i = 0;i < n;i++){
			int a = (int) (Math.random()*20);
			int b = (int) (Math.random()*(20-a));
			int c = (int) (Math.random()*2);
			//StringBuffer question = null;
			//StringBuffer result = null;
			if(c == 0) {
				System.out.println("( "+(i+1)+" ) "+a+" + "+b);
				//question = new StringBuffer("("+(i+1)+")"+a+"+"+b+"=");
				questionlist[i] = "( "+(i+1)+" ) "+a+" + "+b+" =";
				answerlist[i] = " "+(a+b);
				k++;
				//result = new StringBuffer(" "+(a+b));
				
				}
			else {
				b = (int) (Math.random()*a+1);
				System.out.println("( "+(i+1)+" ) "+a+" - "+b);
				//question = new StringBuffer("("+(i+1)+")"+a+"-"+b+"=");
				questionlist[i] = "( "+(i+1)+" ) "+a+"-"+b+" =";
				answerlist[i] = " "+(a-b);
				k++;
				//result = new StringBuffer(" "+(a-b));
				}
			}
		return k;
	}
	public int Grade2(int n){
		for (int i = 0; i < n; i++) {
			yushulist[i]="0";
		}
		int k = 0;
		for(int i = 0;i < n;i++){
			int a = (int) (Math.random()*9+1);
			int b = (int) (Math.random()*9+1);
			int c = (int) (Math.random()*2);
			//StringBuffer question = null;
			//StringBuffer result = null;
			if(c == 0) {
				System.out.println("( "+(i+1)+" ) "+a+" * "+b);
				//question = new StringBuffer("("+(i+1)+")"+a+"*"+b+"=");
				questionlist[i] = "( "+(i+1)+" ) "+a+" * "+b+" =";
				answerlist[i] = " "+(a*b);
				k++;
				//result = new StringBuffer(" "+(a*b));
			}
			else {
				
				System.out.println("( "+(i+1)+" ) "+a+" ÷ "+b);
				//question = new StringBuffer("("+(i+1)+")"+a+"÷"+b+"=");
				questionlist[i] = "( "+(i+1)+" ) "+a+" ÷ "+b+" =";
				answerlist[i] = " "+(a/b);
				yushulist[i] = " "+(a%b);
				k++;
				//result = new StringBuffer(" "+(a/b));
			}
			
		}
//		for (int i = 0; i < n; i++) {
//			System.out.println(yushulist[i]);
//			
//		}
		return k;
	}
	public int Grade3(int n) {
		for (int i = 0; i < n; i++) {
			yushulist[i]="0";
		}
		int k = 0;
		for(int h = 0 ; h < n ; h++) {
			int fu = (int)(Math.random()*3+2);
			StringBuffer[] string = new StringBuffer[2*fu+3];
			int i;
	
				for (i = 0; i < 2*fu-1; i=i+2) {
					ab = (int)(Math.random()*4);
					ac = (int)(Math.random()*20+1);
					string[i] = new StringBuffer();
					string[i].append(" "+number(ac));
					string[i+1] = new StringBuffer();
					string[i+1].append(" "+fuhao(ab));
					//System.out.println(fuhao());
					
				}
				//System.out.println(i);
				ac = (int)(Math.random()*20+1);
				string[i] = new StringBuffer();
				string[i].append(" "+number(ac));
				StringBuffer a = new StringBuffer("");
				
				
				StringBuffer u = new StringBuffer(" (");
				StringBuffer y = new StringBuffer(" )");
				//System.out.println(fu);
					int p = (int)(Math.random()*(2*fu));
					if(p%2==1) {
						p--;
					}
					int o = (int)(Math.random()*(2*fu+2-p-2)+p+2);
					if(o%2==0) {
						o++;
					}
					//System.out.println(p+" "+o);
				if(o==i+1) {
					string[i+1] = new StringBuffer();
					string[i+1]=y;
				}
				else {
					for (int j = i+1; j > o; j--) {
					string[j] = new StringBuffer();
					string[j]=string[j-1];
				}
				string[o]=y;
				}
				
				for (int j = i+2; j > p; j--) {
					string[j]=string[j-1];
				}
				string[p]=u;
				//System.out.println(string[j]);
	//			for (int j = 0; j < i+3; j++) {
	//				System.out.println(string[j]); 
	//				//a.append(b[i]);
	//			}
				for (int j = 0; j < i+3; j++) {
					 a.append(string[j]);
				}
				MathExam m= new MathExam();
				//String input1 = a.toString(); 
				
				String[] getchar = m.getPoland(a.toString());
				String s = m.getResUsePoland(getchar);
				int l = Integer.parseInt(s);
		
				//int l = Integer.parseInt(m.getResUsePoland(m.getPoland(a.toString())));
				if(l<0) {
					h--;
					continue;
				}
				System.out.print("( "+(h+1)+" )");
				System.out.println(a.toString());
				
//				for (int j = 0; j < getcha.length; j++) {
//					System.out.println(getcha[j]);
//				}
				
				//System.out.println("RES:"+s);
				questionlist[h] = "( "+(h+1)+" ) "+a.toString()+" =";
				answerlist[h] = " "+s;
				k++;
		}
		return k;
		}
	public String fuhao(int a) {
		
		switch (a) {
		case 0:
			return "+";
		case 1:
			return "-";
		case 2:
			return "*";
		case 3:
			return "/";
		}
		return null;
	}
	public int number(int a) {
		
		
		return a;
		
	}
	public char[] stringTransferChar(String input) {
		 
		char[] inputchar;
		inputchar = input.toCharArray();
		return inputchar;
	}
 
	public String[] getPoland(String input) {
		Stack<String> stack1 = new Stack<String>();
		Stack<String> stack2 = new Stack<String>();
		char[] inputchararray = this.stringTransferChar(input);
 
		stack1.push("#");
		Boolean pre = false;
		for (char chs : inputchararray) {
			if (chs >= '0' && chs <= '9') {
				if (pre) {
					int temp = Integer.valueOf((String) stack2.pop());
					stack2.push(String.valueOf(temp * 10 + Integer.parseInt(chs + "")));
					//stack2length++;
				} else {
					stack2.push(String.valueOf(chs));
					stack2length++;
					pre = true;
				}
			}
 
			switch (chs) {
			case '(':
				stack1.push(String.valueOf(chs));
				pre = false;
				break;
			case ')':
				while (!(stack1.peek().equals("("))) {
					stack2.push(stack1.pop());
					stack2length++;
				}
				stack1.pop();
				pre = false;
				break;
			case '+':
			case '-':
				while (stack1.peek() != "#") {
					if (stack1.peek().equals("(")) {
						break;
					} else {
						stack2.push(stack1.pop());
						stack2length++;
					}
				}
				stack1.push(String.valueOf(chs));
				pre = false;
				break;
			case '*':
			case '/':
				while (!(stack1.peek().equals("-")) && !(stack1.peek().equals("+")) && !(stack1.peek().equals("#"))) {
					if (stack1.peek().equals("(")) {
						break;
					} else {
						stack2.push(stack1.pop());
						stack2length++;
					}
				}
				stack1.push(String.valueOf(chs));
				pre = false;
				break;
 
			}
 
		}
		while (!stack1.isEmpty() && stack1.peek() != "#") {
			stack2.push(stack1.pop());
			stack2length++;
		}
		String[] chararrfromstack2 = new String[stack2length];
		int i = 0;
		while (!stack2.isEmpty()) {
			chararrfromstack2[i] = (String) stack2.pop();
			i++;
		}
 
		return chararrfromstack2;
	}
	
	public String getResUsePoland(String[] getcha){
		
		Stack<String> stack = new Stack<String>();
		int temx = 0;
		int temd = 0;
		int res = 0;
		String[] datas = new String[getcha.length];
		int k = getcha.length;
		//System.out.println("*****"+k+"****");
		for(int i = 0;i<getcha.length;i++){
			datas[k-1] = getcha[i];
			k--;
		}
		//System.out.println(datas[0]);
		 MathExam m = new MathExam();
		for(int i = 0;i<datas.length;i++){
			if (m.isNum(datas[i])) {
				stack.push(datas[i]);
				
			}else {
				temx = Integer.valueOf((String) stack.pop());
				temd = Integer.valueOf((String) stack.pop());
				switch (datas[i]) {
				case "+":
					res = temx + temd;
					break;
				case "-":
					res = temd - temx;
					break;
				case "*":
					res = temd*temx;
					break;
				case "/":
					if(temx==0) {
						return "-1";
					}
					res = temd/temx;
					break;
 
				default:
					break;
				}
				stack.push(String.valueOf(res));
			}
		}
		return (String) stack.pop();
	}
	
	public boolean isNum(String str){  
		 
		//for (int i = str.length();--i>=0;){  
			//System.out.println(str.length());
			//System.out.println(str.charAt(str.length()-1));
			if (!Character.isDigit(str.charAt(0)))
		   //{  
		    return false;  
		  // }  
		 // } 
		    else
		  return true;  
		}  
	public void Write(int n) throws IOException {
		
		FileOutputStream in =new FileOutputStream("out.txt");
		for (int i = 0; i < n; i++) {
			
			byte[] ba = questionlist[i].getBytes();
			String pa ="\r\n";
			byte[] ta = pa.getBytes(); 
			in.write(ba);
			in.write(ta);
		}
		String wa  = "\r\n";
		System.out.print(wa);
		byte[] ea = wa.getBytes();
		in.write(ea);
		for (int i = 0; i < n; i++) {
			byte[] za = questionlist[i].getBytes();
			byte[] sa = answerlist[i].toString().getBytes();
			in.write(za);
			in.write(sa);
			System.out.println(questionlist[i]+answerlist[i]);
			if(!yushulist[i].equals("0")) {
				byte[] ra =yushulist[i].getBytes();
				String ua = " ......";
				byte[] ya = ua.getBytes();
				in.write(ya);
				in.write(ra);
				System.out.println(questionlist[i]+answerlist[i]+ua+yushulist[i]);
				}
			
			//System.out.println(questionlist[i]);
			String pa ="\r\n";
			byte[] ta = pa.getBytes();
			in.write(ta);
		}
	}

	public static void main(String[] args) throws IOException {
//	// TODO Auto-generated method stub

		new MathExam(args);
		
	}
	
}


