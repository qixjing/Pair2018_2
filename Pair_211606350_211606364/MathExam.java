


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
	ArrayList<String> list = new ArrayList<String>();// ����һ�����ڴ洢ԭʽ���ʽ������
	 // ����һ�����ڴ���ת������沨��ʽ��ReversePolishNotation̫���� ���Լ�д��RPO��  ��֪���������Ϲ淶��
	    ArrayList<String> RPOlist = new ArrayList<String>();
	    Stack<String> stack1 = new Stack<String>();// ���ڴ���ַ���ջ
		Stack<String> stack2 = new Stack<String>();// ���������ջ


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
   System.out.println("e:/out.txt������");
   out_boolean();
 }
 
 
	public  MathExam(String str) {
		// ����һ����������str��StringTokenizer���󣬲��ṩ��+-����()��Ϊ�ָ�����ָ����Ҫ���طָ���
	StringTokenizer StringTokenizer = new StringTokenizer(str,"+-����()",true);
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
 
//����׺���ʽתת��Ϊ�沨�����ʽ
	public void ReversePolishNotation() {
		for (String str : list) {
			if (str.matches("[0-9]+")) {
				RPOlist.add(str);			
			}else if (str.matches("[\\+\\-\\��\\��\\(\\)]")) {
				stack(str);
			}else {
				System.out.println("�Ƿ����ʽ��");
			}
		}
		while(!stack1.isEmpty()) {
			RPOlist.add(stack1.pop());
		}
	}
	
	// ����һ�����ڴ���ַ��ķ���������+-����()���Ž�stack1��
	 public void stack(String zf) {
		 if (stack1.isEmpty()) { // ��Ϊ��ջ�����ַ�����ջ��
			 stack1.push(zf);
			 return ;
		 }if (judge(zf,stack1.peek())) {// �ж����ȼ�,��Ԥ����ַ����ȼ�����ջ��Ԫ�أ������ַ�����ջ��
			 stack1.push(zf);
			 return ;
		 }else{ // �����ȼ�����ջ��Ԫ�أ����ַ�������沨��ʽ�ӵ�������
			 RPOlist.add(stack1.pop());
			 stack(zf);
		 }
	 }
// ����һ�����������жϵ�ǰ�ַ���ջ��Ԫ�ص����ȼ�������true��false	
	 private boolean judge(String str1, String str2) {
			return Judge(str1) > Judge(str2);
		}
		
		private int Judge(String str) {
			switch(str) {
			 case "��" :
			 case "��" :
				 return 2;
			 case "+" :
			 case "-" :
				 return 1;
			 default:
				 return -1;
			 }
		}

	// ����һ�����������沨��ʽ�ӽ���ķ���
	 public int count(String str1,String str2,String str3) {
		 int a = Integer.parseInt(str2);
		 int b = Integer.parseInt(str1);
		 switch(str3) {
		 case "+":
			 return a+b;
		 case "-": 
			 return a-b;
		 case "��":
			 return a*b;
		 case "��":
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
			     System.out.println("("+i+")"+" "+b+" "+"��"+" "+c+" "+"=");
			    d=b*c;list.add("("+i+")"+" "+b+" "+"��"+" "+c+" "+"="+" "+d);}
			    if(rd==0) {
			     while(c==0) {c=(int)(Math.random()*10);}
			     System.setOut(ps);
			     System.out.println("("+i+")"+" "+b+" "+"��"+" "+c+" "+"=");
			     if(b%c==0) {d=b/c;list.add("("+i+")"+" "+b+" "+"��"+" "+c+" "+"="+" "+d);}
			     if(b%c!=0) {d=b/c;f=b%c;list.add("("+i+")"+" "+b+" "+"��"+" "+c+" "+"="+" "+d+"."+"."+"."+f);}}
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
		 char[] operator=new char[]{'+','-','��','��'};
			Random random=new Random();
			ArrayList<String> list1 = new ArrayList<String>();
			for(int i=1;i<=a;i++){
				int n=random.nextInt(2)+2; //2-4�������
				int[] number=new int[n+1]; 
				String bds=new String();
				int rd = (int)Math.random()*100;
				for(int j=0;j<=n;j++){
					number[j]=random.nextInt(100)+1; //4-5������
				}
				for(int j=0;j<n;j++){
					int s=random.nextInt(4);//���ѡ��ĳ�������
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

