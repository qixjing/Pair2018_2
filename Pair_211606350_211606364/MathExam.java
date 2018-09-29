


import java.util.List;
import java.util.Random;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class MathExam {
	ArrayList<String> list = new ArrayList<String>();// 创建一个用于存储原式表达式的数组
	 // 创建一个用于储存转换后的逆波兰式（ReversePolishNotation太长了 可以简写成RPO吗  不知道符不符合规范）
	    ArrayList<String> RPOlist = new ArrayList<String>();
	    Stack<String> stack1 = new Stack<String>();// 用于存放字符的栈
		Stack<String> stack2 = new Stack<String>();// 用于运算的栈


public static void main(String[] args1) throws IOException {
	//String args1[] = {"-n","50","-grade","3"};
	 int n = 0;
	 int grade = 0;
	if (args1[0].equals("-n")) {
		n = Integer.parseInt(args1[1]);
		grade = Integer.parseInt(args1[3]);
	}
	if (args1[0].equals("-grade")){ 
		n = Integer.parseInt(args1[3]);
		grade = Integer.parseInt(args1[1]);
	}  
  MathExam6350(n,grade); 
 }
 
 
 
 static void MathExam6350(int a,int e) throws IOException {
	 
  PrintStream out = System.out;
  PrintStream ps = new PrintStream("e:/out.txt");
  if(e==1) {
	  	grade1(a);
 }  

  if(e==2) {
	  	grade2(a);
  }
 
  if(e==3) {
	  	grade3(a);	
		}
   System.setOut(ps);
   File file = new File("e:/out.txt");
   FileReader reader = new FileReader(file);
   int fileLen = (int)file.length();
   char[] chars = new char[fileLen];
   reader.read(chars);
   String txt = String.valueOf(chars);
   System.setOut(out);
   System.out.println(txt);
   System.out.println("e:/out.txt已生成");
   out_boolean();
 }
 
 
	public  MathExam(String str) {
		// 构造一个用来解析str的StringTokenizer对象，并提供“+-×÷()”为分隔符，指定需要返回分隔符
	StringTokenizer StringTokenizer = new StringTokenizer(str,"+-×÷()",true);
	while(StringTokenizer.hasMoreTokens()) {
			 list.add(StringTokenizer.nextToken());
			 }
		 }
	
	
public static boolean out_boolean() {
	return true;
}



private static int ys(int x,int y){
		Random random=new Random();
		if(x%y!=0){
			y=random.nextInt(100)+1;
			return ys(x,y);
		}
		else{
			return y; 
		}
	}
 
//将中缀表达式转转化为逆波兰表达式
	public void ReversePolishNotation() {
		for (String str : list) {
			if (str.matches("[0-9]+")) {
				RPOlist.add(str);			
			}else if (str.matches("[\\+\\-\\×\\÷\\(\\)]")) {
				stack(str);
			}else {
				System.out.println("非法表达式！");
			}
		}
		while(!stack1.isEmpty()) {
			RPOlist.add(stack1.pop());
		}
	}
	
	// 创建一个用于存放字符的方法，将“+-×÷()”放进stack1中
	 public void stack(String zf) {
		 if (stack1.isEmpty()) { // 若为空栈，将字符存入栈中
			 stack1.push(zf);
			 return ;
		 }if (judge(zf,stack1.peek())) {// 判断优先级,若预存的字符优先级大于栈顶元素，将此字符存入栈中
			 stack1.push(zf);
			 return ;
		 }else{ // 若优先级低于栈顶元素，则将字符存入存逆波兰式子的数组中
			 RPOlist.add(stack1.pop());
			 stack(zf);
		 }
	 }
// 创建一个方法用来判断当前字符与栈顶元素的优先级，返回true或false	
	 private boolean judge(String str1, String str2) {
			return Judge(str1) > Judge(str2);
		}
		
		private int Judge(String str) {
			switch(str) {
			 case "×" :
			 case "÷" :
				 return 2;
			 case "+" :
			 case "-" :
				 return 1;
			 default:
				 return -1;
			 }
		}

	// 创建一个用来计算逆波兰式子结果的方法
	 public int count(String str1,String str2,String str3) {
		 int a = Integer.parseInt(str2);
		 int b = Integer.parseInt(str1);
		 switch(str3) {
		 case "+":
			 return a+b;
		 case "-": 
			 return a-b;
		 case "×":
			 return a*b;
		 case "÷":
			 return a/b;
		 default :
			 return 0;
		 }
	 }
	 
	 public int count() {
		 for (String str:RPOlist) {
			 if (str.matches("[0-9]+")) {
				 stack2.push(str);
			 }else {
				 stack2.push(String.valueOf(count(stack2.pop(),stack2.pop(),str)));
			 }
		 }
		 return Integer.parseInt(stack2.pop());
	 }
	 public static void grade1(int a) throws IOException {
		 List<String>  list  = new ArrayList<String>();
		 PrintStream ps = new PrintStream("e:/out.txt");
		 for(int i=1;i<=a;i++) {
			   int b =(int)(Math.random()*100);
			   int c =(int)(Math.random()*100);
			   int d;
			   int rd=Math.random()>0.5?1:0; 
			   if(rd==1) {
			    System.setOut(ps);
			    System.out.println("("+i+")"+" "+b+" "+"+"+" "+c+" "+"=");
			   d=b+c;list.add("("+i+")"+" "+b+" "+"+"+" "+c+" "+"="+" "+d);}
			   if(rd==0) {
			    while(b-c<0) {b=(int)(Math.random()*100);}
			    System.setOut(ps);
			    System.out.println("("+i+")"+" "+b+" "+"-"+" "+c+" "+"=");
			    d=b-c;list.add("("+i+")"+" "+b+" "+"-"+" "+c+" "+"="+" "+d);}  
			  }
			  System.setOut(ps);
			  System.out.println(" ");
			  for (int i = 0; i < list.size(); i++) {
			   System.setOut(ps);
			   System.out.println( list.get(i));
	 }
}
	 public static void grade2(int a) throws IOException {
		 List<String>  list  = new ArrayList<String>();
		 PrintStream ps = new PrintStream("e:/out.txt");
		 for(int i=1;i<=a;i++) {
			    int b =(int)(Math.random()*10);
			    int c =(int)(Math.random()*10);
			    int d;
			    int f;
			    int rd=Math.random()>0.5?1:0; 
			    if(rd==1) {
			     System.setOut(ps);
			     System.out.println("("+i+")"+" "+b+" "+"×"+" "+c+" "+"=");
			    d=b*c;list.add("("+i+")"+" "+b+" "+"×"+" "+c+" "+"="+" "+d);}
			    if(rd==0) {
			     while(c==0) {c=(int)(Math.random()*10);}
			     System.setOut(ps);
			     System.out.println("("+i+")"+" "+b+" "+"÷"+" "+c+" "+"=");
			     if(b%c==0) {d=b/c;list.add("("+i+")"+" "+b+" "+"÷"+" "+c+" "+"="+" "+d);}
			     if(b%c!=0) {d=b/c;f=b%c;list.add("("+i+")"+" "+b+" "+"÷"+" "+c+" "+"="+" "+d+"."+"."+"."+f);}}
			   }
			   System.setOut(ps);
			   System.out.println(" ");
			   for (int i = 0; i < list.size(); i++) {
			    System.setOut(ps);
			    System.out.println( list.get(i));
}
	 }
	 public static void grade3(int a) throws IOException {
		 List<String>  list  = new ArrayList<String>();
		 PrintStream ps = new PrintStream("e:/out.txt");
		 char[] operator=new char[]{'+','-','×','÷'};
			Random random=new Random();
			ArrayList<String> list1 = new ArrayList<String>();
			for(int i=1;i<=a;i++){
				int n=random.nextInt(2)+2; //2-4个运算符
				int[] number=new int[n+1]; 
				String bds=new String();
				int rd = (int)Math.random()*100;
				for(int j=0;j<=n;j++){
					number[j]=random.nextInt(100)+1; //4-5个数字
				}
				for(int j=0;j<n;j++){
					int s=random.nextInt(4);//随机选择某个运算符
					bds+=String.valueOf(number[j])+String.valueOf(operator[s]);
					if(s==3){number[j+1]=ys(number[j],number[j+1]);}
				}
				bds+=String.valueOf(number[n]);
				list1.add(bds);
				System.setOut(ps);
				System.out.println("("+i+")"+" "+bds);
			}	
			System.setOut(ps);
			System.out.println(" ");
			for (int i = 0; i < list1.size(); i++) {
			String str = list1.get(i);
			MathExam me = new MathExam(str);
			me.ReversePolishNotation();
			System.setOut(ps);
			System.out.println("("+(i+1)+")"+" "+list1.get(i)+"="+me.count());
	 }
	 }
	 
	
	 
 }

