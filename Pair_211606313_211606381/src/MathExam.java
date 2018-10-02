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
import java.util.regex.Pattern;

public class MathExam {
	//mp保存操作符的优先级
	static HashMap<Character, Integer> mp = new HashMap<Character, Integer>();
	static String errorMessage = "输入的参数形式有误，请按照 -n 题量 -grade 数量   或者 -grade 数量   -n 题量  格式输入。";
	static int grade;
	static StringBuffer[] topic;
	static StringBuffer[] standAnswer;
	
	static char[] Operator = { '+', '-', '×', '÷' };
	static {
		mp.put('-',1);
		mp.put('+',1);
		mp.put('×',2);
		mp.put('÷',2);
	}

	public static void main(String[] args){
//		System.out.println(errorMessage);
		if(judgmentParameter(args)) {
			int len = Integer.parseInt(args["-n".equals(args[0]) ? 1 : 3]);
			grade = Integer.parseInt(args["-n".equals(args[0]) ? 3 : 1]);
			topic = new StringBuffer[len];
			standAnswer = new StringBuffer[len];
			for(int i = 0;i<len;i++) {
				topic[i] = new StringBuffer();
				standAnswer[i] = new StringBuffer();
			}
			for(int i = 0;i < len;i++) {
				Character[] infixExpression = generatingTopic(grade);
				try {
		        	topic[i].append("(" + (i+1) + ")");
		        	standAnswer[i].append("(" + (i+1) + ")");
		        	calPoland(infixExpression,i,grade);
		        	topic[i].append(System.lineSeparator());
		        	if(i != len - 1){
		        		standAnswer[i].append(System.lineSeparator());
		        	}
				}catch (ArithmeticException e) {
					// 生成的题目不合法，清空StringBuffer
					topic[i].setLength(0);
					standAnswer[i].setLength(0);
					i--;
				}

			}
			// 输出到文件
			try {
				write("out.txt");
			} catch (IOException e) {
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
	static boolean judgmentParameter(String[] args) {
		if(args.length < 4 || args.length > 4) { 
			return false;
		}else {
			// 1  判断是否是用 -n ， -grade 标识 "-n", "1000","2","-grade"
			if(!(("-n".equals(args[0]) && "-grade".equals(args[2]))|| ("-grade".equals(args[0]) && "-n".equals(args[2])))) {

				return false;
			}
			
			// 2 去除数字参数的前导0
			args[1] = args[1].replaceFirst("^0*", "");
			args[3] = args[3].replaceFirst("^0*", "");
			
			// 3 检验题数参数是否都是数字
			Pattern pattern = Pattern.compile("[0-9]*");
			boolean matches = pattern.matcher(args["-n".equals(args[0]) ? 1 : 3]).matches();
			
			if (matches && args["-n".equals(args[0]) ? 1 : 3].length() > 4) {
				errorMessage = "题目数量过大，请重新运行，输入参数";
				return false;
			} else if (!matches) {
				errorMessage = "题目数量不是正整数，请重新运行，输入一个正整数";
				return false;
			}
			
			// 4 检验年级参数是否是1~3
			Pattern compile = Pattern.compile("[1-3]?");
			boolean matches2 = compile.matcher(args["-n".equals(args[0]) ? 3 : 1]).matches();
			
			if (!matches2) {
				errorMessage = "目前只支持1~3年级，请重新运行，输入1~3中的一个数字";
				return false;
			}    
		}
		return true;
	}
	
	/**
	 * 作用：生成题目操作符
	 * @param len
	 * @param grade
	 * @return 
	 * @throws Exception 
	 */
	static Character[] generatingTopic(int grade){

		IDemand demand = null;
		//生成操作数
		try {
			Class<?> cls = Class.forName("Demand" + grade);
			demand = (IDemand) cls.newInstance();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Character[] infixExpression = demand.operatorGeneration();
		// 将生成的操作符在数组[2,n-2]范围内随机排序
		do {
			List<Character> list = new ArrayList<Character>();  
			for(int j = 2;j < infixExpression.length-1;j++){  
	            list.add(infixExpression[j]);  
	        }  
	          
	        Collections.shuffle(list);  
	          
	        Iterator<Character> ite = list.iterator();  
	        int k = 2;
	        while(ite.hasNext()){  
	            infixExpression[k] = ite.next();
	            k++;
	        }
	    }while(isInfixExpre(infixExpression));
		
        return infixExpression;
	}

	/**
	 * 作用：判断此后缀表达式是否错误
	 * @param infix 只含操作符的后缀表达式数组
	 * @return 错误返回true，正确返回false
	 */
	static boolean isInfixExpre(Character[] infix) {
		if(infix[infix.length-1] == null) {
			return true;
		}
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
	 * 作用：生成最终题目并计算逆波兰表达式
	 * @param infix 后缀表达式数组
	 * @param n 第几个题目
	 * @param str 操作符数组
	 * @return 返回计算结果
	 * @throws Exception 当表达式计算过程中出错时抛出异常
	 */
	static int calPoland(Character infix[],int n,int grade) throws ArithmeticException{
		ArrayList<Character> chlist = new ArrayList<Character>();
		for (Character ch : infix) {
			if(ch != null) {
				chlist.add(ch);
			}
		}
		
		IDemand demand = null;
		int remainder = 0; // 余数

		try {
			Class<?> cls = Class.forName("Demand" + grade);
			demand = (IDemand) cls.newInstance();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
//		if(grade == 3) {
//			demand = new Demand3();
//		}else if (grade == 2) {
//			demand = new Demand2();
//		}else if (grade == 1) {
//			demand = new Demand1();
//		}
		
		Stack<Object> s = new Stack<Object>();
		Stack<String> tops = new Stack<String>();
		int k = 1;//运算符计数器
		for(int i = 0; i < infix.length; i++)
		{
			if(infix[i] == null) {
				s.push(' ');
			}else{				
				int[] num = new int[2];
				String str1,str2;
				Object a = s.pop();
				Object b = s.pop();
				
				boolean falg = demand.operandGeneration(a, b, infix[i],num);

				
				if(falg) {
					if(a instanceof Character) {
						str1 = tops.peek();
						tops.pop();
					}else {
						str1 = " " + num[0];
					}
					if(b instanceof Character) {
						str2 = tops.peek();
						tops.pop();
					}else {
						str2 = " " + num[1];
					}
				}else {
					if(a instanceof Character) {
						str1 = " " + num[0];
					}else {
						str1 = tops.peek();
						tops.pop();
					}
					if(b instanceof Character) {
						str2 = " " + num[1];
					}else {
						str2 = tops.peek();
						tops.pop();
					}
				}
				
				
				switch(infix[i])
				{
					case '+':
						s.push(num[1] + num[0]);
						break;
					case '-':
						s.push(num[1] - num[0]);
						break;
					case '×':
						s.push(num[1] * num[0]);
						break;
					case '÷':
						s.push(num[1] / num[0]);
						if(demand.isRemainder()) {
							remainder = num[1] % num[0]; // 余数
						}
						break;
				}
				tops.push(str2 + " " + infix[i] + str1);
				int j;
				for(j = k;j<chlist.size();j++) {
					if(mp.get(infix[i]) < mp.get(chlist.get(k).charValue())||(mp.get(infix[i]) == mp.get(chlist.get(k).charValue()) && chlist.get(k).charValue() == '-')) {
						break;
					}
				}
				if(j<chlist.size()) {
					String s1 = tops.pop();
					tops.push(" (" + s1 + " )");
				}
				k++;
			}
		}
		topic[n].append(tops.peek());
		standAnswer[n].append(tops.pop());
		standAnswer[n].append(" = " + s.peek());
		if(demand.isRemainder()) {
			standAnswer[n].append("" + (remainder != 0 ? ("..." + remainder) : ""));
		}

		return ((Integer) s.pop()).intValue();
	}
	
	/**
	 * 作用：将生成的题目和和答案写入指定文件
	 * @param filename       将要写入的文件名
	 * @throws IOException   当文件名不合法时抛出异常
	 */
	static void write(String filename) throws IOException {
		// 步骤1：确定输出的文件（目的地）
		// 如果filename中包含路径，必须确保路径已存在
		File file = new File(filename);
		File parentFile = file.getParentFile();
		if (parentFile != null && !parentFile.exists()) {
			parentFile.mkdirs();
		}
		file.createNewFile();
		// 步骤2：创建指向文件的输出流
		OutputStream out = new FileOutputStream(file);
		// 步骤3：写入数据
		for (StringBuffer tp : topic) {
			out.write(tp.toString().getBytes());
		}
		out.write(System.lineSeparator().getBytes());
		for (StringBuffer sa : standAnswer) {
			out.write(sa.toString().getBytes());
		}
		// 步骤4：关闭
		out.close();
	}
}