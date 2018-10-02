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
			String filename ="out.txt";
			File_Initialization(filename);
			List<String> output = Input_Message(args);
			Product_Problem_Answer(Integer.valueOf(output.get(0)), Integer.valueOf(output.get(1)));
			output_boolean = true;
	}
	/**
	 * ����number����Ŀ��������Ŀд���ı��ļ�
	 * @return 
	 * **/
	public static boolean Product_Problem_Answer(int number, int grade) {
		List<String> Calculation_Problem = new ArrayList<String>();
		String word = "";
		Two_Numbers calc = new Two_Numbers();
		for (int i = 1; i <= number; i++) 
		{
			calc.setRemainder(0);
			calc.Iteration(grade);
			word = "("+Integer.toString(i)+") " + calc.getWord();//����һ���ϸ����Ŀ
			if(grade == 3)
				calc.setRemainder(0);
			Calculation_Problem = File_Write_Problem(Calculation_Problem, calc.getSum(), calc.getRemainder(), word);//�������Ŀд���ĵ�
			if(i == number)
			{
				File_Write_Answer(Calculation_Problem);//��������Ŀ�Ĵ�д���ĵ�
				System.out.println("�Ѿ�������Ŀ���뿴out.txt�ĵ�");
			}
		}
		if(number == 0)
		{
			System.out.println("��Ϊ����Ϊ0�����Բ�����������Ŀ��");
			return false;
		}
		return true;
	}


	/**
	 	�������Ҫ�����Ϣ
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
			System.out.print("�������󣡣���");	
		while(number < 0 || number > 1000 || grade < 1 || grade > 3)
		{
			if(j == 1 && i == 1)
			{
				
				check_input_message = input_args[0];//����
				j++;
			}	
			else if(j == 1 && i == 2)
			{
				check_input_message = input_args[1];//�꼶
				j++;
			}
			else
				check_input_message = input.nextLine();
			if(!check_input_message.matches("[0-9]{1,4}") && i == 1)
			{	
				System.out.print("������������Ϸ�����������������(������)��");
			}
			else if(!check_input_message.matches("[1-3]{1}") && i == 2)
			{
				System.out.print("������꼶���Ϸ�!�����������꼶(1~3)��");
			}
			else if(i == 1)
			{
				number = Integer.parseInt(check_input_message);
				if(number > 1000)
					System.out.print("���������̫����������������(0~1000����)��");
				else if(number < 0)
					System.out.print("���������Ϊ��������������������(0~1000����)��");
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
					System.out.print("������꼶���Ϸ�!�����������꼶(1~3)��");
			}
		}
		output.add(String.valueOf(number));
		output.add(String.valueOf(grade));
		return output;
	}
	
	public static boolean File_Write_Answer(List<String> Calculation_Problem) {
		if(Calculation_Problem.size() == 0)
			return false;
		try {
			out.write("\r\n".getBytes());
			for (int i = 0; i < Calculation_Problem.size(); i++)
			{
				out.write(Calculation_Problem.get(i).getBytes());
			}
			return true;
		} 
		catch (IOException e) {
			System.out.println("������д��������ʱ�쳣��"+e.getMessage());
		}
		return false;
	}

	public static List<String> File_Write_Problem(List<String> Calculation_Problem, int sum, int remainder, String word) {
		if(word.isEmpty())
			return null;
		try {
			out.write((word+"\r\n").getBytes());
			if(remainder!=0)
			{
				Calculation_Problem.add(word + " = " + sum + "..." + Integer.toString(remainder) + "\r\n");
			}
			else
			{	
				Calculation_Problem.add(word + " = " + sum + "\r\n");
			}
			return Calculation_Problem;
		} 
		catch (IOException e) 
		{
			System.out.println("������out.write()ʱ�׳��쳣"+e.getMessage());
			return null;
		}
	}
	/**
	 * �����ı��ļ�
	 * **/
	public static File File_Initialization(String filename) {	
		if(filename.isEmpty())
			return null;
		File file = new File(filename);
		if(!file.exists()) 
		{		
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("������file.createNewFile()ʱ�׳��쳣"+e.getMessage());
				return null;
			}
			System.out.println("�������ļ���"+file.getAbsolutePath());
		}//��ȡ����·��

		try {
			out = new FileOutputStream(file);
			return file;
		} catch (FileNotFoundException e) {
			System.out.println("��new FileOutputStream(file)ʱ�׳��쳣��" + e.getMessage());
		}
		return null;
	}
	

}