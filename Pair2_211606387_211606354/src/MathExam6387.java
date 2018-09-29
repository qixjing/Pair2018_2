import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Random;
import java.util.Stack;

import java.text.SimpleDateFormat;

import java.util.Scanner;


public class MathExam6387 {

	private static Scanner in;
	private static int x,y,z,f,h,g;
	private static String[] str ;
	private static String rem;

	public static void main(String args[]) throws FileNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		int n ;
//		in = new Scanner(System.in);
//		n = in.nextInt();
//		g = in.nextInt();
//		命令指令输入 String[] 转化为 int
		int i =Integer.valueOf(args[0]).intValue();//输入题目
		int g =Integer.valueOf(args[1]).intValue();//输入年级
		//输出保存在文件中
		File file = new File("out6387.txt");
		PrintStream ps = new PrintStream(file);
		System.setOut(ps);
		ChoiceGrade(i, g);
		
	}
	
	//一年级加法出题
	public static void GradeOne( int n ) {
		str = new String[n];
		for (int i = 0; i < n ; i++) {
			x = (int)(Math.random()*100);
			y = (int)(Math.random()*100);
			f = (int)(Math.random()*2);
			// 判断加法和减法 0为加法
			if ( f == 0) {
				System.out.println("(" + (i+1) + ")" +" " + x + "+" + y + " = " );
				str[i] = "(" + (i+1) + ")" +" " + x + "+" + y + " " + "=" + " " + (x+y);
			}
			else {
				if (x-y < 0) {
					System.out.println("(" + (i+1) + ")" +" " + y + "-" + x + " = " );
					str[i] = "(" + (i+1) + ")" +" " + y + "-" + x + " " + "=" + " " + (y-x);
				}
				else {
					System.out.println("(" + (i+1) + ")" +" " + x + "-" + y + " = " );
					str[i] = "(" + (i+1) + ")" +" " + x + "-" + y + " " + "=" + " " + (x-y);
				}
			}
		}
		System.out.println("--------标准答案---------");
		//输出答案
		for(String a:str)
			System.out.println(a);
		//输出日期和学号姓名
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		System.out.println("              211606387 姓名：叶宏奇  " +sdf.format(new Date()));
	}
	
	//二年级乘法出题
	public static void GradeTwo( int n ) {
		str = new String[n];
		for (int i = 0; i < n; i++) {
			x = new Random().nextInt(10) + 1;
			y = new Random().nextInt(10) + 1;
			f = (int)(Math.random()*2);
			
			if ( f == 0 && y!=0 && x!=0) {
				System.out.println("(" + (i+1) + ")" +" " + x + " × " + y + " = " );
				str[i] = "(" + (i+1) + ")" +" " + x + " × " + y + " " + "=" + " " + (x*y);
			}
			else {
				if (x-y < 0 ) {
					System.out.println("(" + (i+1) + ")" +" " + y + " ÷ " + x + " = " );
					str[i] = "(" + (i+1) + ")" +" " + y + " ÷ " + x + " " + "=" + " " + (y/x) + JudgementRemainder(y,x);
				}
				else {
					System.out.println("(" + (i+1) + ")" +" " + x + " ÷ " + y + " = " );
					str[i] = "(" + (i+1) + ")" +" " + x + " ÷ " + y + " " + "=" + " " + (x/y) + JudgementRemainder(x,y);
				}
			}
		}
		System.out.println("--------标准答案--------");
		//输出答案
		for(String a:str)
			System.out.println(a);
		//输出日期和姓名学号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		System.out.println("              211606387 姓名：叶宏奇  " +sdf.format(new Date()));
	}
	
	//三年级混合运算
	private static  void GradeThree( int n ) {
		// TODO 自动生成的方法存根
		str = new String[n];
		
		for (int i = 0; i < n; i++) {
			x = new Random().nextInt(10) + 1;
			y = new Random().nextInt(10) + 1;
			z = new Random().nextInt(10) + 1;
			f = (int)(Math.random()*3);
			h = (int)(Math.random()*3);
			//int 转化为 String
			String x1 = String.valueOf(x);
			String y1 = String.valueOf(y);
			String z1 = String.valueOf(z);
			
			String [] strArr = { x1,y1,z1,SymbolicJudgment(h),SymbolicJudgment(f) };
			System.out.println("(" + (i+1) + ")" +" " + z1 + " " + SymbolicJudgment(h) + " " + y1 + " " + SymbolicJudgment(f) + " " + x1 + " = " );
			str[i] = "(" + (i+1) + ")" +" " + z1 +" " + SymbolicJudgment(h) +" " + y1 +" " + SymbolicJudgment(f) +" " + x1 + " = "  + ReversePolish(strArr);
		}
		System.out.println("--------标准答案---------");
		//输出答案
		for(String a:str)
			System.out.println(a);
		//输出日期和学号姓名
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		System.out.println("              211606387 姓名：叶宏奇  " +sdf.format(new Date()));
	}
	//选择年级
	public static int ChoiceGrade( int n , int g ) {
		int TextN = 0;
		if ( g == 1 ) {
			GradeOne(n);
			TextN = 1;
		}
		else if ( g == 2) {
			GradeTwo(n);
			TextN = 2;
		}
		else if ( g == 3) {
			GradeThree(n);
			TextN = 3;
		}
		else {
			System.out.println("程序员能力有限，高年级还未完成");
			TextN = g;
		}
		return TextN;
	}
	
	//判断余数
	public static String JudgementRemainder( int x , int y ) {
		if( (x%y) == 0) {
			
			rem = " ";
		}
		else {
			rem = "..." + (x%y) + " ";
		}
		return rem;
	}
	
	//判断符号
	 private static String SymbolicJudgment(int f) {
		// TODO 自动生成的方法存根
		 if (f == 0) {
			rem = "+";
		}
		 else if (f == 1) {
			 rem = "-";
		}
		 else{
			rem = "*";
		}
		return rem;
	}
	
	//逆波兰表达式 后缀运算 用于混合运算
    private static String ReversePolish (String[] strArr){
        String str = "+-*/";
        Stack<String> stack = new Stack<String>();
        //遍历数组中的每一个元素
        for(String s : strArr){
        	//如果是数字,放入栈中
            if(!str.contains(s)){
                stack.push(s);
            }
            else{
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                switch(s){
                case "+" :
                	stack.push(String.valueOf(a+b));
                    break;
                case "-" :
                	stack.push(String.valueOf(a-b));
                    break ;
                case "*" :
                    stack.push(String.valueOf(a*b));
                    break;
                }
            }
        }
        return stack.pop();
    }
}