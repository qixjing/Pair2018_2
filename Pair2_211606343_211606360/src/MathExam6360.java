import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathExam6360 {
	static int count ;
	static int grade ;
	public static int getCount() {
		return count;
	}

	public static int getGrade() {
		return grade;
	}

	public static void setCount(int count) {
		MathExam6360.count = count;
	}

	public static void setGrade(int grade) {
		MathExam6360.grade = grade;
	}
	static String Date[] = new String[100];
	static String QT[] = new String[1000];				//定义全局变量，存放问题
	static String QT_1[] = new String[1000];
	static String ORDER[] = new String[1000];			//定义存放序号的数组。
	static String AS[] = new String[1000];				//定义全局变量，存放问题和答案
	static String SymB[] = {"+","-","×","÷"};
	static String NL ="\r\n";							//定义全局变量
	static byte[] question;							//定义全局变量
	static byte[] newline = NL.getBytes();				//定义全局变量
	static byte[] answer;								//定义全局变量
	static byte[] order;
	static byte[] date;

	
	
	 public static int Level(String operation){  //优先级判断。
	     int result;
	     switch (operation){
	         case "+":
	             result=1;
	             break;
	         case "-":
	             result=1;
	             break;
	         case "×":
	             result=2;
	             break;
	         case "÷":
	             result=2;
	             break;
	         default:
//	             System.out.println("不存在该运算符");
	             result=0;
	     }
	     return result;
	 }

	 
	public static boolean MathExam(String args[]) {		
		// TODO Auto-generated constructor stub
		boolean B;
		if(args.length == 0)
		{
			calculate(10,3);		//根据年级随机生成式子
			TxT(10);	
		}
		else if(args[0].equals("-n") && args[2].equals("-grade"))   //判断参数数量和年级的顺序。
		{
			B =judge(args[1],args[3]);	//判断参数的格式以及大小是否符合
			if(B==true) {	
				count=Integer.parseInt(args[1]);
				grade=Integer.parseInt(args[3]);
				calculate(count,grade);		//根据年级随机生成式子
				TxT(count);											//创建文本，并将式子写入文本当中		
			}else 
				{System.out.println("输入错误，没机会输入了。");
				return false;}
			
		}
		else if(args[0].equals("-grade") && args[2].equals("-n")) {
			
			B =judge(args[3],args[1]);	//判断参数的格式以及大小是否符合
			if(B==true) {	
				count=Integer.parseInt(args[3]);
				grade=Integer.parseInt(args[1]);
				calculate(count,grade);	//根据年级随机生成式子
				TxT(count);	
				//创建文本，并将式子写入文本当中		
			}else 
				{System.out.println("输入错误，没机会输入了。");
				return false;}
			
		}
		else
			{System.out.println("输入错误，没机会输入了。");
			return false;}
		return true;
	}
	

	private static void TxT(int count) {
    
		DateFormat dt = DateFormat.getDateTimeInstance(); 		//获取当前时间
		Date[0] = dt.format(new Date());
		
		File file = new File("out6343.txt");//创建文本
		try {
			FileOutputStream fos = new FileOutputStream(file);
			for(int i=0;i<count;i++) {
				ORDER[i]="("+(i+1)+") ";
				question = QT[i].getBytes();
				order = ORDER[i].getBytes();
				fos.write(order);
				fos.write(question);
				fos.write(newline);
				
			}
			
			
			for(int i=0;i<count;i++){
				ORDER[i]="("+(i+1)+") ";
				order = ORDER[i].getBytes();
				answer = AS[i].getBytes();
				fos.write(newline);
				fos.write(order);
				fos.write(answer);
				
			}
			date =Date[0].getBytes();
			fos.write(newline);
			fos.write(date);
			fos.flush();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static boolean judge(String count ,String grade) {
		String Regex="[1-9]{1}[0-9]{0,2}";   //正则表达式，将输入的参数限定在正整数范围内，同时给予最大极限。
		String Regex2="[1-3]{1}{0}";         //正则表达式，将输入的参数限定在正整数范围内，同时给予最大极限。
		
		
		Pattern p =Pattern.compile(Regex);
		Pattern p2 =Pattern.compile(Regex2);
		
		
		Matcher M=p.matcher(count);             //进行判断
		Matcher M2=p2.matcher(grade);			
		
		
		return M.matches() && M2.matches();   //返回boolean类型的值
		
	}

	
	private static void calculate(int count,int grade){
		
		if(grade == 1){ 										
			calculate_1(count);
		}
		
		
		if(grade == 2) {
			calculate_2(count);
		}
		
		if(grade == 3) {
		
			calculate_3(count);
		
		}
	}
	
	private static void  calculate_1(int count) {					//一年级计算
		for(int i=1;i<=count;) {
			int a=(int)(Math.random()*10+1);
			int b=(int)(Math.random()*10+1);
			int symbol=(int)(Math.random()*2);
		
			if(symbol==0) {
				QT[i-1]=a + " + " + b;
				QT_1[i-1]=a + "+" + b;
			}
			else if(symbol==1) {
				QT[i-1]=a + " - " + b;
				QT_1[i-1]=a + "-" + b;
			}
		List<String> ls= toInfixExpression(QT_1[i-1]);
  		List<String> rec=parseSuffixExpression(ls);
  		if(reckon(rec).equals("false")) 
  			{continue;}	
  		AS[i-1]=QT[i-1]+" = "+reckon(rec);
		i++;
		}
	}
	
	private static void  calculate_2(int count) {					//二年级计算
		for(int i=1;i<=count;) {
			
			int a=(int)(Math.random()*10);
			int b=(int)(Math.random()*10);
			int symbol=(int)(Math.random()*2);
		
			if(symbol==0) {
				QT[i-1]=a + " × " + b;
				QT_1[i-1]=a + "×" + b;
			}
			else if(symbol==1) {
				QT[i-1]=a + " ÷ " + b;
				QT_1[i-1]=a + "÷" + b;
			}
		List<String> ls= toInfixExpression(QT_1[i-1]);
	  	List<String> rec=parseSuffixExpression(ls);
	  	if(reckon(rec).equals("false")) 
	  		{continue;}	
	  	AS[i-1]=QT[i-1]+" = "+reckon(rec);
		i++;
		}
	}
	
	private static void  calculate_3(int count) {
	     int i=0;
	    while(i<count){
	 
	    	int symbol_number=(int)(Math.random()*5+2);   //随机生成数字，用于判断符号个数
	    	int number_1=(int)(Math.random()*50);
	  		int number_2=(int)(Math.random()*50+1);
	  		int number_3=(int)(Math.random()*50+1);
	  		int symbol_1=(int)(Math.random()*4);
	  		int symbol_2=(int)(Math.random()*4);
	  			
	  		if(symbol_number == 2) {
	  			if(symbol_1==symbol_2)					//保证至少有两种运算符号。
	  				{continue;}
	  			if(Level(SymB[symbol_1])<Level(SymB[symbol_2])) {
			  		QT_1[i]="("+number_1+SymB[symbol_1]+number_2+")"+SymB[symbol_2]+number_3;
			  		QT[i]="( "+number_1+" "+SymB[symbol_1]+" "+ number_2 +" ) "+SymB[symbol_2] +" " +number_3;
	  			}
	  			else if(Level(SymB[symbol_1])>Level(SymB[symbol_2]))  {
	  				QT_1[i]=+number_1+SymB[symbol_1]+"(" + number_2 +SymB[symbol_2]+number_3+")";
			  		QT[i]=number_1+" "+SymB[symbol_1]+" ( "+ number_2 +" "+SymB[symbol_2]  +number_3+" ) ";
	  			}
	  			else {
	  				QT_1[i]=+number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+number_3;
			  		QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" "+number_3;
	  			}
	  				
		  		}
	  			
	  			else if(symbol_number == 3) {
	  				if(symbol_1==symbol_2)
	  				{continue;}
	  				int number_4=(int)(Math.random()*50+1);
	  				int symbol_3=(int)(Math.random()*4);
	  				if(Level(SymB[symbol_1])<Level(SymB[symbol_2])) {
			  			QT_1[i]="("+number_1+SymB[symbol_1]+number_2+")"+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4;
			  			QT[i]="( "+number_1+" "+SymB[symbol_1]+" "+ number_2 +" ) "+SymB[symbol_2] +" " +number_3+" "+SymB[symbol_3]+" "+number_4;
	  				}
	  				else if(Level(SymB[symbol_1])>Level(SymB[symbol_2]))  {
			  			QT_1[i]=+number_1+SymB[symbol_1]+"(" + number_2 +SymB[symbol_2]+number_3+")"+SymB[symbol_3]+number_4;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" ( "+ number_2 +" "+SymB[symbol_2]  +number_3+" ) "+SymB[symbol_3]+" "+number_4;
	  				}
	  				else if(Level(SymB[symbol_2])>Level(SymB[symbol_3]))  {
			  			QT_1[i]=+number_1+SymB[symbol_1]+ number_2 +SymB[symbol_2]+"("+number_3 +SymB[symbol_3]+number_4+")";
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" ( " +number_3+" "+SymB[symbol_3]+" "+number_4+" ) ";
	  				}
	  				else {
	  					QT_1[i]=+number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" "+number_3+" "+SymB[symbol_3]+" "+number_4;
	  				}
	  				
		  			}
	  			
	  			
	  			else if(symbol_number == 4) {
	  				if(symbol_1==symbol_2)
	  				{continue;}
	  				int number_4=(int)(Math.random()*50+1);
	  				int number_5=(int)(Math.random()*50+1);
	  				int symbol_3=(int)(Math.random()*4);
	  				int symbol_4=(int)(Math.random()*4);
	  				if(Level(SymB[symbol_1])<Level(SymB[symbol_2])) {
			  			QT_1[i]="("+number_1+SymB[symbol_1]+number_2+")"+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4+SymB[symbol_4]+number_5;
			  			QT[i]="( "+number_1+" "+SymB[symbol_1]+" "+ number_2 +" ) "+SymB[symbol_2] +" " +number_3+" "+SymB[symbol_3]+" "+number_4+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  				else if(Level(SymB[symbol_1])>Level(SymB[symbol_2])) {
			  			QT_1[i]=number_1+SymB[symbol_1]+"("+number_2+SymB[symbol_2]+number_3+")"+SymB[symbol_3]+number_4+SymB[symbol_4]+number_5;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" ( "+ number_2 +" "+SymB[symbol_2]+" "+number_3+" ) "+SymB[symbol_3]+" "+number_4+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  				else if(Level(SymB[symbol_2])>Level(SymB[symbol_3])) {
			  			QT_1[i]=number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+"("+number_3+SymB[symbol_3]+number_4+")"+SymB[symbol_4]+number_5;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 + " "+SymB[symbol_2]+" ( "+number_3+" "+SymB[symbol_3]+" "+number_4+" ) "+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  				else if(Level(SymB[symbol_3])>Level(SymB[symbol_4])) {
			  			QT_1[i]=number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+number_3+SymB[symbol_3]+"("+number_4+SymB[symbol_4]+number_5+")";
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 + " "+SymB[symbol_2]+" "+number_3+" "+SymB[symbol_3]+" ( "+number_4+" "+SymB[symbol_4]+" "+number_5+" )";
	  				}
	  				else {
		  			QT_1[i]=number_1+SymB[symbol_1]+ number_2+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4+SymB[symbol_4]+number_5;
		  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" " +number_3+" "+SymB[symbol_3] +" "+number_4+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  			}
	  			else {continue;}
	  			List<String> ls= toInfixExpression(QT_1[i]);
	  			List<String> rec=parseSuffixExpression(ls);
	  			if(reckon(rec).equals("false")) {
	  				continue;
	  			}	
	  			AS[i]=QT[i]+" = "+reckon(rec);
	  			i++;
	  			 
	      	}
	     
	    }
	
	
	  public static  List<String> parseSuffixExpression(List<String> ls) {
	        Stack<String> s1=new Stack<String>();
	        List<String> LS = new ArrayList<String>();
	        for (String str : ls) {
	            if (str.matches("\\d+")) {
	                LS.add(str);
	            }
	            else if(str.equals("(")) {
	            	s1.push(str);
	            }
	            else if (str.equals(")")) {
	            	while(!s1.peek().equals("(")) {
	            		LS.add(s1.pop());
	            	}
	            	s1.pop();
	            }
	            else {
	                while (s1.size() != 0 && Level(s1.peek()) >= Level(str)) {
	                    LS.add(s1.pop());
	                }
	                s1.push(str);
	            }
	        }
	        while (s1.size() != 0) {
	            LS.add(s1.pop());
	        }
	        return LS;
	    }
	  
	
	 public static List<String> toInfixExpression(String s) {
	        List<String> ls = new ArrayList<String>();//存储中序表达式
	        int w = 0;
	        String str;
	        char c;

	        do {
	            if ((c = s.charAt(w)) < 48 || (c = s.charAt(w)) > 57) {
	                ls.add("" + c);
	               w++;
	                
	            } else {
	                str = "";
	                while (w < s.length() && (c = s.charAt(w)) >= 48
	                        && (c = s.charAt(w)) <= 57) {
	                    str += c;
	                    w++;
	                }
	                ls.add(str);
	            }

	        } while (w < s.length());
	        return ls;
	    }
	
	 public static  String reckon(List<String> ls) {
	        Stack<String> s=new Stack<String>();
	        for (String str : ls) {
	            if (str.matches("\\d+")) {
	                s.push(str);
	            } else {
	                int b = Integer.parseInt(s.pop());
	                int a = Integer.parseInt(s.pop());
	                String result="";
	                if (str.equals("+")) {
	                    result = Integer.toString(a + b);
	                } else if (str.equals("-")) {
	                	if( (a-b) <0) {
	                    	return "false";
	                    }
	                    result = Integer.toString(a - b);
	                } else if (str.equals("×")) {
	                    result =Integer.toString( a * b);
	                } else if (str.equals("÷")) {
	                	if(b==0) {
	                		return "false";
	                	}
	                	else if (grade==2 && (a%b!=0)) {
							result = Integer.toString(a / b)+"..."+Integer.toString(a % b);
						}else {
							result = Integer.toString(a / b);
						}
	                }
	                s.push("" + result);
	            }
	        }
	        return s.pop();
	    }
	 
	public static void main(String args[]) {
			MathExam(args);

	}

}
