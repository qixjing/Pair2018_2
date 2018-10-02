package Pupil;
/**
 * 
 * 
 * 
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class MathExam {
//	�����
	static char sign[]= {'+','-','*','/'};
//	�����Ŀ
	static List<String> list=new ArrayList<String>();	
//	��Ŵ�
	static List<Integer> answer=new ArrayList<Integer>();
//	������Ŀ
	static int N=0;
	static int M=1;
	static int O=1;
	static double sum1,sum2;
	
	static Random random=new Random();
	
	static RPNcompute RNP=new RPNcompute();
	
//	���ɲ����������
	private static int createNumber(int S) {
//		��Random��nextInt���������������
//		S��ʾ����������ķ�Χ
		return random.nextInt(S);
	}
//	������Ŀ
	public static boolean createProblem() {
//		����pro_num������Ŀ�ͷ��ϣ��������ַ�������list����
//		��ͨ���沨���㷨������Ŀ�Ľ��������answer����
//		ͨ��N�����Ƴ�����Ŀ
		String str;
		for(int i=0;i<N;i++) {
			while(true) {
				if(O>2 && createNumber(5)==0) {
					str=Brackets();
				}else {
					str=nuBrackets();
				}
				sum1=RNP.RPNcalculate(str);
				sum2=(int)sum1;
				if(sum1==sum2 && sum1>=0.0 && sum1<=10000) {
					break;
				}
			}
			list.add(str);
			answer.add((int)sum1);
//			System.out.println(str+"= "+(int)sum1);
		}
		createFile("out");
		waterFile("out");
		return true;
	}
	private static String Brackets() {
		int i;
		int size=random.nextInt(1)+2;
		int start=random.nextInt(3);
		String str="";
		for(i=0;i<start+size+1;i++) {
			if(i==start) {
				str+="( ";
			}
			str=str+random.nextInt(100)+" ";
			if(i==start+size) {
				str+=") ";
			}
			if(i<=start || i>=start+size) {
				str=str+sign[random.nextInt(1)+2]+" ";
			}else {
				str=str+sign[random.nextInt(4)]+" ";
			}
			
		}
		str=str+random.nextInt(100)+" ";
		if(i==start+size) {
			str+=") ";
		}
		
//		System.out.println(str);
		return str;
	}
	private static String nuBrackets() {
		int T=random.nextInt(3)+2;
		if(O<3) {
			T=1;
		}
		String str="";
		for(int i=0;i<T;i++) {
			str=str+random.nextInt(100)+" ";
			str=str+sign[random.nextInt(M)]+" ";
		}
		str=str+random.nextInt(100)+" ";
		
//		System.out.println(str);
		return str;
	}
	
	
//	�����ļ�
	public static boolean createFile(String name) {
		String filename=name+".txt";
		File file=new File(filename);
		if(!file.exists()) {
			System.out.println("�ļ������ɹ�");
			return true;
		}else {
			System.out.println("�ļ��Ѵ���");
			return false;
		}
	}
//	д���ļ�
	public static boolean waterFile(String name) {
		/* ���ļ�д������� */
		PrintStream pS=null;
		try {
			pS = new PrintStream(new FileOutputStream(name+".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�����ļ�ʧ��");
			e.printStackTrace();
			return false;
		}
		/* ��Ŀ */
		for (int i = 0; i < N; i++) {
			pS.println("("+(i+1)+") "+list.get(i));
		}
		pS.println("");
		System.out.println("");
		/* ��׼�� */
		for (int i = 0; i < N; i++) {
			pS.println("("+(i+1)+") "+list.get(i)+" = "+answer.get(i));
		}
		pS.close();
		return true;
	}
	
	
	
	public static void main(String[] args) {
		String num="0";
		String grade="1";
		for(int i=0;i<args.length;i++) {
			if(args[i].equals("-n")) {
				num=args[i+1];
			}
			if(args[i].equals("-grade")) {
				grade=args[i+1];
			}
		}
		if(!num.equals("0") && num.matches("0*[0-9]{0,3}") && grade.matches("[1-3]")) {
			N=Integer.parseInt(num);
			O=Integer.parseInt(grade);
			if(O==1) {
				M=2;
			}else {
				M=4;
			}
			createProblem();
		}else {
			System.out.println("���������Ҫ�������������Ŀ����");
		}
	}
	
}
