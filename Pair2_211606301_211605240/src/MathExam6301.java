
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MathExam6301 {
	static OutputStream out;
	static int i=0,a,b;
	static int[] intArray1=new int[1000];
	static int[] intArray=new int[1000];
	static String word;
	

	public static int multiplication() {
		a=(int) (Math.random()*10);
		b=(int) (Math.random()*10);
		 word=("("+(i+1)+") "+a +" x "+ b +" = ")+"\n";
		return a*b;
		
	}
	public static int division() {
		a =(int) (Math.random()*100);
		b =(int) (1+Math.random()*9);
		word=("("+(i+1)+") "+a +" ÷ "+ b +" = ")+"\n";
		intArray1[i]=a%b;
		return a/b;
		
		
	}
	public static int addition() {
		a=(int)(Math.random()*100);
		b=(int)(Math.random()*(100-a+1));
		word=("("+(i+1)+") "+ a +" + "+ b +" = ")+"\n";
		return a+b;
	}
	public static int subtraction (){
		a=(int)(Math.random()*100);
		b=(int)(Math.random()*(a+1));
		word=("("+(i+1)+") "+a +" - "+ b +" = ")+"\n";
		return a-b;
	}
	public static void math(int n,int grade){
		String filename="out";
		  File file = new File(filename+".txt"); 
		try {
			out = new FileOutputStream(file);
			for(int i=0;i<n;i++)
		{	
				int k=(int) (Math.random()*2);
			if (k==0 && grade==1) {//进行加法计算
				intArray[i]=addition();
		}
			if(k==1 && grade==1){//进行减法计算
			intArray[i]=subtraction();
		}
			if(k==0 && grade==2) {
			intArray[i]=multiplication();
		}
			if(k==1 && grade==2){
			intArray[i]=division();
		}//标记
			try {
				out.write(word.getBytes());
			} catch (IOException e) {
			}
	}
			 String word ="\n";
			  out.write(word.getBytes());
			  //读取数据再输出一次
			  Scanner scanner0=new Scanner(file);
			  i=0;
			  while(scanner0.hasNextLine()&&(i!=n)) {
				  if(intArray1[i]==0) {
				  word =(scanner0.nextLine())+intArray[i]+"\n";
				  }
				  else {
				  word =(scanner0.nextLine())+intArray[i]+"..."+intArray1[i]+"\n"; 
				  }
				  out.write(word.getBytes());
				  i++;
			  }
			  out.close();
		} catch (IOException e) {
		}

	}
	}
