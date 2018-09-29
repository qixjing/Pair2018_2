import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.io.OutputStream;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MathExam6335 {
	/*
 	*本次作业不提供 markdown 模板，但要求博客按照 结构优化、单元测试、性能调优三个部分展开。

	*【单元测试】
	*说明自己设计单元测试的思路
	*选择部分单元测试代码发布在博客中，并说明测试的函数，构造测试数据的思路
	*博客中附上单元测试得到的测试覆盖率截图
	*单元测试总体覆盖率要求达到90%以上，否则单元测试部分视作无效

	*【结构优化】
	*在博客中给出程序的UML类图
	*在博客中给出程序的运行流程图
	*详述程序做出重构的部分，与重构的原因
	*详述重构后每个模块的功能

	*【性能调优】
	*贴出优化前效能分析工具的结果截图
	*描述程序的性能瓶颈
	*给出优化方案
	*贴出优化后效能分析工具的结果截图
	*
 */	
	int firstNumber, secondNumber;		
	int symbol;	//运算符号判断
	int result;
	static int grade;
	static int count;		
	boolean calflag = true;  //返回一个布尔类型，控制题目规范
	
	//调度场和逆波兰函数定义
	Stack<String> operators = new Stack<String>();	//存储操作符
	Stack<String>  operands= new Stack<String>();	//存储操作数
//	private static StringBuilder postfixExpression=new StringBuilder(); //展示表达式
	
	String[] str_ArithmeticProblem = new String[10000];	//存放算术题
	String[] str_MathAnswer = new String[10000];	//存放题目和标准答案
	
	public MathExam6335(String[] args){
		inPut(args[0], args[1],args[2],args[3]);
		mathProblem(count);
		outPut();
	}

	private void inPut(String str0,String str1, String str2, String str3) {
		// TODO Auto-generated method stub
		boolean flag1 = true;		//判断题目年级参数
		boolean flag2 = true;
		
		Scanner in = new Scanner(System.in);
		String regex1 = "0*[1-9]{1}\\d{0,3}";		//正则表达式判断输入参数为非零正整数
		String regex2 = "0*[1-3]{1}{0}";
		Pattern pattern1 = Pattern.compile(regex1);		//定义两组对正则表达式的编译对象
		Pattern pattern2 = Pattern.compile(regex2);
		Matcher matcher1,matcher2;		//定义两组判断参数与正则表达式的操作引擎
		
		while (true) {		
			try {
				if(str0.equals("-n") && str2.equals("-grade")){		//首先匹配输入的参数类型，args[0] 和 args[2] 的内容
					matcher1 = pattern1.matcher(str1);		
					matcher2 = pattern2.matcher(str3);
					flag1 = matcher1.matches();
					flag2 = matcher2.matches();
					if(!flag1 || !flag2){
						throw new NumberFormatException();
					} else {
						count = Integer.valueOf(str1);
						grade = Integer.valueOf(str3);
					}
				}else if(str0.equals("-grade") && str2.equals("-n")){		//regex1和3的表达式都匹配成功
					matcher1 = pattern1.matcher(str3);
					matcher2 = pattern2.matcher(str1);
					flag1 = matcher1.matches();		//题数匹配规范性
					flag2 = matcher2.matches();		//年级匹配规范性
					if(!flag1 || !flag2){		//两个参数不符合正则表达式规范就进入异常
						throw new NumberFormatException();		
					} else {	
						count = Integer.valueOf(str3);
						grade = Integer.valueOf(str1);
					}
				} else {
					System.out.println("输入参数类型有误，即将退出程序");
					System.exit(0);
				}				
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("输入参数不符合规范，即将退出程序");
				System.exit(0);
			}			
			in.close();
			break;
		}
	}
	
	private void outPut() {
		// TODO Auto-generated method stub
		File file = new File("out.txt");
		
		if(!file.exists()){	//判断文件是否存在
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("文件创建时出现错误!");
			}
		}
		
		try {
			String str = "\r\n";
			byte[] bytes = str.getBytes();		//string类型转换为能被机器识别的二进制码
			
			FileOutputStream fos = new FileOutputStream(file);	//文件写入流
			for (int i = 0; i < count; i++) {
				str_ArithmeticProblem[i] = "( " + (i+1) + " )" + str_ArithmeticProblem[i];	//给题目添加序号
				byte[] b_ArithmeticProblem = str_ArithmeticProblem[i].getBytes();
				fos.write(b_ArithmeticProblem);
				fos.write(bytes);
			}
			fos.write(bytes);
			fos.flush();	//刷新内存
			for (int i = 0; i < count; i++) {
				byte[] b_MathAnswer = str_MathAnswer[i].getBytes();
				fos.write(b_MathAnswer);
				fos.write(bytes);
			}
			fos.flush();
			fos.close();	//关闭文件输出流
		
			System.out.print("-------  本次共生成" + count + "道小学"+ grade + "年级算数题，请打开out.txt文件查看详情    -------"); 	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("未找到指定文件!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件写入有误!");
		} 	
	}
	
    private static boolean isOperator(String operator){
    	//判断操作符
        if (operator.equals("+")||operator.equals("-")||operator.equals("×")||operator.equals("÷")
                ||operator.equals("(")||operator.equals(")")) {
            return true;
        }
        return false;
    }
	
    private static int priority(String s){
    	//操作符优先级
        if (s.equals("+") || s.equals("-")) {
        	return 1;
        } else if (s.equals("×") || s.equals("÷")) {
        	return 2;
        } else if(s.equals("(") || s.equals(")")){
        	return 3;
        } else{
        	return 0;
        }
    }
    
    private static int Calculation(int n,int m,String operator){
    	//计算结果
    	int result = -4567;
    	if (operator.equals("+")) {
    		result = n + m;
    	} else if (operator.equals("-")) {
    		if(m > n) {
    			result = -2;	//返回一个负数，差值为负
    		}else {
    			result = n - m;		
			}
    		
    	} else if (operator.equals("×")) {
    		result = n * m;
    		
    	} else if (operator.equals("÷")) {
    		if (m == 0) {
				result = -1;	//返回一个负数，除数为0
				
			} else {
				result = n / m;
				
			}
    	}
    	
    	return result;
    }

	private void toPostfixExpression(String str_mix){
		//逆波兰算法——[中缀表达式转后缀表达式]
		int len = str_mix.length();	//获取表达式的长度
		char ch;	//接受charAt()方法获取的的字符
		String sc;	//保存toString()后的char值
		String str_c = "";
		
		for (int i = 0 ; i <= len-1 ; i++) {
			ch = str_mix.charAt(i); 
			sc = String.valueOf(str_mix.charAt(i));
			
			if(ch >= '0' && ch <= '9'){
				
				while(i < len && str_mix.charAt(i) >= '0' && str_mix.charAt(i) <= '9' ){
					str_c += str_mix.charAt(i);
					i++;
				} 
				
				i--;
				sc = String.valueOf(str_c);
			}
			str_c = "";
			
			if(isOperator(sc))	//判断是否是操作符
			{
				if(operators.isEmpty()){	//判断为空栈，入栈
					operators.push(sc);
					
				} else {
					if(priority(operators.peek()) < priority(sc) && !sc.equals(")")){	
						//栈顶操作符优先级小于当前操作符优先级且操作符不为右括号，入栈
						operators.push(sc);
					} else if(priority(operators.peek()) >= priority(sc) && !sc.equals(")")){
						while(!operators.empty() && !operators.peek().equals("(")	//栈不为空，当前栈顶操作符不为左括号
								&& priority(operators.peek()) >= priority(sc)){		//操作符优先级小于等于当前栈顶操作符优先级
							do {
//								operator_Add = operators.pop();
//								postfixExpression.append(operator_Add);
								operands.push(operators.pop());
							} while (false);	}	// 栈顶操作符是左括号时停止压栈
						operators.push(sc);		//否则直接入栈
					} else if(sc.equals(")")){	//当前扫描到的操作符为右括号(不做入栈操作)，依次压栈相匹配的左括号内容
						do {
//							operator_Add = operators.pop();
//							postfixExpression.append(operator_Add);
							operands.push(operators.pop());
						} while (!operators.peek().equals("("));
						operators.pop();	//弹出栈顶无用操作符左括号
					}
				}
				
			} else {	//非操作符
				if(!sc.equals(" ")){
					operands.push(sc);	
				}
			}
		}
		
		while(!operators.empty()){	//结束字符串扫描后操作符的栈不为空则则压栈
			operands.push(operators.pop());
		}
	}
	
	private int reversePolish() {
		// TODO Auto-generated method stub
		//计算后缀表达式
		String oper;	//存储栈里的元素
		Stack<Integer> postfixNumber = new Stack<Integer>();	//定义一个存储数字或结果的栈
		
		while(!operands.isEmpty()) {	//掉转栈里的后缀表达式出栈顺序
			operators.push(operands.pop());
		}
		
		while(!operators.isEmpty()) {	//存储后缀表达式的栈不为空时，计算
			oper = operators.pop();
			if(!isOperator(oper)){	//判断非操作符，入数组栈栈
				postfixNumber.push(Integer.parseInt(oper));
			} else{
				int m = postfixNumber.pop();	//返回符号两边的数字
				int n = postfixNumber.pop();
				if (Calculation(n, m, oper) < 0) {	//计算后返回值为负数，则表达式存在“差值为负”或者“除数为0”的情况，并跳出循环
					calflag = false;
					while(!operators.isEmpty()) {	//保证下次使用栈时，一定为空
						operators.pop();
					}
					break;
				}
				
				postfixNumber.push(Calculation(n, m, oper));	//结果入栈
				
				if(operators.isEmpty()) {	//控制当栈为空时，结束循环
					break;
				}
			}
		}
		
		if(calflag) {	//控制题目规格化
			return postfixNumber.pop();
		}else {
			calflag = true;
			return -1;
		}
	}
	
	private void mathProblem(int count) {	
		//生成算术题
		Random rnd = new Random();
		
		for (int i = 0; i < count; i++) {
			symbol = rnd.nextInt(2);
			firstNumber = (int)(Math.random()*20+1);
			secondNumber = (int)(Math.random()*20+1);
			
			if(grade == 1){
				switch (symbol) {
				case 0:
					add(firstNumber,secondNumber,i);
					break;
					
				case 1:
					sub(firstNumber,secondNumber,i);
					break;
					
				default:
					break;
				}
			} else if(grade == 2){
				switch (symbol) {
				case 0:
					mul(firstNumber,secondNumber,i);
					break;
					
				case 1:
					div(i);
					break;
					
				default:
					break;
				}
			}else{
				fourMixed(i);
			}	
		}
	}
	
	private String operator() {		
		// TODO Auto-generated method stub
		//随机生成操作符
		Random in =new Random();
		String[] ro = {"+","-","×","÷"};
		return ro[in.nextInt(4)];
	}
	
	private int operand() {
		// TODO Auto-generated method stub
		return (int)(Math.random()*100+1);
	}
	
	private void fourMixed(int i) {
		// TODO Auto-generated method stub
		//四则运算：括号算操作符，总共2-4个操作符
		int res = 0; 

			while (true) {
				StringBuilder add = new StringBuilder();	//定义一个Bulider类型的String，保存生成的表达式
				for(int num = (int)(Math.random()*3+2);num > 0 ; num--) {	//生成表达式，操作符的个数为2~4个
					add.append(operand() + " " + operator()+ " ");  			
				}
				
				add.append(operand() + " ");	//数字的个数是(操作符个数+1)
				add.insert(0, " ");
				
				int positionAdd = add.indexOf("+");		//返回操作数在表达式的位置
				int positionSub = add.indexOf("-");
				int parentheses = (int)(Math.random()*2); 	//控制是否添加括弧
				
				if(positionAdd > 0 && parentheses == 0) {	//匹配到表达式中有加号操作符，且括弧为添加状态
					if(add.charAt(positionAdd+2) >= '0' && add.charAt(positionAdd+2) <= '9') {
						int a = positionAdd+2;
						do {
							a++;
						}while(add.charAt(a) >= '0' && add.charAt(a) <= '9');
						add.insert(a, " )");	//插入右括号
					}
					if(add.charAt(positionAdd-2) >= '0' && add.charAt(positionAdd-2) <= '9') {
						int a = positionAdd-2;
						do {
							a--;
						}while(add.charAt(a) >= '0' && add.charAt(a) <= '9');
						add.insert(a, "(");		//插入左括号
					}
				} else if(positionSub > 0 && parentheses == 0) {	//匹配到表达式中有减号操作符，且括弧为添加状态
					if(add.charAt(positionSub+2) >= '0' && add.charAt(positionSub+2) <= '9') {
						int a = positionSub+2;
						do {
							a++;
						}while(add.charAt(a) >= '0' && add.charAt(a) <= '9');
						add.insert(a, " )");	//插入右括号
					}
					if(add.charAt(positionSub-2) >= '0' && add.charAt(positionSub-2) <= '9') {
						int a = positionSub-2;
						do {
							a--;
						}while(add.charAt(a) >= '0' && add.charAt(a) <= '9');
						add.insert(a, "(");		//插入左括号
					}

				}

				str_ArithmeticProblem[i] = String.valueOf(add);	
//				System.out.println(add);
				toPostfixExpression(str_ArithmeticProblem[i]);
				res = reversePolish();	//获取结果
				
				if (res < 0) {	//结果为负数，表达式不符合规格，重新生成
					continue;
				} else {	//结束循环
					break;
				}	
			}	
			str_MathAnswer[i] ="( " + (i+1) + " ) " + str_ArithmeticProblem[i] + " = " + res;		
			
	}
	
	private void add(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		/*
		 * 加法：
		 *  1.一二年级的加法的两个加数在20以内。
		 * 
		 */
		result = n1 + n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " + " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " + " + n2 + " = " + result;
	}
	
	private void sub(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		/*
		 * 减法：
		 * 
		 * 1.一二年级两数之差应该在大于0；
		 * 2.被减数和减数在20以内。
		 */
		if (n1 < n2) {		//差为负数，交换数值
			int num;
			num = n1;
			n1 = n2;
			n2 = num;
		}
		result = n1 - n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " - " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " - " + n2 + " = " + result;
	}
	
	private void mul(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		/*
		 * 乘法：
		 * 
		 * 1.一二年级的乘法运算应该在0-9以内【九九乘法表】；
		 * 
		 */
		if (n1 > 9) {
			n1 = (int)(Math.random()*10);
		} 
		if (n2 > 9) {
			n2 = (int)(Math.random()*10);
		}
		result = n1 * n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " × " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " × " + n2 + " = " + result;
	}
	
	private void div(int i) {
		// TODO Auto-generated method stub
		/*
		 * 除法：
		 * 
		 * 1.一二年级的除法运算应该在”九九乘法表“范围以内；
		 * 2.分母不能为”0“。
		 * 
		 */
		while(true){
			int n1 = (int)(Math.random()*82);
			int n2 = (int)(Math.random()*81+1);
			if(n1 % n2 == 0){
				result = n1 / n2;
				str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " ÷ " + n2;
				str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " ÷ " + n2 + " = " + result;
			}else if(n1 % n2 != 0){
				result = n1 / n2;
				str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " ÷ " + n2;
				str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " ÷ " + n2 + " = " + result + "..." + (n1 % n2);
			}
			break;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MathExam6335(args);	
	}

}
