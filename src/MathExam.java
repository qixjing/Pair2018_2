import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author LE
 *
 */
public class MathExam {
	static OutputStream out = null;
	static boolean output_boolean = false;
	public static void main(String[] args) {
			File file = File_Initialization();
			List<String> output = Input_Message(args);
			Product_Problem_Answer(Integer.valueOf(output.get(0)), Integer.valueOf(output.get(1)));
			output_boolean = true;
	}
	/**
	 * 生成number个题目，并将题目写入文本文件
	 * @return 
	 * **/
	public static boolean Product_Problem_Answer(int number, int grade) {
		List<String> Calculation_Problem = new ArrayList<String>();
		String word = null;
		Two_Numbers calc = new Two_Numbers();
		for (int i = 1; i <= number; i++) 
		{
			calc.setRemainder(0);
			word = "("+Integer.toString(i)+") " + calc.Iteration(grade);//生成一道合格的题目
			Calculation_Problem = File_Write_Problem(Calculation_Problem, calc.getSum(), calc.getRemainder(), word);//将这道题目写入文档
			if(i == number)
			{
				File_Write_Answer(Calculation_Problem);//将所有题目的答案写入文档
				System.out.println("已经生成题目，请看out.txt文档");
			}
		}
		if(number == 0)
		{
			System.out.println("因为题数为0，所以不进行生成题目！");
			return false;
		}
		return true;
	}


	/**
	 	输入符合要求的信息
	 * **/
	public static List<String> Input_Message(String[] args) {
		int j = 1, i = 1, number = -1, grade = -1;
		Scanner input = new Scanner(System.in);
		String check_input_message = null;
		String[] input_args= {"", ""};
		List<String> output = new ArrayList<String>();
		if(args[0].equals("-n") && args[2].equals("-grade"))
		{	input_args[0] = args[1];input_args[1] = args[3];}
		else if(args[0].equals("-grade") && args[2].equals("-n"))
		{	input_args[0] = args[3];input_args[1] = args[1];}
		else
			System.out.print("输入有误！！！");	
		while(number < 0 || number > 1000 || grade < 1 || grade > 3)
		{
			if(j == 1 && i == 1)
			{
				
				check_input_message = input_args[0];//题数
				j++;
			}	
			else if(j == 1 && i == 2)
			{
				check_input_message = input_args[1];//年级
				j++;
			}
			else
				check_input_message = input.nextLine();
			if(!check_input_message.matches("[0-9]{1,4}") && i == 1)
			{	
				System.out.print("输入的题数不合法！请重新输入题数(纯数字)：");
			}
			else if(!check_input_message.matches("[1-3]{1}") && i == 2)
			{
				System.out.print("输入的年级不合法!请重新输入年级(1~3)：");
			}
			else if(i == 1)
			{
				number = Integer.parseInt(check_input_message);
				if(number > 1000)
					System.out.print("输入的题数太大！请重新输入题数(0~1000以内)：");
				else if(number < 0)
					System.out.print("输入的题数为负数！请重新输入题数(0~1000以内)：");
				else
				{
					i = 2;
					j = 1;
				}	
			}	
			else if(i == 2 && j != 1)
			{
				grade = Integer.parseInt(check_input_message);
				if(grade < 1 || grade > 3)
					System.out.print("输入的年级不合法!请重新输入年级(1~3)：");
			}
		}
		output.add(String.valueOf(number));
		output.add(String.valueOf(grade));
		return output;
	}
	
	public static boolean File_Write_Answer(List<String> Calculation_Problem) {
		try {
			out.write("\r\n".getBytes());
			for (int i = 0; i < Calculation_Problem.size(); i++)
			{
				out.write(Calculation_Problem.get(i).getBytes());
			}
			return true;
		} 
		catch (IOException e) {
			System.out.println("程序在写入计算题答案时异常："+e.getMessage());
			return false;
		}
	}

	public static List<String> File_Write_Problem(List<String> Calculation_Problem, int sum, int remainder, String word) {
		try {
			out.write((word+"\r\n").getBytes());
			if(remainder!=0)
			{
				Calculation_Problem.add(word+" = "+Integer.toString(sum)+"..."+Integer.toString(remainder)+"\r\n");
			}
			else
			{	
				Calculation_Problem.add(word+" = "+Integer.toString(sum)+"\r\n");
			}
			return Calculation_Problem;
		} 
		catch (IOException e) 
		{
			System.out.println("程序在out.write()时抛出异常"+e.getMessage());
			return null;
		}
	}
	/**
	 * 生成文本文件
	 * **/
	public static File File_Initialization() {	
		String filename ="out.txt";
		File file = new File(filename);
		if(!file.exists()) 
		{		
			File parent = file.getParentFile();
			if(parent !=null && !parent.exists())
			{	parent.mkdir();//创建目录
				System.out.println("创建目录："+parent);
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("程序在file.createNewFile()时抛出异常"+e.getMessage());
				return null;
			}
			System.out.println("创建新文件："+file.getAbsolutePath());
		}//获取绝对路径
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("在new FileOutputStream(file)时抛出异常：" + e.getMessage());
		}
		return null;
	}
	

}