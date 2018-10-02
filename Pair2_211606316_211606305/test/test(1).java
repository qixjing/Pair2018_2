package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class test {
		static String[][] str = new String[100][11];
		static String[] markStr =  {"+","-","x","¡Â"};
		static Scanner s = new Scanner(System.in);
		public static void main(String[] args) throws FileNotFoundException {
			String[] mark = {"+","x",null};
			String[] number = {"1","2","3",null};
			double ans = Operation(mark, number, 3);
			System.out.println(ans);
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
						}else if(OpeMarkStrNumberUsing[i] == "¡Â") {
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
	}
