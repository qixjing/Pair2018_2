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
	//mp��������������ȼ�
	static HashMap<Character, Integer> mp = new HashMap<Character, Integer>();
	static String errorMessage = "����Ĳ�����ʽ�����밴�� -n ���� -grade ����   ���� -grade ����   -n ����  ��ʽ���롣";
	static int grade;
	static StringBuffer[] topic;
	static StringBuffer[] standAnswer;
	
	static char[] Operator = { '+', '-', '��', '��' };
	static {
		mp.put('-',1);
		mp.put('+',1);
		mp.put('��',2);
		mp.put('��',2);
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
					// ���ɵ���Ŀ���Ϸ������StringBuffer
					topic[i].setLength(0);
					standAnswer[i].setLength(0);
					i--;
				}

			}
			// ������ļ�
			try {
				write("out.txt");
			} catch (IOException e) {
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
	static boolean judgmentParameter(String[] args) {
		if(args.length < 4 || args.length > 4) { 
			return false;
		}else {
			// 1  �ж��Ƿ����� -n �� -grade ��ʶ "-n", "1000","2","-grade"
			if(!(("-n".equals(args[0]) && "-grade".equals(args[2]))|| ("-grade".equals(args[0]) && "-n".equals(args[2])))) {

				return false;
			}
			
			// 2 ȥ�����ֲ�����ǰ��0
			args[1] = args[1].replaceFirst("^0*", "");
			args[3] = args[3].replaceFirst("^0*", "");
			
			// 3 �������������Ƿ�������
			Pattern pattern = Pattern.compile("[0-9]*");
			boolean matches = pattern.matcher(args["-n".equals(args[0]) ? 1 : 3]).matches();
			
			if (matches && args["-n".equals(args[0]) ? 1 : 3].length() > 4) {
				errorMessage = "��Ŀ�����������������У��������";
				return false;
			} else if (!matches) {
				errorMessage = "��Ŀ�������������������������У�����һ��������";
				return false;
			}
			
			// 4 �����꼶�����Ƿ���1~3
			Pattern compile = Pattern.compile("[1-3]?");
			boolean matches2 = compile.matcher(args["-n".equals(args[0]) ? 3 : 1]).matches();
			
			if (!matches2) {
				errorMessage = "Ŀǰֻ֧��1~3�꼶�����������У�����1~3�е�һ������";
				return false;
			}    
		}
		return true;
	}
	
	/**
	 * ���ã�������Ŀ������
	 * @param len
	 * @param grade
	 * @return 
	 * @throws Exception 
	 */
	static Character[] generatingTopic(int grade){

		IDemand demand = null;
		//���ɲ�����
		try {
			Class<?> cls = Class.forName("Demand" + grade);
			demand = (IDemand) cls.newInstance();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		Character[] infixExpression = demand.operatorGeneration();
		// �����ɵĲ�����������[2,n-2]��Χ���������
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
	 * ���ã��жϴ˺�׺���ʽ�Ƿ����
	 * @param infix ֻ���������ĺ�׺���ʽ����
	 * @return ���󷵻�true����ȷ����false
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
	 * ���ã�����������Ŀ�������沨�����ʽ
	 * @param infix ��׺���ʽ����
	 * @param n �ڼ�����Ŀ
	 * @param str ����������
	 * @return ���ؼ�����
	 * @throws Exception �����ʽ��������г���ʱ�׳��쳣
	 */
	static int calPoland(Character infix[],int n,int grade) throws ArithmeticException{
		ArrayList<Character> chlist = new ArrayList<Character>();
		for (Character ch : infix) {
			if(ch != null) {
				chlist.add(ch);
			}
		}
		
		IDemand demand = null;
		int remainder = 0; // ����

		try {
			Class<?> cls = Class.forName("Demand" + grade);
			demand = (IDemand) cls.newInstance();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
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
		int k = 1;//�����������
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
					case '��':
						s.push(num[1] * num[0]);
						break;
					case '��':
						s.push(num[1] / num[0]);
						if(demand.isRemainder()) {
							remainder = num[1] % num[0]; // ����
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
	 * ���ã������ɵ���Ŀ�ͺʹ�д��ָ���ļ�
	 * @param filename       ��Ҫд����ļ���
	 * @throws IOException   ���ļ������Ϸ�ʱ�׳��쳣
	 */
	static void write(String filename) throws IOException {
		// ����1��ȷ��������ļ���Ŀ�ĵأ�
		// ���filename�а���·��������ȷ��·���Ѵ���
		File file = new File(filename);
		File parentFile = file.getParentFile();
		if (parentFile != null && !parentFile.exists()) {
			parentFile.mkdirs();
		}
		file.createNewFile();
		// ����2������ָ���ļ��������
		OutputStream out = new FileOutputStream(file);
		// ����3��д������
		for (StringBuffer tp : topic) {
			out.write(tp.toString().getBytes());
		}
		out.write(System.lineSeparator().getBytes());
		for (StringBuffer sa : standAnswer) {
			out.write(sa.toString().getBytes());
		}
		// ����4���ر�
		out.close();
	}
}