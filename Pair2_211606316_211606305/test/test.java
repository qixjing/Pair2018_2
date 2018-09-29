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
			String[] number = {"1",null,"3",null};
			number = RemoveNull(number);
			for(int i = 0; i < number.length; i++) {
				System.out.println(number[i]);
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
