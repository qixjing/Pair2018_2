package com.java6369.lesson03;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MathExam {
	private static int n;
	private static final String[] fuhao = {"+", "-"," * ", " / "};
	static StringBuffer Topic = new StringBuffer(); 
	static StringBuffer Answer= new StringBuffer();
	private static int grade = 1;

public static void main(String[] args) throws IOException {//�׳��쳣//
	if (args.length == 0) {
        throw new IllegalArgumentException("���������������");
    }//��֤�������Ŀ������Ϊ0//
    String str = args[0].replaceFirst("^0*", "");//ȥ��0��ͷ��//
    if(str.length()>3) {
    	throw new IllegalArgumentException("��������");//����ֻ����0��1��2
    }
    for(int i =0;(str.charAt(i) < '0' || str.charAt(i++) > '9') && i < str.length();i++) {
    	throw new IllegalArgumentException("��Ҫ���������");//ֻ������0��9�ķ�����
    }
    n = Integer.parseInt(args[0]);
    Exam369();
    writeTo();
    System.out.println("һ�꼶�Ӽ�����鿴out.txt");
}
    private static void Exam369() {
        int result = 0; 
        int num1,num2,sub;
        int yushu=0;
		for (int i = 1; i <= n ; i++) {
			 num1 = (int) (Math.random() * 101);
			 num2 = (int) (Math.random() * 101);
			 sub = (int) (Math.random() *2);
			String symbol = fuhao[sub];
			switch (symbol) {
			case "+":
				result = num1 + num2;
				break;
			case "-":
				if (num1 <num2) {
					int m = num1;
					num1 = m;
					m = num2;
				}
				result =num1 - num2;
				break;
			default:
				System.out.println("����");
			}
			int Choose = (grade == 1) ? 
			((int) (Math.random()*10))%2:((int) (Math.random() * 10)) % 4;
			String symbol2 = fuhao[Choose];
			switch (symbol2) {
			case " + ":
				result = num1 + num2;
				break;
			case " - ":
				result = num1 - num2;
				break;
			case " * ":
				result= num1 * num2;
				break;
			case " / ":
				while(num2 ==0) {
					num2 = (int) (Math.random() * 101);
				}
				result= num1 / num2;
				yushu = num1 % num2;
				break;
			default:
				break;
			}
			// ��¼��Ŀ���
			Topic.append("("+ i +") "+ num1 + " " + symbol + " " + num2 + "\r\n");
			if (symbol.equals(" / ")) {
				Answer.append("(" + i + ") " + num1 +  " " + symbol + " " + num2 + " = " + result
						+ (yushu != 0 ? ("..." + yushu) : "") + "\r\n");
			} else {
				Answer.append("(" + i + ") " + num1 +  " " + symbol + " "  + num2 + " = " + result + "\r\n");
			}
			
		}
    }
    public static void writeTo() throws IOException {
    	  File file= new File("out.txt");
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		byte[] question=Topic.toString().getBytes();
		byte[] answer=Answer.toString().getBytes();
		out.write(question);
		out.write(System.lineSeparator().getBytes());
		out.write(answer);
		out.close();
    }
}



