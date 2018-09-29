import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MathExam {
	static int num1,n,g,f,rem,grade,m,j,level,i;//k 设置等级
	static StringBuffer word,id;
	static OutputStream out;
	static PrintWriter p;

	static int[] str= {0,0,1,1}; // 0为+-，1为*/
	static int[] answer = new int[1000]; //存答案的数组
	static int[] sub = new int[6];//存数字的数组

	static Collection<String> list=new ArrayList<String>();

	public static int[] input(String[] args,int[] str1) {
		String regex="\\d*";
		if(args.length!=4) {//解决输入参数为多个
			System.out.println("输入的参数应为4个");
			str1[0]=0;
			return str1;
		}
		if(!args[1].matches(regex) || !args[3].matches(regex)) {//解决如果输入不为数字
			System.out.println("输入的年级数与题数应为数字");
			str1[0]=0;
			return str1;
			}
		if(!((args[0].equals("-n") && args[2].equals("-grade"))||(args[0].equals("-grade") && args[2].equals("-n")))) {
			System.out.println("查找不到-n或者-grade");
			str1[0]=0;
			return str1;
		}
		String s0=String.valueOf(args[1]);
		String s1=String.valueOf(args[3]);
		grade=Integer.parseInt(s0);
		n=Integer.parseInt(s1);
		
		if(args[0].equals("-n") && args[2].equals("-grade")) {
		n=Integer.parseInt(s0);
		grade=Integer.parseInt(s1);
		}
		if( grade>3 || grade<=0 ) {
			System.out.println("输入年级应在一到三年级");
			str1[0]=0;
			return str1;
		}
		if(n<=0||n>1000)
		{
			System.out.println("输入题目数量应该在1~1000");
			str1[0]=0;
			return str1;
		}
		str1[0]=1;
		str1[1]=n;
		str1[2]=grade;
		return str1;
	}
	
	
	//加法
	static void add(int num) {
		// TODO 自动生成的方法存根
			num1 = (int) (Math.random()*99);
			sub[m]=num1;
		
		answer[i] = num+num1;
	}
	//减法
	static void sub(int num) {
		// TODO 自动生成的方法存根
		num1 = (int) (Math.random()*(answer[i]-1));
		sub[m]=num1;

		answer[i] = num-num1;
	}
	//乘法
	static void mul(int num){
		// TODO 自动生成的方法存根
			num1 = (int) (Math.random()*99);
			sub[m]=num1;

		answer[i]= num*num1;
	}
	//除法
	static void div(int num) {
		// TODO 自动生成的方法存
		//这里写一个等级比较
				num1 = 1+(int)(Math.random()*99);
				rem=num%num1;	
				sub[m]=num1;
				if(rem>0) {
					if(level<1) {
					word.insert(word.length()-1, " - "+rem);
					word.insert(word.length(), " ÷ "+num1);
					}else {
						word.insert(0,"( ");
						word.insert(word.length(), " - "+rem+" ) ÷ "+num1+"");
					}
					answer[i]=(num-rem)/num1;
					j++;
				}else {
					word.insert(word.length()," ÷ "+num1);
					answer[i]=num/num1;}

	}
	static void operation(int n)  {
		// TODO 自动生成的方法存根
		//str数组储存了运算符的等级和符号；answer数组包含式子答案；word数组储存题目；
		//f是随机运算符的个数;num是随机的第一个数;g是选择第g个运算符;i是第i道题；j是当前符号数量
		File file = new File("out.txt"); 
		try {
			p = new PrintWriter(new FileOutputStream(file.getAbsolutePath()));
			out = new FileOutputStream(file);
			id = new StringBuffer("");
			for(int i=0;i<n;i++) {//出n道题目的循环
				m=1; //存数字个数
				level=1;
					f = 2+(int) (Math.random()*2); //f:随机抽取运算符的个数
					g = (int)(Math.random()*(str.length));//第一个运算符
					answer[i]= (int)(Math.random()*99);
					sub[0]=answer[i];
					word= new StringBuffer(answer[i]+"");
					int g1=g;
					for(j=0;j<f;j++) {
						if(level<str[g] && j>f-2 && j>=2)  break;//在需要加括号的情况下运算符不够用
						if(level<str[g]) {
							//加括号
							word.insert(0, "( ");
							word.insert(word.length(), " )");
							j++;
						}
						//这个地方的位置还要再讨论一下
						 //随机选择符号
						if(g==0) {	
							add(answer[i]);
							word.insert(word.length()," + "+num1);
						}
						else if(g==3) {
							mul(answer[i]);
							word.insert(word.length(), " x "+num1);
							
							}
						else if(g==2) {
							div(answer[i]);
						}
						else {
							sub(answer[i]);
							word.insert(word.length()," - "+num1);
						}
						m++;
						level=str[g];
						do{
						if(answer[i]>=100) {
						g = (int)(Math.random()*(str.length-1));
						}else {
						g = (int)(Math.random()*(str.length));		
						}
						
						}while(g==g1);
						g1=5;
						
						
						
					}	
				//查重
				if(examine(sub,answer[i],list)) {
				word.insert(0, "("+(i+1)+")");
				id.insert(id.length(), word+" = "+answer[i]+"\n");
				word.insert(word.length(),"\n");
				p.write(word.toString());
				}else {
					i--;
				}	
			}

			p.write("\n");
			p.write(id.toString());
			p.close();
			out.close();
			
			} catch (IOException e) {
		} 
		
	}
	
	public static boolean examine(int strs[],int answer,Collection<String> list) {
		String str1=""+answer;
		Arrays.sort(strs);
		
		for(int str : strs) {
			str1=str1+str;
		}
		if(list.contains(str1)) {
			return false;
		}
		list.add(str1);
		return true;
	}
	public static void grade(int n,int grade) {
		if(grade==1 || grade==2) {
			new MathExam6301().math(n, grade);
			return ;
		}
		operation(n);
	}
	public static void main(String[] args){
		// TODO 自动生成的方法存根
		int[] str1= new int[3];
		input(args,str1);
		if(str1[0]==0){
			return ;
		}
		n=str1[1];
		grade=str1[2];
		grade(n,grade);	
	}

}