package com.Pair2.MathExam;

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
 	*������ҵ���ṩ markdown ģ�壬��Ҫ�󲩿Ͱ��� �ṹ�Ż�����Ԫ���ԡ����ܵ�����������չ����

	*����Ԫ���ԡ�
	*˵���Լ���Ƶ�Ԫ���Ե�˼·
	*ѡ�񲿷ֵ�Ԫ���Դ��뷢���ڲ����У���˵�����Եĺ���������������ݵ�˼·
	*�����и��ϵ�Ԫ���Եõ��Ĳ��Ը����ʽ�ͼ
	*��Ԫ�������帲����Ҫ��ﵽ90%���ϣ�����Ԫ���Բ���������Ч

	*���ṹ�Ż���
	*�ڲ����и��������UML��ͼ
	*�ڲ����и����������������ͼ
	*�������������ع��Ĳ��֣����ع���ԭ��
	*�����ع���ÿ��ģ��Ĺ���

	*�����ܵ��š�
	*�����Ż�ǰЧ�ܷ������ߵĽ����ͼ
	*�������������ƿ��
	*�����Ż�����
	*�����Ż���Ч�ܷ������ߵĽ����ͼ
	*
 */	
	int firstNumber, secondNumber;		
	int symbol;	//��������ж�
	int result;
	public static int grade; 
	public static int count;	
	public boolean w1 = true;
	public boolean w2 = true;
	public boolean w3 = false;
	public boolean w4 = false;
	boolean calflag = true;  //����һ���������ͣ�������Ŀ�淶
	
	//���ȳ����沨����������
	Stack<String> operators = new Stack<String>();	//�洢������
	Stack<String>  operands= new Stack<String>();	//�洢������
	StringBuilder postfixExpression=new StringBuilder(); //չʾ���ʽ
	
	String[] str_ArithmeticProblem = new String[10000];	//���������
	String[] str_MathAnswer = new String[10000];	//�����Ŀ�ͱ�׼��
	
	public MathExam6335() {
		// TODO Auto-generated constructor stub
		
	}
	
	public MathExam6335(String[] args){
		inPut(args);
		mathProblem(count);
		outPut();
	}

	public void inPut(String[] str) {
		// TODO Auto-generated method stub
		boolean flag1 = true;		//�ж���Ŀ�꼶����
		boolean flag2 = true;
		
		Scanner in = new Scanner(System.in);
		String regex1 = "0*[1-9]{1}\\d{0,3}";		//������ʽ�ж��������Ϊ����������
		String regex2 = "0*[1-3]{1}{0}";
		Pattern pattern1 = Pattern.compile(regex1);		//���������������ʽ�ı������
		Pattern pattern2 = Pattern.compile(regex2);
		Matcher matcher1,matcher2;		//���������жϲ�����������ʽ�Ĳ�������
		
		while (true) {		
			try {
				if(str[0].equals("-n") && str[2].equals("-grade")){		//����ƥ������Ĳ������ͣ�args[0] �� args[2] ������
					matcher1 = pattern1.matcher(str[1]);		
					matcher2 = pattern2.matcher(str[3]);
					flag1 = matcher1.matches();
					flag2 = matcher2.matches();
					if(!flag1 || !flag2){
						throw new NumberFormatException();
					} else {
						count = Integer.valueOf(str[1]);
						grade = Integer.valueOf(str[3]);
					}
				}else if(str[0].equals("-grade") && str[2].equals("-n")){		//regex1��3�ı��ʽ��ƥ��ɹ�
					matcher1 = pattern1.matcher(str[3]);
					matcher2 = pattern2.matcher(str[1]); 
					flag1 = matcher1.matches();		//����ƥ��淶��
					flag2 = matcher2.matches();		//�꼶ƥ��淶��
					if(!flag1 || !flag2){		//��������������������ʽ�淶�ͽ����쳣
						throw new NumberFormatException();		
					} else {	
						count = Integer.valueOf(str[3]);
						grade = Integer.valueOf(str[1]);
					}
				} else {
					System.out.println("��������������󣬼����˳�����");
					w1 = false;
				}				
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("������������Ϲ淶�������˳�����");
				w2 = false;
			}			
			in.close();
			break;
		}
	}
	
	public void outPut() {
		// TODO Auto-generated method stub
		File file = new File("out.txt");
		
		if(!file.exists()){	//�ж��ļ��Ƿ����
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�ļ�����ʱ���ִ���! �����˳�����...."); 
			}
		}
		
		try {
			String str = "\r\n";
			byte[] bytes = str.getBytes();		//string����ת��Ϊ�ܱ�����ʶ��Ķ�������
			
			FileOutputStream fos = new FileOutputStream(file);	//�ļ�д����
			for (int i = 0; i < count; i++) {
				str_ArithmeticProblem[i] = "( " + (i+1) + " )" + str_ArithmeticProblem[i];	//����Ŀ������
				byte[] b_ArithmeticProblem = str_ArithmeticProblem[i].getBytes();
				fos.write(b_ArithmeticProblem);
				fos.write(bytes);
			}
			fos.write(bytes);
			fos.flush();	//ˢ���ڴ�
			for (int i = 0; i < count; i++) {
				byte[] b_MathAnswer = str_MathAnswer[i].getBytes();
				fos.write(b_MathAnswer);
				fos.write(bytes);
			}
			fos.flush();
			fos.close();	//�ر��ļ������
			
			w3 = true;
			System.out.print("-------  ���ι�����" + count + "��Сѧ"+ grade + "�꼶�����⣬���out.txt�ļ��鿴����    -------"); 	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ҵ�ָ���ļ�! �����˳�����....");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�ļ�д������! �����˳�����....");
		} 	
	}
	
	public static boolean isOperator(String operator){
    	//�жϲ�����
        if (operator.equals("+")||operator.equals("-")||operator.equals("��")||operator.equals("��")
                ||operator.equals("(")||operator.equals(")")) {
            return true;
        }
        return false;
    }
	
	public static int priority(String s){
    	//���������ȼ�
        if (s.equals("+") || s.equals("-")) {
        	return 1;
        } else if (s.equals("��") || s.equals("��")) {
        	return 2;
        } else if(s.equals("(") || s.equals(")")){
        	return 3;
        } else{
        	return 0; 
        }
    }
    
	public static int Calculation(int n,int m,String operator){
    	//������
    	int result = -4567;
    	if (operator.equals("+")) {
    		result = n + m;
    	} else if (operator.equals("-")) {
    		if(m > n) {
    			result = -2;	//����һ����������ֵΪ��
    		}else {
    			result = n - m;		
			}
    		
    	} else if (operator.equals("��")) {
    		result = n * m;
    		
    	} else if (operator.equals("��")) {
    		if (m == 0) {
				result = -1;	//����һ������������Ϊ0
				
			} else {
				result = n / m;
				
			}
    	}
    	
    	return result;
    }

	public String toPostfixExpression(String str_mix){ 
		//�沨���㷨����[��׺���ʽת��׺���ʽ]
		int len = str_mix.length();	//��ȡ���ʽ�ĳ���
		char ch;	//����charAt()������ȡ�ĵ��ַ�
		String sc;	//����toString()���charֵ
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
			
			if(isOperator(sc))	//�ж��Ƿ��ǲ�����
			{
				if(operators.isEmpty()){	//�ж�Ϊ��ջ����ջ
					operators.push(sc);
					
				} else {
					if(priority(operators.peek()) < priority(sc) && !sc.equals(")")){	
						//ջ�����������ȼ�С�ڵ�ǰ���������ȼ��Ҳ�������Ϊ�����ţ���ջ
						operators.push(sc);
					} else if(priority(operators.peek()) >= priority(sc) && !sc.equals(")")){
						while(!operators.empty() && !operators.peek().equals("(")	//ջ��Ϊ�գ���ǰջ����������Ϊ������
								&& priority(operators.peek()) >= priority(sc)){		//���������ȼ�С�ڵ��ڵ�ǰջ�����������ȼ�
							do {
								postfixExpression.append(operators.peek());
								operands.push(operators.pop());
							} while (false);	}	// ջ����������������ʱֹͣѹջ
						operators.push(sc);		//����ֱ����ջ
					} else if(sc.equals(")")){	//��ǰɨ�赽�Ĳ�����Ϊ������(������ջ����)������ѹջ��ƥ�������������
						do {
							postfixExpression.append(operators.peek());
							operands.push(operators.pop());
						} while (!operators.peek().equals("("));
						operators.pop();	//����ջ�����ò�����������
					}
				}
				
			} else {	//�ǲ�����
				if(!sc.equals(" ")){
					postfixExpression.append(sc);
					operands.push(sc);	
				}
			}
		}
		
		while(!operators.empty()){	//�����ַ���ɨ����������ջ��Ϊ������ѹջ
			postfixExpression.append(operators.peek());
			operands.push(operators.pop());
		}
