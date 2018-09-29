package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class MathExam_6305_6316_pro {
	static String[][] str = new String[100][11];
	static String[] markStr =  {"+","-","x","÷"};
	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("请输入题目数量(小于100大于0的数)");
		double n = 0;
		while(true) {
			String nStr = "";
			nStr = s.nextLine();
			int isStr = 0;
			for (int i = 0; i < nStr.length(); i++){
				if (!Character.isDigit(nStr.charAt(i)))
				{
					isStr++;
				}
			}
			if(isStr == 0) {
				n = Double.parseDouble(nStr);
				if(n <= 100 && n > 0) {
					break;
				}else {
					System.out.println("您输入的不是小于100大于0的整数数,请重新输入");
				}
			}else {
				System.out.println("您输入的不是小于100大于0的整数数,请重新输入");
			}
		}
		
		
		System.out.println("请输入1（代表一年级）或2（代表二年级）或3（代表三年级）");

		while(true) {
			String nStr = "";
			nStr = s.nextLine();
			int isStr = 0;
			for (int i = 0; i < nStr.length(); i++){
				if (!Character.isDigit(nStr.charAt(i)))
				{
					isStr++;
				}
			}
			if(isStr == 0) {
				double G = Double.parseDouble(nStr);;
				if(G == 1 || G == 2 || G == 3) {
					Grade_three(n, (int)G);
					break;
				}else {
					System.out.println("您输入的不是1或2或3,请重新输入");
				}
			}else {
				System.out.println("您输入的不是1或2或3,请重新输入");
			}
			
		}
	}

	public static String[][] SaveStr(String[] number, String[] markStrNumber, int a, int markNo, int markNumInBracket, int finalAns, int length, String[][] str) {
		int mar = 0;
		int num = 0;
		
		String[] strNow = new String[length];
		for(int i = 0; i < length; i++){
			if(i%2 == 0 && number[num] != null) {
				strNow[i] = number[num];
				num++;
			}else if(i%2 != 0 && markStrNumber[mar] != null) {
				strNow[i] = markStrNumber[mar];
				mar++;
			}
		}
		num = 0;
		
		for(int i = 0; i < length; i++){
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
		for(int i = 0; i < length; i++){
			System.out.println(str[0][i]);
		}
		return str;
	}

	public static void Grade_three(double n, int grade) {
		String[] markStr =  {"+","-","x","÷"};
		for(int a = 0; a < n; a++) {
			String[] markStrNumber = new String[5];
			String[] number = new String[6];
			
			String[] markStrNumberUsing = new String[5];    //用于每一级的数据传递
			String[] numberUsing = new String[6];
	
			
			int markAmount = 1;
			if(grade < 3) {
				markAmount = 1;	
			}else {
				markAmount = (int) (Math.random()*3+2); //符号数量2 - 4
			}
			
			
			int markRandom = 0;
			for(int i = 0; i < markAmount; i++) {    //随机符号放入符号数组中
				if(grade > 1) {
					markRandom = (int) (Math.random()*4);	
				}else {
					markRandom = (int) (Math.random()*2);	
				}
				markStrNumber[i] = markStr[markRandom];
				markStrNumberUsing[i] = markStr[markRandom];
			}
			
			double finalAns = 0;
			
			int randomRangeMax = 0;
			int randomRangeMin = 1; 
			
			if(grade != 2) {
				randomRangeMax = 20;
				randomRangeMin = 1;
			}else {
				if(markRandom == 2) {
					randomRangeMax = 9;
				}else {
					randomRangeMax = 100;
					randomRangeMin = 0;
				}
			}
			if(grade != 2 || grade == 2 && markRandom != 3) {	
				for(int i = 0; i < markAmount + 1; i++) {   //随机数字并放进数组中
					number[i] = String.valueOf((int) (Math.random()*randomRangeMax+randomRangeMin));	
					numberUsing[i] = number[i];
				}
			}else {
				if(markRandom == 3) {
					number[1] = String.valueOf((int) (Math.random()*9+1));
					finalAns = (int) (Math.random()*9+1);
					number[0] = String.valueOf(finalAns * Integer.parseInt(number[1]));
					str = SaveStr(number, markStrNumber, a, -1, -1, (int)finalAns, 5, str);
					continue;
				}
			}
			int bracketAmount = 0;
			if(markAmount == 2 || markAmount == 3){
				bracketAmount = (int) (Math.random()*2);
			}else {
				bracketAmount = 0;
			}
			
			int bracketNow = bracketAmount;
			int markNumInBracket = -1;
			int markNo = -1;
			
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
	
					double ansBracket = Operation(OpeMarkStrNumber, OpeNumber, 3);
					if(ansBracket == 0.1 || ansBracket > 1000) {
						isErrorAns = 1;
						break;
					}
					System.out.println(ansBracket);
					System.out.println(bracketNow);
					numberUsing[markNo-1] = String.valueOf(ansBracket);
					
					markStrNumberUsing = RemoveNull(markStrNumberUsing);
					numberUsing = RemoveNull(numberUsing);	
					
				}else if(bracketNow == 0){
					double ans = Operation(markStrNumberUsing, numberUsing, 3);
					if(ans == 0.1 || ans > 1000 || grade == 1 && ans > 20 || grade == 2 && ans > 100) {
						isErrorAns = 1;
						break;
					}
					finalAns = ans;
					break;
				}
			}
			
			if(isErrorAns == 1) {
				a--;
				continue;
			}
			
			//传入二维数组中
			str = SaveStr(number, markStrNumber, a, markNo, markNumInBracket, (int)finalAns, 11, str);
	
		}
	
		
		Output(n, str);
	}
	
	public static double Operation(String[] OpeMarkStrNumber, String[] OpeNumber, int grade) {
		String[] OpeMarkStrNumberUsing = new String[5];
		String[] OpeNumberUsing = new String[6];
		for(int i = 0; i < OpeMarkStrNumber.length; i++) {
			OpeMarkStrNumberUsing[i] = OpeMarkStrNumber[i];
		}
		for(int i = 0; i < OpeNumber.length; i++) {
			OpeNumberUsing[i] = OpeNumber[i];
		}
		
		if(grade >= 2) {
			while(true) {
				int PMAmount = 0;
				for(int i = 0; i < OpeMarkStrNumberUsing.length; i++) {
					double ans = 0;
					if(OpeMarkStrNumberUsing[i] == "x") {
						ans = Double.parseDouble(OpeNumberUsing[i]) * Double.parseDouble(OpeNumberUsing[i+1]);
						PMAmount++;
					}else if(OpeMarkStrNumberUsing[i] == "÷") {
						ans = Double.parseDouble(OpeNumberUsing[i]) / Double.parseDouble(OpeNumberUsing[i+1]);
						if(ans != (int)ans) {
							return 0.1;
						}
						PMAmount++;
					}
					if(PMAmount > 0) {
						OpeNumberUsing[i] = String.valueOf(ans);
						OpeNumberUsing[i+1] = null;
						OpeMarkStrNumberUsing[i] = null;
						RemoveNull(OpeNumberUsing);
						RemoveNull(OpeMarkStrNumberUsing);
						break;
					}
				}
				
				if(PMAmount == 0) {
					break;
				}
			}
		}
		return PlusMinus(OpeMarkStrNumberUsing, OpeNumberUsing);
	}
	
	public static double PlusMinus(String[] OpeMarkStrNumber, String[] OpeNumber) {
	
		int PMmarkNo = 0;
		double ans = Double.parseDouble(OpeNumber[0]) ;
		while(true) {
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
	
	
	
	public static void Output(double n, String[][] str) {
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
