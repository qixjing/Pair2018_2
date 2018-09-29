package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MathExam211506368 {

	//扫描器，用于扫描键盘输入
	private static Scanner s = new Scanner(System.in);
	private static int firstNum_index = 0;		
	private static int secondNum_index = 2;	
	private static int  thirdNum_index = 4;

	private static int firstOperate_index = 1;		
	private static int  secondOperate_index = 3;

	private static int result_index = 5;

	public static void main(String[] args) {
		System.out.println("请输出所需要的题目数量和年级,用逗号隔开如(100,3):");
		try{

			String str = s.nextLine();
			String[] params = str.split(",");
			if(params.length == 1){
				params = str.split("，");
			}
			int count = Integer.valueOf(params[0]);
			int grade = Integer.valueOf(params[1]);
			File file = new File("D:\\java\\out.txt");
			if(!makeTxt(file)){
				throw new RuntimeException("文件创建失败");
			}
			String[][] titleArr = null;
			if(params.length >= 2){
				titleArr = buildQuestion(count, grade);
			}else{
				titleArr = buildQuestion(count);
			}
			boolean isSuccess = printQuestion(file, titleArr);
			if(isSuccess){
				System.out.println("文件及其算术创建成功！");
			}else{
				System.out.println("文件及其算术创建失败！");
			}
		}catch(InputMismatchException e){
			e.printStackTrace();
			System.out.println("文件及其算术创建失败!");
			System.out.println("输入的题目数量和年级必须为正整数!");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("文件及其算术创建失败!");
		}
	}

	/**
	 * 生成题目，默认为2年级
	 * @param count
	 * @return
	 */
	private static String[][] buildQuestion(int count){
		return buildQuestion(count, 1);
	}

	/**
	 * 生成题目
	 * @param count		题目的数量
	 * @param grade		对应的年级,年级下限制为1
	 * @return
	 */
	private static String[][] buildQuestion(int count, int grade){
		//运算符号范围
		int operate = 0;
		//如果题目数量为负数
		if(count < 0){
			count = 1;
		}
		//如果年级为负数
		if(grade <= 1){
			operate = 0;
		}else if(grade == 2){
			operate = 2;
		}else{
			operate = 3;
		}
		//基数默认为50
		int limited = 49;
		//随机数工具
		Random random = new Random();

		int firstNum = 0;			
		int secondNum = 0;			
		int  thirdNum = 0;			

		int firstOperate = 0;		
		int  secondOperate = 0;		

		int result = 0;			


		//二维数组， 其第二维度 依次存放的是  数字、运算、数字、运算、数字、结果
		String[][] titleArr = new String[count][6];
		for (String[] arr : titleArr) {
			//第一位运算数字
			firstNum = random.nextInt(99) + 1;
			//第二位运算数字
			secondNum = random.nextInt(99) + 1;
			//第三位运算数字
			thirdNum = random.nextInt(99) + 1;

			//第一位符号 0:+ 1:- 2:× 3:÷ 
			firstOperate = random.nextInt(4);
			//第二位符号 0:+ 1:- 2:× 3:÷ 4:四则运算
			secondOperate = random.nextInt(2) + operate;

			switch(secondOperate){
			case 0:
				//加法
				firstOperate = secondOperate;
				arr[firstOperate_index] = paraseOperateStr(firstOperate);
				arr[firstNum_index] = String.valueOf(firstNum);
				arr[thirdNum_index] = String.valueOf(secondNum);
				arr[result_index] = calculate(firstNum, secondNum, firstOperate);
				break;

			case 1:
				//减法
				firstOperate = secondOperate;
				arr[firstOperate_index] = paraseOperateStr(firstOperate);
				arr[firstNum_index] = String.valueOf(firstNum);
				arr[secondNum_index] = String.valueOf(secondNum);
				arr[result_index] = calculate(firstNum, secondNum, firstOperate);
				break;

			case 2:
				//乘法
				firstOperate = secondOperate;
				arr[firstOperate_index] = paraseOperateStr(firstOperate);
				arr[firstNum_index] = String.valueOf(firstNum);
				arr[secondNum_index] = String.valueOf(secondNum);
				arr[result_index] = calculate(firstNum, secondNum, firstOperate);
				break;

			case 3:
				//除法
				firstOperate = secondOperate;
				arr[firstOperate_index] = paraseOperateStr(firstOperate);
				//判断分母不能为0，如果是0，则重新取值
				while(secondNum == 0){
					secondNum = random.nextInt(limited) + 1;
				}
				//余数
				int remainder = (firstNum % secondNum);
				if(remainder == 0){
					arr[result_index] = calculate(firstNum, secondNum, firstOperate);
				}else{
					arr[result_index] = calculate(firstNum, secondNum, firstOperate) + "..." + String.valueOf(remainder);
				}
				arr[firstNum_index] = String.valueOf(secondNum);
				arr[secondNum_index] = String.valueOf(thirdNum);
				break;

			case 4:
				//运算符重新取值
				secondOperate = random.nextInt(4);
				//如果第一个符号的优先相等或高，则不用做括号处理
				if(compareOperate(firstOperate, secondOperate) >= 0){
					//如果第一位符号是除法 要做特殊处理
					if(firstOperate == 3){
						while((firstNum % secondNum) != 0 || result == 0){
							firstNum = random.nextInt(limited);
							//防止除数为0
							secondNum = random.nextInt(limited) + 1;
							result = firstNum / secondNum;
						}
						result = firstNum / secondNum;
						//第二位是乘法和除法
						if(secondOperate == 3 || secondOperate == 2){
							//如果第二位符号是除法 要做特殊处理
							if(secondOperate == 3){
								while((result % thirdNum)!=0 || result/thirdNum==0){
									thirdNum = random.nextInt(limited) + 1;
								}
							}
							//填充数据
							arr[secondNum_index] = String.valueOf(secondNum);
							arr[thirdNum_index] = String.valueOf(thirdNum);
							
							arr[result_index] = calculate(result, thirdNum, secondOperate);
						}else{
							//加法和减法的操作处理
							result = Integer.valueOf(calculate(secondNum, thirdNum, secondOperate));
							//逆推
							int temp = random.nextInt(20);
							firstNum = temp * result;
							arr[secondNum_index] = "( " + String.valueOf(secondNum);
							arr[thirdNum_index] = String.valueOf(thirdNum) + " )";
							arr[result_index] = String.valueOf(temp);
						}
					}else{
						result = Integer.valueOf(calculate(firstNum, secondNum, firstOperate));
						//填充数据
						arr[secondNum_index] = String.valueOf(secondNum);
						arr[thirdNum_index] = String.valueOf(thirdNum);
						arr[result_index] = calculate(result, thirdNum, secondOperate);
					}
					arr[firstNum_index] = String.valueOf(firstNum);
					arr[firstOperate_index] = paraseOperateStr(firstOperate);
					arr[secondOperate_index] = paraseOperateStr(secondOperate);
				}else{
					//优先级低的处理，既加括号
					result = Integer.valueOf(calculate(secondNum, thirdNum, secondOperate));
					if(firstOperate == 3){
						//如果此时result等于0，则重新取值
						while(result == 0){
							secondNum = random.nextInt(limited);
							//防止出现除数为0的情况
							thirdNum = random.nextInt(limited) +1;
							result = Integer.valueOf(calculate(secondNum, thirdNum, secondOperate));
						}
						//此时除数可能很大，所以应该逆推
						int temp = random.nextInt(20);
						
						arr[firstNum_index] = calculate(result, temp, 2);
						arr[result_index] = String.valueOf(temp);
					}else{
						arr[firstNum_index] = String.valueOf(firstNum);
						arr[result_index] = calculate(firstNum, result, firstOperate);
					}
					arr[secondNum_index] = String.valueOf(secondNum);
					arr[thirdNum_index] = String.valueOf(thirdNum);
					arr[firstOperate_index] = paraseOperateStr(firstOperate);
					arr[secondOperate_index] = paraseOperateStr(secondOperate);
				}
				break;
			}

		}
		return titleArr;

	}

	private static boolean printQuestion(File file, String[][] titleArr){
		// 仅带有问题的字符缓存器
		StringBuilder buff = new StringBuilder();
		//携带问题和答案的字符缓存器
		StringBuilder answerbuff = new StringBuilder();
		//换行符
		String newLine = "\r\n";
		//"------------------标准答案------------------"字符
		String divideStr = "------------------标准答案------------------\r\n";
		// "211606359 戴俊涵 yyyy年MM月dd日 HH：mm"
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日\tHH:mm");
		String anthorInfo = "\t\t211506368 \t\t霍金池\t\t" + sdf.format(new Date());

		for(int i =1; i<=titleArr.length; i++){
			String[] arr = titleArr[i-1];
			String str = "("+ i + ") ";
			for(int j = 0; j < 5; j++){
				if(arr[j] != null){
					str += arr[j];
				}
			}
			str += " =";
			buff.append(str + newLine);
			answerbuff.append(str + " " + arr[result_index] + newLine);
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(buff.toString().getBytes());
			fos.write(divideStr.getBytes());
			fos.write(answerbuff.toString().getBytes());
			fos.write(anthorInfo.getBytes());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("文件未找到异常", e);
		}  catch (IOException e) {
			throw new RuntimeException("输入或输出流发生异常", e);
		}
		finally{
			try {
				fos.close();
			} catch (IOException e) {
				throw new RuntimeException("输出流关闭发生异常", e);
			}
		}
		return true;
	}

	/**
	 * 检查文件是否存在
	 * @param f				文件目录
	 * @param fileName		文件名称
	 * @return				目录创建成功则返回true，否则返回false。
	 */
	private static boolean makeTxt(File file){
		//如果目录不存在，则创建父级目录
		if(!file.exists()){
			file.getParentFile().mkdirs();
		}

		//如果文件不存在，则创建文件。
		if(! file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException("文件创建失败", e);
			}
		}

		//清空文本数据，防止出现乱数据
		FileWriter fileWrite = null;
		try {
			fileWrite = new FileWriter(file);
			fileWrite.write("");
			fileWrite.flush();
		} catch (IOException e) {
			throw new RuntimeException("文件清空发生异常", e);
		}finally{
			try {
				fileWrite.close();
			} catch (IOException e) {
				throw new RuntimeException("文件写入流关闭发生异常", e);
			}
		}

		return file.exists();
	}

	/**
	 * 比较优先级
	 * @param first			第一位参数
	 * @param second		第二位参数
	 * @return				0： 相等     1：优先    -1：殿后
	 */
	private static int compareOperate(int first, int second){
		// 0:+ 1:- 2:× 3:÷ 
		int f =  first <= 1 ? 0 : 1;
		int s = second <= 1 ? 0 : 1;
		return f-s;
	}

	/**
	 * 将数字转成符号
	 * @param operate		数字
	 * @return
	 */
	private static String paraseOperateStr(int operate){
		if(operate == 0){
			return " + ";
		}else if(operate == 1){
			return " - ";
		}else if(operate == 2){
			return " × ";
		}else if(operate == 3){
			return " ÷ ";
		}else{
			throw new RuntimeException("无效的符号数字");
		}
	}

	/**
	 * 计算出first和second之间的operate运算的字符串结果
	 * @param first			第一位参数
	 * @param second		第二位参数
	 * @param operate		运算
	 * @return				结果的字符串表示形式
	 */
	private static String calculate(int first, int second, int operate){
		if(operate == 0){
			return String.valueOf(first + second);
		}else if(operate == 1){
			return String.valueOf(first - second);
		}else if(operate == 2){
			return String.valueOf(first * second);
		}else if(operate == 3){
			return String.valueOf(first / second);
		}else{
			throw new RuntimeException("计算传入的参数有误!");
		}
	}
}