//		System.out.println(String.valueOf(postfixExpression));
		return String.valueOf(postfixExpression);
	}
	
	public int reversePolish() {
		// TODO Auto-generated method stub
		//�����׺���ʽ
		String oper;	//�洢ջ���Ԫ��
		Stack<Integer> postfixNumber = new Stack<Integer>();	//����һ���洢���ֻ�����ջ
		
		while(!operands.isEmpty()) {	//��תջ��ĺ�׺���ʽ��ջ˳��
			operators.push(operands.pop());
		}
		
		while(!operators.isEmpty()) {	//�洢��׺���ʽ��ջ��Ϊ��ʱ������
			oper = operators.pop();
			if(!isOperator(oper)){	//�жϷǲ�������������ջջ
				postfixNumber.push(Integer.parseInt(oper));
			} else{
				int m = postfixNumber.pop();	//���ط������ߵ����� 
				int n = postfixNumber.pop();
				if (Calculation(n, m, oper) < 0) {	//����󷵻�ֵΪ����������ʽ���ڡ���ֵΪ�������ߡ�����Ϊ0���������������ѭ��
					calflag = false;
					while(!operators.isEmpty()) {	//��֤�´�ʹ��ջʱ��һ��Ϊ��
						operators.pop();
					}
					break;
				}
				
				postfixNumber.push(Calculation(n, m, oper));	//�����ջ
				
				if(operators.isEmpty()) {	//���Ƶ�ջΪ��ʱ������ѭ��
					break;
				}
			}
		}
		
		if(calflag) {	//������Ŀ���
			w4 = false;
			return postfixNumber.pop();
		}else {
			calflag = true;
			return -1;
		}
	}
	
	public void mathProblem(int count) {	
		//����������
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
	
	public String operator() {		
		// TODO Auto-generated method stub
		//������ɲ�����
		Random in =new Random();
		String[] ro = {"+","-","��","��"};
		return ro[in.nextInt(4)];
	}
	
	public int operand() {
		// TODO Auto-generated method stub
		return (int)(Math.random()*100+1);
	}
	
	public void fourMixed(int i) {
		// TODO Auto-generated method stub
		//�������㣺��������������ܹ�2-4��������
		int res = 0; 

			while (true) {
				StringBuilder add = new StringBuilder();	//����һ��Bulider���͵�String���������ɵı��ʽ
				for(int num = (int)(Math.random()*3+2);num > 0 ; num--) {	//���ɱ��ʽ���������ĸ���Ϊ2~4��
					add.append(operand() + " " + operator()+ " ");  			
				}
				
				add.append(operand() + " ");	//���ֵĸ�����(����������+1)
				add.insert(0, " ");
				
				int positionAdd = add.indexOf("+");		//���ز������ڱ��ʽ��λ��
				int positionSub = add.indexOf("-");
				int parentheses = (int)(Math.random()*2); 	//�����Ƿ��������
				
				if(positionAdd > 0 && parentheses == 0) {	//ƥ�䵽���ʽ���мӺŲ�������������Ϊ���״̬
					if(add.charAt(positionAdd+2) >= '0' && add.charAt(positionAdd+2) <= '9') {
						int a = positionAdd+2;
						do {
							a++;
						}while(add.charAt(a) >= '0' && add.charAt(a) <= '9');
						add.insert(a, " )");	//����������
					}
					if(add.charAt(positionAdd-2) >= '0' && add.charAt(positionAdd-2) <= '9') {
						int a = positionAdd-2;
						do {
							a--;
						}while(add.charAt(a) >= '0' && add.charAt(a) <= '9');
						add.insert(a, "(");		//����������
					}
				} else if(positionSub > 0 && parentheses == 0) {	//ƥ�䵽���ʽ���м��Ų�������������Ϊ���״̬
					if(add.charAt(positionSub+2) >= '0' && add.charAt(positionSub+2) <= '9') {
						int a = positionSub+2; 
						do {
							a++;
						}while(add.charAt(a) >= '0' && add.charAt(a) <= '9');
						add.insert(a, " )");	//����������
					}
					if(add.charAt(positionSub-2) >= '0' && add.charAt(positionSub-2) <= '9') {
						int a = positionSub-2;
						do {
							a--;
						}while(add.charAt(a) >= '0' && add.charAt(a) <= '9');
						add.insert(a, "(");		//����������
					}

				}

				str_ArithmeticProblem[i] = String.valueOf(add);	
//				System.out.println(add);
				toPostfixExpression(str_ArithmeticProblem[i]);
				res = reversePolish();	//��ȡ���
				
				if (res < 0) {	//���Ϊ���������ʽ�����Ϲ����������
					continue;
				} else {	//����ѭ��
					break;
				}	
			}	
			str_MathAnswer[i] ="( " + (i+1) + " ) " + str_ArithmeticProblem[i] + " = " + res;		
			
	}
	
	public int add(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		/*
		 * �ӷ���
		 *  1.һ���꼶�ļӷ�������������20���ڡ�
		 * 
		 */
		result = n1 + n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " + " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " + " + n2 + " = " + result;
		return result;
	}
	
	public int sub(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		/*
		 * ������
		 * 
		 * 1.һ���꼶����֮��Ӧ���ڴ���0��
		 * 2.�������ͼ�����20���ڡ�
		 */
		if (n1 < n2) {		//��Ϊ������������ֵ
			int num;
			num = n1;
			n1 = n2;
			n2 = num;
		}
		result = n1 - n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " - " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " - " + n2 + " = " + result;
		return result;
	}
	
	public int mul(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		/*
		 * �˷���
		 * 
		 * 1.һ���꼶�ĳ˷�����Ӧ����0-9���ڡ��žų˷�����
		 * 
		 */
		if (n1 > 9) {
			n1 = (int)(Math.random()*10);
		} 
		if (n2 > 9) {
			n2 = (int)(Math.random()*10);
		}
		result = n1 * n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2 + " = " + result;
		return result;
	}
	
	public void div(int i) {
		// TODO Auto-generated method stub
		/*
		 * ������
		 * 
		 * 1.һ���꼶�ĳ�������Ӧ���ڡ��žų˷�����Χ���ڣ�
		 * 2.��ĸ����Ϊ��0����
		 * 
		 */
		while(true){
			int n1 = (int)(Math.random()*82);
			int n2 = (int)(Math.random()*81+1);
			if(n1 % n2 == 0){
				result = n1 / n2;
				str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2;
				str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2 + " = " + result;
			}else if(n1 % n2 != 0){
				result = n1 / n2;
				str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2;
				str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2 + " = " + result + "..." + (n1 % n2);
			}
			break;
		}
	}
	
	public String div(int n1,int n2,int i) {
		// TODO Auto-generated method stub
		/*
		 * ������
		 * 
		 * 1.һ���꼶�ĳ�������Ӧ���ڡ��žų˷�����Χ���ڣ�
		 * 2.��ĸ����Ϊ��0����
		 * 
		 */
		String res = null;
		while(true){
			if(n1 % n2 == 0){
				result = n1 / n2;
				str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2;
				str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2 + " = " + result;
				res = String.valueOf(result);
			}else if(n1 % n2 != 0){
				result = n1 / n2;
				str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2;
				str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " �� " + n2 + " = " + result + "..." + (n1 % n2);
				res = String.valueOf(result) + "..." + String.valueOf((n1 % n2));
			} 
			break;
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MathExam6335(args);	
	}

}
