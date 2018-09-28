package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class MathExam_2_6305 {
	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("请输入题目数量(小于100大于0的数)");
		double n = 0;
		while(true) {
			n = s.nextDouble();
			if(n<=100 && n > 0 && (int)n == n) {
				break;
			}else {
				System.out.println("您输入的不是小于100大于0的整数数,请重新输入");
			}
		}
		System.out.println("请输入1（代表一年级）或2（代表二年级）或3（代表三年级）");
		while(true) {
			double G = s.nextDouble();
			if(G==1) {
				Grade_one(n);
				break;
			}else if(G == 2) {
				Grade_two(n);
				break;
			}else if(G == 3) {
				Grade_three(n);
				break;
			}else {
				System.out.println("您输入的不是1或2或3,请重新输入");
			}
		}
	}
	
	public static void Grade_one(double n) {
		String[][] str = new String[100][4];
		for(int i = 0; i < n; i++) {
			int a = (int) (Math.random()*20);
			int b = (int) (Math.random()*20);
			int ans = 0;
			int mark = (int) (Math.random()*2+1);
			String strMark = null;
			if(mark == 1) {
				ans = a+b;
				strMark = "+";
			}else {
				ans = a-b;
				strMark = "-";
			}
			if(ans >= 20 || ans < 0) {
				i--;
				continue;
			}
				
			str[i][0] = String.valueOf(a);
			str[i][1] = strMark;
			str[i][2] = String.valueOf(b);
			str[i][3] = String.valueOf(ans);
		}
		Calculate(n, str);
	}
	
	public static void Grade_two(double n){
		String[][] str = new String[100][4];
		for(int i = 0; i < n; i++) {
			int mark = (int) (Math.random()*4+1);
			int a = 0;
			int b = 0;
			int ans = 0;
			String strMark = null;
			if(mark == 1) {
				strMark = "x";
				a = (int) (Math.random()*9+1);
				b = (int) (Math.random()*9+1);
				ans = a*b;
			}else if (mark == 2){
				strMark = "÷";
				a = (int) (Math.random()*9+1);
				b = (int) (Math.random()*9+1);
				ans = a*b;
			}else if (mark == 3){
				strMark = "+";
				a = (int) (Math.random()*100+1);
				b = (int) (Math.random()*100+1);
				ans = a+b;
				if(ans > 100) {
					i--;
					continue;
				}
			}else {
				strMark = "-";
				a = (int) (Math.random()*100+1);
				b = (int) (Math.random()*100+1);
				ans = a-b;
				if(ans < 0) {
					i--;
					continue;
				}
			}
			
			if(mark == 2) {
				str[i][0] = String.valueOf(ans);
				str[i][1] = strMark;
				str[i][2] = String.valueOf(a);
				str[i][3] = String.valueOf(b);
			}else {
				str[i][0] = String.valueOf(a);
				str[i][1] = strMark;
				str[i][2] = String.valueOf(b);
				str[i][3] = String.valueOf(ans);
			}
		}
		Calculate(n, str);
	}
	
	public static void Grade_three(double n) {
		String[] markStr =  {"+","-","x","÷"};
		String[][] str = new String[100][11];
		
		for(int a = 0; a < n; a++) {
			System.out.println(a+1);
			System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnn");
			String[] markStrNumber = new String[5];
			String[] number = new String[6];
			
			String[] markStrNumberUsing = new String[5];    //用于每一级的数据传递
			String[] numberUsing = new String[6];
			
			int markAmount = (int) (Math.random()*3+2);	 //符号数量2 - 4
			
			
			for(int i = 0; i < markAmount; i++) {    //随机符号放入符号数组中
				int markRandom = (int) (Math.random()*3+2);	
				markStrNumber[i] = markStr[markRandom-1];
				
				markStrNumberUsing[i] = markStr[markRandom-1];
			}
			
			for(int i = 0; i < markAmount + 1; i++) {   //随机数字并放进数组中
				number[i] = String.valueOf((int) (Math.random()*20+1));	
				
				numberUsing[i] = number[i];
			}
			
			for(int i = 0; i < markStrNumberUsing.length; i++) {
				System.out.println(markStrNumberUsing[i]);
			}
			System.out.println("-------------------------------");
			for(int i = 0; i < numberUsing.length; i++) {
				System.out.println(numberUsing[i]);
			}
			System.out.println("-------------------------------");
			int bracketAmount = 0;
			if(markAmount == 2 || markAmount == 3){
				bracketAmount = (int) (Math.random()*2);
			}else {
				bracketAmount = 0;
			}
			
			int bracketNow = bracketAmount;
			System.out.println(bracketNow);
			System.out.println("---------------()--------------");
			int markNumInBracket = -1;
			int markNo = -1;
			double finalAns = 0;
			
			int isErrorAns = 0;
			
			while(true) {
				if(bracketNow == 1) {
					bracketNow--;
					if(markAmount == 2) {
						markNumInBracket = 1;
						markNo = (int) (Math.random()*2+1);
					}else {
						markNumInBracket = (int) (Math.random()*2+1);
						markNo = (int) (Math.random()*(3-markNumInBracket+1)+1);
					}
					
					System.out.println(markNumInBracket);
					System.out.println(markNo);
					System.out.println("-------------!!!---------------");
					String[] OpeMarkStrNumber = new String[markNumInBracket+1];
					String[] OpeNumber = new String[markNumInBracket+2];
					/*----------------------------------------------------*/
					for(int j = 0; j < markNumInBracket; j++) {
						OpeMarkStrNumber[j] = markStrNumberUsing[j+markNo-1];
						markStrNumberUsing[j+markNo-1] = null;
						System.out.println(OpeMarkStrNumber[j]);
					}
					
					for(int j = 0; j < markNumInBracket+1; j++) {
						OpeNumber[j] = numberUsing[j+markNo-1];
						numberUsing[j+markNo-1] = null;
						System.out.println(OpeNumber[j]);
					}
					System.out.println("-------------!!!---------------");
					
					
					double ansBracket = Operation(OpeMarkStrNumber, OpeNumber);
					if(ansBracket == 0.1 || ansBracket > 1000) {
						isErrorAns = 1;
						break;
					}
					System.out.println(ansBracket);
					System.out.println(bracketNow);
					System.out.println("--------------()()--------------");
					numberUsing[markNo-1] = String.valueOf(ansBracket);
					
					markStrNumberUsing = RemoveNull(markStrNumberUsing);
					numberUsing = RemoveNull(numberUsing);	
					
			/*		System.out.println("---------------null-----------------");
					for(int u = 0; u < markStrNumberUsing.length; u++) {
						System.out.println(markStrNumberUsing[u]);
					}
					System.out.println("----------------null----------------");
					for(int u = 0; u < numberUsing.length; u++) {
						System.out.println(numberUsing[u]);
					}
					System.out.println("---------------null----------------");*/
					
					
					
					
				}else if(bracketNow == 0){
					double ans = Operation(markStrNumberUsing, numberUsing);
					if(ans == 0.1 || ans > 1000) {
						isErrorAns = 1;
						break;
					}
					finalAns = ans;
					System.out.println(ans);
					System.out.println("----");
					break;
				}
			}
			
			if(isErrorAns == 1) {
				a--;
				continue;
			}
			
			//传入二维数组中
			int mar = 0;
			int num = 0;
			
			String[] strNow = new String[11];
			for(int i = 0; i < 11; i++){
				if(i%2 == 0 && number[num] != null) {
					strNow[i] = number[num];
					num++;
				}else if(i%2 != 0 && markStrNumber[mar] != null) {
					strNow[i] = markStrNumber[mar];
					mar++;
				}
			}
			num = 0;
			
			for(int i = 0; i < 11; i++){
				if(i == (markNo-1)*2) {
					str[a][i] = "(";
				}else if(i == ((markNo-1)*2 + 2*markNumInBracket + 2)){
					str[a][i] = ")";
				}else if(strNow[num] == null && i != ((markNo-1)*2 + 2*markNumInBracket + 2)) {
					str[a][i] = String.valueOf(finalAns);
					break;
				}else {
					str[a][i] = strNow[num];
					num++;
				}
			}
			for(int i = 0; i < 11; i++){
				System.out.println(str[0][i]);
			}
			
		}

		
		Calculate_Three(n, str);
	}

	public static double Operation(String[] OpeMarkStrNumber, String[] OpeNumber) {
		while(true) {
			int PMAmount = 0;
			
			for(int i = 0; i < OpeMarkStrNumber.length; i++) {
				
				if(OpeMarkStrNumber[i] == "x") {
				/*	System.out.println("---------------x-----------------");
					for(int u = 0; u < OpeMarkStrNumber.length; u++) {
						System.out.println(OpeMarkStrNumber[u]);
					}
					System.out.println("----------------x----------------");
					for(int u = 0; u < OpeNumber.length; u++) {
						System.out.println(OpeNumber[u]);
					}
					System.out.println("---------------x----------------");*/
					
					double ans = Double.parseDouble(OpeNumber[i]) * Double.parseDouble(OpeNumber[i+1]);
					
					OpeNumber[i] = String.valueOf(ans);
					OpeNumber[i+1] = null;
					OpeMarkStrNumber[i] = null;
					OpeNumber = RemoveNull(OpeNumber);
					OpeMarkStrNumber = RemoveNull(OpeMarkStrNumber);
					
					PMAmount++;
					break;
					
				}else if(OpeMarkStrNumber[i] == "÷") {
				/*	System.out.println("----------------÷----------------");
					for(int u = 0; u < OpeMarkStrNumber.length; u++) {
						System.out.println(OpeMarkStrNumber[u]);
					}
					System.out.println("----------------÷----------------");
					for(int u = 0; u < OpeNumber.length; u++) {
						System.out.println(OpeNumber[u]);
					}
					System.out.println("---------------÷---------------");*/
					double ans = Double.parseDouble(OpeNumber[i]) / Double.parseDouble(OpeNumber[i+1]);
					
					if(ans != (int)ans) {
						return 0.1;
					}
					
					
					OpeNumber[i] = String.valueOf(ans);
					OpeNumber[i+1] = null;
					OpeMarkStrNumber[i] = null;
					OpeNumber = RemoveNull(OpeNumber);
					OpeMarkStrNumber = RemoveNull(OpeMarkStrNumber);
					PMAmount++;
					break;
				}
			}
			
			if(PMAmount == 0) {
				break;
			}
		}
		return PlusMinus(OpeMarkStrNumber, OpeNumber);
	}
	
	public static double PlusMinus(String[] OpeMarkStrNumber, String[] OpeNumber) {

		int PMmarkNo = 0;
		double ans = Double.parseDouble(OpeNumber[0]) ;
		while(true) {
			
		/*	System.out.println("++++++++++++++++++++");
			for(int u = 0; u < OpeMarkStrNumber.length; u++) {
				System.out.println(OpeMarkStrNumber[u]);
			}
			System.out.println("++++++++++++++++++++");
			for(int u = 0; u < OpeNumber.length; u++) {
				System.out.println(OpeNumber[u]);
			}
			System.out.println(PMmarkNo);
			System.out.println("++++++++++++++++++++");*/
			
			if(OpeMarkStrNumber[PMmarkNo] == "+") {
				ans = ans + Double.parseDouble(OpeNumber[PMmarkNo+1]);
				PMmarkNo++;
			}else if(OpeMarkStrNumber[PMmarkNo] == "-"){
				ans = ans - Double.parseDouble(OpeNumber[PMmarkNo+1]);
				if(ans < 0) {
					return 0.1;
				}
				PMmarkNo++;
			}else if(OpeMarkStrNumber[PMmarkNo] == null){
				return ans;
			}
		}
	}
	
	
	public static String[] RemoveNull(String[] target) {
		int nullAmount = 0;
		for(int i = 0; i < target.length; i++) {
			if(target[i] != null) {
				nullAmount++;
			}
		}
		if(nullAmount > 0) {
			for(int i = 0; i < target.length; i++) {
				int number = 0;
				if(target[i] == null) {
					for(int j = i; j < target.length; j++) {
						if(j != target.length-1) {
							target[j] = target[j+1];
						}else {
							target[j] = null;
						}
					}
					
					for(int j = i; j < target.length; j++) {
						if(target[j] != null) {
							number++;
						}
					}
					
					if(number > 0) {
						i--;
					}
				}
			}
		}
		return target;
	}


	
	public static void Calculate(double n, String[][] str) {
		try {
			File f=new File("F:\\java\\out.txt");
	        if(!f.exists()){
	            f.getParentFile().mkdirs();          
	        }
	        f.createNewFile();
			FileOutputStream fos = new FileOutputStream("F:\\java\\out.txt");
			for(int i = 0; i < n; i++) {
				fos.write("(".getBytes());
				fos.write(String.valueOf(i+1).getBytes());
				fos.write(")".getBytes());
				for(int j = 0; j < 3; j++) {
					fos.write(str[i][j].getBytes());
					fos.write("\b".getBytes());
				}
				fos.write("=".getBytes());
				fos.write("\r\n".getBytes());
			}
			fos.write("------------------标准答案------------------\r\n".getBytes());
			for(int i = 0; i < n; i++) {
				fos.write("(".getBytes());
				fos.write(String.valueOf(i+1).getBytes());
				fos.write(")".getBytes());
				for(int j = 0; j < 3; j++) {
					fos.write(str[i][j].getBytes());
					fos.write("\b".getBytes());
				}
				fos.write("=\b".getBytes());
				fos.write(str[i][3].getBytes());
				fos.write("\r\n".getBytes());
			}
			fos.close();
		}catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void Calculate_Three(double n, String[][] str) {
		try {
			File f=new File("F:\\java\\out.txt");
	        if(!f.exists()){
	            f.getParentFile().mkdirs();          
	        }
	        f.createNewFile();
			FileOutputStream fos = new FileOutputStream("F:\\java\\out.txt");
			for(int i = 0; i < n; i++) {
				fos.write("(".getBytes());
				fos.write(String.valueOf(i+1).getBytes());
				fos.write(")".getBytes());
				int end = 0;
				
				for(int x = 0; x < 11; x++) {
					if(str[i][x] == null)
					{
						end = x;
						break;
					}
				}
				for(int j = 0; j < end-1; j++) {
					fos.write(str[i][j].getBytes());
					fos.write("\b".getBytes());
				}
				fos.write("=".getBytes());
				fos.write("\r\n".getBytes());
			}
			fos.write("------------------标准答案------------------\r\n".getBytes());
			for(int i = 0; i < n; i++) {
				fos.write("(".getBytes());
				fos.write(String.valueOf(i+1).getBytes());
				fos.write(")".getBytes());
				
				int end = 0;
				for(int x = 0; x < 11; x++) {
					if(str[i][x] == null)
					{
						end = x;
						break;
					}
				}
				
				for(int j = 0; j < end-1; j++) {
					fos.write(str[i][j].getBytes());
					fos.write("\b".getBytes());
				}
				int j = end-1;
				fos.write("=\b".getBytes());
				fos.write(str[i][j].getBytes());
				fos.write("\r\n".getBytes());
			}
			fos.close();
		}catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}