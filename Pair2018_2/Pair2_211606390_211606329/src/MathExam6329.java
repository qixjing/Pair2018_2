import java.io.BufferedReader;					
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MathExam6329{
		static int start=1;
public static void main(String[] args)throws IOException {    
		int x=0,y=0;
		String fh="";
		if(args[0].equals("-grade")&&args[2].equals("-n"))
		{
			y=Integer.parseInt(args[1]);
			x=Integer.parseInt(args[3]);
		}
		else if(args[0].equals("-n")&&args[2].equals("-grade"))
		{
			x=Integer.parseInt(args[1]);
			y=Integer.parseInt(args[3]);
		}
		else
		{
			System.out.println("年级或题数输入格式错误\n请按'-n 题数 -grade 年级'或'-grade 年级 -n 题数'的格式进行输入");
			start=0;
		}
		if(x<=0){
			System.out.println("请输入大于0的题目数");
			start=0;
		}
		if(x>1000){
			System.out.println("题目数输入过多,请输入不大于1000的题目数");
			start=0;
		}
		if(!(y==1||y==2||y==3)){
			System.out.println("年级数请输入为1~3的整数");
			start=0;
		}
			//排错
		
if(start==1){	
	File file = new File ("out.txt");
	if(!file.exists())	
	{
	file.createNewFile();

	}	
	FileWriter fw = new FileWriter(file);
	BufferedWriter bw = new BufferedWriter(fw);
	int no=1,m=0,number = 0;
	int [] a =new int[x+1];
	int [] b =new int[x+1];
	int [] c =new int[x+1];
	//+1防止数组越界
	Random r=new Random();
	while(no<=x){
		{	
			bw.write(String.valueOf('('));
			bw.write(String.valueOf(no));
			bw.write(String.valueOf(')'));
			bw.write(String.valueOf(' '));
		}//序号
		if(y==1){
			number = (int)Math.ceil((r.nextDouble()*2.0));b[m]=number;
		for(;;){
			number = (int)Math.floor((r.nextDouble()*100.0));a[m]=number;
			number = (int)Math.floor((r.nextDouble()*100.0));c[m]=number;
			if(b[m]==1)if(a[m]+c[m]<100){fh="+";break;}
			if(b[m]==2)if(a[m]-c[m]>=0){fh="-";break;}
		}
		}
		if(y==2){number = (int)Math.ceil((r.nextDouble()*2.0));
		b[m]=number+2;
			for(;;){
				number = (int)Math.ceil((r.nextDouble()*50.0));a[m]=number;
				number = (int)Math.ceil((r.nextDouble()*20.0));c[m]=number;
				if(b[m]==3)if(a[m]*c[m]<100){fh="×";break;}
				if(b[m]==4)if(a[m]%c[m]==0&&a[m]>=c[m]){fh="÷";break;}
			
		}
		}//二年级	 	
		System.out.println("("+no+") "+a[m]+" "+fh+" "+c[m]);
		bw.write(String.valueOf(a[m]));
		bw.write(String.valueOf(' '));
		if(b[m]==1)bw.write(String.valueOf('+'));
		else if(b[m]==2)bw.write(String.valueOf('-'));
		else if(b[m]==3)bw.write(String.valueOf('×'));
		else if(b[m]==4)bw.write(String.valueOf('÷'));
		bw.write(String.valueOf(' '));
	 	bw.write(String.valueOf(c[m]));
	 	no++;
	 	m++;
	 	bw.newLine();

	}
	bw.newLine();
	System.out.println("\n");
	int i=0,z=0;
	for(no=1;i<m;i++,no++)
	{
		{			 	
			bw.write(String.valueOf('('));
			bw.write(String.valueOf(no));
			bw.write(String.valueOf(')'));
			bw.write(String.valueOf(' '));
			bw.write(String.valueOf(a[i]));
			bw.write(String.valueOf(' '));
			if(b[i]==1){bw.write(String.valueOf('+'));z=a[i]+c[i];fh="+";}
			else if(b[i]==2){bw.write(String.valueOf('-'));z=a[i]-c[i];fh="-";}
			else if(b[i]==3){bw.write(String.valueOf('×'));z=a[i]*c[i];fh="×";}
			else if(b[i]==4){bw.write(String.valueOf('÷'));z=a[i]/c[i];fh="÷";}
			bw.write(String.valueOf(' '));
		 	bw.write(String.valueOf(c[i]));
		 	bw.write(String.valueOf(' '));
		 	bw.write(String.valueOf('='));
		 	bw.write(String.valueOf(' '));
		 	bw.write(String.valueOf(z));
		 	bw.newLine();
		 	System.out.println("("+no+") "+a[i]+" "+fh+" "+c[i]+" = "+z);
		}
	}bw.close();
	}
}
}